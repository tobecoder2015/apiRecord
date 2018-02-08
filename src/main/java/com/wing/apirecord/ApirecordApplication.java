package com.wing.apirecord;

import com.wing.apirecord.core.NettyHttpProxyServer;
import com.wing.apirecord.core.filter.UrlFilter;
import com.wing.apirecord.core.intercept.HttpProxyIntercept;
import com.wing.apirecord.core.model.Request;
import com.wing.apirecord.core.model.Response;
import com.wing.apirecord.core.record.Message;
import com.wing.apirecord.core.record.RecordMap;
import com.wing.apirecord.core.record.RecordQueue;
import com.wing.apirecord.core.tools.TransferUtil;
import com.wing.apirecord.core.filter.ContentTypeFilter;
import com.wing.apirecord.core.filter.FilterChain;
import com.wing.apirecord.core.filter.MethodFilter;
import com.wing.apirecord.service.FileService;
import com.wing.apirecord.service.FilterService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.Charset;

@SpringBootApplication
@EnableAutoConfiguration
@Slf4j
public class ApirecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirecordApplication.class, args);

		System.setProperty("proxySet", "true");
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "9999");
		log.info("本地代理服务器ip:"+System.getProperty("http.proxyHost"));
		log.info("本地代理服务器port:"+System.getProperty("http.proxyPort"));

		//配置文件
		RecordQueue.getInstance().start();
		FilterChain.addFilter(new ContentTypeFilter(FilterService.contentType));
		FilterChain.addFilter(new UrlFilter(FilterService.urls));
		FilterChain.addFilter(new MethodFilter(FilterService.methods));

		FileService.setSavePath(null);

		new NettyHttpProxyServer().initProxyInterceptFactory(() -> new HttpProxyIntercept() {

			@Override
			public boolean beforeRequest(Channel clientChannel, HttpRequest httpRequest) {
				log.debug("request "+clientChannel.id().asShortText()+"   "+httpRequest.toString());

				Request request = TransferUtil.toRequest(httpRequest);
				Message message = new Message(clientChannel.id().asShortText(), Message.REQUEST_HEAD, request);
				FilterChain filterChain=FilterChain.getFilterChain();
				if (filterChain.doFilter(message, filterChain)){
					//
				} else {
					RecordQueue.getInstance().add(message);
				}

				return true;
			}

			@Override
			public boolean beforeRequest(Channel clientChannel, HttpContent httpContent) {
				log.debug("request httpContent:"+clientChannel.id().asShortText()+"   "+clientChannel.id().asShortText()+"  "+httpContent.content().toString(Charset.defaultCharset()));

				if(!RecordMap.containKey(clientChannel.id().asShortText()))
					return true;
				Message message = new Message(clientChannel.id().asShortText(), Message.REQUEST_BODY, httpContent.content().toString(io.netty.util.CharsetUtil.UTF_8));
				FilterChain filterChain=FilterChain.getFilterChain();
				if (filterChain.doFilter(message, filterChain)){
					//
				} else {
					RecordQueue.getInstance().add(message);
				}

				return true;
			}

			@Override
			public boolean afterResponse(Channel clientChannel, Channel proxyChannel, HttpResponse httpResponse) {
				log.debug("httpResponse:"+clientChannel.id().asShortText()+"  "+httpResponse.toString());
				if(!RecordMap.containKey(clientChannel.id().asShortText()))
					return true;
				Response response = TransferUtil.toReponse(httpResponse);
				Message message = new Message(clientChannel.id().asShortText(), Message.RESPONSE_HEAD, response);
				FilterChain filterChain=FilterChain.getFilterChain();
				if (filterChain.doFilter(message, filterChain)){
					message.setFilter(true);
					RecordQueue.getInstance().add(message);
				} else {
					RecordQueue.getInstance().add(message);
				}
				return true;
			}

			@Override
			public boolean afterResponse(Channel clientChannel, Channel proxyChannel, HttpContent httpContent){
				log.debug("httpContent:"+clientChannel.id().asShortText()+"  "+httpContent.content().toString(Charset.defaultCharset()));

				if(!RecordMap.containKey(clientChannel.id().asShortText()))
					return true;

				ByteBuf buf = httpContent.content().copy();
				byte[] bytes = new byte[buf.readableBytes()];
				buf.readBytes(bytes);
				Message message = new Message(clientChannel.id().asShortText(), Message.RESPONSE_BODY, bytes);
				buf.writeBytes(bytes);
				if(httpContent instanceof LastHttpContent)
					message.setFinish(true);

				FilterChain filterChain=FilterChain.getFilterChain();
				if (filterChain.doFilter(message, filterChain)){
					//
				} else {
					RecordQueue.getInstance().add(message);
				}

				return true;
			}
			@Override
			public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
				ctx.fireExceptionCaught(cause);
//				log.error("异常",cause);
			}


		}).start(9999);



	}
}

package com.wing.apirecord.core.handler;

import com.wing.apirecord.core.NettyHttpProxyServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpClientCodec;

public class HttpProxyInitializer extends ChannelInitializer{

    private Channel clientChannel;
    private boolean isSSL;

    public HttpProxyInitializer(Channel clientChannel,boolean isSSL) {
        this.clientChannel = clientChannel;
        this.isSSL = isSSL;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        if(isSSL){
            ch.pipeline().addLast(NettyHttpProxyServer.clientSslCtx.newHandler(ch.alloc()));
        }
        ch.pipeline().addLast("httpCodec",new HttpClientCodec());
        ch.pipeline().addLast(new HttpProxyClientHandle(clientChannel));
    }
}

package com.wing.apirecord.core.filter;

import com.wing.apirecord.core.model.Request;
import com.wing.apirecord.core.record.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UrlFilter implements Filter {
    private String pattern;
    public UrlFilter(String pattern){
        this.pattern=pattern;
    }

    @Override
    public boolean doFilter(Message message, FilterChain chain) {
        if (message.getMsg() instanceof Request) {
            Request request=(Request)message.getMsg();
            String[] urls = pattern.split(",");

            if (request.getHost().contains("127.0.0.1")||request.getHost().contains("localhost")) {
                return true;
            }

            boolean ok = true;
            for (String url : urls) {
                if (request.getHost().contains(url)) {
                    ok = false;
                    break;
                }
            }
            if (!ok) {
                return chain.doFilter(message, chain);
            } else {
                log.debug("消息体不满足url过滤器 " + request.getHost());
                return true;
            }
        }
        return chain.doFilter(message, chain);
    }
}

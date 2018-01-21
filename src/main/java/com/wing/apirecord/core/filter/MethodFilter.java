package com.wing.apirecord.core.filter;

import com.wing.apirecord.core.model.Request;
import com.wing.apirecord.core.record.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MethodFilter implements Filter {
    private String pattern;
    public MethodFilter(String pattern){
        this.pattern=pattern;
    }

    @Override
    public boolean doFilter(Message message, FilterChain chain) {
        if (message.getMsg() instanceof Request) {
            Request request=(Request)message.getMsg();
            String[] methods=pattern.split(",");
            boolean ok=true;
            for (String method:methods){
                if(request.getMethod().equalsIgnoreCase(method)) {
                    ok=false;
                    break;
                }
            }
            if(!ok){
                return chain.doFilter(message, chain);
            }else {
                log.debug("消息体不满足Method过滤器 " + request.getHost());
                return true;
            }

        }
        return chain.doFilter(message, chain);
    }
}

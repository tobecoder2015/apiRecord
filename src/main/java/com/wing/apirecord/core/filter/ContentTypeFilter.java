package com.wing.apirecord.core.filter;

import com.wing.apirecord.core.model.Response;
import com.wing.apirecord.core.record.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContentTypeFilter implements Filter {
    private String pattern="text/html;";
    public ContentTypeFilter(String pattern){
        this.pattern=pattern;
    }

    @Override
    public boolean doFilter(Message message, FilterChain chain) {
        if (message.getMsg() instanceof Response) {
            Response response=(Response)message.getMsg();
            String[] contentTypes = pattern.split(",");


            boolean ok = true;
            for (String contentType : contentTypes) {
                if (!response.getHeaders().containsKey("Content-Type")||response.getHeaders().containsKey("Content-Type")&&response.getHeaders().get("Content-Type").contains(contentType)) {
                    ok = false;
                    break;
                }
            }
            if (!ok) {
                return chain.doFilter(message, chain);
            } else {
                log.debug("消息体不满足contentType过滤器 " + response.getHeaders().get("Content-Type"));
                return true;
            }
        }
        return chain.doFilter(message, chain);
    }
}

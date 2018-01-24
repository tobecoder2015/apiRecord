package com.wing.apirecord.service;

import com.wing.apirecord.controller.ConfigCenter;
import com.wing.apirecord.core.filter.ContentTypeFilter;
import com.wing.apirecord.core.filter.FilterChain;
import com.wing.apirecord.core.filter.MethodFilter;
import com.wing.apirecord.core.filter.UrlFilter;
import com.wing.apirecord.utils.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FilterService {

    public static String urls= ConfigUtil.getConfig("urls");
    public static String methods=ConfigUtil.getConfig("methods");
    public static String contentType=ConfigUtil.getConfig("contentType");

    public void setFilter(){
        FilterChain.clear();
        FilterChain.addFilter(new ContentTypeFilter(contentType));
        FilterChain.addFilter(new UrlFilter(urls));
        FilterChain.addFilter(new MethodFilter(methods));
    }
}

package com.wing.apirecord.service;

import com.wing.apirecord.controller.ConfigCenter;
import com.wing.apirecord.core.filter.ContentTypeFilter;
import com.wing.apirecord.core.filter.FilterChain;
import com.wing.apirecord.core.filter.MethodFilter;
import com.wing.apirecord.core.filter.UrlFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FilterService {

    public static String urls="3027-mgtoo-sl-mta,api";
    public static String methods="get,post";
    public static String contentType="application/json,text/plain,text/html,application/xhtml+xml,application/xml";


    public void setFilter(){
        FilterChain.clear();
        FilterChain.addFilter(new ContentTypeFilter(contentType));
        FilterChain.addFilter(new UrlFilter(urls));
        FilterChain.addFilter(new MethodFilter(methods));
    }
}

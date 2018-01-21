package com.wing.apirecord.core.filter;

import com.wing.apirecord.core.record.Message;

public interface Filter {
    boolean doFilter(Message message, FilterChain chain);
}

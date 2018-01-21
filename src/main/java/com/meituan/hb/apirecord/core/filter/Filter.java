package com.meituan.hb.apirecord.core.filter;

import com.meituan.hb.apirecord.core.record.Message;

public interface Filter {
    boolean doFilter(Message message, FilterChain chain);
}

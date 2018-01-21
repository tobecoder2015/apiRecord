package com.meituan.hb.apirecord.core.intercept;

/**
 * 拦截器初始化接口
 */
public interface ProxyInterceptFactory {
    HttpProxyIntercept build();
}

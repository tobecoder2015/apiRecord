package com.meituan.hb.apirecord.core.intercept;

public class DefaultInterceptFactory implements ProxyInterceptFactory {
    @Override
    public HttpProxyIntercept build() {
        return new HttpProxyIntercept();
    }
}

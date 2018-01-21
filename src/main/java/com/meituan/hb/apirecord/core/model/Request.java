package com.meituan.hb.apirecord.core.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
public class Request {

    private String method="GET";// 请求方法
    private String scheme = "http";// 协议版本
    private String path = "/";//请求路径，不带有参数
    private String url = "/";//请求URL，带有参数
    private String host="localhost";//请求的主机信息
    private int port = 80;//请求的主机信息
    private Map<String, String> headers; //请求header
    private Map<String, String> queryParaMap; //请求参数的键值对
    private List<String> queryParaPath; //请求参数的路径参数
    private String body=""; //body体

    public  void addHeader(String key,String value) {
        if(headers==null){
            headers=new HashMap<>();
        }
        headers.put(key,value);
    }
    @Override
    public  String toString() {
        return "url:" + this.getUrl() +"  body:"+this.getBody()+"   scheme:" + this.getScheme() + " host:" + this.getHost()
                + " port:" + this.getPort() +"  header:"+this.getHeaders()+"    path:"+this.getPath()+" paramap:"+this.getQueryParaMap();

    }

}


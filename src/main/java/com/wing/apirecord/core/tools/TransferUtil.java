package com.wing.apirecord.core.tools;

import com.wing.apirecord.core.model.Request;
import com.wing.apirecord.core.model.Response;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class TransferUtil {

    public static  Request toRequest(HttpRequest httpRequest){
        Request request=new Request();
        request.setMethod(httpRequest.method().name());
        Iterator headers=httpRequest.headers().iteratorAsString();
        while(headers.hasNext()){
            Map.Entry<String, String> header=(Map.Entry<String, String>)headers.next();
            request.addHeader(header.getKey(),header.getValue());

        }

        String uri=httpRequest.uri();
        request.setUrl(uri);
        int index=uri.indexOf("//");
        if(uri.indexOf("//")==-1){
            return request;
        }
        index+=2;
        int hostindex=uri.indexOf("/",index);

        String host = uri.substring(index,hostindex);

        if (host != null) {
            host=host.trim();
            if (host.contains(":")) {
                request.setHost(host.split(":")[0]);
                request.setPort(Integer.parseInt(host.split(":")[1]));
            } else {
                request.setHost(host);
            }

        }
        String url = uri.substring(hostindex);
        if (url.contains("?")) {
            int splitindex = url.indexOf("?");

            request.setPath(url.substring(0, splitindex));
            String queryMapsLine = url.substring(splitindex+1, url.length());
            String[] queryParaMaps = queryMapsLine.split("&");
            Map<String, String> queryParaMap = new HashMap<>();
            for (int i = 0; i < queryParaMaps.length; i++) {
                int parasplitindex = queryParaMaps[i].indexOf("=");
                if(parasplitindex<0){
                    log.warn(queryParaMaps[i]);
                    continue;
                }
                if (parasplitindex == queryParaMaps[i].length() - 1)
                    queryParaMap.put(queryParaMaps[i].substring(0, parasplitindex), "");
                else
                    queryParaMap.put(queryParaMaps[i].substring(0, parasplitindex), queryParaMaps[i].substring(parasplitindex + 1, queryParaMaps[i].length()));

            }
            request.setQueryParaMap(queryParaMap);

        } else {
            request.setPath(url);
        }
        return request;
    }


    public static  Response toReponse(HttpResponse httpResponse){
        Response response=new Response();
        response.setCode(httpResponse.status().code());
        Iterator headers=httpResponse.headers().iteratorAsString();
        while(headers.hasNext()){
            Map.Entry<String, String> header=(Map.Entry<String, String>)headers.next();
            response.addHeader(header.getKey(),header.getValue());
        }
        return response;
    }
}

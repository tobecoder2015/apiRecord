package com.wing.apirecord.service;

import com.wing.apirecord.core.record.Record;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlGen {

    public static void processUrl(Record record){
        String url =record.getRequest().getPath();

        if(!url.endsWith("/")){
            url=url+"/";
        }
       Map<String,String> queryParaPath=record.getRequest().getQueryParaPath();

        Pattern p = Pattern.compile("/([0-9]+)/");
        Matcher m = p.matcher(url);
        int index=0;
        while(m.find()){
            if(queryParaPath==null)
                queryParaPath=new HashMap<>();
            String pv=  m.group();
            url=url.replaceFirst(pv,"/{pathPara"+index+"}/");
            pv=pv.substring(1,pv.length()-1);
            queryParaPath.put("pathPara"+index,pv);
            index++;
        }
        url=url.substring(0,url.length()-1);
        record.getRequest().setQueryParaPath(queryParaPath);
        record.getRequest().setPath(url);
    }

}

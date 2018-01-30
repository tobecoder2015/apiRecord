package com.wing.apirecord.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wing.apirecord.core.record.Record;
import com.wing.apirecord.utils.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class QueryGen {

    public String getQuery(Record record){
        JSONArray queryPara=new JSONArray();
        JSONObject oneQueryPara=new JSONObject();
        oneQueryPara.put("comments","自动生成");
        JSONObject requset=new JSONObject();
        JSONObject query=null;

        if(record.getRequest().getQueryParaMap()!=null||record.getRequest().getQueryParaPath()!=null){
            Map<String,String> para=new HashMap<>();
            if(record.getRequest().getQueryParaPath()!=null)
                para.putAll(record.getRequest().getQueryParaPath());
            if(record.getRequest().getQueryParaMap()!=null)
                para.putAll(record.getRequest().getQueryParaMap());
             query=(JSONObject) JSONObject.toJSON(para);
            requset.put("query",query);
        }else{
            requset.put("query",new JSONObject());
        }

        if(StringUtils.isNotBlank(record.getRequest().getBody())){
            requset.put("body",JSONObject.parseObject(record.getRequest().getBody()));
        }else{
            requset.put("body",new JSONObject());
        }

        oneQueryPara.put("request",requset);
        oneQueryPara.put("response",new JSONObject());

        queryPara.add(oneQueryPara);
        return JsonFormat.format("["+oneQueryPara.toJSONString().replace("\\\"","\"")+"]");

    }

}

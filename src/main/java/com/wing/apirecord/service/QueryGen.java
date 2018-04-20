package com.wing.apirecord.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wing.apirecord.core.record.Record;
import com.wing.apirecord.utils.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class QueryGen {
    @Resource
    FileService fileService;

    public String addQueryData(Record record,String fileName) {
        JSONArray queryPara = fileService.getData(fileName);
       JSONObject newData= makdeQueryData(record);
       if(queryPara.contains(newData)){
           log.warn("数据已存在");
       }else{
           queryPara.add(newData);
       }

        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for(int i=0;i<queryPara.size();i++) {
            JSONObject oneQueryPara=queryPara.getJSONObject(i);
            sb.append(" {\n");
            sb.append("     \"request\":{\n");
            if (oneQueryPara.getJSONObject("request").containsKey("query"))
                sb.append("         \"query\":" + JSON.toJSONString(oneQueryPara.getJSONObject("request").getJSONObject("query"), SerializerFeature.WriteMapNullValue) + ",\n");
            if (oneQueryPara.getJSONObject("request").containsKey("body"))
                sb.append("         \"body\":" + JSON.toJSONString(oneQueryPara.getJSONObject("request").getJSONObject("body"), SerializerFeature.WriteMapNullValue) + ",\n");
            sb.append("     },\n");
            sb.append("     \"comments\":\"自动生成\",\n");
            sb.append("     \"response\":" + oneQueryPara.getJSONObject("response").toJSONString() + "\n");
            sb.append(" }\n");
        }
        sb.append("]\n");

        return sb.toString();
    }

    public String getQueryData(Record record) {

        JSONObject oneQueryPara=makdeQueryData(record);
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        sb.append(" {\n");
        sb.append("     \"request\":{\n");
        if(oneQueryPara.getJSONObject("request").containsKey("query"))
            sb.append("         \"body\":" +JSON.toJSONString(oneQueryPara.getJSONObject("request").getJSONObject("query"), SerializerFeature.WriteMapNullValue)+ ",\n");
        if(oneQueryPara.getJSONObject("request").containsKey("body"))
            sb.append("         \"body\":" +JSON.toJSONString(oneQueryPara.getJSONObject("request").getJSONObject("body"), SerializerFeature.WriteMapNullValue)+ ",\n");
        sb.append("     },\n");
        sb.append("     \"comments\":\"自动生成\",\n");
        sb.append("     \"response\":" + oneQueryPara.getJSONObject("response").toJSONString() + "\n");
        sb.append(" }\n");
        sb.append("]\n");

        return sb.toString();
    }

        public JSONObject makdeQueryData(Record record) {

        JSONObject oneQueryPara = new JSONObject();
        oneQueryPara.put("comments", "自动生成");
        JSONObject requset = new JSONObject();
        JSONObject query = null;

        if (record.getRequest().getQueryParaMap() != null || record.getRequest().getQueryParaPath() != null) {
            Map<String, String> para = new HashMap<>();
            if (record.getRequest().getQueryParaPath() != null)
                para.putAll(record.getRequest().getQueryParaPath());
            if (record.getRequest().getQueryParaMap() != null)
                para.putAll(record.getRequest().getQueryParaMap());
            query = (JSONObject) JSONObject.toJSON(para);
            requset.put("query", query);
        }

        if (StringUtils.isNotBlank(record.getRequest().getBody())) {
            requset.put("body", JSONObject.parseObject(record.getRequest().getBody()));
        }

        if(!requset.containsKey("query")&&!requset.containsKey("body"))
            requset.put("query", new JSONObject());

        oneQueryPara.put("request", requset);
        oneQueryPara.put("response", JSONObject.parseObject(record.getResponse().getBody()));

        return oneQueryPara;

//        return JsonFormat.format("["+oneQueryPara.toJSONString()+"]");//.replace("\\\"","\"")

    }

}

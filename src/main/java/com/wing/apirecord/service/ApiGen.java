package com.wing.apirecord.service;

import com.alibaba.fastjson.JSON;
import com.wing.apirecord.core.record.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Map;

@Component
@Slf4j
public class ApiGen {

    @Resource
    FileService fileService;

    @Resource
    NameGen nameGen;

    public String apiClass(Record record) throws Exception {

        String packageName = nameGen.getPackageName(record);
        String methodName = nameGen.getMethodName(record);
        String apiClass=fileService.getApiDef();
        if(apiClass==null) {
            apiClass = fileService.getTemplates(FileService.API_CLASS);
            apiClass=apiClass.replace("{packageName}",packageName);
        }
        if(!fileService.isExsit(methodName)) {
            apiClass = apiClass.replace("//{apiDef}", apiDef(record) + "\n\n      //{apiDef}");
            apiClass = apiClass.replace("//{apiMethod}", apiMethod(record) + "\n\n        //{apiMethod}");
        }
        return apiClass;
    }

    public String apiDef(Record record) {
        String methodName = nameGen.getMethodName(record);
        String pathPara=apiPathPara(record);
        String apiDef="";
        if (record.getRequest().getMethod().equalsIgnoreCase("post")) {
            apiDef=fileService.getTemplates(FileService.API_DEF_POST);
        }else{
            apiDef=fileService.getTemplates(FileService.API_DEF_GET);
        }
        apiDef=apiDef.replace("{path}",record.getRequest().getPath());
        apiDef=apiDef.replace("{methodName}",methodName);
        apiDef=apiDef.replace("{pathPara}",pathPara);
        return apiDef;

    }

    private String apiPathPara(Record record)  {
        Map<String,String> pathpara=record.getRequest().getQueryParaPath();
        StringBuilder sb=new StringBuilder();
        if(pathpara!=null){
            Iterator it=pathpara.keySet().iterator();
            while (it.hasNext()){
                String key=(String) it.next();
                sb.append("@Path(\""+key+"\") String "+key+" ,");
            }
        }
        return sb.toString();
    }


    private String apiParaProcess(Record record)  {
        Map<String,String> pathpara=record.getRequest().getQueryParaPath();
        StringBuilder sb=new StringBuilder();
        if(pathpara!=null){
            Iterator it=pathpara.keySet().iterator();
            while (it.hasNext()){
                String key=(String) it.next();
                sb.append("String "+key+"=request.getJSONObject(\"query\").getString(\""+key+"\");\n");
                sb.append("request.getJSONObject(\"query\").remove(\""+key+"\");\n");

            }
        }
        return sb.toString();
    }

    private String pathParaKey(Record record)  {
        Map<String,String> pathpara=record.getRequest().getQueryParaPath();
        StringBuilder sb=new StringBuilder();
        if(pathpara!=null){
            Iterator it=pathpara.keySet().iterator();
            while (it.hasNext()){
                sb.append(it.next()+" ,");
            }
        }
        return sb.toString();
    }


    public String apiMethod(Record record) {
        String methodName = nameGen.getMethodName(record);
        String method=null;
        if (record.getRequest().getMethod().equalsIgnoreCase("post")) {
           method= fileService.getTemplates(FileService.API_METHOD_POST);
        }else {
            method= fileService.getTemplates(FileService.API_METHOD_GET);
        }

        method=method.replace("{methodName}",methodName);
        method=method.replace("{apiParaProcess}",apiParaProcess(record));
        method=method.replace("{pathParaKey}",pathParaKey(record));
        return method;
    }

}

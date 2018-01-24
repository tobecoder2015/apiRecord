package com.wing.apirecord.service;

import com.mashape.unirest.http.Unirest;
import com.wing.apirecord.core.record.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchemaGen {

    public String getSchema(Record record){
        String result=record.getResponse().getBody();
        try {
            return "["+Unirest.post("http://apidiffy.sankuai.com/quicktest/generateschema").field("json",result).asJson().getBody().getObject().getString("schema")+"]";
        }catch (Exception e){
            log.error("获取schema 出错",e);
            return e.getMessage();
        }
    }

}

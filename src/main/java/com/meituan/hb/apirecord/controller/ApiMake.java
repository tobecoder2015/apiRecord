package com.meituan.hb.apirecord.controller;

import com.meituan.hb.apirecord.core.record.Record;
import com.meituan.hb.apirecord.core.record.RecordMap;
import com.meituan.hb.apirecord.service.ApiGen;
import com.meituan.hb.apirecord.service.MethodGen;
import com.meituan.hb.apirecord.service.QueryGen;
import com.meituan.hb.apirecord.service.SchemaGen;
import com.meituan.hb.apirecord.utils.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ApiMake {
    @Resource
    SchemaGen schemaGen;
    @Resource
    QueryGen queryGen;
    @Resource
    ApiGen apiGen;
    @Resource
    MethodGen methodGen;

    @GetMapping(path = "dump")
    public String dumpReord(){
       return RecordMap.dump();
    }


    @GetMapping(path = "index")
    public ModelAndView index(){
        return new ModelAndView("index", "records", RecordMap.getRecord());

    }


    @GetMapping(path = "api/{id}")
    public ModelAndView api(@PathVariable int id ){
        Record record=RecordMap.getRecord().get(id-1);
        log.info("请求参数为："+id);
        Map api=new HashMap();
        api.put("apiDef",apiGen.apiDef(record));
        api.put("apiMethod",apiGen.apiMethod(record));

        api.put("query",queryGen.getQuery(record));
        api.put("schema",schemaGen.getSchema(record));
        api.put("method",methodGen.method(record));

//        api.put("schema", JsonFormat.format((String)api.get("schema")));
//        api.put("query", JsonFormat.format((String)api.get("query")));
        return new ModelAndView("api", "api", api);

    }
}

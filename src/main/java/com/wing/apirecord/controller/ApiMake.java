package com.wing.apirecord.controller;

import com.wing.apirecord.core.record.Record;
import com.wing.apirecord.core.record.RecordMap;
import com.wing.apirecord.service.*;
import lombok.extern.slf4j.Slf4j;
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

    @Resource
    FileService fileService;

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
        Map api=new HashMap();
        api.put("apiDef",apiGen.apiDef(record));
        api.put("apiMethod",apiGen.apiMethod(record));

        api.put("query",queryGen.getQuery(record));
        api.put("schema",schemaGen.getSchema(record));
        api.put("method",methodGen.method(record));
        return new ModelAndView("api", "api", api);
    }

    @GetMapping(path = "api/{id}/write")
    public String apiWrite(@PathVariable int id ){
        Record record=RecordMap.getRecord().get(id-1);
        try {
            String fileName=NameGen.getClassName(record);
            fileService.saveSchema(fileName,schemaGen.getSchema(record));
            fileService.saveCodeModuleTestsuites(fileName,methodGen.method(record));
            fileService.saveData(fileName,queryGen.getQuery(record));
            return "写入文件成功："+FileService.saveCodeBase;

        }catch (Exception e){
            return e.getMessage();
        }
    }
}

package com.wing.apirecord.controller;


import com.alibaba.fastjson.JSONObject;
import com.wing.apirecord.service.CodeService;
import com.wing.apirecord.service.FileService;
import com.wing.apirecord.service.FilterService;
import com.wing.apirecord.utils.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
@Slf4j
public class ConfigCenter {


    @Resource
    FilterService filterService;


    @Resource
    CodeService codeService;


    @GetMapping(path = "/config")
    public ModelAndView config(){
        try {

            Map config=new HashMap<>();
            config.put("contentType",FilterService.contentType);
            config.put("methods",FilterService.methods);
            config.put("urls",FilterService.urls);
            config.put("codeModule",FileService.saveCodeModule);
            config.put("codeRoot",FileService.saveCodeRoot);

            return   new ModelAndView("config", "config",config);
        }catch (Exception e){
            log.error( "服务器出错：",e);
            ErrorMsg errorMsg=new ErrorMsg(e.getMessage(),1);
            return   new ModelAndView("error", "errorMsg",errorMsg);

        }
    }


    @PostMapping(path = "/config/update")
    @ResponseBody
    public String configUpdate(@RequestParam String contentType,@RequestParam String methods,@RequestParam String urls
            ,@RequestParam String codeModule,@RequestParam String codeRoot){
        try {
            FilterService.contentType=contentType;
            FilterService. methods=methods;
            FilterService.urls=urls;
            filterService.setFilter();

            FileService.saveCodeModule=codeModule;
            FileService.saveCodeRoot=codeRoot;
            FileService.updateSavePath();

            ConfigUtil.setConfig("contentType",contentType);
            ConfigUtil.setConfig("methods",methods);
            ConfigUtil.setConfig("urls",urls);
            ConfigUtil.setConfig("saveCodeModule",codeModule);
            ConfigUtil.setConfig("saveCodeRoot",codeRoot);
            ConfigUtil.saveConfig();
            return "更新成功";
        }catch (Exception e){
            return "服务器出错："+e.getMessage();
        }
    }



    @GetMapping(path = "/code")
    public ModelAndView code(){
        try {

            Map code=new HashMap<>();
            code.put("apiClass",codeService.codeTemp(CodeService.API_CLASS));
            code.put("apiDefGet",codeService.codeTemp(CodeService.API_DEF_GET));
            code.put("apiDefPost",codeService.codeTemp(CodeService.API_DEF_POST));
            code.put("apiMethodGet",codeService.codeTemp(CodeService.API_METHOD_GET));
            code.put("apiMethodPost",codeService.codeTemp(CodeService.API_METHOD_POST));
            code.put("method",codeService.codeTemp(CodeService.METHOD));

            return   new ModelAndView("code", "code",code);
        }catch (Exception e){
            log.error( "服务器出错：",e);
            ErrorMsg errorMsg=new ErrorMsg(e.getMessage(),1);
            return   new ModelAndView("error", "errorMsg",errorMsg);

        }
    }

    @PostMapping(path = "/code/update")
    @ResponseBody
    public String codeUpde(@RequestBody String body){
        try {
            JSONObject data=JSONObject.parseObject(body);
              codeService.updateCodeTemp(data.getString("type"),data.getString("code"));

//            codeService.updateCodeTemp(CodeService.API_CLASS,apiClass);
//            codeService.updateCodeTemp(CodeService.API_DEF_GET,apiDefGet);
//            codeService.updateCodeTemp(CodeService.API_DEF_POST,apiDefPost);
//            codeService.updateCodeTemp(CodeService.API_METHOD_GET,apiMethodGet);
//            codeService.updateCodeTemp(CodeService.API_METHOD_POST,apiMethodPost);
//            codeService.updateCodeTemp(CodeService.METHOD,method);

            return "更新成功";
        }catch (Exception e){
            return "服务器出错："+e.getMessage();
        }
    }




}

package com.wing.apirecord.controller;


import com.wing.apirecord.service.FileService;
import com.wing.apirecord.service.FilterService;
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
    public String result(@RequestParam String contentType,@RequestParam String methods,@RequestParam String urls
            ,@RequestParam String codeModule,@RequestParam String codeRoot){
        try {
            FilterService.contentType=contentType;
            FilterService. methods=methods;
            FilterService.urls=urls;
            filterService.setFilter();

            FileService.saveCodeModule=codeModule;
            FileService.saveCodeRoot=codeRoot;
            return "更新成功";
        }catch (Exception e){
            return "服务器出错："+e.getMessage();
        }
    }




}

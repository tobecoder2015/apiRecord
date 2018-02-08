package com.wing.apirecord.service;

import com.wing.apirecord.core.record.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class MethodGen {

    @Resource
    FileService fileService;

    @Resource
    NameGen nameGen;

    public String method(Record record) {
        String className = nameGen.getClassName(record);
        String methodName = nameGen.getMethodName(record);
        String packageName = nameGen.getPackageName(record);

        String method=fileService.getTemplates(FileService.METHOD);
        method=method.replace("{packageName}",packageName);
        method=method.replace("{className}",className);
        method=method.replace("{methodName}",methodName);
        return method;
    }

}

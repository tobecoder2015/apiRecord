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

    public String method(Record record) {
        String className = NameGen.getClassName(record);
        String methodName = NameGen.getMethodName(record);
        String packageName = NameGen.getPackageName(record);

        String method=fileService.getTemplates(FileService.METHOD);
        method=method.replace("{packageName}",packageName);
        method=method.replace("{className}",className);
        method=method.replace("{methodName}",methodName);
        return method;
    }

}

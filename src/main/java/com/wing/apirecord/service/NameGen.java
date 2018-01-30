package com.wing.apirecord.service;

import com.wing.apirecord.core.record.Record;


public class NameGen {

    private static final int size=3;

    public static String getClassName(Record record){
        return process(record).substring(0,1).toUpperCase()+process(record).substring(1)+"Test";
    }


    public static String getPackageName(Record record){
        return FileService.saveCodeModule;
    }

    public static  String getMethodName(Record record){
        return process(record);
    }


    private static  String process(Record record){
        String path =record.getRequest().getPath();
        String[] paths=path.split("\\/");
        int index=paths.length-1;
        while(index>=0){
            if(!paths[index].startsWith("{")){
                    return paths[index];
                }
            index--;
        }
        return null;
    }
}

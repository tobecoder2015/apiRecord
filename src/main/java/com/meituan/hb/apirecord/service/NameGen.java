package com.meituan.hb.apirecord.service;

import com.meituan.hb.apirecord.core.record.Record;

public class NameGen {


    public static String getClassName(Record record){
        String path =record.getRequest().getPath();
        String[] paths=path.split("\\/");
        String name=null;
        if(paths.length>=3)
            name=paths[paths.length-3].substring(0,1).toUpperCase()+paths[paths.length-3].substring(1)
                    +paths[paths.length-2].substring(0,1).toUpperCase()+paths[paths.length-2].substring(1)
                    +paths[paths.length-1].substring(0,1).toUpperCase()+paths[paths.length-1].substring(1);
        else
            name=paths[paths.length-1].substring(0,1).toUpperCase()+paths[paths.length-1].substring(1);
        return name+"Test";
    }


    public static String getPackageName(Record record){
        return "api";
    }

    public static  String getMethodName(Record record){
        String path =record.getRequest().getPath();
        String[] paths=path.split("\\/");
        String name=null;
        if(paths.length>=3)
            name=paths[paths.length-3]
                    +paths[paths.length-2].substring(0,1).toUpperCase()+paths[paths.length-2].substring(1)
                    +paths[paths.length-1].substring(0,1).toUpperCase()+paths[paths.length-1].substring(1);
        else
            name=paths[paths.length-1];
        return name;
    }
}

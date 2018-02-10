package com.wing.apirecord.service;

import com.wing.apirecord.core.record.Record;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Component
public class NameGen {

    @Resource FileService fileService;

    private  final int size=2;

    public  String getClassName(Record record){
        return process(record)+"Test";
    }


    public  String getPackageName(Record record){
        return FileService.saveCodeModule;
    }

    public   String getMethodName(Record record){
        return process(record).substring(0,1).toLowerCase()+process(record).substring(1);
    }


    private   String process(Record record){
        String path =record.getRequest().getPath();
        String[] paths=path.split("\\/");
        int index=paths.length-1;
        List<String> list=new ArrayList<>();
        int count=size;
        while(index>=0&&count>0){
            if(!paths[index].startsWith("{")){
                list.add(paths[index]);
                count--;
                }
            index--;
        }

        StringBuilder sb=new StringBuilder();
        boolean isstart=true;
        for (int i=list.size()-1;i>=0;i--){
            if(isstart) {
                sb.append(list.get(i).substring(0,1).toUpperCase()+list.get(i).substring(1));
                isstart=false;
            } else{
                sb.append(list.get(i).substring(0,1).toUpperCase()+list.get(i).substring(1));
            }
        }
        String methodName= sb.toString();
        if(!fileService.isApiExsit(record)&&fileService.isApiNameExist(methodName)){
            return methodName+"2";
        }else if(fileService.isApiExsit(record)&&fileService.isApiNameExist(methodName)&&fileService.isApiNameRepeat(methodName)){
            return methodName+"2";
        }else {
            return methodName;
        }
    }
}

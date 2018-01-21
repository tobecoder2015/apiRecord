package com.wing.apirecord.core.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Data
@AllArgsConstructor
public class Message  {
    public final static String REQUEST_HEAD="requestHead";
    public final static String RESPONSE_HEAD="responseHead";
    public final static String REQUEST_BODY="requestBody";
    public final static String RESPONSE_BODY="responseBody";

    private String id;
    private String key;
    private Object msg;
    private boolean finish=false;
    private boolean filter=false;

    public Message(String id,String key,Object msg){
        this.id=id;
        this.key=key;
        this.msg=msg;
    }
    public Message(String id,String key,Object msg,boolean filter){
        this.id=id;
        this.key=key;
        this.msg=msg;
        this.filter=filter;
    }



}

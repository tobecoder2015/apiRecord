package com.wing.apirecord.core.record;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wing.apirecord.core.model.Request;
import com.wing.apirecord.core.model.Response;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class RecordMap {
    public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";

    public static final   SimpleDateFormat timeFormat=new SimpleDateFormat(TIME_DEFAULT_FORMAT);
    static Map<String, Record> recordMap = new ConcurrentHashMap<>();
    static List<Record> finishList = new ArrayList<>();

    public static  List<Record>  getRecord() {
        return finishList;
    }
    public static  void  addRecord(Record record) {

        for(Record one:finishList){
            if(record.equals(one)){
                finishList.remove(one);
                break;
            }
        }
        record.setId(finishList.size()+1);
        record.setCreateTime(timeFormat.format(new Date()));
        finishList.add(record);

    }

    public static  boolean  containKey(String key) {
        return recordMap.containsKey(key);
    }


        public static  void  set(Message message) {

        Record record = recordMap.get(message.getId());
        if (record==null&&!message.getKey().equalsIgnoreCase(Message.REQUEST_HEAD))
            return;
        if(message.isFilter()) {
            recordMap.remove(message.getId());
            return;
        }

        switch (message.getKey()) {
            case Message.REQUEST_HEAD:
                record = new Record();
                record.setRequest((Request) message.getMsg());
                recordMap.put(message.getId(), record);
                break;
            case Message.REQUEST_BODY:
                record.setRequestBody(record.getRequest().getBody() + message.getMsg());
                break;
            case Message.RESPONSE_HEAD:
                log.debug("收到response header "+message.getMsg());
                record.setResponse((Response) message.getMsg());
                break;
            case Message.RESPONSE_BODY:
                log.debug("收到response content "+message.getMsg());
                record.getResponse().addBytes((byte[]) message.getMsg());
                if(message.isFinish()) {
                    addRecord(record);
                    recordMap.remove(message.getId());
                    record.getResponse().generateBody();
                    log.debug("生成接口数据"+record.toString());

                }
                break;

        }

    }


    public static String dump(){

        JSONArray array=new JSONArray();
        for (Record record:finishList){
            array.add(JSONObject.toJSON(record));
        }
        return array.toJSONString();
    }
}

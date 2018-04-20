package com.wing.apirecord.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.csvreader.CsvWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;


@Component
@Slf4j
public class ParaGen {

    private  static String userDir = System.getProperty("user.dir");


    public String getCaseData(String meta){
        JSONObject metaJs=JSONObject.parseObject(meta);
       return makeCaseData(metaJs);
    }


    public String exportVsc(String meta) throws Exception{
        JSONObject metaJs=JSONObject.parseObject(meta);
        JSONArray result=makePara(metaJs);
        Set<String> keySet=metaJs.getJSONObject("para").keySet();
        String[] keys=keySet.toArray(new String[] {});

        String path=userDir+"/tmp";
        FileUtils.forceMkdir(new File(path));

        String filePath = userDir+"/tmp/"+metaJs.getString("fileName")+".csv";


            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(filePath,',', Charset.forName("utf-8"));
            String[] headers = keys;
            String[] content=new String[headers.length];
            csvWriter.writeRecord(headers);

            for(int i=0;i<result.size();i++){
                for(int j=0;j<headers.length;j++)
                content[j]=result.getJSONObject(i).getString(headers[j]);
                csvWriter.writeRecord(content);
            }
            csvWriter.close();


        return filePath;
    }



    private String makeCaseData(JSONObject meta){
        JSONArray data=makePara(meta);

        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for(int i=0;i<data.size();i++) {
            JSONObject oneQueryPara=data.getJSONObject(i);
            sb.append(" {\n");
            sb.append("     \"request\":{\n");
            if (meta.getString("method").equals("get"))
                sb.append("         \"query\":" + JSON.toJSONString(oneQueryPara, SerializerFeature.WriteMapNullValue) + "\n");
            if (meta.getString("method").equals("post"))
                sb.append("         \"body\":" + JSON.toJSONString(oneQueryPara, SerializerFeature.WriteMapNullValue) + "\n");
            sb.append("     },\n");
            sb.append("     \"comments\":\"自动生成参数组合\",\n");
            sb.append("     \"response\":{}\n");
            sb.append(" }\n");
        }
        sb.append("]\n");

        return sb.toString();
    }



    private JSONArray makePara(JSONObject meta){
        JSONObject para=meta.getJSONObject("para");
        Iterator iterator=para.keySet().iterator();
        JSONArray result=null;


        while (iterator.hasNext()){
            String key=(String) iterator.next();
            JSONArray values=para.getJSONObject(key).getJSONArray("value");
            result=addNewPara(result,key,values);
        }
        return result;
    }

    private JSONArray addNewPara(JSONArray combinations,String key,JSONArray values){
        JSONArray copy=null;

        if(combinations!=null && combinations.size()>0){
            copy = JSONArray.parseArray(JSON.toJSONString(combinations, SerializerFeature.WriteMapNullValue));
            combinations.clear();
        }else {
            copy=new JSONArray();
            combinations=new JSONArray();
        }

        if(copy.size()>0) {


            for (int i = 0; i < values.size(); i++) {
                JSONArray var = JSONArray.parseArray(JSON.toJSONString(copy, SerializerFeature.WriteMapNullValue));

                String value = values.getString(i);
                for (int j = 0; j < copy.size(); j++) {
                    var.getJSONObject(j).put(key, value);
                }
                combinations.addAll(var);
            }
        }else{
            for (int i = 0; i < values.size(); i++) {
                String value = values.getString(i);
                JSONObject var=new JSONObject();
                var.put(key, value);
                copy.add(var);
            }
            combinations.addAll(copy);
        }

        return combinations;
    }

}

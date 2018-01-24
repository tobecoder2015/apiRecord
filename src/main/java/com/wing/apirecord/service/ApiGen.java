package com.wing.apirecord.service;

import com.wing.apirecord.core.record.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApiGen {


    public String apiDef(Record record) {
        String methodName = NameGen.getMethodName(record);
        if (record.getRequest().getMethod().equalsIgnoreCase("post")) {
          return   " @Headers({\"Content-Type: application/json\", \"Accept: application/json\"})\n" +
                    "@POST(\"" + record.getRequest().getPath() + "\")\n" +
                    "Call<ResponseBody> " + methodName + "(@Body RequestBody requestbody, @HeaderMap Map<String, String> headers);\n";

        }else{
           return   "@GET(\"" + record.getRequest().getPath() + "\")\n" +
                    "Call<ResponseBody> " + methodName + "(@QueryMap Map<String, Object> map, @HeaderMap Map<String, String> headers);\n";

        }
    }


    public String apiMethod(Record record) {
        String methodName = NameGen.getMethodName(record);
        String method="";
        if (record.getRequest().getMethod().equalsIgnoreCase("post")) {
           method= " public ResponseMap "+methodName+"(JSONObject request) throws IOException {\n" +
                    "\n" +
                    "\n" +
                    "        Map<String, Object> bodyMaps = (Map) JSON.parse(request.get(\"body\").toString());\n" +
                    "\n" +
                    "        autoAPIServices service = retrofit().create(autoAPIServices.class);\n" +
                    "\n" +
                    "        String body = bodyMaps.toString();\n" +
                    "        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse(\"application/json; charset=utf-8\"), body);\n" +
                    "\n" +
                    "        Call<ResponseBody> call = service."+methodName+"(requestBody,headerMap);\n" +
                    "        return sendRequest(call);\n" +
                    "    }";
        }else {

        method=" public ResponseMap "+methodName+"(JSONObject request) throws IOException {\n" +
                "                Map<String, Object> queryMaps = (Map) JSON.parse(request.get(\"query\").toString());\n" +
                "\n" +
                "                autoAPIServices service = retrofit().create(autoAPIServices.class);\n" +
                "                Call<ResponseBody> call = service."+methodName+"(queryMaps,headerMap);\n" +
                "                return sendRequest(call);\n"+
                "};";
        }
        return method;
    }

}

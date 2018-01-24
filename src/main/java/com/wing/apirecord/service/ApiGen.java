package com.wing.apirecord.service;

import com.wing.apirecord.core.record.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class ApiGen {

    @Resource
    FileService fileService;


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


    public String apiClass(Record record) throws Exception {

        String packageName = NameGen.getPackageName(record);
        String methodName = NameGen.getMethodName(record);

        String tmp="package "+packageName+";\n" +
                "\n" +
                "import com.alibaba.fastjson.JSON;\n" +
                "import com.alibaba.fastjson.JSONObject;\n" +
                "import com.meituan.hbqa.api.lib.HttpRequestService;\n" +
                "import com.meituan.hbqa.api.lib.ResponseMap;\n" +
                "import okhttp3.RequestBody;\n" +
                "import okhttp3.ResponseBody;\n"+
                "import retrofit2.Call;\n" +
                "import retrofit2.http.*;\n" +
                "\n" +
                "import java.io.IOException;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "\n" +
                "\n" +
                "public class Api extends HttpRequestService {\n" +
                "\n" +
                "    public static  Map<String, String> headerMap=new HashMap<String,String>();\n" +
                "\n" +
                "\n" +
                "    static {\n" +
                "     //       headerMap.put(\"cookie\",\"ssoid=\"+ Login.getSSOId());  //设置首部header\n" +
                "    }\n" +
                "\n" +
                "    public static interface apiServices {\n" +
                "\n" +
                "\n //apiDef" +
                "\n" +
                "    }\n" +
                "\n" +
                "\n //apiMethod" +
                "\n" +
                "}\n";

        String apiClass=fileService.getApiDef();
        if(apiClass==null)
            apiClass=tmp;
        if(!apiClass.contains(methodName)) {
            apiClass = apiClass.replace("//apiDef", apiDef(record) + "\n\n//apiDef");
            apiClass = apiClass.replace("//apiMethod", apiMethod(record) + "\n\n//apiMethod");
        }
        return apiClass;
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
                    "        apiServices service = retrofit().create(apiServices.class);\n" +
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
                "                apiServices service = retrofit().create(apiServices.class);\n" +
                "                Call<ResponseBody> call = service."+methodName+"(queryMaps,headerMap);\n" +
                "                return sendRequest(call);\n"+
                "}";
        }
        return method;
    }

}

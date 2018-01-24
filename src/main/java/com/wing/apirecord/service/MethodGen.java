package com.wing.apirecord.service;

import com.wing.apirecord.core.record.Record;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MethodGen {



    public String method(Record record) {
        String className = NameGen.getClassName(record);
        String methodName = NameGen.getMethodName(record);
        String packageName = NameGen.getPackageName(record);

        String method="package "+packageName+".testsuites;\n" +
                "\n" +
                "import com.alibaba.fastjson.JSONObject;\n" +
                "import com.meituan.hbqa.api.lib.AssertUtil;\n" +
                "import com.meituan.hbqa.api.lib.ResponseMap;\n" +
                "import com.meituan.hbqa.api.lib.TestBase;\n" +
                "import "+packageName+".Api;\n" +
                "import org.testng.Assert;\n" +
                "import org.testng.annotations.BeforeClass;\n" +
                "import org.testng.annotations.Test;\n" +
                "import utils.DbUtil;\n" +
                "import utils.SqlDb;\n" +
                "\n" +
                "\n" +
                "\n" +
                "public class "+className+" extends TestBase {\n" +
                "\n" +
                "    public static Api sApi = null;\n" +
                "    public static  SqlDb mintDb;\n" +
                "\n" +
                "    @BeforeClass\n" +
                "    public void beforeClass() {\n" +
                "        sApi = new Api();\n" +
                "        mintDb = DbUtil.getDb();\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    @Test(enabled = true, dataProvider = \"data\")\n" +
                "    public void apiTest(JSONObject request, JSONObject expect, String comments) throws Exception {\n" +
                "\n" +
                "        ResponseMap responseMap = sApi."+methodName+"(request);\n" +
                "\n" +
                "\n" +
                "        //状态码测试\n" +
                "        AssertUtil.assertHttp200(responseMap);\n" +
                "\n" +
                "        //jsonSchema检查\n" +
                "        AssertUtil.assertJsonSchema(responseMap);\n" +
                "\n" +
                "        //json 校验\n" +
                "        JSONObject result=JSONObject.parseObject(responseMap.getResponseBody());\n" +
                "        Assert.assertEquals(result.getIntValue(\"status\"),0);\n" +
                "        Assert.assertEquals(result.getString(\"message\"),\"成功\");\n" +
                "    }\n" +
                "\n" +
                "}\n" +
                "\n";

        return method;
    }

}

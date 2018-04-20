package com.wing.apirecord.controller;

import com.wing.apirecord.core.record.Record;
import com.wing.apirecord.core.record.RecordMap;
import com.wing.apirecord.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping
public class ParaController {
    @Resource
    ParaGen paraGen;

    @GetMapping(path = "/para")
    public ModelAndView para(){
        Map para=new HashMap();
        para.put("meta","{\n" +
                "\t\"url\":\"api/info\",\n" +
                "        \"fileName\":\"testData\",\n" +
                "\t\"method\":\"get\",\n" +
                "\t\"path\":{\n" +
                "\t},\n" +
                "\t\"para\":{\n" +
                "\t\t\"age\":{\"type\":\"int\",\"desc\":\"\",\"value\":[20,30,40,50],\"errorValue\":[]},\n" +
                "\t    \"role\":{\"type\":\"string\",\"desc\":\"\",\"value\":[\"工程师\",\"老师\",\"医生\"],\"errorValue\":[]},\n" +
                "\t    \"sex\":{\"type\":\"string\",\"desc\":\"\",\"value\":[\"男\",\"女\"],\"errorValue\":[]}\n" +
                "\t}\n" +
                "}\n");
        para.put("data","点击转换后，将根据示例的元数据文件生成参数组合");
        return new ModelAndView("para", "para", para);

    }

    @PostMapping(path = "/para/transfer")
    public String transfer(@RequestBody String meta){
        return  paraGen.getCaseData(meta);

    }

    @PostMapping(path = "/para/download")
    public void download(HttpServletResponse response,@RequestParam String meta){
        try {

            String path = paraGen.exportVsc(meta);

            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            //设置文件名
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            //设置文件打下
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (Exception ex) {
            log.error("下载文件失败",ex);
        }
    }
}


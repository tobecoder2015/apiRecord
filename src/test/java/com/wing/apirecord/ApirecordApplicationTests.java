package com.wing.apirecord;

import com.wing.apirecord.service.ParaGen;
import com.wing.apirecord.service.SchemaGen;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApirecordApplicationTests {

	@Resource
	SchemaGen schemaGen;

	@Resource
	ParaGen paraGen;



	@Test
	public void contextLoads() throws Exception {
		String meta="{\n" +
				"\t\"url\":\"api/info\",\n" +
				"\t\"method\":\"get\",\n" +
				"\t\"path\":{\n" +
				"\t},\n" +
				"\t\"para\":{\n" +
				"\t\t\"age\":{\"type\":\"int\",\"desc\":\"\",\"value\":[20,30,40,50],\"errorValue\":[]},\n" +
				"\t    \"role\":{\"type\":\"string\",\"desc\":\"\",\"value\":[\"工程师\",\"老师\",\"医生\"],\"errorValue\":[]},\n" +
				"\t    \"sex\":{\"type\":\"string\",\"desc\":\"\",\"value\":[\"男\",\"女\"],\"errorValue\":[]},\n" +
				"\t    \"url\":{\"type\":\"string\",\"desc\":\"\",\"value\":[\"/meituan\",\"/sankuai\"],\"errorValue\":[]},\n" +
				"\t    \"other\":{\"type\":\"string\",\"desc\":\"\",\"value\":[\"[1,2,3]\",\"[4,5,6]\"],\"errorValue\":[]}\n" +
				"\n" +
				"\n" +
				"\t}\n" +
				"}";
		paraGen.exportVsc(meta);
	}




}

package com.meituan.hb.apirecord;

import com.meituan.hb.apirecord.service.SchemaGen;
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

	@Test
	public void contextLoads() {
	}




}

package com.yicj.mybatis.dao;

import java.io.IOException;

import org.junit.Test;

import com.yicj.mybatis.entity.Classes;
import com.yicj.mybatis.service.ClassesService;

public class ClassesServiceTest {

	
	@Test
	public void testFindClassesById() throws IOException {
		Integer cid = 1 ;
		ClassesService service = new ClassesService() ;
		Classes classes = service.findClassesById(cid);
		System.out.println(classes);
	}
}

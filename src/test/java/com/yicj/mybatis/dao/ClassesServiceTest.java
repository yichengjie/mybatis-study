package com.yicj.mybatis.dao;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.yicj.mybatis.entity.Classes;
import com.yicj.mybatis.service.ClassesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ClassesServiceTest {
	@Autowired
	private ClassesService service ;
	
	@Test
	public void testFindClassesById() throws IOException {
		Integer cid = 1 ;
		Classes classes = service.findClassesById(cid);
		System.out.println(classes);
	}
}

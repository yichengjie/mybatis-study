package com.yicj.mybatis.dao;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.yicj.mybatis.entity.Student;
import com.yicj.mybatis.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class StudentServiceTest {
	@Autowired
	private StudentService service ;
	
	@Test
	public void testFindStudentById() throws IOException {
		Integer sid = 1 ;
		Student student = service.findStudentById(sid) ;
		System.out.println(student);
	}

}

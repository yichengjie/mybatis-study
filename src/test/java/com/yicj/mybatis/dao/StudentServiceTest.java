package com.yicj.mybatis.dao;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.yicj.mybatis.entity.Student;
import com.yicj.mybatis.service.StudentService;

public class StudentServiceTest {
	
	private StudentService service ;
	
	@Before
	public void before() {
		service = new StudentService() ;
	}
	
	@Test
	public void testFindStudentById() throws IOException {
		Integer sid = 1 ;
		Student student = service.findStudentById(sid) ;
		System.out.println(student);
	}

}

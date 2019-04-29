package com.yicj.mybatis.service.impl;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yicj.mybatis.entity.Student;
import com.yicj.mybatis.mapper.StudentMapper;
import com.yicj.mybatis.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentMapper mapper ;
	
	public Student findStudentById(Integer sid) throws IOException {
		return  mapper.findStudentById(sid) ;
	}
}

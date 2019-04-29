package com.yicj.mybatis.service;

import java.io.IOException;

import com.yicj.mybatis.entity.Student;

public interface StudentService {
	public Student findStudentById(Integer sid) throws IOException ;
}

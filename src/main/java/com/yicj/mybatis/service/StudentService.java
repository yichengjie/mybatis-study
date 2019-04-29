package com.yicj.mybatis.service;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.yicj.mybatis.entity.Student;
import com.yicj.mybatis.mapper.StudentMapper;
import com.yicj.mybatis.util.SessionFactoryUtil;

public class StudentService {
	
	public Student findStudentById(Integer sid) throws IOException {
		SqlSession session = null ;
		try {
			session = SessionFactoryUtil.getSession();
			StudentMapper mapper = session.getMapper(StudentMapper.class) ;
			return  mapper.findStudentById(sid) ;
		} finally {
			SessionFactoryUtil.closeSession(session);
		}
	}
}

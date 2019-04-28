package com.yicj.mybatis.mapper.impl;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yicj.mybatis.entity.Dept;
import com.yicj.mybatis.mapper.DeptDAO;
import com.yicj.mybatis.util.SessionFactoryUtil;

public class DeptDAOImpl implements DeptDAO {
	Logger logger = LoggerFactory.getLogger(DeptDAOImpl.class) ;
	
	public List<Dept> findAll() throws IOException {
		SqlSession session = null ;
		List<Dept> depts = null ;
		try {
			session = SessionFactoryUtil.getSession() ;
			DeptDAO mapper = session.getMapper(DeptDAO.class) ;
			depts = mapper.findAll() ;
		}finally {
			if(session!=null) {
				session.close(); 
			}
		}
		return depts;
	}

	public void insertDept(Dept dept) throws IOException {
		SqlSession session = null ;
		try {
			session = SessionFactoryUtil.getSession() ;
			DeptDAO mapper = session.getMapper(DeptDAO.class) ;
			mapper.insertDept(dept);
			session.commit();
		} finally {
			if (session !=null){
				session.close();
			}
		}

	}
	
	
	public List<Object> findAll2() throws IOException {
		SqlSession session = null ;
		List<Dept> depts = null ;
		try {
			session = SessionFactoryUtil.getSession() ;
			List<Object> allUsers = session.selectList("com.yicj.mybatis.dao.DeptDAO.findAll");
			return allUsers ;
		}finally {
			if(session!=null) {
				session.close(); 
			}
		}
	}
}

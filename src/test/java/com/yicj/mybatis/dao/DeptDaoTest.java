package com.yicj.mybatis.dao;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yicj.mybatis.dao.impl.DeptDAOImpl;
import com.yicj.mybatis.entity.Dept;

public class DeptDaoTest {
	static Logger logger = LoggerFactory.getLogger(DeptDaoTest.class) ;
	@Test
	public void testFindAll() throws IOException {
		DeptDAO dao = new DeptDAOImpl() ;
		List<Dept> depts = dao.findAll() ;
		logger.info(depts.toString());
		
	}

	@Test
	public void testInsertDept() throws IOException {
		DeptDAO dao = new DeptDAOImpl() ;
		Dept dept = new Dept("部门4") ;
		dao.insertDept(dept);
	}
	
	@Test
	public void testFindAll2() throws IOException {
		DeptDAO dao = new DeptDAOImpl() ;
		List<Object> depts = dao.findAll2();
		logger.info(depts.toString());
	} 

}

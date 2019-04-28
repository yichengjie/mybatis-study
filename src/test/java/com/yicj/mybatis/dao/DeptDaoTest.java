package com.yicj.mybatis.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yicj.mybatis.entity.Dept;
import com.yicj.mybatis.mapper.DeptMapper;
import com.yicj.mybatis.service.DeptService;
import com.yicj.mybatis.vo.QueryVO;

public class DeptDaoTest {
	static Logger logger = LoggerFactory.getLogger(DeptDaoTest.class) ;
	
	@Test
	public void testFindAll() throws IOException {
		DeptService service = new DeptService() ;
		List<Dept> depts = service.findAll() ;
		logger.info(depts.toString());
	}

	@Test
	public void testInsertDept() throws IOException {
		DeptService service = new DeptService() ;
		Dept dept = new Dept("部门4") ;
		service.insertDept(dept);
	}
	
	@Test
	public void testFindAll2() throws IOException {
		DeptService service = new DeptService() ;
		List<Object> depts = service.findAll2();
		logger.info(depts.toString());
	} 
	
	@Test
	public void testFindByIds() throws IOException {
		List<Dept> depts = new ArrayList<Dept>() ;
		depts.add(new Dept(1)) ;
		depts.add(new Dept(2)) ;
		depts.add(new Dept(3)) ;
		QueryVO vo = new QueryVO() ;
		vo.setDepts(depts); ;
		DeptService service = new DeptService() ;
		List<Dept> retList = service.findByIds(vo);
		System.out.println(retList);
	}

	@Test
	public void queryDeptByName() throws IOException {
		String deptname = "部门" ;
		DeptService service = new DeptService() ;
		List<Dept> list = service.queryDeptByName(deptname) ;
		System.out.println(list);
	}

	@Test
	public void queryDeptByName2() throws IOException {
		String deptname = "部门" ;
		DeptService service = new DeptService() ;
		List<Dept> list = service.queryDeptByName2(deptname) ;
		System.out.println(list);
	}

	@Test
	public void queryDeptByName3() throws IOException {
		String deptname = "部门" ;
		DeptService service = new DeptService() ;
		List<Dept> list = service.queryDeptByName3(deptname) ;
		System.out.println(list);
	}

	
	

}

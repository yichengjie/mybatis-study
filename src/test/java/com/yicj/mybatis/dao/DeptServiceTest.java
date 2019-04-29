package com.yicj.mybatis.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.yicj.mybatis.entity.Dept;
import com.yicj.mybatis.service.DeptService;
import com.yicj.mybatis.vo.QueryVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class DeptServiceTest {
	static Logger logger = LoggerFactory.getLogger(DeptServiceTest.class) ;
	@Autowired
	private DeptService service ;
	
	
	@Test
	public void testFindAll() throws IOException {
		List<Dept> depts = service.findAll() ;
		logger.info(depts.toString());
	}

	@Test
	public void testInsertDept() throws IOException {
		Dept dept = new Dept("部门6") ;
		service.insertDept(dept);
		System.out.println(dept);
	}
	
	
	@Test
	public void testFindByIds() throws IOException {
		List<Dept> depts = new ArrayList<Dept>() ;
		depts.add(new Dept(1)) ;
		depts.add(new Dept(2)) ;
		depts.add(new Dept(3)) ;
		QueryVO vo = new QueryVO() ;
		vo.setDepts(depts); ;
		List<Dept> retList = service.findByIds(vo);
		System.out.println(retList);
	}

	@Test
	public void queryDeptByName() throws IOException {
		String deptname = "部门" ;
		List<Dept> list = service.queryDeptByName(deptname) ;
		System.out.println(list);
	}

	@Test
	public void queryDeptByName2() throws IOException {
		String deptname = "部门" ;
		List<Dept> list = service.queryDeptByName2(deptname) ;
		System.out.println(list);
	}

	@Test
	public void queryDeptByName3() throws IOException {
		String deptname = "部门" ;
		List<Dept> list = service.queryDeptByName3(deptname) ;
		System.out.println(list);
	}

}

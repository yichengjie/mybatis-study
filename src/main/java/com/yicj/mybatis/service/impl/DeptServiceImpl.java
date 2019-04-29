package com.yicj.mybatis.service.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yicj.mybatis.entity.Dept;
import com.yicj.mybatis.mapper.DeptMapper;
import com.yicj.mybatis.service.DeptService;
import com.yicj.mybatis.vo.QueryVO;

@Service
public class DeptServiceImpl implements DeptService{
	@Autowired
	private DeptMapper mapper ;
	public List<Dept> findAll() throws IOException {
		return mapper.findAll() ;
	}
	public void insertDept(Dept dept) throws IOException {
		mapper.insertDept(dept);
	}
	public List<Dept> findByIds(QueryVO vo) throws IOException{
		return mapper.findByIds(vo) ;
	}
	public List<Dept> queryDeptByName(String deptname) throws IOException {
		return mapper.queryDeptByName(deptname) ;
	}
	public List<Dept> queryDeptByName2(String deptname) throws IOException {
		return mapper.queryDeptByName2(deptname) ;
	}
	public List<Dept> queryDeptByName3(String deptname) throws IOException {
		return mapper.queryDeptByName3(deptname) ;
	}
}

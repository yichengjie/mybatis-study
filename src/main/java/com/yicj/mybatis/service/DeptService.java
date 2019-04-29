package com.yicj.mybatis.service;

import java.io.IOException;
import java.util.List;
import com.yicj.mybatis.entity.Dept;
import com.yicj.mybatis.vo.QueryVO;

public interface DeptService {
	public List<Dept> findAll() throws IOException ;
	public void insertDept(Dept dept) throws IOException ;
	public List<Dept> findByIds(QueryVO vo) throws IOException ;
	public List<Dept> queryDeptByName(String deptname) throws IOException ;
	public List<Dept> queryDeptByName2(String deptname) throws IOException ;
	public List<Dept> queryDeptByName3(String deptname) throws IOException ;
}

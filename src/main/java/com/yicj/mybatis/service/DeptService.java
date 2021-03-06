package com.yicj.mybatis.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yicj.mybatis.entity.Dept;
import com.yicj.mybatis.mapper.DeptMapper;
import com.yicj.mybatis.util.SessionFactoryUtil;
import com.yicj.mybatis.vo.QueryVO;

public class DeptService{
	Logger logger = LoggerFactory.getLogger(DeptService.class) ;
	
	public List<Dept> findAll() throws IOException {
		SqlSession session = null ;
		List<Dept> depts = null ;
		try {
			session = SessionFactoryUtil.getSession() ;
			DeptMapper mapper = session.getMapper(DeptMapper.class) ;
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
			DeptMapper mapper = session.getMapper(DeptMapper.class) ;
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
		try {
			session = SessionFactoryUtil.getSession() ;
			List<Object> depts = session.selectList("com.yicj.mybatis.mapper.DeptMapper.findAll");
			return depts ;
		}finally {
			if(session!=null) {
				session.close(); 
			}
		}
	}
	
	public List<Dept> findByIds(QueryVO vo) throws IOException{
		SqlSession session = null ;
		try {
			session = SessionFactoryUtil.getSession() ;
			DeptMapper mapper = session.getMapper(DeptMapper.class) ;
			return mapper.findByIds(vo) ;
		}finally {
			if(session!=null) {
				session.close(); 
			}
		}
	}

	public List<Dept> queryDeptByName(String deptname) throws IOException {
		SqlSession session = null ;
		try {
			session = SessionFactoryUtil.getSession() ;
			DeptMapper mapper = session.getMapper(DeptMapper.class) ;
			return mapper.queryDeptByName(deptname) ;
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	public List<Dept> queryDeptByName2(String deptname) throws IOException {
		SqlSession session = null ;
		try {
			session = SessionFactoryUtil.getSession() ;
			DeptMapper mapper = session.getMapper(DeptMapper.class) ;
			return mapper.queryDeptByName2(deptname) ;
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	public List<Dept> queryDeptByName3(String deptname) throws IOException {
		SqlSession session = null ;
		try {
			session = SessionFactoryUtil.getSession() ;
			DeptMapper mapper = session.getMapper(DeptMapper.class) ;
			return mapper.queryDeptByName3(deptname) ;
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}


	
}

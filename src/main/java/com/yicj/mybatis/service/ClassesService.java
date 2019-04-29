package com.yicj.mybatis.service;

import java.io.IOException;
import org.apache.ibatis.session.SqlSession;
import com.yicj.mybatis.entity.Classes;
import com.yicj.mybatis.mapper.ClassesMapper;
import com.yicj.mybatis.util.SessionFactoryUtil;

public class ClassesService  {
	
	public Classes findClassesById(Integer cid) throws IOException {
		SqlSession session = null ;
		Classes classes = null ;
		try {
			session = SessionFactoryUtil.getSession() ;
			ClassesMapper mapper = session.getMapper(ClassesMapper.class) ;
			classes = mapper.findClassesById(cid) ;
		}finally {
			if(session!=null) {
				session.close(); 
			}
		}
		return classes;
	}
	

}

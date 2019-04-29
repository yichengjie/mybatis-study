package com.yicj.mybatis.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.yicj.mybatis.entity.Groups;
import com.yicj.mybatis.mapper.UsersMapper;
import com.yicj.mybatis.util.SessionFactoryUtil;

public class UsersService {
	
	public List<Groups> findGroupsById(Integer uid) throws IOException {
		SqlSession session = null ;
		try {
			session = SessionFactoryUtil.getSession();
			UsersMapper mapper = session.getMapper(UsersMapper.class) ;
			return mapper.findGroupsById(uid);
		} finally {
			SessionFactoryUtil.closeSession(session);
		}
	}

}

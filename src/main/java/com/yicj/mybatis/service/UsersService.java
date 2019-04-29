package com.yicj.mybatis.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.yicj.mybatis.entity.Groups;
import com.yicj.mybatis.mapper.UsersMapper;
import com.yicj.mybatis.util.SessionFactoryUtil;

public class UsersService {
	
	public List<Groups> findGroupsListByUid(Integer uid) throws IOException {
		SqlSession session = null ;
		try {
			session = SessionFactoryUtil.getSession();
			UsersMapper mapper = session.getMapper(UsersMapper.class) ;
			return mapper.findGroupsListByUid(uid);
		} finally {
			SessionFactoryUtil.closeSession(session);
		}
	}

}

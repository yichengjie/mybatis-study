package com.yicj.mybatis.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.yicj.mybatis.entity.Groups;
import com.yicj.mybatis.mapper.GroupsMapper;
import com.yicj.mybatis.util.SessionFactoryUtil;

public class GroupsService {
	
	public List<Groups> findUsersListByGid(Integer gid) throws IOException {
		SqlSession session = null ;
		try {
			session = SessionFactoryUtil.getSession();
			GroupsMapper mapper = session.getMapper(GroupsMapper.class) ;
			return mapper.findUsersListByGid(gid);
		} finally {
			SessionFactoryUtil.closeSession(session);
		}
	}

}

package com.yicj.mybatis.service.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yicj.mybatis.entity.Groups;
import com.yicj.mybatis.mapper.GroupsMapper;
import com.yicj.mybatis.service.GroupsService;

@Service
public class GroupsServiceImpl implements GroupsService{
	@Autowired
	private GroupsMapper mapper ;
	
	public List<Groups> findUsersListByGid(Integer gid) throws IOException {
		return mapper.findUsersListByGid(gid);
	}
}

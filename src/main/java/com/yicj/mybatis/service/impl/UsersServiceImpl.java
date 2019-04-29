package com.yicj.mybatis.service.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yicj.mybatis.entity.Groups;
import com.yicj.mybatis.mapper.UsersMapper;
import com.yicj.mybatis.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersMapper mapper ;
	
	@Override
	public List<Groups> findGroupsListByUid(Integer uid) throws IOException {
		return mapper.findGroupsListByUid(uid);
	}
}

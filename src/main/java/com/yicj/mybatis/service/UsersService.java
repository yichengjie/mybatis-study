package com.yicj.mybatis.service;

import java.io.IOException;
import java.util.List;

import com.yicj.mybatis.entity.Groups;

public interface UsersService {
	
	public List<Groups> findGroupsListByUid(Integer uid) throws IOException  ;
}

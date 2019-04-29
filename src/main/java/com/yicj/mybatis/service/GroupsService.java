package com.yicj.mybatis.service;

import java.io.IOException;
import java.util.List;

import com.yicj.mybatis.entity.Groups;

public interface GroupsService {
	public List<Groups> findUsersListByGid(Integer gid) throws IOException ;
}

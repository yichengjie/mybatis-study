package com.yicj.mybatis.mapper;

import java.util.List;

import com.yicj.mybatis.entity.Groups;

public interface UsersMapper {
	
	List<Groups> findGroupsListByUid(Integer uid) ;
}

package com.yicj.mybatis.mapper;

import java.util.List;

import com.yicj.mybatis.entity.Groups;

public interface GroupsMapper {
	
	List<Groups> findUsersById(Integer gid) ;

}

package com.yicj.mybatis.dao;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import com.yicj.mybatis.entity.Groups;
import com.yicj.mybatis.service.UsersService;

public class UsersServiceTest {
	
	
	@Test
	public void testFindGroupsById() throws IOException {
		Integer uid = 1 ;
		UsersService service = new UsersService() ;
		List<Groups> groups = service.findGroupsListByUid(uid) ;
		System.out.println(groups);
	}
	

}
package com.yicj.mybatis.dao;

import java.io.IOException;
import java.util.List;
import org.junit.Test;
import com.yicj.mybatis.entity.Groups;
import com.yicj.mybatis.service.GroupsService;

public class GroupsServiceTest {
	
	
	@Test
	public void testFindUsersById() throws IOException {
		Integer gid = 3 ;
		GroupsService service = new GroupsService() ;
		List<Groups> groups = service.findUsersListByGid(gid);
		System.out.println(groups);
	}
	

}
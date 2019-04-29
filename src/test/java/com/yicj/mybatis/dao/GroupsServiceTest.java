package com.yicj.mybatis.dao;

import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.yicj.mybatis.entity.Groups;
import com.yicj.mybatis.service.GroupsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class GroupsServiceTest {
	@Autowired
	private GroupsService service ;
	
	@Test
	public void findUsersListByGid() throws IOException {
		Integer gid = 3 ;
		List<Groups> groups = service.findUsersListByGid(gid);
		System.out.println(groups);
	}
	

}
package com.yicj.mybatis.dao;

import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.yicj.mybatis.entity.Groups;
import com.yicj.mybatis.service.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class UsersServiceTest {
	
	@Autowired
	private UsersService service ;
	
	@Test
	public void findGroupsListByUid() throws IOException {
		Integer uid = 1 ;
		List<Groups> groups = service.findGroupsListByUid(uid) ;
		System.out.println(groups);
	}
	

}
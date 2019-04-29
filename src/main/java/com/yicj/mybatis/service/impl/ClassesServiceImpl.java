package com.yicj.mybatis.service.impl;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yicj.mybatis.entity.Classes;
import com.yicj.mybatis.mapper.ClassesMapper;
import com.yicj.mybatis.service.ClassesService;

@Service
public class ClassesServiceImpl  implements ClassesService {
	@Autowired
	private ClassesMapper mapper ;
	
	public Classes findClassesById(Integer cid) throws IOException {
		return mapper.findClassesById(cid) ;
	}
}

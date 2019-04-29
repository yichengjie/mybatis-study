package com.yicj.mybatis.service;

import java.io.IOException;

import com.yicj.mybatis.entity.Classes;

public interface ClassesService {
	
	public Classes findClassesById(Integer cid) throws IOException ;
}

package com.yicj.mybatis.vo;

import java.util.List;

import com.yicj.mybatis.entity.Dept;

public class QueryVO {
	
	private List<Dept> depts  ;

	public List<Dept> getDepts() {
		return depts;
	}

	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}
}

package com.yicj.mybatis.entity;

import java.util.List;

public class Users {
	
	private Integer uid ;
	private String uname ;
	private List<Groups> groups ;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public List<Groups> getGroups() {
		return groups;
	}
	public void setGroups(List<Groups> groups) {
		this.groups = groups;
	}
	@Override
	public String toString() {
		return "Users [uid=" + uid + ", uname=" + uname + ", groups=" + groups + "]";
	}
}

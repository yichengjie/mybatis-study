package com.yicj.mybatis.entity;

import java.util.List;

public class Groups {
	
	private Integer gid ;
	private String gname ;
	private List<Users> users ;
	
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "Groups [gid=" + gid + ", gname=" + gname + ", users=" + users + "]";
	}
}

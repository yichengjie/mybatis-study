package com.yicj.mybatis.entity;

public class Student {
	
	private Integer sid ;
	private String sname ;
	private Classes classes ;
	
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Classes getClasses() {
		return classes;
	}
	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", classes=" + classes + "]";
	}
}

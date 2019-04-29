package com.yicj.mybatis.entity;

import java.util.Set;

public class Classes {
	
	private Integer cid ;
	private String cname ;
	private Set<Student> students ;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	@Override
	public String toString() {
		return "Classes [cid=" + cid + ", cname=" + cname + ", students=" + students + "]";
	}
}

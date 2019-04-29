package com.yicj.mybatis.entity;


public class Dept  {
    private Integer deptno;
    private String deptname;
    
    public Dept() {
    }
    
    public Dept(Integer deptno) {
    	this.deptno = deptno ;
    }
    
    public Dept(String deptname) {
        this.deptname = deptname;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public Dept(Integer deptno, String deptname) {
        this.deptno = deptno;
        this.deptname = deptname;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", deptname='" + deptname + '\'' +
                '}';
    }
}

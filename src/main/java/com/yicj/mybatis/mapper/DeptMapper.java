package com.yicj.mybatis.mapper;

import java.io.IOException;
import java.util.List;
import com.yicj.mybatis.entity.Dept;
import com.yicj.mybatis.vo.QueryVO;

public interface DeptMapper {
    public List<Dept> findAll() throws IOException;
    public void insertDept(Dept dept)throws IOException ;
    public List<Object> findAll2() throws IOException ;
    public List<Dept> findByIds(QueryVO vo) ;
}
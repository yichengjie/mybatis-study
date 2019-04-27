package com.yicj.mybatis.dao;

import java.io.IOException;
import java.util.List;
import com.yicj.mybatis.entity.Dept;

public interface DeptDAO {
    public List<Dept> findAll() throws IOException;
}
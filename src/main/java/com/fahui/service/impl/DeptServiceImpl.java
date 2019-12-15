package com.fahui.service.impl;

import com.fahui.bean.Dept;
import com.fahui.dao.DeptMapper;
import com.fahui.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    public List<Dept> selectAll() {
        List<Dept> list = deptMapper.selectAll();
        return list;
    }
}

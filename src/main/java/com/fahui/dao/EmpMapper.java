package com.fahui.dao;

import com.fahui.bean.Emp;

import java.util.List;

public interface EmpMapper {
    List<Emp> selectAll(Emp emp);
    void saveEmp(Emp emp);
    Emp selectByOne(Integer mgr);       //判断员工是否存在
    int updateEmp(Emp emp);
    int deleteEmp(int[] idArray);         //删除员工
}

package com.fahui.service;

import com.fahui.bean.Emp;

import java.util.List;

public interface EmpService {
    List<Emp> selectAll(Emp emp);      //查询所有
    void saveEmp(Emp emp);      //添加员工
    Emp selectByOne(Integer mgr);   //判断领导及查询员工
    Boolean updateEmp(Emp emp);     //更新员工
    Boolean deleteEmp(int[] idArray);     //删除员工
}

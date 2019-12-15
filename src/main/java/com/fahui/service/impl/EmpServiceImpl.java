package com.fahui.service.impl;

import com.fahui.bean.Emp;
import com.fahui.dao.EmpMapper;
import com.fahui.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    public List<Emp> selectAll(Emp emp) {
        List<Emp> list = empMapper.selectAll(emp);
        return list;
    }

    public void saveEmp(Emp emp) {
        empMapper.saveEmp(emp);
    }

    public Emp selectByOne(Integer mgr){
        Emp emp = empMapper.selectByOne(mgr);
        if(emp!=null){
            return emp;
        }else {
            return null;
        }
    }

    public Boolean updateEmp(Emp emp) {
        Emp emp1 = selectByOne(emp.getId());
        if(emp!=null){
            int i = empMapper.updateEmp(emp);
            if(i>0){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public Boolean deleteEmp(int[] idArray) {
        int i = empMapper.deleteEmp(idArray);
        if(i>0){
            return true;
        }else {
            return false;
        }
    }
}

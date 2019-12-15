package com.fahui.test;

import com.fahui.bean.Dept;
import com.fahui.bean.Emp;
import com.fahui.dao.DeptMapper;
import com.fahui.dao.EmpMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class test2 {
    private ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");;

    @Test
    public void get3(){
        EmpMapper mapper = act.getBean(EmpMapper.class);
        Emp emp = new Emp();
        emp.setId(1001);
        emp.setName("张三");
        emp.setJob("dddd");
        emp.setMgr(1001);
        emp.setSal(2222f);
        emp.setComm(222f);
        emp.setDeptno(30);

        Integer emp1 = mapper.updateEmp(emp);
        System.out.println(emp1);
    }
    @Test
    public void get2(){
        DeptMapper mapper = act.getBean(DeptMapper.class);
        List<Dept> list = mapper.selectAll();
        for (Dept d : list){
            System.out.println(d);
        }
    }

    @Test
    public void get4(){
        EmpMapper mapper = act.getBean(EmpMapper.class);
        Emp emp = new Emp();
        List<Emp> list = mapper.selectAll(emp);
        for (Emp emp1 : list) {
            System.out.println(emp1);
        }
    }
}

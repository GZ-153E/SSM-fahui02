package com.fahui.test;

import com.fahui.bean.Emp;
import com.fahui.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class test1 {
    @Autowired
    private EmpService empService;

    @Test
    public void test1(){
        Emp emp = new Emp();
        //emp.setId(1001);
        //emp.setName("i");
        //emp.setMgr(7698);
        List<Emp> emps = empService.selectAll(emp);
        for (Emp emp1 : emps) {
            System.out.println(emp1);
        }
    }
}

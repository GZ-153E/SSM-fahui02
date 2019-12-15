package com.fahui.controller;

import com.fahui.bean.Dept;
import com.fahui.bean.Msg;
import com.fahui.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /*
    * 查询所有部门
    * */
    @RequestMapping(value = "selectAll",method = RequestMethod.GET)
    @ResponseBody
    public Msg selectAll(){
        List<Dept> list = deptService.selectAll();
        return Msg.success().add("dept",list);
    }
}

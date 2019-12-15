package com.fahui.controller;

import com.fahui.bean.Emp;
import com.fahui.bean.Msg;
import com.fahui.service.EmpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/js")
public class EmpJSApiController {
    @Autowired
    private EmpService empService;

    @RequestMapping("jsAPIIndex")
    public String jsAPIIndex(){
        return "jsapi/main";
    }

    /*
    *查询所有及待条件查询
    * */
    @RequestMapping(value="/empAll",method=RequestMethod.GET)
    @ResponseBody
    public Msg selectAll(@RequestParam(value="pn",defaultValue="1")Integer pn,Emp emp){
        //System.out.println(emp);
        PageHelper.startPage(pn,5);                     //传页码，及每页显示多少条数据 //pn是页码默认是1
        List<Emp> list = empService.selectAll(emp);
        PageInfo<Emp> page = new PageInfo<>(list,5); //封装
        return Msg.success().add("pageInfo",page);
    }

    /*
    * 添加员工
    * */
    @RequestMapping(value = "/saveEmp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Emp emp, BindingResult result){
        //BindingResult封装校验信息  bean里有配置
        //result.hasErrors()方法中有校验成功与失败
        //System.out.println(result.hasErrors());
        if(result.hasErrors()){
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<>();//封装错误字段
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                //System.out.print("错误的字段名："+fieldError.getField());
                //System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            if(emp.getId()!=null){
                String id = emp.getId().toString();
                Boolean idMes = id.matches("^[0-9]{4}$");
                if(!idMes){
                    map.put("id","员工编号必须为4为数字且不能为空！");
                }
            }else{
                map.put("id","员工编号必须为4为数字且不能为空！");
            }
            return Msg.fail().add("errorFields", map);
        }else{
            String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            emp.setHiredate(time);
            empService.saveEmp(emp);            //添加员工
            return Msg.success();
        }

    }

    /*
    * 更新员工
    * */
    @RequestMapping(value = "updateEmp",method = RequestMethod.POST)
    @ResponseBody
    public Msg updateEmp(@Valid Emp emp, BindingResult result){
        Integer id = emp.getId();
        //System.out.println(id);
        if(result.hasErrors()){
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<>();      //封装错误字段
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            if(emp.getId()!=null){
                Boolean idMes = id.toString().matches("^[0-9]{4}$");
                if(!idMes){
                    map.put("id","员工编号必须为4为数字且不能为空！");
                }
            }else{
                map.put("id","员工编号必须为4为数字且不能为空！");
            }
            return Msg.fail().add("errorFields", map);
        }else{
            Boolean b = empService.updateEmp(emp);//更新员工
            if(b){
                return Msg.success();
            }else {
                return Msg.fail();
            }
        }
    }

    /*
    * 删除员工批量及单个
    * */
    @RequestMapping(value = "deleteEmp",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(int[] idArray){
        Boolean i = empService.deleteEmp(idArray);
        if(i){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }


    /*
    * 判断领导是否存在
    * */
    @RequestMapping(value = "selectByOne",method = RequestMethod.GET)
    @ResponseBody
    public Msg selectByOne(@RequestParam("empId")Integer empId){
        Emp emp = empService.selectByOne(empId);
        if(emp!=null){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }

    /*@RequestMapping("alljson")
    @ResponseBody
    public List<Emp> sell(){
        List<Emp> list = empService.selectAll();
        return list;
    }*/

    /*public static void main(String[] args) {
        String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println(time);
    }*/
}

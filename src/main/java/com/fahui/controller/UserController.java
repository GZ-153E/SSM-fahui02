package com.fahui.controller;

import com.fahui.bean.Msg;
import com.fahui.bean.User;
import com.fahui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/boss")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/loginIndex")
    public String loginIndex(){
        return "login";
    }

    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session){
        session.removeAttribute("LOGIN_USER");
        return "redirect:/boss/loginIndex";     //转发
    }

    /*
    * 登陆方法
    * */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Msg login(@Valid User user, HttpSession session, BindingResult result){
        //System.out.println("user"+user);
        if(result.hasErrors()){
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<>();//封装错误字段
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                //System.out.print("错误的字段名："+fieldError.getField());
                //System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }else{
            User one = userService.selectByOne(user);
            if(one!=null){
                session.setAttribute("LOGIN_USER",user);
                return Msg.success();
            }else {
                return Msg.fail();
            }
        }

    }
}

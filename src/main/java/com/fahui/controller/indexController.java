package com.fahui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class indexController {

    @RequestMapping("/jsapi")
    public String jsapi(){
        return "jsapi/main";
    }
}

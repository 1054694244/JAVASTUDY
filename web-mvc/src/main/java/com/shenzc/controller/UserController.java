package com.shenzc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shenzc
 * @create 2020-07-06-17:26
 */
@Controller
public class UserController {

    @RequestMapping("/getUser.do")
    public Object getUser(String name, HttpServletRequest request, HttpServletResponse response){
        System.out.println("getUser");
        return null;
    }

    @RequestMapping("/getList.do")
    public Object getList(){
        System.out.println("getList");
        return null;
    }

}

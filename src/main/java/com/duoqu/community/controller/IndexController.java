package com.duoqu.community.controller;


import com.duoqu.community.model.User;
import com.duoqu.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token= cookie.getValue();
                    User user = userService.findByToken(token);
                    request.getSession().setAttribute("user",user);
                    break;
                }
            }
        }else{
            return "index";
        }
        return "index";
    }
}

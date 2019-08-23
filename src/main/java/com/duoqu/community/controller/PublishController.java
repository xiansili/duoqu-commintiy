package com.duoqu.community.controller;

import com.duoqu.community.mapper.UserMapper;
import com.duoqu.community.model.Question;
import com.duoqu.community.model.User;
import com.duoqu.community.service.QuestionService;
import com.duoqu.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String dopublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tags") String tags,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tags",tags);

        if (title==null || title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description==null || description==""){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        if (tags==null || tags==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        // 获取用户
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userService.findByToken(token);
                    request.getSession().setAttribute("user", user);
                    break;
                }
            }
        } else {
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTags(tags);
        question.setCreator(user.getName());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionService.addQuestion(question);
        return "redirect:/";

    }
}
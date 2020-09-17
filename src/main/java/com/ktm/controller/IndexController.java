package com.ktm.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.dto.QuestionDTO;
import com.ktm.entity.User;
import com.ktm.service.IQuestionService;
import com.ktm.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Resource
    private IUserService userService;

    @Resource
    private IQuestionService questionService;


    @GetMapping({"/index", "/"})
    public String index(HttpServletRequest request,
                        @RequestParam(name = "current",defaultValue = "1") int current,
                        @RequestParam(name = "size",defaultValue = "4") int size) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getToken, cookie.getValue()));
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }


        IPage<QuestionDTO> result = questionService.paging(new Page<QuestionDTO>(current, size));
        request.setAttribute("questions",result.getRecords());
        return "index";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "index";
    }
}

package com.ktm.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ktm
 * @since 2020-09-17
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/index";
    }
}

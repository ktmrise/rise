package com.ktm.controller;


import com.ktm.entity.Question;
import com.ktm.entity.User;
import com.ktm.service.IQuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PublishController {

    @Resource
    private IQuestionService questionService;


    @GetMapping("/publish")
    public String publish(HttpServletRequest request) {
        request.setAttribute("question",new Question());
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(Question question, HttpSession session) {
        User user = (User) session.getAttribute("user");

        question.setCreator(user.getId());
        questionService.insertOrUpdate(question);
        return "redirect:/index";
    }

    @GetMapping("/publishByQuestionId")
    public String publishByQuestionId(@RequestParam(name = "questionId") Integer id, HttpServletRequest request) {

        Question question = questionService.getById(id);

        request.setAttribute("question", question);
        return "publish";
    }
}

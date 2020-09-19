package com.ktm.controller;


import com.ktm.dto.QuestionDTO;
import com.ktm.entity.Question;
import com.ktm.service.IQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ktm
 * @since 2020-09-17
 */
@Controller
public class QuestionController {

    @Resource
    private IQuestionService questionService;


    @GetMapping("/question")
    public String detail(@RequestParam(name = "questionId") Integer id, HttpServletRequest request) {

        QuestionDTO questionDTO = questionService.selectQuestionById(id);
        request.setAttribute("question", questionDTO);
        return "question";
    }
}

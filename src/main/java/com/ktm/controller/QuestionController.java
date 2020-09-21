package com.ktm.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktm.dto.CommentDTO;
import com.ktm.dto.QuestionDTO;
import com.ktm.entity.Comment;
import com.ktm.entity.Question;
import com.ktm.service.ICommentService;
import com.ktm.service.IQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Resource
    private ICommentService commentService;


    @GetMapping("/question")
    public String detail(@RequestParam(name = "questionId") Integer id, HttpServletRequest request) {

        QuestionDTO questionDTO = questionService.selectQuestionById(id);
        List<CommentDTO> commentDTOS = commentService.selectComments(id);
        request.setAttribute("comments", commentDTOS);
        request.setAttribute("question", questionDTO);
        return "question";
    }
}

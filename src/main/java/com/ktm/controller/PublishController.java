package com.ktm.controller;


import com.ktm.cache.TagsCache;
import com.ktm.entity.Question;
import com.ktm.entity.User;
import com.ktm.service.IQuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
        request.setAttribute("question", new Question());
        request.setAttribute("tags", TagsCache.getTagDTO());
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(Question question, HttpSession session, HttpServletRequest request) {
        request.setAttribute("tags", TagsCache.getTagDTO());
        if (StringUtils.isEmpty(question.getTitle())) {
            request.setAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(question.getDescription())) {
            request.setAttribute("error", "内容不能为空");
            return "publish";
        }
        String invalidTags = TagsCache.invalidTags(question.getTag());
        if (!StringUtils.isEmpty(invalidTags)  ) {
            request.setAttribute("error", "输入的标签不合法"+invalidTags);
            return "publish";
        }
        User user = (User) session.getAttribute("user");
        question.setCreator(user.getId());
        questionService.insertOrUpdate(question);
        return "redirect:/index";
    }

    @GetMapping("/publishByQuestionId")
    public String publishByQuestionId(@RequestParam(name = "questionId") Integer id, HttpServletRequest request) {
        request.setAttribute("tags", TagsCache.getTagDTO());
        Question question = questionService.getById(id);

        request.setAttribute("question", question);
        request.setAttribute("tags", TagsCache.getTagDTO());
        return "publish";
    }
}

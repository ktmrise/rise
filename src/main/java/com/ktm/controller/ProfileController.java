package com.ktm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.dto.QuestionDTO;
import com.ktm.entity.User;
import com.ktm.service.IQuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController extends BaseController {


    @Resource
    private IQuestionService questionService;


    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action, HttpServletRequest request,
                          @RequestParam(name = "current", defaultValue = "1") int current,
                          @RequestParam(name = "size", defaultValue = "6", required = false) int size) {
        if ("questions".equals(action)) {
            request.setAttribute("section", "questions");
            request.setAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            request.setAttribute("section", "replies");
            request.setAttribute("sectionName", "最新回复");
        }

        User user = (User) request.getSession().getAttribute("user");
        IPage<QuestionDTO> result = questionService.pagingByUserId(new Page<QuestionDTO>(current, size), user.getId());


        //分页信息
        pageData(request, current, result);
        return "profile";
    }



}

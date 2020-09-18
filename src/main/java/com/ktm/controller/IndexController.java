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
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {


    @Resource
    private IUserService userService;

    @Resource
    private IQuestionService questionService;


    @GetMapping({"/index", "/"})
    public String index(HttpServletRequest request,
                        @RequestParam(name = "current", defaultValue = "1") int current,
                        @RequestParam(name = "size", defaultValue = "8", required = false) int size) {



        IPage<QuestionDTO> result = questionService.paging(new Page<QuestionDTO>(current, size));

        //返回要显示的页码
        List<Integer> pages = selectPages(current, result.getPages());

        //是否包含第一页
        boolean isContainFirst = isContainFirst(pages);
        boolean isContainEnd = isContainEnd(pages, (int) result.getPages());

        request.setAttribute("questions", result.getRecords());
        request.setAttribute("pages", pages);
        request.setAttribute("total", result.getPages());
        request.setAttribute("current", current);
        request.setAttribute("isContainFirst",isContainFirst);
        request.setAttribute("isContainEnd",isContainEnd);
        return "index";
    }

    private boolean isContainEnd(List<Integer> pages, int resultPages) {
        if (pages.contains(resultPages)) {
            return true;
        }
        return false;
    }

    private boolean isContainFirst(List<Integer> pages) {
        if (pages.contains(1)) {
            return true;
        }
        return false;
    }

    private List<Integer> selectPages(int current, long totalPages) {
        List<Integer> pageList = new ArrayList<>();
        if (current <= 3) {
            for (int i = 1; i <= 3 + current; i++) {
                pageList.add(i);
            }
            return pageList;
        }

        if (current < totalPages - 3) {
            for (int i = current - 3; i <= current + 3; i++) {
                pageList.add(i);
            }
            return pageList;
        }

        if (current >= totalPages - 6) {


            for (int i = current; i <totalPages-3; i++) {
                pageList.add(i);
            }
            for (int i = (int) (totalPages - 3); i <= totalPages; i++) {
                pageList.add(i);
            }
            return pageList;
        }

        return pageList;

    }


    @RequestMapping("/logout")
    public String logout() {
        return "index";
    }
}

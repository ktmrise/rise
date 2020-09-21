package com.ktm.controller;


import com.ktm.dto.ResultDTO;
import com.ktm.entity.Comment;
import com.ktm.entity.Question;
import com.ktm.entity.User;
import com.ktm.service.ICommentService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ktm
 * @since 2020-09-20
 */
@Controller
public class CommentController {


    @Resource
    private ICommentService commentService;


    @PostMapping("/comment")
    @ResponseBody
    public ResultDTO comment(@RequestBody Comment comment, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.fail("当前操作需要登录",4000);
        }
        comment.setCommentator(user.getId()).setCreateTime(LocalDate.now()).setModifiedTime(LocalDate.now());


        commentService.insertComment(comment);
        return ResultDTO.ok();
    }


    @ResponseBody
    @GetMapping("/test")
    public Object test() {
        return  ResultDTO.ok();
    }
}

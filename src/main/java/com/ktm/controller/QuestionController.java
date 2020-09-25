package com.ktm.controller;


import com.ktm.dto.CommentDTO;
import com.ktm.dto.NotificationDTO;
import com.ktm.dto.QuestionDTO;
import com.ktm.entity.Question;
import com.ktm.entity.User;
import com.ktm.service.ICommentService;
import com.ktm.service.INotificationService;
import com.ktm.service.IQuestionService;
import org.springframework.web.bind.annotation.GetMapping;

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

    @Resource
    private INotificationService notificationService;


    @GetMapping("/question")
    public String detail(@RequestParam(name = "questionId") Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        QuestionDTO questionDTO = questionService.selectQuestionById(id);
        List<Question> relatedQuestions = questionService.selectRelatedQuestions(id);
        List<CommentDTO> commentDTOS = commentService.selectComments(id);
        request.setAttribute("comments", commentDTOS);
        request.setAttribute("question", questionDTO);
        request.setAttribute("relatedQuestions", relatedQuestions);
        List<NotificationDTO> unreadNotifications;
        if (user!=null) {
            unreadNotifications = notificationService.selectUnreadNotifications(null, user.getId());
            request.setAttribute("unreadCount",unreadNotifications.size());
        }



        return "question";
    }
}

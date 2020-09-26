package com.ktm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.dto.NotificationDTO;
import com.ktm.dto.QuestionDTO;
import com.ktm.entity.User;
import com.ktm.service.INotificationService;
import com.ktm.service.IQuestionService;
import com.ktm.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController extends BaseController {


    @Resource
    private IUserService userService;

    @Resource
    private IQuestionService questionService;

    @Resource
    private INotificationService notificationService;


    @GetMapping({"/index", "/"})
    public String index(HttpServletRequest request,
                        @RequestParam(name = "current", defaultValue = "1") int current,
                        @RequestParam(name = "size", defaultValue = "6", required = false) int size,
                        String keyword) {

        User user = (User) request.getSession().getAttribute("user");

        IPage<QuestionDTO> result = questionService.paging(new Page<QuestionDTO>(current, size),keyword);

        //分页信息
        pageData(request, current, result);
        List<NotificationDTO> unreadNotifications;
        if (user!=null) {
            unreadNotifications = notificationService.selectUnreadNotifications(null, user.getId());
            request.setAttribute("unreadCount",unreadNotifications.size());
        }
        request.setAttribute("keyword",keyword);
        return "index";
    }




}

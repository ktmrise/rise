package com.ktm.controller;


import com.ktm.dto.NotificationDTO;
import com.ktm.entity.Notification;
import com.ktm.entity.User;
import com.ktm.service.INotificationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ktm
 * @since 2020-09-23
 */
@Controller
public class NotificationController {


    @Resource
    private INotificationService notificationService;


    @RequestMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/index";
        }

        Notification notification = notificationService.read(id, user);
        return "redirect:/question?questionId=" + notification.getOuterId();


    }

}

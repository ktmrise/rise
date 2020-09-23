package com.ktm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.dto.NotificationDTO;
import com.ktm.entity.Notification;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ktm.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ktm
 * @since 2020-09-23
 */
public interface INotificationService extends IService<Notification> {

    List<NotificationDTO> selectUnreadNotifications(Page<Object> page);

    Notification read(Integer id, User user);
}

package com.ktm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.dto.NotificationDTO;
import com.ktm.entity.Notification;
import com.ktm.entity.User;
import com.ktm.mapper.NotificationMapper;
import com.ktm.service.INotificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ktm
 * @since 2020-09-23
 */
@Service
public class NotificationServiceImpl extends ServiceImpl<NotificationMapper, Notification> implements INotificationService {

    @Resource
    private NotificationMapper notificationMapper;

    @Override
    public List<NotificationDTO> selectUnreadNotifications(Page<Object> page, Integer userId) {
        QueryWrapper<Notification> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 0);
        wrapper.eq("receiver", userId);
        return notificationMapper.selectUnreadNotifications(page, wrapper);
    }

    @Override
    public Notification read(Integer id, User user) {
        Notification notification = notificationMapper.selectById(id);
        if (notification.getReceiver().intValue()!=user.getId().intValue()) {
            throw new RuntimeException("错误");
        }
        notificationMapper.read(id);
        return notification;
    }
}

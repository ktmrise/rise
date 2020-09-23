package com.ktm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.dto.NotificationDTO;
import com.ktm.entity.Notification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ktm
 * @since 2020-09-23
 */
public interface NotificationMapper extends BaseMapper<Notification> {

    List<NotificationDTO> selectUnreadNotifications(Page<Object> page,@Param(Constants.WRAPPER) QueryWrapper<Notification> wrapper);

    void read(Integer id);
}

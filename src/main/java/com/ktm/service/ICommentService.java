package com.ktm.service;

import com.ktm.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ktm
 * @since 2020-09-20
 */
public interface ICommentService extends IService<Comment> {

    void insertComment(Comment comment);
}

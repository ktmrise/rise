package com.ktm.service;

import com.ktm.dto.CommentDTO;
import com.ktm.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    List<CommentDTO> selectComments(Integer parentId);

    List<CommentDTO> selectTwoComments(Integer parentId);
}

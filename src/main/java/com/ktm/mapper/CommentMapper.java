package com.ktm.mapper;

import com.ktm.dto.CommentDTO;
import com.ktm.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ktm
 * @since 2020-09-20
 */
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentDTO> selectCommentsByParentId(Integer parentId);
}

package com.ktm.service.impl;

import com.ktm.Exception.CustomizeErrorCode;
import com.ktm.Exception.CustomizeException;
import com.ktm.dto.CommentDTO;
import com.ktm.entity.Comment;
import com.ktm.entity.Question;
import com.ktm.mapper.CommentMapper;
import com.ktm.mapper.QuestionMapper;
import com.ktm.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ktm
 * @since 2020-09-20
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    private CommentMapper commentMapper;


    @Resource
    private QuestionMapper questionMapper;

    @Override
    public void insertComment(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.PARAM_NOT_FOUND);
        }
        if (comment.getType() == 1) {
            Question dbQuestion = questionMapper.selectById(comment.getParentId());
            if (dbQuestion == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_QUESTION_NOT_FOUND);
            } else {
                commentMapper.insert(comment);
                questionMapper.addCommentCount(dbQuestion.getId());
            }
        }

        if (comment.getType() == 2) {
            Comment dbComment = commentMapper.selectById(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);

        }

    }

    @Override
    public List<CommentDTO> selectComments(Integer parentId) {

        return commentMapper.selectCommentsByParentId(parentId);
    }
}

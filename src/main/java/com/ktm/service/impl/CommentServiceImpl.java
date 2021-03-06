package com.ktm.service.impl;

import com.ktm.Exception.CustomizeErrorCode;
import com.ktm.Exception.CustomizeException;
import com.ktm.dto.CommentDTO;
import com.ktm.entity.Comment;
import com.ktm.entity.Notification;
import com.ktm.entity.Question;
import com.ktm.entity.User;
import com.ktm.enums.CommentTypeEnum;
import com.ktm.enums.NotificationStatusEnum;
import com.ktm.enums.NotificationTypeEnum;
import com.ktm.mapper.CommentMapper;
import com.ktm.mapper.NotificationMapper;
import com.ktm.mapper.QuestionMapper;
import com.ktm.mapper.UserMapper;
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


    @Resource
    private NotificationMapper notificationMapper;


    @Resource
    private UserMapper userMapper;

    @Override
    public void insertComment(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.PARAM_NOT_FOUND);
        }
        //回复问题
        if (comment.getType().equals(CommentTypeEnum.REPLY_QUESTION.getType())) {
            Question dbQuestion = questionMapper.selectById(comment.getParentId());
            if (dbQuestion == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_QUESTION_NOT_FOUND);
            } else {
                commentMapper.insert(comment);
                questionMapper.addCommentCount(dbQuestion.getId());
                if (!comment.getCommentator().equals(dbQuestion.getCreator())) {
                    createNotification(comment, NotificationTypeEnum.REPLY_QUESTION.getType(), dbQuestion.getCreator());
                }

            }
        }

        //回复评论
        if (comment.getType().equals(CommentTypeEnum.REPLY_COMMENT.getType())) {
            Comment dbComment = commentMapper.selectById(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
            commentMapper.addCommentCount(dbComment.getId());
            if (!comment.getCommentator().equals(dbComment.getCommentator())) {
                createNotification(comment, NotificationTypeEnum.REPLY_COMMMENT.getType(), dbComment.getCommentator());

            }

        }

    }

    private void createNotification(Comment comment, Integer type, Integer receiver) {
        Notification notification = new Notification();
        User user = userMapper.selectById(comment.getCommentator());
        if (type.equals(NotificationTypeEnum.REPLY_QUESTION.getType())) {
            Question question = questionMapper.selectById(comment.getParentId());
            notification.setOuterTitle(question.getTitle());
            notification.setOuterId(comment.getParentId());
        } else {
            Comment dbComment = commentMapper.selectById(comment.getParentId());
            notification.setOuterTitle(dbComment.getContent());
            notification.setOuterId(dbComment.getParentId());
        }
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus())
                .setCreateTime(LocalDate.now())
                .setNotifier(comment.getCommentator())
                .setType(type)
                .setReceiver(receiver)
                .setNotifierName(user.getName());
        notificationMapper.insert(notification);
    }

    @Override
    public List<CommentDTO> selectComments(Integer parentId) {

        return commentMapper.selectCommentsByParentId(parentId);
    }

    @Override
    public List<CommentDTO> selectTwoComments(Integer parentId) {
        return commentMapper.selectTwoCommentsByParentId(parentId);
    }
}

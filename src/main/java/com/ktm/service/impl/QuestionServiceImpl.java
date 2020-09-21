package com.ktm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.Exception.CustomizeErrorCode;
import com.ktm.Exception.CustomizeException;
import com.ktm.dto.QuestionDTO;
import com.ktm.entity.Question;
import com.ktm.mapper.QuestionMapper;
import com.ktm.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ktm
 * @since 2020-09-17
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public void insert(Question question) {
        question.setCreateTime(LocalDate.now())
                .setModifiedTime(LocalDate.now());
        questionMapper.insert(question);
    }

    @Override
    public IPage<QuestionDTO> paging(Page page) {
        return questionMapper.selectPages(page);
    }

    @Override
    public IPage<QuestionDTO> pagingByUserId(Page page, Integer userId) {
        return questionMapper.selectPagesByUserId(page, new QueryWrapper<QuestionDTO>().eq("q.creator", userId).orderByDesc("q.id"));
    }

    @Override
    public QuestionDTO selectQuestionById(Integer id) {
        QuestionDTO questionDTO = questionMapper.selectQuestionById(id);
        questionMapper.addViewCount(id);
        if (questionDTO == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        return questionDTO;
    }

    @Override
    public void insertOrUpdate(Question question) {
        Question dbQuestion = questionMapper.selectById(question.getId());
        if (dbQuestion != null) {
            question.setModifiedTime(LocalDate.now());
            questionMapper.updateById(question);
        } else {
            question.setCreateTime(LocalDate.now());
            question.setModifiedTime(LocalDate.now());
            questionMapper.insert(question);
        }
    }


}

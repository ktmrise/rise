package com.ktm.service.impl;

import com.ktm.entity.Question;
import com.ktm.mapper.QuestionMapper;
import com.ktm.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
}

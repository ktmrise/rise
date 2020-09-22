package com.ktm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.dto.QuestionDTO;
import com.ktm.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ktm
 * @since 2020-09-17
 */
public interface IQuestionService extends IService<Question> {

    void insert(Question question);

    IPage<QuestionDTO> paging(Page page);

    IPage<QuestionDTO> pagingByUserId(Page page,Integer id);

    QuestionDTO selectQuestionById(Integer id);

    void insertOrUpdate(Question question);

    List<Question> selectRelatedQuestions(Integer id);
}

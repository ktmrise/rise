package com.ktm.service;

import com.ktm.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

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
}

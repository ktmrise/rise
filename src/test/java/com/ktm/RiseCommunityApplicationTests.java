package com.ktm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.dto.QuestionDTO;
import com.ktm.mapper.QuestionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RiseCommunityApplicationTests {


    @Resource
    private QuestionMapper questionMapper;


    @Test
    void contextLoads() {
        IPage<QuestionDTO> result = questionMapper.selectPages(new Page(2, 4));
        System.out.println(result.getRecords());
    }

}

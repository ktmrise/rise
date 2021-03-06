package com.ktm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ktm.dto.QuestionDTO;
import com.ktm.entity.User;
import com.ktm.mapper.QuestionMapper;
import com.ktm.mapper.UserMapper;
import com.ktm.service.IUserService;
import com.ktm.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RiseCommunityApplicationTests {


    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private IUserService userService;

    @Resource
    private UserMapper userMapper;


    @Test
    void contextLoads() {
        User one = userMapper.selectOne(new QueryWrapper<User>().eq("account_id", 60636088));
        System.out.println(one);
        User user = userService.getOne(new QueryWrapper<User>().eq("account_id", 60636088));
        System.out.println(user);
    }

}

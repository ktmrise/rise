package com.ktm.service.impl;

import com.ktm.entity.User;
import com.ktm.mapper.UserMapper;
import com.ktm.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ktm
 * @since 2020-09-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}

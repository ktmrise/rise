package com.ktm.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ktm.entity.User;
import com.ktm.service.IUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class Interceptor implements WebMvcConfigurer {


    @Resource
    private IUserService userService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

                Cookie[] cookies = request.getCookies();
                if (cookies.length > 0) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("token")) {
                            User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getToken, cookie.getValue()));
                            if (user != null) {
                                request.getSession().setAttribute("user", user);
                            }
                            break;
                        }
                    }
                }

                return true;
            }
        }).addPathPatterns("/index");
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error").setViewName("error");
    }
}

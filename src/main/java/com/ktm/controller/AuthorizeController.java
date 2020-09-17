package com.ktm.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktm.dto.AccessTokenDTO;
import com.ktm.dto.GithubUser;
import com.ktm.entity.User;
import com.ktm.provider.GithubProvider;
import com.ktm.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.UUID;

@Controller
public class AuthorizeController {


    @Value("${client_id}")
    String client_id;

    @Value("${client_secret}")
    String client_secret;

    @Value("${redirect_uri}")
    String redirect_uri;

    @Resource
    private GithubProvider githubProvider;

    @Resource
    private IUserService userService;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                            HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id).setClient_secret(client_secret).setCode(code).setState(state).setRedirect_uri(redirect_uri);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getGithubUser(accessToken);
        if (githubUser != null) {
            User dbUser = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getAccountId, githubUser.getId()));
            if (dbUser == null) {
                User user = new User();
                String token = UUID.randomUUID().toString();
                user.setCreateTime(LocalDate.now()).
                        setModifiedTime(LocalDate.now()).
                        setName(githubUser.getName()).
                        setAccountId(String.valueOf(githubUser.getId())).
                        setToken(token).
                        setBio(githubUser.getBio());
                userService.save(user);
                response.addCookie(new Cookie("token", token));
            } else {
                response.addCookie(new Cookie("token", dbUser.getToken()));
            }


        }
        return "redirect:/index";

    }

}

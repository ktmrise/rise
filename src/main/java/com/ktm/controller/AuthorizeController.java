package com.ktm.controller;


import com.ktm.dto.AccessTokenDTO;
import com.ktm.dto.GithubUser;
import com.ktm.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state, HttpSession session) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id).setClient_secret(client_secret).setCode(code).setState(state).setRedirect_uri(redirect_uri);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getGithubUser(accessToken);
        if (githubUser != null) {
            session.setAttribute("name", githubUser.getName());
        }
        return "redirect:/index";

    }

}

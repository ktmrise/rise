package com.ktm.provider;

import com.alibaba.fastjson.JSON;
import com.ktm.dto.AccessTokenDTO;
import com.ktm.dto.GithubUser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = okhttp3.RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {

            String untreatedToken = response.body().string();
            String token = untreatedToken.split("=")[1].split("&")[0];
            System.out.println(token);
            return token;
        } catch (IOException e) {
            log.error("token获取出错");
            return null;

        }

    }

    public GithubUser getGithubUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();


        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            GithubUser githubUser = JSON.parseObject(res, GithubUser.class);
            return githubUser;

        } catch (IOException e) {
            log.error("githubUser获取出错");
            return null;
        }
    }
}

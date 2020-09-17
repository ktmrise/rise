package com.ktm.dto;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GithubUser {

    private Long id;
    private String name;
    private String bio;  //描述
    private String avatar_url;
}

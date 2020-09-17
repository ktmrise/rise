package com.ktm.dto;

import com.ktm.entity.Question;
import lombok.Data;


@Data
public class QuestionDTO extends Question {

    private String authorName;
    private String avatarUrl;
}

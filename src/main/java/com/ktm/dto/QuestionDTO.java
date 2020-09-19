package com.ktm.dto;

import com.ktm.entity.Question;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionDTO extends Question {

    private String authorName;
    private String avatarUrl;
}

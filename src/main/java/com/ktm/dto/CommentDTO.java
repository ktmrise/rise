package com.ktm.dto;

import com.ktm.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentDTO extends Comment {


    String authorAvatarUrl;
    String authorName;

}

package com.ktm.dto;

import lombok.Data;

@Data
public class NotificationDTO {


    private String notifierName;
    private String titleOrContent;
    private Integer type;
    private Integer outerId;
    private Integer id;
    private Integer status;
}

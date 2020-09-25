package com.ktm.controller;


import com.ktm.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileUploadController {


    @PostMapping("/fileUpload")
    @ResponseBody
    public Object fileUpload() {
        FileDTO fileDTO = new FileDTO();
        return fileDTO.setSuccess(1).setMessage("头像").setUrl("http://localhost:8887/images/bg.jpg");

    }
}

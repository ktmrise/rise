package com.ktm.controller;


import com.ktm.dto.FileDTO;
import com.ktm.provider.UcloudProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;

@Controller
public class FileUploadController {


    @Resource
    private UcloudProvider ucloudProvider;


    @PostMapping("/fileUpload")
    @ResponseBody
    public Object fileUpload(@RequestParam(name = "editormd-image-file") MultipartFile photo) throws IOException {

        String url = ucloudProvider.upload(photo.getInputStream(), photo.getContentType(), Objects.requireNonNull(photo.getOriginalFilename()));

        FileDTO fileDTO = new FileDTO();
        return fileDTO.setSuccess(1).setMessage("头像").setUrl(url);

    }
}

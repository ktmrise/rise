package com.ktm.Exception;


import com.alibaba.fastjson.JSON;
import com.ktm.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public Object handler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (request.getContentType().equals("application/json")) {

            ResultDTO resultDTO;

            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.fail(e.getMessage());
            } else {
                resultDTO = ResultDTO.fail();
            }

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter writer = response.getWriter();

            writer.write(JSON.toJSONString(resultDTO));
            writer.flush();
            writer.close();
            return null;
        } else {
            if (e instanceof CustomizeException) {
                request.setAttribute("message", e.getMessage());
            } else {
                request.setAttribute("message", "服务出错了,稍后再试一试");
            }

            log.error(e.getMessage());

            return new ModelAndView("error");
        }


    }


}

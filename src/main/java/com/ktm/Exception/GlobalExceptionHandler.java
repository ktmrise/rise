package com.ktm.Exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ModelAndView handler(Exception e, HttpServletRequest request) {
        if (e instanceof CustomizeException) {
            request.setAttribute("message", e.getMessage());
        } else {
            request.setAttribute("message", "服务出错了,稍后再试一试");
        }

      log.error(e.getMessage());

        return new ModelAndView("error");
    }


    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("java.servlet.error.status_code");
        if (statusCode != null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;

        }
        return HttpStatus.valueOf(statusCode);
    }
}

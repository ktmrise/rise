package com.ktm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ktm.dto.QuestionDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class BaseController {


    protected boolean isContainEnd(List<Integer> pages, int resultPages) {
        return pages.contains(resultPages);
    }

    protected boolean isContainFirst(List<Integer> pages) {
        return pages.contains(1);
    }

    protected List<Integer> selectPages(int current, long totalPages) {
        List<Integer> pageList = new ArrayList<>();
        if (current <= 3) {
            for (int i = 1; i <=Math.min(3 + current,totalPages) ; i++) {
                pageList.add(i);
            }
            return pageList;
        }

        if (current < totalPages - 3) {
            for (int i = current - 3; i <= current + 3; i++) {
                pageList.add(i);
            }
            return pageList;
        }

        if (current >= totalPages - 6) {
            for (int i = current; i < totalPages - 3; i++) {
                pageList.add(i);
            }
            for (int i = (int) (totalPages - 3); i <= totalPages; i++) {
                pageList.add(i);
            }
            return pageList;
        }

        return pageList;

    }

    protected void pageData(HttpServletRequest request, int current, IPage<QuestionDTO> result) {
        List<Integer> pages = selectPages(current, result.getPages());

        //是否包含第一页
        boolean isContainFirst = isContainFirst(pages);
        //是否包含最后一页
        boolean isContainEnd = isContainEnd(pages, (int) result.getPages());

        request.setAttribute("questions", result.getRecords());
        request.setAttribute("pages", pages);
        request.setAttribute("total", result.getPages());
        request.setAttribute("current", current);
        request.setAttribute("isContainFirst", isContainFirst);
        request.setAttribute("isContainEnd", isContainEnd);
    }
}

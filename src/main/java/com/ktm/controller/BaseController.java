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

    private static List<Integer> list(int cur, long total) {
        List<Integer> res = new ArrayList<>();
        //左边部分
        for (int i = 3; i >= 1; i--) {
            int j = cur - i;
            if (j >= 1) res.add(j);
        }
        res.add(cur);
        //右边部分
        for (int i = 1; i <= 3; i++) {
            int j = cur + i;
            if (j <= total) res.add(j);
        }
        return res;
    }

    protected void pageData(HttpServletRequest request, int current, IPage<QuestionDTO> result) {
        List<Integer> pages = list(current, result.getPages());

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

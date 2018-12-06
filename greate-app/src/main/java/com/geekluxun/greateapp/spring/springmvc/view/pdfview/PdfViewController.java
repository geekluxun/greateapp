package com.geekluxun.greateapp.spring.springmvc.view.pdfview;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-30 11:07
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/pdf")
@Slf4j
public class PdfViewController {
    @Autowired
    PdfView pdfView;

    @GetMapping("/person")
    public void viewPdf(HttpServletRequest request, HttpServletResponse response) {
        Map model = createData();
        try {
            pdfView.renderMergedOutputModel(model, request, response);
        } catch (Exception e) {
            throw new RuntimeException("查看PDF异常", e);
        }
        log.info("返回结果");
    }

    private Map createData() {
        Map data = new HashMap();
        data.put("name", "luxun");
        data.put("age", 11);
        return data;
    }
}

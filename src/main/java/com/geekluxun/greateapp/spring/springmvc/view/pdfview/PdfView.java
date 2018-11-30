package com.geekluxun.greateapp.spring.springmvc.view.pdfview;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfObject;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-30 11:04
 * @Description:
 * @Other:
 */
@Component
public class PdfView extends AbstractPdfView {
    
    public PdfView(){
        super();
    }
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        document.add(new Paragraph("luxun"));
    }
}

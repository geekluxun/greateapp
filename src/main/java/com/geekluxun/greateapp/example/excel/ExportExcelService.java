package com.geekluxun.greateapp.example.excel;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/29 17:46
 * Description:
 */
public interface ExportExcelService {

    <T> void exportExcel(String[] headers, Collection<T> dataset, String fileName, HttpServletResponse response);




}

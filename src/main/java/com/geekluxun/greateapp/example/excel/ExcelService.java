package com.geekluxun.greateapp.example.excel;

import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Collection;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/29 17:46
 * Description:
 */
public interface ExcelService {

    <T> File exportExcelToLocalFile(String[] headers, Collection<T> dataset, String fileName, String fileDir);


}

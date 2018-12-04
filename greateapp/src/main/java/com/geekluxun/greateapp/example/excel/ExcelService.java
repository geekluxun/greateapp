package com.geekluxun.greateapp.example.excel;

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

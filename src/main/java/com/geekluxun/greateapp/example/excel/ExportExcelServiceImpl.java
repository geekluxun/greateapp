package com.geekluxun.greateapp.example.excel;

import com.geekluxun.greateapp.controller.MainController;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * Project: greateapp
 * Author: luxun
 * Date: 2018/1/29 17:46
 * Description:
 */
@Service(value = "ExportExcelService")
public class ExportExcelServiceImpl implements ExportExcelService {

    private static final Logger logger = LoggerFactory.getLogger(ExportExcelServiceImpl.class);


    /**
     * javabean导出到excel
     *
     * @param headers
     * @param dataset
     * @param fileName
     * @param response
     * @param <T>
     */
    public <T> void exportExcel(String[] headers, Collection<T> dataset, String fileName, HttpServletResponse response) {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(fileName);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        try {
            // 遍历集合数据，产生数据行
            Iterator<T> it = dataset.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                T t = (T) it.next();
                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();

                //填充一行数据的各个字段
                for (short i = 0; i < headers.length; i++) {
                    XSSFCell cell = row.createCell(i);
                    Field field = fields[i];
                    String fieldName = field.getName();
                    //get方法名
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Class tCls = t.getClass();
                    //获取get方法
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    //调用get方法
                    Object value = getMethod.invoke(t, new Object[]{});
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    // 字段是Date的格式化一下
                    if (field.getType().isAssignableFrom(Date.class)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        textValue = sdf.format((Date) value);
                    } else if (value != null && value != "") {   // 其它数据类型都当作字符串简单处理
                        textValue = value.toString();
                    }

                    if (textValue != null) {
                        XSSFRichTextString richString = new XSSFRichTextString(textValue);
                        cell.setCellValue(richString);
                    }
                }
            }

            saveExportedFile(workbook, fileName);
            //saveExportedFile(workbook, fileName, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法说明: 指定路径下生成EXCEL文件
     *
     * @return
     */
    private void saveExportedFile(XSSFWorkbook workbook, String name, HttpServletResponse response) throws Exception {
        BufferedOutputStream fos = null;
        try {
            String fileName = name + ".xlsx";
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            fos = new BufferedOutputStream(response.getOutputStream());
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }


    private void saveExportedFile(XSSFWorkbook workbook, String name) throws Exception {

        String fileName = name + "_" + new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date()) + ".xlsx";
        String filetPath = "d:/user/" + fileName;
        BufferedOutputStream fos = null;

        File file = new File(filetPath);
        if (!file.getParentFile().exists()){
            Boolean result = file.getParentFile().mkdirs();
            if (!result){
                throw  new RuntimeException("创建目录失败");
            }
        }

        try {
            fos = new BufferedOutputStream(new FileOutputStream(file));
            workbook.write(fos);
        } catch (Exception e) {
            logger.error("========== 保存EXCEL文件=========", e);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

    }
}
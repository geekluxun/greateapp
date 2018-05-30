package com.geekluxun.greateapp.example.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
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
@Service
public class ExcelServiceImpl implements ExcelService {

    private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

    //默认的配置属性
    private final int defaultColumnWidth = 20;
    private final short align = CellStyle.ALIGN_CENTER;
    private final String dateFormat = "yyyy-MM-dd HH:mm:ss";


    /**
     * @param headers
     * @param dataset
     * @param fileName
     * @param <T>
     * @return
     */
    public <T> File exportExcelToLocalFile(String[] headers, Collection<T> dataset, String fileName, String fileDir) {
        File file = null;
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(align);

        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(fileName);

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(defaultColumnWidth);

        XSSFRow row;

        if (headers == null || headers.length == 0) {
            logger.error("列数不能为空");
            return null;
        }

        // 产生表格标题行
        row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(style);
        }

        try {
            // 遍历集合数据，产生数据行
            Iterator<T> it = dataset.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                T t = it.next();
                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();

                //填充一行数据的各个字段
                for (short i = 0; i < headers.length; i++) {
                    XSSFCell cell = row.createCell(i);
                    cell.setCellStyle(style);

                    Field field = fields[i];
                    String fieldName = field.getName();
                    //get方法名
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Class tCls = t.getClass();
                    //获取get方法
                    Method getMethod = tCls.getMethod(getMethodName);
                    //调用get方法
                    Object value = getMethod.invoke(t);
                    Class type = field.getType();
                    if (type.isAssignableFrom(Date.class)){
                        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                        cell.setCellValue(sdf.format((Date) value));
                    } else if (type.isAssignableFrom(short.class)
                            || type.isAssignableFrom(Short.class)
                            || type.isAssignableFrom(int.class)
                            || type.isAssignableFrom(Integer.class)
                            || type.isAssignableFrom(long.class)
                            || type.isAssignableFrom(Long.class)
                            || type.isAssignableFrom(double.class)
                            || type.isAssignableFrom(Double.class)
                            || type.isAssignableFrom(float.class)
                            || type.isAssignableFrom(Float.class)
                            || type.isAssignableFrom(BigDecimal.class)
                            ){
                        cell.setCellValue(((Number)value).doubleValue());
                    } else if (type.isAssignableFrom((boolean.class))
                            || type.isAssignableFrom(Boolean.class)){
                        cell.setCellValue((boolean)value);
                    } else {
                        logger.error("数据类型不支持");
                        return null;
                    }
                }
            }

            //保存到本地
            file = saveExportedFile(workbook, fileName, fileDir);

        } catch (NoSuchMethodException e) {
            logger.error("传入参数不是标准的POJO：", e);
        } catch (Exception e) {
            logger.error("生成excel：", e);
        }

        return file;
    }


    /**
     * @param workbook
     * @param name
     * @param response
     * @throws Exception
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


    /**
     * @param workbook
     * @param fileName
     * @return
     * @throws IOException
     */
    private File saveExportedFile(XSSFWorkbook workbook, String fileName, String fileDir) throws IOException {

        String filetPath = fileDir + fileName + ".xlsx";
        BufferedOutputStream fos = null;

        File file = new File(filetPath);
        if (!file.getParentFile().exists()) {
            Boolean result = file.getParentFile().mkdirs();
            if (!result) {
                throw new RuntimeException("创建目录失败");
            }
        }

        try {
            fos = new BufferedOutputStream(new FileOutputStream(file));
            workbook.write(fos);
        } catch (Exception e) {
            logger.error("==========保存excel文件=========", e);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

        return file;

    }


}
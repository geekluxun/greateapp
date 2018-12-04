package com.geekluxun.greateapp.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luxun on 2017/12/28.
 */
public class gbk2utf8 {
    private static List<File> filelist = new ArrayList<File>();

    public static void main(String[] argc) {
        //getFileList("E:\\workspace\\intellij\\mylearning\\sjmszc", ".java");
        getFileList("D:\\sjmszc-code", ".java");
        batchFilegbk2Utf8(filelist);
    }

    private static void batchFilegbk2Utf8(List<File> filelist) {
        for (File file : filelist) {
            gbk2Utf8(file.getAbsolutePath());
        }
    }

    public static void gbk2Utf8(String fileName) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            StringBuffer sb = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "GBK"));
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str).append("\r\n");
            }

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 递归获取指定目录下所有指定类型文件
     *
     * @param strPath 文件夹地址
     * @param suffix  文件名后缀
     * @return
     */

    public static List<File> getFileList(String strPath, String suffix) {
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                System.out.println("fileName:" + fileName);
                if (files[i].isDirectory()) { // 如果是文件夹就递归调用
                    getFileList(files[i].getAbsolutePath(), suffix);
                } else if (fileName.endsWith(suffix)) {
                    filelist.add(files[i]);
                }
            }
        }
        return filelist;
    }


}

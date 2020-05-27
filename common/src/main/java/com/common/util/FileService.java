package com.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileService {


    /**
     * 保存文件到sdcard中某个路径下
     *
     * @param fileName
     * @param content
     * @param type     0--脉诊原始数据  1-脉诊左右手数据
     */
    public static boolean writeFile(String fileName, String content, int type) {
        String mPath = null;

        //设置目录
        switch (type) {
            case 0:
                mPath = CommonConstants.EXTERNALFILESDIR+"/crash";
                break;
        }
        boolean issave;
        File file = new File(mPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File f = new File(mPath + "/" + fileName + ".txt");
        try {
            FileOutputStream fileOS = new FileOutputStream(f);
            try {
                fileOS.write(content.getBytes());
                BufferedWriter buf = new BufferedWriter(new OutputStreamWriter(fileOS));
                buf.write(content, 0, content.length());
                buf.flush();
                buf.close();
                fileOS.close();
                issave = true;
            } catch (IOException e) {
                e.printStackTrace();
                issave = false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            issave = false;
        }
        return issave;
    }




}

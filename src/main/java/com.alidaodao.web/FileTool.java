package com.alidaodao.web;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class FileTool {
    private final static String SAFARI = "Safari";
    private final static String EDGE = "Edge";
    private final static String MSIE = "MSIE";
    private final static String TRIDENT = "Trident";

    private static String filePath;

    public void setFilePath(String filePath) {
        FileTool.filePath = filePath;
    }

    /**
     * 作者 史东晗
     * 时间 2018/1/11 下午5:05
     * 方法名 getFileByte
     * 参数 [file]
     * 返回值 byte[]
     * 描述 获取文件流
     */
    public static byte[] getFileByte(File file) {
        try {
            long fileSize = file.length();
            if (fileSize > Integer.MAX_VALUE) {
                return null;
            }
            FileInputStream fi = new FileInputStream(file);
            byte[] buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead;
            while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            // 确保所有数据均被读取
            if (offset != buffer.length) {
            }
            fi.close();
            return buffer;
        } catch (IOException e) {

        }
    }

    /**
     * 作者 史东晗
     * 时间 2018/1/11 下午5:12
     * 方法名 saveFile
     * 参数 [file]
     * 返回值 void
     * 描述 保存文件
     */
    public static void saveFile(MultipartFile file, String fileSrc) {
        try {
            File path = new File(filePath);
            if (!path.exists()) {
                path.mkdirs();
            }
            File newFile = new File(filePath + File.separator + fileSrc);
//            if (!newFile.exists()) {
//                newFile.createNewFile();
//            }
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SystemException(ResponseCodeEnum.FILE_UPLOAD_ERROR, e);
        }
    }

    /**
     * 删除文件
     * @param fileSrc
     */
    public static void deleteFile(String fileSrc) {
        File file = new File(filePath+fileSrc);
        if (!file.exists()) {
            log.warn("物理文件{}不存在，删除失败!",fileSrc);
        }
        file.delete();
        log.warn("物理文件{}删除成功!",fileSrc);
    }





}

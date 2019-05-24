package com.example.lenovo.myapplication.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lenovo on 2019/4/5.
 * auther:lenovo
 * Date：2019/4/5
 */
public class MD5Utils {
    //    加密字符串
    public static String getMd5String(String srcStr) {
        try {
            MessageDigest mInstance = MessageDigest.getInstance("MD5");
            mInstance.update(srcStr.getBytes("utf-8"));
            byte[] mBytes = mInstance.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b :
                    mBytes) {
                if (Integer.toHexString(0xff & b).length() == 0) {
                    sb.append("0").append(Integer.toHexString(0xff & b));
                } else {
                    sb.append(Integer.toHexString(0xff & b));
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    //     文件校验码
    public static String md5ForFile(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        int buffersize = 1024;
        FileInputStream fis = null;
        DigestInputStream dis = null;
        try {
            MessageDigest mMessageDigest = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            dis = new DigestInputStream(fis, mMessageDigest);
            byte[] buffer = new byte[buffersize];
            int line = 0;
            //DigestInputStream实际上在流处理文件时就在内部就进行了一定的处理
            while ((line = dis.read(buffer)) > 0) {
                mMessageDigest.update(buffer, 0, line);
            }
            fis.close();
            //通过DigestInputStream对象得到一个最终的MessageDigest对象。
            mMessageDigest = dis.getMessageDigest();
            // 通过messageDigest拿到结果，也是字节数组，包含16个元素
            byte[] mDigest = mMessageDigest.digest();
            // 同样，把字节数组转换成字符串
            StringBuilder hex = new StringBuilder(mDigest.length * 2);
            for (byte b : mDigest) {
                if ((b & 0xFF) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}

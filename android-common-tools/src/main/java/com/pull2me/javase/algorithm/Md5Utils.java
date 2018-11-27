package com.pull2me.javase.algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5加密
 *
 * @author drz 2016年5月23日下午8:22:57
 */
public class Md5Utils {


    public static String conver2MD5(String str) {
        String hex;
        StringBuilder sb = null;
        // 获取Md5加密类对象
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            // 从原始数据的字节数组中生成16位（二进制）MD5密文摘要
            byte[] digest = md5.digest(str.getBytes());
            // System.out.println(digest.length);
            // 将16位的密文摘要转换为32位的字符串
            sb = new StringBuilder();
            for (byte b : digest) {
                int ch = b & 0xff;
                // 将int型数值转换为16进制的数字字符串
                hex = Integer.toHexString(ch);
                if (ch < 16) {
                    hex = "0" + hex;
                }
                sb.append(hex);
            }

        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        return sb.toString();
    }
}
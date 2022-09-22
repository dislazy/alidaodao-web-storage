package com.alidaodao.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CryptoTools {

    /**
     * 获取MD5的Hash码(32位)。
     *
     * @param data 需要转化hash码的数据。
     * @return MD5的hash码, 异常时, 返回原数据
     */
    public static String md5Encode(String... data) {
        return getHash("MD5", data);
    }

    /**
     * 获取SHA1
     *
     * @param data 需要转化的数据。
     * @return SHA1的hash码, 异常时, 返回原数据
     */
    public static String sha1Encode(String... data) {
        return getHash("SHA-1", data);
    }

    /**
     * 获取加密信息
     *
     * @param data 需要转化的数据。
     * @return hash码, 异常时, 返回原数据
     */
    private static String getHash(String algorithm, String... data) {
        try {
            // 签名生成
            MessageDigest md = MessageDigest.getInstance(algorithm);
            //update each data
            for (String elem : data) {
                md.update(elem.getBytes());
            }

            byte[] digest = md.digest();
            //变换成16进制表示
            StringBuilder buffer = new StringBuilder();
            for (byte aDigest : digest) {
                String tmp = Integer.toHexString(aDigest & 0xff);
                if (tmp.length() == 1) {
                    buffer.append('0');
                }
                buffer.append(tmp);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return Arrays.toString(data);
        }
    }
}
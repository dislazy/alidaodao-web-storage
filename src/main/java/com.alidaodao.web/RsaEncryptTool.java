package com.alidaodao.web;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaEncryptTool {
    /**
     * 加密
     * @param message 需要加密的字符串
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(final String message, final String publicKey ) {
        //base64编码的公钥
        try {
            final byte[] decoded = Base64.getDecoder().decode(publicKey);
            final RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
            //RSA加密
            final Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);

            final byte[] bytes = message.getBytes("UTF-8");
            final int len = bytes.length;
            int offset = 0;
            int i = 0;
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();

            while (len > offset) {
                byte[] cache;
                if (len - offset > 117) {
                    cache = cipher.doFinal(bytes, offset, 117);
                } else {
                    cache = cipher.doFinal(bytes, offset, len - offset);
                }
                bos.write(cache);
                i++;
                offset = 117 * i;
            }
            bos.close();

            final String encryptMessage = Base64.getEncoder().encodeToString(bos.toByteArray());
            return encryptMessage;
        } catch (InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException
                | IllegalBlockSizeException | BadPaddingException | IOException e) {
            throw new RuntimeException("error");
        }
    }

    /**
     * 解密
     * @param message 需要解密的密文
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String message, final String privateKey) {
        try {
            if (message.contains(" ")) {
                message = message.replaceAll(" ", "+");
            }
            //base64编码的私钥
            final byte[] decoded = Base64.getDecoder().decode(privateKey);
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            //64位解码加密后的字符串
            final byte[] inputByte = Base64.getDecoder().decode(message.getBytes("UTF-8"));

            final int len = inputByte.length;
            int offset = 0;
            int i = 0;
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while (len - offset > 0) {
                byte[] cache;
                if (len - offset > 128) {
                    cache = cipher.doFinal(inputByte, offset, 128);
                } else {
                    cache = cipher.doFinal(inputByte, offset, len - offset);
                }
                bos.write(cache);
                i++;
                offset = 128 * i;
            }
            bos.close();

            return new String(bos.toByteArray(), "UTF-8");
        } catch (InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException
                | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | IOException e) {
            throw new RuntimeException();
        }
    }
}
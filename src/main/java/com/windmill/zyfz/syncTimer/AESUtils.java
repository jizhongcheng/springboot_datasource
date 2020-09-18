package com.windmill.zyfz.syncTimer;

/**
 * @author: HuangSen
 * @description:
 * @Version: [1.0.0]
 * @Copy: [com.windmill]
 * @create: 2020-06-20 15:32
 **/


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtils {


    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static String sKey = "JSDLUSERINFOKEYS";
    private static String ivParameter = "JSDLUSERAESIVFMD";
    private static AESUtils instance = null;


    public static void main(String[] args) {
        try {
            System.out.println(encrypt("我"));
//            System.out.println(decrypt("ZmZoMFpHUXc3Rkx5WWkzb05OSXp5QT09"));
//            System.out.println(decrypt("ffh0ZGQw7FLyYi3oNNIzyA=="));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 加密
     */
    public static String encrypt(String encData) throws Exception {
        byte[] encDataBytes = encData.getBytes();
        encData = new String(Base64.encodeBase64(encDataBytes));
        System.out.println("encData:" +encData);
        if (sKey == null) {
            return null;
        }
        if (sKey.length() != 16) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));

        System.out.println("new String((encrypted):" + parseByte2HexStr(encrypted));
        System.out.println("new String(Base64.encodeBase64(encrypted):" +new String(Base64.encodeBase64(encrypted)));
        return new String(Base64.encodeBase64(encrypted));

    }


    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    /**
     * 解密
     */
    public static String decrypt(String sSrc) throws Exception {
        try {
            sSrc = new String(Base64.decodeBase64(sSrc.getBytes("utf-8")));
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decodeBase64(sSrc.getBytes());// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private AESUtils(String sKey) {
        this.sKey = sKey;
    }

    public static AESUtils getInstance(String sKey) {
        if (instance == null)
            instance = new AESUtils(sKey);
        return instance;
    }

}
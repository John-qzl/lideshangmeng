package com.lidegou.lideshangmeng.mobile.util;

import org.apache.commons.codec.binary.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Y on 2017/2/10.
 */

public class EncryptUtils {
    public static final String KEY = "UITN25LMUQC436IM";
    private static final String AESTYPE = "AES/ECB/PKCS5Padding";

    public static String AES_Encrypt(String plainText) {
        plainText = plainText + "," + getTime();
        byte[] encrypt = null;
        try {
            Key key = generateKey(KEY);
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypt = cipher.doFinal(plainText.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(encrypt));
    }

    public static String AES_Decrypt(String encryptData) {
        byte[] decrypt = null;
        try {
            Key key = generateKey(KEY);
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypt = cipher.doFinal(Base64.decodeBase64(encryptData.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(decrypt).trim();
    }

    private static Key generateKey(String key) throws Exception {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            return keySpec;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static String getTime() {
        return System.currentTimeMillis() + "";
    }
}

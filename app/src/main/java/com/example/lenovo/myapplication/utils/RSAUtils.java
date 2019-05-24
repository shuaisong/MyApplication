package com.example.lenovo.myapplication.utils;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by lenovo on 2019/4/5.
 * auther:lenovo
 * Date：2019/4/5
 */
public class RSAUtils {
    //构建Cipher实例时所传入的的字符串，默认为"RSA/NONE/PKCS1Padding"
    private static String mTransform = "RSA/NONE/PKCS1Padding";

    //    构建Base64转码时的flag设置，默认为Base64.DEFAULT
    private static int mBase64Mode = Base64.DEFAULT;

    public static void init(String transform, int base64Mode) {
        mTransform = transform;
        mBase64Mode = base64Mode;
    }

    /**
     * @param keyLength 密匙长度
     * @return 密匙
     */
    public static KeyPair generateKeyPair(int keyLength) {
        KeyPair keyPair = null;
        try {
            KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance("RSA");
            pairGenerator.initialize(keyLength);//设置密匙长度
            keyPair = pairGenerator.generateKeyPair();//获取密匙队
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyPair;
    }

    /**
     * 加密或解密方法
     *
     * @param srcData 原数据
     * @param key     公匙或私匙
     * @param mode    加密或解密
     *                Cipher. ENCRYPT_MODE 加密
     *                Cipher.DECRYPT_MODE 解密
     * @return 结果数据
     */
    public static byte[] processData(byte[] srcData, Key key, int mode) {
        byte[] resultData = null;
        try {
            Cipher cipher = Cipher.getInstance(mTransform);
            cipher.init(mode, key);
            resultData = cipher.doFinal(srcData);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return resultData;
    }

    /**
     * 公匙加密并base64转码
     *
     * @param srcData   原字节数据
     * @param publicKey 公匙
     * @return 结果
     */
    public static String encryptDataByPublicKey(byte[] srcData, PublicKey publicKey) {
        byte[] data = processData(srcData, publicKey, Cipher.ENCRYPT_MODE);
        return Base64.encodeToString(data, mBase64Mode);
    }

    /**
     * 私匙解密并base64转码
     *
     * @param encryptedData    原字节数据
     * @param privateKey 私匙
     * @return 结果
     */
    public static byte[] decryptDataByPrivate(String  encryptedData, PrivateKey privateKey) {
        byte[]bytes=Base64.decode(encryptedData,mBase64Mode);
        return processData(bytes,privateKey,Cipher.DECRYPT_MODE);
    }


    /**
     * @param encryptedData 数据
     * @param privateKey    私钥
     * @return 解码数据
     */
    //使用私钥解密，返回解码数据
    public static String decryptToStrByPrivate(String encryptedData, PrivateKey privateKey) {
        return new String(decryptDataByPrivate(encryptedData,privateKey));
    }

}

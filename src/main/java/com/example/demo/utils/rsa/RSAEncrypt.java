package com.example.demo.utils.rsa;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author GEEX177
 */
@Slf4j
public class RSAEncrypt {
    /**
     * 字节数据转字符串专用集合
     */
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    private static final String PRIVATE_KEY = "/private_key_pkcs8.pem";

    private static final String PUBLIC_KEY = "/public_key.pem";

    /**
     * 随机生成密钥对
     */
    public static void genKeyPair(String filePath) {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        try {
            // 得到公钥字符串
            Base64 base64 = new Base64();
            String publicKeyString = new String(base64.encode(publicKey.getEncoded()));
            // 得到私钥字符串
            String privateKeyString = new String(base64.encode(privateKey.getEncoded()));
            // 将密钥对写入到文件
            FileWriter pubfw = new FileWriter(filePath + PUBLIC_KEY);
            FileWriter prifw = new FileWriter(filePath + PRIVATE_KEY);
            BufferedWriter pubbw = new BufferedWriter(pubfw);
            BufferedWriter pribw = new BufferedWriter(prifw);
            pubbw.write(publicKeyString);
            pribw.write(privateKeyString);
            pubbw.flush();
            pubbw.close();
            pubfw.close();
            pribw.flush();
            pribw.close();
            prifw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中输入流中加载公钥
     *
     * @param path 公钥输入流
     * @throws Exception 加载公钥时产生的异常
     */
    public static String loadPublicKeyByFile(String path) throws IOException, NullPointerException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path
                    + PUBLIC_KEY));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            log.error("公钥数据流读取错误", e);
            throw new IOException("公钥数据流读取错误");
        } catch (NullPointerException e) {
            log.error("公钥输入流为空", e);
            throw new NullPointerException("公钥输入流为空");
        }
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NullPointerException, IOException {
        try {
            BASE64Decoder base64 = new BASE64Decoder();
            byte[] buffer = base64.decodeBuffer(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            log.error("无此算法", e);
            throw new NoSuchAlgorithmException("无此算法");
        } catch (InvalidKeySpecException e) {
            log.error("公钥非法", e);
            throw new InvalidKeySpecException("公钥非法");
        } catch (NullPointerException e) {
            log.error("公钥数据非空", e);
            throw new NullPointerException("公钥数据为空");
        } catch (IOException e) {
            log.error("BASE64 decode失败", e);
            throw new IOException("BASE64 decode失败");
        }
    }

    /**
     * 从文件中加载私钥
     *
     * @param path 私钥文件名
     * @return 是否成功
     * @throws Exception
     */
    public static String loadPrivateKeyByFile(String path) throws IOException, NullPointerException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + PRIVATE_KEY));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            log.error("私钥数据读取错误", e);
            throw new IOException("私钥数据读取错误");
        } catch (NullPointerException e) {
            log.error("私钥输入流为空", e);
            throw new NullPointerException("私钥输入流为空");
        }
    }

    /**
     * 从字符串中加载私钥
     *
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NullPointerException, IOException {
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            log.error("无此算法", e);
            throw new NoSuchAlgorithmException("无此算法");
        } catch (InvalidKeySpecException e) {
            log.error("私钥非法", e);
            throw new InvalidKeySpecException("私钥非法");
        } catch (NullPointerException e) {
            log.error("私钥数据为空", e);
            throw new NullPointerException("私钥数据为空");
        } catch (IOException e) {
            log.error("BASE64 decode 失败", e);
            throw new IOException("BASE64 decode 失败", e);
        }
    }

    /**
     * 公钥加密过程
     *
     * @param publicKey 公钥
     * @param srcData   明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] srcData)
            throws Exception {
        if (publicKey == null) {
            throw new NullPointerException("加密公钥为空, 请设置");
        }
        return RsaCodingUtil.rsaByPublicKey(srcData, publicKey, Cipher.ENCRYPT_MODE);
    }

    /**
     * 私钥加密过程
     *
     * @param privateKey 私钥
     * @param srcData    明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] srcData)
            throws Exception {
        if (privateKey == null) {
            throw new NullPointerException("加密私钥为空, 请设置");
        }
        return RsaCodingUtil.rsaByPrivateKey(srcData, privateKey, Cipher.ENCRYPT_MODE);
    }

    /**
     * 私钥解密过程
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)
            throws Exception {
        if (privateKey == null) {
            throw new NullPointerException("解密私钥为空, 请设置");
        }
        return RsaCodingUtil.rsaByPrivateKey(cipherData, privateKey, Cipher.DECRYPT_MODE);
    }

    /**
     * 公钥解密过程
     *
     * @param publicKey  公钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData)
            throws Exception {
        if (publicKey == null) {
            throw new NullPointerException("解密公钥为空, 请设置");
        }
        return RsaCodingUtil.rsaByPublicKey(cipherData, publicKey, Cipher.DECRYPT_MODE);
    }

    /**
     * 字节数据转十六进制字符串
     *
     * @param data 输入数据
     * @return 十六进制内容
     */
    public static String byteArrayToString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            // 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
            // 取出字节的低四位 作为索引得到相应的十六进制标识符
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
            if (i < data.length - 1) {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }
}

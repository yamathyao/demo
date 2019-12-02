package com.example.demo.utils.rsa;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * <b>Rsa加解密工具</b><br>
 *
 * @author GEEX177
 */
@Slf4j
public final class RsaCodingUtil {


    // ======================================================================================
    // 公私钥算法
    // ======================================================================================

    /**
     * 公钥算法
     *
     * @param srcData   源字节
     * @param publicKey 公钥
     * @param mode      加密 OR 解密
     * @return
     */
    public static byte[] rsaByPublicKey(byte[] srcData, PublicKey publicKey, int mode) throws NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance(RsaConst.RSA_CHIPER);
            cipher.init(mode, publicKey);
            // 分段加密
            int blockSize = (mode == Cipher.ENCRYPT_MODE) ? RsaConst.ENCRYPT_KEYSIZE : RsaConst.DECRYPT_KEYSIZE;
            byte[] encryptedData = null;
            for (int i = 0; i < srcData.length; i += blockSize) {
                // 注意要使用2的倍数，否则会出现加密后的内容再解密时为乱码
                byte[] doFinal = cipher.doFinal(subarray(srcData, i, i + blockSize));
                encryptedData = addAll(encryptedData, doFinal);
            }
            return encryptedData;

        } catch (NoSuchAlgorithmException e) {
            log.error("公钥算法-不存在的解密算法:", e);
            throw new NoSuchAlgorithmException("公钥算法-不存在的解密算法:");
        } catch (NoSuchPaddingException e) {
            log.error("公钥算法-无效的补位算法:", e);
            throw new NoSuchPaddingException("公钥算法-无效的补位算法");
        } catch (IllegalBlockSizeException e) {
            log.error("公钥算法-无效的块大小:", e);
            throw new IllegalBlockSizeException("公钥算法-无效的块大小");
        } catch (BadPaddingException e) {
            log.error("公钥算法-补位算法异常:", e);
            throw new BadPaddingException("公钥算法-补位算法异常");
        } catch (InvalidKeyException e) {
            log.error("公钥算法-无效的私钥:", e);
            throw new InvalidKeyException("公钥算法-无效的私钥:");
        }
    }

    /**
     * 私钥算法
     *
     * @param srcData    源字节
     * @param privateKey 私钥
     * @param mode       加密 OR 解密
     * @return
     */
    public static byte[] rsaByPrivateKey(byte[] srcData, PrivateKey privateKey, int mode) throws NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        try {
            Cipher cipher = Cipher.getInstance(RsaConst.RSA_CHIPER);
            cipher.init(mode, privateKey);
            // 分段加密
            int blockSize = (mode == Cipher.ENCRYPT_MODE) ? RsaConst.ENCRYPT_KEYSIZE : RsaConst.DECRYPT_KEYSIZE;
            byte[] decryptData = null;

            for (int i = 0; i < srcData.length; i += blockSize) {
                byte[] doFinal = cipher.doFinal(subarray(srcData, i, i + blockSize));

                decryptData = addAll(decryptData, doFinal);
            }
            return decryptData;
        } catch (NoSuchAlgorithmException e) {
            log.error("私钥算法-不存在的解密算法:", e);
            throw new NoSuchAlgorithmException("私钥算法-不存在的解密算法:");
        } catch (NoSuchPaddingException e) {
            log.error("私钥算法-无效的补位算法:", e);
            throw new NoSuchPaddingException("私钥算法-无效的补位算法");
        } catch (IllegalBlockSizeException e) {
            log.error("私钥算法-无效的块大小:", e);
            throw new IllegalBlockSizeException("私钥算法-无效的块大小");
        } catch (BadPaddingException e) {
            log.error("私钥算法-补位算法异常:", e);
            throw new BadPaddingException("私钥算法-补位算法异常");
        } catch (InvalidKeyException e) {
            log.error("私钥算法-无效的私钥:", e);
            throw new InvalidKeyException("私钥算法-无效的私钥:");
        }
    }

    /**
     * 分段密文
     *
     * @param array
     * @param startIndexInclusive
     * @param endIndexExclusive
     * @return
     */
    public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;

        if (newSize <= 0) {
            return new byte[0];
        }

        byte[] subarray = new byte[newSize];

        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);

        return subarray;
    }

    /**
     * 组合密文
     *
     * @param array1
     * @param array2
     * @return
     */
    public static byte[] addAll(byte[] array1, byte[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }

    public static byte[] clone(byte[] array) {
        if (array == null) {
            return null;
        }
        return (byte[]) array.clone();
    }
}

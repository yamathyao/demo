package com.example.demo.hashmap;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author GEEX177
 * @date 2019/10/30
 */
public class HashingAlg {

    public HashingAlg() {
    }

    /**
     * 使用md5算法
     *
     * @param key
     * @return
     */
    public static long md5HashingAlg(String key) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(key.getBytes());
            byte[] bKey = md5.digest();
            return ((long) (bKey[3] & 0xFF) << 24) | ((long) (bKey[2] & 0xFF) << 16)
                    | ((long) (bKey[1] & 0xFF) << 8 | (long) (bKey[0] & 0xFF));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return 0L;
    }

    /**
     * 使用FNV1hash算法
     *
     * @param key
     * @return
     */
    public static long fnv1HashingAlg(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); ++i) {
            hash = (hash ^ key.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }
}

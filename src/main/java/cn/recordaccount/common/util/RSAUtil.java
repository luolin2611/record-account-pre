package cn.recordaccount.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


/**
 * RSA公钥/私钥/签名工具包
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，
 */
@Slf4j
public class RSAUtil {

    private RSAUtil() {
        throw new IllegalStateException("RsaUtil");
    }


    /**
     * 加密算法RSA
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 245;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 256;

    /**
     * RSA 位数 如果采用2048 上面最大加密和最大解密则须填写:  245 256
     */
    private static final int INITIALIZE_LENGTH = 2048;
    /**
     * 生成密钥对(公钥和私钥)
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(INITIALIZE_LENGTH);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Map<String, Object> keyMap = new HashMap<>(2);
            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);
            return keyMap;
        } catch (Exception e) {
            log.error("获取公私钥对出现问题", e);
            return null;
        }
    }

    /**
     * 获取私钥
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 获取公钥 密钥对
     *
     * @param keyMap 密钥对
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) {
        PrivateKey privateK = getPrivateKey(privateKey);
        Signature signature = null;
        try {
            signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateK);
            signature.update(data);
            return Base64.encodeBase64String(signature.sign());
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
            return null;
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException", e);
            return null;
        } catch (SignatureException e) {
            log.error("", e);
            return null;
        }
    }

    /**
     * 校验数字签名
     *
     * @param data      已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign      数字签名
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) {
        PublicKey publicK = getPublicKey(publicKey);
        Signature signature = null;
        try {
            signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicK);
            signature.update(data);
            return signature.verify(Base64.decodeBase64(sign));
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
            return false;
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException", e);
            return false;
        } catch (SignatureException e) {
            log.error("", e);
            return false;
        }
    }

    /**
     * 获取私钥
     *
     * @param privateKey
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKey) {
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (Exception e) {
            log.info("获取私钥时遇到异常", e);
            return null;
        }
    }

    /**
     * 获取公钥
     *
     * @param publicKey
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            log.info("获取公钥时遇到异常", e);
            return null;
        }
    }

    /**
     * 对数据进行分段处理
     *
     * @param data
     * @param maxHandleBlock
     * @param cipher
     * @return
     */
    private static byte[] dataSplit(byte[] data, int maxHandleBlock, Cipher cipher) {
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据进行分段 加/解密
        try {
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > maxHandleBlock) {
                    cache = cipher.doFinal(data, offSet, maxHandleBlock);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * maxHandleBlock;
            }
            byte[] handledData = out.toByteArray();
            out.close();
            return handledData;
        } catch (Exception e) {
            log.info("数据分段时遇到异常", e);
            return new byte[0];
        }
    }

    /**
     * 获取cipher
     *
     * @param mode 模式-加密/解密
     * @param key  公/私钥
     * @return
     */
    private static Cipher getCipher(int mode, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
            cipher.init(mode, key);
            return cipher;
        } catch (Exception e) {
            log.info("获取cipher时遇到异常", e);
            return null;
        }
    }

    /**
     * 公钥加密
     *
     * @param data      源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, String publicKey) {
        Key publicK = getPublicKey(publicKey);
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, publicK);
        return Base64.encodeBase64String(dataSplit(data.getBytes(), MAX_ENCRYPT_BLOCK, cipher));
    }

    /**
     * 私钥加密
     *
     * @param data       源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String data, String privateKey) {
        Key privateK = getPrivateKey(privateKey);
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, privateK);
        return Base64.encodeBase64String(dataSplit(data.getBytes(), MAX_ENCRYPT_BLOCK, cipher));
    }

    /**
     * 公钥解密
     *
     * @param encryptedData 已加密数据
     * @param publicKey     公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String encryptedData, String publicKey) {
        Key publicK = getPublicKey(publicKey);
        Cipher cipher = getCipher(Cipher.DECRYPT_MODE, publicK);
        return new String(dataSplit(Base64.decodeBase64(encryptedData), MAX_DECRYPT_BLOCK, cipher));
    }

    /**
     * 私钥解密
     *
     * @param encryptedData 已加密数据
     * @param privateKey    私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String encryptedData, String privateKey) {
        Key privateK = getPrivateKey(privateKey);
        Cipher cipher = getCipher(Cipher.DECRYPT_MODE, privateK);
        return new String(dataSplit(Base64.decodeBase64(encryptedData), MAX_DECRYPT_BLOCK, cipher));
    }
}
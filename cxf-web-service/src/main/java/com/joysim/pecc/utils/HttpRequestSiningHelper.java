package com.joysim.pecc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;


/**
 * 用于REST OPEN API的请求签名辅助类.
 *
 * @author Marco.hu(huzhiguo@cvte.cn)
 */
public class HttpRequestSiningHelper {
    private static final Logger logger = LoggerFactory.getLogger(HttpRequestSiningHelper.class);

    private static final String DEFAULT_METHOD = "HmacMD5";//默认算法

    public static String createSignatureBase(final String method, final String dateHeader, final String requestUri, final String body) {
        final StringBuffer builder = new StringBuffer();

        builder.append(method).append("\n");
        builder.append(dateHeader).append("\n");
        builder.append(requestUri).append("\n");
        if (body != null && !"".equals(body.trim())) try {
            builder.append(EncryptUtils.MD5Encode(body).trim());
        } catch (Exception e) {
            throw new RuntimeException("MD5 加密失败", e);
        }

        return builder.toString();
    }

    /**
     * 创建请求签名.
     *
     * @param method
     * @param dateHeader
     * @param requestUri
     * @param body
     * @param secretKey
     * @return
     */
    public static String createRequestSignature(final String method, final String dateHeader, final String requestUri,
                                                final String body, final String secretKey) {
        final String signatureBase = createSignatureBase(method, dateHeader, requestUri, body);
        logger.info(signatureBase);
        final byte[] result = createMacSignature(secretKey, signatureBase);
        return encodeBase64WithoutLinefeed(result);
    }

    public static byte[] createMacSignature(String secretKey, String source) {
        try {
            final SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), DEFAULT_METHOD);
            final Mac mac = Mac.getInstance(DEFAULT_METHOD);
            mac.init(keySpec);
            return mac.doFinal(source.getBytes("UTF-8"));
        } catch (Exception ex) {
            throw new RuntimeException("HmacMD5 加密失败", ex);
        }
    }


    /**
     * Base64加密.
     *
     * @param result
     * @return
     */
    public static String encodeBase64WithoutLinefeed(byte[] result) {
        return EncryptUtils.base64Encode(result).trim();
    }


    private static Calendar cal(int field, int amount) {
        final Calendar c = Calendar.getInstance();
        c.add(field, amount);
        return c;
    }

}

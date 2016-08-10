package com.joysim.pecc.utils;

public final class HmacAttributes {
	
	/**
	 * 系统帐号.
	 */
    public static final String X_AUTHENTICATED_USERNAME = "x-authenticated-username";
    
    /**
     * 用于请求签名的字符串.
     */
    public static final String X_HMAC_AUTH_TO_SIGNATURE = "x-hmac-auth-to-signature";
    
    /**
     * 用于本次请求的签名.
     */
    public static final String X_HMAC_AUTH_SIGNATURE = "x-hmac-auth-signature";
    
    /**
     * 用户请求签名的时间戳.
     */
    public static final String X_HMAC_AUTH_DATE = "x-hmac-auth-date";
    
    /**
     * 用于请求签名的签名方法.默认为 HmacSHA256.
     */
    public static final String X_HMAC_AUTH_METHOD = "x-hmac-signature-method";

}

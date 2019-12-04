package com.ispeng.my.shop.commons.utils;

public class RegexpUtils {

    /**
     * 验证手机号
     */
    public static final String PHONE = "^[1][3,4,5,7,8][0-9]{9}$";

    /**
     * 验证邮箱地址
     */
    public static final String EMAIL = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";


    /**
     * 验证手机号
     */
    public static boolean checkPhone(String phone){
        return phone.matches(PHONE);
    }
    /**
     * 验证邮箱
     */
    public static boolean checkEmail(String email){
        return email.matches(EMAIL);
    }
}

package com.ispeng.my.shop.commons.dto;

import java.io.Serializable;

/**
 * 自定义返回结果
 */
public class BaseResult  implements Serializable {
    public static final int STATUS_SUCCSESS = 200;
    public static final int STATUS_FAIL = 500;
    private int status;
    private String message;

    public static BaseResult success(){
        return BaseResult.creteResult(STATUS_SUCCSESS,"成功");
    }

    public static BaseResult success(String message){

        return BaseResult.creteResult(STATUS_SUCCSESS,message);
    }



    public static BaseResult fail(){
        return BaseResult.creteResult(STATUS_FAIL,"失败");
    }

    public static BaseResult fail(String message){
        return BaseResult.creteResult(STATUS_FAIL,message);
    }

    public static BaseResult fail(int status,String message){
        return BaseResult.creteResult(status,message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    private static BaseResult creteResult(int status,String message){
        BaseResult baseResult = new  BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        return baseResult;
    }
}

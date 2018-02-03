package com.musiccrawl.common.model;

/**
 * 响应体
 * Created by Administrator on 2017/10/23.
 */
public class ResponseModel<T> {
    //定义响应码
    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 500;

    public T body;
    public int code;

    public ResponseModel(int code ,T body) {
        this.body = body;
        this.code = code;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

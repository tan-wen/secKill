package com.went.toy.seckill.dto;

/**
 * Created by Administrator on 2017/4/23.
 */
//所有的ajax返回的类型，封装json结果
public class SeckillResult<T> {

    private Boolean isSuccess;

    private T data;

    private String error;

    public SeckillResult(Boolean isSuccess, T data) {
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public SeckillResult(Boolean isSuccess, String error) {
        this.isSuccess = isSuccess;
        this.error = error;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

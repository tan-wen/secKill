package com.went.toy.seckill.exception;

/**
 * 秒杀异常
 * Created by Administrator on 2017/4/23.
 */
public class SeckillServiceException extends RuntimeException {

    public SeckillServiceException(String message) {
        super(message);
    }

    public SeckillServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

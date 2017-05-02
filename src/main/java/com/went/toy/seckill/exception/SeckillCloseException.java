package com.went.toy.seckill.exception;

/**
 * Created by Administrator on 2017/4/23.
 */
public class SeckillCloseException extends SeckillServiceException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}

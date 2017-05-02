package com.went.toy.seckill.exception;

/**
 * Created by Administrator on 2017/4/23.
 */
public class RepeatKillException extends SeckillServiceException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.went.toy.seckill.enums;

/**
 * Created by Administrator on 2017/4/23.
 */
public enum SeckillStatEnum {

    SUCCESS (1, "秒杀成功"),
    END (0, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROE(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");


    private int state;

    private String stateInfo;

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStatEnum stateOf(int index){
        for (SeckillStatEnum statEnum: values()) {
            if (index == statEnum.getState()){
                return statEnum;
            }
        }
        return null;
    }
}

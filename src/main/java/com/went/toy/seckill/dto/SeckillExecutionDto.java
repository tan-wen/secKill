package com.went.toy.seckill.dto;

import com.went.toy.seckill.entity.SuccessKilled;
import com.went.toy.seckill.enums.SeckillStatEnum;

/**
 * 秒杀执行后结果
 * Created by Administrator on 2017/4/23.
 */
public class SeckillExecutionDto {

    //秒杀记录
    private Long seckillId;

    //秒杀状态
    private int state;

    //秒杀状态表示信息
    private String stateInfo;

    //秒杀成功记录
    private SuccessKilled successKilled;

    public SeckillExecutionDto(Long seckillId, SeckillStatEnum statEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecutionDto(Long seckillId, SeckillStatEnum statEnum) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecutionDto{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}

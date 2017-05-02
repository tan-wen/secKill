package com.went.toy.seckill.service;

import com.went.toy.seckill.dto.ExposerDto;
import com.went.toy.seckill.dto.SeckillExecutionDto;
import com.went.toy.seckill.entity.Seckill;
import com.went.toy.seckill.exception.RepeatKillException;
import com.went.toy.seckill.exception.SeckillCloseException;
import com.went.toy.seckill.exception.SeckillServiceException;

import java.util.List;

/**
 * 业务接口：站在"使用者"角度设计接口
 * Created by Administrator on 2017/4/23.
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     */
    Seckill getById(Long seckillId);

    /**
     * 秒杀开启时输出秒杀地址
     * 秒杀未开启时输出系统时间和秒杀开始时间
     * @param seckillId
     * @return
     */
    ExposerDto exportSeckillUrl(Long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecutionDto ExecuteSeckill(Long seckillId, Long userPhone, String md5)
        throws SeckillServiceException, RepeatKillException, SeckillCloseException;
}

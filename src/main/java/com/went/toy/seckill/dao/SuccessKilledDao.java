package com.went.toy.seckill.dao;

import com.went.toy.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/4/22.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复（数据库中该表使用联合主键）
     * @param seckillId
     * @param userphone
     * @return 插入行数
     */
    int save(@Param("seckillId") Long seckillId,
             @Param("userPhone") Long userPhone);

    /**
     * 根据秒杀商品ID查询秒杀成功的记录，携带秒杀商品的信息
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Long seckillId,
                                       @Param("userPhone") Long userPhone);
}

package com.went.toy.seckill.dao;

import com.went.toy.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */
public interface SeckillDao {

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 更新操作影响的行数
     */
    int reduceNumber (@Param("seckillId") Long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据ID查询秒杀商品
     * @param seckillId
     * @return
     */
    Seckill queryById (Long seckillId);

    /**
     * 查询所有秒杀商品，分页显示
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> quaryAll (@Param("offset")int offset,
                            @Param("limit")int limit);
}

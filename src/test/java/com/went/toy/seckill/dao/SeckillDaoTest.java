package com.went.toy.seckill.dao;

import com.went.toy.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * Created by Administrator on 2017/4/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void queryById() throws Exception {
        Long id = 1000l;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);

        /**
         * 1000元秒杀iPhone6
            Seckill
            {
                id=1000,
                name='1000元秒杀iPhone6',
                number=100,
                createTime=Sat Apr 22 00:37:09 CST 2017,
                startTime=Fri Apr 21 00:00:00 CST 2017,
                endTime=Sat Apr 22 00:00:00 CST 2017
            }
         */
    }

    @Test
    public void quaryAll() throws Exception {
        List<Seckill> seckillList = seckillDao.quaryAll(1,2);
        for (Seckill seckill: seckillList) {
            System.out.println(seckill);
        }

        /*
         *Seckill{
         *  id=1001,
         *  name='500元秒杀iPad',
         *  number=200,
         *  createTime=Sat Apr 22 00:37:09 CST 2017,
         *  startTime=Fri Apr 21 00:00:00 CST 2017,
         *  endTime=Sat Apr 22 00:00:00 CST 2017
         * }
         * Seckill{
         *  id=1002,
         *  name='300元秒杀小米6',
         *  number=300,
         *  createTime=Sat Apr 22 00:37:09 CST 2017,
         *  startTime=Fri Apr 21 00:00:00 CST 2017,
         *  endTime=Sat Apr 22 00:00:00 CST 2017
         *  }
         */
    }

    @Test
    public void reduceNumber() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = format.parse("2017-04-21 22:10:10");
        int updateCount = seckillDao.reduceNumber(1000l,now);
        System.out.println("updateCount : " + updateCount);
    }

}
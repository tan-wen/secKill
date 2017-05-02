package com.went.toy.seckill.dao;

import com.went.toy.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/4/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao killedDao;

    @Test
    public void save() throws Exception {
        int insertCout = killedDao.save(1000l,18625238715l);
        System.out.println("insertCout : " + insertCout);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        SuccessKilled killed = killedDao.queryByIdWithSeckill(1000l,18625238715l);
        System.out.println(killed);
        System.out.println(killed.getSeckill());
    }

}
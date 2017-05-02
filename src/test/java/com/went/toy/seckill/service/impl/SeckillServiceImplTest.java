package com.went.toy.seckill.service.impl;

import com.went.toy.seckill.dto.ExposerDto;
import com.went.toy.seckill.dto.SeckillExecutionDto;
import com.went.toy.seckill.entity.Seckill;
import com.went.toy.seckill.exception.RepeatKillException;
import com.went.toy.seckill.exception.SeckillCloseException;
import com.went.toy.seckill.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/4/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(SeckillServiceImplTest.class);

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckills = seckillService.getSeckillList();
        logger.info("list={}", seckills);
    }

    @Test
    public void getById() throws Exception {
        Seckill seckill = seckillService.getById(1000l);
        logger.info("seckill : {}", seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        ExposerDto exposerDto = seckillService.exportSeckillUrl(1001l);
        logger.info("exposerDto {}", exposerDto);

        //exposerDto ExposerDto{
        // isExposed=true,
        // md5='6fe92fe08140bae92acc6c537415cc91',
        // seckillId=1001,
        // now=null,
        // start=null,
        // end=null}
    }

    @Test
    public void executeSeckill() throws Exception {
        String md5 = "6fe92fe08140bae92acc6c537415cc91";
        try {
            SeckillExecutionDto seckillExecutionDto =
                    seckillService.ExecuteSeckill(1001l,18625238715l,md5);
            logger.info("seckillExecutionDto {}", seckillExecutionDto);
            //业务异常不向外抛
        }catch (RepeatKillException e){
            logger.error("repeat seckill");
        }catch (SeckillCloseException e){
            logger.error("seckill is closed");
        }
    }

}
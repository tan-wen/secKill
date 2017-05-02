package com.went.toy.seckill.service.impl;

import com.went.toy.seckill.dao.SeckillDao;
import com.went.toy.seckill.dao.SuccessKilledDao;
import com.went.toy.seckill.dto.ExposerDto;
import com.went.toy.seckill.dto.SeckillExecutionDto;
import com.went.toy.seckill.entity.Seckill;
import com.went.toy.seckill.entity.SuccessKilled;
import com.went.toy.seckill.enums.SeckillStatEnum;
import com.went.toy.seckill.exception.RepeatKillException;
import com.went.toy.seckill.exception.SeckillCloseException;
import com.went.toy.seckill.exception.SeckillServiceException;
import com.went.toy.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private static final String slat = "sfjadfji2@2i3uioajf^&(&&&^uur";

    private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    public List<Seckill> getSeckillList() {
        return seckillDao.quaryAll(0,4);
    }

    public Seckill getById(Long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public ExposerDto exportSeckillUrl(Long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (null == seckill){
            return new ExposerDto(Boolean.FALSE, seckillId);
        }
        Long startTime = seckill.getStartTime().getTime();
        Long endTIme = seckill.getEndTime().getTime();
        Long nowTime = new Date().getTime();

        //秒杀尚未开始或者秒杀已经结束
        if (nowTime < startTime || nowTime > endTIme){
            return new ExposerDto(Boolean.FALSE, seckillId, nowTime, startTime, endTIme);
        }
        String md5 = getMd5(seckillId);
        return new ExposerDto(Boolean.TRUE, md5, seckillId);
    }

    private String getMd5(Long seckillId) {
        String base = seckillId + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Transactional
    public SeckillExecutionDto ExecuteSeckill(Long seckillId, Long userPhone, String md5)
            throws SeckillServiceException, RepeatKillException, SeckillCloseException {
        if (null == md5 || !md5.equals(getMd5(seckillId))){
            throw new SeckillServiceException("seckill data rewirte");
        }
        //执行秒杀逻辑
        Date nowTime = new Date();
        try {
            int updateCount = seckillDao.reduceNumber(seckillId,nowTime);
            if (0 >= updateCount){
                throw new SeckillCloseException("seckill is closed");
            } else {
                //记录购买行为
                int insertCount = successKilledDao.save(seckillId, userPhone);
                if (0 >= insertCount){
                    //重复秒杀
                    throw new RepeatKillException("seckill repeated");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                    return new SeckillExecutionDto(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }

        }catch (SeckillCloseException e){
            throw e;
        }catch (RepeatKillException e){
            throw e;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            //所有编译器异常转化为运行期异常，运行期异常才会rollback
            throw new SeckillServiceException("seckill inner error : " + e.getMessage());
        }
    }
}

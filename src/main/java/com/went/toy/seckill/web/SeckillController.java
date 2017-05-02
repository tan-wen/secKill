package com.went.toy.seckill.web;

import com.went.toy.seckill.dto.ExposerDto;
import com.went.toy.seckill.dto.SeckillExecutionDto;
import com.went.toy.seckill.dto.SeckillResult;
import com.went.toy.seckill.entity.Seckill;
import com.went.toy.seckill.enums.SeckillStatEnum;
import com.went.toy.seckill.exception.RepeatKillException;
import com.went.toy.seckill.exception.SeckillCloseException;
import com.went.toy.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(SeckillController.class);

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> seckillList = seckillService.getSeckillList();
        model.addAttribute("seckillList", seckillList);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (null == seckillId) {
            return "direct:/seckill/list";  //重定向
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (null == seckill) {
            return "forward:/seckill/list"; //转发
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<ExposerDto> exposer(Long seckillId) {
        SeckillResult<ExposerDto> result;
        try {
            ExposerDto exposerDto = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<ExposerDto>(Boolean.TRUE, exposerDto);
        } catch (Exception e) {
            result = new SeckillResult<ExposerDto>(Boolean.FALSE, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/executor",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecutionDto> executor(@PathVariable("seckillId") Long seckillId,
                                                       @PathVariable("md5") String md5,
                                                       @CookieValue(value = "killerPhone", required = false)
                                                               Long userPhone) {
        if (null == userPhone) {
            logger.error("cookie has no user phone");
            return new SeckillResult<SeckillExecutionDto>(Boolean.FALSE, "未注册");
        }
        SeckillResult<SeckillExecutionDto> result;
        SeckillExecutionDto executionDto;
        try {
            executionDto = seckillService.ExecuteSeckill(seckillId, userPhone, md5);
            result = new SeckillResult<SeckillExecutionDto>(Boolean.TRUE, executionDto);
        } catch (RepeatKillException e) {
            executionDto = new SeckillExecutionDto(seckillId, SeckillStatEnum.REPEAT_KILL);
            result = new SeckillResult<SeckillExecutionDto>(Boolean.FALSE, executionDto);
        } catch (SeckillCloseException e) {
            executionDto = new SeckillExecutionDto(seckillId, SeckillStatEnum.END);
            result = new SeckillResult<SeckillExecutionDto>(Boolean.FALSE, executionDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            executionDto = new SeckillExecutionDto(seckillId, SeckillStatEnum.INNER_ERROE);
            result = new SeckillResult<SeckillExecutionDto>(Boolean.FALSE, executionDto);
        }
        return result;
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Date> time() {
        Date now = new Date();
        return new SeckillResult<Date>(Boolean.TRUE, now);
    }
}

package com.went.toy.seckill.dto;

/**
 * 暴露秒杀地址DTO
 * Created by Administrator on 2017/4/23.
 */
public class ExposerDto {

    //是否开启秒杀
    private Boolean isExposed;

    //加密
    private String md5;

    //秒杀商品ID
    private Long seckillId;

    //系统当前时间（毫秒）
    private Long now;

    //秒杀开始时间
    private Long start;

    //秒杀结束时间
    private Long end;

    public ExposerDto(Boolean isExposed, String md5, Long seckillId) {
        this.isExposed = isExposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public ExposerDto(Boolean isExposed, Long seckillId, Long now, Long start, Long end) {
        this.isExposed = isExposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public ExposerDto(Boolean isExposed, Long seckillId) {
        this.isExposed = isExposed;
        this.seckillId = seckillId;
    }

    public Boolean getExposed() {
        return isExposed;
    }

    public void setExposed(Boolean exposed) {
        isExposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getNow() {
        return now;
    }

    public void setNow(Long now) {
        this.now = now;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "ExposerDto{" +
                "isExposed=" + isExposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}

##数据库初始化脚本

##创建数据库

##CREATE DATABASE seckill;

##use seckill;

CREATE TABLE seckill (
  id BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  name VARCHAR(120) NOT NULL COMMENT '商品名称',
  number INT NOT NULL COMMENT '商品数量',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() COMMENT '创建时间',
  start_time TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
  end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (id),
  KEY idx_start_time (start_time),
  KEY idx_end_time (end_time),
  KEY idx_create_time (create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT = '秒杀库存库';

##初始化数据
INSERT INTO seckill (name, number, start_time, end_time)
VALUES
  ('1000元秒杀iPhone6',100,'2017-4-21 00:00:00','2017-4-22 00:00:00'),
  ('500元秒杀iPad',200,'2017-4-21 00:00:00','2017-4-22 00:00:00'),
  ('300元秒杀小米6',300,'2017-4-21 00:00:00','2017-4-22 00:00:00'),
  ('200元秒杀魅族',500,'2017-4-21 00:00:00','2017-4-22 00:00:00');

## 秒杀成功明细表
CREATE TABLE success_killed (
  seckill_id BIGINT NOT NULL COMMENT '秒杀商品ID',
  user_phone BIGINT NOT NULL COMMENT '用户手机ID',
  state TINYINT NOT NULL DEFAULT -1 COMMENT '状态',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (seckill_id,user_phone),
  KEY idx_create_time(create_time)
)ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '秒杀成功明细表';

SELECT * FROM seckill;

SELECT * FROM success_killed;
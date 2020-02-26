-- ----------------------------
-- Table structure for t_day_trend
-- ----------------------------
DROP TABLE IF EXISTS `t_day_trend`;
CREATE TABLE `t_day_trend` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stock_id` int(11) DEFAULT NULL COMMENT '股票id',
  `volume` bigint(20) DEFAULT NULL COMMENT '成交量',
  `open` double DEFAULT NULL COMMENT '开盘价',
  `high` double DEFAULT NULL COMMENT '最高',
  `close` double DEFAULT NULL COMMENT '收盘价',
  `low` double DEFAULT NULL COMMENT '最低',
  `chg` double DEFAULT NULL COMMENT '涨跌额',
  `percent` double DEFAULT NULL COMMENT '涨跌幅',
  `turnoverrate` double DEFAULT NULL COMMENT '换手率',
  `ma5` double DEFAULT NULL COMMENT '5日线',
  `ma10` double DEFAULT NULL COMMENT '10日线',
  `ma20` double DEFAULT NULL COMMENT '20日线',
  `ma30` double DEFAULT NULL COMMENT '30日线',
  `dif` double DEFAULT NULL COMMENT 'dif',
  `dea` double DEFAULT NULL COMMENT 'dea',
  `macd` double DEFAULT NULL COMMENT 'macd',
  `time` bigint(20) DEFAULT NULL COMMENT '时间节点',
  `is_hammer` varchar(1) DEFAULT NULL,
  `kdjk` double DEFAULT NULL,
  `kdjd` double DEFAULT NULL,
  `kdjj` double DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_st` (`stock_id`,`time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for t_hammer_grown
-- ----------------------------
DROP TABLE IF EXISTS `t_hammer_grown`;
CREATE TABLE `t_hammer_grown` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hammer_day_trend_id` int(11) DEFAULT NULL,
  `stock_id` int(11) DEFAULT NULL,
  `days_diff` int(11) DEFAULT NULL,
  `relative_grown_rate` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

-- ----------------------------
-- Table structure for t_stock
-- ----------------------------
DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE `t_stock` (
  `id` int(11) NOT NULL,
  `stock_no` varchar(255)  DEFAULT NULL,
  `stock_short_no` varchar(255)  DEFAULT NULL,
  `stock_name` varchar(255)  DEFAULT NULL,
  `board` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

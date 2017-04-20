/*
Navicat MySQL Data Transfer

Source Server         : remote
Source Server Version : 50635
Source Host           : 115.159.187.114:3306
Source Database       : yimai

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-04-20 19:04:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ym_category
-- ----------------------------
DROP TABLE IF EXISTS `ym_category`;
CREATE TABLE `ym_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类表的主键',
  `name` varchar(20) NOT NULL COMMENT '分类名称',
  `status` int(1) NOT NULL COMMENT '当前分类信息的状态是否可用 1为可用 0为不可用',
  `uid` varchar(32) DEFAULT NULL COMMENT '修改或者增加分类信息的管理员的id',
  `icon` varchar(20) NOT NULL COMMENT '图标的名称',
  `created` date DEFAULT NULL COMMENT '创建分类的时间',
  `updated` datetime DEFAULT NULL COMMENT '更新分类的时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=22692412034160 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ym_item
-- ----------------------------
DROP TABLE IF EXISTS `ym_item`;
CREATE TABLE `ym_item` (
  `id` varchar(32) NOT NULL COMMENT '商品的唯一标识',
  `title` varchar(50) NOT NULL COMMENT '商品的概述信息',
  `price` int(11) NOT NULL COMMENT '商品的价格',
  `uid` varchar(32) NOT NULL COMMENT '商品的出售者的id',
  `status` int(1) NOT NULL COMMENT '商品的状态 0:待售 1:已交易 2:已交易',
  `cateid` bigint(20) NOT NULL COMMENT '分类的唯一标识',
  `descid` varchar(32) DEFAULT NULL COMMENT '对商品的要点描述',
  `image` varchar(500) DEFAULT NULL COMMENT '商品的图片',
  `pass_status` int(1) NOT NULL COMMENT '该商品的信息通过审核状态 0:未通过 1.已通过',
  `editor` varchar(32) DEFAULT NULL COMMENT '编辑商品的管理员的id',
  `created` datetime DEFAULT NULL COMMENT '商品的创建时间',
  `updated` datetime DEFAULT NULL COMMENT '商品的更新时间',
  PRIMARY KEY (`id`),
  KEY `FK_ym_item` (`uid`),
  KEY `FK_ym_item_desc` (`descid`),
  KEY `FK_ym_item_category` (`cateid`),
  CONSTRAINT `FK_ym_item` FOREIGN KEY (`uid`) REFERENCES `ym_user` (`id`),
  CONSTRAINT `FK_ym_item_category` FOREIGN KEY (`cateid`) REFERENCES `ym_category` (`id`),
  CONSTRAINT `FK_ym_item_desc` FOREIGN KEY (`descid`) REFERENCES `ym_item_desc` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ym_item_desc
-- ----------------------------
DROP TABLE IF EXISTS `ym_item_desc`;
CREATE TABLE `ym_item_desc` (
  `id` varchar(32) NOT NULL COMMENT '商品详细描述的主键id',
  `content` text NOT NULL,
  `status` int(1) NOT NULL COMMENT '商品详细描述的状态,0.待审核 1.可用 2.已删除',
  `editor` varchar(32) DEFAULT NULL COMMENT '审核或者编辑者的管理员的id',
  `created` date DEFAULT NULL COMMENT '此商品描述创建的时间',
  `updated` datetime DEFAULT NULL COMMENT '此商品描述更新的时间',
  `item_id` varchar(32) NOT NULL COMMENT '关联的商品的id',
  PRIMARY KEY (`id`),
  KEY `FK_ym_desc_item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ym_order
-- ----------------------------
DROP TABLE IF EXISTS `ym_order`;
CREATE TABLE `ym_order` (
  `id` bigint(20) NOT NULL COMMENT '订单的唯一标识',
  `itemId` varchar(40) DEFAULT NULL COMMENT '商品的id',
  `title` varchar(70) NOT NULL COMMENT '商品的标题',
  `status` int(1) NOT NULL COMMENT '订单的状态 0:未付款 1:已付款,待发货 2:已发货 3:已完成',
  `sellerid` varchar(32) NOT NULL COMMENT '物品出售者的id',
  `buyerid` varchar(32) NOT NULL COMMENT '购买者的id',
  `price` int(10) NOT NULL COMMENT '购买的商品的价格',
  `image` varchar(100) DEFAULT NULL COMMENT '订单所对应的图片的信息',
  `expressid` int(15) DEFAULT NULL COMMENT '商品的物流信息id',
  `addressid` varchar(32) DEFAULT NULL,
  `editor` varchar(32) DEFAULT NULL COMMENT '编辑信息的管理员的id',
  `created` datetime NOT NULL COMMENT '订单的创建的时间',
  `expire` datetime NOT NULL COMMENT '订单过期时间',
  `finish` datetime DEFAULT NULL COMMENT '订单交易完成时间',
  PRIMARY KEY (`id`),
  KEY `forignkey` (`addressid`),
  CONSTRAINT `forignkey` FOREIGN KEY (`addressid`) REFERENCES `ym_user_addr` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ym_user
-- ----------------------------
DROP TABLE IF EXISTS `ym_user`;
CREATE TABLE `ym_user` (
  `id` varchar(32) NOT NULL COMMENT '用户的唯一标识',
  `username` varchar(20) NOT NULL COMMENT '用户登录的用户名',
  `passwd` varchar(50) NOT NULL COMMENT '用户登录的密码,使用MD5加密存储',
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户的昵称,填入此项,在页面展示时显示此昵称',
  `phone` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL COMMENT '用户的生日',
  `addressid` varchar(32) DEFAULT NULL,
  `email` varchar(30) NOT NULL COMMENT '用户的邮箱',
  `state` int(1) unsigned zerofill NOT NULL COMMENT '当前账户的状态 0为不可用,1为可用',
  `editor` varchar(32) DEFAULT NULL COMMENT '对用户进行信息管理的管理员的id',
  `created` datetime NOT NULL COMMENT '用户的注册时间',
  `updated` datetime NOT NULL COMMENT '用户更新信息时的时间',
  `admin` int(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否为管理员',
  `forbidden` tinyint(4) NOT NULL COMMENT '此用户的账户是否被禁用,0被禁用,1未被禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ym_user_addr
-- ----------------------------
DROP TABLE IF EXISTS `ym_user_addr`;
CREATE TABLE `ym_user_addr` (
  `id` varchar(32) NOT NULL COMMENT '用户地址的唯一标识',
  `uid` varchar(32) NOT NULL COMMENT '用户的id',
  `address` varchar(200) DEFAULT NULL COMMENT '用户地址的内容',
  `username` varchar(20) NOT NULL COMMENT '收货人',
  `phone` varchar(20) NOT NULL COMMENT '收货人的手机号码',
  `def_addr` tinyint(4) DEFAULT NULL COMMENT '是否为默认地址,0为非默认地址,1为默认地址',
  `created` date NOT NULL COMMENT '用户地址的创建时间',
  `updated` datetime NOT NULL COMMENT '时间地址的更新的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ym_wallet
-- ----------------------------
DROP TABLE IF EXISTS `ym_wallet`;
CREATE TABLE `ym_wallet` (
  `id` varchar(32) NOT NULL COMMENT '用户钱包的唯一标识',
  `status` int(1) NOT NULL COMMENT '用户钱包的状态 0.未开通 1.已开通',
  `remain` int(15) NOT NULL COMMENT '用户钱包的余额',
  `userid` varchar(32) NOT NULL COMMENT '标识用户的信息',
  `created` date DEFAULT NULL COMMENT '创建用户钱包的时间',
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_wallet` (`userid`),
  CONSTRAINT `fk_user_wallet` FOREIGN KEY (`userid`) REFERENCES `ym_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ym_wallet_action
-- ----------------------------
DROP TABLE IF EXISTS `ym_wallet_action`;
CREATE TABLE `ym_wallet_action` (
  `id` bigint(20) NOT NULL COMMENT '用户消费消息记录的唯一标识',
  `subject` varchar(500) NOT NULL COMMENT '用户消费的主题',
  `title` varchar(50) NOT NULL COMMENT '商品的标题',
  `status` int(1) NOT NULL COMMENT '交易的状态  1.交易成功 2.交易失败',
  `fee` int(15) NOT NULL COMMENT '消费的费用金额',
  `state` int(1) NOT NULL COMMENT '消费记录目的  1.收入  2.支出',
  `walletid` varchar(32) NOT NULL COMMENT '用户钱包id的唯一标识',
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_wallet_action` (`walletid`),
  CONSTRAINT `fk_wallet_action` FOREIGN KEY (`walletid`) REFERENCES `ym_wallet` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

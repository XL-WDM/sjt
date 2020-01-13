/*
Navicat MySQL Data Transfer

Source Server         : sjt
Source Server Version : 50645
Source Host           : 192.168.8.9:3306
Source Database       : sjt

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2020-01-13 14:47:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `contacts` varchar(64) NOT NULL COMMENT '联系人',
  `phone` varchar(11) NOT NULL COMMENT '联系电话',
  `province` varchar(64) NOT NULL COMMENT '省',
  `city` varchar(64) NOT NULL COMMENT '市',
  `county` varchar(64) NOT NULL COMMENT '县',
  `address` varchar(512) NOT NULL COMMENT '详细地址',
  `door_number` varchar(128) NOT NULL COMMENT '门牌号',
  `tag` varchar(1) NOT NULL COMMENT '地址标签(1-家, 2-公司, 3-学校)',
  `contacts_call` varchar(1) NOT NULL COMMENT '称呼(1-先生, 2-女士)',
  `is_default` varchar(1) NOT NULL COMMENT '是否默认地址(0-不是, 1-是)',
  `status` varchar(1) NOT NULL COMMENT '状态(0-无效, 1-有效)',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_address_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_address_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='收货地址表';

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES ('1', '2', '胡一龙', '15575311111', '湖南省', '益阳市', '安化县', '湖南省益阳市安化县', '平口镇南区化工厂', '1', '1', '0', '1', '2019-09-17 14:28:14', '2019-09-17 14:28:55');
INSERT INTO `t_address` VALUES ('2', '2', '胡一龙', '13118598289', '湖南省', '益阳市', '安化县', '湖南省益阳市安化县', '平口镇南区化工厂', '1', '1', '1', '0', '2019-09-17 14:29:06', '2019-09-17 14:29:06');
INSERT INTO `t_address` VALUES ('3', '3', '李大帅', '15073820717', '北京', '北京市', '东城区', '北京北京市东城区', '西单102栋12号', '1', '1', '1', '1', '2019-09-17 14:31:40', '2019-10-17 10:45:59');
INSERT INTO `t_address` VALUES ('4', '4', '鲁班', '15073820717', '上海', '上海市', '松江区', '上海上海市松江区', '城置路300弄15号101室', '1', '1', '1', '1', '2019-09-17 14:49:12', '2020-01-13 14:38:07');

-- ----------------------------
-- Table structure for t_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_banner`;
CREATE TABLE `t_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `banner_name` varchar(36) NOT NULL COMMENT 'banner名称',
  `banner_type` varchar(1) NOT NULL DEFAULT '1' COMMENT 'banner类型(1-首页top轮播图, 2-GIF小视频, 3-山田日记banner, 4-首页center轮播图)',
  `img_url` varchar(512) NOT NULL COMMENT '图片url地址',
  `img_version` bigint(20) NOT NULL COMMENT '图片版本(?_v=xxxxxxxx)',
  `url` varchar(512) DEFAULT NULL COMMENT 'banner跳转地址',
  `url_type` varchar(1) NOT NULL DEFAULT '1' COMMENT '地址类型(1-内部地址, 2-外部地址)',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态(0-无效 1-有效)',
  `sort_num` int(11) NOT NULL DEFAULT '0' COMMENT '排序编号',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='banner配置表';

-- ----------------------------
-- Records of t_banner
-- ----------------------------
INSERT INTO `t_banner` VALUES ('1', '轮播图1', '1', 'https://www.lcpmkt.com/images/banner/banner_top_01.png', '1563160127343', 'https://www.baidu.com/', '2', '1', '1', '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO `t_banner` VALUES ('2', '轮播图2', '1', 'https://www.lcpmkt.com/images/banner/banner_top_02.png', '1563160127343', 'https://www.baidu.com/', '2', '1', '2', '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO `t_banner` VALUES ('3', '轮播图3', '1', 'https://www.lcpmkt.com/images/banner/banner_top_03.png', '1563160127343', 'https://www.baidu.com/', '2', '1', '3', '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO `t_banner` VALUES ('4', 'GIF小视频', '2', 'https://www.lcpmkt.com/images/banner/gif_banner.png', '1563160127343', 'https://www.baidu.com/', '2', '1', '0', '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO `t_banner` VALUES ('5', '山田日记', '3', 'https://www.lcpmkt.com/images/banner/note_banner.png', '1563160127345', 'https://www.baidu.com/', '2', '1', '0', '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO `t_banner` VALUES ('6', '中部轮播图1', '4', 'https://www.lcpmkt.com/images/banner/banner_center_01.png', '1563160127343', 'https://www.baidu.com/', '2', '1', '1', '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO `t_banner` VALUES ('7', '中部轮播图2', '4', 'https://www.lcpmkt.com/images/banner/banner_center_02.png', '1563160127343', 'https://www.baidu.com/', '2', '1', '2', '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO `t_banner` VALUES ('8', '中部轮播图3', '4', 'https://www.lcpmkt.com/images/banner/banner_center_03.png', '1563160127343', 'https://www.baidu.com/', '2', '1', '3', '2019-07-15 11:11:12', '2019-07-15 11:11:12');

-- ----------------------------
-- Table structure for t_notes
-- ----------------------------
DROP TABLE IF EXISTS `t_notes`;
CREATE TABLE `t_notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `descript` varchar(512) NOT NULL COMMENT '描述',
  `big_img` varchar(128) NOT NULL COMMENT '大图',
  `small_img` varchar(128) NOT NULL COMMENT '小图',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态(0-可用, 1-不可用)',
  `is_craftsman` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否为匠人精神日记(0-否, 1-是)',
  `note_context` text NOT NULL COMMENT '内容',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='山田日记';

-- ----------------------------
-- Records of t_notes
-- ----------------------------
INSERT INTO `t_notes` VALUES ('1', '大大的海鲜干，炒菜、煲汤放几只，好鲜！', '“山海农人” 大虾干，用新鲜打捞的大海虾制作，无防腐剂添加', 'https://www.lcpmkt.com/images/notes/note_craftsman_big_01.png', 'https://www.lcpmkt.com/images/notes/note_small_01.png', '1', '0', '无内容', '2019-07-22 15:47:56', '2019-07-22 15:47:56');
INSERT INTO `t_notes` VALUES ('2', '有大颗草莓干的手工牛轧糖，过年的糖就它了', '台湾“芒里偷闲” 手工牛轧糖，三种口味可选，甜而不腻，有嚼劲', 'https://www.lcpmkt.com/images/notes/note_craftsman_big_01.png', 'https://www.lcpmkt.com/images/notes/note_small_01.png', '1', '0', '无内容', '2019-07-22 15:47:56', '2019-07-22 15:47:56');
INSERT INTO `t_notes` VALUES ('3', '宝宝新春小棉袄，年味十足！', '“嗯哈” 猪年限定款儿童新春棉衣，传统服饰样式，精致提花棉缎，厚实保暖', 'https://www.lcpmkt.com/images/notes/note_craftsman_big_01.png', 'https://www.lcpmkt.com/images/notes/note_small_01.png', '1', '0', '无内容', '2019-07-22 15:47:56', '2019-07-22 15:47:56');
INSERT INTO `t_notes` VALUES ('4', '山田匠人', '生活原本来源与自然，手作然我更加贴近自然——上尖匠人 Hans', 'https://www.lcpmkt.com/images/notes/note_craftsman_big_01.png', 'https://www.lcpmkt.com/images/notes/note_small_01.png', '1', '1', '无内容', '2019-07-22 15:47:56', '2019-07-22 15:47:56');
INSERT INTO `t_notes` VALUES ('5', '山田匠人', '生活原本来源与自然，手作然我更加贴近自然——上尖匠人 Hans', 'https://www.lcpmkt.com/images/notes/note_craftsman_big_01.png', 'https://www.lcpmkt.com/images/notes/note_small_01.png', '1', '1', '无内容', '2019-07-22 15:47:56', '2019-07-22 15:47:56');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_no` varchar(36) NOT NULL COMMENT '订单号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `address_id` int(11) DEFAULT NULL COMMENT '收货地址id',
  `total_amount` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '商品总金额(单位：分)',
  `org_amount` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '订单总金额(单位：分)',
  `discount_amount` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '优惠金额(单位：分)',
  `payment` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '支付金额(单位：分)',
  `post_fee` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '邮费(单位：分)',
  `status` varchar(1) NOT NULL COMMENT '订单状态(1-待支付, 2-待发货, 3-待收货, 4-已完成, 5-已取消)',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单更新时间',
  `payment_date` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `consign_date` timestamp NULL DEFAULT NULL COMMENT '发货时间',
  `end_date` timestamp NULL DEFAULT NULL COMMENT '订单完成时间',
  `close_date` timestamp NULL DEFAULT NULL COMMENT '订单关闭时间',
  `shipping_code` varchar(36) DEFAULT NULL COMMENT '物流单号',
  `shipping_name` varchar(36) DEFAULT NULL COMMENT '物流名称',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  `buyer_message` varchar(128) DEFAULT NULL COMMENT '买家留言',
  `buyer_rate` varchar(1) NOT NULL DEFAULT '0' COMMENT '买家是否评论(0-否, 1-是)',
  `contact_phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `contact_name` varchar(64) DEFAULT NULL COMMENT '联系人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_order_order_no_uindex` (`order_no`),
  KEY `t_order_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_order_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='订单信息表';

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '6573032867021074432', '2', null, '1000', '1000', '0', '1000', '0', '5', '2019-09-17 14:30:35', '2019-09-18 14:31:28', null, null, null, '2019-09-18 14:31:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('2', '6573033027914575872', '3', null, '900', '900', '0', '900', '0', '5', '2019-09-17 14:31:14', '2019-09-18 14:31:28', null, null, null, '2019-09-18 14:31:28', null, null, '北京北京市东城区西单102栋12号', null, '0', '18638321650', '李大帅');
INSERT INTO `t_order` VALUES ('3', '6573037601496903680', '4', null, '1000', '1000', '0', '1000', '0', '5', '2019-09-17 14:49:24', '2019-09-18 14:50:28', null, null, null, '2019-09-18 14:50:28', null, null, '上海上海市松江区城置路300弄15号101室', null, '0', '18046235230', '鲁班');
INSERT INTO `t_order` VALUES ('4', '6573053858434658304', '4', null, '1000', '1000', '0', '1000', '0', '5', '2019-09-17 15:54:19', '2019-09-18 15:54:28', null, null, null, '2019-09-18 15:54:28', null, null, '上海上海市松江区城置路300弄15号101室', null, '0', '18046235230', '鲁班');
INSERT INTO `t_order` VALUES ('5', '6581029524010577920', '3', null, '9900', '9900', '0', '9900', '0', '5', '2019-10-09 16:06:49', '2019-10-10 16:07:28', null, null, null, '2019-10-10 16:07:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('6', '6581029746501627904', '3', null, '1000', '1000', '0', '1000', '0', '5', '2019-10-09 16:07:42', '2019-10-10 16:08:28', null, null, null, '2019-10-10 16:08:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('7', '6581306474956468224', '3', null, '11800', '11800', '0', '11800', '0', '5', '2019-10-10 10:27:20', '2019-10-11 10:27:28', null, null, null, '2019-10-11 10:27:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('8', '6581307656357687296', '3', null, '1000', '1000', '0', '1000', '0', '5', '2019-10-10 10:32:01', '2019-10-11 10:32:28', null, null, null, '2019-10-11 10:32:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('9', '6581308646867742720', '3', null, '64900', '64900', '0', '64900', '0', '5', '2019-10-10 10:35:57', '2019-10-11 10:36:28', null, null, null, '2019-10-11 10:36:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('10', '6581322229785702400', '3', null, '900', '900', '0', '900', '0', '5', '2019-10-10 11:29:56', '2019-10-11 11:30:28', null, null, null, '2019-10-11 11:30:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('11', '6581326123517489152', '7', null, '9900', '9900', '0', '9900', '0', '5', '2019-10-10 11:45:24', '2019-10-11 11:46:28', null, null, null, '2019-10-11 11:46:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('12', '6581414562589978624', '3', null, '900', '900', '0', '900', '0', '5', '2019-10-10 17:36:50', '2019-10-11 17:37:28', null, null, null, '2019-10-11 17:37:28', null, null, '北京北京市东城区西单102栋12号', null, '0', '18638321650', '李大帅');
INSERT INTO `t_order` VALUES ('13', '6581443055537238016', '3', null, '900', '900', '0', '900', '0', '5', '2019-10-10 19:30:03', '2019-10-11 19:30:28', null, null, null, '2019-10-11 19:30:28', null, null, '北京北京市东城区西单102栋12号', null, '0', '18638321650', '李大帅');
INSERT INTO `t_order` VALUES ('14', '6581672372141699072', '4', null, '900', '900', '0', '900', '0', '5', '2019-10-11 10:41:16', '2019-10-12 10:41:28', null, null, null, '2019-10-12 10:41:28', null, null, '上海上海市松江区城置路300弄15号101室', null, '0', '18046235230', '鲁班');
INSERT INTO `t_order` VALUES ('15', '6581672891757244416', '4', null, '12700', '12700', '0', '12700', '0', '5', '2019-10-11 10:43:20', '2019-10-12 10:43:28', null, null, null, '2019-10-12 10:43:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('16', '6581676775389147136', '10', null, '3900', '3900', '0', '3900', '0', '5', '2019-10-11 10:58:46', '2019-10-12 10:59:28', null, null, null, '2019-10-12 10:59:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('17', '6581676809358815232', '10', null, '3900', '3900', '0', '3900', '0', '5', '2019-10-11 10:58:54', '2019-10-12 10:59:28', null, null, null, '2019-10-12 10:59:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('18', '6581677165664940032', '10', null, '1000', '1000', '0', '1000', '0', '5', '2019-10-11 11:00:19', '2019-10-12 11:00:28', null, null, null, '2019-10-12 11:00:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('19', '6581734224863309824', '3', null, '9900', '9900', '0', '9900', '0', '5', '2019-10-11 14:47:03', '2019-10-12 14:47:28', null, null, null, '2019-10-12 14:47:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('20', '6581741643119079424', '3', null, '9900', '9900', '0', '9900', '0', '5', '2019-10-11 15:16:32', '2019-10-12 15:17:28', null, null, null, '2019-10-12 15:17:28', null, null, '北京北京市东城区西单102栋12号', null, '0', '18638321650', '李大帅');
INSERT INTO `t_order` VALUES ('21', '6581914200187219968', '10', null, '7800', '7800', '0', '7800', '0', '5', '2019-10-12 02:42:13', '2019-10-13 02:42:28', null, null, null, '2019-10-13 02:42:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('22', '6582032288081653760', '3', null, '9900', '9900', '0', '9900', '0', '5', '2019-10-12 10:31:27', '2019-10-13 10:32:28', null, null, null, '2019-10-13 10:32:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('23', '6582032327453585408', '3', null, '9900', '9900', '0', '9900', '0', '5', '2019-10-12 10:31:36', '2019-10-13 10:32:28', null, null, null, '2019-10-13 10:32:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('24', '6582334531267866624', '22', null, '3900', '3900', '0', '3900', '0', '5', '2019-10-13 06:32:27', '2019-10-14 06:33:28', null, null, null, '2019-10-14 06:33:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('25', '6582337788342054912', '23', null, '3900', '3900', '0', '3900', '0', '5', '2019-10-13 06:45:24', '2019-10-14 06:46:28', null, null, null, '2019-10-14 06:46:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('26', '6582920617924898816', '4', null, '900', '900', '0', '900', '0', '5', '2019-10-14 21:21:21', '2019-10-15 21:22:28', null, null, null, '2019-10-15 21:22:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('27', '6583842292354920448', '4', null, '1', '1', '0', '1', '0', '4', '2019-10-17 10:23:46', '2019-10-17 11:15:15', '2019-10-17 10:27:47', '2019-10-17 10:26:03', '2019-10-17 11:15:15', null, '235540787543', '顺丰快递', '上海上海市松江区城置路300弄15号101室', null, '0', 'cc', '鲁班');
INSERT INTO `t_order` VALUES ('28', '6583845278703562752', '3', null, '1', '1', '0', '1', '0', '4', '2019-10-17 10:35:38', '2019-10-24 10:47:28', '2019-10-17 10:49:43', '2019-10-17 10:46:50', '2019-10-24 10:47:28', null, '235540787543', '顺丰快递', '北京北京市东城区西单102栋12号', null, '0', '15073820717', '李大帅');
INSERT INTO `t_order` VALUES ('29', '6583846784521613312', '4', null, '1', '1', '0', '1', '0', '5', '2019-10-17 10:41:37', '2019-10-18 10:42:28', null, null, null, '2019-10-18 10:42:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('30', '6584714639253319680', '3', null, '9900', '9900', '0', '9900', '0', '5', '2019-10-19 20:10:10', '2019-10-20 20:10:28', null, null, null, '2019-10-20 20:10:28', null, null, '北京北京市东城区西单102栋12号', null, '0', '15073820717', '李大帅');
INSERT INTO `t_order` VALUES ('31', '6585257806310551552', '33', null, '3900', '3900', '0', '3900', '0', '5', '2019-10-21 08:08:31', '2019-10-22 08:09:28', null, null, null, '2019-10-22 08:09:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('32', '6587268249459765248', '3', null, '900', '900', '0', '900', '0', '5', '2019-10-26 21:17:19', '2019-10-27 21:17:28', null, null, null, '2019-10-27 21:17:28', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('33', '6595030436777439232', '3', null, '7900', '7900', '0', '7900', '0', '5', '2019-11-17 07:21:27', '2019-11-18 07:21:17', null, null, null, '2019-11-18 07:21:17', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('34', '6595030459699310592', '3', null, '9900', '9900', '0', '9900', '0', '5', '2019-11-17 07:21:32', '2019-11-18 07:21:17', null, null, null, '2019-11-18 07:21:17', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('35', '6595030537176494080', '3', null, '19800', '19800', '0', '19800', '0', '5', '2019-11-17 07:21:51', '2019-11-18 07:22:17', null, null, null, '2019-11-18 07:22:17', null, null, '北京北京市东城区西单102栋12号', null, '0', '15073820717', '李大帅');
INSERT INTO `t_order` VALUES ('36', '6596275922167541760', '3', null, '9900', '9900', '0', '9900', '0', '5', '2019-11-20 17:50:57', '2019-11-21 17:51:17', null, null, null, '2019-11-21 17:51:17', null, null, null, null, '0', null, null);
INSERT INTO `t_order` VALUES ('37', '6615753631159431168', '4', null, '1', '1', '0', '1', '0', '1', '2020-01-13 14:37:29', '2020-01-13 14:37:29', null, null, null, null, null, null, '上海上海市松江区城置路300弄15号101室', null, '0', '15073820717', '鲁班');

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `product_spec_id` int(11) NOT NULL COMMENT '商品规格id',
  `num` int(11) NOT NULL COMMENT '商品购买数量',
  `total_fee` decimal(10,0) NOT NULL COMMENT '商品总金额',
  `discount_amount` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '优惠金额(单位：分)',
  `price` decimal(10,0) NOT NULL COMMENT '商品单价(单位：分)',
  `product_img` varchar(512) DEFAULT NULL COMMENT '商品图片',
  `product_name` varchar(36) NOT NULL COMMENT '商品名称',
  `product_descript` varchar(512) DEFAULT NULL COMMENT '商品描述',
  PRIMARY KEY (`id`),
  KEY `t_order_item_t_order_id_fk` (`order_id`),
  KEY `t_order_item_t_product_info_id_fk` (`product_spec_id`),
  CONSTRAINT `t_order_item_t_order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`),
  CONSTRAINT `t_order_item_t_product_spec_id_fk` FOREIGN KEY (`product_spec_id`) REFERENCES `t_product_spec` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='订单详情表';

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
INSERT INTO `t_order_item` VALUES ('1', '1', '7', '1', '1000', '0', '1000', 'https://www.lcpmkt.com/images/product/product_07_01.jpg', '咖啡巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('2', '2', '10', '1', '900', '0', '900', 'https://www.lcpmkt.com/images/product/product_07_04.jpg', '原味巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('3', '3', '7', '1', '1000', '0', '1000', 'https://www.lcpmkt.com/images/product/product_07_01.jpg', '咖啡巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('4', '4', '7', '1', '1000', '0', '1000', 'https://www.lcpmkt.com/images/product/product_07_01.jpg', '咖啡巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('5', '5', '6', '1', '9900', '0', '9900', 'https://www.lcpmkt.com/images/product/product_06_00.jpg', '栗子巧克力', '新品上市栗子巧克力致敬经典顺丰包邮');
INSERT INTO `t_order_item` VALUES ('6', '6', '7', '1', '1000', '0', '1000', 'https://www.lcpmkt.com/images/product/product_07_01.jpg', '咖啡巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('7', '7', '1', '1', '7900', '0', '7900', 'https://www.lcpmkt.com/images/product/product_01_00.png', '抗霾舒畅柠果膏', '纯手工制作冰糖柠檬膏抵抗雾霾舒畅呼吸');
INSERT INTO `t_order_item` VALUES ('8', '7', '4', '1', '3900', '0', '3900', 'https://www.lcpmkt.com/images/product/product_04_00.png', '鲜柠古力', '新品上市鲜柠古力柠檬巧克力片酸甜可口（2盒顺丰包邮）');
INSERT INTO `t_order_item` VALUES ('9', '8', '7', '1', '1000', '0', '1000', 'https://www.lcpmkt.com/images/product/product_07_01.jpg', '咖啡巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('10', '9', '22', '11', '64900', '0', '5900', 'https://www.lcpmkt.com/images/product/product_07_08.jpg', '任性随机6颗', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('11', '10', '13', '1', '900', '0', '900', 'https://www.lcpmkt.com/images/product/product_07_07.jpg', '榛子巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('12', '11', '2', '1', '9900', '0', '9900', 'https://www.lcpmkt.com/images/product/product_02_00.png', '解压双降柠果膏', '生津润喉降燥降火');
INSERT INTO `t_order_item` VALUES ('13', '12', '10', '1', '900', '0', '900', 'https://www.lcpmkt.com/images/product/product_07_04.jpg', '原味巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('14', '13', '10', '1', '900', '0', '900', 'https://www.lcpmkt.com/images/product/product_07_04.jpg', '原味巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('15', '14', '10', '1', '900', '0', '900', 'https://www.lcpmkt.com/images/product/product_07_04.jpg', '原味巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('16', '15', '2', '1', '9900', '0', '9900', 'https://www.lcpmkt.com/images/product/product_02_00.png', '解压双降柠果膏', '生津润喉降燥降火');
INSERT INTO `t_order_item` VALUES ('17', '15', '7', '1', '1000', '0', '1000', 'https://www.lcpmkt.com/images/product/product_07_01.jpg', '咖啡巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('18', '15', '10', '1', '900', '0', '900', 'https://www.lcpmkt.com/images/product/product_07_04.jpg', '原味巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('19', '15', '13', '1', '900', '0', '900', 'https://www.lcpmkt.com/images/product/product_07_07.jpg', '榛子巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('20', '16', '4', '1', '3900', '0', '3900', 'https://www.lcpmkt.com/images/product/product_04_00.png', '鲜柠古力', '新品上市鲜柠古力柠檬巧克力片酸甜可口（2盒顺丰包邮）');
INSERT INTO `t_order_item` VALUES ('21', '17', '4', '1', '3900', '0', '3900', 'https://www.lcpmkt.com/images/product/product_04_00.png', '鲜柠古力', '新品上市鲜柠古力柠檬巧克力片酸甜可口（2盒顺丰包邮）');
INSERT INTO `t_order_item` VALUES ('22', '18', '11', '1', '1000', '0', '1000', 'https://www.lcpmkt.com/images/product/product_07_05.jpg', '黑苦巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('23', '19', '2', '1', '9900', '0', '9900', 'https://www.lcpmkt.com/images/product/product_02_00.png', '解压双降柠果膏', '生津润喉降燥降火');
INSERT INTO `t_order_item` VALUES ('24', '20', '2', '1', '9900', '0', '9900', 'https://www.lcpmkt.com/images/product/product_02_00.png', '解压双降柠果膏', '生津润喉降燥降火');
INSERT INTO `t_order_item` VALUES ('25', '21', '4', '2', '7800', '0', '3900', 'https://www.lcpmkt.com/images/product/product_04_00.png', '鲜柠古力', '新品上市鲜柠古力柠檬巧克力片酸甜可口（2盒顺丰包邮）');
INSERT INTO `t_order_item` VALUES ('26', '22', '2', '1', '9900', '0', '9900', 'https://www.lcpmkt.com/images/product/product_02_00.png', '解压双降柠果膏', '生津润喉降燥降火');
INSERT INTO `t_order_item` VALUES ('27', '23', '2', '1', '9900', '0', '9900', 'https://www.lcpmkt.com/images/product/product_02_00.png', '解压双降柠果膏', '生津润喉降燥降火');
INSERT INTO `t_order_item` VALUES ('28', '24', '4', '1', '3900', '0', '3900', 'https://www.lcpmkt.com/images/product/product_04_00.png', '鲜柠古力', '新品上市鲜柠古力柠檬巧克力片酸甜可口（2盒顺丰包邮）');
INSERT INTO `t_order_item` VALUES ('29', '25', '4', '1', '3900', '0', '3900', 'https://www.lcpmkt.com/images/product/product_04_00.png', '鲜柠古力', '新品上市鲜柠古力柠檬巧克力片酸甜可口（2盒顺丰包邮）');
INSERT INTO `t_order_item` VALUES ('30', '26', '13', '1', '900', '0', '900', 'https://www.lcpmkt.com/images/product/product_07_07.jpg', '榛子巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('31', '27', '23', '1', '1', '0', '1', 'https://www.lcpmkt.com/images/product/product_00_00.jpg', '补差价专拍', '补差价专拍');
INSERT INTO `t_order_item` VALUES ('32', '28', '23', '1', '1', '0', '1', 'https://www.lcpmkt.com/images/product/product_00_00.jpg', '补差价专拍', '补差价专拍');
INSERT INTO `t_order_item` VALUES ('33', '29', '23', '1', '1', '0', '1', 'https://www.lcpmkt.com/images/product/product_00_00.jpg', '补差价专拍', '补差价专拍');
INSERT INTO `t_order_item` VALUES ('34', '30', '2', '1', '9900', '0', '9900', 'https://www.lcpmkt.com/images/product/product_02_00.png', '解压双降柠果膏', '生津润喉降燥降火');
INSERT INTO `t_order_item` VALUES ('35', '31', '4', '1', '3900', '0', '3900', 'https://www.lcpmkt.com/images/product/product_04_00.png', '鲜柠古力', '新品上市鲜柠古力柠檬巧克力片酸甜可口（2盒顺丰包邮）');
INSERT INTO `t_order_item` VALUES ('36', '32', '10', '1', '900', '0', '900', 'https://www.lcpmkt.com/images/product/product_07_04.jpg', '原味巧克力', '七种口味随意搭配6颗起发');
INSERT INTO `t_order_item` VALUES ('37', '33', '1', '1', '7900', '0', '7900', 'https://www.lcpmkt.com/images/product/product_01_00.png', '抗霾舒畅柠果膏', '纯手工制作冰糖柠檬膏抵抗雾霾舒畅呼吸');
INSERT INTO `t_order_item` VALUES ('38', '34', '2', '1', '9900', '0', '9900', 'https://www.lcpmkt.com/images/product/product_02_00.png', '解压双降柠果膏', '生津润喉降燥降火');
INSERT INTO `t_order_item` VALUES ('39', '35', '2', '2', '19800', '0', '9900', 'https://www.lcpmkt.com/images/product/product_02_00.png', '解压双降柠果膏', '生津润喉降燥降火');
INSERT INTO `t_order_item` VALUES ('40', '36', '2', '1', '9900', '0', '9900', 'https://www.lcpmkt.com/images/product/product_02_00.png', '解压双降柠果膏', '生津润喉降燥降火');
INSERT INTO `t_order_item` VALUES ('41', '37', '23', '1', '1', '0', '1', 'https://www.lcpmkt.com/images/product/product_00_00.jpg', '补差价专拍', '补差价专拍');

-- ----------------------------
-- Table structure for t_payment_flow
-- ----------------------------
DROP TABLE IF EXISTS `t_payment_flow`;
CREATE TABLE `t_payment_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `pay_no` varchar(64) NOT NULL COMMENT '支付流水号',
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `pay_order_no` varchar(64) DEFAULT NULL COMMENT '支付订单号(例如：微信支付订单号)',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `amount` decimal(10,0) NOT NULL COMMENT '支付金额(单位：分)',
  `integral` int(11) NOT NULL DEFAULT '0' COMMENT '使用的积分',
  `pay_type` varchar(1) NOT NULL COMMENT '支付类型(1-微信, 2-支付宝, 3-银联)',
  `status` varchar(1) NOT NULL COMMENT '支付状态(0-取消, 1-未支付, 2-已支付, 3-支付异常)',
  `note` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `pay_complete_date` timestamp NULL DEFAULT NULL COMMENT '支付完成时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_payment_flow_pay_no_uindex` (`pay_no`),
  UNIQUE KEY `t_payment_flow_pay_order_no_uindex` (`pay_order_no`),
  KEY `t_payment_flow_t_order_order_no_fk` (`order_no`),
  KEY `t_payment_flow_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_payment_flow_t_order_order_no_fk` FOREIGN KEY (`order_no`) REFERENCES `t_order` (`order_no`),
  CONSTRAINT `t_payment_flow_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='支付流水表';

-- ----------------------------
-- Records of t_payment_flow
-- ----------------------------
INSERT INTO `t_payment_flow` VALUES ('1', '6573053745280724992', '6573037601496903680', null, '4', '1000', '0', '1', '2', null, '2019-09-17 15:53:52', '2019-09-17 15:53:52', null);
INSERT INTO `t_payment_flow` VALUES ('2', '6573054191147823104', '6573053858434658304', null, '4', '1000', '0', '1', '2', null, '2019-09-17 15:55:38', '2019-09-17 15:55:38', null);
INSERT INTO `t_payment_flow` VALUES ('3', '6581414580315107328', '6581414562589978624', null, '3', '900', '0', '1', '2', null, '2019-10-10 17:36:54', '2019-10-10 17:36:54', null);
INSERT INTO `t_payment_flow` VALUES ('4', '6581443088684822528', '6581443055537238016', null, '3', '900', '0', '1', '2', null, '2019-10-10 19:30:11', '2019-10-10 19:30:11', null);
INSERT INTO `t_payment_flow` VALUES ('5', '6581672466761003008', '6581672372141699072', null, '4', '900', '0', '1', '2', null, '2019-10-11 10:41:39', '2019-10-11 10:41:39', null);
INSERT INTO `t_payment_flow` VALUES ('6', '6581741662710673408', '6581741643119079424', null, '3', '9900', '0', '1', '2', null, '2019-10-11 15:16:36', '2019-10-11 15:16:36', null);
INSERT INTO `t_payment_flow` VALUES ('7', '4200000449201910170056361205', '6583842292354920448', null, '4', '1', '0', '1', '3', null, '2019-10-17 10:23:58', '2019-10-17 10:24:18', '2019-10-17 10:27:47');
INSERT INTO `t_payment_flow` VALUES ('8', '4200000451201910175141113347', '6583845278703562752', null, '3', '1', '0', '1', '3', null, '2019-10-17 10:45:54', '2019-10-17 10:46:13', '2019-10-17 10:49:43');
INSERT INTO `t_payment_flow` VALUES ('9', '6584714664461086720', '6584714639253319680', null, '3', '9900', '0', '1', '2', null, '2019-10-19 20:10:16', '2019-10-19 20:10:16', null);
INSERT INTO `t_payment_flow` VALUES ('10', '6595030571364265984', '6595030537176494080', null, '3', '19800', '0', '1', '2', null, '2019-11-17 07:21:59', '2019-11-17 07:21:59', null);
INSERT INTO `t_payment_flow` VALUES ('11', '6615753713707528192', '6615753631159431168', null, '4', '1', '0', '1', '2', null, '2020-01-13 14:37:49', '2020-01-13 14:37:49', null);

-- ----------------------------
-- Table structure for t_product_category
-- ----------------------------
DROP TABLE IF EXISTS `t_product_category`;
CREATE TABLE `t_product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `category_name` varchar(32) NOT NULL COMMENT '分类名称',
  `category_level` int(11) NOT NULL DEFAULT '1' COMMENT '分类层级',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态(0-删除, 1-可用)',
  `icon` varchar(36) DEFAULT NULL COMMENT '分类图标',
  `img_url` varchar(512) DEFAULT NULL COMMENT '分类图片URL',
  `url` varchar(512) DEFAULT NULL COMMENT '链接',
  `pid` int(11) DEFAULT NULL COMMENT '父级编号',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_product_category_t_product_category_id_fk` (`pid`),
  CONSTRAINT `t_product_category_t_product_category_id_fk` FOREIGN KEY (`pid`) REFERENCES `t_product_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='商品分类表';

-- ----------------------------
-- Records of t_product_category
-- ----------------------------
INSERT INTO `t_product_category` VALUES ('1', '山田产品', '1', '1', null, null, null, null, '2019-07-19 10:57:11', '2019-07-19 10:57:11');
INSERT INTO `t_product_category` VALUES ('2', '手作农产品', '2', '1', null, null, null, '1', '2019-07-19 10:57:11', '2019-07-19 10:57:11');
INSERT INTO `t_product_category` VALUES ('3', '养生果膏系列', '3', '1', null, 'https://www.lcpmkt.com/images/category/category_ysgg.png', 'https://www.baidu.com/', '2', '2019-07-11 15:27:59', '2019-07-11 15:27:59');
INSERT INTO `t_product_category` VALUES ('4', '鲜果古力系列', '3', '1', null, 'https://www.lcpmkt.com/images/category/category_ysgg.png', 'https://www.baidu.com/', '2', '2019-07-11 15:27:59', '2019-07-11 15:27:59');
INSERT INTO `t_product_category` VALUES ('5', '手作巧克力系列', '3', '1', null, 'https://www.lcpmkt.com/images/category/category_ysgg.png', 'https://www.baidu.com/', '2', '2019-07-11 17:23:41', '2019-07-11 17:23:41');
INSERT INTO `t_product_category` VALUES ('6', '手作解馋包系列', '3', '1', '', 'https://www.lcpmkt.com/images/category/category_ysgg.png', 'https://www.baidu.com/', '2', '2019-07-11 17:23:41', '2019-07-11 17:23:41');
INSERT INTO `t_product_category` VALUES ('7', '精致礼盒装系列', '3', '1', '', 'https://www.lcpmkt.com/images/category/category_jksg.png', 'https://www.baidu.com/', '2', '2019-07-11 17:23:41', '2019-07-11 17:23:41');

-- ----------------------------
-- Table structure for t_product_detail_desc
-- ----------------------------
DROP TABLE IF EXISTS `t_product_detail_desc`;
CREATE TABLE `t_product_detail_desc` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `type` varchar(1) NOT NULL DEFAULT '1' COMMENT '描述内容类型(1-图片, 2-文本)',
  `content` text NOT NULL COMMENT '内容',
  `sort_num` int(11) NOT NULL DEFAULT '0' COMMENT '排序编号',
  PRIMARY KEY (`id`),
  KEY `t_product_detail_desc_t_product_info_id_fk` (`product_id`),
  CONSTRAINT `t_product_detail_desc_t_product_info_id_fk` FOREIGN KEY (`product_id`) REFERENCES `t_product_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8 COMMENT='商品详情描述内容';

-- ----------------------------
-- Records of t_product_detail_desc
-- ----------------------------
INSERT INTO `t_product_detail_desc` VALUES ('1', '1', '1', 'https://si.geilicdn.com/pcitem1239723585-698900000167aa4d58400a02853e_750_842.jpg', '0');
INSERT INTO `t_product_detail_desc` VALUES ('2', '1', '1', 'https://si.geilicdn.com/pcitem1239723585-4b4900000167aa4d551c0a02685e_750_366.jpg', '1');
INSERT INTO `t_product_detail_desc` VALUES ('3', '1', '1', 'https://si.geilicdn.com/pcitem1239723585-4b6d00000167aa4d58910a02685e_750_1134.jpg', '2');
INSERT INTO `t_product_detail_desc` VALUES ('4', '1', '1', 'https://si.geilicdn.com/pcitem1239723585-4bde00000167aa4d58e30a026860_750_1298.jpg', '3');
INSERT INTO `t_product_detail_desc` VALUES ('5', '1', '1', 'https://si.geilicdn.com/pcitem1239723585-6e0900000167aa4d58c30a028841_750_1116.jpg', '4');
INSERT INTO `t_product_detail_desc` VALUES ('6', '1', '1', 'https://si.geilicdn.com/pcitem1239723585-6e1200000167aa4d59770a028841_750_1400.jpg', '5');
INSERT INTO `t_product_detail_desc` VALUES ('7', '1', '1', 'https://si.geilicdn.com/pcitem1239723585-699000000167aa4d59030a02853e_750_856.jpg', '6');
INSERT INTO `t_product_detail_desc` VALUES ('8', '1', '1', 'https://si.geilicdn.com/pcitem1239723585-699600000167aa4d59970a02853e_750_1672.jpg', '7');
INSERT INTO `t_product_detail_desc` VALUES ('9', '1', '1', 'https://si.geilicdn.com/pcitem1239723585-4be800000167aa4d59990a026860_750_1280.jpg', '8');
INSERT INTO `t_product_detail_desc` VALUES ('10', '1', '1', 'https://si.geilicdn.com/pcitem1239723585-4b7e00000167aa4d599b0a02685e_750_1158.jpg', '9');
INSERT INTO `t_product_detail_desc` VALUES ('11', '1', '1', 'https://si.geilicdn.com/pcitem1239723585-4b7900000167aa4d593a0a02685e_750_678.jpg', '10');
INSERT INTO `t_product_detail_desc` VALUES ('12', '1', '1', 'https://si.geilicdn.com/img-52560000016c27b5af2b0a211580-unadjust_1125_636.png', '11');
INSERT INTO `t_product_detail_desc` VALUES ('13', '2', '1', 'https://si.geilicdn.com/pcitem1239723585-131100000167c49b29c70a217216_750_366.jpg', '1');
INSERT INTO `t_product_detail_desc` VALUES ('14', '2', '1', 'https://si.geilicdn.com/pcitem1239723585-1ae100000167c49b84b50a217252_750_1298.jpg', '2');
INSERT INTO `t_product_detail_desc` VALUES ('15', '2', '1', 'https://si.geilicdn.com/pcitem1239723585-562b000001675d77ffea0a02685e-unadjust_1484_948.png', '3');
INSERT INTO `t_product_detail_desc` VALUES ('16', '2', '1', 'https://si.geilicdn.com/pcitem1239723585-5714000001675d7800210a026860-unadjust_1437_940.png', '4');
INSERT INTO `t_product_detail_desc` VALUES ('17', '2', '1', 'https://si.geilicdn.com/pcitem1239723585-13f200000167c49b84bc0a216239_750_1116.jpg', '5');
INSERT INTO `t_product_detail_desc` VALUES ('18', '2', '1', 'https://si.geilicdn.com/pcitem1239723585-16fe00000167c49b851b0a217216_750_1400.jpg', '6');
INSERT INTO `t_product_detail_desc` VALUES ('19', '2', '1', 'https://si.geilicdn.com/pcitem1239723585-199200000167c49b85470a217205_750_856.jpg', '7');
INSERT INTO `t_product_detail_desc` VALUES ('20', '2', '1', 'https://si.geilicdn.com/pcitem1239723585-195900000167c49b86030a20b7b9_750_1672.jpg', '8');
INSERT INTO `t_product_detail_desc` VALUES ('21', '2', '1', 'https://si.geilicdn.com/pcitem1239723585-1af200000167c49b86430a217252_750_1280.jpg', '9');
INSERT INTO `t_product_detail_desc` VALUES ('22', '2', '1', 'https://si.geilicdn.com/pcitem1239723585-140c00000167c49b86b90a216239_750_1158.jpg', '10');
INSERT INTO `t_product_detail_desc` VALUES ('23', '2', '1', 'https://si.geilicdn.com/pcitem1239723585-171a00000167c49b86d30a217216_750_678.jpg', '11');
INSERT INTO `t_product_detail_desc` VALUES ('24', '2', '1', 'https://si.geilicdn.com/pcitem1239723585-1a8c000001675d74095f0a02685e-unadjust_2107_1403.png', '12');
INSERT INTO `t_product_detail_desc` VALUES ('25', '2', '1', 'https://si.geilicdn.com/img-52560000016c27b5af2b0a211580-unadjust_1125_636.png', '13');
INSERT INTO `t_product_detail_desc` VALUES ('26', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-7aad00000167aa67a8ab0a026860_750_1149.jpg', '1');
INSERT INTO `t_product_detail_desc` VALUES ('27', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-7a2900000167aa67a7cf0a02685e_750_410.jpg', '2');
INSERT INTO `t_product_detail_desc` VALUES ('28', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-186000000167aa67a8b00a02853e_750_945.jpg', '3');
INSERT INTO `t_product_detail_desc` VALUES ('29', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-1cdf00000167aa67a8ca0a028841_750_757.jpg', '4');
INSERT INTO `t_product_detail_desc` VALUES ('30', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-7a3800000167aa67a9090a02685e_750_786.jpg', '5');
INSERT INTO `t_product_detail_desc` VALUES ('31', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-7ab400000167aa67a9530a026860_750_800.jpg', '6');
INSERT INTO `t_product_detail_desc` VALUES ('32', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-187100000167aa67a9c30a02853e_750_915.jpg', '7');
INSERT INTO `t_product_detail_desc` VALUES ('33', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-1cf100000167aa67aa160a028841_750_843.jpg', '8');
INSERT INTO `t_product_detail_desc` VALUES ('34', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-7ac500000167aa67aa9e0a026860_750_824.jpg', '9');
INSERT INTO `t_product_detail_desc` VALUES ('35', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-7a4500000167aa67aa550a02685e_750_487.jpg', '10');
INSERT INTO `t_product_detail_desc` VALUES ('36', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-188100000167aa67aaee0a02853e_750_717.jpg', '11');
INSERT INTO `t_product_detail_desc` VALUES ('37', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-1d0000000167aa67ab4a0a028841_750_732.jpg', '12');
INSERT INTO `t_product_detail_desc` VALUES ('38', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-7a6000000167aa67abae0a02685e_750_736.jpg', '13');
INSERT INTO `t_product_detail_desc` VALUES ('39', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-7ae100000167aa67ac580a026860_750_737.jpg', '14');
INSERT INTO `t_product_detail_desc` VALUES ('40', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-189c00000167aa67acb60a02853e_750_737.jpg', '15');
INSERT INTO `t_product_detail_desc` VALUES ('41', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-1d1800000167aa67acb90a028841_750_299.jpg', '16');
INSERT INTO `t_product_detail_desc` VALUES ('42', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-7a7500000167aa67ad440a02685e_750_638.jpg', '17');
INSERT INTO `t_product_detail_desc` VALUES ('43', '3', '1', 'https://si.geilicdn.com/pcitem1239723585-7af100000167aa67ad810a026860_750_568.jpg', '18');
INSERT INTO `t_product_detail_desc` VALUES ('44', '3', '1', 'https://si.geilicdn.com/img-52560000016c27b5af2b0a211580-unadjust_1125_636.png', '19');
INSERT INTO `t_product_detail_desc` VALUES ('45', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-5d7f0000016b55832b5b0a21167e_1500_1986.jpg', '1');
INSERT INTO `t_product_detail_desc` VALUES ('46', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-0de50000016b55832ba60a217205_1500_1544.jpg', '2');
INSERT INTO `t_product_detail_desc` VALUES ('47', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-26310000016b55832bad0a20b7b9_1500_1022.jpg', '3');
INSERT INTO `t_product_detail_desc` VALUES ('48', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-284d0000016b55832c120a21924a_1500_1594.jpg', '4');
INSERT INTO `t_product_detail_desc` VALUES ('49', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-0d2d0000016b558a9e000a217252-unadjust_1825_1213.png', '5');
INSERT INTO `t_product_detail_desc` VALUES ('50', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-00740000016b55832d6b0a21924b_1500_2186.jpg', '6');
INSERT INTO `t_product_detail_desc` VALUES ('51', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-68460000016b55870dfd0a20b7b9-unadjust_747_523.png', '7');
INSERT INTO `t_product_detail_desc` VALUES ('52', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-53cc0000016b55873e2e0a217252-unadjust_898_284.png', '8');
INSERT INTO `t_product_detail_desc` VALUES ('53', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-07120000016b5588bbaa0a21924a-unadjust_414_246.png', '9');
INSERT INTO `t_product_detail_desc` VALUES ('54', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-720a0000016b558905ff0a217252-unadjust_1269_847.png', '10');
INSERT INTO `t_product_detail_desc` VALUES ('55', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-75980000016b55893ada0a217252-unadjust_927_659.png', '11');
INSERT INTO `t_product_detail_desc` VALUES ('56', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-09af0000016b55832dcf0a217216_1500_1518.jpg', '12');
INSERT INTO `t_product_detail_desc` VALUES ('57', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-4ddd0000016b5589c7910a21167e-unadjust_804_1192.png', '13');
INSERT INTO `t_product_detail_desc` VALUES ('58', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-545a0000016b55898b4e0a219248-unadjust_1337_891.png', '14');
INSERT INTO `t_product_detail_desc` VALUES ('59', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-0e0f0000016b55832e1a0a217205_1500_2356.jpg', '15');
INSERT INTO `t_product_detail_desc` VALUES ('60', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-26650000016b55832e810a20b7b9_1500_1808.jpg', '16');
INSERT INTO `t_product_detail_desc` VALUES ('61', '4', '1', 'https://si.geilicdn.com/pcitem1239723585-28780000016b55832edf0a21924a_1500_2730.jpg', '17');
INSERT INTO `t_product_detail_desc` VALUES ('62', '4', '1', 'https://si.geilicdn.com/img-52560000016c27b5af2b0a211580-unadjust_1125_636.png', '18');
INSERT INTO `t_product_detail_desc` VALUES ('63', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-2e260000016bc11ed4750a217252_800_800.jpg', '1');
INSERT INTO `t_product_detail_desc` VALUES ('64', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-3c630000016bc120e6b90a219248-unadjust_1868_1471.png', '2');
INSERT INTO `t_product_detail_desc` VALUES ('65', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-0cba0000016bc12748d20a211580_1200_1589.jpg', '3');
INSERT INTO `t_product_detail_desc` VALUES ('66', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-40dc0000016bc12775900a217205_1200_1235.jpg', '4');
INSERT INTO `t_product_detail_desc` VALUES ('67', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-12440000016bc127959a0a211580_1200_798.jpg', '5');
INSERT INTO `t_product_detail_desc` VALUES ('68', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-71430000016bc127b55d0a20b7b9_1200_1214.jpg', '6');
INSERT INTO `t_product_detail_desc` VALUES ('69', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-39e90000016bc127d8710a21924b_1200_1446.jpg', '7');
INSERT INTO `t_product_detail_desc` VALUES ('70', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-40c60000016bc12808200a219248-unadjust_815_889.png', '8');
INSERT INTO `t_product_detail_desc` VALUES ('71', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-23520000016bc12880d30a211580_750_842.jpg', '9');
INSERT INTO `t_product_detail_desc` VALUES ('72', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-04490000016bc128805a0a217216_750_366.jpg', '10');
INSERT INTO `t_product_detail_desc` VALUES ('73', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-18f20000016bc12880d50a21167e_750_1134.jpg', '11');
INSERT INTO `t_product_detail_desc` VALUES ('74', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-62200000016bc12881440a217252_750_1298.jpg', '12');
INSERT INTO `t_product_detail_desc` VALUES ('75', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-543b0000016bc12881950a217205_750_1116.jpg', '13');
INSERT INTO `t_product_detail_desc` VALUES ('76', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-46490000016bc12881d50a21924b_750_1400.jpg', '14');
INSERT INTO `t_product_detail_desc` VALUES ('77', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-00330000016bc128823a0a20b7b9_750_856.jpg', '15');
INSERT INTO `t_product_detail_desc` VALUES ('78', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-23790000016bc12882e40a211580_750_1672.jpg', '16');
INSERT INTO `t_product_detail_desc` VALUES ('79', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-191b0000016bc12883280a21167e_750_1280.jpg', '17');
INSERT INTO `t_product_detail_desc` VALUES ('80', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-624e0000016bc12883ac0a217252_750_1158.jpg', '18');
INSERT INTO `t_product_detail_desc` VALUES ('81', '5', '1', 'https://si.geilicdn.com/pcitem1239723585-54640000016bc12883e00a217205_750_678.jpg', '19');
INSERT INTO `t_product_detail_desc` VALUES ('82', '5', '1', 'https://si.geilicdn.com/img-52560000016c27b5af2b0a211580-unadjust_1125_636.png', '20');
INSERT INTO `t_product_detail_desc` VALUES ('83', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-677a0000016b5579ba970a217216-unadjust_789_1212.png', '1');
INSERT INTO `t_product_detail_desc` VALUES ('84', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-7a7b0000016b557a91f60a217252_1500_1973.jpg', '2');
INSERT INTO `t_product_detail_desc` VALUES ('85', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-748e0000016b557c7cea0a219248-unadjust_805_1209.png', '3');
INSERT INTO `t_product_detail_desc` VALUES ('86', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-3c590000016b557cea000a21924a-unadjust_1788_907.png', '4');
INSERT INTO `t_product_detail_desc` VALUES ('87', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-6c810000016b557a92620a21924b_1500_1974.jpg', '5');
INSERT INTO `t_product_detail_desc` VALUES ('88', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-49950000016b557a93c90a21167e_1500_2988.jpg', '6');
INSERT INTO `t_product_detail_desc` VALUES ('89', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-7a0b0000016b557a94710a217205_1500_1443.jpg', '7');
INSERT INTO `t_product_detail_desc` VALUES ('90', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-77000000016b557d6c1d0a211580-unadjust_696_778.png', '8');
INSERT INTO `t_product_detail_desc` VALUES ('91', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-683f0000016b557f951e0a20b7b9-unadjust_493_176.png', '9');
INSERT INTO `t_product_detail_desc` VALUES ('92', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-2f010000016b557fe4480a219248-unadjust_1813_1198.png', '10');
INSERT INTO `t_product_detail_desc` VALUES ('93', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-734f0000016b55801bd10a21924a-unadjust_1819_1213.png', '11');
INSERT INTO `t_product_detail_desc` VALUES ('94', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-747a0000016b55804b930a20b7b9-unadjust_1812_1201.png', '12');
INSERT INTO `t_product_detail_desc` VALUES ('95', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-53e80000016b5580994e0a21924b-unadjust_1799_1193.png', '13');
INSERT INTO `t_product_detail_desc` VALUES ('96', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-09a90000016b558186650a20b7b9-unadjust_473_159.png', '14');
INSERT INTO `t_product_detail_desc` VALUES ('97', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-467e0000016b557a97660a211580_1500_1991.jpg', '15');
INSERT INTO `t_product_detail_desc` VALUES ('98', '6', '1', 'https://si.geilicdn.com/pcitem1239723585-53e70000016b557a975b0a219248_1500_2180.jpg', '16');
INSERT INTO `t_product_detail_desc` VALUES ('99', '6', '1', 'https://si.geilicdn.com/img-52560000016c27b5af2b0a211580-unadjust_1125_636.png', '17');
INSERT INTO `t_product_detail_desc` VALUES ('100', '7', '1', 'https://si.geilicdn.com/pcitem1239723585-089f0000016b78c97fe40a217252_790_9456.jpg', '1');
INSERT INTO `t_product_detail_desc` VALUES ('101', '7', '1', 'https://si.geilicdn.com/img-52560000016c27b5af2b0a211580-unadjust_1125_636.png', '2');
INSERT INTO `t_product_detail_desc` VALUES ('102', '8', '1', 'https://si.geilicdn.com/pcitem1239723585-4b490000016850e455ae0a217205_1534_7027.jpg', '1');
INSERT INTO `t_product_detail_desc` VALUES ('103', '8', '1', 'https://si.geilicdn.com/pcitem1239723585-76b40000016850e458710a217252_1534_6606.jpg', '2');
INSERT INTO `t_product_detail_desc` VALUES ('104', '8', '1', 'https://si.geilicdn.com/pcitem1239723585-368d0000016850e4574f0a217216_1534_7324.jpg', '3');
INSERT INTO `t_product_detail_desc` VALUES ('105', '8', '1', 'https://si.geilicdn.com/pcitem1239723585-369b0000016850e458680a217216_1534_5725.jpg', '4');
INSERT INTO `t_product_detail_desc` VALUES ('106', '8', '1', 'https://v.qq.com/x/page/j0826nbt6fw.html?', '5');
INSERT INTO `t_product_detail_desc` VALUES ('107', '8', '1', 'https://si.geilicdn.com/pcitem1239723585-4b6d0000016850e458500a217205_1534_4822.jpg', '6');
INSERT INTO `t_product_detail_desc` VALUES ('108', '8', '1', 'https://si.geilicdn.com/img-52560000016c27b5af2b0a211580-unadjust_1125_636.png', '7');
INSERT INTO `t_product_detail_desc` VALUES ('109', '9', '1', 'https://si.geilicdn.com/pcitem1239723585-790800000167c4ad46600a217216_750_366.jpg', '1');
INSERT INTO `t_product_detail_desc` VALUES ('110', '9', '1', 'https://si.geilicdn.com/pcitem1239723585-7e5300000167c4ad7a970a20b7b9_800_600.jpg', '2');
INSERT INTO `t_product_detail_desc` VALUES ('111', '9', '1', 'https://si.geilicdn.com/bj-wd-1239723585-1537960932247-470354769_770_770.jpg?w=770&h=770', '1');
INSERT INTO `t_product_detail_desc` VALUES ('112', '9', '1', 'https://si.geilicdn.com/bj-wd-1239723585-1537973521037-210062156_1200_800.jpg?w=1200&h=800', '2');
INSERT INTO `t_product_detail_desc` VALUES ('113', '9', '1', 'https://si.geilicdn.com/bj-wd-1239723585-1537961452023-1972534232_2560_1706.jpg?w=2560&h=1706', '3');
INSERT INTO `t_product_detail_desc` VALUES ('114', '9', '1', 'https://si.geilicdn.com/bj-wd-1239723585-1537960934260-2013652786_2560_1706.jpg?w=2560&h=1706', '4');
INSERT INTO `t_product_detail_desc` VALUES ('115', '9', '1', 'https://si.geilicdn.com/bj-wd-1239723585-1537961456800-1355620625_2356_3072.jpg?w=2356&h=3072', '5');
INSERT INTO `t_product_detail_desc` VALUES ('116', '9', '1', 'https://si.geilicdn.com/pcitem1239723585-626900000167c4b5330b0a217252_800_800.jpg', '6');
INSERT INTO `t_product_detail_desc` VALUES ('117', '9', '1', 'https://si.geilicdn.com/pcitem1239723585-60a400000167c4b55d190a217216_800_800.jpg', '7');
INSERT INTO `t_product_detail_desc` VALUES ('118', '9', '1', 'https://si.geilicdn.com/pcitem1239723585-5d7e00000167c4b55d950a216239_800_800.jpg', '8');
INSERT INTO `t_product_detail_desc` VALUES ('119', '9', '1', 'https://si.geilicdn.com/pcitem1239723585-5f6c00000167c4b5806a0a216239_1280_767.jpg', '9');
INSERT INTO `t_product_detail_desc` VALUES ('120', '9', '1', 'https://si.geilicdn.com/img-52560000016c27b5af2b0a211580-unadjust_1125_636.png', '10');
INSERT INTO `t_product_detail_desc` VALUES ('121', '10', '1', 'https://si.geilicdn.com/pcitem1239723585-7ac300000167c4469d890a217252-unadjust_1640_1095.png', '1');
INSERT INTO `t_product_detail_desc` VALUES ('122', '10', '1', 'https://si.geilicdn.com/pcitem1239723585-7bc100000167c446d6550a20b7b9_750_366.jpg', '2');
INSERT INTO `t_product_detail_desc` VALUES ('123', '10', '1', 'https://si.geilicdn.com/pcitem1239723585-7f3500000167c44720f10a20b7b9_1181_1969.jpg', '3');
INSERT INTO `t_product_detail_desc` VALUES ('124', '10', '1', 'https://si.geilicdn.com/pcitem1239723585-0ea200000167c4485f750a20b7b9-unadjust_1646_1101.png', '4');
INSERT INTO `t_product_detail_desc` VALUES ('125', '10', '1', 'https://si.geilicdn.com/pcitem1239723585-0d7700000167c448ba5e0a216239-unadjust_1005_1021.png', '5');
INSERT INTO `t_product_detail_desc` VALUES ('126', '10', '1', 'https://si.geilicdn.com/pcitem1239723585-16cf00000167c449049e0a217205-unadjust_987_868.png', '6');
INSERT INTO `t_product_detail_desc` VALUES ('127', '10', '1', 'https://si.geilicdn.com/pcitem1239723585-280a00000167c4554e8e0a216239-unadjust_795_931.png', '7');
INSERT INTO `t_product_detail_desc` VALUES ('128', '10', '1', 'https://si.geilicdn.com/pcitem1239723585-21f300000167c45f94640a216239-unadjust_812_771.png', '8');
INSERT INTO `t_product_detail_desc` VALUES ('129', '10', '1', 'https://si.geilicdn.com/pcitem1239723585-77a300000167c45bb41c0a217205-unadjust_1635_1095.png', '9');
INSERT INTO `t_product_detail_desc` VALUES ('130', '10', '1', 'https://si.geilicdn.com/pcitem1239723585-74c500000167c45be4280a216239_1280_767.jpg', '10');
INSERT INTO `t_product_detail_desc` VALUES ('131', '10', '1', 'https://si.geilicdn.com/img-52560000016c27b5af2b0a211580-unadjust_1125_636.png', '11');
INSERT INTO `t_product_detail_desc` VALUES ('132', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-4cd30000016b9cb338970a217252_790_1322.jpg', '1');
INSERT INTO `t_product_detail_desc` VALUES ('133', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-44200000016b9cb337ef0a217205_790_785.jpg', '2');
INSERT INTO `t_product_detail_desc` VALUES ('134', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-69860000016b9cb337020a20b7b9_790_716.jpg', '3');
INSERT INTO `t_product_detail_desc` VALUES ('135', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-66e50000016b9cb337490a21924a_790_510.jpg', '4');
INSERT INTO `t_product_detail_desc` VALUES ('136', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-3aa30000016b9cb337490a21924b_790_348.jpg', '5');
INSERT INTO `t_product_detail_desc` VALUES ('137', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-11220000016b9cb3377f0a21167e_790_323.jpg', '6');
INSERT INTO `t_product_detail_desc` VALUES ('138', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-43190000016b9cb337ee0a217216_790_337.jpg', '7');
INSERT INTO `t_product_detail_desc` VALUES ('139', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-16ba0000016b9cb338810a211580_790_507.jpg', '8');
INSERT INTO `t_product_detail_desc` VALUES ('140', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-699f0000016b9cb338920a20b7b9_790_348.jpg', '9');
INSERT INTO `t_product_detail_desc` VALUES ('141', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-1f120000016b9cb3387f0a219248_2_1156.jpg', '10');
INSERT INTO `t_product_detail_desc` VALUES ('142', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-621e0000016c4acb36d80a20b7b9_788_328.jpg', '11');
INSERT INTO `t_product_detail_desc` VALUES ('143', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-1f260000016b9cb339c90a219248_788_828.jpg', '12');
INSERT INTO `t_product_detail_desc` VALUES ('144', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-16cd0000016b9cb33a0e0a211580_790_591.jpg', '13');
INSERT INTO `t_product_detail_desc` VALUES ('145', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-69ba0000016b9cb33a0b0a20b7b9_790_578.jpg', '14');
INSERT INTO `t_product_detail_desc` VALUES ('146', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-4cf00000016b9cb33a3b0a217252_790_579.jpg', '15');
INSERT INTO `t_product_detail_desc` VALUES ('147', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-3ad90000016b9cb33acd0a21924b_790_970.jpg', '16');
INSERT INTO `t_product_detail_desc` VALUES ('148', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-115d0000016b9cb33b3e0a21167e_790_651.jpg', '17');
INSERT INTO `t_product_detail_desc` VALUES ('149', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-43530000016b9cb33bd00a217216_790_971.jpg', '18');
INSERT INTO `t_product_detail_desc` VALUES ('150', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-69dd0000016b9cb33c530a20b7b9_790_765.jpg', '19');
INSERT INTO `t_product_detail_desc` VALUES ('151', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-4d150000016b9cb33c880a217252_790_308.jpg', '20');
INSERT INTO `t_product_detail_desc` VALUES ('152', '11', '1', 'https://si.geilicdn.com/img-52560000016c27b5af2b0a211580-unadjust_1125_636.png', '21');
INSERT INTO `t_product_detail_desc` VALUES ('153', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-7ac300000167c4469d890a217252-unadjust_1640_1095.png', '22');
INSERT INTO `t_product_detail_desc` VALUES ('154', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-7bc100000167c446d6550a20b7b9_750_366.jpg', '23');
INSERT INTO `t_product_detail_desc` VALUES ('155', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-7f3500000167c44720f10a20b7b9_1181_1969.jpg', '24');
INSERT INTO `t_product_detail_desc` VALUES ('156', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-0ea200000167c4485f750a20b7b9-unadjust_1646_1101.png', '25');
INSERT INTO `t_product_detail_desc` VALUES ('157', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-0d7700000167c448ba5e0a216239-unadjust_1005_1021.png', '26');
INSERT INTO `t_product_detail_desc` VALUES ('158', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-16cf00000167c449049e0a217205-unadjust_987_868.png', '27');
INSERT INTO `t_product_detail_desc` VALUES ('159', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-280a00000167c4554e8e0a216239-unadjust_795_931.png', '0');
INSERT INTO `t_product_detail_desc` VALUES ('160', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-21f300000167c45f94640a216239-unadjust_812_771.png', '0');
INSERT INTO `t_product_detail_desc` VALUES ('161', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-77a300000167c45bb41c0a217205-unadjust_1635_1095.png', '0');
INSERT INTO `t_product_detail_desc` VALUES ('162', '11', '1', 'https://si.geilicdn.com/pcitem1239723585-74c500000167c45be4280a216239_1280_767.jpg', '0');
INSERT INTO `t_product_detail_desc` VALUES ('163', '11', '1', 'https://si.geilicdn.com/img-52560000016c27b5af2b0a211580-unadjust_1125_636.png', '0');

-- ----------------------------
-- Table structure for t_product_info
-- ----------------------------
DROP TABLE IF EXISTS `t_product_info`;
CREATE TABLE `t_product_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `product_name` varchar(128) NOT NULL COMMENT '商品名称',
  `supplier_id` int(11) NOT NULL COMMENT '供应商id',
  `one_level_category` int(11) DEFAULT NULL COMMENT '一级分类',
  `two_level_category` int(11) DEFAULT NULL COMMENT '二级分类',
  `three_level_category` int(11) DEFAULT NULL COMMENT '三级分类',
  `publish_status` varchar(1) NOT NULL COMMENT '商品发布状态(0-无效, 1-上架, 2-下架)',
  `new_arrivals` varchar(1) NOT NULL DEFAULT '0' COMMENT '是否为新品推荐(0-否, 1-是)',
  `main_image` varchar(512) NOT NULL COMMENT '主图',
  `product_details` text COMMENT '商品详情',
  `descript` varchar(512) DEFAULT NULL COMMENT '商品描述',
  `spec_grop_name` varchar(128) DEFAULT NULL COMMENT '规格组名称',
  `spec_type` varchar(1) NOT NULL DEFAULT '1' COMMENT '规格类型(1-单规格, 2-多规格)',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商品录入时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商品更新时间',
  PRIMARY KEY (`id`),
  KEY `t_product_info_t_product_category_id_fk` (`one_level_category`),
  KEY `t_product_info_t_product_category_id_fk_2` (`two_level_category`),
  KEY `t_product_info_t_product_category_id_fk_3` (`three_level_category`),
  KEY `t_product_info_t_supplier_info_id_fk` (`supplier_id`),
  CONSTRAINT `t_product_info_t_product_category_id_fk` FOREIGN KEY (`one_level_category`) REFERENCES `t_product_category` (`id`),
  CONSTRAINT `t_product_info_t_product_category_id_fk_2` FOREIGN KEY (`two_level_category`) REFERENCES `t_product_category` (`id`),
  CONSTRAINT `t_product_info_t_product_category_id_fk_3` FOREIGN KEY (`three_level_category`) REFERENCES `t_product_category` (`id`),
  CONSTRAINT `t_product_info_t_supplier_info_id_fk` FOREIGN KEY (`supplier_id`) REFERENCES `t_supplier_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Records of t_product_info
-- ----------------------------
INSERT INTO `t_product_info` VALUES ('1', '抗霾舒畅柠果膏', '1', '1', '2', '3', '1', '1', 'https://www.lcpmkt.com/images/product/product_01_00.png', '解压生津润喉增强抵抗力', '纯手工制作冰糖柠檬膏抵抗雾霾舒畅呼吸', null, '1', '2019-08-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO `t_product_info` VALUES ('2', '解压双降柠果膏', '1', '1', '2', '3', '1', '1', 'https://www.lcpmkt.com/images/product/product_02_00.png', '生津润喉降燥降火', '生津润喉降燥降火', null, '1', '2019-11-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO `t_product_info` VALUES ('3', '暖宫柠果膏', '1', '1', '2', '3', '1', '0', 'https://www.lcpmkt.com/images/product/product_03_00.jpg', '女王节福利顺丰包邮当归暖宫柠檬膏美颜滋润缓解生理期不适症状无添加冰糖柠檬膏', '女王节福利顺丰包邮当归暖宫柠檬膏美颜滋润缓解生理期不适症状无添加冰糖柠檬膏', null, '1', '2019-07-18 17:24:26', '2019-07-11 17:24:26');
INSERT INTO `t_product_info` VALUES ('4', '鲜柠古力', '1', '1', '2', '4', '1', '1', 'https://www.lcpmkt.com/images/product/product_04_00.png', '新品上市鲜柠古力柠檬巧克力片酸甜可口（2盒顺丰包邮）', '新品上市鲜柠古力柠檬巧克力片酸甜可口（2盒顺丰包邮）', null, '1', '2019-11-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO `t_product_info` VALUES ('5', '鲜橙古力', '1', '1', '2', '4', '1', '0', 'https://www.lcpmkt.com/images/product/product_05_00.jpg', '限时惊喜套餐柠檬精的最爱：鲜柠古力+抗霾舒畅柠果膏', '限时惊喜套餐柠檬精的最爱：鲜柠古力+抗霾舒畅柠果膏', null, '1', '2019-01-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO `t_product_info` VALUES ('6', '栗子巧克力', '1', '1', '2', '5', '1', '1', 'https://www.lcpmkt.com/images/product/product_06_00.jpg', '新品上市栗子巧克力致敬经典顺丰包邮', '新品上市栗子巧克力致敬经典顺丰包邮', null, '1', '2019-06-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO `t_product_info` VALUES ('7', 'DIY巧克力', '1', '1', '2', '5', '1', '0', 'https://www.lcpmkt.com/images/product/product_07_00.png', 'DIY手作巧克力七种口味随意搭配6颗起发', '七种口味随意搭配6颗起发', '手作巧克力系列', '2', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO `t_product_info` VALUES ('8', '纯实牛肉干', '1', '1', '2', '6', '1', '0', 'https://www.lcpmkt.com/images/product/product_08_00.jpg', '山尖田——纯实牛肉干 幸福双旦季 纯手工制作无添加 只选顺丰', '幸福双旦季 纯手工制作无添加 只选顺丰', null, '1', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO `t_product_info` VALUES ('9', '鲜虾薄脆', '1', '1', '2', '6', '1', '0', 'https://www.lcpmkt.com/images/product/product_09_00.jpg', '山尖田——鲜虾薄脆虾饼 纯手作制作无添加 保质期短建议尽快食用 顺丰快递幸福双旦季', '纯手作制作无添加 保质期短建议尽快食用 顺丰快递幸福双旦季', null, '1', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO `t_product_info` VALUES ('10', '随饮小憩', '1', '1', '2', '7', '1', '0', 'https://www.lcpmkt.com/images/product/product_10_00.jpg', '随饮小憩——中式下午茶礼盒 圣诞优惠季全场八折买一送二柠檬膏牛肉干虾饼杏仁瓦片芝士坚果酥紫草膏', '圣诞优惠季全场八折买一送二柠檬膏牛肉干虾饼杏仁瓦片芝士坚果酥紫草膏', '随饮小憩款式', '2', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO `t_product_info` VALUES ('11', '梵高月饼', '1', '1', '2', '7', '1', '0', 'https://www.lcpmkt.com/images/product/product_11_00.jpg', '山尖田——鲜虾薄脆虾饼 纯手作制作无添加 保质期短建议尽快食用 顺丰快递幸福双旦季', '山尖田新品梵高艺术月饼奶黄月饼预定', '梵高月饼', '2', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO `t_product_info` VALUES ('12', '补差价专拍', '1', '1', '2', '4', '1', '0', 'https://www.lcpmkt.com/images/product/product_00_00.jpg', null, '补差价专拍', null, '1', '2019-08-02 14:54:25', '2019-08-02 14:54:25');

-- ----------------------------
-- Table structure for t_product_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_product_pic`;
CREATE TABLE `t_product_pic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `pic_url` varchar(256) NOT NULL COMMENT '图片URL',
  `descript` varchar(64) DEFAULT NULL COMMENT '图片描述',
  `sort_num` int(11) NOT NULL DEFAULT '0' COMMENT '图片排序编号',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态(0-无效, 1-有效)',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_product_pic_t_product_info_id_fk` (`product_id`),
  CONSTRAINT `t_product_pic_t_product_info_id_fk` FOREIGN KEY (`product_id`) REFERENCES `t_product_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='商品图片表';

-- ----------------------------
-- Records of t_product_pic
-- ----------------------------
INSERT INTO `t_product_pic` VALUES ('1', '1', 'https://www.lcpmkt.com/images/product/pic_01_01.jpg', null, '1', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('2', '1', 'https://www.lcpmkt.com/images/product/pic_01_02.jpg', null, '2', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('3', '1', 'https://www.lcpmkt.com/images/product/pic_01_03.jpg', null, '3', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('4', '1', 'https://www.lcpmkt.com/images/product/pic_01_04.jpg', null, '4', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('5', '1', 'https://www.lcpmkt.com/images/product/pic_01_05.jpg', null, '5', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('6', '2', 'https://www.lcpmkt.com/images/product/pic_02_01.jpg', null, '1', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('7', '2', 'https://www.lcpmkt.com/images/product/pic_02_02.jpg', null, '2', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('8', '2', 'https://www.lcpmkt.com/images/product/pic_02_03.jpg', null, '3', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('9', '2', 'https://www.lcpmkt.com/images/product/pic_02_04.jpg', null, '4', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('10', '2', 'https://www.lcpmkt.com/images/product/pic_02_05.jpg', null, '5', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('11', '3', 'https://www.lcpmkt.com/images/product/pic_03_01.jpg', null, '1', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('12', '3', 'https://www.lcpmkt.com/images/product/pic_03_02.jpg', null, '2', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('13', '3', 'https://www.lcpmkt.com/images/product/pic_03_03.jpg', null, '3', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('14', '3', 'https://www.lcpmkt.com/images/product/pic_03_04.jpg', null, '4', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('15', '3', 'https://www.lcpmkt.com/images/product/pic_03_05.jpg', null, '5', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('16', '3', 'https://www.lcpmkt.com/images/product/PIC_03_06.jpg', null, '6', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('17', '4', 'https://www.lcpmkt.com/images/product/pic_04_01.jpg', null, '1', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('18', '4', 'https://www.lcpmkt.com/images/product/pic_04_02.jpg', null, '2', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('19', '4', 'https://www.lcpmkt.com/images/product/pic_04_03.jpg', null, '3', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('20', '4', 'https://www.lcpmkt.com/images/product/pic_04_04.jpg', null, '4', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('21', '4', 'https://www.lcpmkt.com/images/product/pic_04_05.jpg', null, '5', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('22', '4', 'https://www.lcpmkt.com/images/product/pic_04_06.jpg', null, '6', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('23', '4', 'https://www.lcpmkt.com/images/product/pic_04_07.jpg', null, '7', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('24', '4', 'https://www.lcpmkt.com/images/product/pic_04_08.jpg', null, '8', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('25', '5', 'https://www.lcpmkt.com/images/product/pic_05_01.jpg', null, '1', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('26', '5', 'https://www.lcpmkt.com/images/product/pic_05_02.jpg', null, '2', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('27', '5', 'https://www.lcpmkt.com/images/product/pic_05_03.jpg', null, '3', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('28', '5', 'https://www.lcpmkt.com/images/product/pic_05_04.jpg', null, '4', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('29', '5', 'https://www.lcpmkt.com/images/product/pic_05_05.jpg', null, '5', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('30', '5', 'https://www.lcpmkt.com/images/product/pic_05_06.jpg', null, '6', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('31', '5', 'https://www.lcpmkt.com/images/product/pic_05_07.jpg', null, '7', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('32', '5', 'https://www.lcpmkt.com/images/product/pic_05_08.jpg', null, '8', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('33', '5', 'https://www.lcpmkt.com/images/product/pic_05_09.jpg', null, '9', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('34', '6', 'https://www.lcpmkt.com/images/product/pic_06_01.jpg', null, '1', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('35', '6', 'https://www.lcpmkt.com/images/product/pic_06_02.jpg', null, '2', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('36', '6', 'https://www.lcpmkt.com/images/product/pic_06_03.jpg', null, '3', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('37', '6', 'https://www.lcpmkt.com/images/product/pic_06_04.jpg', null, '4', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('38', '6', 'https://www.lcpmkt.com/images/product/pic_06_05.jpg', null, '5', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('39', '6', 'https://www.lcpmkt.com/images/product/pic_06_06.jpg', null, '6', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('40', '6', 'https://www.lcpmkt.com/images/product/pic_06_07.jpg', null, '7', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('41', '6', 'https://www.lcpmkt.com/images/product/pic_06_08.jpg', null, '8', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('42', '7', 'https://www.lcpmkt.com/images/product/pic_07_01.jpg', null, '1', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('43', '7', 'https://www.lcpmkt.com/images/product/pic_07_02.jpg', null, '2', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('44', '7', 'https://www.lcpmkt.com/images/product/pic_07_03.jpg', null, '3', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('45', '7', 'https://www.lcpmkt.com/images/product/pic_07_04.jpg', null, '4', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('46', '7', 'https://www.lcpmkt.com/images/product/pic_07_05.jpg', null, '5', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('47', '7', 'https://www.lcpmkt.com/images/product/pic_07_06.jpg', null, '6', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('48', '7', 'https://www.lcpmkt.com/images/product/pic_07_07.jpg', null, '7', '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO `t_product_pic` VALUES ('49', '7', 'https://www.lcpmkt.com/images/product/pic_07_08.jpg', null, '8', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('50', '7', 'https://www.lcpmkt.com/images/product/pic_07_09.jpg', null, '9', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('51', '8', 'https://www.lcpmkt.com/images/product/pic_08_01.jpg', null, '1', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('52', '8', 'https://www.lcpmkt.com/images/product/pic_08_02.jpg', null, '2', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('53', '8', 'https://www.lcpmkt.com/images/product/pic_08_03.jpg', null, '3', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('54', '8', 'https://www.lcpmkt.com/images/product/pic_08_04.jpg', null, '4', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('55', '8', 'https://www.lcpmkt.com/images/product/pic_08_05.jpg', null, '5', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('56', '8', 'https://www.lcpmkt.com/images/product/pic_08_06.jpg', null, '6', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('57', '9', 'https://www.lcpmkt.com/images/product/pic_09_01.jpg', null, '1', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('58', '9', 'https://www.lcpmkt.com/images/product/pic_09_02.jpg', null, '2', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('59', '9', 'https://www.lcpmkt.com/images/product/pic_09_03.jpg', null, '3', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('60', '9', 'https://www.lcpmkt.com/images/product/pic_09_04.jpg', null, '4', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('61', '9', 'https://www.lcpmkt.com/images/product/pic_09_05.jpg', null, '5', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('62', '9', 'https://www.lcpmkt.com/images/product/pic_09_06.jpg', null, '6', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('63', '10', 'https://www.lcpmkt.com/images/product/pic_10_01.jpg', null, '1', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('64', '10', 'https://www.lcpmkt.com/images/product/pic_10_02.jpg', null, '2', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('65', '10', 'https://www.lcpmkt.com/images/product/pic_10_03.jpg', null, '3', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('66', '10', 'https://www.lcpmkt.com/images/product/pic_10_04.jpg', null, '4', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('67', '10', 'https://www.lcpmkt.com/images/product/pic_10_05.jpg', null, '5', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('68', '11', 'https://www.lcpmkt.com/images/product/pic_11_01.jpg', null, '1', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('69', '11', 'https://www.lcpmkt.com/images/product/pic_11_02.jpg', null, '2', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('70', '11', 'https://www.lcpmkt.com/images/product/pic_11_03.jpg', null, '3', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('71', '11', 'https://www.lcpmkt.com/images/product/pic_11_04.jpg', null, '4', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('72', '11', 'https://www.lcpmkt.com/images/product/pic_11_05.jpg', null, '5', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('73', '11', 'https://www.lcpmkt.com/images/product/pic_11_06.jpg', null, '6', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('74', '11', 'https://www.lcpmkt.com/images/product/pic_11_07.jpg', null, '7', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('75', '11', 'https://www.lcpmkt.com/images/product/pic_11_08.jpg', null, '8', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('76', '11', 'https://www.lcpmkt.com/images/product/pic_11_09.jpg', null, '9', '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO `t_product_pic` VALUES ('77', '12', 'https://www.lcpmkt.com/images/product/pic_00_01.jpg', null, '0', '1', '2019-08-02 14:55:30', '2019-08-02 14:55:30');

-- ----------------------------
-- Table structure for t_product_properties
-- ----------------------------
DROP TABLE IF EXISTS `t_product_properties`;
CREATE TABLE `t_product_properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `properties_name` varchar(36) NOT NULL COMMENT '属性名称',
  `properties_value` varchar(128) NOT NULL COMMENT '属性值',
  `sort_num` int(11) NOT NULL DEFAULT '0' COMMENT '排序编号',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_product_properties_t_product_info_id_fk` (`product_id`),
  CONSTRAINT `t_product_properties_t_product_info_id_fk` FOREIGN KEY (`product_id`) REFERENCES `t_product_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='商品属性表';

-- ----------------------------
-- Records of t_product_properties
-- ----------------------------
INSERT INTO `t_product_properties` VALUES ('1', '1', '产地', '上海青浦', '1', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('2', '1', '保质期', '90天', '2', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('3', '1', '净含量', '280ml', '3', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('4', '1', '存储方式', '阴凉干燥30°C以下', '4', '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO `t_product_properties` VALUES ('10', '1', '食用方法', '可冲泡 可舀食 可当健康早餐', '5', '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO `t_product_properties` VALUES ('11', '2', '产地', '上海青浦', '1', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('12', '2', '保质期', '90天', '2', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('13', '2', '净含量', '280ml', '3', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('14', '2', '存储方式', '阴凉干燥30°C以下', '4', '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO `t_product_properties` VALUES ('15', '2', '食用方法', '可冲泡 可舀食 可当健康早餐', '5', '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO `t_product_properties` VALUES ('16', '3', '产地', '上海青浦', '1', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('17', '3', '保质期', '90天', '2', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('18', '3', '净含量', '280ml', '3', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('19', '3', '存储方式', '阴凉干燥30°C以下', '4', '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO `t_product_properties` VALUES ('20', '3', '食用方法', '可冲泡 可舀食 可当健康早餐', '5', '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO `t_product_properties` VALUES ('21', '4', '产地', '上海青浦', '1', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('22', '4', '保质期', '30天', '2', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('23', '4', '净含量', '16g*6', '3', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('24', '4', '存储方式', '冷藏保存', '4', '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO `t_product_properties` VALUES ('25', '4', '食用方法', '即食', '5', '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO `t_product_properties` VALUES ('26', '5', '产地', '上海青浦', '1', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('27', '5', '保质期', '30天', '2', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('28', '5', '净含量', '20g*6', '3', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('29', '5', '食用方法', '即食', '4', '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO `t_product_properties` VALUES ('30', '5', '食用方法', '即食', '5', '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO `t_product_properties` VALUES ('31', '6', '产地', '上海青浦', '1', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('32', '6', '保质期', '120天', '2', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('33', '6', '净含量', '12g*6', '3', '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO `t_product_properties` VALUES ('34', '6', '存储方式', '冷冻保存', '4', '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO `t_product_properties` VALUES ('35', '6', '食用方法', '即食', '5', '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO `t_product_properties` VALUES ('36', '7', '产地', '上海青浦', '1', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('37', '7', '保质期', '30天', '2', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('38', '7', '净含量', '11g*6', '3', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('39', '7', '存储方式', '冷藏保存', '4', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('40', '7', '食用方法', '即食', '5', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('41', '8', '产地', '上海青浦', '1', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('42', '8', '保质期', '30天', '2', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('43', '8', '净含量', '120g', '3', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('44', '8', '存储方式', '阴凉干燥30°C以下', '4', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('45', '8', '食用方法', '即食', '5', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('46', '9', '产地', '上海青浦', '1', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('47', '9', '保质期', '15天', '2', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('48', '9', '净含量', '10g左右*6', '3', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('49', '9', '存储方式', '密封阴凉干燥30°C以下', '4', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('50', '9', '食用方法', '即食', '5', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('51', '10', '产地', '上海青浦', '1', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('52', '10', '保质期', '90天', '2', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('53', '10', '净含量', '400g/盒', '3', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('54', '10', '存储方式', '阴凉干燥30°C以下', '4', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('55', '10', '食用方法', '即食', '5', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('56', '11', '产地', '广州深圳', '1', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('57', '11', '保质期', '60天', '2', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('58', '11', '净含量', '480g/盒', '3', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('59', '11', '存储方式', '阴凉干燥30°C以下', '4', '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO `t_product_properties` VALUES ('60', '11', '食用方法', '即食', '5', '2019-07-30 17:58:11', '2019-07-30 17:58:11');

-- ----------------------------
-- Table structure for t_product_spec
-- ----------------------------
DROP TABLE IF EXISTS `t_product_spec`;
CREATE TABLE `t_product_spec` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `spec_name` varchar(128) NOT NULL COMMENT '规格名称',
  `price` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '规格单价(单位：分)',
  `spec_image` varchar(512) NOT NULL COMMENT '规格图片',
  `stock_num` int(11) NOT NULL DEFAULT '0' COMMENT '规格库存',
  `order_stock_num` int(11) NOT NULL DEFAULT '0' COMMENT '规格库存下单未结算数(<= 库存数量, 下单+n, 订单结束-n)',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `t_product_multi_spec_t_product_info_id_fk` (`product_id`),
  CONSTRAINT `t_product_multi_spec_t_product_info_id_fk` FOREIGN KEY (`product_id`) REFERENCES `t_product_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='商品规格信息';

-- ----------------------------
-- Records of t_product_spec
-- ----------------------------
INSERT INTO `t_product_spec` VALUES ('1', '1', '抗霾舒畅柠果膏', '7900', 'https://www.lcpmkt.com/images/product/product_01_00.png', '100', '59', '18');
INSERT INTO `t_product_spec` VALUES ('2', '2', '解压双降柠果膏', '9900', 'https://www.lcpmkt.com/images/product/product_02_00.png', '100', '55', '30');
INSERT INTO `t_product_spec` VALUES ('3', '3', '暖宫柠果膏', '9900', 'https://www.lcpmkt.com/images/product/product_03_00.jpg', '100', '100', '10');
INSERT INTO `t_product_spec` VALUES ('4', '4', '鲜柠古力', '3900', 'https://www.lcpmkt.com/images/product/product_04_00.png', '100', '86', '24');
INSERT INTO `t_product_spec` VALUES ('5', '5', '鲜橙古力', '7900', 'https://www.lcpmkt.com/images/product/product_05_00.jpg', '100', '90', '10');
INSERT INTO `t_product_spec` VALUES ('6', '6', '栗子巧克力', '9900', 'https://www.lcpmkt.com/images/product/product_06_00.jpg', '100', '65', '12');
INSERT INTO `t_product_spec` VALUES ('7', '7', '咖啡巧克力', '1000', 'https://www.lcpmkt.com/images/product/product_07_01.jpg', '100', '70', '22');
INSERT INTO `t_product_spec` VALUES ('8', '7', '橙味巧克力', '1000', 'https://www.lcpmkt.com/images/product/product_07_02.jpg', '100', '0', '0');
INSERT INTO `t_product_spec` VALUES ('9', '7', '焦糖巧克力', '1000', 'https://www.lcpmkt.com/images/product/product_07_03.jpg', '100', '4', '4');
INSERT INTO `t_product_spec` VALUES ('10', '7', '原味巧克力', '900', 'https://www.lcpmkt.com/images/product/product_07_04.jpg', '100', '80', '22');
INSERT INTO `t_product_spec` VALUES ('11', '7', '黑苦巧克力', '1000', 'https://www.lcpmkt.com/images/product/product_07_05.jpg', '100', '0', '2');
INSERT INTO `t_product_spec` VALUES ('12', '7', '椰丝巧克力', '1000', 'https://www.lcpmkt.com/images/product/product_07_06.jpg', '100', '0', '0');
INSERT INTO `t_product_spec` VALUES ('13', '7', '榛子巧克力', '900', 'https://www.lcpmkt.com/images/product/product_07_07.jpg', '100', '0', '6');
INSERT INTO `t_product_spec` VALUES ('14', '8', '纯实牛肉干', '7900', 'https://www.lcpmkt.com/images/product/product_08_00.jpg', '100', '0', '0');
INSERT INTO `t_product_spec` VALUES ('15', '9', '鲜虾薄脆', '6800', 'https://www.lcpmkt.com/images/product/product_09_00.jpg', '100', '0', '0');
INSERT INTO `t_product_spec` VALUES ('16', '10', '健康运动款', '13900', 'https://www.lcpmkt.com/images/product/product_10_01.jpg', '100', '0', '0');
INSERT INTO `t_product_spec` VALUES ('17', '10', '成熟男士款', '15900', 'https://www.lcpmkt.com/images/product/product_10_02.jpg', '100', '0', '0');
INSERT INTO `t_product_spec` VALUES ('18', '10', '优雅女士款', '12900', 'https://www.lcpmkt.com/images/product/product_10_03.jpg', '100', '0', '0');
INSERT INTO `t_product_spec` VALUES ('19', '11', '[杏福团圆]奶黄月饼', '15800', 'https://www.lcpmkt.com/images/product/product_11_01.jpg', '100', '0', '0');
INSERT INTO `t_product_spec` VALUES ('20', '11', '[盛开的杏树]香茶月饼礼盒', '35800', 'https://www.lcpmkt.com/images/product/product_11_02.jpg', '100', '0', '0');
INSERT INTO `t_product_spec` VALUES ('21', '11', '[月满杏树]月饼礼盒', '49800', 'https://www.lcpmkt.com/images/product/product_11_03.jpg', '100', '0', '0');
INSERT INTO `t_product_spec` VALUES ('22', '7', '任性随机6颗', '5900', 'https://www.lcpmkt.com/images/product/product_07_08.jpg', '100', '0', '2');
INSERT INTO `t_product_spec` VALUES ('23', '12', '补差价专拍', '1', 'https://www.lcpmkt.com/images/product/product_00_00.jpg', '997', '1', '7');

-- ----------------------------
-- Table structure for t_product_stock
-- ----------------------------
DROP TABLE IF EXISTS `t_product_stock`;
CREATE TABLE `t_product_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `product_stock_num` int(11) NOT NULL COMMENT '商品库存数量',
  `order_stock_num` int(11) NOT NULL DEFAULT '0' COMMENT '订单库存数量(<= 商品库存数量, 下单+n, 订单结束-n)',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `t_product_stock_t_product_info_id_fk` (`product_id`),
  CONSTRAINT `t_product_stock_t_product_info_id_fk` FOREIGN KEY (`product_id`) REFERENCES `t_product_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品库存表';

-- ----------------------------
-- Records of t_product_stock
-- ----------------------------

-- ----------------------------
-- Table structure for t_supplier_info
-- ----------------------------
DROP TABLE IF EXISTS `t_supplier_info`;
CREATE TABLE `t_supplier_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `supplier_name` varchar(36) NOT NULL COMMENT '供应商名称',
  `supplier_type` varchar(1) NOT NULL COMMENT '供应商类型(1-自营, 2-品台)',
  `link_man` varchar(16) DEFAULT NULL COMMENT '供应商联系人',
  `phone` varchar(16) NOT NULL COMMENT '联系电话',
  `bank_name` varchar(36) NOT NULL COMMENT '供应商开户银行名称',
  `bank_account` varchar(36) NOT NULL COMMENT '供应商银行账号',
  `address` varchar(256) NOT NULL COMMENT '供应商地址',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态(0-禁用, 1-启动)',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='供应商信息表';

-- ----------------------------
-- Records of t_supplier_info
-- ----------------------------
INSERT INTO `t_supplier_info` VALUES ('1', '山田尖', '1', '山田尖', '15555555555', '交通银行', '44444444444444', '上海', '1', '2019-07-11 17:21:30', '2019-07-11 17:21:30');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(36) DEFAULT NULL COMMENT '用户名',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `face_url` varchar(512) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(36) DEFAULT NULL COMMENT '昵称',
  `sex` varchar(1) NOT NULL DEFAULT '0' COMMENT '性别(0-未设置, 1-男, 2-女)',
  `birtrday` date DEFAULT NULL COMMENT '生日',
  `identity_card_type` varchar(1) DEFAULT NULL COMMENT '证件类型(1-身份证, 2.军官证, 3.护照)',
  `identity_card` varchar(36) DEFAULT NULL COMMENT '证件号码',
  `user_point` int(11) NOT NULL DEFAULT '0' COMMENT '积分',
  `member_level` varchar(1) NOT NULL DEFAULT '0' COMMENT '会员级别(0-普通用户, 1-普通会员)',
  `status` varchar(2) NOT NULL DEFAULT '1' COMMENT '状态(0-冻结 1-可用)',
  `register_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_username_uindex` (`username`),
  UNIQUE KEY `t_user_email_uindex` (`email`),
  UNIQUE KEY `t_user_phone_uindex` (`phone`),
  UNIQUE KEY `t_user_identity_card_uindex` (`identity_card`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('2', 'admin', null, null, '49DF90E9A008FB3B3C21018A33D20103', null, null, '0', null, null, null, '0', '0', '1', '2019-07-26 13:57:45', '2019-07-26 13:57:44', '0');
INSERT INTO `t_user` VALUES ('3', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-09-17 13:55:43', '2019-09-17 13:55:33', '0');
INSERT INTO `t_user` VALUES ('4', 'hyla', null, null, '49DF90E9A008FB3B3C21018A33D20103', null, null, '0', null, null, null, '0', '0', '1', '2019-09-17 14:45:56', '2019-09-17 14:45:46', '0');
INSERT INTO `t_user` VALUES ('5', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-09 16:06:05', '2019-10-09 16:05:57', '0');
INSERT INTO `t_user` VALUES ('6', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-09 16:25:03', '2019-10-09 16:24:55', '0');
INSERT INTO `t_user` VALUES ('7', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-10 11:32:48', '2019-10-10 11:32:40', '0');
INSERT INTO `t_user` VALUES ('8', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-10 13:07:17', '2019-10-10 13:07:09', '0');
INSERT INTO `t_user` VALUES ('9', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-10 17:42:31', '2019-10-10 17:42:23', '0');
INSERT INTO `t_user` VALUES ('10', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-11 10:44:34', '2019-10-11 10:44:26', '0');
INSERT INTO `t_user` VALUES ('11', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-11 13:30:12', '2019-10-11 13:30:03', '0');
INSERT INTO `t_user` VALUES ('12', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-11 16:18:21', '2019-10-11 16:18:13', '0');
INSERT INTO `t_user` VALUES ('13', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-11 16:18:21', '2019-10-11 16:18:13', '0');
INSERT INTO `t_user` VALUES ('14', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-11 16:34:24', '2019-10-11 16:34:16', '0');
INSERT INTO `t_user` VALUES ('15', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-11 16:36:37', '2019-10-11 16:36:29', '0');
INSERT INTO `t_user` VALUES ('16', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-11 17:02:01', '2019-10-11 17:01:53', '0');
INSERT INTO `t_user` VALUES ('17', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-11 17:02:01', '2019-10-11 17:01:53', '0');
INSERT INTO `t_user` VALUES ('18', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-11 17:25:21', '2019-10-11 17:25:13', '0');
INSERT INTO `t_user` VALUES ('19', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-11 17:55:50', '2019-10-11 17:55:41', '0');
INSERT INTO `t_user` VALUES ('20', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-11 17:55:59', '2019-10-11 17:55:51', '0');
INSERT INTO `t_user` VALUES ('21', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-12 11:20:38', '2019-10-12 11:20:30', '0');
INSERT INTO `t_user` VALUES ('22', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-13 06:30:05', '2019-10-13 06:29:57', '0');
INSERT INTO `t_user` VALUES ('23', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-13 06:44:38', '2019-10-13 06:44:30', '0');
INSERT INTO `t_user` VALUES ('24', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-14 00:22:31', '2019-10-14 00:22:23', '0');
INSERT INTO `t_user` VALUES ('25', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-14 11:20:22', '2019-10-14 11:20:14', '0');
INSERT INTO `t_user` VALUES ('26', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-15 14:00:35', '2019-10-15 14:00:27', '0');
INSERT INTO `t_user` VALUES ('27', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-17 10:04:00', '2019-10-17 10:03:52', '0');
INSERT INTO `t_user` VALUES ('28', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-18 15:20:36', '2019-10-18 15:20:29', '0');
INSERT INTO `t_user` VALUES ('29', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-18 15:20:36', '2019-10-18 15:20:29', '0');
INSERT INTO `t_user` VALUES ('30', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-18 15:44:25', '2019-10-18 15:44:18', '0');
INSERT INTO `t_user` VALUES ('31', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-19 13:31:35', '2019-10-19 13:31:27', '0');
INSERT INTO `t_user` VALUES ('32', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-19 17:48:43', '2019-10-19 17:48:36', '0');
INSERT INTO `t_user` VALUES ('33', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-21 08:06:54', '2019-10-21 08:06:46', '0');
INSERT INTO `t_user` VALUES ('34', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-21 19:45:06', '2019-10-21 19:44:59', '0');
INSERT INTO `t_user` VALUES ('35', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-28 10:39:31', '2019-10-28 10:39:25', '0');
INSERT INTO `t_user` VALUES ('36', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-10-30 12:23:47', '2019-10-30 12:23:41', '0');
INSERT INTO `t_user` VALUES ('37', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-11-04 11:06:31', '2019-11-04 11:06:21', '0');
INSERT INTO `t_user` VALUES ('38', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-11-04 11:47:37', '2019-11-04 11:47:28', '0');
INSERT INTO `t_user` VALUES ('39', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-11-11 07:43:47', '2019-11-11 07:43:38', '0');
INSERT INTO `t_user` VALUES ('40', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-11-12 22:19:25', '2019-11-12 22:19:16', '0');
INSERT INTO `t_user` VALUES ('41', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-11-18 08:11:50', '2019-11-18 08:12:05', '0');
INSERT INTO `t_user` VALUES ('42', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-11-25 03:07:23', '2019-11-25 03:07:39', '0');
INSERT INTO `t_user` VALUES ('43', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-11-27 03:05:42', '2019-11-27 03:05:58', '0');
INSERT INTO `t_user` VALUES ('44', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-12-02 05:11:09', '2019-12-02 05:11:25', '0');
INSERT INTO `t_user` VALUES ('45', null, null, null, null, null, null, '0', null, null, null, '0', '0', '1', '2019-12-09 13:41:11', '2019-12-09 13:41:27', '0');

-- ----------------------------
-- Table structure for t_user_oauths
-- ----------------------------
DROP TABLE IF EXISTS `t_user_oauths`;
CREATE TABLE `t_user_oauths` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `oauth_id` varchar(128) NOT NULL COMMENT '授权id(比如: openid)',
  `union_id` varchar(128) DEFAULT NULL COMMENT 'unionid',
  `session_key` varchar(64) DEFAULT NULL COMMENT '微信小程序session_key',
  `status` varchar(1) NOT NULL DEFAULT '1' COMMENT '授权状态(0-取消授权, 1-已授权)',
  `oauth_type` varchar(1) NOT NULL COMMENT '授权类型(1-微信小程序, 2-微信公众号 or 微信开放平台, 3-QQ, 4-微博)',
  `oauth_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '授权时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `t_user_oauths_t_user_id_fk` (`user_id`),
  CONSTRAINT `t_user_oauths_t_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='用户授权登录表';

-- ----------------------------
-- Records of t_user_oauths
-- ----------------------------
INSERT INTO `t_user_oauths` VALUES ('1', '3', 'otXxp5JLzTHoW9D9a09GP_2jv_bk', null, 'jr4VXh147f17ulwsCUzz6g==', '1', '1', '2019-09-17 13:55:33', '2019-09-17 13:55:33');
INSERT INTO `t_user_oauths` VALUES ('2', '4', 'otXxp5NykBfEBg18lWcdvw_RbEJc', null, 'jbeWTmwBlDnJ8fHKtuoDOA==', '1', '1', '2019-09-17 14:45:46', '2019-09-17 14:45:46');
INSERT INTO `t_user_oauths` VALUES ('3', '5', 'otXxp5P1e0m8yY3S59ifY1IX0Psk', null, 'KGTs0WO23YOn/3GhQkeOaA==', '1', '1', '2019-10-09 16:05:57', '2019-10-09 16:05:57');
INSERT INTO `t_user_oauths` VALUES ('4', '6', 'otXxp5An-hpKAO92rAyIs5tO8u7o', null, '6cVZJcfAhO1w8FbawYEfgQ==', '1', '1', '2019-10-09 16:24:55', '2019-10-09 16:24:55');
INSERT INTO `t_user_oauths` VALUES ('5', '7', 'otXxp5Exc662Xno6jpXP0EQdR4sY', null, 'XRPGzHiML1iC6bAzGQxwig==', '1', '1', '2019-10-10 11:32:40', '2019-10-10 11:32:40');
INSERT INTO `t_user_oauths` VALUES ('6', '8', 'otXxp5CUWelA8KGRThixZd9yxjAY', null, 'gJrZUBTxDe1h+0yAlmIL0A==', '1', '1', '2019-10-10 13:07:09', '2019-10-10 13:07:09');
INSERT INTO `t_user_oauths` VALUES ('7', '9', 'otXxp5E9jqxmx8U8LqHhyuTO4SIo', null, '6yacnfafNSCt2YoLHulgZg==', '1', '1', '2019-10-10 17:42:23', '2019-10-10 17:42:23');
INSERT INTO `t_user_oauths` VALUES ('8', '10', 'otXxp5ADrdNDpORHIs126LdDHlU0', null, 'gAKA6siSq1odZtPTVAAsnw==', '1', '1', '2019-10-11 10:44:26', '2019-10-11 10:44:26');
INSERT INTO `t_user_oauths` VALUES ('9', '11', 'otXxp5Ddmo7hBeBEKIPxMF1goLTY', null, 'iC444vtRutgWGGQVQRetnw==', '1', '1', '2019-10-11 13:30:03', '2019-10-11 13:30:03');
INSERT INTO `t_user_oauths` VALUES ('10', '13', 'otXxp5MHndwshxVA2pBLXAMTqS28', null, '4hSmkAoL4wuvetXpAUgrQg==', '1', '1', '2019-10-11 16:18:13', '2019-10-11 16:18:13');
INSERT INTO `t_user_oauths` VALUES ('11', '12', 'otXxp5MHndwshxVA2pBLXAMTqS28', null, '4hSmkAoL4wuvetXpAUgrQg==', '1', '1', '2019-10-11 16:18:13', '2019-10-11 16:18:13');
INSERT INTO `t_user_oauths` VALUES ('12', '14', 'otXxp5GK3nwfYr5Z1kPG9HpTzUSI', null, 'e89cJ/b96WUyjgiLtIPglw==', '1', '1', '2019-10-11 16:34:16', '2019-10-11 16:34:16');
INSERT INTO `t_user_oauths` VALUES ('13', '15', 'otXxp5NA59Kb_hYr3efv3fph7nRg', null, 'llwxVLBVdT3+2KlKlmUHuA==', '1', '1', '2019-10-11 16:36:29', '2019-10-11 16:36:29');
INSERT INTO `t_user_oauths` VALUES ('14', '16', 'otXxp5NF7SpUY0ZkXPEZCndTzCiQ', null, 'K6nVJW4ToVJqZLUT/AJimQ==', '1', '1', '2019-10-11 17:01:53', '2019-10-11 17:01:53');
INSERT INTO `t_user_oauths` VALUES ('15', '17', 'otXxp5CUc-aYpHOlBTx7f3DbfSBI', null, 'MhxOiaUjYQQiK7+iv4ipzQ==', '1', '1', '2019-10-11 17:01:53', '2019-10-11 17:01:53');
INSERT INTO `t_user_oauths` VALUES ('16', '18', 'otXxp5E6Uc0jUzeb6hYMB40gibAw', null, 'XBBGpvs+mcGI/x7ABCBDBA==', '1', '1', '2019-10-11 17:25:13', '2019-10-11 17:25:13');
INSERT INTO `t_user_oauths` VALUES ('17', '19', 'otXxp5AIjivsvVVlJpMgcB1635HQ', null, 'RjjWQ6dXyXGIaBeUkYSQqg==', '1', '1', '2019-10-11 17:55:41', '2019-10-11 17:55:41');
INSERT INTO `t_user_oauths` VALUES ('18', '20', 'otXxp5MMpiv9EaqDyjbuk-xGhfTo', null, 'Vhqi/dwRd0hsJNGt0EY8DQ==', '1', '1', '2019-10-11 17:55:51', '2019-10-11 17:55:51');
INSERT INTO `t_user_oauths` VALUES ('19', '21', 'otXxp5C-U6lUvd7fnMA99i-qMOZI', null, 'ZNbmyE9kibKbFgFIK4TW0w==', '1', '1', '2019-10-12 11:20:30', '2019-10-12 11:20:30');
INSERT INTO `t_user_oauths` VALUES ('20', '22', 'otXxp5HO0SBlxAF11lnlr8YvzXTo', null, 'pV/EWkxfwZ/FOGfvBJLQeg==', '1', '1', '2019-10-13 06:29:57', '2019-10-13 06:29:57');
INSERT INTO `t_user_oauths` VALUES ('21', '23', 'otXxp5Gql7-vmNTjFOCDOn7aRkbI', null, 'BezcJvRlslLIuekPdhhFyg==', '1', '1', '2019-10-13 06:44:30', '2019-10-13 06:44:30');
INSERT INTO `t_user_oauths` VALUES ('22', '24', 'otXxp5LTb8ld6779tKnl26jQctbs', null, '3/rZY+l80rp+2hthm75XPg==', '1', '1', '2019-10-14 00:22:23', '2019-10-14 00:22:23');
INSERT INTO `t_user_oauths` VALUES ('23', '25', 'otXxp5EM-gnih3nXpHEnr_4Ogr60', null, 'BWqMvF7FyHPNezc0E57Mqg==', '1', '1', '2019-10-14 11:20:14', '2019-10-14 11:20:14');
INSERT INTO `t_user_oauths` VALUES ('24', '26', 'otXxp5IgUTTt7a0h_hcOSDghkPN4', null, 'ybJYDlpMSomOtytuC5ztgg==', '1', '1', '2019-10-15 14:00:27', '2019-10-15 14:00:27');
INSERT INTO `t_user_oauths` VALUES ('25', '27', 'otXxp5Miud-Av3gKRQyxbKbJlFew', null, 'gOmYQsCxoijMqAtP9pm0+A==', '1', '1', '2019-10-17 10:03:52', '2019-10-17 10:03:52');
INSERT INTO `t_user_oauths` VALUES ('26', '28', 'otXxp5PdAUgDJjH9TY6B5jbBz6xY', null, 'mfkBrS8ZoMdlblTV721hig==', '1', '1', '2019-10-18 15:20:29', '2019-10-18 15:20:29');
INSERT INTO `t_user_oauths` VALUES ('27', '29', 'otXxp5Of6cr5v8mVZ80hbVfV43bI', null, 'YOJTo4EmHoZLE6173fbPOw==', '1', '1', '2019-10-18 15:20:29', '2019-10-18 15:20:29');
INSERT INTO `t_user_oauths` VALUES ('28', '30', 'otXxp5F8Plh6oLHl0_G1MFTohPHc', null, 'YH4X/0vAydsf9tePQRabQQ==', '1', '1', '2019-10-18 15:44:18', '2019-10-18 15:44:18');
INSERT INTO `t_user_oauths` VALUES ('29', '31', 'otXxp5LYQ5V33XygCHSCEXXXSIgs', null, 'YijmO+HWYGDtxnSG2bdCoQ==', '1', '1', '2019-10-19 13:31:27', '2019-10-19 13:31:27');
INSERT INTO `t_user_oauths` VALUES ('30', '32', 'otXxp5LpvVCPYW-AMqyN0sUmZASw', null, 'K2jn4IaGLwXtOiW4nIw6lQ==', '1', '1', '2019-10-19 17:48:36', '2019-10-19 17:48:36');
INSERT INTO `t_user_oauths` VALUES ('31', '33', 'otXxp5Ln0Rl3NUqousScp55WMRC8', null, '7sBjeGU16PDXnuSgeaSEqg==', '1', '1', '2019-10-21 08:06:46', '2019-10-21 08:06:46');
INSERT INTO `t_user_oauths` VALUES ('32', '34', 'otXxp5Om6x5yFKYfCAmg5AClvfas', null, '8DLBIzBkgry4a05vot78Vg==', '1', '1', '2019-10-21 19:44:59', '2019-10-21 19:44:59');
INSERT INTO `t_user_oauths` VALUES ('33', '35', 'otXxp5Me1bWqRkOhKeIe8lN0faPQ', null, 'cUDCztVfIx+rSxsX1l6Uqw==', '1', '1', '2019-10-28 10:39:25', '2019-10-28 10:39:25');
INSERT INTO `t_user_oauths` VALUES ('34', '36', 'otXxp5FUoIRP56eosrRvWcngdow8', null, 'Rpp9bZV8ESL891MFrAVC0w==', '1', '1', '2019-10-30 12:23:41', '2019-10-30 12:23:41');
INSERT INTO `t_user_oauths` VALUES ('35', '37', 'otXxp5ETdczr6QjONejzCCQ3ENO4', null, 'e57XcdsLg+g/X2tYKEnG7w==', '1', '1', '2019-11-04 11:06:21', '2019-11-04 11:06:21');
INSERT INTO `t_user_oauths` VALUES ('36', '38', 'otXxp5JuSrWprdBCmpDUqrKYvc0E', null, 'VDXizyjrDn0WtRb3LB0REw==', '1', '1', '2019-11-04 11:47:28', '2019-11-04 11:47:28');
INSERT INTO `t_user_oauths` VALUES ('37', '39', 'otXxp5EojidLPbZNDJQQLicz4ma0', null, 'v5p1MpRzsTTtxOrPnzxTPw==', '1', '1', '2019-11-11 07:43:38', '2019-11-11 07:43:38');
INSERT INTO `t_user_oauths` VALUES ('38', '40', 'otXxp5IjMGNcx5txUB29gFfwiCmI', null, 'WxytcOEqHvpREHL/HyiGMw==', '1', '1', '2019-11-12 22:19:16', '2019-11-12 22:19:16');
INSERT INTO `t_user_oauths` VALUES ('39', '41', 'otXxp5Dk979bOO7jb0IcBsbu1cW0', null, 'f3qhXNeHV0I1bsqRg1vOkg==', '1', '1', '2019-11-18 08:12:05', '2019-11-18 08:12:05');
INSERT INTO `t_user_oauths` VALUES ('40', '42', 'otXxp5GcDCHiHZmj0-wYoZyV64LE', null, 'eLeEmSqwkkIVrV4Z7jzLfA==', '1', '1', '2019-11-25 03:07:39', '2019-11-25 03:07:39');
INSERT INTO `t_user_oauths` VALUES ('41', '43', 'otXxp5MRdodAY0p4tdraz0LLNnz8', null, '/yxiLprS2rCiON+muAfGog==', '1', '1', '2019-11-27 03:05:58', '2019-11-27 03:05:58');
INSERT INTO `t_user_oauths` VALUES ('42', '44', 'otXxp5DlYOxwqLNUpUEPWoCZwNMA', null, 'z+yo1aimLKjw8cfW7erMYA==', '1', '1', '2019-12-02 05:11:25', '2019-12-02 05:11:25');
INSERT INTO `t_user_oauths` VALUES ('43', '45', 'otXxp5NEP_b9_rPUxBTmtQ5pExvY', null, '8i5OYMIbrKgb4CXTRv3lpg==', '1', '1', '2019-12-09 13:41:27', '2019-12-09 13:41:27');

-- ----------------------------
-- Table structure for t_user_sign_log
-- ----------------------------
DROP TABLE IF EXISTS `t_user_sign_log`;
CREATE TABLE `t_user_sign_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `token` varchar(64) NOT NULL COMMENT 'token',
  `sign_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登陆时间',
  `expiration_time` datetime NOT NULL COMMENT '过期时间(时间戳)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_sign_log_token_uindex` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 COMMENT='用户登录日志表';

-- ----------------------------
-- Records of t_user_sign_log
-- ----------------------------
INSERT INTO `t_user_sign_log` VALUES ('1', '2', 'fdbc134d_bd70_41c2_acf1_0921182cece2', '2019-09-17 10:38:53', '2087-10-05 13:53:10');
INSERT INTO `t_user_sign_log` VALUES ('2', '2', '4f949d77_e6a0_44d4_9cd5_fb15281d6f47', '2019-09-17 10:39:00', '2087-10-05 13:53:17');
INSERT INTO `t_user_sign_log` VALUES ('3', '2', '111e9bc4_3f76_4432_9858_d87992fa4952', '2019-09-17 10:51:52', '2087-10-05 14:06:09');
INSERT INTO `t_user_sign_log` VALUES ('4', '2', 'a0b3119b_1519_4f00_afd3_4b34f6271a95', '2019-09-17 11:03:17', '2087-10-05 14:17:34');
INSERT INTO `t_user_sign_log` VALUES ('5', '3', '233acc9c_aaaf_4dc0_9e71_42e851d6f95d', '2019-09-17 13:55:33', '2019-09-18 13:55:43');
INSERT INTO `t_user_sign_log` VALUES ('6', '3', '6f5d7915_d3f7_49bb_94a8_80d9e74509ab', '2019-09-17 14:01:02', '2019-09-18 14:01:12');
INSERT INTO `t_user_sign_log` VALUES ('7', '3', '8b79cdcf_8834_466e_8552_cb4d88c62d7f', '2019-09-17 14:04:53', '2019-09-18 14:05:03');
INSERT INTO `t_user_sign_log` VALUES ('8', '3', 'f7ebe28d_f6cb_470b_bc72_1749fcdbf68e', '2019-09-17 14:05:11', '2019-09-18 14:05:21');
INSERT INTO `t_user_sign_log` VALUES ('9', '3', '8e64c5d7_3525_4db6_851f_4c9d198b1ccd', '2019-09-17 14:09:55', '2019-09-18 14:10:04');
INSERT INTO `t_user_sign_log` VALUES ('10', '3', '54c5a16b_5621_4d3f_beab_c54a4669c973', '2019-09-17 14:10:05', '2019-09-18 14:10:15');
INSERT INTO `t_user_sign_log` VALUES ('11', '2', '62357edd_29ef_4194_ae1a_2b26b86c4dc7', '2019-09-17 14:26:09', '2087-10-05 17:40:26');
INSERT INTO `t_user_sign_log` VALUES ('12', '4', '0f1abd36_d934_4094_8df1_30b78ae24de8', '2019-09-17 14:45:46', '2019-09-18 14:45:56');
INSERT INTO `t_user_sign_log` VALUES ('13', '4', 'fea62fe5_2a1a_4da9_a709_4224a1ef486a', '2019-09-17 14:48:57', '2087-10-05 18:03:14');
INSERT INTO `t_user_sign_log` VALUES ('14', '3', 'c630fd19_4e8f_4fe7_8d74_54f31ddb4dd8', '2019-09-20 09:44:49', '2019-09-21 09:44:59');
INSERT INTO `t_user_sign_log` VALUES ('15', '3', '6fcb3162_8d9a_4c55_8bc3_8573a843ff20', '2019-10-09 16:03:00', '2019-10-10 16:03:08');
INSERT INTO `t_user_sign_log` VALUES ('16', '3', '99fb5f32_4746_47bb_a5f9_3f1281fe78f0', '2019-10-09 16:05:17', '2019-10-10 16:05:25');
INSERT INTO `t_user_sign_log` VALUES ('17', '5', 'd153b191_c4ef_4108_a232_52e8b5a2b730', '2019-10-09 16:05:57', '2019-10-10 16:06:05');
INSERT INTO `t_user_sign_log` VALUES ('18', '3', 'd010a116_3828_42fe_8d12_f5e3a14d53b6', '2019-10-09 16:24:17', '2019-10-10 16:24:25');
INSERT INTO `t_user_sign_log` VALUES ('19', '6', '1cd98b7e_1db2_40f3_9641_21c23126eaa6', '2019-10-09 16:24:55', '2019-10-10 16:25:03');
INSERT INTO `t_user_sign_log` VALUES ('20', '3', '9369a6e7_e07c_4f96_831e_a1250c0c227e', '2019-10-10 09:05:10', '2019-10-11 09:05:18');
INSERT INTO `t_user_sign_log` VALUES ('21', '3', 'ccb638dd_7638_418a_a016_dbfd6fc29029', '2019-10-10 11:29:37', '2019-10-11 11:29:45');
INSERT INTO `t_user_sign_log` VALUES ('22', '7', '415e8aaf_3ee5_436d_b975_30d0b1ec98ef', '2019-10-10 11:32:40', '2019-10-11 11:32:48');
INSERT INTO `t_user_sign_log` VALUES ('23', '8', '48f6b43f_e3dc_4fb1_9be6_ed76f18c21b6', '2019-10-10 13:07:09', '2019-10-11 13:07:17');
INSERT INTO `t_user_sign_log` VALUES ('24', '3', '430303da_de89_4eba_ba0d_738e21779ad9', '2019-10-10 17:33:12', '2019-10-11 17:33:21');
INSERT INTO `t_user_sign_log` VALUES ('25', '9', '4bc1a8fb_ebeb_43c8_8303_53b3ded7b703', '2019-10-10 17:42:23', '2019-10-11 17:42:31');
INSERT INTO `t_user_sign_log` VALUES ('26', '3', '6440e321_e991_49b3_8c1e_5e7db4dd4bcf', '2019-10-10 17:45:10', '2019-10-11 17:45:18');
INSERT INTO `t_user_sign_log` VALUES ('27', '4', '89ee041f_06b7_46b3_97c9_732fe6d6457d', '2019-10-11 10:40:28', '2019-10-12 10:40:36');
INSERT INTO `t_user_sign_log` VALUES ('28', '10', 'eb680d87_c16b_41a1_ae56_1d375d4c6eb5', '2019-10-11 10:44:26', '2019-10-12 10:44:34');
INSERT INTO `t_user_sign_log` VALUES ('29', '2', 'b11c7720_8214_40d5_9b8c_7b5cda2a2c7b', '2019-10-11 10:59:45', '2087-10-29 14:14:00');
INSERT INTO `t_user_sign_log` VALUES ('30', '11', 'c5d6bc01_f236_404f_af75_2ccfd76422c2', '2019-10-11 13:30:03', '2019-10-12 13:30:12');
INSERT INTO `t_user_sign_log` VALUES ('31', '10', 'c6f92e3a_82a3_4db3_8240_d207d22969e7', '2019-10-11 15:23:43', '2019-10-12 15:23:51');
INSERT INTO `t_user_sign_log` VALUES ('32', '10', '9babbf90_5a3e_4445_84fd_c40acd625c16', '2019-10-11 15:52:29', '2019-10-12 15:52:37');
INSERT INTO `t_user_sign_log` VALUES ('33', '4', 'f749a21a_48df_49cc_adac_748cd6d8c0dc', '2019-10-11 15:56:06', '2019-10-12 15:56:14');
INSERT INTO `t_user_sign_log` VALUES ('34', '13', '9d0f91bf_2847_43bb_b587_506779f1ba0d', '2019-10-11 16:18:13', '2019-10-12 16:18:21');
INSERT INTO `t_user_sign_log` VALUES ('35', '12', '9f45a5af_54bc_4908_be34_7f338598a8e0', '2019-10-11 16:18:13', '2019-10-12 16:18:21');
INSERT INTO `t_user_sign_log` VALUES ('36', '14', '9f9752f6_d768_4bbc_88ed_dbbdcee32fea', '2019-10-11 16:34:16', '2019-10-12 16:34:24');
INSERT INTO `t_user_sign_log` VALUES ('37', '15', '351019c3_d380_439d_b8d6_f9463eb9db4c', '2019-10-11 16:36:29', '2019-10-12 16:36:37');
INSERT INTO `t_user_sign_log` VALUES ('38', '3', '898dad15_5a56_4bcc_b089_e1569bc172b2', '2019-10-11 16:40:50', '2019-10-12 16:40:58');
INSERT INTO `t_user_sign_log` VALUES ('39', '16', '845e8ab2_9d6f_4d13_bdfb_e89add5cc543', '2019-10-11 17:01:53', '2019-10-12 17:02:01');
INSERT INTO `t_user_sign_log` VALUES ('40', '17', 'c555f502_68d8_4022_979c_966ff59fe7cc', '2019-10-11 17:01:53', '2019-10-12 17:02:01');
INSERT INTO `t_user_sign_log` VALUES ('41', '18', '61e99fcf_9fb2_42f7_840a_a88116ada2aa', '2019-10-11 17:25:13', '2019-10-12 17:25:21');
INSERT INTO `t_user_sign_log` VALUES ('42', '19', 'abf9e88c_516e_4e99_a1df_a1469b982e00', '2019-10-11 17:55:41', '2019-10-12 17:55:50');
INSERT INTO `t_user_sign_log` VALUES ('43', '20', 'a1fc19b6_df71_47ba_8022_e5ec340d6825', '2019-10-11 17:55:51', '2019-10-12 17:55:59');
INSERT INTO `t_user_sign_log` VALUES ('44', '3', 'f863cc03_83c1_4e75_8441_baa1c434e36e', '2019-10-12 10:30:59', '2019-10-13 10:31:07');
INSERT INTO `t_user_sign_log` VALUES ('45', '21', '72deb4eb_ad20_4df7_8a48_87f0e48f28c3', '2019-10-12 11:20:30', '2019-10-13 11:20:38');
INSERT INTO `t_user_sign_log` VALUES ('46', '9', '512e4c5c_ad28_47a8_b079_9d4f995a6f60', '2019-10-12 14:21:40', '2019-10-13 14:21:49');
INSERT INTO `t_user_sign_log` VALUES ('47', '22', '9c810f42_f1fa_4b25_8dbe_d8b6ef9c6da5', '2019-10-13 06:29:57', '2019-10-14 06:30:05');
INSERT INTO `t_user_sign_log` VALUES ('48', '23', 'df560af0_b022_4dae_9bce_15d785f43d94', '2019-10-13 06:44:30', '2019-10-14 06:44:38');
INSERT INTO `t_user_sign_log` VALUES ('49', '24', '1bab25b3_6bb3_4897_bf33_00e737d4939d', '2019-10-14 00:22:23', '2019-10-15 00:22:31');
INSERT INTO `t_user_sign_log` VALUES ('50', '25', 'c3a68620_b9f1_462f_939d_8c88a63bba40', '2019-10-14 11:20:14', '2019-10-15 11:20:22');
INSERT INTO `t_user_sign_log` VALUES ('51', '23', '67bc5c2a_59be_44cb_a197_5e675592ecdf', '2019-10-14 21:13:00', '2019-10-15 21:13:08');
INSERT INTO `t_user_sign_log` VALUES ('52', '9', 'e364580f_d61c_4f8c_b7fb_ac3d095193b0', '2019-10-14 21:16:52', '2019-10-15 21:17:00');
INSERT INTO `t_user_sign_log` VALUES ('53', '4', '8dd7523e_28bc_4e5e_a823_b93f7d02e78d', '2019-10-14 21:19:01', '2019-10-15 21:19:09');
INSERT INTO `t_user_sign_log` VALUES ('54', '26', '3ca0c827_939f_4fca_a2f8_a19dab1bc914', '2019-10-15 14:00:27', '2019-10-16 14:00:35');
INSERT INTO `t_user_sign_log` VALUES ('55', '3', '6a1be97e_07a5_485c_8025_fad72e8c0304', '2019-10-16 17:35:04', '2019-10-17 17:35:12');
INSERT INTO `t_user_sign_log` VALUES ('56', '4', 'fefc1f74_7a4d_4370_81b9_26ad8ac8ade5', '2019-10-17 09:55:01', '2019-10-18 09:55:09');
INSERT INTO `t_user_sign_log` VALUES ('57', '4', '4ee45acd_3e1d_4021_8cfa_a66100081c62', '2019-10-17 09:58:03', '2019-10-18 09:58:11');
INSERT INTO `t_user_sign_log` VALUES ('58', '4', '55fa9468_d876_445e_b72a_9b2603fb0f3b', '2019-10-17 10:02:18', '2019-10-18 10:02:26');
INSERT INTO `t_user_sign_log` VALUES ('59', '27', '8ae871dc_1934_4019_8231_cafb53321b24', '2019-10-17 10:03:52', '2019-10-18 10:04:00');
INSERT INTO `t_user_sign_log` VALUES ('60', '4', '47294669_77ac_4db4_8399_52c63cb5eb8b', '2019-10-17 10:13:18', '2019-10-18 10:13:26');
INSERT INTO `t_user_sign_log` VALUES ('61', '2', '44edbf13_d529_4f8b_abec_387fc0071f5f', '2019-10-17 10:45:29', '2087-11-04 13:59:44');
INSERT INTO `t_user_sign_log` VALUES ('62', '3', '165ca6a2_44ce_4843_985a_d3d8d58d9514', '2019-10-17 10:47:14', '2019-10-18 10:47:21');
INSERT INTO `t_user_sign_log` VALUES ('63', '9', 'a785f0a2_49f8_4f8a_be71_e56fb5c708c0', '2019-10-17 10:57:18', '2019-10-18 10:57:26');
INSERT INTO `t_user_sign_log` VALUES ('64', '3', '9da7e34f_56ac_402e_a295_393f20e6dc2d', '2019-10-17 10:59:14', '2019-10-18 10:59:22');
INSERT INTO `t_user_sign_log` VALUES ('65', '4', 'accf82a5_b1aa_4248_b26b_c9a7be41a42a', '2019-10-17 11:00:20', '2019-10-18 11:00:28');
INSERT INTO `t_user_sign_log` VALUES ('66', '3', 'aa8081cf_8352_4c32_b640_9e2d6aa72549', '2019-10-17 11:01:02', '2019-10-18 11:01:10');
INSERT INTO `t_user_sign_log` VALUES ('67', '3', '799ec5b7_c8a6_40c1_8d26_0bf16b288049', '2019-10-17 11:02:10', '2019-10-18 11:02:17');
INSERT INTO `t_user_sign_log` VALUES ('68', '3', '7aae9479_4e29_4ce7_b0b9_949cb9ec8675', '2019-10-17 11:03:11', '2019-10-18 11:03:18');
INSERT INTO `t_user_sign_log` VALUES ('69', '3', 'a27f5347_467d_4c70_b793_5655ffac9eb8', '2019-10-17 11:03:26', '2019-10-18 11:03:33');
INSERT INTO `t_user_sign_log` VALUES ('70', '3', '1d2a5f96_ba09_4317_9bc2_df29f78fd113', '2019-10-17 11:03:44', '2019-10-18 11:03:51');
INSERT INTO `t_user_sign_log` VALUES ('71', '3', 'bcd3b5b4_9f38_44a1_99c5_b2eec85a2421', '2019-10-17 11:08:59', '2019-10-18 11:09:07');
INSERT INTO `t_user_sign_log` VALUES ('72', '3', '522a6b5b_b56e_4d98_b37f_910a390ddb8d', '2019-10-17 11:09:42', '2019-10-18 11:09:50');
INSERT INTO `t_user_sign_log` VALUES ('73', '4', '00372111_6ffb_48ab_a965_59ea7a64cbb5', '2019-10-17 11:11:59', '2019-10-18 11:12:07');
INSERT INTO `t_user_sign_log` VALUES ('74', '3', '39cc0073_93e4_4a09_8645_179930897e18', '2019-10-17 11:12:35', '2019-10-18 11:12:42');
INSERT INTO `t_user_sign_log` VALUES ('75', '3', '02b20804_e099_446f_ae7e_6e5a4d912520', '2019-10-17 11:13:11', '2019-10-18 11:13:19');
INSERT INTO `t_user_sign_log` VALUES ('76', '3', 'eb9239b5_4dab_4307_8d1d_1ea38d1ee6e2', '2019-10-17 11:14:55', '2019-10-18 11:15:03');
INSERT INTO `t_user_sign_log` VALUES ('77', '3', '74ef8828_169d_4ddc_8fee_413238e0e4fa', '2019-10-17 11:18:26', '2019-10-18 11:18:33');
INSERT INTO `t_user_sign_log` VALUES ('78', '3', '2d18e823_889b_4ddf_9bbc_f1110da04134', '2019-10-17 11:18:49', '2019-10-18 11:18:57');
INSERT INTO `t_user_sign_log` VALUES ('79', '3', '49f6ae5c_8d9b_4001_8ba7_c9e138c59126', '2019-10-17 11:22:39', '2019-10-18 11:22:47');
INSERT INTO `t_user_sign_log` VALUES ('80', '3', '592e7f1c_41e4_4d13_915f_aaa1085a1666', '2019-10-17 11:23:04', '2019-10-18 11:23:12');
INSERT INTO `t_user_sign_log` VALUES ('81', '2', '0a47e073_407b_4f03_a445_f2c29579df62', '2019-10-17 14:29:59', '2087-11-04 17:44:13');
INSERT INTO `t_user_sign_log` VALUES ('82', '9', 'a19b5b51_7af3_4a7f_80de_959348cfe522', '2019-10-18 11:47:39', '2019-10-19 11:47:47');
INSERT INTO `t_user_sign_log` VALUES ('83', '28', 'af957188_ec1b_4226_ad4d_46e94f659de4', '2019-10-18 15:20:29', '2019-10-19 15:20:36');
INSERT INTO `t_user_sign_log` VALUES ('84', '29', '4b1e654f_e860_4c64_83df_9fbd9d4780d0', '2019-10-18 15:20:29', '2019-10-19 15:20:36');
INSERT INTO `t_user_sign_log` VALUES ('85', '30', 'a9fd2a30_9054_49e2_a734_2dbd2ac0b574', '2019-10-18 15:44:18', '2019-10-19 15:44:25');
INSERT INTO `t_user_sign_log` VALUES ('86', '31', '5689b4d1_9475_4e94_a2c2_c2232d375b58', '2019-10-19 13:31:27', '2019-10-20 13:31:35');
INSERT INTO `t_user_sign_log` VALUES ('87', '32', '16e698d1_cd7f_49d0_99a6_7394610ff81b', '2019-10-19 17:48:36', '2019-10-20 17:48:43');
INSERT INTO `t_user_sign_log` VALUES ('88', '3', '5fa106cd_8ff0_4eac_a724_026d8c6f86d1', '2019-10-19 20:10:02', '2019-10-20 20:10:10');
INSERT INTO `t_user_sign_log` VALUES ('89', '3', 'c7137a75_40d4_40ed_8ad4_c95046540015', '2019-10-20 12:58:58', '2019-10-21 12:59:06');
INSERT INTO `t_user_sign_log` VALUES ('90', '9', '398d6dd5_a791_4390_bc66_c755d8d9faa7', '2019-10-20 14:07:13', '2019-10-21 14:07:21');
INSERT INTO `t_user_sign_log` VALUES ('91', '33', '766454af_a83e_4ab7_a9cd_f48cbd959630', '2019-10-21 08:06:46', '2019-10-22 08:06:54');
INSERT INTO `t_user_sign_log` VALUES ('92', '23', '004128d0_031d_4842_85c6_6a21ac2a5753', '2019-10-21 15:53:38', '2019-10-22 15:53:45');
INSERT INTO `t_user_sign_log` VALUES ('93', '34', '32ce4e18_20cf_46b2_996c_e2b0931122cd', '2019-10-21 19:44:59', '2019-10-22 19:45:06');
INSERT INTO `t_user_sign_log` VALUES ('94', '3', '089a0093_e183_440e_9c23_928a66770589', '2019-10-26 21:16:14', '2019-10-27 21:16:21');
INSERT INTO `t_user_sign_log` VALUES ('95', '32', '12d4262f_2755_4462_a71f_ffaa43f466be', '2019-10-27 15:41:57', '2019-10-28 15:42:03');
INSERT INTO `t_user_sign_log` VALUES ('96', '4', '36459fa6_4cca_41b1_ac0f_53a621783a0a', '2019-10-28 08:31:20', '2019-10-29 08:31:27');
INSERT INTO `t_user_sign_log` VALUES ('97', '35', '17009000_9072_4605_a337_74e3a56889b2', '2019-10-28 10:39:25', '2019-10-29 10:39:31');
INSERT INTO `t_user_sign_log` VALUES ('98', '3', 'e2d6ad71_569b_4f1c_82d1_3ba4621e235d', '2019-10-29 14:43:01', '2019-10-30 14:43:08');
INSERT INTO `t_user_sign_log` VALUES ('99', '3', '13fa067f_02fb_47f1_9a41_31221d3a517d', '2019-10-30 10:17:41', '2019-10-31 10:17:48');
INSERT INTO `t_user_sign_log` VALUES ('100', '36', '229d7846_fb62_445c_b42b_4838b166ca4b', '2019-10-30 12:23:41', '2019-10-31 12:23:47');
INSERT INTO `t_user_sign_log` VALUES ('101', '37', '1463a51d_c194_4659_9670_d41a26009976', '2019-11-04 11:06:21', '2019-11-05 11:06:31');
INSERT INTO `t_user_sign_log` VALUES ('102', '38', 'b4c8a137_8de2_46ce_98a5_4a7c9508fd1f', '2019-11-04 11:47:28', '2019-11-05 11:47:37');
INSERT INTO `t_user_sign_log` VALUES ('103', '37', '3783755c_9024_4e96_a860_f0f2bb9e1976', '2019-11-09 11:41:10', '2019-11-10 11:41:19');
INSERT INTO `t_user_sign_log` VALUES ('104', '39', '04a95092_7cfe_486f_8347_ef7fd4ff3f12', '2019-11-11 07:43:38', '2019-11-12 07:43:47');
INSERT INTO `t_user_sign_log` VALUES ('105', '40', '7aca4d4f_518a_4739_b576_be2c6571ab56', '2019-11-12 22:19:16', '2019-11-13 22:19:25');
INSERT INTO `t_user_sign_log` VALUES ('106', '3', 'caa5a83a_0451_49af_a588_8b644fdd8d22', '2019-11-16 14:21:02', '2019-11-17 14:21:10');
INSERT INTO `t_user_sign_log` VALUES ('107', '41', '315ae490_7507_4858_803d_797565f8d1fd', '2019-11-18 08:12:05', '2019-11-19 08:11:50');
INSERT INTO `t_user_sign_log` VALUES ('108', '3', '6ce6ed90_b6f8_47a6_ad39_0d8c224ae4da', '2019-11-20 17:50:34', '2019-11-21 17:50:19');
INSERT INTO `t_user_sign_log` VALUES ('109', '3', '4b40be07_1fdb_48f7_bb8a_1d16e635de85', '2019-11-24 19:01:30', '2019-11-25 19:01:15');
INSERT INTO `t_user_sign_log` VALUES ('110', '42', 'b9e0b0f4_69a1_44ae_a47d_733456dba386', '2019-11-25 03:07:39', '2019-11-26 03:07:23');
INSERT INTO `t_user_sign_log` VALUES ('111', '43', '039ca4b6_2932_4312_98b5_969f6858a85c', '2019-11-27 03:05:58', '2019-11-28 03:05:42');
INSERT INTO `t_user_sign_log` VALUES ('112', '3', 'cb1ce1fc_67b4_45a2_93f1_2e5d5dfe70e0', '2019-12-01 00:37:29', '2019-12-02 00:37:13');
INSERT INTO `t_user_sign_log` VALUES ('113', '44', '1cf76065_b79b_4c3a_ac32_a8710f53f9a2', '2019-12-02 05:11:25', '2019-12-03 05:11:09');
INSERT INTO `t_user_sign_log` VALUES ('114', '45', '68d20f5d_f1e6_4c42_a7a0_c719829c99bd', '2019-12-09 13:41:27', '2019-12-10 13:41:11');
INSERT INTO `t_user_sign_log` VALUES ('115', '4', 'b9176b77_b075_4380_9b3f_3b746aecb619', '2020-01-13 14:36:22', '2020-01-14 14:36:50');

-- ----------------------------
-- Table structure for t_wx_oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_oauth_access_token`;
CREATE TABLE `t_wx_oauth_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `access_token` varchar(128) NOT NULL COMMENT 'access_token',
  `expires_in` bigint(20) NOT NULL COMMENT 'access_token接口调用凭证超时时间，单位（秒）',
  `expires_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '凭证过期时间',
  `refresh_token` varchar(128) NOT NULL COMMENT '用户刷新access_token',
  `openid` varchar(128) DEFAULT NULL COMMENT '用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的openid',
  `scope` varchar(64) DEFAULT NULL COMMENT '用户授权的作用域，使用逗号（,）分隔',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refresh_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '刷新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_wx_oauth_access_token_access_token_uindex` (`access_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网页授权记录表';

-- ----------------------------
-- Records of t_wx_oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for t_wx_snsapi_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_snsapi_userinfo`;
CREATE TABLE `t_wx_snsapi_userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT ' 自增id',
  `openid` varchar(128) NOT NULL COMMENT '用户的唯一标识',
  `nickname` varchar(64) NOT NULL COMMENT '用户昵称',
  `sex` varchar(1) DEFAULT NULL COMMENT '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
  `country` varchar(64) DEFAULT NULL COMMENT '国家，如中国为CN',
  `province` varchar(64) DEFAULT NULL COMMENT '用户个人资料填写的省份',
  `city` varchar(64) DEFAULT NULL COMMENT '普通用户个人资料填写的城市',
  `headimgurl` varchar(512) DEFAULT NULL COMMENT '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
  `privilege` varchar(128) DEFAULT NULL COMMENT '用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）',
  `unionid` varchar(128) DEFAULT NULL COMMENT '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_wx_snsapi_userinfo_openid_uindex` (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信用户信息';

-- ----------------------------
-- Records of t_wx_snsapi_userinfo
-- ----------------------------

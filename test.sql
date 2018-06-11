/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-09 16:31:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'chenyuan', '9B65CE38D6F53BBE7F809789D761210885A4CE52', '1', null, '2018-05-21 20:16:57');
INSERT INTO `t_admin` VALUES ('2', 'pander', '9B65CE38D6F53BBE7F809789D761210885A4CE52', '1', null, '2018-05-21 20:16:59');

-- ----------------------------
-- Table structure for t_document
-- ----------------------------
DROP TABLE IF EXISTS `t_document`;
CREATE TABLE `t_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `author` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0 审核中 1 审核通过 2 审核未过',
  `url` varchar(255) DEFAULT NULL,
  `user_id` char(10) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `download_num` int(11) DEFAULT NULL COMMENT '下载量',
  `read_num` int(11) DEFAULT NULL COMMENT '浏览量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_document
-- ----------------------------
INSERT INTO `t_document` VALUES ('1', '附件1.承诺书', 'F:\\word_project\\1525426885627_附件1.承诺书.doc', '', '1', 'doc-ieksjnng4zz0zz9', '1', 'chenyuan', '12', '108', '2018-05-21 19:22:22');
INSERT INTO `t_document` VALUES ('2', '附件2-指导卡', 'F:\\word_project\\1525426885627_附件2-指导卡.doc', '', '0', 'doc-ieksjntk65x0mkk', '1', 'chenyuan', '34', '345', '2018-05-21 19:23:31');
INSERT INTO `t_document` VALUES ('3', '附件3.指导教师评语及成绩表', 'F:\\word_project\\1525426885627_附件3.指导教师评语及成绩表.doc', '', '0', 'doc-ieksjnz3ds57u5c', '1', 'chenyuan', '15', '235', '2018-05-21 19:23:43');
INSERT INTO `t_document` VALUES ('4', '附件5.评阅人评语及成绩告', 'F:\\word_project\\1525426885627_附件5.评阅人评语及成绩.doc', '', '0', 'doc-ieksjp4fcb7k4s1', '1', 'chenyuan', '566', '123432', '2018-05-21 19:23:56');
INSERT INTO `t_document` VALUES ('5', '附件6.答辩记录', 'F:\\word_project\\1525426885627_附件6.答辩记录.doc', null, '1', 'doc-ieksjp9aghfwzey', '1', 'cehnyuan', '23', '2345', '2018-05-21 19:24:03');
INSERT INTO `t_document` VALUES ('6', '附件7.答辩委员会评语', 'F:\\word_project\\1525426885627_附件7.答辩委员会评语.doc', null, '0', 'doc-ieksjpevtdfgd4e', '2', 'jason', '1', '2', '2018-05-21 19:33:05');
INSERT INTO `t_document` VALUES ('7', '附件8.评分汇总表及成绩', 'F:\\word_project\\1525426885627_附件8.评分汇总表及成绩.doc', null, '0', 'doc-ieksjpjzbjs7pt4', '2', 'jason', '1', '2', '2018-05-21 19:33:08');
INSERT INTO `t_document` VALUES ('8', '开题报告_终稿', 'F:\\word_project\\1525426885627_开题报告_终稿.doc', null, '0', 'doc-ieksjpzdsfj05p3', '3', 'jack', '2', '2', '2018-05-21 19:33:10');
INSERT INTO `t_document` VALUES ('9', '论文', 'F:\\word_project\\1525426885627_论文.doc', null, '0', 'doc-ieksk0mc95bnfc4', '3', 'jack', '3', '3', '2018-05-21 19:33:14');
INSERT INTO `t_document` VALUES ('10', '任务书_终稿', 'F:\\word_project\\1525426885627_任务书_终稿.doc', null, '1', 'doc-ieksk4nvtg532ds', '4', 'cherry', '4', '3', '2018-05-21 19:33:26');
INSERT INTO `t_document` VALUES ('11', '数据库高级编程大作业', 'F:\\word_project\\1525426885627_数据库高级编程大作业.doc', null, '0', 'doc-ieksk4z1axug64r', '4', 'cherry', '4', '5', '2018-05-21 19:33:22');
INSERT INTO `t_document` VALUES ('12', '外文翻译_终稿', 'F:\\word_project\\1525426885627_外文翻译_终稿.doc', null, '0', 'doc-ieksk5bny7477nb', '4', 'cherry', '4', '4', '2018-05-21 19:33:21');
INSERT INTO `t_document` VALUES ('13', '	\r\n文献综述_终稿', 'F:\\word_project\\1525426885627_文献综述_终稿.doc', null, '0', 'doc-ieksk933k16cmxr', '4', 'cherry', '4', '6', '2018-05-21 19:33:21');
INSERT INTO `t_document` VALUES ('14', '	\r\n照片采集标准', 'F:\\word_project\\1525426885627_照片采集标准.doc', null, '2', 'doc-ieksk9eu73ndj67', '5', 'Juliy', '4', '6', '2018-05-21 19:33:20');
INSERT INTO `t_document` VALUES ('20', '浙江工商大学计算机与信息工程学院学生素质评价实施细则.doc', 'F:/word_project/1526979286783_浙江工商大学计算机与信息工程学院学生素质评价实施细则.doc', null, '0', 'doc-iempypbgpykz21p', '1', 'chenyuan', '0', '0', '2018-05-22 16:54:50');

-- ----------------------------
-- Table structure for t_keyword
-- ----------------------------
DROP TABLE IF EXISTS `t_keyword`;
CREATE TABLE `t_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) DEFAULT NULL,
  `content_key` text COMMENT '每个关键词用‘|’分割\r\n            ',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_keyword
-- ----------------------------
INSERT INTO `t_keyword` VALUES ('1', '1', '论文|学位|本人|承诺|学术|代写|他人|处理|组织|或者|', '2018-05-21 19:43:50');
INSERT INTO `t_keyword` VALUES ('2', '2', '指导|2018|论文|毕业|系统|毕业论文|讨论|检查|方法|实现|', '2018-05-21 19:44:34');
INSERT INTO `t_keyword` VALUES ('3', '3', '毕业|设计|同学|文献|论文|选题|问题|内容|要求|毕业设计|', '2018-05-21 19:45:12');
INSERT INTO `t_keyword` VALUES ('4', '4', '设计|论文|内容|毕业|实际|毕业设计|同学|完成|意义|10|', '2018-05-21 19:46:13');
INSERT INTO `t_keyword` VALUES ('5', '5', '记录|答辩|信息|毕业|毕业论文|工商|浙江|信息学|姓名|设计|', '2018-05-21 19:46:37');
INSERT INTO `t_keyword` VALUES ('6', '6', '答辩|论文|小组|签名|30|评语|20|组成|成员|毕业论文|', '2018-05-21 19:47:01');
INSERT INTO `t_keyword` VALUES ('7', '7', '成绩|评定|论文|教师|设计|评分|指导|合计|评阅|答辩|', '2018-05-21 19:48:35');
INSERT INTO `t_keyword` VALUES ('8', '8', '管理|文档|系统|技术|开发|用户|模块|实现|数据|进行|', '2018-05-21 19:48:15');
INSERT INTO `t_keyword` VALUES ('9', '9', '数据|文档|xml|系统|我们|存储|查询|管理|一个|可以|', '2018-05-21 19:48:59');
INSERT INTO `t_keyword` VALUES ('10', '10', '文档|管理|系统|完成|研究|基于|技术|应用|大学|设计|', '2018-05-21 19:49:21');
INSERT INTO `t_keyword` VALUES ('11', '11', 'as|入库|dim|not|randomnum|记录|null|int|storein|管理|', '2018-05-21 19:49:22');
INSERT INTO `t_keyword` VALUES ('12', '12', '数据|文档|xml|系统|我们|存储|查询|管理|一个|可以|', '2018-05-21 19:50:11');
INSERT INTO `t_keyword` VALUES ('13', '13', '文档|分享|可以|用户|在线|分类|系统|算法|管理|网站|', '2018-05-21 19:51:37');
INSERT INTO `t_keyword` VALUES ('14', '14', '要求|10|成像|照片|可以|以是|mm|摄影|背景|大小|', '2018-05-21 19:51:38');
INSERT INTO `t_keyword` VALUES ('16', '15', '学生 评价 素质 成绩 能力 评议 专业 学院 加分 综合', '2018-05-22 09:44:44');
INSERT INTO `t_keyword` VALUES ('17', '16', '12 学习 20 网上 党校 信息 报告 平台 会议 单位', '2018-05-22 10:11:06');
INSERT INTO `t_keyword` VALUES ('18', '17', '12 学习 20 网上 党校 信息 报告 平台 会议 单位', '2018-05-22 10:14:05');
INSERT INTO `t_keyword` VALUES ('19', '18', '12 学习 20 网上 党校 信息 报告 平台 会议 单位', '2018-05-22 10:22:30');
INSERT INTO `t_keyword` VALUES ('20', '19', '学生 评价 素质 成绩 能力 评议 专业 学院 加分 综合', '2018-05-22 10:23:08');
INSERT INTO `t_keyword` VALUES ('21', '20', '学生 评价 素质 成绩 能力 评议 专业 学院 加分 综合', '2018-05-22 16:54:50');

-- ----------------------------
-- Table structure for t_label
-- ----------------------------
DROP TABLE IF EXISTS `t_label`;
CREATE TABLE `t_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lable_name` varchar(50) NOT NULL,
  `level` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `label` (`lable_name`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_label
-- ----------------------------
INSERT INTO `t_label` VALUES ('1', '教育专区', '1', '0', '1', '2018-05-21 18:33:28');
INSERT INTO `t_label` VALUES ('2', '生活休闲', '1', '0', '1', '2018-05-21 18:33:30');
INSERT INTO `t_label` VALUES ('3', '小学教育', '2', '15', '1', '2018-05-21 18:33:32');
INSERT INTO `t_label` VALUES ('4', '初中教育', '2', '15', '1', '2018-05-21 18:33:34');
INSERT INTO `t_label` VALUES ('5', '高中教育', '2', '15', '1', '2018-05-21 18:33:35');
INSERT INTO `t_label` VALUES ('6', '大学教育', '2', '15', '1', '2018-05-21 18:33:38');
INSERT INTO `t_label` VALUES ('7', '研究生教育', '2', '15', '1', '2018-05-21 18:33:42');
INSERT INTO `t_label` VALUES ('8', '美食', '2', '15', '1', '2018-05-21 18:33:44');
INSERT INTO `t_label` VALUES ('9', '旅游', '2', '15', '1', '2018-05-21 18:33:45');
INSERT INTO `t_label` VALUES ('15', 'IT技术', '1', '0', '1', '2018-05-21 16:17:15');
INSERT INTO `t_label` VALUES ('16', '人工智能', '2', '14', '1', '2018-05-21 16:17:33');
INSERT INTO `t_label` VALUES ('17', '云计算', '2', '14', '1', '2018-05-21 16:17:42');
INSERT INTO `t_label` VALUES ('18', '大数据', '2', '14', '1', '2018-05-21 16:17:57');
INSERT INTO `t_label` VALUES ('19', '区块链', '2', '15', '1', '2018-05-21 16:18:13');
INSERT INTO `t_label` VALUES ('20', '数据库', '2', '15', '1', '2018-05-21 16:18:24');
INSERT INTO `t_label` VALUES ('21', '游戏开发', '2', '15', '1', '2018-05-21 16:18:28');
INSERT INTO `t_label` VALUES ('22', '前端', '2', '15', '1', '2018-05-21 16:18:37');
INSERT INTO `t_label` VALUES ('23', '移动开发', '2', '15', '1', '2018-05-21 16:18:44');
INSERT INTO `t_label` VALUES ('24', '物联网', '2', '15', '1', '2018-05-21 16:18:55');
INSERT INTO `t_label` VALUES ('25', '架构', '2', '15', '1', '2018-05-21 16:19:00');
INSERT INTO `t_label` VALUES ('26', '运维', '2', '15', '1', '2018-05-21 16:19:43');
INSERT INTO `t_label` VALUES ('42', '影视', '2', '28', '1', '2018-05-21 16:27:42');
INSERT INTO `t_label` VALUES ('43', 'KTV', '2', '15', '1', '2018-05-21 16:27:50');
INSERT INTO `t_label` VALUES ('44', '酒吧', '2', '15', '1', '2018-05-21 16:28:07');
INSERT INTO `t_label` VALUES ('65', '新闻', '1', '0', '1', '2018-05-21 19:54:58');
INSERT INTO `t_label` VALUES ('66', '国内新闻', '2', '1', '1', '2018-05-21 19:55:10');
INSERT INTO `t_label` VALUES ('67', '国际新闻', '2', '1', '1', '2018-05-21 19:55:17');
INSERT INTO `t_label` VALUES ('68', '娱乐新闻', '2', '1', '1', '2018-05-21 19:55:24');
INSERT INTO `t_label` VALUES ('69', '政治新闻', '2', '1', '1', '2018-05-21 19:55:31');
INSERT INTO `t_label` VALUES ('70', '文档', '1', '0', '1', '2018-05-21 19:56:01');
INSERT INTO `t_label` VALUES ('72', '其他', '2', '1', '1', '2018-05-21 19:57:42');
INSERT INTO `t_label` VALUES ('73', '学术', '2', '1', '1', '2018-05-21 19:57:48');
INSERT INTO `t_label` VALUES ('74', '本科论文', '2', '1', '1', '2018-05-21 19:58:04');
INSERT INTO `t_label` VALUES ('75', '硕士论文', '2', '1', '1', '2018-05-21 19:58:11');
INSERT INTO `t_label` VALUES ('76', '博士论文', '2', '1', '1', '2018-05-21 19:58:18');
INSERT INTO `t_label` VALUES ('77', '摄影', '2', '1', '1', '2018-05-21 20:14:35');
INSERT INTO `t_label` VALUES ('78', '党校教育', '2', '1', '1', '2018-05-22 10:11:57');
INSERT INTO `t_label` VALUES ('79', '素质评价', '2', '1', '1', '2018-05-22 10:26:36');

-- ----------------------------
-- Table structure for t_link
-- ----------------------------
DROP TABLE IF EXISTS `t_link`;
CREATE TABLE `t_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) DEFAULT NULL,
  `label_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_link
-- ----------------------------
INSERT INTO `t_link` VALUES ('1', '1', '73');
INSERT INTO `t_link` VALUES ('2', '1', '70');
INSERT INTO `t_link` VALUES ('3', '1', '74');
INSERT INTO `t_link` VALUES ('4', '2', '70');
INSERT INTO `t_link` VALUES ('5', '2', '74');
INSERT INTO `t_link` VALUES ('6', '3', '70');
INSERT INTO `t_link` VALUES ('7', '3', '74');
INSERT INTO `t_link` VALUES ('8', '4', '70');
INSERT INTO `t_link` VALUES ('9', '4', '74');
INSERT INTO `t_link` VALUES ('10', '5', '70');
INSERT INTO `t_link` VALUES ('11', '5', '74');
INSERT INTO `t_link` VALUES ('12', '6', '70');
INSERT INTO `t_link` VALUES ('13', '6', '74');
INSERT INTO `t_link` VALUES ('14', '7', '70');
INSERT INTO `t_link` VALUES ('15', '7', '74');
INSERT INTO `t_link` VALUES ('16', '8', '70');
INSERT INTO `t_link` VALUES ('17', '8', '74');
INSERT INTO `t_link` VALUES ('18', '8', '73');
INSERT INTO `t_link` VALUES ('19', '9', '70');
INSERT INTO `t_link` VALUES ('20', '9', '74');
INSERT INTO `t_link` VALUES ('21', '9', '15');
INSERT INTO `t_link` VALUES ('23', '10', '70');
INSERT INTO `t_link` VALUES ('24', '10', '74');
INSERT INTO `t_link` VALUES ('25', '11', '20');
INSERT INTO `t_link` VALUES ('26', '11', '15');
INSERT INTO `t_link` VALUES ('27', '12', '70');
INSERT INTO `t_link` VALUES ('28', '12', '74');
INSERT INTO `t_link` VALUES ('29', '13', '70');
INSERT INTO `t_link` VALUES ('30', '13', '74');
INSERT INTO `t_link` VALUES ('31', '14', '2');
INSERT INTO `t_link` VALUES ('32', '14', '77');
INSERT INTO `t_link` VALUES ('41', '20', '70');
INSERT INTO `t_link` VALUES ('42', '20', '79');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operater` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '1 是审核通过 2是审核失败 3是上传 4是下载\r\n 5是浏览',
  `document_id` int(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('1', 'chenyuan', '3', '1', '2018-05-21 19:09:45');
INSERT INTO `t_log` VALUES ('2', 'chenyuan', '3', '2', '2018-05-21 19:09:40');
INSERT INTO `t_log` VALUES ('3', 'chenyuan', '3', '3', '2018-05-21 19:09:47');
INSERT INTO `t_log` VALUES ('4', 'chenyuan', '3', '4', '2018-05-21 19:09:48');
INSERT INTO `t_log` VALUES ('5', 'chenyuan', '3', '5', '2018-05-21 19:09:50');
INSERT INTO `t_log` VALUES ('6', 'jason', '3', '6', '2018-05-21 19:10:57');
INSERT INTO `t_log` VALUES ('7', 'jason', '3', '7', '2018-05-22 19:10:58');
INSERT INTO `t_log` VALUES ('8', 'jack', '3', '8', '2018-05-22 19:11:00');
INSERT INTO `t_log` VALUES ('9', 'jack', '3', '9', '2018-05-21 19:11:01');
INSERT INTO `t_log` VALUES ('10', 'cherry', '3', '10', '2018-05-21 19:11:03');
INSERT INTO `t_log` VALUES ('11', 'cherry', '3', '11', '2018-05-21 19:11:04');
INSERT INTO `t_log` VALUES ('12', 'cherry', '3', '12', '2018-05-21 19:11:07');
INSERT INTO `t_log` VALUES ('13', 'cherry', '3', '13', '2018-05-21 19:11:09');
INSERT INTO `t_log` VALUES ('14', 'jason', '3', '14', '2018-05-21 19:11:10');
INSERT INTO `t_log` VALUES ('15', 'chenyuan', '1', '1', '2018-05-21 19:12:22');
INSERT INTO `t_log` VALUES ('16', 'chenyuan', '1', '5', '2018-05-21 19:12:29');
INSERT INTO `t_log` VALUES ('17', 'chenyuan', '1', '10', '2018-05-21 19:12:31');
INSERT INTO `t_log` VALUES ('18', 'chenyuan', '2', '14', '2018-05-21 19:12:45');
INSERT INTO `t_log` VALUES ('19', 'cherry', '4', '3', '2018-05-22 19:13:05');
INSERT INTO `t_log` VALUES ('20', 'cherry', '5', '4', '2018-05-22 19:13:07');
INSERT INTO `t_log` VALUES ('21', 'cherry', '4', '5', '2018-05-22 19:13:09');
INSERT INTO `t_log` VALUES ('22', 'jason', '5', '11', '2018-05-22 19:13:25');
INSERT INTO `t_log` VALUES ('23', 'jack', '4', '3', '2018-05-22 19:13:28');
INSERT INTO `t_log` VALUES ('24', 'cherry', '4', '7', '2018-05-22 19:13:30');
INSERT INTO `t_log` VALUES ('25', 'chenyuan', '5', '1', '2018-05-22 19:13:56');
INSERT INTO `t_log` VALUES ('26', 'chenyuan', '5', '1', '2018-05-22 19:13:59');
INSERT INTO `t_log` VALUES ('27', 'chenyuan', '5', '1', '2018-05-22 19:18:21');
INSERT INTO `t_log` VALUES ('28', 'chenyuan', '4', '1', '2018-05-22 19:20:26');
INSERT INTO `t_log` VALUES ('29', 'chenyuan', '4', '1', '2018-05-22 19:25:11');
INSERT INTO `t_log` VALUES ('30', 'chenyuan', '4', '2', '2018-05-22 19:25:13');
INSERT INTO `t_log` VALUES ('31', 'chenyuan', '5', '1', '2018-05-22 19:54:16');
INSERT INTO `t_log` VALUES ('32', 'chenyuan', '5', '1', '2018-05-22 09:40:47');
INSERT INTO `t_log` VALUES ('33', 'chenyuan', '5', '1', '2018-05-22 09:42:55');
INSERT INTO `t_log` VALUES ('34', 'chenyuan', '3', '15', '2018-05-22 09:44:44');
INSERT INTO `t_log` VALUES ('35', 'chenyuan', '3', '16', '2018-05-22 10:11:06');
INSERT INTO `t_log` VALUES ('36', 'chenyuan', '3', '17', '2018-05-22 10:14:05');
INSERT INTO `t_log` VALUES ('37', 'chenyuan', '3', '18', '2018-05-22 10:22:30');
INSERT INTO `t_log` VALUES ('38', 'chenyuan', '3', '19', '2018-05-22 10:23:08');
INSERT INTO `t_log` VALUES ('39', 'chenyuan', '5', '2', '2018-05-22 16:54:29');
INSERT INTO `t_log` VALUES ('40', 'chenyuan', '4', '1', '2018-05-22 16:54:37');
INSERT INTO `t_log` VALUES ('41', 'chenyuan', '3', '20', '2018-05-22 16:54:50');

-- ----------------------------
-- Table structure for t_statisitc
-- ----------------------------
DROP TABLE IF EXISTS `t_statisitc`;
CREATE TABLE `t_statisitc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `download_num` int(11) DEFAULT NULL,
  `read_num` int(11) DEFAULT NULL,
  `upload_num` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8 COMMENT='日统计表';

-- ----------------------------
-- Records of t_statisitc
-- ----------------------------
INSERT INTO `t_statisitc` VALUES ('1', '1', '1', '1', '2018-04-23 17:59:38');
INSERT INTO `t_statisitc` VALUES ('2', '1', '1', '1', '2018-04-24 17:14:00');
INSERT INTO `t_statisitc` VALUES ('3', '123', '300', '12', '2018-04-25 17:16:00');
INSERT INTO `t_statisitc` VALUES ('4', '234', '400', '23', '2018-04-26 17:18:00');
INSERT INTO `t_statisitc` VALUES ('5', '145', '500', '34', '2018-04-27 17:20:00');
INSERT INTO `t_statisitc` VALUES ('6', '156', '400', '25', '2018-04-28 13:56:00');
INSERT INTO `t_statisitc` VALUES ('7', '178', '300', '18', '2018-04-29 13:57:00');
INSERT INTO `t_statisitc` VALUES ('8', '134', '200', '17', '2018-05-01 13:58:00');
INSERT INTO `t_statisitc` VALUES ('9', '234', '300', '25', '2018-04-30 13:59:00');
INSERT INTO `t_statisitc` VALUES ('223', '120', '400', '23', '2018-05-02 17:41:00');
INSERT INTO `t_statisitc` VALUES ('224', '123', '345', '13', '2018-05-03 17:42:00');
INSERT INTO `t_statisitc` VALUES ('225', '134', '456', '34', '2018-05-04 17:43:00');
INSERT INTO `t_statisitc` VALUES ('226', '111', '532', '45', '2018-05-05 17:44:00');
INSERT INTO `t_statisitc` VALUES ('227', '222', '235', '34', '2018-05-06 17:45:00');
INSERT INTO `t_statisitc` VALUES ('228', '222', '454', '32', '2018-05-07 17:46:00');
INSERT INTO `t_statisitc` VALUES ('229', '111', '456', '23', '2018-05-08 17:47:00');
INSERT INTO `t_statisitc` VALUES ('230', '123', '542', '54', '2018-05-09 17:48:00');
INSERT INTO `t_statisitc` VALUES ('231', '123', '235', '65', '2018-05-10 17:49:00');
INSERT INTO `t_statisitc` VALUES ('232', '234', '234', '75', '2018-05-11 17:50:00');
INSERT INTO `t_statisitc` VALUES ('233', '123', '453', '35', '2018-05-12 17:51:00');
INSERT INTO `t_statisitc` VALUES ('234', '167', '345', '45', '2018-05-13 17:52:00');
INSERT INTO `t_statisitc` VALUES ('235', '178', '234', '76', '2018-05-14 17:53:00');
INSERT INTO `t_statisitc` VALUES ('236', '167', '345', '45', '2018-05-15 17:54:00');
INSERT INTO `t_statisitc` VALUES ('237', '156', '367', '65', '2018-05-16 17:55:00');
INSERT INTO `t_statisitc` VALUES ('238', '127', '289', '45', '2018-05-17 17:56:00');
INSERT INTO `t_statisitc` VALUES ('239', '234', '287', '43', '2018-05-18 17:57:00');
INSERT INTO `t_statisitc` VALUES ('240', '245', '378', '24', '2018-05-19 17:58:00');
INSERT INTO `t_statisitc` VALUES ('241', '222', '368', '54', '2018-05-20 17:59:00');
INSERT INTO `t_statisitc` VALUES ('242', '245', '345', '56', '2018-05-21 18:30:22');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `read_num` int(11) DEFAULT NULL,
  `down_num` int(11) DEFAULT NULL,
  `upload_num` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `password` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'chenyuan', '1234567890@qq.com', '17845678653', '女', '234', '46', '5', '2018-05-21 19:06:02', '9B65CE38D6F53BBE7F809789D761210885A4CE52', '1', '');
INSERT INTO `t_user` VALUES ('2', 'jason', '1224324561@qq.com', '17855467893', '男', '65', '56', '2', '2018-05-21 19:06:01', '9B65CE38D6F53BBE7F809789D761210885A4CE52', '1', '');
INSERT INTO `t_user` VALUES ('3', 'jack', '1224435678@qq.com', '17834576878', '男', '657', '354', '2', '2018-05-21 19:06:00', '9B65CE38D6F53BBE7F809789D761210885A4CE52', '1', '');
INSERT INTO `t_user` VALUES ('4', 'cherry', '1224434568@qq.com', '17864432123', '女', '234', '234', '4', '2018-05-21 19:32:38', '9B65CE38D6F53BBE7F809789D761210885A4CE52', '1', '');
INSERT INTO `t_user` VALUES ('5', 'Juliy', '1222344568@qq.com', '17844632123', '女', '12', '3', '1', '2018-05-21 19:05:59', '9B65CE38D6F53BBE7F809789D761210885A4CE52', '1', '');
INSERT INTO `t_user` VALUES ('6', 'Mike', '3436535336@qq.com', '13756453224', '男', '23', '56', '0', '2018-05-21 19:05:55', '9B65CE38D6F53BBE7F809789D761210885A4CE52', '1', null);

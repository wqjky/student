# student 在navicate中执行以下查询语句创建数据库
/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-03-09 08:38:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `admin` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin', 'admin', 'admin');
INSERT INTO `admin` VALUES ('2', 'admin', 'wqj', 'wqj', 'student');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `sno` varchar(20) NOT NULL,
  `department` varchar(20) NOT NULL,
  `hometown` varchar(20) NOT NULL,
  `mark` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `sex` varchar(20) NOT NULL,
  `admin` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('18', '张三', '001', '信息科学技术学院', '辽宁', '80', 'zhangsan@163.com', '13888888888', '男', 'student');
INSERT INTO `student` VALUES ('19', '李四', '002', '理学院', '上海', '70', 'lisi@sina.com', '13812341234', '男', 'student');
INSERT INTO `student` VALUES ('20', '王五', '003', '外国语学院', '北京', '88', 'wangwu@126.com', '13698765432', '女', 'student');
INSERT INTO `student` VALUES ('22', '陈恬儿', '11', '1', '1', '1', '1', '1', '1', 'student');
INSERT INTO `student` VALUES ('23', '888888', '0', '0', '0', '0', '0', '0', 'm', 'student');
INSERT INTO `student` VALUES ('24', 'wqj', '20161554106', '信息工程学院', '河南', '80', 'wqj@qq.com', '18303601062', '男', null);

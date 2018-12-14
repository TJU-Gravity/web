/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : contest

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-12-13 22:33:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `contestEndDate` varchar(255) DEFAULT NULL,
  `claim` text,
  `reward` text,
  `platform` varchar(255) DEFAULT NULL,
  `del` int(11) DEFAULT '0',
  `content` text,
  `logo` text,
  `contestStartDate` varchar(255) DEFAULT NULL,
  `registrationStartDate` varchar(255) DEFAULT NULL,
  `registrationEndDate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game
-- ----------------------------

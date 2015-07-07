/*
 Navicat Premium Data Transfer

 Source Server         : CoursesSelectedSys
 Source Server Type    : MySQL
 Source Server Version : 50622
 Source Host           : localhost
 Source Database       : CreatePro

 Target Server Type    : MySQL
 Target Server Version : 50622
 File Encoding         : utf-8

 Date: 06/30/2015 15:30:59 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `URL`
-- ----------------------------
DROP TABLE IF EXISTS `URL`;
CREATE TABLE `URL` (
  `URLID` int(50) NOT NULL AUTO_INCREMENT,
  `SEEDID` int(50) DEFAULT NULL,
  `URL` varchar(1024) DEFAULT NULL,
  `PATH` varchar(200) DEFAULT NULL,
  `TITLE` varchar(1024) DEFAULT NULL,
  `TIME` datetime(6) DEFAULT CURRENT_TIMESTAMP(6),
  `CONTENT` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`URLID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `seed`
-- ----------------------------
DROP TABLE IF EXISTS `seed`;
CREATE TABLE `seed` (
  `SeedId` int(50) NOT NULL AUTO_INCREMENT,
  `URL` varchar(1024) DEFAULT NULL,
  `PROPERTIES` varchar(20) DEFAULT NULL,
  `TITLE` varchar(200) DEFAULT NULL,
  `TIME` varchar(200) DEFAULT NULL,
  `CONTENT` varchar(200) DEFAULT NULL,
  `UNIT_PRIORITY` int(11) DEFAULT NULL,
  PRIMARY KEY (`SeedId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

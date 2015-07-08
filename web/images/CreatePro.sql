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

 Date: 07/08/2015 13:36:21 PM
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
  `DOCSIZE` int(11) DEFAULT NULL,
  `LASTCRAWLERTIME` varchar(8) DEFAULT NULL,
  `CYCLE` int(20) DEFAULT NULL,
  `URLVALUE` int(20) DEFAULT NULL,
  `PAGECONTENTVALUE` int(20) DEFAULT NULL,
  `URLSTATUS` int(1) DEFAULT NULL,
  PRIMARY KEY (`URLID`)
) ENGINE=InnoDB AUTO_INCREMENT=508 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `URL`
-- ----------------------------
BEGIN;
INSERT INTO `URL` VALUES ('473', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=23463&ctl=Detail&mid=44082&Id=155290&SkinSrc=[L]Skins/jwc2-1/jwc2-1', '0', null, '0', '0', '0', '0'), ('474', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=24450&ctl=Detail&mid=46393&Id=154190&SkinSrc=[', '0', null, '0', '0', '0', '0'), ('475', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=23463&ctl=Detail&mid=44082&Id=154172&SkinSrc=[L]Skins/jwc2-1/jwc2-1', '0', null, '0', '0', '0', '0'), ('476', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=23463&ctl=Detail&mid=44082&Id=154096&SkinSrc=[L]Skins/jwc2-1/jwc2-1', '0', null, '0', '0', '0', '0'), ('477', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=23463&ctl=Detail&mid=44082&Id=153995&SkinSrc=[L]Skins/jwc2-1/jwc2-1', '0', null, '0', '0', '0', '0'), ('478', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=23463&ctl=Detail&mid=44082&Id=153924&SkinSrc=[L]Skins/jwc2-1/jwc2-1', '0', null, '0', '0', '0', '0'), ('479', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=24450&ctl=Detail&mid=46393&Id=153739&SkinSrc=[', '0', null, '0', '0', '0', '0'), ('480', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=23463&ctl=Detail&mid=44082&Id=153502&SkinSrc=[L]Skins/jwc2-1/jwc2-1', '0', null, '0', '0', '0', '0'), ('481', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=23463&ctl=Detail&mid=44082&Id=153503&SkinSrc=[L]Skins/jwc2-1/jwc2-1', '0', null, '0', '0', '0', '0'), ('482', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=23463&ctl=Detail&mid=44082&Id=153501&SkinSrc=[L]Skins/jwc2-1/jwc2-1', '0', null, '0', '0', '0', '0'), ('483', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=24450&ctl=Detail&mid=47536&Id=153239&SkinSrc=[', '0', null, '0', '0', '0', '0'), ('484', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=23463&ctl=Detail&mid=44082&Id=153242&SkinSrc=[L]Skins/jwc2-1/jwc2-1', '0', null, '0', '0', '0', '0'), ('485', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=24450&ctl=Detail&mid=46393&Id=153175&SkinSrc=[', '0', null, '0', '0', '0', '0'), ('486', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=23463&ctl=Detail&mid=44082&Id=153154&SkinSrc=[L]Skins/jwc2-1/jwc2-1', '0', null, '0', '0', '0', '0'), ('487', '1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=23463&ctl=Detail&mid=44082&Id=152729&SkinSrc=[L]Skins/jwc2-1/jwc2-1', '0', null, '0', '0', '0', '0'), ('488', '2', 'http://www.xzbg.shu.edu.cn/Default.aspx?tabid=24214&ctl=Detail&mid=45360&Id=179982', '0', null, '0', '0', '0', '0'), ('489', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30654&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('490', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30638&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('491', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30634&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('492', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30625&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('493', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30606&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('494', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30603&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('495', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30593&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('496', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30575&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('497', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30572&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('498', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30553&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('499', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30549&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('500', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30547&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('501', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30546&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('502', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30535&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('503', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30614&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('504', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30515&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('505', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30502&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('506', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30493&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0'), ('507', '2', 'http://news.shu.edu.cn/Default.aspx?tabid=446&ctl=Details&mid=1053&ItemID=30452&SkinSrc=[L]Skins/SHUnews_o/SHUnews_1', '0', null, '0', '0', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `seed`
-- ----------------------------
DROP TABLE IF EXISTS `seed`;
CREATE TABLE `seed` (
  `SeedId` int(20) NOT NULL AUTO_INCREMENT,
  `URL` varchar(1024) DEFAULT NULL,
  `SITENAME` varchar(1024) DEFAULT NULL,
  `DNLIMITE` varchar(20) DEFAULT NULL,
  `STATUS` bit(1) DEFAULT NULL,
  PRIMARY KEY (`SeedId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `seed`
-- ----------------------------
BEGIN;
INSERT INTO `seed` VALUES ('1', 'http://www.jwc.shu.edu.cn/Default.aspx?tabid=23463', 'Shanghai University', 'shu.edu.cn', b'0'), ('2', 'http://news.shu.edu.cn/Default.aspx?tabid=446', 'Shanghai University', 'shu.edu.cn', b'0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : edu
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : sms

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 14/07/2019 12:14:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gradeId` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gradeId`(`gradeId`) USING BTREE,
  CONSTRAINT `clazz_ibfk_1` FOREIGN KEY (`gradeId`) REFERENCES `grade` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES (4, 8, '123456', '1234');
INSERT INTO `clazz` VALUES (5, 26, '1109班', '');
INSERT INTO `clazz` VALUES (6, 19, '1111', '222');
INSERT INTO `clazz` VALUES (7, 8, '222', '1111');
INSERT INTO `clazz` VALUES (8, 8, '111', '');
INSERT INTO `clazz` VALUES (9, 8, '222', '');
INSERT INTO `clazz` VALUES (10, 8, '1', '1');
INSERT INTO `clazz` VALUES (12, 17, '213', '123');
INSERT INTO `clazz` VALUES (13, 8, '555', '5555');
INSERT INTO `clazz` VALUES (14, 19, '12312312', '123');
INSERT INTO `clazz` VALUES (15, 23, 'dfsgt', '123123');

SET FOREIGN_KEY_CHECKS = 1;

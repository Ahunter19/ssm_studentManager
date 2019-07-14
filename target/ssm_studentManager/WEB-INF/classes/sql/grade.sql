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

 Date: 13/07/2019 14:56:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '年级',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES (1, '小学四年级', '小学四年级');
INSERT INTO `grade` VALUES (8, '小学三年级', '小学三年级');
INSERT INTO `grade` VALUES (17, '小学二年级', '小学一年级');
INSERT INTO `grade` VALUES (18, '小学一年级', '小学一年级');
INSERT INTO `grade` VALUES (19, '小学5年级', '小学5年级');
INSERT INTO `grade` VALUES (20, '小学6年级', '小学6年级');
INSERT INTO `grade` VALUES (21, '初中1年级', '初中1年级');
INSERT INTO `grade` VALUES (22, '初中2年级', '初中2年级');
INSERT INTO `grade` VALUES (23, '初中5年级', '初中5年级');
INSERT INTO `grade` VALUES (24, '高中1年级', '高中1年级');
INSERT INTO `grade` VALUES (25, '高中2年级', '高中2年级');
INSERT INTO `grade` VALUES (26, '高中3年级', '高中3年级');

SET FOREIGN_KEY_CHECKS = 1;

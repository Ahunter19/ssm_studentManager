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

 Date: 13/07/2019 14:56:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', 'admin');
INSERT INTO `user` VALUES (2, 'admin1', '123');
INSERT INTO `user` VALUES (3, 'admin2', '123');
INSERT INTO `user` VALUES (4, '123', '123');
INSERT INTO `user` VALUES (5, '123', '123');
INSERT INTO `user` VALUES (6, 'acely', '123456');
INSERT INTO `user` VALUES (7, 'acely', '123456');
INSERT INTO `user` VALUES (14, 'aaa', 'aaa');

SET FOREIGN_KEY_CHECKS = 1;

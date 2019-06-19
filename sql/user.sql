/*
 Navicat Premium Data Transfer

 Source Server         : local_MySql
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : work

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 03/04/2019 14:36:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_gender` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张张张张', 23, '北京', '男', 'aaaaa', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (2, '王王王五', 25, '天津', '女', 'bbbb', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (3, '李李李李', 22, '上海', '男', 'cccc', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (4, '刘刘刘刘', 34, '南京', '女', 'ddddd', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (6, '李李李李', 27, '山西', '男', 'eeeee', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (7, '李李李李', 24, '山西', '男', 'fffff', NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (8, '李李李李', 27, '山西', '男', 'aaa', '123456', '13718533040', 'zjylove2006@126.com', NULL);
INSERT INTO `user` VALUES (25, '周周周周', 29, NULL, NULL, 'zjylove2006', 'dayong020100229', '13718533040', 'zjylove2006@126.com', NULL);
INSERT INTO `user` VALUES (26, '大周', 30, '北京', '0', 'admin1', '123456', '13718533040', 'zjylove2006@126.com', NULL);
INSERT INTO `user` VALUES (27, '大周', 30, '北京', '1', 'admin', '123456', '13718533040', 'zjylove2006@126.com', NULL);
INSERT INTO `user` VALUES (37, '胖货', 31, '山西大同', '男', 'panghuo', '1B2M2Y8AsgTpgAmY7PhCfg==', '13623428590', 'gjm123@126.com', NULL);
INSERT INTO `user` VALUES (38, '我们', 200, '山西大同', '一群男', 'home', NULL, '各自电话', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

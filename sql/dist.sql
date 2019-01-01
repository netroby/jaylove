create database if not exists jaylove;
use jaylove;
CREATE TABLE `article` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8mb4,
  `images` JSON NOT NULL,
  `publish_time` bigint NOT NULL DEFAULT 0,
  `publish_status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`aid`),
  FULLTEXT KEY `content` (`content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `access_token` (
  `token` varchar(256) NOT NULL,
  PRIMARY KEY (`token`),
  UNIQUE KEY `access_token_Token_uindex` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE UNIQUE INDEX access_token_Token_uindex ON access_token (Token);
CREATE TABLE `app_info` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `app_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL COMMENT 'android/ios',
  `icon` varchar(255),
  `note` varchar(255)
) character set = utf8;

CREATE TABLE `app_publish_history` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `app_id` int NOT NULL,
  `version` varchar(255) NOT NULL COMMENT '版本号',
  `note` varchar(255),
  `file_id` varchar(255) COMMENT '下载地址',
  `size` double COMMENT '文件大小',
  `count` int COMMENT '下载次数',
  `publish_date` timestamp COMMENT '发布时间'
) character set = utf8;

CREATE TABLE `app_file` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `md5` varchar(255) UNIQUE NOT NULL,
  `size` double,
  `path` varchar(255)
) character set = utf8;

ALTER TABLE `app_publish_history` ADD FOREIGN KEY (`app_id`) REFERENCES `app_info` (`id`);

ALTER TABLE `app_publish_history` ADD FOREIGN KEY (`file_id`) REFERENCES `app_file` (`id`);
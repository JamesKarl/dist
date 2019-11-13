CREATE TABLE `AppInfo` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `appId` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL COMMENT 'android/ios',
  `icon` varchar(255),
  `note` varchar(255)
);

CREATE TABLE `AppPublishHistory` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `appId` int NOT NULL,
  `version` varchar(255) NOT NULL COMMENT '版本号',
  `note` varchar(255),
  `url` varchar(255) COMMENT '下载地址',
  `size` double COMMENT '文件大小',
  `count` int COMMENT '下载次数',
  `publishDate` timestamp COMMENT '发布时间'
);

ALTER TABLE `AppPublishHistory` ADD FOREIGN KEY (`appId`) REFERENCES `AppInfo` (`id`);

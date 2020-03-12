# drop database if exists `jfinal`;
# create database `jfinal`;

use jfinal;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       int(11) auto_increment,
    `account`  varchar(255) not null,
    `password` varchar(255) not null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
insert into `user`
values (1, 'root', 'root'),
       (2, 'admin', 'admin');

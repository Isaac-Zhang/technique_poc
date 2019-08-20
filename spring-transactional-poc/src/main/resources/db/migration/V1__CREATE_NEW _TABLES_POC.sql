CREATE TABLE IF NOT EXISTS `test_transaction`(
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
`name` varchar(128) NOT NULL COMMENT '内容名称',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '用来测试transaction';
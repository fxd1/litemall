create table if not EXISTS `user`(
   `id` int(32) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
   `nick_name` varchar(24) NOT NULL DEFAULT '' COMMENT '微信昵称',
   `avatar` varchar(10000) NOT NULL DEFAULT '' COMMENT '微信头像',
   `open_id` varchar(30) NOT NULL DEFAULT '' COMMENT '小程序中标识',
   `type` tinyint NOT NULL DEFAULT 0 COMMENT '0：普通用户 1：会员',
   `sex` tinyint NOT NULL DEFAULT 0 COMMENT '0：女生 1：男生',
   `integral` int(32) NOT NULL DEFAULT 0 COMMENT '积分',
   `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '更新时间',
    `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

create table if not EXISTS `address`(
   `id` int(32) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
   `user_id` int(32) NOT NULL DEFAULT 0 COMMENT '用户id',
   `phone` varchar(15) NOT NULL DEFAULT '' COMMENT '电话',
   `detail` varchar(50) NOT NULL DEFAULT '' COMMENT '详细地址',
   `priority` tinyint NOT NULL DEFAULT 0 COMMENT '优先级',
   `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '更新时间',
    `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
   PRIMARY KEY (`id`),
   UNIQUE KEY `uniq_user_id` (user_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='地址表';

create table if not EXISTS `good`(
   `id` int(32) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
   `user_id` int(32) NOT NULL DEFAULT 0 COMMENT '用户的id',
   `order_id` int(32) NOT NULL DEFAULT 0 COMMENT '订单id',
   `class_id` int(32) NOT NULL DEFAULT 0 COMMENT '种类id',
   `name` varchar(50) NOT NULL DEFAULT '' COMMENT '物品的名称',
    `number` int(32) NOT NULL DEFAULT 0 COMMENT '数量',
   `price` double(10,2) NOT NULL DEFAULT 0 COMMENT '单价',
   `status` tinyint NOT NULL DEFAULT 0 COMMENT '物品的状态：0，提交 1.已付款未发货 3. 已经发货未付款 4.已经发货且已付款 5.取消, 6 删除',
   `promotion_type` tinyint NOT NULL DEFAULT 0 COMMENT '营销种类 0：无 1：拼团 2：砍价',
   `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '更新时间',
   `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
   PRIMARY KEY (`id`),
   KEY `user_id_index`(user_id),
   KEY `class_id_index`(class_id),
   KEY `order_id_index`(order_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='物品';

create table if not exists `order`(
   `id` int(32) unsigned NOT NULL  AUTO_INCREMENT COMMENT 'id',
   `user_id` int(32) unsigned NOT NULL DEFAULT 0 COMMENT '',
    `address_id` int(32) unsigned NOT NULL DEFAULT 0 COMMENT 'id',
   `promotion_type` tinyint NOT NULL DEFAULT 0 COMMENT '营销种类 0：无 1：满减',
   `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '更新时间',
   `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)

)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='订单';


create table if not EXISTS `class`(
   `id` int(32) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
   `class_name` int(32) NOT NULL DEFAULT 0 COMMENT '种类名称',
   `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '更新时间',
   `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='种类';

create table if not EXISTS `trip`(
   `id` int(32) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
   `country_name` varchar(32) NOT NULL DEFAULT '' COMMENT '出国地点',
   `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '更新时间',
   `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
   `end_time` TIMESTAMP NOT NULL  COMMENT '回国时间',
   `start_time` TIMESTAMP NOT NULL COMMENT '出国时间',
   PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='行程表';


create table if not EXISTS `group_buy`(
   `id` int(32) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
   `good_id` int(32) NOT NULL DEFAULT 0 COMMENT '商品id',
   `user_id` int(32) NOT NULL DEFAULT 0 COMMENT '发起人',
   `sum` int(5) NOT NULL DEFAULT 0 COMMENT '拼团总人数',
   `current_number` int(5) NOT NULL DEFAULT 0 COMMENT '拼团人数',
   `origin_price` double (10,2) NOT NULL DEFAULT 0 COMMENT '发起人价格',
   `sponsor_price` double (10,2) NOT NULL DEFAULT 0  COMMENT '拼团价格',
   `group_price` double (10,2) NOT NULL DEFAULT 0 COMMENT '发起人价格',
   `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态：0：正常 -1：取消 1：拼团失败 2：拼团成功',
   `update_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT  '更新时间',
    `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
    `deadline_time` TIMESTAMP NOT NULL COMMENT '截止时间',
   PRIMARY KEY (`id`),
   KEY `good_id` (good_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='拼团';

create table if not EXISTS `bargain`(
   `id` int(32) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
   `good_id` int(32) NOT NULL DEFAULT 0 COMMENT '商品id',
   `user_id` int(32) NOT NULL DEFAULT 0 COMMENT '发起人',
   `threshold` int(10) NOT NULL DEFAULT 0 COMMENT '砍价的上限价',
   `max` int(10) NOT NULL DEFAULT 0 COMMENT '最多的人数',
   `bargain_user_ids` varchar (3000) NOT NULL DEFAULT '' COMMENT '砍价人id',
   `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态：0：正常 -1：取消 1：结束',
   `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '更新时间',
    `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
   PRIMARY KEY (`id`),
   KEY `good_id`(good_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='砍价';


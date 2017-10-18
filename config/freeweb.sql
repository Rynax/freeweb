CREATE USER 'freeweb'@'localhost';
SET PASSWORD FOR 'freeweb'@'localhost' = PASSWORD('freeweb');

GRANT SELECT ON freeweb.* TO 'freeweb'@'localhost';
GRANT ALL on freeweb.* TO 'freeweb'@'localhost';

CREATE SCHEMA `freeweb` DEFAULT CHARACTER SET utf8 ;

DROP TABLE IF EXISTS `freeweb`.`index_info`;
DROP TABLE IF EXISTS `freeweb`.`menu_info`;
DROP TABLE IF EXISTS `freeweb`.`user_info`;
DROP TABLE IF EXISTS `freeweb`.`address_info`;
DROP TABLE IF EXISTS `freeweb`.`cart_info`;
DROP TABLE IF EXISTS `freeweb`.`product_info`;
DROP TABLE IF EXISTS `freeweb`.`product_type_info`;
DROP TABLE IF EXISTS `freeweb`.`shop_info`;
DROP TABLE IF EXISTS `freeweb`.`user_detail_info`;


CREATE TABLE `freeweb`.`user_detail_info` (
	user_id int(11) not null AUTO_INCREMENT COMMENT '用户id',
    nick_name varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci not null unique COMMENT '用户昵称',
	real_name varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '实名',
    real_name_status int(1) not null default 0 COMMENT '实名认证状态: 0:未认证, 1:已认证',
    idcard_type int(1) default 0 COMMENT '证件类型: 0:中国身份证',
	idcard_number varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci unique COMMENT '证件号',
    register_time datetime not null default now() COMMENT '用户注册时间',
	user_status int(1) not null default 0 COMMENT '用户状态: 0:正常, 1:冻结, 2:删除, 3: 首次登陆',
    primary key (`user_id`),
    key (`nick_name`)
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户详细信息表';

INSERT INTO `freeweb`.`user_detail_info` VALUES (null, 'admin', null, 0, null, null, now(), 0);
INSERT INTO `freeweb`.`user_detail_info` VALUES (null, '测试用户', null, 0, null, null, now(), 3);


CREATE TABLE `freeweb`.`user_info` (
	user_id int(11) not null COMMENT '用户id',
	login_name varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci not null COMMENT '登录用户名',
	login_pwd varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci not null COMMENT '登陆密码',
	account_status int(1) not null default 0 COMMENT '账号状态: 0:正常, 1:冻结, 2:删除',
    primary key (`login_name`),
    key (`user_id`),
    constraint `user_info_user_id` foreign key (`user_id`) references `freeweb`.`user_detail_info` (`user_id`) on delete cascade on update cascade
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表';

INSERT INTO `freeweb`.`user_info` VALUES (1, 'admin', 'admin', 0);
INSERT INTO `freeweb`.`user_info` VALUES (2, '测试用户', '测试密码', default);


CREATE TABLE `freeweb`.`address_info` (
	addr_id int(11) not null AUTO_INCREMENT COMMENT '地址id',
    user_id int(11) not null COMMENT '用户id',
    address varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci not null COMMENT '地址',
    postcode varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '邮编',
    phone varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci not null COMMENT '联系电话',
    addr_status int(1) not null default 0 COMMENT '地址状态: 0:普通, 1:首选',
    primary key (`addr_id`),
    key (`user_id`),
    constraint `address_info_user_id` foreign key (`user_id`) references `freeweb`.`user_detail_info` (`user_id`) on delete cascade on update cascade
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地址表';

INSERT INTO `freeweb`.`address_info` VALUES (null, 2, '沈阳市', null, '13010241024', 1);


CREATE TABLE `freeweb`.`product_type_info` (
	prod_type_id int(11) not null AUTO_INCREMENT COMMENT '产品类别id',
    prod_type_name varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci not null unique COMMENT '产品类别名称',
    primary key (`prod_type_id`),
    key (`prod_type_name`)
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '品类表';

INSERT INTO `freeweb`.`product_type_info` VALUES (null, '测试类别');


CREATE TABLE `freeweb`.`shop_info` (
	shop_id int(11) not null AUTO_INCREMENT COMMENT '商铺id',
    user_id int(11) not null COMMENT '用户id',
    shop_name varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci not null unique COMMENT '商铺名称',
    shop_time datetime not null default now() COMMENT '商铺建立时间',
    shop_status int(1) not null default 0 COMMENT '商铺状态: 0:正常, 1:冻结',
    primary key (`shop_id`),
    key (`user_id`),
    key (`shop_name`),
    constraint `shop_info_user_id` foreign key (`user_id`) references `freeweb`.`user_detail_info` (`user_id`) on delete cascade on update cascade
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商铺表';

INSERT INTO `freeweb`.`shop_info` VALUES (null, 2, '测试商铺', 0);


CREATE TABLE `freeweb`.`product_info` (
	prod_id int(11) not null AUTO_INCREMENT COMMENT '产品id',
    shop_id int(11) not null COMMENT '商铺id',
    prod_type_id int(11) COMMENT '类别id',
    prod_name varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci not null COMMENT '产品名称',
    prod_src_price int(11) not null default 0 COMMENT '原价',
    prod_cur_price int(11) not null default 0 COMMENT '现价',
    prod_save int(11) not null default 0 COMMENT '库存',
    prod_desc varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '产品详情, json',
    prod_spict varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '产品图片小, 方形半行展示, 图片的json数组',
    prod_mpict varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '产品图片中, 矩形整行展示, 图片的json数组',
    prod_lpict varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '产品图片大, 详情页内展示, 图片的json数组',
    prod_status int(1) not null default 1 COMMENT '产品状态: 0:正常, 1:下架',
    primary key (`prod_id`),
    key (`shop_id`),
    key (`prod_type_id`),
    key (`prod_name`),
    constraint `product_info_shop_id` foreign key (`shop_id`) references `freeweb`.`shop_info` (`shop_id`) on delete cascade on update cascade,
    constraint `product_info_prod_type_id` foreign key (`prod_type_id`) references `freeweb`.`product_type_info` (`prod_type_id`) on delete set null on update cascade
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品表';

INSERT INTO `freeweb`.`product_info` VALUES (null, 1, 1, '测试商品1', 100, 0.01, 3, '{"desc":"这是一个测试商品1","addr":"沈阳市"}', '', '[{"img":"products/1/medium/4.jpg"},{"img":"products/1/medium/5.jpg"}]', '', 0);
INSERT INTO `freeweb`.`product_info` VALUES (null, 1, null, '测试商品2', 200, 0.01, 3, '{"desc":"这是一个测试商品2","addr":"沈阳市"}', '', '[{"img":"products/2/medium/1.jpg"},{"img":"products/2/medium/3.jpg"},{"img":"products/2/medium/6.jpg"}]', '', 0);
INSERT INTO `freeweb`.`product_info` VALUES (null, 1, null, '测试商品3', 10000, 0.01, 3, '{"desc":"这是一个测试商品3","addr":"XX市"}', '', '[{"img":"products/3/medium/2.jpg"}]', '', 0);
INSERT INTO `freeweb`.`product_info` VALUES (null, 1, 1, '测试商品4', 10000, 0.01, 3, '{"desc":"这是一个测试商品4","addr":"XX市"}', '', '[{"img":"products/4/medium/2.jpg"}]', '', 0);


CREATE TABLE `freeweb`.`cart_info` (
	cart_id int(11) not null AUTO_INCREMENT COMMENT '购物车id, 结账用',
    prod_id int(11) not null COMMENT '产品id',
    shop_id int(11) not null COMMENT '商铺id',
    user_id int(11) not null COMMENT '用户id',
    prod_cart_time datetime not null default now() COMMENT '加购时间',
    prod_cart_num int(11) not null COMMENT '购买数量',
    primary key (`cart_id`),
    key (`prod_id`),
    key (`shop_id`),
    key (`user_id`),
    constraint `cart_info_prod_id` foreign key (`prod_id`) references `freeweb`.`product_info` (`prod_id`) on delete cascade on update cascade,
    constraint `cart_info_shop_id` foreign key (`shop_id`) references `freeweb`.`shop_info` (`shop_id`) on delete cascade on update cascade,
    constraint `cart_info_user_id` foreign key (`user_id`) references `freeweb`.`user_detail_info` (`user_id`) on delete cascade on update cascade
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车内商品表';

INSERT INTO `freeweb`.`cart_info` VALUES (null, 1, 1, 2, now(), 1);
INSERT INTO `freeweb`.`cart_info` VALUES (null, 3, 1, 2, now(), 2);


CREATE TABLE `freeweb`.`index_info` (
	id int(11) not null AUTO_INCREMENT COMMENT '自增id',
    prod_id int(11) not null COMMENT '产品id',
    priority int(11) not null COMMENT '首页显示顺序, 越大越靠上',
    primary key (`id`),
    constraint `index_info_prod_id` foreign key (`prod_id`) references `freeweb`.`product_info` (`prod_id`) on delete cascade on update cascade
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '首页图片信息表';

INSERT INTO `freeweb`.`index_info` VALUES (null, 1, 1);
INSERT INTO `freeweb`.`index_info` VALUES (null, 2, 2);
INSERT INTO `freeweb`.`index_info` VALUES (null, 3, 4);
INSERT INTO `freeweb`.`index_info` VALUES (null, 4, 4);


CREATE TABLE `freeweb`.`menu_info` (
	id int(11) not null AUTO_INCREMENT COMMENT '自增id',
    class_id int(1) not null COMMENT '菜单栏目id, 可重复',
    priority int(2) not null COMMENT '按钮顺序: 0: 主按钮, 如果有子按钮, 则url被忽略; 其余: 子按钮, 从1开始, 由下至上排列',
	menu_name varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci not null COMMENT '按钮名称',
	link_url varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci not null default '' COMMENT '链接目标url',
    primary key (`id`)
) ENGINE = INNODB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表';

INSERT INTO `freeweb`.`menu_info` VALUES (null, 4, 0, '主页', 'index.html');
INSERT INTO `freeweb`.`menu_info` VALUES (null, 2, 0, '购物车', 'cart.html');
INSERT INTO `freeweb`.`menu_info` VALUES (null, 3, 0, '我的', 'user.html');
INSERT INTO `freeweb`.`menu_info` VALUES (null, 1, 0, '测试', 'test1.html');
INSERT INTO `freeweb`.`menu_info` VALUES (null, 1, 1, '测试子项1', 'test1.html');
INSERT INTO `freeweb`.`menu_info` VALUES (null, 1, 2, '测试子项2', 'test2.html');

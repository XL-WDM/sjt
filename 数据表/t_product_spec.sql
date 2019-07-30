drop table t_product_spec;
create table t_product_spec
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	spec_name varchar(128) not null comment '规格名称',
	price decimal default '0' not null comment '规格单价(单位：分)',
	spec_image varchar(512) not null comment '规格图片',
	stock_num int default '0' not null comment '规格库存',
	order_stock_num int default '0' not null comment '规格库存下单未结算数(<= 库存数量, 下单+n, 订单结束-n)',
	version int default '0' not null comment '版本号',
	constraint t_product_multi_spec_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品规格信息' engine=InnoDB
;

create index t_product_multi_spec_t_product_info_id_fk
	on t_product_spec (product_id)
;

INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (1, 1, '抗霾舒畅柠果膏', 7900, 'http://www.smallbug.top/images/product/product_01_00.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (2, 2, '解压双降柠果膏', 9900, 'http://www.smallbug.top/images/product/product_02_00.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (3, 3, '暖宫柠果膏', 9900, 'http://www.smallbug.top/images/product/product_03_00.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (4, 4, '鲜柠古力', 3900, 'http://www.smallbug.top/images/product/product_04_00.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (5, 5, '鲜橙古力', 7900, 'http://www.smallbug.top/images/product/product_05_00.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (6, 6, '栗子巧克力', 9900, 'http://www.smallbug.top/images/product/product_06_00.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (7, 7, '咖啡巧克力', 1000, 'http://www.smallbug.top/images/product/product_07_01.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (8, 7, '橙味巧克力', 1000, 'http://www.smallbug.top/images/product/product_07_02.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (9, 7, '焦糖巧克力', 1000, 'http://www.smallbug.top/images/product/product_07_03.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (10, 7, '原味巧克力', 900, 'http://www.smallbug.top/images/product/product_07_04.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (11, 7, '黑苦巧克力', 1000, 'http://www.smallbug.top/images/product/product_07_05.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (12, 7, '椰丝巧克力', 1000, 'http://www.smallbug.top/images/product/product_07_06.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (13, 7, '榛子巧克力', 900, 'http://www.smallbug.top/images/product/product_07_07.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (14, 8, '纯实牛肉干', 7900, 'http://www.smallbug.top/images/product/product_08_00.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (15, 9, '鲜虾薄脆', 6800, 'http://www.smallbug.top/images/product/product_09_00.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (16, 10, '健康运动款', 13900, 'http://www.smallbug.top/images/product/product_10_01.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (17, 10, '成熟男士款', 15900, 'http://www.smallbug.top/images/product/product_10_02.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (18, 10, '优雅女士款', 12900, 'http://www.smallbug.top/images/product/product_10_03.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (19, 11, '[杏福团圆]奶黄月饼', 15800, 'http://www.smallbug.top/images/product/product_11_01.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (20, 11, '[盛开的杏树]香茶月饼礼盒', 35800, 'http://www.smallbug.top/images/product/product_11_02.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (21, 11, '[月满杏树]月饼礼盒', 49800, 'http://www.smallbug.top/images/product/product_11_03.png', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (22, 7, '任性随机6颗', 5900, 'http://www.smallbug.top/images/product/product_07_08.jpg', 100, 0, 0);
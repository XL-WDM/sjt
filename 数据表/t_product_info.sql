drop table t_product_info;
create table t_product_info
(
	id int auto_increment comment '自增id'
		primary key,
	product_name varchar(128) not null comment '商品名称',
	supplier_id int not null comment '供应商id',
	one_level_category int null comment '一级分类',
	two_level_category int null comment '二级分类',
	three_level_category int null comment '三级分类',
	publish_status varchar(1) not null comment '商品发布状态(0-无效, 1-上架, 2-下架)',
	new_arrivals varchar(1) default '0' not null comment '是否为新品推荐(0-否, 1-是)',
	main_image varchar(512) not null comment '主图',
	product_details text null comment '商品详情',
	descript varchar(512) null comment '商品描述',
	spec_grop_name varchar(128) null comment '规格组名称',
	spec_type varchar(1) default '1' not null comment '规格类型(1-单规格, 2-多规格)',
	create_date datetime default CURRENT_TIMESTAMP not null comment '商品录入时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '商品更新时间',
	constraint t_product_info_t_supplier_info_id_fk
		foreign key (supplier_id) references t_supplier_info (id),
	constraint t_product_info_t_product_category_id_fk
		foreign key (one_level_category) references t_product_category (id),
	constraint t_product_info_t_product_category_id_fk_2
		foreign key (two_level_category) references t_product_category (id),
	constraint t_product_info_t_product_category_id_fk_3
		foreign key (three_level_category) references t_product_category (id)
)
comment '商品信息表' engine=InnoDB
;

create index t_product_info_t_product_category_id_fk
	on t_product_info (one_level_category)
;

create index t_product_info_t_product_category_id_fk_2
	on t_product_info (two_level_category)
;

create index t_product_info_t_product_category_id_fk_3
	on t_product_info (three_level_category)
;

create index t_product_info_t_supplier_info_id_fk
	on t_product_info (supplier_id)
;

INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (1, '抗霾舒畅柠果膏', 1, 1, 2, 3, '1', '1', 'http://www.smallbug.top/images/product/product_01.png', null, null, null, '1', '2019-08-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (2, '解压双降柠果膏', 1, 1, 2, 3, '1', '1', 'http://www.smallbug.top/images/product/product_01.png', null, null, null, '1', '2019-11-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (3, '暖宫柠果膏', 1, 1, 2, 3, '1', '0', 'http://www.smallbug.top/images/product/product_01.png', null, null, null, '1', '2019-07-18 17:24:26', '2019-07-11 17:24:26');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (4, '鲜柠古力', 1, 1, 2, 4, '1', '1', 'http://www.smallbug.top/images/product/product_01.png', null, null, null, '1', '2019-11-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (5, '鲜橙古力', 1, 1, 2, 4, '1', '0', 'http://www.smallbug.top/images/product/product_01.png', null, null, null, '1', '2019-01-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (6, '栗子巧克力', 1, 1, 2, 5, '1', '1', 'http://www.smallbug.top/images/product/product_01.png', null, null, null, '1', '2019-06-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (7, 'DIY巧克力', 1, 1, 2, 5, '1', '0', 'http://www.smallbug.top/images/product/product_00.jpg', null, null, '手作巧克力系列', '2', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (8, '纯实牛肉干', 1, 1, 2, 6, '1', '0', 'http://www.smallbug.top/images/product/product_10.png', null, null, null, '1', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (9, '鲜虾薄脆', 1, 1, 2, 6, '1', '0', 'http://www.smallbug.top/images/product/product_11.jpg', null, null, null, '1', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (10, '随饮小憩', 1, 1, 2, 7, '1', '0', 'http://www.smallbug.top/images/product/product_12.png', null, null, '随饮小憩', '2', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (11, '梵高月饼', 1, 1, 2, 7, '1', '0', 'http://www.smallbug.top/images/product/product_13.png', null, null, '梵高月饼', '2', '2019-07-24 09:25:50', '2019-07-24 09:25:50');

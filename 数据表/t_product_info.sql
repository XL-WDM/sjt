drop table t_product_info;
create table t_product_info
(
	id int auto_increment comment '自增id'
		primary key,
	product_name varchar(32) not null comment '商品名称',
	supplier_id int not null comment '供应商id',
	one_level_category int null comment '一级分类',
	two_level_category int null comment '二级分类',
	three_level_category int null comment '三级分类',
	price bigint not null comment '商品价格(单位：分)',
	discount_amount bigint default '0' not null comment '优惠金额(单位：分)',
	publish_status varchar(1) not null comment '发布状态(0-删除, 1-上架, 2-下架)',
	descript varchar(512) null comment '商品描述',
	product_details text null comment '商品详情',
	create_date datetime default CURRENT_TIMESTAMP not null comment '商品录入时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '商品更新时间',
	constraint t_product_info_t_supplier_info_id_fk
		foreign key (supplier_id) references t_supplier_info (id)
)
comment '商品信息表' engine=InnoDB
;

create index t_product_info_t_supplier_info_id_fk
	on t_product_info (supplier_id)
;



INSERT INTO stj.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, price, discount_amount, publish_status, descript, product_details, create_date, update_date) VALUES (1, '抗霾舒畅柠果膏', 1, 5, null, null, 7900, 0, '1', '抗霾舒畅柠果膏280ml', null, '2019-07-11 17:24:26', '2019-07-11 17:24:26');

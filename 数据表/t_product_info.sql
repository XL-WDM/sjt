drop table t_product_info;
create table t_product_info
(
	id int auto_increment comment '自增id'
		primary key,
	product_code varchar(32) not null comment '商品编码',
	product_name varchar(32) not null comment '商品名称',
	supplier_id int not null comment '供应商id',
	one_level_category int null comment '一级分类',
	two_level_category int null comment '二级分类',
	three_level_category int null comment '三级分类',
	price bigint not null comment '商品价格(单位：分)',
	discount_amount bigint default '0' not null comment '优惠金额(单位：分)',
	publish_status varchar(1) not null comment '发布状态(0-删除, 1-上架, 2-下架)',
	audit_status varchar(1) not null comment '审核状态(0-不通过, 1-通过)',
	descript varchar(512) null comment '商品描述',
	create_date datetime default CURRENT_TIMESTAMP not null comment '商品录入时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '商品更新时间',
	constraint t_product_info_t_supplier_info_id_fk
		foreign key (supplier_id) references t_supplier_info (id)
)comment '商品信息表' engine=InnoDB;

create index t_product_info_t_supplier_info_id_fk
	on t_product_info (supplier_id);


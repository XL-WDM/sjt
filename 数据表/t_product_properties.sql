drop table t_product_properties;
create table t_product_properties
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	properties_name varchar(36) not null comment '属性名称',
	properties_value varchar(128) not null comment '属性值',
	order_no int default 0 not null comment '排序编号'
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_product_properties_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品属性表' engine=InnoDB;

create index t_product_properties_t_product_info_id_fk
	on t_product_properties (product_id);


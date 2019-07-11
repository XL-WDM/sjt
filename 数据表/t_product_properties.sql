drop table t_product_properties;
create table t_product_properties
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	properties_name varchar(36) not null comment '属性名称',
	properties_value varchar(128) not null comment '属性值',
	sort_num int default '0' not null comment '排序编号',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_product_properties_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品属性表' engine=InnoDB
;

create index t_product_properties_t_product_info_id_fk
	on t_product_properties (product_id)
;

INSERT INTO stj.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (1, 1, '产地', '上海普陀', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO stj.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (2, 1, '保质期', '90天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO stj.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (3, 1, '净含量', '280ml', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO stj.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (4, 1, '存储方式', '阴凉干燥30℃', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO stj.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (5, 1, '成分', '柠檬、橙皮、冰糖、干玫瑰等', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');

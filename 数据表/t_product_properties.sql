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

INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (1, 1, '产地', '上海青浦', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (2, 1, '保质期', '90天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (3, 1, '净含量', '280ml', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (4, 1, '存储方式', '阴凉干燥30°C以下', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (10, 1, '食用方法', '可冲泡 可舀食 可当健康早餐', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (11, 2, '产地', '上海青浦', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (12, 2, '保质期', '90天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (13, 2, '净含量', '280ml', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (14, 2, '存储方式', '阴凉干燥30°C以下', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (15, 2, '食用方法', '可冲泡 可舀食 可当健康早餐', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (16, 3, '产地', '上海青浦', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (17, 3, '保质期', '90天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (18, 3, '净含量', '280ml', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (19, 3, '存储方式', '阴凉干燥30°C以下', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (20, 3, '食用方法', '可冲泡 可舀食 可当健康早餐', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (21, 4, '产地', '上海青浦', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (22, 4, '保质期', '30天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (23, 4, '净含量', '16g*6', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (24, 4, '存储方式', '冷藏保存', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (25, 4, '食用方法', '即食', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (26, 5, '产地', '上海青浦', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (27, 5, '保质期', '30天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (28, 5, '净含量', '20g*6', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (29, 5, '食用方法', '即食', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (30, 5, '食用方法', '即食', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (31, 6, '产地', '上海青浦', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (32, 6, '保质期', '120天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (33, 6, '净含量', '12g*6', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (34, 6, '存储方式', '冷冻保存', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (35, 6, '食用方法', '即食', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (36, 7, '产地', '上海青浦', 1, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (37, 7, '保质期', '30天', 2, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (38, 7, '净含量', '11g*6', 3, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (39, 7, '存储方式', '冷藏保存', 4, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (40, 7, '食用方法', '即食', 5, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (41, 8, '产地', '上海青浦', 1, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (42, 8, '保质期', '30天', 2, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (43, 8, '净含量', '120g', 3, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (44, 8, '存储方式', '阴凉干燥30°C以下', 4, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (45, 8, '食用方法', '即食', 5, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (46, 9, '产地', '上海青浦', 1, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (47, 9, '保质期', '15天', 2, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (48, 9, '净含量', '10g左右*6', 3, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (49, 9, '存储方式', '密封阴凉干燥30°C以下', 4, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (50, 9, '食用方法', '即食', 5, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (51, 10, '产地', '上海青浦', 1, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (52, 10, '保质期', '90天', 2, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (53, 10, '净含量', '400g/盒', 3, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (54, 10, '存储方式', '阴凉干燥30°C以下', 4, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (55, 10, '食用方法', '即食', 5, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (56, 11, '产地', '广州深圳', 1, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (57, 11, '保质期', '60天', 2, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (58, 11, '净含量', '480g/盒', 3, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (59, 11, '存储方式', '阴凉干燥30°C以下', 4, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (60, 11, '食用方法', '即食', 5, '2019-07-30 17:58:11', '2019-07-30 17:58:11');

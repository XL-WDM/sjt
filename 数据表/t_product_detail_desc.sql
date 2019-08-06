drop table t_product_detail_desc;
create table t_product_detail_desc
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	type varchar(1) default '1' not null comment '描述内容类型(1-图片, 2-文本)',
	content text not null comment '内容',
	sort_num int default '0' not null comment '排序编号',
	constraint t_product_detail_desc_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品详情描述内容' engine=InnoDB
;

create index t_product_detail_desc_t_product_info_id_fk
	on t_product_detail_desc (product_id)
;


drop table t_product_pic;
create table t_product_pic
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	pic_url varchar(512) not null comment '图片URL',
	descript varchar(64) null comment '图片描述',
	sort_num int default '0' not null comment '图片排序编号',
	is_master varchar(1) default '0' not null comment '是否主图(0-否, 1-是)',
  status varchar(1) default '1' not null comment '状态(0-无效, 1-有效)',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_product_pic_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品图片表' engine=InnoDB
;

create index t_product_pic_t_product_info_id_fk
	on t_product_pic (product_id)
;



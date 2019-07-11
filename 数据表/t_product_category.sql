drop table t_product_category;
create table t_product_category
(
	id int auto_increment comment '自增id'
		primary key,
	name varchar(32) not null comment '分类名称',
	code varchar(32) not null comment '分类编码',
	level int default '1' not null comment '分类层级',
	status int default '1' not null comment '状态(0-删除, 1-可用)',
	icon varchar(36) null comment '分类图标',
	img_url varchar(512) null comment '分类图片',
	url varchar(512) null comment '分类链接',
	pid int null comment '父级编号',
	create_date datetime default current_timestamp not null comment '创建时间',
	update_date datetime default current_timestamp not null comment '更新时间',
	constraint t_product_category_t_product_category_id_fk
		foreign key (pid) references t_product_category (id)
) comment '商品分类表' engine=innodb;

create index t_product_category_t_product_category_id_fk
	on t_product_category (pid);


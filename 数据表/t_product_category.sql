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
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_product_category_t_product_category_id_fk
		foreign key (pid) references t_product_category (id)
)
comment '商品分类表' engine=InnoDB
;

create index t_product_category_t_product_category_id_fk
	on t_product_category (pid)
;



INSERT INTO stj.t_product_category (id, name, code, level, status, icon, img_url, url, pid, create_date, update_date) VALUES (3, '随饮小憩系列', 'SYXQ', 1, 1, null, null, null, null, '2019-07-11 15:27:59', '2019-07-11 15:27:59');
INSERT INTO stj.t_product_category (id, name, code, level, status, icon, img_url, url, pid, create_date, update_date) VALUES (4, '健康蔬果膏系列', 'JKSGG', 1, 1, null, null, null, null, '2019-07-11 15:27:59', '2019-07-11 15:27:59');
INSERT INTO stj.t_product_category (id, name, code, level, status, icon, img_url, url, pid, create_date, update_date) VALUES (5, '养生果膏系列', 'YSGG', 1, 1, null, null, null, null, '2019-07-11 17:23:41', '2019-07-11 17:23:41');

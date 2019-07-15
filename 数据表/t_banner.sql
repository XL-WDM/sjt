drop table t_banner;
create table t_banner
(
	id int auto_increment comment '自增id'
		primary key,
	banner_name varchar(36) not null comment 'banner名称',
	banner_type varchar(1) default '1' not null comment 'banner类型(1-首页top轮播图, 2-GIF小视频, 3-山田日记banner, 4-首页center轮播图)',
	img_url varchar(128) not null comment '图片url地址',
	img_version bigint not null comment '图片版本(?_v=xxxxxxxx)',
	url varchar(128) null comment 'banner跳转地址',
	url_type varchar(1) default '1' not null comment '地址类型(1-内部地址, 2-外部地址)',
	status varchar(1) default '1' not null comment '状态(0-无效 1-有效)',
	sort_num int default '0' not null comment '排序编号',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间'
)
comment 'banner配置表' engine=InnoDB
;




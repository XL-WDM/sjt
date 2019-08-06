drop table t_banner;
create table t_banner
(
	id int auto_increment comment '自增id'
		primary key,
	banner_name varchar(36) not null comment 'banner名称',
	banner_type varchar(1) default '1' not null comment 'banner类型(1-首页top轮播图, 2-GIF小视频, 3-山田日记banner, 4-首页center轮播图)',
	img_url varchar(512) not null comment '图片url地址',
	img_version bigint not null comment '图片版本(?_v=xxxxxxxx)',
	url varchar(512) null comment 'banner跳转地址',
	url_type varchar(1) default '1' not null comment '地址类型(1-内部地址, 2-外部地址)',
	status varchar(1) default '1' not null comment '状态(0-无效 1-有效)',
	sort_num int default '0' not null comment '排序编号',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间'
)
comment 'banner配置表' engine=InnoDB
;

INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (1, '轮播图1', '1', 'http://www.smallbug.top/images/banner/banner_top_01.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 1, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (2, '轮播图2', '1', 'http://www.smallbug.top/images/banner/banner_top_02.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 2, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (3, '轮播图3', '1', 'http://www.smallbug.top/images/banner/banner_top_03.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 3, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (4, 'GIF小视频', '2', 'http://www.smallbug.top/images/banner/gif_banner.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 0, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (5, '山田日记', '3', 'http://www.smallbug.top/images/banner/note_banner.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 0, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (6, '中部轮播图1', '4', 'http://www.smallbug.top/images/banner/banner_center_01.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 1, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (7, '中部轮播图2', '4', 'http://www.smallbug.top/images/banner/banner_center_02.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 2, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (8, '中部轮播图3', '4', 'http://www.smallbug.top/images/banner/banner_center_03.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 3, '2019-07-15 11:11:12', '2019-07-15 11:11:12');

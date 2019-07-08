create table t_user
(
	id int auto_increment comment '用户ID'
		primary key,
	username varchar(36) null comment '用户名',
	email varchar(64) null comment '邮箱',
	phone varchar(11) null comment '手机号',
	password varchar(64) null comment '密码',
	face_url varchar(128) null comment '头像',
	nickname varchar(36) null comment '昵称',
	sex varchar(1) null comment '性别',
	birtrday date null comment '生日',
	status varchar(2) default '1' not null comment '状态(0-冻结 1-可用)',
	register_date datetime default CURRENT_TIMESTAMP not null comment '注册时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	version int default '0' not null comment '版本号',
	constraint t_user_username_uindex
		unique (username),
	constraint t_user_email_uindex
		unique (email),
	constraint t_user_phone_uindex
		unique (phone)
)
comment '用户信息表' engine=InnoDB;

insert into stj.t_user (id, username, email, phone, password, face_url, nickname, sex, birtrday, status, register_date, update_date, version) values (1, 'test', '15073820717@163.com', '15073820717', '21e4b8b96569fcbe56c6c7e3762240fd', null, null, null, null, '1', '2019-07-08 18:13:36', '2019-07-08 18:13:36', 0);

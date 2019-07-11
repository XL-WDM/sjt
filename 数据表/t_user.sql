drop table t_user;
create table t_user
(
	id int auto_increment comment '用户id'
		primary key,
	username varchar(36) null comment '用户名',
	email varchar(64) null comment '邮箱',
	phone varchar(11) null comment '手机号',
	password varchar(64) null comment '密码',
	face_url varchar(128) null comment '头像',
	nickname varchar(36) null comment '昵称',
	sex varchar(1) null comment '性别',
	birtrday date null comment '生日',
	identity_card_type varchar(1) null comment '证件类型(1-身份证, 2.军官证, 3.护照)',
	identity_card varchar(36) null comment '证件号码',
	user_point int default '0' not null comment '积分',
	member_level varchar(1) default '0' not null comment '会员级别(0-普通用户, 1-普通会员)',
	status varchar(2) default '1' not null comment '状态(0-冻结 1-可用)',
	register_date datetime default CURRENT_TIMESTAMP not null comment '注册时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	version int default '0' not null comment '版本号',
	constraint t_user_username_uindex
		unique (username),
	constraint t_user_email_uindex
		unique (email),
	constraint t_user_phone_uindex
		unique (phone),
	constraint t_user_identity_card_uindex
		unique (identity_card)
)
comment '用户信息表' engine=InnoDB
;


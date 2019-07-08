create table t_user
(
	ID int auto_increment comment '用户ID'
		primary key,
	USERNAME varchar(36) null comment '用户名',
	EMAIL varchar(64) null comment '邮箱',
	PHONE varchar(11) null comment '手机号',
	PASSWORD varchar(64) null comment '密码',
	FACE_URL varchar(128) null comment '头像',
	NICKNAME varchar(36) null comment '昵称',
	SEX varchar(1) null comment '性别',
	BIRTRDAY date null comment '生日',
	STATUS varchar(2) default '1' not null comment '状态(0-冻结 1-可用)',
	REGISTER_DATE datetime default CURRENT_TIMESTAMP not null comment '注册时间',
	UPDATE_DATE datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	VERSION int default '0' not null comment '版本号',
	constraint T_USER_USERNAME_uindex
		unique (USERNAME),
	constraint T_USER_EMAIL_uindex
		unique (EMAIL),
	constraint T_USER_PHONE_uindex
		unique (PHONE)
)
comment '用户信息表' engine=InnoDB;

INSERT INTO stj.t_user (ID, USERNAME, EMAIL, PHONE, PASSWORD, FACE_URL, NICKNAME, SEX, BIRTRDAY, STATUS, REGISTER_DATE, UPDATE_DATE, VERSION) VALUES (1, 'test', '15073820717@163.com', '15073820717', '21e4b8b96569fcbe56c6c7e3762240fd', null, null, null, null, '1', '2019-07-08 18:13:36', '2019-07-08 18:13:36', 0);

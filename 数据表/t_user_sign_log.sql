drop table t_user_sign_log;
create table t_user_sign_log
(
	id int auto_increment comment '自增id'
		primary key,
	user_id int not null comment '用户id',
	token varchar(64) not null comment 'token',
	sign_date datetime default CURRENT_TIMESTAMP not null comment '登陆时间',
	expiration_time datetime not null comment '过期时间(时间戳)',
	constraint t_user_sign_log_token_uindex
		unique (token)
)
comment '用户登录日志表' engine=InnoDB
;


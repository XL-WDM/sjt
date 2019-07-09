drop table t_user_sign_log;
create table t_user_sign_log
(
    id int primary key comment '自增id' auto_increment,
    user_id int not null comment '用户id',
    token varchar(64) not null comment 'token',
    sign_date datetime default now() not null comment '登录时间',
    expiration_time datetime not null comment '过期时间(时间戳)'
);
create unique index t_user_sign_log_token_uindex on t_user_sign_log (token);
alter table t_user_sign_log comment = '用户登录日志表';

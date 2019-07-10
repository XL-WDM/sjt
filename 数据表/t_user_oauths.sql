drop table t_user_oauths;
create table t_user_oauths
(
    id int primary key comment '自增id' auto_increment,
    user_id int not null comment '用户id',
    oauth_id varchar(128) not null comment '授权id(比如: openid)',
    union_id varchar(128) null comment 'unionid',
    status varchar(1) default 1 not null comment '授权状态(0-取消授权, 1-已授权)',
    oauth_type varchar(1) not null comment '授权类型(1-微信小程序, 2-微信公众号, 3-微信开放平台, 4-qq, 5-微博)',
    oauth_date datetime default now() not null comment '授权时间',
    update_date datetime default now() not null comment '更新时间',
    constraint t_user_oauths_t_user_id_fk foreign key (user_id) references t_user (id)
);
alter table t_user_oauths comment = '用户授权登录表';

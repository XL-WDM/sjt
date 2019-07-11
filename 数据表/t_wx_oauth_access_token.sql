drop table t_wx_oauth_access_token;
create table t_wx_oauth_access_token
(
	id int auto_increment comment '自增id'
		primary key,
	access_token varchar(128) not null comment 'access_token',
	expires_in bigint not null comment 'access_token接口调用凭证超时时间，单位（秒）',
	expires_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '凭证过期时间',
	refresh_token varchar(128) not null comment '用户刷新access_token',
	openid varchar(128) null comment '用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的openid',
	scope varchar(64) null comment '用户授权的作用域，使用逗号（,）分隔',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	refresh_date datetime default CURRENT_TIMESTAMP not null comment '刷新时间',
	constraint t_wx_oauth_access_token_access_token_uindex
		unique (access_token)
)
comment '网页授权记录表' engine=InnoDB
;


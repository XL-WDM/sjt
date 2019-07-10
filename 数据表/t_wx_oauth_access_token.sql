drop table t_wx_oauth_access_token;
create table t_wx_oauth_access_token
(
  id int primary key comment '自增id' auto_increment,
  access_token varchar(128) not null comment 'access_token',
  expires_in bigint not null comment 'access_token接口调用凭证超时时间，单位（秒）',
  refresh_token varchar(128) not null comment '用户刷新access_token',
  openid varchar(128) comment '用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的openid',
  scope varchar(64) comment '用户授权的作用域，使用逗号（,）分隔'
);
create unique index t_wx_oauth_access_token_access_token_uindex on t_wx_oauth_access_token (access_token);
alter table t_wx_oauth_access_token comment = '网页授权记录表';
ALTER TABLE t_wx_oauth_access_token ADD create_date datetime DEFAULT now() NOT NULL COMMENT '创建时间';
ALTER TABLE t_wx_oauth_access_token ADD refresh_date datetime DEFAULT now() NOT NULL COMMENT '刷新时间';

drop table t_wx_snsapi_userinfo;
create table t_wx_snsapi_userinfo
(
	id int auto_increment comment ' 自增id'
		primary key,
	openid varchar(128) not null comment '用户的唯一标识',
	nickname varchar(64) not null comment '用户昵称',
	sex varchar(1) null comment '用户的性别，值为1时是男性，值为2时是女性，值为0时是未知',
	country varchar(64) null comment '国家，如中国为CN',
	province varchar(64) null comment '用户个人资料填写的省份',
	city varchar(64) null comment '普通用户个人资料填写的城市',
	headimgurl varchar(512) null comment '用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。',
	privilege varchar(128) null comment '用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）',
	unionid varchar(128) null comment '只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。',
	constraint t_wx_snsapi_userinfo_openid_uindex
		unique (openid)
)
comment '微信用户信息' engine=InnoDB
;


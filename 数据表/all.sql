drop table t_user;

-- ########################### 用户信息表 ###########################
create table t_user
(
	id int auto_increment comment '用户id'
		primary key,
	username varchar(36) null comment '用户名',
	email varchar(64) null comment '邮箱',
	phone varchar(11) null comment '手机号',
	password varchar(64) null comment '密码',
	face_url varchar(512) null comment '头像',
	nickname varchar(36) null comment '昵称',
	sex varchar(1) default '0' not null comment '性别(0-未设置, 1-男, 2-女)',
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
comment '用户信息表' engine=InnoDB CHARSET=utf8
;



-- ########################### 用户授权登录表 ###########################
create table t_user_oauths
(
	id int auto_increment comment '自增id'
		primary key,
	user_id int not null comment '用户id',
	oauth_id varchar(128) not null comment '授权id(比如: openid)',
	union_id varchar(128) null comment 'unionid',
	session_key varchar(64) null comment '微信小程序session_key',
	status varchar(1) default '1' not null comment '授权状态(0-取消授权, 1-已授权)',
	oauth_type varchar(1) not null comment '授权类型(1-微信小程序, 2-微信公众号 or 微信开放平台, 3-QQ, 4-微博)',
	oauth_date datetime default CURRENT_TIMESTAMP not null comment '授权时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_user_oauths_t_user_id_fk
		foreign key (user_id) references t_user (id)
)
comment '用户授权登录表' engine=InnoDB CHARSET=utf8
;

create index t_user_oauths_t_user_id_fk
	on t_user_oauths (user_id)
;



-- ########################### 用户登录日志表 ###########################
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
comment '用户登录日志表' engine=InnoDB CHARSET=utf8
;



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
comment '网页授权记录表' engine=InnoDB CHARSET=utf8
;




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
comment '微信用户信息' engine=InnoDB CHARSET=utf8
;



create table t_notes
(
	id int auto_increment comment '自增id'
		primary key,
	title varchar(64) not null comment '标题',
	big_img varchar(512) not null comment '大图',
	small_img varchar(512) not null comment '小图',
	note_context text not null comment '内容',
	is_craftsman varchar(1) default '0' not null comment '是否为匠人精神日记(0-否, 1-是)',
	status varchar(1) default '1' not null comment '状态(0-可用, 1-不可用)',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间'
)
comment '山田日记' engine=InnoDB CHARSET=utf8
;



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
comment 'banner配置表' engine=InnoDB CHARSET=utf8
;

INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (1, '轮播图1', '1', 'http://www.smallbug.top/images/banner/banner_top_01.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 1, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (2, '轮播图2', '1', 'http://www.smallbug.top/images/banner/banner_top_02.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 2, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (3, '轮播图3', '1', 'http://www.smallbug.top/images/banner/banner_top_03.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 3, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (4, 'GIF小视频', '2', 'http://www.smallbug.top/images/banner/gif_banner.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 0, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (5, '山田日记', '3', 'http://www.smallbug.top/images/banner/note_banner.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 0, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (6, '中部轮播图1', '4', 'http://www.smallbug.top/images/banner/banner_center_01.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 1, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (7, '中部轮播图2', '4', 'http://www.smallbug.top/images/banner/banner_center_02.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 2, '2019-07-15 11:11:12', '2019-07-15 11:11:12');
INSERT INTO sjt.t_banner (id, banner_name, banner_type, img_url, img_version, url, url_type, status, sort_num, create_date, update_date) VALUES (8, '中部轮播图3', '4', 'http://www.smallbug.top/images/banner/banner_center_03.png', 1563160127343, 'https://www.baidu.com/', '2', '1', 3, '2019-07-15 11:11:12', '2019-07-15 11:11:12');



create table t_address
(
	id int auto_increment comment '自增id'
		primary key,
	user_id int not null comment '用户id',
	contacts varchar(64) not null comment '联系人',
	phone varchar(11) not null comment '联系电话',
	province varchar(64) not null comment '省',
	city varchar(64) not null comment '市',
	county varchar(64) not null comment '县',
	address varchar(512) not null comment '详细地址',
	door_number varchar(128) not null comment '门牌号',
	tag varchar(1) not null comment '地址标签(1-家, 2-公司, 3-学校)',
	contacts_call varchar(1) not null comment '称呼(1-先生, 2-女士)',
	is_default varchar(1) not null comment '是否默认地址(0-不是, 1-是)',
	status varchar(1) not null comment '状态(0-无效, 1-有效)',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_address_t_user_id_fk
		foreign key (user_id) references t_user (id)
)
comment '收货地址表' engine=InnoDB CHARSET=utf8
;

create index t_address_t_user_id_fk
	on t_address (user_id)
;


create table t_product_category
(
	id int auto_increment comment '自增id'
		primary key,
	category_name varchar(32) not null comment '分类名称',
	category_level int default '1' not null comment '分类层级',
	status int default '1' not null comment '状态(0-删除, 1-可用)',
	icon varchar(36) null comment '分类图标',
	img_url varchar(512) null comment '分类图片URL',
	url varchar(512) null comment '链接',
	pid int null comment '父级编号',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_product_category_t_product_category_id_fk
		foreign key (pid) references t_product_category (id)
)
comment '商品分类表' engine=InnoDB CHARSET=utf8
;

create index t_product_category_t_product_category_id_fk
	on t_product_category (pid)
;

INSERT INTO sjt.t_product_category (id, category_name, category_level, status, icon, img_url, url, pid, create_date, update_date) VALUES (1, '山田产品', 1, 1, null, null, null, null, '2019-07-19 10:57:11', '2019-07-19 10:57:11');
INSERT INTO sjt.t_product_category (id, category_name, category_level, status, icon, img_url, url, pid, create_date, update_date) VALUES (2, '手作农产品', 2, 1, null, null, null, 1, '2019-07-19 10:57:11', '2019-07-19 10:57:11');
INSERT INTO sjt.t_product_category (id, category_name, category_level, status, icon, img_url, url, pid, create_date, update_date) VALUES (3, '养生果膏系列', 3, 1, null, 'http://www.smallbug.top/images/category/category_ysgg.png', 'https://www.baidu.com/', 2, '2019-07-11 15:27:59', '2019-07-11 15:27:59');
INSERT INTO sjt.t_product_category (id, category_name, category_level, status, icon, img_url, url, pid, create_date, update_date) VALUES (4, '鲜果古力系列', 3, 1, null, 'http://www.smallbug.top/images/category/category_ysgg.png', 'https://www.baidu.com/', 2, '2019-07-11 15:27:59', '2019-07-11 15:27:59');
INSERT INTO sjt.t_product_category (id, category_name, category_level, status, icon, img_url, url, pid, create_date, update_date) VALUES (5, '手作巧克力系列', 3, 1, null, 'http://www.smallbug.top/images/category/category_ysgg.png', 'https://www.baidu.com/', 2, '2019-07-11 17:23:41', '2019-07-11 17:23:41');
INSERT INTO sjt.t_product_category (id, category_name, category_level, status, icon, img_url, url, pid, create_date, update_date) VALUES (6, '手作解馋包系列', 3, 1, '', 'http://www.smallbug.top/images/category/category_ysgg.png', 'https://www.baidu.com/', 2, '2019-07-11 17:23:41', '2019-07-11 17:23:41');
INSERT INTO sjt.t_product_category (id, category_name, category_level, status, icon, img_url, url, pid, create_date, update_date) VALUES (7, '精致礼盒装系列', 3, 1, '', 'http://www.smallbug.top/images/category/category_jksg.png', 'https://www.baidu.com/', 2, '2019-07-11 17:23:41', '2019-07-11 17:23:41');




create table t_supplier_info
(
	id int auto_increment comment '自增id'
		primary key,
	supplier_name varchar(36) not null comment '供应商名称',
	supplier_type varchar(1) not null comment '供应商类型(1-自营, 2-品台)',
	link_man varchar(16) null comment '供应商联系人',
	phone varchar(16) not null comment '联系电话',
	bank_name varchar(36) not null comment '供应商开户银行名称',
	bank_account varchar(36) not null comment '供应商银行账号',
	address varchar(256) not null comment '供应商地址',
	status varchar(1) default '1' not null comment '状态(0-禁用, 1-启动)',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间'
)
comment '供应商信息表' engine=InnoDB CHARSET=utf8
;

INSERT INTO sjt.t_supplier_info (id, supplier_name, supplier_type, link_man, phone, bank_name, bank_account, address, status, create_date, update_date) VALUES (1, '山田尖', '1', '山田尖', '15555555555', '交通银行', '44444444444444', '上海', '1', '2019-07-11 17:21:30', '2019-07-11 17:21:30');





create table t_product_info
(
	id int auto_increment comment '自增id'
		primary key,
	product_name varchar(128) not null comment '商品名称',
	supplier_id int not null comment '供应商id',
	one_level_category int null comment '一级分类',
	two_level_category int null comment '二级分类',
	three_level_category int null comment '三级分类',
	publish_status varchar(1) not null comment '商品发布状态(0-无效, 1-上架, 2-下架)',
	new_arrivals varchar(1) default '0' not null comment '是否为新品推荐(0-否, 1-是)',
	main_image varchar(512) not null comment '主图',
	product_details text null comment '商品详情',
	descript varchar(512) null comment '商品描述',
	spec_grop_name varchar(128) null comment '规格组名称',
	spec_type varchar(1) default '1' not null comment '规格类型(1-单规格, 2-多规格)',
	create_date datetime default CURRENT_TIMESTAMP not null comment '商品录入时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '商品更新时间',
	constraint t_product_info_t_supplier_info_id_fk
		foreign key (supplier_id) references t_supplier_info (id),
	constraint t_product_info_t_product_category_id_fk
		foreign key (one_level_category) references t_product_category (id),
	constraint t_product_info_t_product_category_id_fk_2
		foreign key (two_level_category) references t_product_category (id),
	constraint t_product_info_t_product_category_id_fk_3
		foreign key (three_level_category) references t_product_category (id)
)
comment '商品信息表' engine=InnoDB CHARSET=utf8
;

create index t_product_info_t_product_category_id_fk
	on t_product_info (one_level_category)
;

create index t_product_info_t_product_category_id_fk_2
	on t_product_info (two_level_category)
;

create index t_product_info_t_product_category_id_fk_3
	on t_product_info (three_level_category)
;

create index t_product_info_t_supplier_info_id_fk
	on t_product_info (supplier_id)
;

INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (1, '抗霾舒畅柠果膏', 1, 1, 2, 3, '1', '1', 'http://www.smallbug.top/images/product/product_01_00.png', '解压生津润喉增强抵抗力', '纯手工制作冰糖柠檬膏抵抗雾霾舒畅呼吸', null, '1', '2019-08-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (2, '解压双降柠果膏', 1, 1, 2, 3, '1', '1', 'http://www.smallbug.top/images/product/product_02_00.png', '生津润喉降燥降火', '生津润喉降燥降火', null, '1', '2019-11-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (3, '暖宫柠果膏', 1, 1, 2, 3, '1', '0', 'http://www.smallbug.top/images/product/product_03_00.jpg', '女王节福利顺丰包邮当归暖宫柠檬膏美颜滋润缓解生理期不适症状无添加冰糖柠檬膏', '女王节福利顺丰包邮当归暖宫柠檬膏美颜滋润缓解生理期不适症状无添加冰糖柠檬膏', null, '1', '2019-07-18 17:24:26', '2019-07-11 17:24:26');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (4, '鲜柠古力', 1, 1, 2, 4, '1', '1', 'http://www.smallbug.top/images/product/product_04_00.png', '新品上市鲜柠古力柠檬巧克力片酸甜可口（2盒顺丰包邮）', '新品上市鲜柠古力柠檬巧克力片酸甜可口（2盒顺丰包邮）', null, '1', '2019-11-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (5, '鲜橙古力', 1, 1, 2, 4, '1', '0', 'http://www.smallbug.top/images/product/product_05_00.jpg', '限时惊喜套餐柠檬精的最爱：鲜柠古力+抗霾舒畅柠果膏', '限时惊喜套餐柠檬精的最爱：鲜柠古力+抗霾舒畅柠果膏', null, '1', '2019-01-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (6, '栗子巧克力', 1, 1, 2, 5, '1', '1', 'http://www.smallbug.top/images/product/product_06_00.jpg', '新品上市栗子巧克力致敬经典顺丰包邮', '新品上市栗子巧克力致敬经典顺丰包邮', null, '1', '2019-06-11 17:24:26', '2019-07-11 17:24:26');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (7, 'DIY巧克力', 1, 1, 2, 5, '1', '0', 'http://www.smallbug.top/images/product/product_07_00.png', 'DIY手作巧克力七种口味随意搭配6颗起发', '七种口味随意搭配6颗起发', '手作巧克力系列', '2', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (8, '纯实牛肉干', 1, 1, 2, 6, '1', '0', 'http://www.smallbug.top/images/product/product_08_00.jpg', '山尖田——纯实牛肉干 幸福双旦季 纯手工制作无添加 只选顺丰', '幸福双旦季 纯手工制作无添加 只选顺丰', null, '1', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (9, '鲜虾薄脆', 1, 1, 2, 6, '1', '0', 'http://www.smallbug.top/images/product/product_09_00.jpg', '山尖田——鲜虾薄脆虾饼 纯手作制作无添加 保质期短建议尽快食用 顺丰快递幸福双旦季', '纯手作制作无添加 保质期短建议尽快食用 顺丰快递幸福双旦季', null, '1', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (10, '随饮小憩', 1, 1, 2, 7, '1', '0', 'http://www.smallbug.top/images/product/product_10_00.jpg', '随饮小憩——中式下午茶礼盒 圣诞优惠季全场八折买一送二柠檬膏牛肉干虾饼杏仁瓦片芝士坚果酥紫草膏', '圣诞优惠季全场八折买一送二柠檬膏牛肉干虾饼杏仁瓦片芝士坚果酥紫草膏', '随饮小憩款式', '2', '2019-07-24 09:25:50', '2019-07-24 09:25:50');
INSERT INTO sjt.t_product_info (id, product_name, supplier_id, one_level_category, two_level_category, three_level_category, publish_status, new_arrivals, main_image, product_details, descript, spec_grop_name, spec_type, create_date, update_date) VALUES (11, '梵高月饼', 1, 1, 2, 7, '1', '0', 'http://www.smallbug.top/images/product/product_11_00.jpg', '山尖田——鲜虾薄脆虾饼 纯手作制作无添加 保质期短建议尽快食用 顺丰快递幸福双旦季', '山尖田新品梵高艺术月饼奶黄月饼预定', '梵高月饼', '2', '2019-07-24 09:25:50', '2019-07-24 09:25:50');


create table t_product_pic
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	pic_url varchar(256) not null comment '图片URL',
	descript varchar(64) null comment '图片描述',
	sort_num int default '0' not null comment '图片排序编号',
	status varchar(1) default '1' not null comment '状态(0-无效, 1-有效)',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_product_pic_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品图片表' engine=InnoDB CHARSET=utf8
;

create index t_product_pic_t_product_info_id_fk
	on t_product_pic (product_id)
;

INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (1, 1, 'http://www.smallbug.top/images/product/pic_01_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (2, 1, 'http://www.smallbug.top/images/product/pic_01_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (3, 1, 'http://www.smallbug.top/images/product/pic_01_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (4, 1, 'http://www.smallbug.top/images/product/pic_01_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (5, 1, 'http://www.smallbug.top/images/product/pic_01_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (6, 2, 'http://www.smallbug.top/images/product/pic_02_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (7, 2, 'http://www.smallbug.top/images/product/pic_02_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (8, 2, 'http://www.smallbug.top/images/product/pic_02_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (9, 2, 'http://www.smallbug.top/images/product/pic_02_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (10, 2, 'http://www.smallbug.top/images/product/pic_02_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (11, 3, 'http://www.smallbug.top/images/product/pic_03_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (12, 3, 'http://www.smallbug.top/images/product/pic_03_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (13, 3, 'http://www.smallbug.top/images/product/pic_03_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (14, 3, 'http://www.smallbug.top/images/product/pic_03_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (15, 3, 'http://www.smallbug.top/images/product/pic_03_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (16, 3, 'http://www.smallbug.top/images/product/PIC_03_06.jpg', null, 6, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (17, 4, 'http://www.smallbug.top/images/product/pic_04_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (18, 4, 'http://www.smallbug.top/images/product/pic_04_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (19, 4, 'http://www.smallbug.top/images/product/pic_04_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (20, 4, 'http://www.smallbug.top/images/product/pic_04_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (21, 4, 'http://www.smallbug.top/images/product/pic_04_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (22, 4, 'http://www.smallbug.top/images/product/pic_04_06.jpg', null, 6, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (23, 4, 'http://www.smallbug.top/images/product/pic_04_07.jpg', null, 7, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (24, 4, 'http://www.smallbug.top/images/product/pic_04_08.jpg', null, 8, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (25, 5, 'http://www.smallbug.top/images/product/pic_05_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (26, 5, 'http://www.smallbug.top/images/product/pic_05_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (27, 5, 'http://www.smallbug.top/images/product/pic_05_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (28, 5, 'http://www.smallbug.top/images/product/pic_05_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (29, 5, 'http://www.smallbug.top/images/product/pic_05_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (30, 5, 'http://www.smallbug.top/images/product/pic_05_06.jpg', null, 6, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (31, 5, 'http://www.smallbug.top/images/product/pic_05_07.jpg', null, 7, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (32, 5, 'http://www.smallbug.top/images/product/pic_05_08.jpg', null, 8, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (33, 5, 'http://www.smallbug.top/images/product/pic_05_09.jpg', null, 9, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (34, 6, 'http://www.smallbug.top/images/product/pic_06_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (35, 6, 'http://www.smallbug.top/images/product/pic_06_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (36, 6, 'http://www.smallbug.top/images/product/pic_06_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (37, 6, 'http://www.smallbug.top/images/product/pic_06_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (38, 6, 'http://www.smallbug.top/images/product/pic_06_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (39, 6, 'http://www.smallbug.top/images/product/pic_06_06.jpg', null, 6, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (40, 6, 'http://www.smallbug.top/images/product/pic_06_07.jpg', null, 7, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (41, 6, 'http://www.smallbug.top/images/product/pic_06_08.jpg', null, 8, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (42, 7, 'http://www.smallbug.top/images/product/pic_07_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (43, 7, 'http://www.smallbug.top/images/product/pic_07_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (44, 7, 'http://www.smallbug.top/images/product/pic_07_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (45, 7, 'http://www.smallbug.top/images/product/pic_07_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (46, 7, 'http://www.smallbug.top/images/product/pic_07_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (47, 7, 'http://www.smallbug.top/images/product/pic_07_06.jpg', null, 6, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (48, 7, 'http://www.smallbug.top/images/product/pic_07_07.jpg', null, 7, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (49, 7, 'http://www.smallbug.top/images/product/pic_07_08.jpg', null, 8, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (50, 7, 'http://www.smallbug.top/images/product/pic_07_09.jpg', null, 9, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (51, 8, 'http://www.smallbug.top/images/product/pic_08_01.jpg', null, 1, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (52, 8, 'http://www.smallbug.top/images/product/pic_08_02.jpg', null, 2, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (53, 8, 'http://www.smallbug.top/images/product/pic_08_03.jpg', null, 3, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (54, 8, 'http://www.smallbug.top/images/product/pic_08_04.jpg', null, 4, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (55, 8, 'http://www.smallbug.top/images/product/pic_08_05.jpg', null, 5, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (56, 8, 'http://www.smallbug.top/images/product/pic_08_06.jpg', null, 6, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (57, 9, 'http://www.smallbug.top/images/product/pic_09_01.jpg', null, 1, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (58, 9, 'http://www.smallbug.top/images/product/pic_09_02.jpg', null, 2, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (59, 9, 'http://www.smallbug.top/images/product/pic_09_03.jpg', null, 3, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (60, 9, 'http://www.smallbug.top/images/product/pic_09_04.jpg', null, 4, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (61, 9, 'http://www.smallbug.top/images/product/pic_09_05.jpg', null, 5, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (62, 9, 'http://www.smallbug.top/images/product/pic_09_06.jpg', null, 6, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (63, 10, 'http://www.smallbug.top/images/product/pic_10_01.jpg', null, 1, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (64, 10, 'http://www.smallbug.top/images/product/pic_10_02.jpg', null, 2, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (65, 10, 'http://www.smallbug.top/images/product/pic_10_03.jpg', null, 3, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (66, 10, 'http://www.smallbug.top/images/product/pic_10_04.jpg', null, 4, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (67, 10, 'http://www.smallbug.top/images/product/pic_10_05.jpg', null, 5, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (68, 11, 'http://www.smallbug.top/images/product/pic_11_01.jpg', null, 1, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (69, 11, 'http://www.smallbug.top/images/product/pic_11_02.jpg', null, 2, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (70, 11, 'http://www.smallbug.top/images/product/pic_11_03.jpg', null, 3, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (71, 11, 'http://www.smallbug.top/images/product/pic_11_04.jpg', null, 4, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (72, 11, 'http://www.smallbug.top/images/product/pic_11_05.jpg', null, 5, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (73, 11, 'http://www.smallbug.top/images/product/pic_11_06.jpg', null, 6, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (74, 11, 'http://www.smallbug.top/images/product/pic_11_07.jpg', null, 7, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (75, 11, 'http://www.smallbug.top/images/product/pic_11_08.jpg', null, 8, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (76, 11, 'http://www.smallbug.top/images/product/pic_11_09.jpg', null, 9, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');




create table t_product_properties
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	properties_name varchar(36) not null comment '属性名称',
	properties_value varchar(128) not null comment '属性值',
	sort_num int default '0' not null comment '排序编号',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_product_properties_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品属性表' engine=InnoDB CHARSET=utf8
;

create index t_product_properties_t_product_info_id_fk
	on t_product_properties (product_id)
;

INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (1, 1, '产地', '上海青浦', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (2, 1, '保质期', '90天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (3, 1, '净含量', '280ml', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (4, 1, '存储方式', '阴凉干燥30°C以下', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (10, 1, '食用方法', '可冲泡 可舀食 可当健康早餐', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (11, 2, '产地', '上海青浦', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (12, 2, '保质期', '90天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (13, 2, '净含量', '280ml', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (14, 2, '存储方式', '阴凉干燥30°C以下', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (15, 2, '食用方法', '可冲泡 可舀食 可当健康早餐', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (16, 3, '产地', '上海青浦', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (17, 3, '保质期', '90天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (18, 3, '净含量', '280ml', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (19, 3, '存储方式', '阴凉干燥30°C以下', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (20, 3, '食用方法', '可冲泡 可舀食 可当健康早餐', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (21, 4, '产地', '上海青浦', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (22, 4, '保质期', '30天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (23, 4, '净含量', '16g*6', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (24, 4, '存储方式', '冷藏保存', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (25, 4, '食用方法', '即食', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (26, 5, '产地', '上海青浦', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (27, 5, '保质期', '30天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (28, 5, '净含量', '20g*6', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (29, 5, '食用方法', '即食', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (30, 5, '食用方法', '即食', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (31, 6, '产地', '上海青浦', 1, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (32, 6, '保质期', '120天', 2, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (33, 6, '净含量', '12g*6', 3, '2019-07-11 17:43:23', '2019-07-11 17:43:23');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (34, 6, '存储方式', '冷冻保存', 4, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (35, 6, '食用方法', '即食', 5, '2019-07-11 17:43:24', '2019-07-11 17:43:24');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (36, 7, '产地', '上海青浦', 1, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (37, 7, '保质期', '30天', 2, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (38, 7, '净含量', '11g*6', 3, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (39, 7, '存储方式', '冷藏保存', 4, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (40, 7, '食用方法', '即食', 5, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (41, 8, '产地', '上海青浦', 1, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (42, 8, '保质期', '30天', 2, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (43, 8, '净含量', '120g', 3, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (44, 8, '存储方式', '阴凉干燥30°C以下', 4, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (45, 8, '食用方法', '即食', 5, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (46, 9, '产地', '上海青浦', 1, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (47, 9, '保质期', '15天', 2, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (48, 9, '净含量', '10g左右*6', 3, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (49, 9, '存储方式', '密封阴凉干燥30°C以下', 4, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (50, 9, '食用方法', '即食', 5, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (51, 10, '产地', '上海青浦', 1, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (52, 10, '保质期', '90天', 2, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (53, 10, '净含量', '400g/盒', 3, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (54, 10, '存储方式', '阴凉干燥30°C以下', 4, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (55, 10, '食用方法', '即食', 5, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (56, 11, '产地', '广州深圳', 1, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (57, 11, '保质期', '60天', 2, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (58, 11, '净含量', '480g/盒', 3, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (59, 11, '存储方式', '阴凉干燥30°C以下', 4, '2019-07-30 17:58:11', '2019-07-30 17:58:11');
INSERT INTO sjt.t_product_properties (id, product_id, properties_name, properties_value, sort_num, create_date, update_date) VALUES (60, 11, '食用方法', '即食', 5, '2019-07-30 17:58:11', '2019-07-30 17:58:11');



create table t_product_stock
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	product_stock_num int not null comment '商品库存数量',
	order_stock_num int default '0' not null comment '订单库存数量(<= 商品库存数量, 下单+n, 订单结束-n)',
	version int default '0' not null comment '版本号',
	constraint t_product_stock_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品库存表' engine=InnoDB CHARSET=utf8
;

create index t_product_stock_t_product_info_id_fk
	on t_product_stock (product_id)
;



create table t_product_spec
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	spec_name varchar(128) not null comment '规格名称',
	price decimal default '0' not null comment '规格单价(单位：分)',
	spec_image varchar(512) not null comment '规格图片',
	stock_num int default '0' not null comment '规格库存',
	order_stock_num int default '0' not null comment '规格库存下单未结算数(<= 库存数量, 下单+n, 订单结束-n)',
	version int default '0' not null comment '版本号',
	constraint t_product_multi_spec_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品规格信息' engine=InnoDB CHARSET=utf8
;

create index t_product_multi_spec_t_product_info_id_fk
	on t_product_spec (product_id)
;

INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (1, 1, '抗霾舒畅柠果膏', 7900, 'http://www.smallbug.top/images/product/product_01_00.png', 100, 59, 14);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (2, 2, '解压双降柠果膏', 9900, 'http://www.smallbug.top/images/product/product_02_00.png', 100, 55, 10);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (3, 3, '暖宫柠果膏', 9900, 'http://www.smallbug.top/images/product/product_03_00.jpg', 100, 100, 10);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (4, 4, '鲜柠古力', 3900, 'http://www.smallbug.top/images/product/product_04_00.png', 100, 86, 10);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (5, 5, '鲜橙古力', 7900, 'http://www.smallbug.top/images/product/product_05_00.jpg', 100, 90, 10);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (6, 6, '栗子巧克力', 9900, 'http://www.smallbug.top/images/product/product_06_00.jpg', 100, 65, 10);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (7, 7, '咖啡巧克力', 1000, 'http://www.smallbug.top/images/product/product_07_01.jpg', 100, 70, 10);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (8, 7, '橙味巧克力', 1000, 'http://www.smallbug.top/images/product/product_07_02.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (9, 7, '焦糖巧克力', 1000, 'http://www.smallbug.top/images/product/product_07_03.jpg', 100, 4, 4);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (10, 7, '原味巧克力', 900, 'http://www.smallbug.top/images/product/product_07_04.jpg', 100, 80, 10);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (11, 7, '黑苦巧克力', 1000, 'http://www.smallbug.top/images/product/product_07_05.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (12, 7, '椰丝巧克力', 1000, 'http://www.smallbug.top/images/product/product_07_06.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (13, 7, '榛子巧克力', 900, 'http://www.smallbug.top/images/product/product_07_07.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (14, 8, '纯实牛肉干', 7900, 'http://www.smallbug.top/images/product/product_08_00.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (15, 9, '鲜虾薄脆', 6800, 'http://www.smallbug.top/images/product/product_09_00.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (16, 10, '健康运动款', 13900, 'http://www.smallbug.top/images/product/product_10_01.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (17, 10, '成熟男士款', 15900, 'http://www.smallbug.top/images/product/product_10_02.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (18, 10, '优雅女士款', 12900, 'http://www.smallbug.top/images/product/product_10_03.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (19, 11, '[杏福团圆]奶黄月饼', 15800, 'http://www.smallbug.top/images/product/product_11_01.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (20, 11, '[盛开的杏树]香茶月饼礼盒', 35800, 'http://www.smallbug.top/images/product/product_11_02.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (21, 11, '[月满杏树]月饼礼盒', 49800, 'http://www.smallbug.top/images/product/product_11_03.jpg', 100, 0, 0);
INSERT INTO sjt.t_product_spec (id, product_id, spec_name, price, spec_image, stock_num, order_stock_num, version) VALUES (22, 7, '任性随机6颗', 5900, 'http://www.smallbug.top/images/product/product_07_08.jpg', 100, 0, 0);




create table t_order
(
	id int auto_increment comment '自增id'
		primary key,
	order_no varchar(36) not null comment '订单号',
	user_id int not null comment '用户id',
	address_id int null comment '收货地址id',
	total_amount decimal default '0' not null comment '商品总金额(单位：分)',
	org_amount decimal default '0' not null comment '订单总金额(单位：分)',
	discount_amount decimal default '0' not null comment '优惠金额(单位：分)',
	payment decimal default '0' not null comment '支付金额(单位：分)',
	post_fee decimal default '0' not null comment '邮费(单位：分)',
	status varchar(1) not null comment '订单状态(1-待支付, 2-待发货, 3-待收货, 4-已完成, 5-已取消)',
	create_date timestamp default CURRENT_TIMESTAMP not null comment '订单创建时间',
	update_date timestamp default CURRENT_TIMESTAMP not null comment '订单更新时间',
	payment_date timestamp null comment '支付时间',
	consign_date timestamp null comment '发货时间',
	end_date timestamp null comment '订单完成时间',
	close_date timestamp null comment '订单关闭时间',
	shipping_code varchar(36) null comment '物流单号',
	shipping_name varchar(36) null comment '物流名称',
	address varchar(256) null comment '地址',
	buyer_message varchar(128) null comment '买家留言',
	buyer_rate varchar(1) default '0' not null comment '买家是否评论(0-否, 1-是)',
	contact_phone varchar(11) null comment '联系电话',
	contact_name varchar(64) null comment '联系人',
	constraint t_order_order_no_uindex
		unique (order_no),
	constraint t_order_t_user_id_fk
		foreign key (user_id) references t_user (id)
)
comment '订单信息表' engine=InnoDB CHARSET=utf8
;

create index t_order_t_user_id_fk
	on t_order (user_id)
;




create table t_order_item
(
	id int auto_increment comment '自增id'
		primary key,
	order_id int not null comment '订单id',
	product_spec_id int not null comment '商品规格id',
	num int not null comment '商品购买数量',
	total_fee decimal not null comment '商品总金额',
	discount_amount decimal default '0' not null comment '优惠金额(单位：分)',
	price decimal not null comment '商品单价(单位：分)',
	product_img varchar(512) null comment '商品图片',
	product_name varchar(36) not null comment '商品名称',
	product_descript varchar(512) null comment '商品描述',
	constraint t_order_item_t_order_id_fk
		foreign key (order_id) references t_order (id),
	constraint t_order_item_t_product_spec_id_fk
		foreign key (product_spec_id) references t_product_spec (id)
)
comment '订单详情表' engine=InnoDB CHARSET=utf8
;

create index t_order_item_t_order_id_fk
	on t_order_item (order_id)
;

create index t_order_item_t_product_info_id_fk
	on t_order_item (product_spec_id)
;


create table t_payment_flow
(
	id int auto_increment comment '自增id'
		primary key,
	pay_no varchar(64) not null comment '支付流水号',
	order_no varchar(64) not null comment '订单号',
	pay_order_no varchar(64) null comment '支付订单号(例如：微信支付订单号)',
	user_id int not null comment '用户id',
	amount decimal not null comment '支付金额(单位：分)',
	integral int default '0' not null comment '使用的积分',
	pay_type varchar(1) not null comment '支付类型(1-微信, 2-支付宝, 3-银联)',
	status varchar(1) not null comment '支付状态(0-取消, 1-未支付, 2-已支付, 3-支付异常)',
	note varchar(256) null comment '备注',
	create_date timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
	pay_complete_date timestamp null comment '支付完成时间',
	constraint t_payment_flow_pay_no_uindex
		unique (pay_no),
	constraint t_payment_flow_pay_order_no_uindex
		unique (pay_order_no),
	constraint t_payment_flow_t_order_order_no_fk
		foreign key (order_no) references t_order (order_no),
	constraint t_payment_flow_t_user_id_fk
		foreign key (user_id) references t_user (id)
)
comment '支付流水表' engine=InnoDB CHARSET=utf8
;

create index t_payment_flow_t_order_order_no_fk
	on t_payment_flow (order_no)
;

create index t_payment_flow_t_user_id_fk
	on t_payment_flow (user_id)
;


create table t_product_detail_desc
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	type varchar(1) default '1' not null comment '描述内容类型(1-图片, 2-文本)',
	content text not null comment '内容',
	sort_num int default '0' not null comment '排序编号',
	constraint t_product_detail_desc_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品详情描述内容' engine=InnoDB CHARSET=utf8
;

create index t_product_detail_desc_t_product_info_id_fk
	on t_product_detail_desc (product_id)
;

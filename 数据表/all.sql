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
comment '用户信息表' engine=InnoDB
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
comment '用户授权登录表' engine=InnoDB
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
comment '用户登录日志表' engine=InnoDB
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
comment '网页授权记录表' engine=InnoDB
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
comment '微信用户信息' engine=InnoDB
;



create table t_notes
(
	id int auto_increment comment '自增id'
		primary key,
	title varchar(64) not null comment '标题',
	big_img varchar(128) not null comment '大图',
	small_img varchar(128) not null comment '小图',
	note_context text not null comment '内容',
	is_craftsman varchar(1) default '0' not null comment '是否为匠人精神日记(0-否, 1-是)',
	status varchar(1) default '1' not null comment '状态(0-可用, 1-不可用)',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间'
)
comment '山田日记' engine=InnoDB
;

create table t_banner
(
	id int auto_increment comment '自增id'
		primary key,
	banner_name varchar(36) not null comment 'banner名称',
	banner_type varchar(1) default '1' not null comment 'banner类型(1-首页top轮播图, 2-GIF小视频, 3-山田日记banner, 4-首页center轮播图)',
	img_url varchar(128) not null comment '图片url地址',
	img_version bigint not null comment '图片版本(?_v=xxxxxxxx)',
	url varchar(128) null comment 'banner跳转地址',
	url_type varchar(1) default '1' not null comment '地址类型(1-内部地址, 2-外部地址)',
	status varchar(1) default '1' not null comment '状态(0-无效 1-有效)',
	sort_num int default '0' not null comment '排序编号',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间'
)
comment 'banner配置表' engine=InnoDB
;

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
comment '收货地址表' engine=InnoDB
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
	img_url varchar(512) null comment '分类图片',
	url varchar(512) null comment '分类链接',
	pid int null comment '父级编号',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_product_category_t_product_category_id_fk
		foreign key (pid) references t_product_category (id)
)
comment '商品分类表' engine=InnoDB
;

create index t_product_category_t_product_category_id_fk
	on t_product_category (pid)
;

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
comment '供应商信息表' engine=InnoDB
;



create table t_product_info
(
	id int auto_increment comment '自增id'
		primary key,
	product_name varchar(32) not null comment '商品名称',
	supplier_id int not null comment '供应商id',
	one_level_category int null comment '一级分类',
	two_level_category int null comment '二级分类',
	three_level_category int null comment '三级分类',
	price decimal not null comment '商品价格(单位：分)',
	discount_amount decimal default '0' not null comment '优惠金额(单位：分)',
	publish_status varchar(1) not null comment '商品发布状态(0-无效, 1-上架, 2-下架)',
	new_arrivals varchar(1) default '0' not null comment '是否为新品推荐(0-否, 1-是)',
	descript varchar(512) null comment '商品描述',
	product_details text null comment '商品详情',
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
comment '商品信息表' engine=InnoDB
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

create table t_product_pic
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	pic_url varchar(256) not null comment '图片URL',
	descript varchar(64) null comment '图片描述',
	sort_num int default '0' not null comment '图片排序编号',
	is_master varchar(1) default '0' not null comment '是否主图(0-否, 1-是)',
	status varchar(1) default '1' not null comment '状态(0-无效, 1-有效)',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_product_pic_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品图片表' engine=InnoDB
;

create index t_product_pic_t_product_info_id_fk
	on t_product_pic (product_id)
;

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
comment '商品属性表' engine=InnoDB
;

create index t_product_properties_t_product_info_id_fk
	on t_product_properties (product_id)
;


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
comment '商品库存表' engine=InnoDB
;

create index t_product_stock_t_product_info_id_fk
	on t_product_stock (product_id)
;


create table t_order
(
	id int auto_increment comment '自增id'
		primary key,
	order_no varchar(36) not null comment '订单号',
	user_id int not null comment '用户id',
	address_id int not null comment '收货地址id',
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
	shipping_name varchar(36) null comment '物流名称',
	shipping_code varchar(36) null comment '物流单号',
	buyer_message varchar(128) null comment '买家留言',
	buyer_rate varchar(1) default '0' not null comment '买家是否评论(0-否, 1-是)',
	constraint t_order_order_no_uindex
		unique (order_no),
	constraint t_order_t_user_id_fk
		foreign key (user_id) references t_user (id),
	constraint t_order_t_address_id_fk
		foreign key (address_id) references t_address (id)
)
comment '订单信息表' engine=InnoDB
;

create index t_order_t_address_id_fk
	on t_order (address_id)
;

create index t_order_t_user_id_fk
	on t_order (user_id)
;



create table t_order_item
(
	id int auto_increment comment '自增id'
		primary key,
	order_id int not null comment '订单id',
	product_id int not null comment '商品id',
	num int not null comment '商品购买数量',
	total_fee decimal not null comment '商品总金额',
	discount_amount decimal default '0' not null comment '优惠金额(单位：分)',
	price decimal not null comment '商品单价(单位：分)',
	product_img varchar(512) null comment '商品图片',
	product_name varchar(36) not null comment '商品名称',
	product_descript varchar(512) null comment '商品描述',
	constraint t_order_item_t_order_id_fk
		foreign key (order_id) references t_order (id),
	constraint t_order_item_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '订单详情表' engine=InnoDB
;

create index t_order_item_t_order_id_fk
	on t_order_item (order_id)
;

create index t_order_item_t_product_info_id_fk
	on t_order_item (product_id)
;



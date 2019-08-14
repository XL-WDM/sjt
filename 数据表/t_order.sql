drop table t_order;
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
comment '订单信息表' engine=InnoDB
;

create index t_order_t_user_id_fk
	on t_order (user_id)
;




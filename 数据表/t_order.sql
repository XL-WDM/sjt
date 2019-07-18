drop table t_order;
create table t_order
(
	id int auto_increment comment '自增id'
		primary key,
	order_no varchar(36) not null comment '订单号',
	user_id int not null comment '用户id',
	payment bigint null comment '实付金额(单位：分)',
	payment_type varchar(1) null comment '支付类型(1-线上支付, 2-线下支付)',
	post_fee bigint default '0' not null comment '邮费(单位：分)',
	status varchar(1) not null comment '订单状态(1-未付款, 2-已付款, 3-未发货, 4-已发货, 5-交易成功, 6-交易关闭)',
	create_date timestamp default CURRENT_TIMESTAMP not null comment '订单创建时间',
	update_date timestamp default CURRENT_TIMESTAMP not null comment '订单更新时间',
	payment_date timestamp not null comment '付款时间',
	consign_date timestamp not null comment '发货时间',
	end_date timestamp not null comment '交易完成时间',
	close_date timestamp not null comment '交易关闭时间',
	shipping_name varchar(36) null comment '物流名称',
	shipping_code varchar(36) null comment '物流单号',
	buyer_message varchar(128) null comment '买家留言',
	buyer_rate varchar(1) not null comment '买家是否评论(0-否, 1-是)',
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


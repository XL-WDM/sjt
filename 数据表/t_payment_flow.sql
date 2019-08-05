drop table t_payment_flow;
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
comment '支付流水表' engine=InnoDB
;

create index t_payment_flow_t_order_order_no_fk
	on t_payment_flow (order_no)
;

create index t_payment_flow_t_user_id_fk
	on t_payment_flow (user_id)
;


drop table t_order_item;
create table t_order_item
(
	id int auto_increment comment '自增id'
		primary key,
	order_id int not null comment '订单id',
	product_id int not null comment '商品id',
	num int not null comment '商品购买数量',
	price decimal not null comment '商品单价(单位：分)',
	total_fee decimal not null comment '商品总金额',
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






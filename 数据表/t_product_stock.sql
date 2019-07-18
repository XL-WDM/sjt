drop table t_product_stock;
create table t_product_stock
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	product_stock_num int not null comment '商品库存数量',
	order_stock_num int null comment '订单库存数量(<= 商品库存数量, 下单+n, 订单结束-n)',
	version int default '0' not null comment '版本号',
	constraint t_product_stock_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品库存表' engine=InnoDB
;

create index t_product_stock_t_product_info_id_fk
	on t_product_stock (product_id)
;



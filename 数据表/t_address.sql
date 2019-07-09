drop table t_address;
create table t_address
(
	id int auto_increment comment '自增id'
		primary key,
	user_id int not null comment '用户id',
	contacts varchar(64) not null comment '联系人',
	phone varchar(11) not null comment '联系电话',
	city varchar(64) not null comment '城市',
	address varchar(512) not null comment '收货地址',
	door_number varchar(128) not null comment '门牌号',
	tag varchar(1) not null comment '地址标签(1-家, 2-公司, 3-学校)',
	contacts_call varchar(1) not null comment '称呼(1-先生, 2-女士)',
	is_default varchar(1) not null comment '是否默认地址(0-不是, 1-是)',
	status varchar(1) not null comment '状态(0-无效, 1-有效)'
)
comment '收货地址表' engine=innodb;

insert into stj.t_address (id, user_id, contacts, phone, city, address, door_number, tag, contacts_call, is_default, status) values (1, 1, '胡一龙', '15073820717', '上海', '灵山路2011弄', '30号202室', '1', '1', '1', '1');

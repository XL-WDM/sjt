drop table t_supplier_info;
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



INSERT INTO stj.t_supplier_info (id, supplier_name, supplier_type, link_man, phone, bank_name, bank_account, address, status, create_date, update_date) VALUES (1, '山田尖', '1', '山田尖', '15555555555', '交通银行', '44444444444444', '上海', '1', '2019-07-11 17:21:30', '2019-07-11 17:21:30');

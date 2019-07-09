CREATE TABLE t_address
(
    id int PRIMARY KEY COMMENT '自增id' AUTO_INCREMENT,
    user_id int NOT NULL COMMENT '用户id',
    contacts VARCHAR(64) NOT NULL COMMENT '联系人',
    phone varchar(11) NOT NULL COMMENT '联系电话',
    city varchar(64) NOT NULL COMMENT '城市',
    address varchar(512) NOT NULL COMMENT '收货地址',
    door_number VARCHAR(128) NOT NULL COMMENT '门牌号',
    tag varchar(1) NOT NULL COMMENT '地址标签(1-家, 2-公司, 3-学校)',
    `call` varchar(1) NOT NULL COMMENT '称呼(1-先生, 2-女士)',
    is_default varchar(1) NOT NULL COMMENT '是否默认地址(0-不是, 1-是)',
    status varchar(1) NOT NULL COMMENT '状态(0-无效, 1-有效)'
);
ALTER TABLE t_address COMMENT = '收货地址表';

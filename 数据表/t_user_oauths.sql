drop table t_user_oauths;
CREATE TABLE t_user_oauths
(
    id int PRIMARY KEY COMMENT '自增id' AUTO_INCREMENT,
    user_id int NOT NULL COMMENT '用户id',
    oauth_id varchar(128) NOT NULL COMMENT '授权id(比如: openid)',
    union_id varchar(128) NOT NULL COMMENT 'unionid',
    status varchar(1) DEFAULT 1 NOT NULL COMMENT '授权状态(0-取消授权, 1-已授权)',
    oauth_type varchar(1) NOT NULL COMMENT '授权类型(1-微信, 2-QQ, 3-微博)',
    oauth_date datetime DEFAULT now() NOT NULL COMMENT '授权时间',
    update_date datetime NOT NULL COMMENT '更新时间',
    CONSTRAINT t_user_oauths_t_user_ID_fk FOREIGN KEY (user_id) REFERENCES t_user (ID)
);
ALTER TABLE t_user_oauths COMMENT = '用户授权登录表';

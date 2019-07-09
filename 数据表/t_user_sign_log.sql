CREATE TABLE t_user_sign_log
(
    id int PRIMARY KEY COMMENT '自增id' AUTO_INCREMENT,
    user_id int NOT NULL COMMENT '用户id',
    token varchar(64) NOT NULL COMMENT 'token',
    sign_date datetime DEFAULT now() NOT NULL COMMENT '登陆时间',
    expiration_time datetime NOT NULL COMMENT '过期时间(时间戳)'
);
CREATE UNIQUE INDEX t_user_sign_log_token_uindex ON t_user_sign_log (token);
ALTER TABLE t_user_sign_log COMMENT = '用户登录日志表';

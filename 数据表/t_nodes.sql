drop table t_notes;
create table t_notes
(
	id int auto_increment comment '自增id'
		primary key,
	title varchar(64) not null comment '标题',
	big_img varchar(512) not null comment '大图',
	small_img varchar(512) not null comment '小图',
	note_context text not null comment '内容',
	is_craftsman varchar(1) default '0' not null comment '是否为匠人精神日记(0-否, 1-是)',
	status varchar(1) default '1' not null comment '状态(0-可用, 1-不可用)',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间'
)
comment '山田日记' engine=InnoDB
;




drop table t_notes;
create table t_notes
(
	id int auto_increment comment '自增id'
		primary key,
	title varchar(64) not null comment '标题',
	descript varchar(512) not null comment '描述',
	big_img varchar(128) not null comment '大图',
	small_img varchar(128) not null comment '小图',
	status varchar(1) default '1' not null comment '状态(0-可用, 1-不可用)',
	is_craftsman varchar(1) default '0' not null comment '是否为匠人精神日记(0-否, 1-是)',
	note_context text not null comment '内容',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间'
)
comment '山田日记' engine=InnoDB
;


INSERT INTO sjt.t_notes (id, title, descript, big_img, small_img, status, is_craftsman, note_context, create_date, update_date) VALUES (1, '大大的海鲜干，炒菜、煲汤放几只，好鲜！', '“山海农人” 大虾干，用新鲜打捞的大海虾制作，无防腐剂添加', 'http://www.smallbug.top/images/notes/note_craftsman_big_01.png', 'http://www.smallbug.top/images/notes/note_small_01.png', '1', '0', '无内容', '2019-07-22 15:47:56', '2019-07-22 15:47:56');
INSERT INTO sjt.t_notes (id, title, descript, big_img, small_img, status, is_craftsman, note_context, create_date, update_date) VALUES (2, '有大颗草莓干的手工牛轧糖，过年的糖就它了', '台湾“芒里偷闲” 手工牛轧糖，三种口味可选，甜而不腻，有嚼劲', 'http://www.smallbug.top/images/notes/note_craftsman_big_01.png', 'http://www.smallbug.top/images/notes/note_small_01.png', '1', '0', '无内容', '2019-07-22 15:47:56', '2019-07-22 15:47:56');
INSERT INTO sjt.t_notes (id, title, descript, big_img, small_img, status, is_craftsman, note_context, create_date, update_date) VALUES (3, '宝宝新春小棉袄，年味十足！', '“嗯哈” 猪年限定款儿童新春棉衣，传统服饰样式，精致提花棉缎，厚实保暖', 'http://www.smallbug.top/images/notes/note_craftsman_big_01.png', 'http://www.smallbug.top/images/notes/note_small_01.png', '1', '0', '无内容', '2019-07-22 15:47:56', '2019-07-22 15:47:56');
INSERT INTO sjt.t_notes (id, title, descript, big_img, small_img, status, is_craftsman, note_context, create_date, update_date) VALUES (4, '山田匠人', '生活原本来源与自然，手作然我更加贴近自然——上尖匠人 Hans', 'http://www.smallbug.top/images/notes/note_craftsman_big_01.png', 'http://www.smallbug.top/images/notes/note_small_01.png', '1', '1', '无内容', '2019-07-22 15:47:56', '2019-07-22 15:47:56');
INSERT INTO sjt.t_notes (id, title, descript, big_img, small_img, status, is_craftsman, note_context, create_date, update_date) VALUES (5, '山田匠人', '生活原本来源与自然，手作然我更加贴近自然——上尖匠人 Hans', 'http://www.smallbug.top/images/notes/note_craftsman_big_01.png', 'http://www.smallbug.top/images/notes/note_small_01.png', '1', '1', '无内容', '2019-07-22 15:47:56', '2019-07-22 15:47:56');





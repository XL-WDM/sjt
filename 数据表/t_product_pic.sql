drop table t_product_pic;
create table t_product_pic
(
	id int auto_increment comment '自增id'
		primary key,
	product_id int not null comment '商品id',
	pic_url varchar(256) not null comment '图片URL',
	descript varchar(64) null comment '图片描述',
	sort_num int default '0' not null comment '图片排序编号',
	status varchar(1) default '1' not null comment '状态(0-无效, 1-有效)',
	create_date datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_date datetime default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_product_pic_t_product_info_id_fk
		foreign key (product_id) references t_product_info (id)
)
comment '商品图片表' engine=InnoDB
;

create index t_product_pic_t_product_info_id_fk
	on t_product_pic (product_id)
;






INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (1, 1, 'http://www.smallbug.top/images/product/pic_01_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (2, 1, 'http://www.smallbug.top/images/product/pic_01_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (3, 1, 'http://www.smallbug.top/images/product/pic_01_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (4, 1, 'http://www.smallbug.top/images/product/pic_01_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (5, 1, 'http://www.smallbug.top/images/product/pic_01_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (6, 2, 'http://www.smallbug.top/images/product/pic_02_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (7, 2, 'http://www.smallbug.top/images/product/pic_02_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (8, 2, 'http://www.smallbug.top/images/product/pic_02_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (9, 2, 'http://www.smallbug.top/images/product/pic_02_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (10, 2, 'http://www.smallbug.top/images/product/pic_02_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (11, 3, 'http://www.smallbug.top/images/product/pic_03_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (12, 3, 'http://www.smallbug.top/images/product/pic_03_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (13, 3, 'http://www.smallbug.top/images/product/pic_03_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (14, 3, 'http://www.smallbug.top/images/product/pic_03_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (15, 3, 'http://www.smallbug.top/images/product/pic_03_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (16, 3, 'http://www.smallbug.top/images/product/PIC_03_06.jpg', null, 6, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (17, 4, 'http://www.smallbug.top/images/product/pic_04_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (18, 4, 'http://www.smallbug.top/images/product/pic_04_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (19, 4, 'http://www.smallbug.top/images/product/pic_04_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (20, 4, 'http://www.smallbug.top/images/product/pic_04_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (21, 4, 'http://www.smallbug.top/images/product/pic_04_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (22, 4, 'http://www.smallbug.top/images/product/pic_04_06.jpg', null, 6, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (23, 4, 'http://www.smallbug.top/images/product/pic_04_07.jpg', null, 7, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (24, 4, 'http://www.smallbug.top/images/product/pic_04_08.jpg', null, 8, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (25, 5, 'http://www.smallbug.top/images/product/pic_05_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (26, 5, 'http://www.smallbug.top/images/product/pic_05_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (27, 5, 'http://www.smallbug.top/images/product/pic_05_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (28, 5, 'http://www.smallbug.top/images/product/pic_05_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (29, 5, 'http://www.smallbug.top/images/product/pic_05_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (30, 5, 'http://www.smallbug.top/images/product/pic_05_06.jpg', null, 6, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (31, 5, 'http://www.smallbug.top/images/product/pic_05_07.jpg', null, 7, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (32, 5, 'http://www.smallbug.top/images/product/pic_05_08.jpg', null, 8, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (33, 5, 'http://www.smallbug.top/images/product/pic_05_09.jpg', null, 9, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (34, 6, 'http://www.smallbug.top/images/product/pic_06_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (35, 6, 'http://www.smallbug.top/images/product/pic_06_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (36, 6, 'http://www.smallbug.top/images/product/pic_06_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (37, 6, 'http://www.smallbug.top/images/product/pic_06_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (38, 6, 'http://www.smallbug.top/images/product/pic_06_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (39, 6, 'http://www.smallbug.top/images/product/pic_06_06.jpg', null, 6, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (40, 6, 'http://www.smallbug.top/images/product/pic_06_07.jpg', null, 7, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (41, 6, 'http://www.smallbug.top/images/product/pic_06_08.jpg', null, 8, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (42, 7, 'http://www.smallbug.top/images/product/pic_07_01.jpg', null, 1, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (43, 7, 'http://www.smallbug.top/images/product/pic_07_02.jpg', null, 2, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (44, 7, 'http://www.smallbug.top/images/product/pic_07_03.jpg', null, 3, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (45, 7, 'http://www.smallbug.top/images/product/pic_07_04.jpg', null, 4, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (46, 7, 'http://www.smallbug.top/images/product/pic_07_05.jpg', null, 5, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (47, 7, 'http://www.smallbug.top/images/product/pic_07_06.jpg', null, 6, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (48, 7, 'http://www.smallbug.top/images/product/pic_07_07.jpg', null, 7, '1', '2019-07-30 22:40:32', '2019-07-30 22:40:32');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (49, 7, 'http://www.smallbug.top/images/product/pic_07_08.jpg', null, 8, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (50, 7, 'http://www.smallbug.top/images/product/pic_07_09.jpg', null, 9, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (51, 8, 'http://www.smallbug.top/images/product/pic_08_01.jpg', null, 1, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (52, 8, 'http://www.smallbug.top/images/product/pic_08_02.jpg', null, 2, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (53, 8, 'http://www.smallbug.top/images/product/pic_08_03.jpg', null, 3, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (54, 8, 'http://www.smallbug.top/images/product/pic_08_04.jpg', null, 4, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (55, 8, 'http://www.smallbug.top/images/product/pic_08_05.jpg', null, 5, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (56, 8, 'http://www.smallbug.top/images/product/pic_08_06.jpg', null, 6, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (57, 9, 'http://www.smallbug.top/images/product/pic_09_01.jpg', null, 1, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (58, 9, 'http://www.smallbug.top/images/product/pic_09_02.jpg', null, 2, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (59, 9, 'http://www.smallbug.top/images/product/pic_09_03.jpg', null, 3, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (60, 9, 'http://www.smallbug.top/images/product/pic_09_04.jpg', null, 4, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (61, 9, 'http://www.smallbug.top/images/product/pic_09_05.jpg', null, 5, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (62, 9, 'http://www.smallbug.top/images/product/pic_09_06.jpg', null, 6, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (63, 10, 'http://www.smallbug.top/images/product/pic_10_01.jpg', null, 1, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (64, 10, 'http://www.smallbug.top/images/product/pic_10_02.jpg', null, 2, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (65, 10, 'http://www.smallbug.top/images/product/pic_10_03.jpg', null, 3, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (66, 10, 'http://www.smallbug.top/images/product/pic_10_04.jpg', null, 4, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (67, 10, 'http://www.smallbug.top/images/product/pic_10_05.jpg', null, 5, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (68, 11, 'http://www.smallbug.top/images/product/pic_11_01.jpg', null, 1, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (69, 11, 'http://www.smallbug.top/images/product/pic_11_02.jpg', null, 2, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (70, 11, 'http://www.smallbug.top/images/product/pic_11_03.jpg', null, 3, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (71, 11, 'http://www.smallbug.top/images/product/pic_11_04.jpg', null, 4, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (72, 11, 'http://www.smallbug.top/images/product/pic_11_05.jpg', null, 5, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (73, 11, 'http://www.smallbug.top/images/product/pic_11_06.jpg', null, 6, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (74, 11, 'http://www.smallbug.top/images/product/pic_11_07.jpg', null, 7, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (75, 11, 'http://www.smallbug.top/images/product/pic_11_08.jpg', null, 8, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');
INSERT INTO sjt.t_product_pic (id, product_id, pic_url, descript, sort_num, status, create_date, update_date) VALUES (76, 11, 'http://www.smallbug.top/images/product/pic_11_09.jpg', null, 9, '1', '2019-07-30 22:40:33', '2019-07-30 22:40:33');



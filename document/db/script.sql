-- DataBase 생성
CREATE DATABASE STUDY_DB;
USE STUDY_DB;

create table test
(
	COLUMN1 int auto_increment primary key,
	COLUMN2 varchar(20) null
)
comment '테스트 연결 확인 테이블';

create table user
(
	user_id varchar(20) not null comment '유저 이름',
	password varchar(200) null comment '패스워드',
	email varchar(50) null comment '이메일',
	role varchar(10) default 'R001' not null comment '권한 레벨',
	insert_dt datetime default CURRENT_TIMESTAMP not null comment '생성일자',
	delete_dt datetime null comment '삭제 일자',
	use_yn varchar(2) default 'Y' not null,
	constraint user_user_id_uindex
		unique (user_id)
)
comment '유저 정보';
alter table user add primary key (user_id);



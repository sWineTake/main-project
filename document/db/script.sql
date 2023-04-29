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
	login_dt datetime null comment '로그인 일자',
	wrong_cnt int default 0 null comment '패스워드 틀릿 횟수',
	use_yn varchar(2) default 'Y' not null,
	constraint user_user_id_uindex unique (user_id)
)
comment '유저 정보';
alter table user add primary key (user_id);
INSERT INTO study_db.user (user_id, password, email, role, insert_dt, delete_dt, login_dt, wrong_cnt, use_yn) VALUES ('admin001', '$2a$10$TfMsxLCKa8YcioPlkOh02uA2MTyReTcugC6v59d8YoIcuDrjzfn1y', 'woosong@naver.com', 'R003', '2022-04-11 10:23:53', null, null, 0, 'Y');
INSERT INTO study_db.user (user_id, password, email, role, insert_dt, delete_dt, login_dt, wrong_cnt, use_yn) VALUES ('user1234', '$2a$10$XH09U37pBeH985V0gRPZrO3xtZL.6L.bw9XojbCyl8bBrln6Av.TK', 'test1234@naver.com', 'R000', '2022-04-22 08:14:54', null, null, 0, 'Y');

create table board
(
	board_seq int auto_increment comment '시퀀스' primary key,
	board_code varchar(30) not null comment '게시판 분류 코드(comm_code)',
	title varchar(100) not null comment '제목',
	content text not null comment '내용',
	writer varchar(20) not null comment '글쓴이',
	writer_ip varchar(30) not null comment '글쓴이 IP',
	insert_dt datetime default CURRENT_TIMESTAMP null comment '글 생성 일자',
	delete_dt datetime null comment '글 삭제 일자',
	up_count int default 0 null comment '추천수',
	use_yn varchar(2) null comment '사용여부'
)
comment '게시판 ';








-- DataBase 생성
CREATE DATABASE STUDY_DB;
USE STUDY_DB;

create table test
(
	COLUMN1 int auto_increment primary key,
	COLUMN2 varchar(20) null
)
comment '테스트 연결 확인 테이블';


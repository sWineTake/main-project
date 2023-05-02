use study_by;

drop table if exists ITEM CASCADE;
drop table if exists CATEGORY CASCADE;
drop table if exists CATEGORY_ITEM CASCADE;
drop table if exists DELIVERY CASCADE;
drop table if exists MEMBER CASCADE;
drop table if exists ORDER_ITEM CASCADE;
drop table if exists ORDERS CASCADE;
drop table if exists ALBUM CASCADE;
drop table if exists Book CASCADE;
drop table if exists MOVIE CASCADE;

create table ITEM (
    ITEM_ID bigint not null,
    CREATE_DATE timestamp,
    LAST_MODIFIED_DATE timestamp,
    NAME varchar(255),
    PRICE integer not null,
    STOCK_QUANTITIY integer not null,
    primary key (ITEM_ID)
);

create table CATEGORY (
    CATEGORY_ID bigint not null,
    CREATE_DATE timestamp,
    LAST_MODIFIED_DATE timestamp,
    NAME varchar(255),
    PARENT_ID bigint,
    primary key (CATEGORY_ID)
);

create table CATEGORY_ITEM (
    CATEGORY_ITEM_ID bigint not null,
    CREATE_DATE timestamp,
    LAST_MODIFIED_DATE timestamp,
    CATEGORY_ID bigint,
    ITEM_ID bigint,
    primary key (CATEGORY_ITEM_ID)
);

create table DELIVERY (
    DELIVERY_ID bigint not null,
    CREATE_DATE timestamp,
    LAST_MODIFIED_DATE timestamp,
    CITY varchar(255),
    DELIVERY_STATUS varchar(255),
    STREET varchar(255),
    ZIPCODE varchar(255),
    primary key (DELIVERY_ID)
);

CREATE TABLE MEMBER (
  MEMBER_ID BIGINT AUTO_INCREMENT NOT NULL,
  SNS_ID VARCHAR(255),
  CREATE_DATE TIMESTAMP,
  LAST_MODIFIED_DATE TIMESTAMP,
  CITY VARCHAR(255),
  NAME VARCHAR(255),
  STREET VARCHAR(255),
  ZIPCODE VARCHAR(255),
  PRIMARY KEY (MEMBER_ID)
);

create table ORDER_ITEM (
    ORDER_ITEM_ID bigint not null,
    CREATE_DATE timestamp,
    LAST_MODIFIED_DATE timestamp,
    COUNT integer not null,
    ORDER_PRICE integer not null,
    ITEM_ID bigint,
    ORDER_ID bigint,
    primary key (ORDER_ITEM_ID)
);

create table ORDERS (
    ORDER_ID bigint not null,
    CREATE_DATE timestamp,
    LAST_MODIFIED_DATE timestamp,
    ORDER_DATE timestamp,
    STATUS varchar(255),
    DELIVERY_ID bigint,
    MEMBER_ID bigint,
    primary key (ORDER_ID)
);

create table ALBUM (
    ARTIST varchar(255),
    ETC varchar(255),
    ITEM_ID bigint not null,
    primary key (ITEM_ID)
);

create table Book (
    AUTHOR varchar(255),
    ISBN varchar(255),
    ITEM_ID bigint not null,
    primary key (ITEM_ID)
);

create table MOVIE (
    ACTOR varchar(255),
    DIRECTOR varchar(255),
    ITEM_ID bigint not null,
    primary key (ITEM_ID)
);

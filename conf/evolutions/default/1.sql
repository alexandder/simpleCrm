# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table contact (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  last_name                 varchar(255),
  company                   varchar(255),
  email                     varchar(255),
  phone_number              varchar(255),
  constraint pk_contact primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  email                     varchar(255),
  password                  varchar(255),
  is_admin                  tinyint(1) default 0,
  image                     longblob,
  constraint pk_user primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table contact;

drop table user;

SET FOREIGN_KEY_CHECKS=1;


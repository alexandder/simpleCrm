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
  user_id                   bigint,
  constraint pk_contact primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  email                     varchar(255),
  password                  varchar(255),
  is_admin                  tinyint(1) default 0,
  image                     varchar(255),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;

alter table contact add constraint fk_contact_user_1 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_contact_user_1 on contact (user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

SET FOREIGN_KEY_CHECKS=1;


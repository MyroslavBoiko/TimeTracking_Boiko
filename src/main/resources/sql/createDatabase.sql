/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     09.08.2018 16:24:58                          */
/*==============================================================*/

drop database if exists time_tracking;

create database time_tracking;

use time_tracking;

drop table if exists activity;

drop table if exists activity_translate;

drop table if exists add_request;

drop table if exists assignment;

drop table if exists delete_request;

drop table if exists language;

drop table if exists user;

drop table if exists user_type;

/*==============================================================*/
/* Table: activity                                              */
/*==============================================================*/
create table activity
(
   activity_id          bigint not null auto_increment,
   description          varchar(100) not null unique,
   primary key (activity_id)
);

/*==============================================================*/
/* Table: activity_translate                                    */
/*==============================================================*/
create table activity_translate
(
   translate_id         bigint not null auto_increment,
   language_id          bigint not null,
   activity_id          bigint not null,
   description          varchar(100) not null default 'No translate for current language',
   primary key (translate_id)
);

/*==============================================================*/
/* Table: add_request                                           */
/*==============================================================*/
create table request_to_add
(
   add_id               bigint not null auto_increment,
   activity_id          bigint not null,
   user_id              bigint not null,
   is_active            bool not null default true,
   primary key (add_id)
);

/*==============================================================*/
/* Table: assignment                                            */
/*==============================================================*/
create table assignment
(
   assign_id            bigint not null auto_increment,
   user_email			varchar(35) not null,
   description          varchar(100) not null,
   is_active            bool not null default true,
   total_time           bigint,
   primary key (assign_id)
);

/*==============================================================*/
/* Table: delete_request                                        */
/*==============================================================*/
create table request_to_delete
(
   delete_id            bigint not null auto_increment,
   assign_id            bigint not null unique,
   user_id              bigint not null,
   is_active            bool not null default true,
   primary key (delete_id)
);

/*==============================================================*/
/* Table: language                                              */
/*==============================================================*/
create table language
(
   language_id          bigint not null auto_increment,
   language_name        varchar(15) not null unique,
   language_code        varchar(2) not null unique,
   primary key (language_id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              bigint not null auto_increment,
   user_type_id         bigint not null,
   email                varchar(35) not null unique,
   password             varchar(64) not null,
   first_name           varchar(35) not null,
   last_name            varchar(35) not null,
   primary key (user_id)
);

/*==============================================================*/
/* Table: user_type                                             */
/*==============================================================*/
create table user_type
(
   user_type_id         bigint not null auto_increment,
   type_name            varchar(35) not null unique,
   primary key (user_type_id)
);

alter table activity_translate add constraint FK_activity_to_act_translate foreign key (activity_id)
      references activity (activity_id) on delete restrict on update restrict;

alter table activity_translate add constraint FK_anguage_to_act_translate foreign key (language_id)
      references language (language_id) on delete restrict on update restrict;

alter table request_to_add add constraint FK_activity_to_add_request foreign key (activity_id)
      references activity (activity_id) on delete restrict on update restrict;

alter table request_to_add add constraint FK_user_to_add_request foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table assignment add constraint FK_activity_to_assign foreign key (description)
      references activity (description) on delete restrict on update restrict;

alter table assignment add constraint FK_user_to_assign foreign key (user_email)
      references user (email) on delete restrict on update restrict;

alter table request_to_delete add constraint FK_assign_to_delete_request2 foreign key (assign_id)
      references assignment (assign_id) on delete restrict on update restrict;

alter table request_to_delete add constraint FK_user_to_delete_request foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table user add constraint FK_user_type_to_user foreign key (user_type_id)
      references user_type (user_type_id) on delete restrict on update restrict;


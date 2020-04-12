drop sequence if exists seq8_1;
create sequence seq8_1
	increment by 1
	minvalue 	10000000
	maxvalue 	39999999
	start 		10000000
	cache 1
	cycle
;

drop sequence if exists seq8_2;
create sequence seq8_2
	increment by 1
	minvalue 	40000000
	maxvalue 	69999999
	start		40000000
	cache 1
	cycle
;

drop sequence if exists seq8_3;
create sequence seq8_3
	increment by 1
	minvalue 	70000000
	maxvalue 	99999999
	start 		70000000
	cache 1
	cycle
;

drop table if exists users;
create table users (
  userid varchar(64) not null default hash('SHA256', to_char(now(), 'YYYYMMDDHH24MISS') || nextval('seq8_1') || nextval('seq8_2') || nextval('seq8_3')),
  first_name varchar(250) not null,
  last_name varchar(250) not null,
  email varchar(250) default null,
  reg_dt timestamp not null default current_timestamp,
  constraint users_pk primary key (userid)
);

/* for spring-security */
drop table if exists user_role_mng;
create table user_role_mng (
  role_id varchar(64) not null default hash('SHA256', to_char(now(), 'YYYYMMDDHH24MISS') || nextval('seq8_1') || nextval('seq8_2') || nextval('seq8_3')),
  role varchar(64) unique,
  role_desc varchar(255),
  reg_dt timestamp not null default current_timestamp,
  constraint user_role_mng_pk primary key (role_id)
);
drop table if exists user;
create table user (
  user_id varchar(64) not null default hash('SHA256', to_char(now(), 'YYYYMMDDHH24MISS') || nextval('seq8_1') || nextval('seq8_2') || nextval('seq8_3')),
  active_yn char(1) not null default 0,
  expired_yn char(1) not null default 0,
  locked_yn char(1) not null default 0,
  pwd_expired_yn char(1) not null default 0,
  login_id varchar(64) unique,
  user_name varchar(64) not null,
  pwd varchar(255) not null,
  reg_dt timestamp not null default current_timestamp,
  upd_dt timestamp not null default current_timestamp,
  constraint user_pk primary key (user_id)
);
drop table if exists user_role;
create table user_role(
  user_id varchar(64) not null ,
  role_id varchar(64) not null ,
  reg_dt timestamp not null default current_timestamp,
  constraint user_role_pk primary key (user_id, role_id)
);
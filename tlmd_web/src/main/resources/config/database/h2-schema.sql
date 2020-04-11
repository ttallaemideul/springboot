drop sequence if exists seq6_1;
create sequence seq6_1
	increment by 1
	minvalue 100000
	maxvalue 900000
	start 100000
	cache 1
	cycle
;

drop sequence if exists seq6_2;
create sequence seq6_2
	increment by 1
	minvalue 100000
	maxvalue 900000
	start 200000
	cache 1
	cycle
;

drop sequence if exists seq6_3;
create sequence seq6_3
	increment by 1
	minvalue 100000
	maxvalue 900000
	start 300000
	cache 1
	cycle
;

drop table if exists users;
  
create table users (
  userid varchar(32) not null default to_char(now(), 'YYYYMMDDHH24MISS') || nextval('seq6_1') || nextval('seq6_2') || nextval('seq6_3'),
  first_name varchar(250) not null,
  last_name varchar(250) not null,
  email varchar(250) default null,
  reg_dt timestamp not null default current_timestamp,
  constraint users_pk primary key (userid)
);
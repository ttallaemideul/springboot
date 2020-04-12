insert into users (first_name, last_name, email) values
  ('Bruce', 'Lee', 'bruce.lee@bruce.lee.com')
  ,('Github', 'Io', 'io@gitgub.com')
  ,('Captain', 'America', 'captain@marvel.com')
  ,('Ttallaemideul', 'Fafa', null)
  ;

insert into user_role_mng (role_id, role, role_desc) values
('10d586e706cf73c4a533ea58f0bdf88c5346cd81f654368a24b76109e3d9c0fd', 'USER', '사용자')
,('6668bc8ab45c3be7641b3ffbc14dd894a297cfb420eccc69da0f1e93f2dd9b03', 'ADMIN', '관리자')
;
insert into user (user_id
, active_yn
, login_id
, user_name
, pwd
) values
(
	'f06b79c296b30c645c969a60b5599c87bb4be92f0e7cebac907ace544b4c784b'
	, 1
	, 'admin'
	, '집사'
	, '$2a$10$mryqQ6w5YV.ZT6BC/5xC/eMiB25ye2ghnRsuMOkymD3zoX5m4u1ge'
)
, (
	'215087deafb0d72b6d489d2f1b4150a057a98df249c299b9dbc6a5dc774b7cdc'
	, 1
	, 'tlmd'
	, '아빠'
	, '$2a$10$mryqQ6w5YV.ZT6BC/5xC/eMiB25ye2ghnRsuMOkymD3zoX5m4u1ge'
)
;
insert into user_role (user_id, role_id) values
('f06b79c296b30c645c969a60b5599c87bb4be92f0e7cebac907ace544b4c784b', '6668bc8ab45c3be7641b3ffbc14dd894a297cfb420eccc69da0f1e93f2dd9b03')
,('215087deafb0d72b6d489d2f1b4150a057a98df249c299b9dbc6a5dc774b7cdc', '10d586e706cf73c4a533ea58f0bdf88c5346cd81f654368a24b76109e3d9c0fd')
;


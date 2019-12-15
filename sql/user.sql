prompt PL/SQL Developer Export Tables for user SCOTT@ORCL
prompt Created by _芸子 on 2019年12月15日
set feedback off
set define off

prompt Disabling triggers for TBL_USER...
alter table TBL_USER disable all triggers;
prompt Deleting TBL_USER...
delete from TBL_USER;
commit;
prompt Loading TBL_USER...
insert into TBL_USER (username, password)
values ('admin', '123456');
commit;
prompt 1 records loaded
prompt Enabling triggers for TBL_USER...
alter table TBL_USER enable all triggers;

set feedback on
set define on
prompt Done

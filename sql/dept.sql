prompt PL/SQL Developer Export Tables for user SCOTT@ORCL
prompt Created by _芸子 on 2019年12月15日
set feedback off
set define off

prompt Disabling triggers for TBL_DEPT...
alter table TBL_DEPT disable all triggers;
prompt Deleting TBL_DEPT...
delete from TBL_DEPT;
commit;
prompt Loading TBL_DEPT...
insert into TBL_DEPT (deptno, dname, loc)
values (10, 'ACCOUNTING', 'NEW YORK');
insert into TBL_DEPT (deptno, dname, loc)
values (20, 'RESEARCH', 'DALLAS');
insert into TBL_DEPT (deptno, dname, loc)
values (30, 'SALES', 'CHICAGO');
insert into TBL_DEPT (deptno, dname, loc)
values (40, 'OPERATIONS', 'BOSTON');
commit;
prompt 4 records loaded
prompt Enabling triggers for TBL_DEPT...
alter table TBL_DEPT enable all triggers;

set feedback on
set define on
prompt Done

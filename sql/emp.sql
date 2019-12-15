prompt PL/SQL Developer Export Tables for user SCOTT@ORCL
prompt Created by _芸子 on 2019年12月15日
set feedback off
set define off

prompt Disabling triggers for TBL_EMP...
alter table TBL_EMP disable all triggers;
prompt Deleting TBL_EMP...
delete from TBL_EMP;
commit;
prompt Loading TBL_EMP...
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7369, 'SMITH', 'CLERK', 7902, '2018/12/17', 800, null, 20);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7499, 'ALLEN', 'SALESMAN', 7698, '2018/2/20', 1600, 300, 30);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7521, 'WARD', 'SALESMAN', 7698, '2018/2/22', 1250, 500, 30);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7566, 'JONES', 'MANAGER', 7839, '2018/4/2', 2975, null, 20);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7654, 'MARTIN', 'SALESMAN', 7698, '2018/9/28', 1250, 1400, 30);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7698, 'BLAKE', 'MANAGER', 7839, '2018/5/1', 2850, null, 30);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7782, 'CLARK', 'MANAGER', 7839, '2018/6/9', 2450, null, 10);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7788, 'SCOTT', 'ANALYST', 7566, '2018/4/19', 3000, null, 20);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7839, 'KING', 'PRESid,ENT', null, '2018/11/17', 5000, null, 10);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7844, 'TURNER', 'SALESMAN', 7698, '2018/9/8', 1500, null, 30);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7876, 'ADAMS', 'CLERK', 7788, '2018/5/23', 1100, null, 20);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7900, 'JAMES', 'CLERK', 7698, '2018/12/3', 950, null, 30);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7902, 'FORD', 'ANALYST', 7566, '2018/12/3', 3000, null, 20);
insert into TBL_EMP (id, name, job, mgr, hiredate, sal, comm, deptno)
values (7934, 'MILLER', 'CLERK', 7782, '2018/1/23', 1300, null, 10);
commit;
prompt 14 records loaded
prompt Enabling triggers for TBL_EMP...
alter table TBL_EMP enable all triggers;

set feedback on
set define on
prompt Done

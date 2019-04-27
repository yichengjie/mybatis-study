CREATE DATABASE IF NOT EXISTS mybatis_db DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

drop table dept ;

create table dept(
  deptno int primary key auto_increment ,
  deptname varchar(30)
) ; 

insert into dept (deptname) values('部门1') ;
insert into dept (deptname) values('部门2') ;
insert into dept (deptname) values('部门3') ;


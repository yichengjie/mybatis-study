CREATE DATABASE IF NOT EXISTS mybatis_db DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

drop table dept ;

create table dept(
  deptno int primary key auto_increment ,
  deptname varchar(30)
) ; 

insert into dept (deptname) values('研发部') ;
insert into dept (deptname) values('合约部') ;
insert into dept (deptname) values('小卖部') ;


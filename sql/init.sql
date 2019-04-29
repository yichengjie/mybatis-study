CREATE DATABASE IF NOT EXISTS mybatis_db DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

drop table dept ;

create table dept(
  deptno int primary key auto_increment ,
  deptname varchar(30)
) ; 

insert into dept (deptname) values('部门1') ;
insert into dept (deptname) values('部门2') ;
insert into dept (deptname) values('部门3') ;


create table classes (
 cid       int           primary key auto_increment ,
 cname     varchar(50)   not null 
) ;

INSERT INTO `mybatis_db`.`classes`(`cid`,`cname`) VALUES ( '1','软件工程1版');
INSERT INTO `mybatis_db`.`classes`(`cid`,`cname`) VALUES ( '2','软件工程2班');

create table student(
  sid      int          primary key auto_increment ,
  sname varchar(50)     not null ,
  cid      int          not null
) ;
INSERT INTO `mybatis_db`.`studen`(`sid`,`sname`,`cid`) VALUES ( '1','张三','1');
INSERT INTO `mybatis_db`.`studen`(`sid`,`sname`,`cid`) VALUES ( '2','李四','1');
INSERT INTO `mybatis_db`.`studen`(`sid`,`sname`,`cid`) VALUES ( '3','王五','1');
INSERT INTO `mybatis_db`.`studen`(`sid`,`sname`,`cid`) VALUES ( '4','赵六','2');



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
INSERT INTO `mybatis_db`.`student`(`sid`,`sname`,`cid`) VALUES ( '1','张三','1');
INSERT INTO `mybatis_db`.`student`(`sid`,`sname`,`cid`) VALUES ( '2','李四','1');
INSERT INTO `mybatis_db`.`student`(`sid`,`sname`,`cid`) VALUES ( '3','王五','1');
INSERT INTO `mybatis_db`.`student`(`sid`,`sname`,`cid`) VALUES ( '4','赵六','2');



CREATE TABLE `mybatis_db`.`users`(     `uid` INT NOT NULL AUTO_INCREMENT ,     `uname` VARCHAR(50) NOT NULL ,     PRIMARY KEY (`uid`)  );
CREATE TABLE `mybatis_db`.`groups`(     `gid` INT NOT NULL AUTO_INCREMENT ,     `gname` VARCHAR(50) NOT NULL ,     PRIMARY KEY (`gid`)  );
CREATE TABLE `mybatis_db`.`users_groups`(     `user_id` INT ,     `group_id` INT   );

INSERT INTO `mybatis_db`.`groups`(`gid`,`gname`) VALUES ( '1','小组1');
INSERT INTO `mybatis_db`.`groups`(`gid`,`gname`) VALUES ( '2','小组2');
INSERT INTO `mybatis_db`.`groups`(`gid`,`gname`) VALUES ( '3','小组3');
INSERT INTO `mybatis_db`.`groups`(`gid`,`gname`) VALUES ( '4','小组4');
INSERT INTO `mybatis_db`.`groups`(`gid`,`gname`) VALUES ( '5','小组5');
INSERT INTO `mybatis_db`.`groups`(`gid`,`gname`) VALUES ( '6','小组6');
INSERT INTO `mybatis_db`.`groups`(`gid`,`gname`) VALUES ( '7','小组7');

INSERT INTO `mybatis_db`.`users`(`uid`,`uname`) VALUES ( '1','用户1');
INSERT INTO `mybatis_db`.`users`(`uid`,`uname`) VALUES ( '2','用户2');
INSERT INTO `mybatis_db`.`users`(`uid`,`uname`) VALUES ( '3','用户3');
INSERT INTO `mybatis_db`.`users`(`uid`,`uname`) VALUES ( '4','用户4');
INSERT INTO `mybatis_db`.`users`(`uid`,`uname`) VALUES ( '5','用户5');
INSERT INTO `mybatis_db`.`users`(`uid`,`uname`) VALUES ( '6','用户6');


INSERT INTO `mybatis_db`.`users_groups`(`user_id`,`group_id`) VALUES ( '1','3');
INSERT INTO `mybatis_db`.`users_groups`(`user_id`,`group_id`) VALUES ( '1','4');
INSERT INTO `mybatis_db`.`users_groups`(`user_id`,`group_id`) VALUES ( '1','5');
/*----------------------------------------------------------------------------*/
INSERT INTO `mybatis_db`.`users_groups`(`user_id`,`group_id`) VALUES ( '2','2');
INSERT INTO `mybatis_db`.`users_groups`(`user_id`,`group_id`) VALUES ( '2','3');
INSERT INTO `mybatis_db`.`users_groups`(`user_id`,`group_id`) VALUES ( '2','4');
INSERT INTO `mybatis_db`.`users_groups`(`user_id`,`group_id`) VALUES ( '2','5');






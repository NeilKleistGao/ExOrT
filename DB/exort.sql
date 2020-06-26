set character_set_server=utf8;

drop database if exists exort;
create database exort default character set utf8;

use exort;

drop table if exists `participation`;
drop table if exists `character`;
drop table if exists `arrangement`;

create table `character`(
    id int primary key auto_increment,
    name varchar(64) not null,
    area varchar(32),
    school varchar(128)
) engine=InnoDB default charset=utf8;

create table `arrangement`(
    id int primary key auto_increment,
    name varchar(64) not null,
    start_date Date not null,
    end_date Date not null,
    `repeat` int not null,
    start_time Time not null,
    end_time Time not null
) engine=InnoDB default charset=utf8;

create table `participation`(
    character_id int,
    arrangement_id int,
    primary key(character_id, arrangement_id),
    foreign key cid(character_id) references `character`(id) on delete no action on update no action,
    foreign key aid(arrangement_id) references `arrangement`(id) on delete no action on update no action
) engine=InnoDB default charset=utf8;

grant select, update, insert delete on exort.* on "exort"@"localhost" identified by "exort";

insert into `character`(id, name) values(0, "我");
create table if not exists `character`(
    id int primary key,
    name varchar(64) not null,
    area varchar(32) not null,
    school varchar(128)
) engine=InnoDB default charset=utf8;
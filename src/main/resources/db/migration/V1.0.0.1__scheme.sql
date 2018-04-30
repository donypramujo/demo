drop table if exists student;

create table student (
  id varchar(36),
  name varchar(255) not null,
  primary key (id)
);
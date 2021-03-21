CREATE SCHEMA if not exists todolists;
USE todolists;

drop table if exists to_do_task CASCADE;
drop table if exists task_list CASCADE;

create table task_list
(
    task_list_id bigint AUTO_INCREMENT,
    name         varchar(255) not null,
    primary key (task_list_id)
);
create table to_do_task
(
    task_id     bigint auto_increment,
    complete    boolean      not null,
    description varchar(255) not null,
    due_date    timestamp,
    task_list   bigint       not null,
    primary key (task_id)
);
alter table to_do_task
    add constraint FKo0tholkquejg2l5phe7tcx2np foreign key (task_list) references task_list (task_list_id) on delete cascade;
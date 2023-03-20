drop table if exists budget;
drop table if exists budget_category;

create table budget_category
(
    seq       bigint primary key auto_increment,
    upper_seq bigint,
    name      varchar(20) not null,
    foreign key (upper_seq) references budget_category (seq)
);

create table budget
(
    seq                 bigint primary key auto_increment,
    budget_category_seq bigint       not null,
    institution         varchar(20)  not null,
    content             varchar(100) not null,
    amount              int          not null,
    etc                 varchar(100),
    foreign key (budget_category_seq) references budget_category (seq)
);
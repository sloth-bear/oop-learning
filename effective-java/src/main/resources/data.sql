insert into budget_category(seq, name)
values (1, '수입');
insert into budget_category(seq, name)
values (2, '지출');
insert into budget_category(seq, name)
values (3, '저축');
insert into budget_category(seq, name)
values (4, '투자');

insert into budget_category(seq, upper_seq, name)
values (5, 2, '고정지출');
insert into budget_category(seq, upper_seq, name)
values (6, 2, '유동지출');

insert into budget_category(seq, upper_seq, name)
values (7, 3, '정기적금');

insert into budget_category(seq, upper_seq, name)
values (8, 4, '미국주식');

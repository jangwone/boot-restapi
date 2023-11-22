insert into user_details(id,birth_date,name)
values(10001, current_date(), 'user1');

insert into user_details(id,birth_date,name)
values(10002, current_date(), 'user2');

insert into user_details(id,birth_date,name)
values(10003, current_date(), 'user3');

insert into post(id,description,user_id)
values(20001, 'postno.1', 10001);

insert into post(id,description,user_id)
values(20002, 'postno.1', 10001);

insert into post(id,description,user_id)
values(20003, 'postno.1', 10001);

insert into post(id,description,user_id)
values(20004, 'postno.2', 10002);

insert into post(id,description,user_id)
values(20005, 'postno.2', 10002);
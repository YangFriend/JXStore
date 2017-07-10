
---------------------用户表 
create table t_user(
id int auto_increment primary key,
userName varchar(30) not null,
userPassword char(32) not null,
money double(18,2),
email varchar(30),
phone varchar(20),
gread  int(1),
regDate date,
status int(1) default 0
)
;
--金额需大于0
alter table t_user add constraint status_constraint check(status in(0,1));
--userName唯一
alter table t_user add constraint userName_constraint unique(userName);
--金额需大于0
alter table t_user add constraint money_constraint check(money > 0 );



---------------------商品表
create table t_goods(
id  int auto_increment primary key,
name varchar(25) not null,
price double(18,2) check (price >0),
image varchar(50),
gameType varchar(10) not null,
info varchar(4000),
addedDate date,
status int(1) default 0 check(status in( 1,0)),
constraint name_gameType_constraint unique(name,gameType)
)
;


---------------------账号库表
create table t_gameAccounts(
id  int auto_increment primary key,
goodsId int,
ter varchar(20) not null,
regDate date,
accountName varchar(30) not null,
password_ varchar(30) not null,
version_ int,
sell int(1) default 0 check(sell in( 1,0)),
foreign key (goodsId) references t_goods(id)
)
;


---------------------订单概览表
create table t_order(
id  int auto_increment primary key,
userId int,
orderDate date,
allValue double(18,2),
actPrice double(18,2),
goodsNum int(5),
accountNum int(5),
foreign key (userId) references t_user(id)
)
;

---------------------订单/商品详情
create table t_order_goodsDetail(
id  int auto_increment primary key,
orderId int,
goodsId int,
goodsname varchar(25),
goodsinfo varchar(4000),
goodsprice double(18,2),
num_ int(5) not null,
foreign key (orderId) references t_order(id)
)
;


---------------------订单/账号详情
create table t_order_accountDetail(
id  int auto_increment primary key,
goodsDetail_id int,
idx int,
ter varchar(20) not null,
accountName varchar(30) not null,
password varchar(30) not null
)
;

---------------------管理员
create table t_managerUser(
id_ int auto_increment primary key,
name_ varchar(30) not null,
password_ varchar(30) not null,
permission_ int(1) not null
)
;
alter table t_managerUser add constraint constraint_name_ unique(name_);
--alter table t_managerUser modify name_ not null;



---------------------公告表
create table t_bulletin(
id_ int auto_increment primary key,
manager_id int,
title varchar(100),
content text(4000),
releaseDate date,
foreign key (manager_id) references t_managerUser(id_)
)
;






---------------------





---------------------







---------------------用户表 
create table t_user(
id number primary key,
userName varchar2(30)  not null,	--用户名
userPassword char(32) not null,	--密码
money number(18,2), 				--账户余额			
email varchar2(30),					--电子邮件
phone varchar2(20),					--手机
gread  number(1), 				--会员等级 减价大酬宾
regDate date,					--注册日期
status number(1) default 0 check(status in(0,1))--账号状态 ;0为正常 1为被禁止登陆
)
;
alter table t_user add constraint email_constraint unique(email);		--email唯一
alter table t_user add constraint userName_constraint unique(userName);	--userName唯一
alter table t_user add constraint money_constraint check(money > 0 );	--金额需大于0
CREATE SEQUENCE t_user_seq INCREMENT BY 1 START WITH 1 NOMAXvalue NOCYCLE CACHE 10; --序列




---------------------商品表
create table t_goods(
id  number primary key,
name varchar2(25) not null,			--游戏名称
price number(10,2) check (price >0),--价格
image varchar2(50),					--图片
info varchar2(4000),				--介绍
gameType varchar2(10) not null,			--类型
addedDate date,  					--上架日期
status number(1) default 0 check(status in( 1,0)),--状态;0为正常,1为下架 
constraint name_gameType_constraint unique(name,gameType) --唯一约束 (游戏名称,类型)
)
;
CREATE SEQUENCE t_goods_seq INCREMENT BY 1 START WITH 1 NOMAXvalue NOCYCLE CACHE 10;



---------------------账号库表
create table t_gameAccounts(
id  number primary key,
goodsId references t_goods(id),		--外键约束为商品表(id )
ter varchar(20) not null,			--游戏平台
regDate date,               		--注册日期
accountName varchar(30) not null,	--账户名
password_ varchar(30) not null,  	--密码
version_ number,     				--同步锁 版本标记(hibernate) 
sell number(1) default 0 check(sell in( 1,0))--是否售出; 0为未售,1为售出
)
;

CREATE SEQUENCE t_gameAccounts_seq INCREMENT BY 1 START WITH 1 NOMAXvalue NOCYCLE CACHE 10;



---------------------订单概览表
create table t_order(
id  number primary key,			--主键
userId references t_user(id),--外键约束为用户表(id )
orderDate date,					--下单日期
allValue number(12,2),			--商品价值
actPrice number(12,2)			--实付金额
goodsNum number(5),				--购买的游戏数
accountNum number(5)			--获得的账号数
)
;
CREATE SEQUENCE t_order_seq INCREMENT BY 1 START WITH 1 NOMAXvalue NOCYCLE CACHE 10;

---------------------订单/商品详情
create table t_order_goodsDetail(
id  number primary key,	
orderId references t_order(id),	--外键约束为订单表(id )
goodsId number,					--商品id
goodsname varchar2(25),				--游戏名称
goodsinfo varchar2(4000),		--介绍
goodsprice number(10,2),		--价格
num_ number(5) not null 		--购买数量
)
;
CREATE SEQUENCE t_orderDetail_seq INCREMENT BY 1 START WITH 1 NOMAXvalue NOCYCLE CACHE 10;

---------------------订单/账号详情
create table t_order_accountDetail(
id  number primary key,	
goodsDetail_id references t_order_goodsDetail(id), --约束商品详情表(id )
idx number,
ter varchar(20) not null,		  --游戏平台
accountName varchar(30) not null, --账户名
password varchar(30) not null    	--密码
)
;
CREATE SEQUENCE t_accountDetail_seq INCREMENT BY 1 START WITH 1 NOMAXvalue NOCYCLE CACHE 10;



---------------------管理员
create table t_managerUser(
id_ number primary key,	
name_ varchar2(30) not null,	--登陆名
password_ varchar2(30) not null,--密码
permission_ number(1) not null	--权限等级
)
;
alter table t_managerUser add constraint constraint_name_ unique(name_);
--alter table t_managerUser modify name_ not null;

CREATE SEQUENCE t_managerUser_seq INCREMENT BY 1 START WITH 1 NOMAXvalue NOCYCLE CACHE 10;


---------------------公告表
create table t_bulletin(
id_ number primary key,	
manager_id references t_managerUser(id_),--管理员id
title varchar2(100),					--标题
content varchar2(4000),					--内容
releaseDate date						--发布日期
)
;
CREATE SEQUENCE t_bulletin_seq INCREMENT BY 1 START WITH 1 NOMAXvalue NOCYCLE CACHE 10;








---------------------





---------------------






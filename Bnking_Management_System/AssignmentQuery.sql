use mydb;
create table Bank(account_no integer,
account_holder varchar(20) primary key,
ifsc_code varchar(20),
branch varchar(10),
account_type varchar(10),
balance numeric(12,2),
pin numeric(4));

use mydb;
create table bank(
acc_no integer primary key auto_increment,
acc_holder varchar(20) not null,
ifsc_code varchar(20) unique not null,
branch varchar(10),
acc_type varchar(10),
balance numeric(12,2) check(balance >1999),
pin numeric(4) unique not null
);
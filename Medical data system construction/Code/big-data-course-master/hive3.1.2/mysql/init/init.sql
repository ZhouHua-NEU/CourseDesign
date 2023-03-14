create database hive_db;
create user hive IDENTIFIED by 'hive';
grant all privileges on *.* to hive@'%' identified by 'hive';
grant all privileges on *.* to root@'%' identified by 'root';
flush privileges;

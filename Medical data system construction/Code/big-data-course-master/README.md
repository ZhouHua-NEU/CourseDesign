# BigDataCourse

## 1. 介绍
使用Dockerfile、docker-compose搭建大数据基础组件

## 2. TODO

- [x] Hadoop3.1.3
- [x] Zookeeper
- [x] HBase
- [x] Hive
- [x] ElasticSearch
- [x] Kafka
- [ ] Spark
- [ ] Flink


## 3. 问题

1. Port are not available: listen tcp 0.0.0.0/<port>: bind: An attempt...
Windows解决方法: 
在powershell中运行以下命令
```
net stop winnat
net start winnat
```

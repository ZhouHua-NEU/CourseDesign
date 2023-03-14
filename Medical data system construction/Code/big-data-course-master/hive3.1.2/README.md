​		本项目构造了一个本地+远程模式的Hive (3.1.2) 三节点集群, 同时, 在三个节点中还包含了Hadoop (3.1.3) 核心组件, 如HDFS、YARN、MapReduce. 在三节点集群外, 一个包含mysql服务的节点用于存储Hive的元数据信息. 整个集群的信息图下:

| 节点hostname   | 节点ip     | 部署的服务                                                   | 用途      |
| -------------- | ---------- | ------------------------------------------------------------ | --------- |
| cluster-master | 172.15.0.2 | NameNode、ResourceManager<br />MetastoreServer、HiveServer2、HiveCli、BeelineCli | 主节点    |
| cluster-slave1 | 172.15.0.3 | DataNode、NodeManager、HiveCli、BeelineCli                   | 从节点    |
| cluster-slave2 | 172.15.0.4 | DataNode、NodeManager、HiveCli、BeelineCli                   | 从节点    |
| mysql          | 172.15.0.5 | mysqld                                                       | mysql服务 |

​		此外, 如果宿主机资源允许, 可以在集群中开启hbase和zookeeper服务. 开启方法如下:

- 开启zookeeper: 分别进入三个节点(除mysql节点), 运行命令`zkServer.sh start`;
- 开启hbase: 进入cluster-master节点, 运行命令`start-hbase.sh`

## 1. 如何使用

​		**开启**: 在当前目录运行命令`docker-compose up -d` 

​		**关闭**: 在当前目录运行命令`docker-compose down`

## 2. 其它信息

| 服务名称  | 端口                                                         |
| --------- | ------------------------------------------------------------ |
| HDFS      | WebUI端口: 9870<br />RPC通信端口: 9000                       |
| MapReduce | WebUI端口: 8088                                              |
| Hive      | HiveServer2 WebUI端口: 10002<br />HiveServer2 RPC通信端口: 10000 |
| Mysql     | JDBC 端口: 3306                                              |






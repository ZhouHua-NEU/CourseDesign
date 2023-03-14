##  1. 环境介绍

​        本次`docker-compose.yml`中除了配置了Flume和ElasticSearch套件(ElasticSearch7.8, Kibana7.8, es_head)外, 还包含前面所构建的Hadoop生态圈组件(HDFS、MR、YARN、Zookeeper、Hbase、Hive、Kafka、Sqoop). 

​		<u>由于ES对于资源需求非常大, 若是在资源受限的Docker中运行ES集群, 很可能会面临已开启的服务掉线或是ES运行不成功的窘迫.</u> 为了避免这种情况发生, **本项目目前只默认运行ES套件**, 其他服务处于关闭状态.  如需使用其他服务, 建议先关闭ES套件, 然后开启其他服务, 所需运行的命令如下:

- 关闭ES套件

  | 服务    | 命令                                     | 仅cluster-master运行 | 所有主机运行 |
  | ------- | ---------------------------------------- | -------------------- | ------------ |
  | ES      | jps\|grep Elasticsearch<br>kill -9 <PID> | no                   | yes          |
  | Kibana  | lsof -i:5601<br />kill -9 <PID>          | yes                  | no           |
  | ES_Head | lsof -i:9100<br />kill -9 <PID>          | yes                  | no           |

- 开启服务

  | 服务      | 命令                                                         | 仅cluster-master运行 | 所有主机运行 |
  | --------- | ------------------------------------------------------------ | -------------------- | ------------ |
  | Hadoop    | start-all.sh                                                 | yes                  | no           |
  | Zookeeper | zkServer.sh start                                            | no                   | yes          |
  | HBase     | start-hbase.sh                                               | yes                  | no           |
  | Hive      | nohup hive --service metastore >> <br />$HIVE_HOME/metastoreservice.txt 2>&1 & | yes                  | no           |
  | Hive      | nohup hive --service hiveserver2 >> <br />$HIVE_HOME/hiveserver2.txt 2>&1 & | yes                  | no           |
  | Kafka     | kafka-server-start.sh -daemon <br />$KAFKA_HOME/config/server.properties | no                   | yes          |
  | ES        | su elsearch && elasticsearch -d                              | no                   | yes          |
  | Kibana    | nohup kibana --allow-root >> <br />$KIBANA_HOME/log 2>&1 &   | yes                  | no           |
  | ES_Head   | nohup grunt server >> es_head.log 2>&1 &                     | yes                  | no           |

## 2. ES 端口介绍

ES端口和说明如下表:

| 服务          | 端口号 | 说明                              |
| ------------- | ------ | --------------------------------- |
| ElasticSearch | 9200   | http访问端口, 支持RESTful API访问 |
| ElasticSearch | 9300   | 客户端监听端口                    |
| ES_Head       | 9100   | ES_Head Web UI                    |
| Kibana        | 5601   | Kibana Web UI                     |



## 3. 开启和关闭

在当前目录下:

- 开启服务: `docker-compose up -d`
- 关闭服务: `docker-compose down`
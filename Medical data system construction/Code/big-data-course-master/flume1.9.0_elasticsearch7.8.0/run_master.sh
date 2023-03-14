# echo ">>>>>>>>>>>>>>>>>>>> RUNNING zookeeper <<<<<<<<<<<<<<<<<<<<" &&\
# $ZOOKEEPER_HOME/bin/zkServer.sh start &&\
# echo ">>>>>>>>>>>>>>>>>>>> RUNNING hadoop <<<<<<<<<<<<<<<<<<<<" &&\
# /bin/bash ${HADOOP_HOME}/sbin/start-all.sh &&\
# echo ">>>>>>>>>>>>>>>>>>>> RUNNING hive <<<<<<<<<<<<<<<<<<<<" &&\
# ${HIVE_HOME}/bin/schematool -dbType mysql -initSchema &&\
# /bin/bash -c "nohup ${HIVE_HOME}/bin/hive --service metastore >> /metastoreservice.txt 2>&1 &" &&\
# /bin/bash -c "nohup ${HIVE_HOME}/bin/hive --service hiveserver2 >> /hiveserver2.txt 2>&1 &" &&\
# echo ">>>>>>>>>>>>>>>>>>>> RUNNING hbase <<<<<<<<<<<<<<<<<<<<" &&\
# $HBASE_HOME/bin/start-hbase.sh &&\
# echo ">>>>>>>>>>>>>>>>>>>> RUNNING kafka <<<<<<<<<<<<<<<<<<<<" &&\
# $KAFKA_HOME/bin/kafka-server-start.sh -daemon $KAFKA_HOME/config/server.properties &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING elasticsearch <<<<<<<<<<<<<<<<<<<<" &&\
cd $ES_HOME/bin && su elsearch -c "./elasticsearch -d" &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING kibana <<<<<<<<<<<<<<<<<<<<" &&\
/bin/bash -c "nohup $KIBANA_HOME/bin/kibana --allow-root >> $KIBANA_HOME/log 2>&1 &" &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING es_head <<<<<<<<<<<<<<<<<<<<" &&\
export PATH=$PATH:$NODE_HOME/bin &&\
cd /opt/elasticsearch/head && /bin/bash -c "nohup $NODE_HOME/bin/grunt server >> es_head.log 2>&1 &" &&\
echo "start success" && sleep 36000000s && echo "stop service"
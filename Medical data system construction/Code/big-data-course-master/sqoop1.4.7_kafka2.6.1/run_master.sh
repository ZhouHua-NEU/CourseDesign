echo ">>>>>>>>>>>>>>>>>>>> RUNNING hadoop <<<<<<<<<<<<<<<<<<<<" &&\
/bin/bash ${HADOOP_HOME}/sbin/start-all.sh &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING hive <<<<<<<<<<<<<<<<<<<<" &&\
${HIVE_HOME}/bin/schematool -dbType mysql -initSchema &&\
/bin/bash -c "nohup ${HIVE_HOME}/bin/hive --service metastore >> /metastoreservice.txt 2>&1 &" &&\
/bin/bash -c "nohup ${HIVE_HOME}/bin/hive --service hiveserver2 >> /hiveserver2.txt 2>&1 &" &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING zookeeper <<<<<<<<<<<<<<<<<<<<" &&\
$ZOOKEEPER_HOME/bin/zkServer.sh start &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING hbase <<<<<<<<<<<<<<<<<<<<" &&\
$HBASE_HOME/bin/start-hbase.sh &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING sqoop <<<<<<<<<<<<<<<<<<<<" &&\
sed -i -e 's/\r//g' "/data/sqoop/sqoop-env.sh" && sed -i -e 's/\r//g' "/data/sqoop/prepare_sqoop.sh" &&\
chmod 777 /data/sqoop/prepare_sqoop.sh && /data/sqoop/prepare_sqoop.sh &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING kafka <<<<<<<<<<<<<<<<<<<<" &&\
sed -i -e 's/\r//g' "/data/kafka/prepare_kafka.sh" &&\
chmod 777 /data/kafka/prepare_kafka.sh && /data/kafka/prepare_kafka.sh &&\
$KAFKA_HOME/bin/kafka-server-start.sh -daemon $KAFKA_HOME/config/server.properties &&\
echo "start success" && sleep 36000000s && echo "stop service"

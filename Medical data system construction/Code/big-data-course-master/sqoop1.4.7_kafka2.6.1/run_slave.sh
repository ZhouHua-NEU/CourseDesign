echo ">>>>>>>>>>>>>>>>>>>> RUNNING zookeeper <<<<<<<<<<<<<<<<<<<<" &&\
$ZOOKEEPER_HOME/bin/zkServer.sh start &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING sqoop <<<<<<<<<<<<<<<<<<<<" &&\
sed -i -e 's/\r//g' "/data/sqoop/sqoop-env.sh" && sed -i -e 's/\r//g' "/data/sqoop/prepare_sqoop.sh" &&\
chmod 777 /data/sqoop/prepare_sqoop.sh && /data/sqoop/prepare_sqoop.sh &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING kafka <<<<<<<<<<<<<<<<<<<<" &&\
sed -i -e 's/\r//g' "/data/kafka/prepare_kafka.sh" &&\
chmod 777 /data/kafka/prepare_kafka.sh && /data/kafka/prepare_kafka.sh &&\
$KAFKA_HOME/bin/kafka-server-start.sh -daemon $KAFKA_HOME/config/server.properties &&\
echo "start success" && sleep 36000000s && echo "stop service"

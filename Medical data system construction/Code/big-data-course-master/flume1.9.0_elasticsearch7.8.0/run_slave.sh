# echo ">>>>>>>>>>>>>>>>>>>> RUNNING zookeeper <<<<<<<<<<<<<<<<<<<<" &&\
# $ZOOKEEPER_HOME/bin/zkServer.sh start &&\
# echo ">>>>>>>>>>>>>>>>>>>> RUNNING kafka <<<<<<<<<<<<<<<<<<<<" &&\
# $KAFKA_HOME/bin/kafka-server-start.sh -daemon $KAFKA_HOME/config/server.properties &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING elasticsearch <<<<<<<<<<<<<<<<<<<<" &&\
cd $ES_HOME/bin && su elsearch -c "./elasticsearch -d" &&\
echo "start success" && sleep 36000000s && echo "stop service"
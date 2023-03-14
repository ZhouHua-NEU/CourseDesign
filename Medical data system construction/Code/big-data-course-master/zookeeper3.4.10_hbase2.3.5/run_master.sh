echo ">>>>>>>>>>>>>>>>>>>> RUNNING hadoop <<<<<<<<<<<<<<<<<<<<" &&\
/bin/bash ${HADOOP_HOME}/sbin/start-all.sh &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING zookeeper <<<<<<<<<<<<<<<<<<<<" &&\
/bin/bash ${ZOOKEEPER_HOME}/bin/zkServer.sh start &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING hbase <<<<<<<<<<<<<<<<<<<<" &&\
/bin/bash ${HBASE_HOME}/bin/start-hbase.sh &&\
echo "start success" && sleep 36000000s && echo "stop service"

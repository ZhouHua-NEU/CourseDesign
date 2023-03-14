echo ">>>>>>>>>>>>>>>>>>>> RUNNING hadoop <<<<<<<<<<<<<<<<<<<<" &&\
/bin/bash ${HADOOP_HOME}/sbin/start-all.sh &&\
echo ">>>>>>>>>>>>>>>>>>>> RUNNING hive <<<<<<<<<<<<<<<<<<<<" &&\
${HIVE_HOME}/bin/schematool -dbType mysql -initSchema &&\
/bin/bash -c "nohup ${HIVE_HOME}/bin/hive --service metastore >> /metastoreservice.txt 2>&1 &" &&\
/bin/bash -c "nohup ${HIVE_HOME}/bin/hive --service hiveserver2 >> /hiveserver2.txt 2>&1 &" &&\
echo "start success" && sleep 36000000s && echo "stop service"
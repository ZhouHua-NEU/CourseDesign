DATA_DIR="/data"
KAFKA_FILE_NAME="kafka_2.12-2.6.1.tgz"
KAFKA_NEW_NAME="kafka-2.12"
KAFKA_SAVE_DIR="/opt/kafka"
KAFKA_DOWNLOAD_URL="https://mirrors.tuna.tsinghua.edu.cn/apache/kafka/3.1.2/kafka_2.12-3.1.2.tgz"
KAFKA_HOME="$KAFKA_SAVE_DIR/$KAFKA_NEW_NAME"
mkdir -p $KAFKA_SAVE_DIR
tar -zxvf "$DATA_DIR/$KAFKA_FILE_NAME" -C $KAFKA_SAVE_DIR
mv -f "$KAFKA_SAVE_DIR/kafka_2.12-2.6.1" "$KAFKA_SAVE_DIR/$KAFKA_NEW_NAME"

echo "" >> "/root/.bashrc"
echo "export KAFKA_HOME=$KAFKA_HOME" >> "/root/.bashrc"
echo "export PATH=\$PATH:\$KAFKA_HOME/bin" >> "/root/.bashrc"

hostname=$(hostname)
echo -e "$hostname"
if [ $hostname = "cluster-master" ]; then
        id=1;
elif [ $hostname = "cluster-slave1" ]; then
        id=2;
else
        id=3;
fi

echo "# 当前Broker(KAFKA节点)的id" >> "$KAFKA_HOME/config/server.properties"
echo "broker.id=${id}" >> "$KAFKA_HOME/config/server.properties"
echo "# 每个主题分区的数量" >> "$KAFKA_HOME/config/server.properties"
echo "num.partitions=2" >> "$KAFKA_HOME/config/server.properties"
echo "# 每个分区默认的副本数, 不包含领导者副本" >> "$KAFKA_HOME/config/server.properties"
echo "default.replication.factor=2" >> "$KAFKA_HOME/config/server.properties"
echo "# KAFKA服务监听地址,为\${host_ip}:port" >> "$KAFKA_HOME/config/server.properties"
echo "listeners=PLAINTEXT://$hostname:9092" >> "$KAFKA_HOME/config/server.properties"
echo "# 系统日志默认存储位置" >> "$KAFKA_HOME/config/server.properties"
echo "log.dir=/opt/kafka/kafka-2.6.0/logs" >> "$KAFKA_HOME/config/server.properties"
echo "# KAFKA在zookeeper上的工作空间, 为了Zookeeper节点的规范性, " >> "$KAFKA_HOME/config/server.properties"
echo "# 将KAFKA的工作空间设置为/kafka" >> "$KAFKA_HOME/config/server.properties"
echo "zookeeper.connect=${hostname}:2181/kafka" >> "$KAFKA_HOME/config/server.properties"

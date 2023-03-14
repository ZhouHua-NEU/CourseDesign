DATA_DIR="/data"
SQOOP_FILE_NAME="sqoop-1.4.7.bin__hadoop-2.6.0.tar.gz"
HIVE_HIME="/opt/hive/hive-3.1.2"
SQOOP_HOME="/opt/sqoop/sqoop-1.4.7"
mkdir -p "/opt/sqoop"
tar -zxvf "$DATA_DIR/$SQOOP_FILE_NAME" -C "/opt/sqoop/"
mv -f "/opt/sqoop/sqoop-1.4.7.bin__hadoop-2.6.0/" "/opt/sqoop/sqoop-1.4.7"
echo "export SQOOP_HOME=/opt/sqoop/sqoop-1.4.7" >> "/root/.bashrc"
echo "export PATH=\$PATH:\$SQOOP_HOME/bin" >> "/root/.bashrc"
echo "export HADOOP_CLASSPATH=\$HADOOP_CLASSPATH:\$HIVE_HOME/lib/*" >> "/root/.bashrc"
cp "$HIVE_HOME/lib/mysql-connector-java-5.1.47.jar" "$SQOOP_HOME/lib/"
cp "$DATA_DIR/sqoop/sqoop-env.sh" "$SQOOP_HOME/conf"
chmod a+rwx -R /opt/sqoop
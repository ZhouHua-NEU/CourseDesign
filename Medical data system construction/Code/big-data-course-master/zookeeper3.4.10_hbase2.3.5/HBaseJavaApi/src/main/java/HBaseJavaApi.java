import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HBaseJavaApi {

    // 数据库连接配置
    private static Configuration conf = HBaseConfiguration.create();

    // 数据库连接实例
    private static Connection connection = null;

    // 数据库管理对象
    private static Admin admin = null;


    public static void main(String[] args) throws IOException {

        // 1. 连接HBase数据库
        connectHBase();


        // 2. 创建数据表
        String tableName = "students_info";
        List<String> columnFamilies = Arrays.asList("personal_info", "office_info");
        createTable(tableName, columnFamilies);

        // 3. 在表中插入数据
        List<Map<String, String>> data = new ArrayList<>();
        Map<String, String> item = new HashMap<>();
        item.put("name", "张三");
        item.put("city", "北京");
        item.put("phone", "131********");
        item.put("tel", "010-11111111");
        item.put("address", "atguigu");
        data.add(item);
        insertData(tableName, data);

        // 4. 查询数据
        String rowKey = "row_key1";
        getData(tableName, rowKey);

        // 5. 删除数据
        deleteData(tableName, rowKey);

        // 6. 关闭HBase数据库
        closeHBase();
    }

    private static void deleteData(String tableName, String rowKey) throws IOException {
        // 新建一个Table对象，传入指定的table名，Table负责与记录相关的操作，如增删改查等
        Table table = connection.getTable(TableName.valueOf(tableName));
        // 创建删除对象Delete，根据rowkey删除一整条数据
        Delete delete = new Delete(rowKey.getBytes());
        table.delete(delete);
        // 释放资源
        table.close();
        System.out.println("数据删除成功");
    }

    private static void getData(String tableName, String rowKey) throws IOException {
        // 新建一个Table对象，传入指定的table名，Table负责与记录相关的操作，如增删改查等
        Table table = connection.getTable(TableName.valueOf(tableName));
        // 创建Get对象，Get对象可以根据rowkey查询
        Get get = new Get(rowKey.getBytes());
        // 查询数据，取得结果集，保存在Result中
        Result result = table.get(get);
        // 循环输出单元格的数据
        for (Cell cell : result.rawCells()){
            // 取得当前单元格所属列族的名称
            String family = new String(CellUtil.cloneFamily(cell));
            // 取得当前单元格的列名
            String qualifier = new String(CellUtil.cloneQualifier(cell));
            // 取得当前单元格的列值
            String value = new String(CellUtil.cloneValue(cell));
            // 输出结果
            System.out.println("列：" + family + ":" + qualifier + "————值:" + value);
        }
        // 释放资源
        table.close();
        System.out.println("数据查询成功");
    }

    private static void insertData(String tableName, List<Map<String, String>> data) throws IOException {
        System.out.println("开始添加数据");
        // 获取一个Table对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        // 新建Put对象并指定ROWKEY
        Put put = new Put("row_key1".getBytes(StandardCharsets.UTF_8));
        // 指定列族"personal_info"、列名"name"、值"张三"
        for (Map<String, String> item : data) {
            put.addColumn("personal_info".getBytes(), "name".getBytes(), item.get("name").getBytes());
            put.addColumn("personal_info".getBytes(), "city".getBytes(), item.get("city").getBytes());
            put.addColumn("personal_info".getBytes(), "phone".getBytes(), item.get("phone").getBytes());

            put.addColumn("office_info".getBytes(), "phone".getBytes(), item.get("phone").getBytes());
            put.addColumn("office_info".getBytes(), "address".getBytes(), item.get("address").getBytes());
        }
        // Table对象调用put操作
        table.put(put);

        // 操作执行完成，关闭table
        table.close();
        System.out.println("数据插入完毕");
    }

    private static void createTable(String table, List<String> columnFamilies) throws IOException {
        // 设置HBase表名
        TableName tableName = TableName.valueOf(table);
        // 调用tableExists查看数据表是否存在
        if (admin.tableExists(tableName)){
            System.out.println("表已存在， 将删除原表");
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            System.out.println("已删除原表");
        }
        // 新建一个HTableDescriptor表的描述对象，用于添加列族
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        // 遍历列族，挨个将列族添加到HTableDescriptor对象中
        for (String cf: columnFamilies){
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }

        // 调用createTable创建数据表
        admin.createTable(hTableDescriptor);
        System.out.println("数据表创建成功");



    }

    private static void closeHBase() throws IOException {
        if (admin != null){
            admin.close();
            admin = null;
        }
        if (connection != null){
            connection.close();
            connection = null;
        }

    }

    private static void connectHBase() throws IOException {
        // 设置Zookeeper的通信ip和端口，需要与集群中的Zookeeper对应
        conf.set("hbase.zookeeper.quorum",
                "172.15.0.2:2181,172.15.0.3:2181,172.15.0.4:2181");

        // 设置连接对象
        connection = ConnectionFactory.createConnection(conf);

        // 获取数据库管理对象
        admin = connection.getAdmin();
        System.out.println("数据库连接成功");
    }
}

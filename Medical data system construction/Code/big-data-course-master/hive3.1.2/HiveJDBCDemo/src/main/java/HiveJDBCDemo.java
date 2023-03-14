import java.sql.*;

public class HiveJDBCDemo {

    // 1. 创建驱动、地址、用户名和密码属性
    public static String driver = "org.apache.hive.jdbc.HiveDriver";
    public static String url = "jdbc:hive2://cluster-master:10000/test_hive2";
    public static String userName = "root";
    public static String password = "";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 2. 加载驱动
        Class.forName(driver);
        // 3. 创建链接
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();

        // 4. 执行查询结果
        ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
        // 5. 处理查询结果
        // 将每一行的每个元素按照<Column_Name>-<Column_Class>-<Column_Value>打印
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()){
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                System.out.print(metaData.getColumnName(i+1) + "-"
                        + metaData.getColumnClassName(i+1) + "-"
                        + resultSet.getString(i+1) + "\t"
                );
            }
            System.out.println();
        }

        // 6.关闭链接， 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}

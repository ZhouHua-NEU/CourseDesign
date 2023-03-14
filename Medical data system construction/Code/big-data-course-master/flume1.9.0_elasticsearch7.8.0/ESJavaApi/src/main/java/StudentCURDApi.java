import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class StudentCURDApi {

    // 声明全局变量
    private static TransportClient client;
    public static void main(String[] args) throws UnknownHostException {
        // 1. 连接ES集群
        connetES();
        // 2. 新增一条学生信息
        addStudentInfo();

        // 3. 更新学生信息
        updateStudentInfo();

        // 4. 查询学生信息
        getStudentInfo();

        // 5. 删除学生信息
        deleteStudentInfo();


        // 6. 关闭连接
        closeES();


    }

    private static void connetES() throws UnknownHostException {
        // 准备环境：集群名称、集群地址、TCP端口（注意和RESTful API）端口区分
        Settings setting = Settings.builder().put("cluster.name", "es-cluster").build();
        String hostIp = "172.15.0.2";
        Integer hostPort = 9300;
        // 建立一个客户端对象
        client = new PreBuiltTransportClient(setting).addTransportAddress(
                new TransportAddress(InetAddress.getByName(hostIp), hostPort)
        );
    }

    private static void addStudentInfo() {
        // 配置数据信息，可以有多种方式配置， 这里介绍使用HashMap的配置方法
        Map<String, Object> data = new HashMap<>();
        data.put("name", "wangwu");
        data.put("age", 18);
        data.put("gender", "male");
        data.put("score", 97);
        // 可以不指定文档的id， 由ES自动生成id
        IndexResponse indexResponse = client.prepareIndex("student", "_doc", "1001")
                .setSource(data).get();
        System.out.println(indexResponse.getResult());
    }

    private static void updateStudentInfo() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("score", 98);
        // 更新文档时需要指定索引、类型、文档id
        UpdateResponse updateResponse = client.prepareUpdate("student", "_doc", "1001")
                .setDoc(data).get();
        System.out.println(updateResponse.getResult());
    }

    private static void getStudentInfo() {
        // 查询文档时也需要指定索引、类型、文档id
        GetResponse response = client.prepareGet("student", "_doc", "1001").get();
        System.out.println(response.getSourceAsString());
    }

    private static void deleteStudentInfo() {
        DeleteResponse deleteResponse = client.prepareDelete("student", "_doc", "1001").get();
        System.out.println(deleteResponse.getResult());
    }

    private static void closeES() {
        client.close();
    }


}

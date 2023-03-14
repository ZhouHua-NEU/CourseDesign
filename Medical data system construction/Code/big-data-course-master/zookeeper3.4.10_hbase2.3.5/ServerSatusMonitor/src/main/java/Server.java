import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Server {
    //Zookeeper连接字符串
    private static String connectString = "172.15.0.2:2181,172.15.0.3:2181,172.15.0.4:2181";
    // 会话超时最大时长
    private static int sessionTimeout = 2000;
    // Zookeeper的指定监听节点
    private static String PARENTNODE = "/servers";
    // 全局变量Zookeeper对象
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        // 1. 连接zookeeper
        connectZookeeper();


        // 2. 创建临时顺序节点
        createEphemeralNode(args[0]);


        // 3. 执行业务代码
        doBussiness();


        // 4. 关闭连接
        closeZookeeper();
    }

    private static void closeZookeeper() throws InterruptedException {
        zooKeeper.close();
        zooKeeper = null;
    }

    private static void doBussiness() throws InterruptedException {
        Thread.sleep(2000);
    }

    private static void createEphemeralNode(String hostname) throws InterruptedException, KeeperException {
        zooKeeper.create(PARENTNODE + "/" + hostname, hostname.getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    private static void connectZookeeper() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, null);
    }

}

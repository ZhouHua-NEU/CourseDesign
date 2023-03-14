import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Client {

    //Zookeeper连接字符串
    private static String connectString = "172.15.0.2:2181,172.15.0.3:2181,172.15.0.4:2181";
    // 会话超时最大时长
    private static int sessionTimeout = 2000;
    // Zookeeper的指定监听节点
    private static String PARENTNODE = "/servers";
    // 全局变量Zookeeper对象
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {

        // 1. 连接Zookeeper并设置watcher
        connectZookeeper();

        // 2. 创建/servers持久节点
        createServers();

        // 3. 打印/servers节点所有的子节点列表
        printServerList();

        // 4. 保持连接
        keepConnect();
    }

    private static void keepConnect() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private static void printServerList() throws InterruptedException, KeeperException {
        final List<String> children = zooKeeper.getChildren(PARENTNODE, true);
        for (String child: children) {
            System.out.println(PARENTNODE + "/" + child);
        }
    }

    private static void createServers() throws InterruptedException, KeeperException {
        zooKeeper.create(PARENTNODE, "服务器列表".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    private static void connectZookeeper() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, watchedEvent -> {
           if (watchedEvent.getType() == Watcher.Event.EventType.NodeChildrenChanged){
               try {
                   printServerList();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } catch (KeeperException e) {
                   e.printStackTrace();
               }
           }
        });

    }
}

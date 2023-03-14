import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ZookeeperWatcherApi {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        // 创建连接，调用Zookeeper构造函数，函数可传入参数为
        // 1. 连接字符串，定义在$ZOOKEEPER_HOME/conf/zoo.cfg中，由zookeeper服务器的hostname:port组成
        // 2. 连接时长，当连接超过该时长是抛出connectionTimeOut异常
        // 3. Watcher机制，用于监听真个Zookeeper集群的节点
        String connectStr = "172.15.0.2:2181,172.15.0.3:2181,172.15.0.4:2181";


        // 使用构造函数创建Zookeeper对象并设置Watcher
        ZooKeeper zk = new ZooKeeper(connectStr, 3000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getType() == Event.EventType.NodeCreated){
                    System.out.println(watchedEvent.getPath() + "被创建");
                } else if (watchedEvent.getType() == Event.EventType.NodeDeleted){
                    System.out.println(watchedEvent.getPath() + "被删除");
                } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                    System.out.println(watchedEvent.getPath() + "子节点被删除");
                } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged){
                    System.out.println(watchedEvent.getPath() + "数据被修改");
                } else {
                    System.out.println("Nothing to do.");
                }
            }
        });

        // 使用exists()设置监听, 直接设置true会回传到构造函数
        zk.exists("/app2", true);
        zk.create("/app2", "test app 2".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);


        // 使用getData()设置监听，直接设置true回传到构造函数
        zk.getData("/app2", true, null);
        zk.setData("/app2", "test app 3".getBytes(StandardCharsets.UTF_8), -1);


        // 使用getChildren设置监听，直接设置true回传到构造函数
        zk.getChildren("/app2", true);
        zk.create("/app2/child1", "test app2 child1".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);


        // 删除节点
        zk.getChildren("/app2", true);
        zk.delete("/app2/child1", -1);
        zk.exists("/app2", true);
        zk.delete("/app2", -1);

        Thread.sleep(Long.MAX_VALUE);


        zk.close();
        zk = null;
    }
}

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ZookeeperJavaApi {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        // 创建连接，调用Zookeeper构造函数，函数可传入参数为
        // 1. 连接字符串，定义在$ZOOKEEPER_HOME/conf/zoo.cfg中，由zookeeper服务器的hostname:port组成
        // 2. 连接时长，当连接超过该时长是抛出connectionTimeOut异常
        // 3. Watcher机制，用于监听真个Zookeeper集群的节点
        String connectStr = "172.15.0.2:2181,172.15.0.3:2181,172.15.0.4:2181";
        // ZooKeeper构造函数的参数：
        // 1. connectString, 字符串， 为znode的路径
        // 2. sessionTimeout, 整型，为连接的最大时长，超过该时长则判断与zookeeper集群连接超时
        // 3. watcher, zookeeper的Watcher, 设置watcher(监听)
        ZooKeeper zk = new ZooKeeper(connectStr, 3000, null);

        // 调用Zookeeper对象的create()接口创建节点，可传入参数为
        // 1. 节点路径，与命令行create命令一样，需创建父节点才能创建子节点
        // 2. 元数据字符串， 必须为byte[]格式
        // 3. ACL(控制访问列表)模式, 包含在org.apache.zookeeper.ZooDefs.Ids中，可选权限有：
        //    a) OPEN_ACL_UNSAFE 完全开放的ACL，任何连接的客户端都可以操作该znode的属性:
        //    b) CREATOR_ALL_ACL: 仅创建者有权限修改、读取数据
        //    c) READ_ACL_UNSAFE: 只读权限
        // 4. 节点类型，包含在org.apache.zookeeper.CreateMode中，可选类型有：
        //    a) PERSISTENT: 持久节点；
        //    b) PERSISTENT_SEQUENTIAL: 持久顺序节点;
        //    c) EPHEMERAL: 临时节点;
        //    d) EPHEMERAL_SEQUENTIAL: 临时顺序节点.
        zk.create("/app1", "testAppJavaApi".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("创建节点/app1成功.");

        // 调用Zookeeper对象的setData接口修改节点的元数据，可传入参数有：
        // 1. 节点路径；
        // 2. 元数据字符串;
        // 3. 数据版本号，-1默认为所有版本；
        Stat stat = zk.setData("/app1", "testAppJavaApi_setData".getBytes(), -1);
        System.out.println(stat.toString());

        // 调用Zookepper对象的getData()接口获取节点的状态(存储在Stat的对象中)和数据
        // getData接口可传入参数：
        // 1. 节点路径；
        // 2. null或者观察者对象Watcher，对节点的数据变化进行监听，一旦数据发生改变就会出发
        //    watcher的指定回调函数(process)
        // 3. Stat对象，用于存储节点的状态信息
        Stat stat1 = new Stat();
        byte[] res = zk.getData("/app1", null, stat1);
        System.out.println("创建时间: " + new Date(stat1.getCtime()));
        System.out.println("修改时间: " + new Date(stat1.getMtime()));
        System.out.println("ACL版本号: " + stat1.getAversion());
        System.out.println("节点修改版本号: " + stat1.getCversion());
        System.out.println("数据版本号: " + stat1.getVersion());
        System.out.println("创建zxid: " + stat1.getCzxid());
        System.out.println("修改zxid: " + stat1.getMzxid());
        System.out.println("节点修改zxid: " + stat1.getPzxid());
        System.out.println("临时节点所有者: " + stat1.getEphemeralOwner());
        System.out.println("子节点数量: " + stat1.getNumChildren());
        System.out.println("数据长度: " + stat1.getDataLength());
        System.out.println("元数据字符串" + new String(res));

        // 调用Zookeeper对象的exists判断节点是否存在，如存在则将节点的数据
        // 放入stat2中，否则stat2的数据为空
        // 1. 节点路径
        // 2. null或者观察者对象Watcher，对节点的数据变化进行监听，一旦数据发生改变就会出发
        Stat stat2 = zk.exists("/app2", null);
        System.out.println(stat2);


        // 调用Zookeeper对象的getChildren()接口查询子节点，可传入参数有：
        // 1. 父节点路径
        // 2. Watcher对象或者null
        List<String> children = zk.getChildren("/app1", null);
        System.out.println(children);


        // 调用Zookeeper对象的delete()接口删除节点，可传入参数有
        // 1. 待删除节点的路径；
        // 2. 节点版本号。如果设置为-1，代表不检查版本直接删除节点；否则
        //    将检查传入版本号和节点版本号是否相同，相同则删除节点，不相同则
        //    抛出`BadVersionException`异常.
        zk.delete("/app1", -1);

        zk.close();
        zk = null;
    }
}

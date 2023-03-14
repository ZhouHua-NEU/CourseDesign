import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

public class HDFSJavaApi {

    // 设置HDFS默认访问地址：
    public final static String HDFS_PATH = "hdfs://202.206.19.34:9000";
    // 配置对象声明
    private static Configuration configuration = null;
    // HDFS文件系统对象声明
    private static FileSystem fs = null;

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        // 开启HDFS链接
        setUp();


        // 1. 创建目录"/lessons/lesson2/zhl/javaApi"， 调用`fs.mkdirs()`
//        String dirName = "/lessons/lesson2/zhl/javaApi";
//        mkdir(dirName);

        // 2. 上传文件, 将test.txt文件上传到/lessons/lesson2/zhl/javaApi, 调用`fs.copyFromLocalFile()`
//        String localPath = "./test.txt";
//        String destPath = "/lessons/lesson2/zhl/javaApi/test.txt";
//        put(localPath, destPath);
        // 3. 下载文件， 将集群的/lessons/lesson2/zhl/javaApi/test.txt下载到本地， 调用`copyToLocalFile()`
//        String hdfsPath = "/lessons/lesson2/zhl/javaApi/test.txt";
//        String localPath = "./";
//        get(localPath, hdfsPath);

        // 4. 创建文件并写入数据, 在"/lessons/lesson2/zhl/javaApi/test1.txt", 并写入"Hello HDFS test1"
        //    调用接口`fs.create()`
//        String filePath = "/lessons/lesson2/zhl/javaApi/test1.txt";
//        String content = "Hello HDFS test1111\n";
//        create(filePath, content);


        // 5. 查看文件内容， 查看"/lessons/lesson2/zhl/javaApi/test1.txt", 调用`fs.open()`
//        String filePath = "/lessons/lesson2/zhl/javaApi/test1.txt";
//        read(filePath);


        // 6.对文件追加内容, 在"/lessons/lesson2/zhl/javaApi/test1.txt"文件后面追加"Hello Hadoop",
        //   调用`fs.append()`
//        String fileName = "/lessons/lesson2/zhl/javaApi/test1.txt";
//        String appendContent = "Hello Hadoop\n";
//        append(fileName, appendContent);


        // 7.删除文件， 删除"/lessons/lesson2/zhl/javaApi/test1.txt"文件， 调用`fs.deleteOnExit()`
//        String fileName = "/lessons/lesson2/zhl/javaApi/test1.txt";
//        delete(fileName);


        // 8. 遍历文件夹, 遍历"/lessons/lesson1/"的文件夹，调用`fs.listStatus()`
//        String filePath = "/lessons/lesson1/";
//        ls(filePath);

        // 9. 查看文件信息， 查看"/lessons/lesson2/zhl/javaApi/test.txt"的元数据信息， 调用`fs.getFileStatus()`
        String filePath = "/lessons/lesson2/zhl/javaApi/test.txt";
        getMetaData(filePath);


        // 关闭HDFS链接
        tearDown();
    }

    // 创建资源
    private static void setUp() throws URISyntaxException, IOException, InterruptedException {
        System.out.println("HDFSApp setUp");
        configuration = new Configuration(); // 创建配置对象
        fs = FileSystem.get(new URI(HDFS_PATH), configuration, "roo"); //文件系统对象
    }

    // 释放资源
    private static void tearDown() throws IOException {
        // 释放资源
        configuration = null;
        fs.close();
        System.out.println("HDFS app tearDown.");
    }

    // 创建文件夹
    private static void mkdir(String dirName) throws IOException {
        Path path = new Path(dirName);
        boolean isOK = fs.mkdirs(path);
        if (isOK){
            System.out.println(dirName + "创建成功");
        } else {
            System.out.println(dirName + "创建失败");
        }
    }

    // 上传文件
    private static void put(String localPath, String destPath) throws IOException {
        Path local = new Path(localPath); //1.设置本地文件夹
        Path dest = new Path(destPath); //2.设置HDFS文件夹
        fs.copyFromLocalFile(local, dest);
        System.out.println("文件上传成功");
    }

    // 下载文件
    private static void get(String localPath, String hdfsPath) throws IOException {
        fs.copyToLocalFile(new Path(hdfsPath), new Path(localPath)); //设置远程和本地文件
        System.out.println("文件下载成功");
    }

    // 写入数据
    private static void create(String fileName, String content) throws IOException {
        Path path = new Path(fileName); //1. 新建目标文件路径
        FSDataOutputStream outputStream = fs.create(path, (short) 2);//2. 创建文件并获取文件输出流对象, 并设置备份数为2
        outputStream.write(content.getBytes(StandardCharsets.UTF_8));//3. 写入数据
        outputStream.close();//4. 关闭文件输出流
        System.out.printf("文件创建和写入完成");
    }

    // 查看文件
    private static void read(String filePath) throws IOException {
        Path path = new Path(filePath); //1. 设置目标文件路径
        FSDataInputStream inputStream = fs.open(path);//2. 打开文件到字节流中
        IOUtils.copyBytes(inputStream, System.out, 4096, false);//3. 将文件内容拷贝到
        IOUtils.closeStream(inputStream);
    }

    // 追加内容
    private static void append(String fileName, String appendContent) throws IOException {
        Path path = new Path(fileName); //1. 设置目标文件路径
        FSDataOutputStream outputStream = fs.append(path);//2. 创建文件并获取文件输出流对象
        outputStream.write(appendContent.getBytes(StandardCharsets.UTF_8)); //3. 写入数据
        outputStream.close();//4. 关闭文件输出流
        System.out.println("内容追加成功");
    }

    // 删除文件
    private static void delete(String fileName) throws IOException {
        Path path = new Path(fileName); //1. 设置目标文件路径
        boolean isOk = fs.deleteOnExit(path); // 2. 删除目标文件
        if (isOk){ //3. 打印信息
            System.out.println(fileName + "文件删除成功");
        } else {
            System.out.println(fileName + "文件删除失败");
        }
    }

    // 遍历文件夹
    private static void ls(String dirName) throws IOException {
        Path path = new Path(dirName); // 1.设置目标文件夹
        FileStatus[] fileStatuses = fs.listStatus(path); //2. 获取所有文件（夹）的元数据
        for (FileStatus fileStatus : fileStatuses) { //3. 遍历获取文件（夹）的路径
            Path path1 = fileStatus.getPath();
            System.out.println(path1);
        }
    }

    // 获取文件元数据信息
    private static void getMetaData(String dirName) throws IOException {
        Path path = new Path(dirName);
        FileStatus fileStatus = fs.getFileStatus(path);
        if (fileStatus.isDirectory()){  // 根据文件类型输出
            System.out.println("这是一个文件夹");
        } else {
            System.out.println("这是一个文件");
        }
        // 输出文件元数据信息
        System.out.println("文件路径：" + fileStatus.getPath());
        System.out.println("文件修改时间: " + new Timestamp(fileStatus.getModificationTime()));
        System.out.println("上次访问时间: " + new Timestamp(fileStatus.getAccessTime()));
        System.out.println("文件长度：" + fileStatus.getLen());
        System.out.println("文件备份数: " + fileStatus.getReplication());
        System.out.println("文件块大小: " + fileStatus.getBlockSize());
        System.out.println("文件所有者：" + fileStatus.getOwner());
        System.out.println("文件所在分组：" + fileStatus.getGroup());
        System.out.println("文件的权限：" + fileStatus.getPermission().toString());
    }
}

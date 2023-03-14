import MRClasses.MyMapClass;
import MRClasses.MyPartitioner;
import MRClasses.MyReduceClass;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;



public class WordCount  {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        // 问题. HADOOP_HOME未找到，常出现在windows操作系统.
        // 方法： 1）需要在集群中(202.206.19.34://data/hadoop/hadoop-3.1.3.zip下载到电脑；2）解压hadoop-3.1.3, 并且将
        //       hadoop-3.1.3的目录路径设置为环境变量HADOOP_HOME; 3）将hadoop-3.1.3/bin/hadoop.dll复制到C://Windows/System32
        //       目录中.
        
        // 1. 配置MapReduce程序环境
        Configuration conf = new Configuration(true);
        conf.set("fs.default.name", "hdfs://202.206.19.34:9000"); // 配置hdfs的默认端口号
        System.setProperty("HADOOP_USER_NAME", "root"); // 将本机的用户名设置成root，否则会报权限错误


        // 2. 配置一个job实例
        Job job = Job.getInstance(conf, "wordCount_teacher"); // 设置任务名称
        job.setJarByClass(WordCount.class);

        // 3. 设置输入路径
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("/lessons/lesson2/zhl/wordCount/input/helloHadoop"));

        // 4. 设置输出路径
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path("/lessons/lesson2/zhl/wordCount/output"));

        // 5. 将定义的Mapper类传给job, 并设置Mapper的输出
        job.setMapperClass(MyMapClass.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        // 6. 将定义好的Reducer类传给job
        job.setReducerClass(MyReduceClass.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        // 设置分区的算法
        job.setPartitionerClass(MyPartitioner.class);
        // 设置reduce任务的数量=分区数量=输出文件数量
        job.setNumReduceTasks(4);

        int r = job.waitForCompletion(true) ? 0 : 1;
        System.out.println(r);
    }

}

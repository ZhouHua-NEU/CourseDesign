import Case1.Mapper.ReadHDFSStudentMapper;
import Case1.Reducer.WriteHBaseStudentReducer;
import Case2.MyMapper.MyMapper;
import Case2.MyReducer.MyReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

import java.io.IOException;

public class HBaseMRApi{

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        // 1. 配置HBase的Zookeeper位置
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","cluster-master:2181,cluster-slave1:2181,cluster-slave2:2181");

        // 2. 配置Job实例
        Job job = Job.getInstance(conf, "hbase_mr_job");
        job.setJarByClass(HBaseMRApi.class);

        // 3. 设置输入文件路径(如果使用TableMapper, 此处应该是为Job初始化Mapper)
        if (args[0].equals("1")){
            // 从HDFS中载入数据
            Path inputPath = new Path("hdfs://cluster-master:9000/lessons/lesson3/zhl/data/students_info.csv");
            job.setInputFormatClass(TextInputFormat.class);
            TextInputFormat.addInputPath(job, inputPath);
            // 4. 设置Mapper类
            job.setMapperClass(ReadHDFSStudentMapper.class);
            job.setMapOutputKeyClass(ImmutableBytesWritable.class);
            job.setMapOutputValueClass(Put.class);
            TableMapReduceUtil.initTableReducerJob("students_info", WriteHBaseStudentReducer.class, job);
        } else {
            // 从HBase中载入数据
            TableMapReduceUtil.initTableMapperJob("students_info", new Scan(), MyMapper.class, ImmutableBytesWritable.class, Put.class, job);
            TableMapReduceUtil.initTableReducerJob("students_info1", MyReducer.class, job);
        }

        // 5. 设置Reducer类
        job.setOutputKeyClass(Put.class);
        job.setOutputValueClass(NullWritable.class);
        job.setNumReduceTasks(1);

        // 6. 等待程序结束
        System.out.println(job.waitForCompletion(true) ? 0: 1);
    }
}

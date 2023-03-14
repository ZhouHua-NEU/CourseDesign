package Case1.Mapper;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * 读取HDFS中的文件students_info.txt的数据
 */
public class ReadHDFSStudentMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {

    /**
     * 接收HDFS中的数据，并处理成能存入HBase的样式
     * @param key map阶段输入key，默认为每一行下标
     * @param value map阶段输入value, 为一行的内容
     * @param context 上下文
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 1. 将每一行读取的数据转换成字符串
        //    将一行数据根据","（英文逗号）分割成字符串数组
        String lineValue = value.toString();
        String[] values = lineValue.split(",");

        // 2. 依此从字符串数组中获取数据
        String rowKey = values[0];
        String name = values[1];
        String city = values[2];
        String phone = values[3];
        String tel = values[4];
        String address = values[5];
        System.out.println(rowKey + "-" + name + "-" + city + "-" + phone + "-" + tel + "-" + address);

        // 3. 构造rowkey, rowkey为ImmutableBytesWritable类型
        ImmutableBytesWritable rowKeyWritable = new ImmutableBytesWritable(
                Bytes.toBytes(rowKey)
        );

        // 4. 构造Put对象，可以添加一行的数据
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn("personal_info".getBytes(), "name".getBytes(), name.getBytes());
        put.addColumn("personal_info".getBytes(), "city".getBytes(), city.getBytes());
        put.addColumn("personal_info".getBytes(), "phone".getBytes(), phone.getBytes());
        put.addColumn("office_info".getBytes(), "tel".getBytes(), tel.getBytes());
        put.addColumn("office_info".getBytes(), "address".getBytes(), address.getBytes());

        // 5. 将数据推送到reduce阶段
        context.write(rowKeyWritable, put);
    }
}

package Case1.Reducer;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.NullWritable;

import java.io.IOException;


/**
 * 将Put操作应用到数据表上
 */
public class WriteHBaseStudentReducer extends
        TableReducer<ImmutableBytesWritable, Put, NullWritable> {

    /**
     * @param key 输入的rowkey
     * @param values 输入的Put对象，为一个可迭代对象
     * @param context 上下文对象
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(ImmutableBytesWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {


        for (Put put : values){
            context.write(NullWritable.get(), put);
        }
    }
}

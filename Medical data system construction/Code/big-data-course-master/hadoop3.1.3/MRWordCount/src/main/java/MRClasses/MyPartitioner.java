package MRClasses;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;

public class MyPartitioner extends Partitioner<Text, LongWritable> {

    @Override
    public int getPartition(Text key, LongWritable value, int i) {
        System.out.println(key.toString());
        System.out.println(value.get());
        String name = key.toString();
        if ("bye".equals(name)) {
            return 0 % i;
        } else if ("hadoop".equals(name)){
            return 1 % i;
        } else if ("hello".equals(name)) {
            return 2 % i;
        } else {
            return 3 % i;
        }
    }
}

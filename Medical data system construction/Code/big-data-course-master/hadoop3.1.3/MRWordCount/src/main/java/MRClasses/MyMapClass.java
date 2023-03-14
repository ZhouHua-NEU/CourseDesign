package MRClasses;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MyMapClass extends Mapper<LongWritable, Text, Text, LongWritable> {

    // Map阶段的输入为<Long, String>
    // 输出为<String, Long>

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // key指每一行的编号
        // value指每一行的内容
        // context为上下文， 可以理解为一个全局变量， 能将Map阶段的结果推送到下一阶段

        // 1. 将value从Text类型转换为String类型
        String line = value.toString();
        // 2. 将line(句子)切分为一个个的word(词语)
        String[] words = line.split(" ");
        // 3. 遍历输出
        for (String word : words) {
            context.write(new Text(word), new LongWritable(1));
        }

    }
}

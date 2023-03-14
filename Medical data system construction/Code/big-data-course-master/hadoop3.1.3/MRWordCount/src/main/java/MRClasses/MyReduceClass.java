package MRClasses;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyReduceClass extends Reducer<Text, LongWritable, Text, LongWritable> {

    // Reduce阶段的输入为<text, Long>
    // Reduce阶段的输出为<text, Long>

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        // key表示一个短语(word)
        // values表示该短语是否出现，出现一次就用1表示
        // 这样该短语出现的次数=所有1相加的大小

        Long sum = 0L;
        for (LongWritable value : values){
            sum += value.get();
        }
        // 输出
        context.write(key, new LongWritable(sum));

    }
}

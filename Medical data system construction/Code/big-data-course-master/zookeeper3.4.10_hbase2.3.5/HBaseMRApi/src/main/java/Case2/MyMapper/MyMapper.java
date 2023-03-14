package Case2.MyMapper;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;

import java.io.IOException;

public class MyMapper extends TableMapper<ImmutableBytesWritable, Put> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
        Cell[] cells = value.rawCells();
        Put put = new Put(key.copyBytes());
        for (Cell cell : cells) {
            String family = new String(CellUtil.cloneFamily(cell));
            String qualifier = new String(CellUtil.cloneQualifier(cell));
            String val = new String(CellUtil.cloneValue(cell));
            System.out.println(family + "-" + qualifier + "-" + val);
            put.addColumn(family.getBytes(), qualifier.getBytes(), val.getBytes());
        }
        context.write(key, put);
    }
}

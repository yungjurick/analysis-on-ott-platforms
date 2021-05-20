import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class NetflixOriginalCleanReducer extends Reducer<Text, IntWritable, Text, NullWritable> {

  @Override

  public void reduce(Text key, Iterable<IntWritable> values, Context context)
    throws IOException, InterruptedException {

    context.write(key, NullWritable.get());
  }
}
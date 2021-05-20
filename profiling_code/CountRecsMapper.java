import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountRecsMapper extends Mapper<LongWritable, Text, Text, IntWritable> {  
  @Override
  
  public void map(LongWritable key, Text value, Context context)
    throws IOException, InterruptedException {

      String line = value.toString();
      String[] tokenArray = line.split(",");

      if (!tokenArray[0].equals("")) {
        context.write(new Text("line"), new IntWritable(1));
      }
    }
}
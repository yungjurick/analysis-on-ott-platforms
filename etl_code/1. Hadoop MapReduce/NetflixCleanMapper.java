import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NetflixCleanMapper extends Mapper<LongWritable, Text, Text, IntWritable> {  
  @Override
  
  public void map(LongWritable key, Text value, Context context)
    throws IOException, InterruptedException {

    String line = value.toString();
    List<String> tokenArray = Arrays.asList(
      line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)")
    );

    // Filter those records that are included in Netflix
    if (tokenArray.size() == 12 && tokenArray.get(1).equals("Movie")) {
      
      List<Integer> omitColumnIndexes = Arrays.asList(0, 4, 6, 10, 11);
      List<String> filteredTokens = new ArrayList<String>();

      for (int index = 0; index < tokenArray.size(); index++) {
        if (!omitColumnIndexes.contains(index)) {
          // Clean duration format
          if (index == 9) {
            List<String> splitDuration = Arrays.asList(tokenArray
              .get(index)
              .split(" ")
            );
            String durationInMinutes = splitDuration.get(0);
            filteredTokens.add(durationInMinutes);
          } else {
            filteredTokens.add(tokenArray.get(index)); 
          }
        }
      }

      String cleanedRec = String.join(",", filteredTokens);

      context.write(new Text(cleanedRec), new IntWritable(1));
    }
  }
}
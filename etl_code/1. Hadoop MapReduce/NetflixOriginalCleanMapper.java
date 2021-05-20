import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NetflixOriginalCleanMapper extends Mapper<LongWritable, Text, Text, IntWritable> {  
  @Override
  
  public void map(LongWritable key, Text value, Context context)
    throws IOException, InterruptedException {

    String line = value.toString();
    // 
    List<String> tokenArray = Arrays.asList(
      line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1)
    );

    if (!tokenArray.get(1).equals("Title") && tokenArray.size() == 48) {
      
      if (!tokenArray.get(1).equals("") && !tokenArray.get(21).equals(""))
      {
        List<Integer> includeColumnIndexes = Arrays.asList(0, 1, 21);
        List<String> filteredTokens = new ArrayList<String>();

        for (int index = 0; index < tokenArray.size(); index++) {
          if (includeColumnIndexes.contains(index)) {
            if (index == 21) {
              // Extract Released Year From Released Date
              String releasedDate = tokenArray.get(index);
              String year = "20" + releasedDate.substring(releasedDate.length() - 2);
  
              filteredTokens.add(year);
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
}
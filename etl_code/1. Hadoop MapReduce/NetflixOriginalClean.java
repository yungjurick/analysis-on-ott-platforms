import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NetflixOriginalClean {

  public static void main(String[] args) throws Exception {
    
    if (args.length != 2) {
      System.err.println("Usage: NetflixClean <input path> <output path>");
      System.exit(-1);
    }
    Job job = new Job();
    job.setJarByClass(NetflixOriginalClean.class);
    job.setJobName("Netflix Original Movies Dataset Cleaning Job");

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    job.setMapperClass(NetflixOriginalCleanMapper.class);
    job.setReducerClass(NetflixOriginalCleanReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
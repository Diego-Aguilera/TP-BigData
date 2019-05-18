import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TwitterJob {
    public static void main(String[] args){
        try {
            Configuration conf = new Configuration();
            conf.set("mapred.textoutputformat.separator", ",");
            Job job = Job.getInstance(conf, "TwitterJob");
            job.setJarByClass(TwitterJob.class);
            job.setMapperClass(TwitterMapper.class);
            job.setCombinerClass(TwitterReducer.class);
            job.setReducerClass(TwitterReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(FloatWritable.class);

            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

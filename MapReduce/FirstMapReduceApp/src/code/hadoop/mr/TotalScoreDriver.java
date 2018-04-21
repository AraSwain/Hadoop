package code.hadoop.mr;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Driver class to find total score by a player
 * 
 * @author aravind
 *
 */
public class TotalScoreDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

	// Create a job object
	Job myJob = new Job();

	// Set Job properties
	// myJob.setJarByClass(cls);
	myJob.setJobName("PlayerTotalScore");
	myJob.setJarByClass(TotalScoreDriver.class);

	myJob.setMapperClass(TotalScoreMapper.class);
	myJob.setReducerClass(TotalScoreReducer.class);

	myJob.setMapOutputKeyClass(Text.class);
	myJob.setMapOutputValueClass(IntWritable.class);

	myJob.setOutputKeyClass(Text.class);
	myJob.setOutputKeyClass(IntWritable.class);

	myJob.setInputFormatClass(TextInputFormat.class);
	myJob.setOutputFormatClass(TextOutputFormat.class);

	FileInputFormat.addInputPath(myJob, new Path(args[0]));
	FileOutputFormat.setOutputPath(myJob, new Path(args[1]));

	// Run the Job
	myJob.waitForCompletion(true);
    }
}

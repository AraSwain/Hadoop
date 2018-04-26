package code.hadoop.mr.gop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Driver class to find total score by a player
 * This is with more configuration parameters
 * 
 * @author Aravind
 *
 */
public class TotalScoreDriverGOP  extends Configured implements Tool{
    public static void main(String[] args) throws Exception {
	ToolRunner.run(new Configuration(), new TotalScoreDriverGOP(), args);
    }

    @Override
    public int run(String[] args) throws Exception {

	// Create a job object
	Job myJob = new Job();

	// Set Job properties
	myJob.setJobName("GOP_Player_TotalScore");
	myJob.setJarByClass(TotalScoreDriverGOP.class);

	myJob.setMapperClass(TotalScoreMapper.class);
	myJob.setReducerClass(TotalScoreReducer.class);

	myJob.setMapOutputKeyClass(Text.class);
	myJob.setMapOutputValueClass(IntWritable.class);

	myJob.setOutputKeyClass(Text.class);
	myJob.setOutputValueClass(IntWritable.class);

	myJob.setInputFormatClass(TextInputFormat.class);
	myJob.setOutputFormatClass(TextOutputFormat.class);

	FileInputFormat.addInputPath(myJob, new Path(args[0]));
	FileOutputFormat.setOutputPath(myJob, new Path(args[1]));
	
	//Delete the output directory, if already exists
	FileSystem dfs = FileSystem.get(new Configuration());
	dfs.deleteOnExit(new Path(args[1]));
	
	// Run the Job
	boolean status = myJob.waitForCompletion(true);
	return status ? 0 : 1;
    }
    
    
}

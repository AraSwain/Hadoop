package code.hadoop.mr.mapperjoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.ToolRunner;
import org.bigdata.mr.mapperjoin.MapJoinDriver;

/**
 * Driver class to find total score by a player
 * This is with more configuration parameters
 * 
 * @author Aravind
 *
 */
public class TotalScoreCountryDriver {
    public static void main(String[] args) throws Exception {
	int exitCode = ToolRunner.run(new Configuration(), new MapJoinDriver(), args);
	System.exit(exitCode);
    }
    
    public int run(String[] args) throws Exception  {
	if (args.length != 2) {
	    System.out.printf("Two parameters are required- <input dir> <output dir>n");
	    return -1;
	}
	
	// Create a job object
	Job myJob = new Job();

	// Set Job properties
	myJob.setJobName("PlayerTotalScore");
	myJob.setJarByClass(TotalScoreCountryDriver.class);

	myJob.setMapperClass(TotalScoreCountryMapper.class);

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
	
	// Set required number of Reducers
	myJob.setNumReduceTasks(0);

	// Run the Job
	boolean result = myJob.waitForCompletion(true);
	return result == true ? 1:0;
    }
}

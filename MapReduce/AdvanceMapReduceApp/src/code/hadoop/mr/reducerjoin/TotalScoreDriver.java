package code.hadoop.mr.reducerjoin;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Driver class to find total score by a player
 * This is with more configuration parameters
 * 
 * @author Aravind
 *
 */
public class TotalScoreDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

	// Create a job object
	Job myJob = new Job();

	// Set Job properties
	myJob.setJobName("PlayerTotalScore");
	myJob.setJarByClass(TotalScoreDriver.class);

	myJob.setReducerClass(PlayerScoreCountryJoinReducer.class);

	myJob.setMapOutputKeyClass(Text.class);
	myJob.setMapOutputValueClass(Text.class);

	myJob.setOutputKeyClass(Text.class);
	myJob.setOutputValueClass(Text.class);

	myJob.setInputFormatClass(TextInputFormat.class);
	myJob.setOutputFormatClass(TextOutputFormat.class);

	//FileInputFormat.addInputPath(myJob, new Path(args[0]));
	MultipleInputs.addInputPath(myJob, new Path(args[0]), TextInputFormat.class, PlayerTotalScoreMapper.class);
	MultipleInputs.addInputPath(myJob, new Path(args[1]), TextInputFormat.class, PlayerCountryMapper.class);
	FileOutputFormat.setOutputPath(myJob, new Path(args[2]));
	
	//Delete the output directory, if already exists
	FileSystem dfs = FileSystem.get(new Configuration());
	dfs.deleteOnExit(new Path(args[2]));
	
	// Set required number of Reducers
	myJob.setNumReduceTasks(1);

	// Run the Job
	myJob.waitForCompletion(true);
    }
}

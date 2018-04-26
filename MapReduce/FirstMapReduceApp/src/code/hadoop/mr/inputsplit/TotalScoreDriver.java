package code.hadoop.mr.inputsplit;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 * Driver class to find total score by a player
 * This is with NLineInputFormat
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
	myJob.setOutputValueClass(IntWritable.class);

	//myJob.setInputFormatClass(TextInputFormat.class);
	NLineInputFormat.setNumLinesPerSplit(myJob, 30);
	myJob.setOutputFormatClass(TextOutputFormat.class);

	FileInputFormat.addInputPath(myJob, new Path(args[0]));
	FileOutputFormat.setOutputPath(myJob, new Path(args[1]));
	
	//Delete the output directory, if already exists
	FileSystem dfs = FileSystem.get(new Configuration());
	dfs.deleteOnExit(new Path(args[1]));
	
	// Set required number of Reducers
	myJob.setNumReduceTasks(4);

	// Run the Job
	myJob.waitForCompletion(true);
    }
}

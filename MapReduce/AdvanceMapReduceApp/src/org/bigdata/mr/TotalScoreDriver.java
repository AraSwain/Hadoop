package org.bigdata.mr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class TotalScoreDriver {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		// Create a job obj
		
		Job myJob = new Job();
		
		// Set Job Properties
		
		myJob.setJobName("Player&TotalScore"); //optional - job identified by this name
		myJob.setJarByClass(TotalScoreDriver.class); //mandatory to set
		
		//myJob.setMapperClass(TotalScoreMapper.class);
		myJob.setReducerClass(TotalScoreReducer.class);
		//myJob.setPartitionerClass(MyPartitioner.class);
		//myJob.setCombinerClass(TotalScoreReducer.class);
		
		//mapper class
		
		myJob.setMapOutputKeyClass(Text.class); //player name which represent by text
		myJob.setMapOutputValueClass(Text.class); //identify score which is present by int 
		
		//reducer class
		
		myJob.setOutputKeyClass(Text.class);
		myJob.setOutputValueClass(Text.class);
		
	    myJob.setInputFormatClass(TextInputFormat.class); 
		//myJob.setInputFormatClass(NLineInputFormat.class); 
		//NLineInputFormat.setNumLinesPerSplit(myJob, 30);
		myJob.setOutputFormatClass(TextOutputFormat.class);
		//myJob.setOutputFormatClass(SequenceFileOutputFormat.class);//only hadoop ecosystem understand it other than not possible
		
		MultipleInputs.addInputPath(myJob, new Path(args[0]), TextInputFormat.class, TotalScoreMapper.class);
		MultipleInputs.addInputPath(myJob, new Path(args[1]), TextInputFormat.class, CountryMapper.class);
		FileOutputFormat.setOutputPath(myJob, new Path(args[2]));
		
		//FileInputFormat.addInputPath(myJob, new Path(args[0]));
		//FileOutputFormat.setOutputPath(myJob, new Path(args[1]));
		
		//Remove directory if exists
		
		FileSystem dfs = FileSystem.get(new Configuration()); // contact with hdfs-site.xml configurations file
		//dfs.delete(new Path(args[1]),true); //delete the existed directory
		dfs.delete(new Path(args[2]),true);
		
		//set desired # reducers
		
		myJob.setNumReduceTasks(1);
		
		//run the job
		
		myJob.waitForCompletion(true);

	}

}

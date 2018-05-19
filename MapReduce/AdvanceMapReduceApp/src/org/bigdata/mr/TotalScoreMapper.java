package org.bigdata.mr;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TotalScoreMapper extends Mapper <LongWritable,Text,Text,Text> {
@Override
protected void map(LongWritable key, Text value,Context context)
		throws IOException, InterruptedException {
	
	String lineContent = value.toString();
	String [] splits = lineContent.split("\t");
	Text keyIntd = new Text();
	keyIntd.set(splits[0]);
	context.write(keyIntd, new Text(splits[1] + "\tCricketScores"));
	
}
}

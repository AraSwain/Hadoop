package org.bigdata.mr;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountryMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	// TODO Auto-generated method stub
	String lineContent = value.toString();
	String[] splits = lineContent.split(",");
	Text keyIntd = new Text();
	keyIntd.set(splits[0]);
	context.write(keyIntd, new Text(splits[1] + "\tPlayerCountry"));
    }
}

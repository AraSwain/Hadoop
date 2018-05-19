package org.bigdata.mr;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TotalScoreReducer extends Reducer<Text, Text, Text, Text> {

    // Text, IntWritable -> Input Para - Player, Individual Score
    // Text, IntWritable -> Out Para - Base on transformation
    IntWritable totalScore = new IntWritable();

    @Override
    protected void reduce(Text keyIntd, Iterable<Text> valIntdList, Context context)
	    throws IOException, InterruptedException {

	String country = null;
	int sum = 0;
	for (Text t : valIntdList) {
	    String[] tArray = t.toString().split("\t");

	    if ((tArray[1]).equals("CricketScores")) {
		sum = sum + Integer.parseInt(tArray[0]);
	    } else if ((tArray[1]).equals("PlayerCountry")) {
		country = tArray[0];
	    }
	}

	totalScore.set(sum);
	context.write(keyIntd, new Text(sum + "\t" + country));

    }

}
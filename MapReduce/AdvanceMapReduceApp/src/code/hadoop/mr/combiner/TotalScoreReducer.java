package code.hadoop.mr.combiner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * This is the reducer class to find the total score by a player
 * 
 * @author Aravind
 *
 */
public class TotalScoreReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    IntWritable totalScore = new IntWritable();

    @Override
    protected void reduce(Text keyIntd, Iterable<IntWritable> valIntdList, Context context)
	    throws IOException, InterruptedException {

	int sum = 0;
	for (IntWritable val : valIntdList) {
	    sum = sum + val.get();
	}

	totalScore.set(sum);
	context.write(keyIntd, totalScore);

	// context.write(keyIntd, new IntWritable(sum));
    }
}

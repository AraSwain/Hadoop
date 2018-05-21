package code.hadoop.mr.sample;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * This is the mapper class for the job to find the total score by a player.
 * 
 * @author Aravind
 *
 */
public class TotalScoreMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

	String lineContent = value.toString();
	String[] splits = lineContent.split("\t");
	Text keyIntd = new Text();
	IntWritable valueIntd = new IntWritable();

	keyIntd.set(splits[0]);
	valueIntd.set(Integer.parseInt(splits[1]));

	context.write(keyIntd, valueIntd);
    }
}

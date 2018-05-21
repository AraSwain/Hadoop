package code.hadoop.mr.assignment;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * This is the mapper class for the job to find the total score by a player.
 * 
 * @author aravind
 *
 */
public class TotalScoreMapper extends Mapper<Text, Text, Text, IntWritable> {

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {

	String lineContent = value.toString();
	String[] splits = lineContent.split("\t");
	Text keyIntd = new Text();
	IntWritable valueIntd = new IntWritable();

	keyIntd.set(key);
	valueIntd.set(Integer.parseInt(splits[0]));

	context.write(keyIntd, valueIntd);
    }
}

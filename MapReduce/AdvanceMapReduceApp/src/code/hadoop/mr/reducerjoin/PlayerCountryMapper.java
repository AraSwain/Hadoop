package code.hadoop.mr.reducerjoin;

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
public class PlayerCountryMapper extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

	String lineContent = value.toString();
	String[] splits = lineContent.split(",");
	Text keyIntd = new Text();

	keyIntd.set(splits[0]);

	context.write(keyIntd, new Text(splits[1] + "\tflagB"));
    }
}

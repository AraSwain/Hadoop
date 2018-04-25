package code.hadoop.mr.reducerjoin;

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
public class PlayerScoreCountryJoinReducer extends Reducer<Text, Text, Text, Text> {

    IntWritable totalScore = new IntWritable();

    @Override
    protected void reduce(Text keyIntd, Iterable<Text> valIntdList, Context context)
	    throws IOException, InterruptedException {

	
	int sum = 0;
	String country = null;
	for(Text val:valIntdList) {
	    
	    
	    String[] valArray = val.toString().split("\t");
	    if(valArray[1].equals("flag_A")) {
		sum = sum + Integer.parseInt(valArray[0]);
	    }
	    else if(valArray[1].equals("flag_B")) {
		country = valArray[0];
	    }
	}
	
	totalScore.set(sum);
	context.write(keyIntd, new Text(country + "\t" + sum));

    }
}

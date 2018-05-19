package code.hadoop.mr.mapperjoin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
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
public class TotalScoreCountryMapper extends Mapper<LongWritable, Text, Text, Text> {
    
    public static final String CACHE_FILE_NAME = "PlayerCountry.txt";
    private Map<String, String> countryMap = new HashMap<>();

    @Override
    protected void setup(Context context)
            throws IOException, InterruptedException {
        Path[] cachedLocalFilesPath = DistributedCache.getLocalCacheFiles(context.getConfiguration());
        for(Path path:cachedLocalFilesPath) {
            if(path.getName().contains(CACHE_FILE_NAME)) {
        	loadCountryHashMap(path, context);
            }
        }
    }
    
    public void loadCountryHashMap(Path path, Context context) throws IOException {
	String line = null;
	BufferedReader bReader = new BufferedReader(new FileReader(path.toString()));
	while((line = bReader.readLine()) != null) {
	    String[] countryArr = line.split(",");
	    countryMap.put(countryArr[0].trim(), countryArr[1].trim());
	}
    }
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

	Text keyOutput = new Text();
	Text valueOutput = new Text();
	
	String lineContent = value.toString();
	String[] splits = lineContent.split("\t");
	
	String country = countryMap.get(splits[0].trim());
	

	keyOutput.set(splits[0]);
	valueOutput.set(splits[1] + "\t" + country);

	context.write(keyOutput, valueOutput);
    }
}

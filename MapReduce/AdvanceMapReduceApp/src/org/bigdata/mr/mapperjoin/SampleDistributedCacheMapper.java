package org.bigdata.mr.mapperjoin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class SampleDistributedCacheMapper extends Mapper<LongWritable, Text, Text, Text> {

    private static HashMap<String, String> departmentMap = new HashMap<String, String>();
    private BufferedReader brReader;
    private String strDeptName = "";
    private Text txtMapOutputKey = new Text("");
    private Text txtMapOutputValue = new Text("");

    // defining the a user counter as "MYCOUNTER" to get the details about the data
    // and exception in the user format— in other words, this is for data profiling
    enum MYCOUNTER {
	RECORD_COUNT, FILE_EXISTS, FILE_NOT_FOUND, SOME_OTHER_ERROR
    }

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
	Path[] cacheFilesLocal = DistributedCache.getLocalCacheFiles(context.getConfiguration());
	// for loop – to check no.of files cached and also increment the FILE_EXISTS
	// counter value
	for (Path eachPath : cacheFilesLocal) {
	    if (eachPath.getName().toString().trim().equals("departments_txt")) {
		context.getCounter(MYCOUNTER.FILE_EXISTS).increment(1);
		// User defined function to load the cached file data to the HashMap
		loadDepartmentsHashMap(eachPath, context);
	    }
	}

    }

    private void loadDepartmentsHashMap(Path filePath, Context context) throws IOException {
	String strLineRead = "";

	try {
	    brReader = new BufferedReader(new FileReader(filePath.toString()));

	    while ((strLineRead = brReader.readLine()) != null) {
		String deptFieldArray[] = strLineRead.split("\t");
		// Store all the fields available in department file into the defined HashMap
		departmentMap.put(deptFieldArray[0].trim(), deptFieldArray[1].trim());
	    }
	}
	// Increment the FILE_NOT_FOUND user defined counter incase if we get
	// FileNotFoundException
	catch (FileNotFoundException e) {
	    e.printStackTrace();
	    context.getCounter(MYCOUNTER.FILE_NOT_FOUND).increment(1);
	}
	// Increment the SOME_OTHER_ERROR user defined counter incase if we get any
	// other exception
	catch (IOException e) {
	    context.getCounter(MYCOUNTER.SOME_OTHER_ERROR).increment(1);
	    e.printStackTrace();
	} finally {
	    if (brReader != null) {
		brReader.close();
	    }
	}
    }

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	// Increment the RECORD_COUNT user defined counter based for each record in the

	context.getCounter(MYCOUNTER.RECORD_COUNT).increment(1);
	// if the records has field values then only its going to execute the logic
	if (value.toString().length() > 0) {
	    // split the field values based on the tab delimitor and store them in the array
	    String arrEmpAttributes[] = value.toString().split("\t");

	    try {
		// pull the department name from the cached hashmap by passing the department id
		// which is pulled from the input file(arrEmpAttributes[3])
		strDeptName = (String) departmentMap.get(arrEmpAttributes[3].toString());
	    } finally {
		// check if the department name – if it has null or then assign "NOT-FOUND" else
		strDeptName = ((strDeptName.equals(null) || strDeptName.equals("")) ? "NOT-FOUND" : strDeptName);
	    }
	    // load the EMP ID as Key to the Text object, which will be writiing to the
	    // output
	    txtMapOutputKey.set(arrEmpAttributes[0].toString());
	    // load the EMPID + EMPNAME + SALARY + DEPTNAME as Value to the Text object,
	    // which will be writiing to the output
	    txtMapOutputValue.set(arrEmpAttributes[0].toString() + "t" + arrEmpAttributes[1].toString() + "t"
		    + arrEmpAttributes[2].toString() + "t" + arrEmpAttributes[3].toString() + "t" + strDeptName);

	}
	// storing the Text objects in the context object which will be used to write
	// output
	context.write(txtMapOutputKey, txtMapOutputValue);
	strDeptName = "";
    }
}
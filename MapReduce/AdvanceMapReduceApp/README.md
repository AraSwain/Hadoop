## Reducer Side Join

**How to run this**
1. Create a jar from this source. (I have uploaded the jar to __resources__ folder named __reducerjoin.jar__)
2. Upload the files(CricketScores.txt, PlayerCountry.txt) to two different folders in hdfs.
	* Upload CricketScores.txt to __/hdfs/scores/__
	* Upload PlayerCountry.txt to __/hdfs/country/__
3. Run the app with the below command
```sh
hadoop jar reducerjoin.jar /hdfs/scores/ /hdfs/country/ /hdfs/reducerjoin_output/
```


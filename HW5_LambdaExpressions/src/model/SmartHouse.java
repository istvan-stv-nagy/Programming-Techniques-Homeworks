package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class SmartHouse {
	
	private static final String FILENAME = "activities.txt";
	private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private List<MonitoredData> monitoredData;
	
	private BufferedWriter bw = null;
	
	public SmartHouse() {
		monitoredData = new ArrayList<MonitoredData>();
		try {
			bw = new BufferedWriter(new FileWriter("output.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public void read() {
		try (Stream<String> stream = Files.lines(Paths.get(FILENAME))) {

			monitoredData = stream.map(d -> new MonitoredData(DateTime.parse(d.split("\t\t")[0],DateTimeFormat.forPattern(PATTERN))
					,DateTime.parse(d.split("\t\t")[1],DateTimeFormat.forPattern(PATTERN))
					,d.split("\t\t")[2].trim())).collect(Collectors.toList());
			MonitoredData.firstDay = monitoredData.get(0).getStartTime();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			
	public void countDistinctDays() {
		long count = monitoredData.parallelStream()
		.distinct()
		.count();
		writeToFile("1. Number of distinct days:" + count);
	}
	
	public void writeDistinctActions() {
		writeToFile("\n");
		writeToFile("2. Number of occurences of each action:");
		Map<String,Long> map =
				monitoredData.parallelStream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
		
		map.entrySet().stream().forEach(e -> writeToFile(e.getValue() + "\t" + e.getKey()));
	}
	
	public void activityCount() {
		writeToFile("\n");
		writeToFile("3. Activity count for each day of the log:");
		Map<Integer,Map<String,Long>> map =
				monitoredData.stream()
		        .collect(Collectors.groupingBy(MonitoredData::getDay, Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting())));
		map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(e -> writeToFile(e.getKey() + "\t" + e.getValue()));
	}
	
	public void totalTime() {
		writeToFile("\n");
		writeToFile("4. Total duration of each activity:");
		Map<String, Long> map =
				monitoredData.parallelStream()
				.collect
				(Collectors.groupingBy(MonitoredData::getActivity, Collectors.summingLong(MonitoredData::getDuration)));
		map.entrySet().stream().filter(e -> e.getValue() >= 10*3600).forEach(e -> writeToFile(e.getKey() + "=" + e.getValue()/3600 + "hr " + (e.getValue()/60)%60 + "min " + e.getValue()%60 +"sec"));
	}
	
	public void percentage() {
		writeToFile("\n");
		writeToFile("5. % of durations less or equal to 5 minutes for each activity:");
		Map<String,Double> ls =
				monitoredData.stream()
				.collect
				(Collectors.groupingBy(MonitoredData::getActivity, Collectors.mapping(MonitoredData::getDuration, Collectors.averagingDouble(d -> {
					return d<300 ? 1 : 0;
				}))));
		
		ls.entrySet().stream().forEach(e -> writeToFile(e.getKey() + " : " + (int)(e.getValue() * 100) + "%")); 
		
		writeToFile("\n");
		writeToFile("6. Activities having 90% of durations less than 5 minutes:");
		ls.entrySet().stream()
		.filter(map -> map.getValue() >= 0.9f)
		.map(map -> map.getKey())
		.forEach(e -> writeToFile(e + " "));
	}
	
	public void closeFile() {
		try {
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeToFile(String msg) {
		try {
			bw.write(msg);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

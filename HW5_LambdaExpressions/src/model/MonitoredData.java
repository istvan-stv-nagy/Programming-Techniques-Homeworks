package model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class MonitoredData {
	
	public static DateTime firstDay;
	
	private DateTime startTime;
	private DateTime endTime;
	private String activity;
	
	public MonitoredData(DateTime startTime, DateTime endTime, String activity) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.activity = activity;
	}

	public DateTime getStartTime() {
		return startTime;
	}
	
	public Integer getDay() {
		return startTime.minus(firstDay.getMillis()).getDayOfMonth();
	}
	
	public Long getDuration() {
		return endTime.minus(startTime.getMillis()).getMillis()/1000;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public String getActivity() {
		return activity;
	}

	@Override
	public int hashCode() {
		DateTimeFormatter dayFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
		String format = dayFormat.print(startTime);
		final int prime = 31;
		int result = 1;
		result = prime * result + ((startTime == null) ? 0 : format.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		DateTimeFormatter dayFormat = DateTimeFormat.forPattern("yyyy-MM-dd");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		MonitoredData other = (MonitoredData) obj;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!(dayFormat.print(startTime).equals(dayFormat.print(other.getStartTime())))) {
			System.out.println("problem");
			return false;
		}
		return true;
	}
}



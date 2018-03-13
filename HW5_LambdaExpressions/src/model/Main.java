package model;

public class Main {
	public static void main(String args[]) {
		SmartHouse s = new SmartHouse();
		s.read();
		s.countDistinctDays();
		s.writeDistinctActions();
		s.activityCount();
		s.totalTime();
		s.percentage();
		s.closeFile();
	}
}

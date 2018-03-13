package model;

public class Timer extends Thread {
	
	private int limit;
	private int currentTime;
	private int speed;
	private boolean running;
	
	private int secOffset = 0;
	private int minOffset = 0;
	private int hourOffset = 0;
	
	public static String toTime(int currentTime) {
		int time = currentTime;
		int hour = time / 3600;
		time -= hour * 3600;
		int min = time / 60;
		time -= min * 60;
		int sec = time % 60;
		String result ="";
		if (hour != 0) {
			result += hour+"hr ";
		}
		if (min != 0) {
			result += min+"min ";
		}
		result += sec+"s";
		return result;
	}
	
	public Timer(int limit, int speed, int hour, int min, int sec) {
		this.limit = limit;
		this.speed = speed;
		this.currentTime = 0;
		this.running = true;
		this.hourOffset = hour;
		this.minOffset = min;
		this.secOffset = sec;
	}
	
	public void run() {
		while (currentTime < limit*60) {
			try {
				sleep(50*speed);
				currentTime += 2;
				secOffset += 3;
				if (secOffset >= 60) {
					secOffset -= 60;
					minOffset++;
				}
				if (minOffset >= 60) {
					minOffset -= 60;
					hourOffset++;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			currentTime++;
		}
		System.out.println("\nThe shop is closed. No more clients can come inside\n");
		running = false;
	}
	
	public synchronized boolean isHour() {
		if (minOffset == 0 && secOffset == 0)
			return true;
		return false;
	}
	
	public int getHour() {
		return hourOffset;
	}
	
	public synchronized String printTime() {
		return hourOffset + ":" + minOffset;
	}
	
	public synchronized int getTime() {
		return currentTime;
	}
	
	public synchronized boolean isRunning() {
		return running;
	}
}

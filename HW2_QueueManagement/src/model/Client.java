package model;
import java.util.Random;

public class Client {
	
	public static int MIN_SERVICE_TIME;
	public static int MAX_SERVICE_TIME;
	
	private int arrivalTime;
	private int serviceTime;
	private int finishTime;
	
	private int waitTime;
	
	private int id;
		
	public Client(int arrivalTime, int id) {
		this.arrivalTime = arrivalTime;
		Random rand = new Random();
		this.serviceTime = rand.nextInt(MAX_SERVICE_TIME - MIN_SERVICE_TIME + 1) + MIN_SERVICE_TIME;
		this.finishTime = 0; 
		this.id = id;
	}
	
	public void leave(int finishTime) {
		this.finishTime = finishTime;
	}
	
	public void setWaitTime(int value) {
		this.waitTime = value;
	}
	
	public int getWaitTime() {
		return waitTime;
	}
	
	public int getWaitingTime() {
		return finishTime - arrivalTime;
	}
	
	public int getServiceTime() {
		return serviceTime;
	}
	
	public int getID() {
		return id;
	}
}

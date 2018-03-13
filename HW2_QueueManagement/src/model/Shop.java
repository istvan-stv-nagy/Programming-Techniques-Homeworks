package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Shop extends Thread {
	
	//shop parameters
	private float minArrivalTime;
	private float maxArrivalTime;
	
	public static int WAITING_TOLERANCE; //the maximum number of	
	public static int SIMULATION_TIME;
	private int maxQueues;
	
	private ArrayList<Queue> queues;
	private Timer timer;
	
	private int currentQueue;
	private int clientsNumber;
	private BufferedWriter bw;
		
	Random rand = new Random();
	
	public Shop(BufferedWriter bw) {
		this.queues = new ArrayList<Queue>();
		currentQueue = 1;
		clientsNumber = 0;
		this.bw = bw;
	}
	
	public void run() {
		timer.start();
		try {
			bw.write("Simulation started at " + timer.printTime());
			bw.newLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Queue initialQueue = new Queue(currentQueue++,timer,bw);
		queues.add(initialQueue);
		initialQueue.start();
		while (timer.isRunning()) {
			//generate a new client
			try {
				//time interval between clients
				sleep((int)(1000*(minArrivalTime + (rand.nextFloat()*(maxArrivalTime - minArrivalTime)))));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Client client = new Client(timer.getTime(),++clientsNumber);
			
			//check if a new queue must be opened or not
			if (notEnoughQueues() && queues.size() < maxQueues) {
				Queue newQueue = new Queue(currentQueue++,timer,bw);
				queues.add(newQueue);
				newQueue.start();
			}
			//add the client to the queue where he will wait the shortest amount of time
			findQueue().add(client);
		}
		for (Queue q : queues) {
			if (q.isRunning())
				q.finish();
		}
	}
	
	public void setTimer(int hour, int min, int sec) {
		timer = new Timer(SIMULATION_TIME,1,hour,min,sec);
	}
	
	public boolean notEnoughQueues() {
		for (Queue q : queues) {
			if (q.getWaitTime() < WAITING_TOLERANCE)
				return false;
		}
		return true;
	}
	
	public boolean finished() {
		for (Queue q : queues) {
			if (q.isRunning())
				return false;
		}
		return true;
	}
	
	public synchronized Queue findQueue() {
		if (queues.size() == 0)
			return null;
		Queue result = queues.get(0);
		for (Queue q : queues) {
			if (q.getWaitTime() < result.getWaitTime()) {
				result = q;
			}
		}
		return result;
	}
	
	public Queue getQueue(int id) {
		if (queues.size()< id || id <= 0)
			return null;
		return queues.get(id-1);
	}
	
	public synchronized int getQueueNumber() {
		return queues.size();
	}
	
	public String getInfo(int queueID) {
		if (queueID <= queues.size() && queueID > 0)
			return queues.get(queueID-1).getInfo();
		return null;
	}
	
	public String getShopTime() {
		if (timer != null)
			return timer.printTime();
		return null;
	}
	
	public void setParamters(float minArrivalTime, float maxArrivalTime, int minServiceTime, int maxServiceTime, int maxQueues, int simTime, int tolerance) {
		this.minArrivalTime = minArrivalTime;
		this.maxArrivalTime = maxArrivalTime;
		Client.MAX_SERVICE_TIME = maxServiceTime;
		Client.MIN_SERVICE_TIME = minServiceTime;
		this.maxQueues = maxQueues;
		Shop.SIMULATION_TIME = simTime;
		Shop.WAITING_TOLERANCE = tolerance;
	}
}


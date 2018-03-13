package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Queue extends Thread {
	
	private int id;
	
	private int numberOfClients;
	private int totalServiceTime;
	private int totalWaitingTime;
	
	private int emptyTime = 0;
	private int closeTime;
	private boolean open;
	
	private Timer timer;
	private BufferedWriter bw;
	
	private List<Client> clients;
	
	public Queue(int id, Timer timer, BufferedWriter bw) {
		this.id = id;
		this.clients = new LinkedList<Client>();
		this.totalServiceTime = 0;
		this.totalWaitingTime = 0;
		this.numberOfClients = 0;
		this.timer = timer;
		this.emptyTime = 0;
		this.open = false;
		this.bw = bw;
	}
	
	public void run() {
		try{
		while (timer.isRunning() || (!isEmpty())) {
			if (!open && (!isEmpty()))
				open();
			else if (open && isEmpty())
				close();
			Client currentClient = remove();
			sleep(currentClient.getServiceTime()*1000);
		}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized Client remove() {
		while (clients.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Client client = clients.get(0);
		clients.remove(0);
		totalServiceTime += client.getServiceTime();
		client.leave(timer.getTime());
		totalWaitingTime += client.getWaitingTime();
		try {
			bw.write("client " + client.getID() + " served at queue " + id + "at time:" + timer.printTime());
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		notifyAll();
		return client;
	}
	
	public synchronized void add(Client c) {
		int waitTime = 0;
		for (Client client : clients)
			waitTime = waitTime + client.getServiceTime();
		clients.add(c);
		c.setWaitTime(waitTime);
		numberOfClients++;
		try {
			bw.write("client " + c.getID() + " added at queue " + id + "at time:" + timer.printTime());
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		notifyAll();
	}
	
	public synchronized void finish() {
		notify();
	}
	
	private synchronized boolean isEmpty() {
		if (clients.size() == 0)
			return true;
		return false;
	}
	
	private void open() {
		this.open = true;
		emptyTime += timer.getTime() - closeTime;
	}
	
	private void close() {
		this.open = false;
		closeTime = timer.getTime();
	}
	
	public synchronized int getWaitTime() {
		int time = 0;
		for (Client c : clients) {
			time = time + c.getServiceTime();
		}
		return time;
	}
	
	public boolean isRunning() {
		return timer.isRunning() || (!isEmpty());
	}
	
	public float getAverageServiceTime() {
		return (float)totalServiceTime / numberOfClients;
	}
	
	public float getAverageWaitingTime() {
		return (float)totalWaitingTime / numberOfClients;
	}
	
	public int getEmptyTime() {
		return emptyTime;
	}
	
	public String getInfo() {
		String message = "";
		message += "queue " + id + " :\n";
		message += "customers " + numberOfClients + "\n";
		message += "avg service time : " + Timer.toTime((int)(getAverageServiceTime()*60)) + "\n";
		message += "avg waiting time : " + Timer.toTime((int)getAverageWaitingTime()) + "\n";
		message += "time empty : " + Timer.toTime((int)getEmptyTime()) + "\n";
		return message;
	}
	
	public synchronized String getCustomerLine() {
		String result = "Queue " + id + " : ";
		for (Client c : clients) {
			if (c.getWaitTime() < (Shop.WAITING_TOLERANCE*3)/4)
				result += "\u263A";
			else 
				result += "\u2639";
		}
		if (clients.size() == 0)
			result+="empty";
		return result+"\n";
	}
	
	public int getID() {
		return id;
	}
}

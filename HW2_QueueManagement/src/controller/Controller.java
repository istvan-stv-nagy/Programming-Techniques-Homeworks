package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import model.Shop;
import view.View;

public class Controller {
	
	private Shop shop;
	private View view;
	
	private BufferedWriter bw = null;
	private FileWriter fw = null;
	private static final String FILENAME = "log.txt";
	
	public Controller(View view) {
		this.view = view;
		BtnListener btnListener = new BtnListener();
		view.setBtnInfoListener(btnListener);
		view.setBtnStartListener(btnListener);
	}
	
	public boolean setupShop() {
		float minArrivalTime, maxArrivalTime;
		int minServiceTime, maxServiceTime;
		int maxQueues;
		int simTime;
		int tolerance;
		int hour,min,sec;
		try {
		minArrivalTime = Float.parseFloat(view.getTextMinArrival());
		maxArrivalTime = Float.parseFloat(view.getTextMaxArrival());
		minServiceTime = Integer.parseInt(view.getTextMinService());
		maxServiceTime = Integer.parseInt(view.getTextMaxService());
		maxQueues = Integer.parseInt(view.getTextMaxQueues());
		simTime = Integer.parseInt(view.getTextSimulation());
		tolerance = Integer.parseInt(view.getTolerance());
		hour = Integer.parseInt(view.getHour());
		min = Integer.parseInt(view.getMinute());
		sec = Integer.parseInt(view.getSec());
		shop.setParamters(minArrivalTime,maxArrivalTime,minServiceTime,maxServiceTime,maxQueues,simTime,tolerance);
		shop.setTimer(hour,min,sec);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
		
	}
	
	class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "start" :
				try {
				fw = new FileWriter(FILENAME);
				bw = new BufferedWriter(fw);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				shop = new Shop(bw);
				if (setupShop()) {
					shop.start();
					Thread update = new Thread(new Runnable() {
						@Override
						public void run() {
							String state = "";
							while (shop.isAlive() || !shop.finished()) {
								state = "";
								if (shop.getQueueNumber() > 0) {
									for (int i=1; i<=3; i++)
										if (shop.getQueueNumber() >= i)
											state += shop.getQueue(i).getCustomerLine();
								}
								state += shop.getShopTime();
								view.setTextArea(state);
							}
							state="all clients served";
							view.setTextArea(state);
							try {
								bw.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					});
					update.start();
				} else {
					//set error message
					view.showMessage("Invalid Parameters!");
				}
				break;
			case "info" :
				int queueID;
				try {
					queueID = Integer.parseInt(view.getTextQueueNumber());
					view.setTextQueueInfo(shop.getInfo(queueID));
				} catch (NumberFormatException exeption) {
					view.setTextQueueInfo(null);
				}
				
				break;
			}
			
		}
		
	}
}

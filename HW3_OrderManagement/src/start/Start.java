package start;

import presentation.Controller;
import presentation.View;


public class Start {
	
	public static void main(String args[]) {
		View v = new View();
		
		@SuppressWarnings("unused")
		Controller c = new Controller(v);
	}
	
}

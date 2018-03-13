package model;

import controller.Controller;
import view.View;

public class Test {

	public static void main(String args[]) {
		View view = new View();
		
		Controller controller = new Controller(view);
	}
	
}

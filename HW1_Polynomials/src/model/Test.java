package model;

import controller.Controller;
import view.View;

public class Test {
	
	public static void main(String args[]) {
		
		Polynomial first = new Polynomial();
		Polynomial second = new Polynomial();
		
		View view = new View();
		
		Controller controller = new Controller(first, second, view);
	}
	
}

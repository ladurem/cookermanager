package fr.inria.phoenix.scenario.kitchen.impl.context;


public class Test {

	public static void main(String args[]){
		System.out.println("STARTED");
		KitchenTimer kt = new KitchenTimer();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("CHANGED");
		kt.setTimer(10000);
	}
	
}

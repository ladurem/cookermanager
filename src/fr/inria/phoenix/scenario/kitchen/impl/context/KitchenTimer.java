package fr.inria.phoenix.scenario.kitchen.impl.context;

import java.util.Timer;
import java.util.TimerTask;


public class KitchenTimer {
 
	// Timer value = 15 minutes
	public long timer = 15*60*1000;
	// Interval = 1s
	public final int INTERVAL = 1000;
	public boolean running = true;
	public Timer t;
	
	
	public KitchenTimer(){
		timer();
	}
	
	public KitchenTimer(long timer){
		this.timer = timer;
		timer();
	}
	
	public long getTimer(){
		return timer;
	}
	
	public void setTimer(long timer){
		this.timer = timer;
	}
	
	public void timer(){

		
		t = new Timer();
		TimerTask timerTask = new TimerTask() {
			  @Override
			  public void run() {
				  if (timer > 0){
		    	    	timer -=1000;
		    	    	System.out.println(timer);
		    	    }
		    	    else{
		    	    	running = false;
		    	    	t.cancel();
		    	    	System.out.println("STOPPED");
		    	    }
			  }
		};
		
		t.scheduleAtFixedRate(timerTask, 0, INTERVAL);	
	}
}
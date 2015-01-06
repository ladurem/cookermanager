package fr.inria.phoenix.scenario.kitchen.impl.context;

import java.util.Timer;
import java.util.TimerTask;


public class KitchenTimer {
	
	public long timer = Config.timer;
	public long interval = Config.INTERVAL;
	
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
	
	public long getInterval(){
		return interval;
	}
	
	public void setInterval(long interval){
		this.interval = interval;
	}
	
	public void stopTimer(){
		t.cancel();
		timer = Config.timer;
	}
	
	public void timer(){

		t = new Timer();
		TimerTask timerTask = new TimerTask() {
			  @Override
			  public void run() {
				  if (timer > 0){
		    	    	timer -= interval;
		    	    	System.out.println(timer);
		    	    }
		    	    else{
		    	    	running = false;
		    	    	t.cancel();
		    	    	System.out.println("STOPPED");
		    	    }
			  }
		};
		
		t.scheduleAtFixedRate(timerTask, 0, interval);	
	}
}
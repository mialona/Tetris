package business;

import java.util.TimerTask;

import domain.Time;

public class StopWatchThread extends TimerTask {

	private long initialTime;
	private Time time;
	
	public StopWatchThread(Time time) {
		this.initialTime = System.currentTimeMillis() - time.getTimeMillis();
		
		this.time = time;
	}
	
	public StopWatchThread(Time time, long startTime) {
		this.initialTime = startTime;
		
		this.time = time;
	}

	@Override
	public void run() {
		this.time.setTimeMillis(System.currentTimeMillis() - initialTime);
	}

}

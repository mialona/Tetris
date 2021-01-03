package domain;

public class Time {
	
	private int hours;
	private int minutes;
	private int seconds;
	private String string;
	
	public Time() {
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
		
		this.setString();
	}
	
	public Time(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		
		this.setString();
	}

	public synchronized long getTimeMillis() {
		long time = Integer.toUnsignedLong(this.hours*3600 + this.minutes*60 + this.seconds);
		return time*1000;
	}

	public synchronized void setTimeMillis(long timeMillis) {
		int time = (int)(timeMillis / 1000);
		
		this.seconds = time % 60;
		int timeMinutes = time / 60;
		this.minutes = timeMinutes % 60;
		this.hours = timeMinutes / 60;
		
		this.setString();
	}

	public synchronized int getHours() {
		return hours;
	}

	public synchronized void setHours(int hours) {
		this.hours = hours;
		
		this.setString();
	}

	public synchronized int getMinutes() {
		return minutes;
	}

	public synchronized void setMinutes(int minutes) {
		this.minutes = minutes;
		
		this.setString();
	}

	public synchronized int getSeconds() {
		return seconds;
	}

	public synchronized void setSeconds(int seconds) {
		this.seconds = seconds;
		
		this.setString();
	}

	@Override
	public String toString() {
		return this.string;
	}
	
	private void setString() {
		this.string = this.hours + ":" + (this.minutes < 10 ? "0" : "") + this.minutes + ":" + (this.seconds < 10 ? "0" : "") + this.seconds;
	}
	
}

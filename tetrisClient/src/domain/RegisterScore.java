package domain;

import java.util.Date;

public class RegisterScore {

	private long score;
	private Time time;
	private Date date;
	
	public RegisterScore(long score, Time time, Date date) {
		this.score = score;
		this.time = time;
		this.date = date;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}

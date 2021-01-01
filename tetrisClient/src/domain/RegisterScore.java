package domain;

import java.util.Date;

public class RegisterScore {
	
	private long score;
	private Date date;
	
	public RegisterScore(long score, Date date) {
		super();
		this.score = score;
		this.date = date;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}

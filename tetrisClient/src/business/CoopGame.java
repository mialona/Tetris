package business;

import java.util.Date;
import java.util.List;

import dataAccess.CoopScoresAccess;
import domain.RegisterScore;

public class CoopGame extends Game {

	private CoopScoresAccess csa;
	
	public CoopGame() {
		super(2);
		
		this.csa = new CoopScoresAccess();
	}
	
	@Override
	public void start() {
		super.start(true);
	}

	public boolean isLose() {
		return this.isLose(0) || this.isLose(1);
	}

	public long getScore() {
		return this.getScore(0) + this.getScore(1);
	}

	public long getHighscore(){
		return this.csa.getHighscore();
	}

	public List<RegisterScore> getScores(){
		return this.csa.getScores();
	}

	public void saveScore(){
		this.csa.addScore(new RegisterScore(this.getScore(), this.getTime(), new Date()));
	}

}

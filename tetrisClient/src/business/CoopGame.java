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
	public boolean start() {
		return super.start(true);
	}

	public boolean isLose() {
		return this.isPlayerLose(0) || this.isPlayerLose(1);
	}

	public long getScore() {
		return this.getPlayerScore(0) + this.getPlayerScore(1);
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

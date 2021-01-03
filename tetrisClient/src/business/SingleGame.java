package business;

import java.util.Date;
import java.util.List;

import dataAccess.SingleScoresAccess;
import domain.RegisterScore;

public class SingleGame extends Game {

	private SingleScoresAccess ssa;
	
	public SingleGame() {
		super(1);
		
		this.ssa = new SingleScoresAccess();
	}

	@Override
	public void start() {
		super.start(true);
	}

	public long getHighscore(){
		return this.ssa.getHighscore();
	}

	public List<RegisterScore> getScores(){
		return this.ssa.getScores();
	}

	public void saveScore(){
		this.ssa.addScore(new RegisterScore(this.getScore(0), this.getTime(), new Date()));
	}
	
}

package business;

import java.util.ArrayList;
import java.util.List;

import domain.RegisterScore;

public class SingleGame extends Game {

	private long highscore;
	
	public SingleGame() {
		super();
		
		this.highscore = 0; //search
	}

	public long getHighscore(){
		return this.highscore;
	}

	public List<RegisterScore> getScores(){
		return new ArrayList<RegisterScore>(); //search
	}

	public void saveScore(){
		if(this.highscore < this.getScore()) {
			//save
		}
		
		//save
	}
	
}

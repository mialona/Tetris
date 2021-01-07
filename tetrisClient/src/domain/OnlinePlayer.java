package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class OnlinePlayer implements Serializable {

	private static final long serialVersionUID = 3714303349626567239L;
	private ArrayList<Block> blocks;
	private long score;
	private boolean lose;
	private boolean modified;
	
	public OnlinePlayer() {
		this.blocks = new ArrayList<Block>();
		this.score = 0;
		this.lose = false;
		this.modified = false;
	}

	public OnlinePlayer(ArrayList<Block> blocks, long score, boolean lose, boolean modified) {
		this.blocks = blocks;
		this.score = score;
		this.lose = lose;
		this.modified = modified;
	}

	public synchronized ArrayList<Block> getBlocks() {
		return this.blocks;
	}

	public synchronized void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
		this.modified = true;
	}

	public synchronized long getScore() {
		return this.score;
	}

	public synchronized void setScore(long score) {
		this.score = score;
		this.modified = true;
	}

	public synchronized boolean isLose() {
		return this.lose;
	}

	public synchronized void setLose(boolean lose) {
		this.lose = lose;
		this.modified = true;
	}

	public synchronized boolean isModified() {
		return this.modified;
	}

	public synchronized void updated() {
		this.modified = false;
	}
	
}

package business;

import java.util.List;
import java.util.Timer;

import domain.Block;

public abstract class Game {
	
	private Player player;
	private Timer timer;

	public Game() {
		TetrominoGenerator.initiate();
		
		this.player = new Player(TetrominoGenerator.getTetromino());
		this.player.setNextTetromino(TetrominoGenerator.getTetromino());
		this.timer = new Timer(true);
	}
	
	public void start() {
		this.timer.scheduleAtFixedRate(new LoopThread(this.player), 0, 750);
	}
	
	public boolean lose() {
		return this.player.isOutOfLimit();
	}
	
	public void stop() {
		this.timer.cancel();
	}
	
	public long getScore() {
		return this.player.getScore();
	}
	
	public List<Block> getPlayerBlocksNext() {
		return this.player.getBlocksNextTetromino();
	}
	
	public List<Block> getPlayerBlocks() {
		return this.player.getBlocks();
	}
	
	public boolean isPlayerModified() {
		return this.player.isModified();
	}
	
	public void updatedPlayer() {
		this.player.updated();
	}
	
	public void moveDownPlayer() {
		Thread thread = new Thread(new MoveDownThread(player));
		thread.setDaemon(true);
		thread.start();
	}
	
	public void moveRightPlayer() {
		Thread thread = new Thread(new MoveRightThread(player));
		thread.setDaemon(true);
		thread.start();
	}
	
	public void moveLeftPlayer() {
		Thread thread = new Thread(new MoveLeftThread(player));
		thread.setDaemon(true);
		thread.start();
	}
	
	public void rotatePlayer() {
		Thread thread = new Thread(new RotateThread(player));
		thread.setDaemon(true);
		thread.start();
	}

}

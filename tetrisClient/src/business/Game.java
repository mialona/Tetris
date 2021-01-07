package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import domain.Block;
import domain.Player;
import domain.Time;

public abstract class Game {
	
	private boolean running;
	private long startTime;
	private Time time;
	private Timer timeTimer;
	private List<Player> players;
	private List<Timer> timers;

	public Game(int numberPlayers) {
		this.running = false;		
		this.startTime = -1;
		this.players = new ArrayList<Player>();
		
		for(int i = 0; i < numberPlayers; i++) {
			Player player = new Player(TetrominoGenerator.getRandomTetromino());
			player.setNextTetromino(TetrominoGenerator.getRandomTetromino());
			this.players.add(player);
		}
		
		this.time = new Time();
	}
	
	protected boolean start(boolean paused) {
		if(!this.running) {
			this.timers = new ArrayList<Timer>();
			for(int i = 0; i < this.players.size(); i++) {
				this.timers.add(new Timer(true));
				this.timers.get(i).scheduleAtFixedRate(new LoopThread(this.players.get(i)), 0, 750);
			}
	
			if(this.startTime == -1) {
				this.startTime = System.currentTimeMillis();
			}
			this.timeTimer = new Timer(true);
			if(paused) {
				this.timeTimer.scheduleAtFixedRate(new StopWatchThread(this.time), 0, 1000);
			}
			else {
				this.timeTimer.scheduleAtFixedRate(new StopWatchThread(this.time, this.startTime), 0, 1000);
			}
			
			this.running = true;
			return true;
		}
		else {
			return false;
		}
	}
	
	public abstract boolean start();
	
	public boolean stop() {
		if(this.running) {
			for(Timer timer : this.timers) {
				timer.cancel();
			}
	
			this.timeTimer.cancel();
			
			this.running = false;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isRunning(){
		return this.running;
	}
	
	public Time getTime(){
		return this.time;
	}
	
	public long getPlayerScore(int playerNumber) throws IndexOutOfBoundsException {
		return this.players.get(playerNumber).getScore();
	}
	
	public boolean isPlayerLose(int playerNumber) throws IndexOutOfBoundsException {
		return this.players.get(playerNumber).isOutOfLimit();
	}
	
	public List<Block> getPlayerBlocksNext(int playerNumber) throws IndexOutOfBoundsException {
		return this.players.get(playerNumber).getBlocksNextTetromino();
	}
	
	public List<Block> getPlayerBlocks(int playerNumber) throws IndexOutOfBoundsException {
		return this.players.get(playerNumber).getBlocks();
	}
	
	public boolean isPlayerModified(int playerNumber) throws IndexOutOfBoundsException {
		return this.players.get(playerNumber).isModified();
	}
	
	public void updatedPlayer(int playerNumber) throws IndexOutOfBoundsException {
		this.players.get(playerNumber).updated();
	}
	
	public void moveDownPlayer(int playerNumber) throws IndexOutOfBoundsException {
		if(!this.isPlayerLose(playerNumber)) {
			Thread thread = new Thread(new MoveDownThread(this.players.get(playerNumber)));
			thread.setDaemon(true);
			thread.start();
		}
	}
	
	public void moveRightPlayer(int playerNumber) throws IndexOutOfBoundsException {
		if(!this.isPlayerLose(playerNumber)) {
			Thread thread = new Thread(new MoveRightThread(this.players.get(playerNumber)));
			thread.setDaemon(true);
			thread.start();
		}
	}
	
	public void moveLeftPlayer(int playerNumber) throws IndexOutOfBoundsException {
		if(!this.isPlayerLose(playerNumber)) {
			Thread thread = new Thread(new MoveLeftThread(this.players.get(playerNumber)));
			thread.setDaemon(true);
			thread.start();
		}
	}
	
	public void rotatePlayer(int playerNumber) throws IndexOutOfBoundsException {
		if(!this.isPlayerLose(playerNumber)) {
			Thread thread = new Thread(new RotateThread(this.players.get(playerNumber)));
			thread.setDaemon(true);
			thread.start();
		}
	}

}

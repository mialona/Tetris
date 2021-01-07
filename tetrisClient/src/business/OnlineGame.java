package business;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import domain.Block;
import domain.OnlinePlayer;

public class OnlineGame extends Game {

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Timer senderTimer;
	private int numOnlinePlayers;
	private List<OnlinePlayer> onlinePlayers;
	private boolean ended;
	
	public OnlineGame(int numOnlinePlayers, ObjectInputStream ois, ObjectOutputStream oos) {
		super(1);
		
		this.numOnlinePlayers = numOnlinePlayers;
		this.ois = ois;
		this.oos = oos;
		
		this.onlinePlayers = new ArrayList<OnlinePlayer>();
		for(int i = 0; i < this.numOnlinePlayers; i++) {
			this.onlinePlayers.add(new OnlinePlayer());
		}
		
		this.ended = false;
	}

	@Override
	public boolean start() {
		boolean start = super.start(false);
		
		if(start) {
			this.senderTimer = new Timer(true);
			this.senderTimer.scheduleAtFixedRate(new OnlineThread(this), 0, 500);
		}
		
		return start;
	}
	
	public boolean stop() {
		boolean stop = super.stop();
		
		if(stop) {
			this.senderTimer.cancel();
		}
		
		return stop;
	}

	public boolean isEnded() {
		return this.ended;
	}

	public void setEnded() {
		this.ended = true;
	}

	public boolean isAllPlayersLose() {
		boolean lose = this.isPlayerLose(0);
		
		for(OnlinePlayer onlinePlayer : this.onlinePlayers) {
			lose &= onlinePlayer.isLose();
		}
		
		return lose;
	}
	
	public int actualWinner() {
		switch(numOnlinePlayers) {
			case 1:
				if(this.getPlayerScore(0)>this.getOnlinePlayerScore(0)) {
					return 0;
				}
				else if(this.getOnlinePlayerScore(0)>this.getPlayerScore(0)) {
					return 1;
				}
				else {
					return -1;
				}
			case 2:
				if((this.getPlayerScore(0)>this.getOnlinePlayerScore(0)) &&
						(this.getPlayerScore(0)>this.getOnlinePlayerScore(1))) {
					return 0;
				}
				else if((this.getOnlinePlayerScore(0)>this.getPlayerScore(0)) &&
						(this.getOnlinePlayerScore(0)>this.getOnlinePlayerScore(1))) {
					return 1;
				}
				else if((this.getOnlinePlayerScore(1)>this.getPlayerScore(0)) &&
						(this.getOnlinePlayerScore(1)>this.getOnlinePlayerScore(0))) {
					return 2;
				}
				else {
					return -1;
				}
			default:
				if((this.getPlayerScore(0)>this.getOnlinePlayerScore(0)) &&
						(this.getPlayerScore(0)>this.getOnlinePlayerScore(1)) &&
						(this.getPlayerScore(0)>this.getOnlinePlayerScore(2))) {
					return 0;
				}
				else if((this.getOnlinePlayerScore(0)>this.getPlayerScore(0)) &&
						(this.getOnlinePlayerScore(0)>this.getOnlinePlayerScore(1)) &&
						(this.getOnlinePlayerScore(0)>this.getOnlinePlayerScore(2))) {
					return 1;
				}
				else if((this.getOnlinePlayerScore(1)>this.getPlayerScore(0)) &&
						(this.getOnlinePlayerScore(1)>this.getOnlinePlayerScore(0)) &&
						(this.getOnlinePlayerScore(1)>this.getOnlinePlayerScore(2))) {
					return 2;
				}
				else if((this.getOnlinePlayerScore(2)>this.getPlayerScore(0)) &&
						(this.getOnlinePlayerScore(2)>this.getOnlinePlayerScore(1)) &&
						(this.getOnlinePlayerScore(2)>this.getOnlinePlayerScore(0))) {
					return 3;
				}
				else {
					return -1;
				}
		}
	}
	
	public int getNumOnlinePlayers(){
		return this.numOnlinePlayers;
	}
	
	public void setListOnlinePlayers(List<OnlinePlayer> onlinePlayers){
		this.onlinePlayers = onlinePlayers;
	}
	
	public long getOnlinePlayerScore(int onlinePlayerNumber) throws IndexOutOfBoundsException {
		return this.onlinePlayers.get(onlinePlayerNumber).getScore();
	}
	
	public boolean isOnlinePlayerLose(int onlinePlayerNumber) throws IndexOutOfBoundsException {
		return this.onlinePlayers.get(onlinePlayerNumber).isLose();
	}
	
	public List<Block> getOnlinePlayerBlocks(int onlinePlayerNumber) throws IndexOutOfBoundsException {
		return this.onlinePlayers.get(onlinePlayerNumber).getBlocks();
	}
	
	public boolean isOnlinePlayerModified(int onlinePlayerNumber) throws IndexOutOfBoundsException {
		return this.onlinePlayers.get(onlinePlayerNumber).isModified();
	}
	
	public void updatedOnlinePlayer(int onlinePlayerNumber) throws IndexOutOfBoundsException {
		this.onlinePlayers.get(onlinePlayerNumber).updated();
	}
	
	public ObjectInputStream getInputStream() {
		return this.ois;
	}
	
	public ObjectOutputStream getOutputStream() {
		return this.oos;
	}

}

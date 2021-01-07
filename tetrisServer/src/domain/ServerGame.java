package domain;

import java.util.ArrayList;
import java.util.List;

public class ServerGame {

	private int id;
	private int numMaxPlayers;
	private String description;
	private List<OnlinePlayer> players;
	private boolean ended;
	
	public ServerGame(int id, int numMaxPlayers, String description) {
		this.id = id;
		this.numMaxPlayers = numMaxPlayers;
		this.description = description;
		this.players = new ArrayList<OnlinePlayer>();
		this.ended = false;
	}

	public int getId() {
		return this.id;
	}

	public int getNumMaxPlayers() {
		return this.numMaxPlayers;
	}

	public String getDescription() {
		return this.description;
	}

	public int getNumPlayers() {
		return this.players.size();
	}

	public synchronized int addPlayer() {
		this.players.add(new OnlinePlayer());
		
		return this.players.size() - 1;
	}

	public void setPlayer(int numPlayer, OnlinePlayer player) {
		this.players.set(numPlayer, player);
	}

	public synchronized void deletePlayer(int numPlayer) {
		this.players.remove(numPlayer);
	}

	public OnlinePlayer getPlayer(int numPlayer){
		return this.players.get(numPlayer);
	}

	public void setLosePlayer(int numPlayer) {
		this.players.get(numPlayer).setLose(true);
	}

	public boolean isAllPlayersLose() {
		boolean ended = true;
		
		for(OnlinePlayer onlinePlayer : this.players) {
			ended &= onlinePlayer.isLose();
		}
		
		return ended;
	}

	public boolean isEnded() {
		return ended || this.isAllPlayersLose();
	}

	public void setEnded() {
		this.ended = true;
	}

	@Override
	public String toString() {
		return this.id + "," + this.getNumPlayers() + "," +
				this.numMaxPlayers + "," + this.description + "\r\n";
	}
	
}

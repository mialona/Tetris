package business;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import domain.OnlinePlayer;
import domain.ServerGame;
import presentation.ServerConsole;

public class RequestThread implements Runnable {

	private Socket socket;
	private HashMap<Integer, ServerGame> serverGames;
	private ServerGame game;
	private int numPlayer;
	private ArrayList<String> gamesInfo;
	private boolean waitingState;
	
	public RequestThread(Socket socket, HashMap<Integer, ServerGame> serverGames) {
		this.socket = socket;
		this.serverGames = serverGames;
		this.game = null;
		this.numPlayer = -1;
		this.gamesInfo = new ArrayList<String>();
		this.waitingState = false;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
			
			String request = ((String) ois.readObject());
			if(request.equals("create")) {
				int numMaxPlayers = (Integer) ois.readObject();
				String description = (String) ois.readObject();
				
				if(numMaxPlayers > 1) {
					this.game = new ServerGame(IdGenerator.getNextId(), numMaxPlayers, description);
					this.numPlayer = this.game.addPlayer();
					this.serverGames.put(this.game.getId(),this.game);
					
					ServerConsole.println(this.socket.getInetAddress().getHostAddress()+" has created a "+
							this.game.getNumMaxPlayers()+"-player game ("+this.game.getId()+"): "+this.game.getDescription()+".");
					oos.writeObject("ok");
					oos.flush();
				}
				else {
					oos.writeObject("error");
					oos.flush();
				}
			}
			else if(request.equals("connect")) {
				ServerGame chooseServerGame = this.serverGames.get((Integer) ois.readObject());
				if(chooseServerGame.getNumPlayers() < chooseServerGame.getNumMaxPlayers()) {
					this.numPlayer = chooseServerGame.addPlayer();
					this.game = chooseServerGame;
					
					ServerConsole.println("Player "+this.numPlayer+" has connected to game "+this.game.getId()+".");
					oos.writeObject("ok");
					oos.flush();
				}
				else {
					oos.writeObject("error");
					oos.flush();
				}
			}
			else if(request.equals("get")) {
				this.serverGames.forEach((key,value) -> {
					if((!value.isEnded())&&(value.getNumPlayers() < value.getNumMaxPlayers())) {
						this.gamesInfo.add(value.toString());
					}
				});
				
				oos.writeObject(this.gamesInfo);
				oos.flush();
				ServerConsole.println("Available games sent to "+this.socket.getInetAddress().getHostAddress()+".");
			}
			
			if(this.game != null) {
				this.waitingState = true;
				while((this.game.getNumPlayers() < this.game.getNumMaxPlayers()) &&
						!this.game.isEnded() && this.socket.isConnected() && !this.socket.isClosed()) {
					oos.writeObject("wait");
					oos.flush();
					Thread.sleep(500);
				}
				this.waitingState = false;
				
				if (!this.game.isEnded() && this.socket.isConnected() && !this.socket.isClosed()) {
					ServerConsole.println("Player "+this.numPlayer+" has started game "+this.game.getId()+".");
					oos.writeObject("start");
					oos.flush();
					
					while(!this.game.isAllPlayersLose() && this.socket.isConnected() && !this.socket.isClosed()) {
			            this.game.setPlayer(this.numPlayer, (OnlinePlayer) ois.readObject());
			            
			            for(int i = 0; i < this.game.getNumMaxPlayers(); i++) {
		        			if(i != this.numPlayer) {
		        				oos.writeObject(this.game.getPlayer(i));
								oos.flush();
		        			}
		        		}
			            
			            if(this.game.isEnded()) {
			            	oos.writeObject("ended");
							oos.flush();
			            }
			            else {
			            	oos.writeObject("continue");
							oos.flush();
			            }
					}
				}
				else {
					oos.writeObject("error");
					oos.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(this.game != null) {
				if(this.waitingState) {
					this.game.deletePlayer(this.numPlayer);
					
					if(this.numPlayer == 0) {
						this.game.setEnded();
					}
				}
				else {
					this.game.setLosePlayer(this.numPlayer);
				}
			}
			
			if(this.numPlayer != -1) {
				ServerConsole.println("Player "+this.numPlayer+" has exited game "+this.game.getId()+".");
			}
			
			try {
				String hostAddress = this.socket.getInetAddress().getHostAddress();
				this.socket.close();
				ServerConsole.println(hostAddress+" has disconnected.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

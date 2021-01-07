package business;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class OnlineGameManager {

	private Socket socket = null;
	private int state;
	
	public OnlineGameManager(String host, int port){
		this.state = 0;
		try {
			this.socket = new Socket(host, port);
		} catch (Exception e) {
			this.setErrorState();
			e.printStackTrace();
		}
	}

	public OnlineGame create(int numMaxPlayers, String description){
		OnlineGame onlineGame = null;
		
		if(!this.isErrorState()) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
				
				oos.writeObject("create");
				oos.writeObject(numMaxPlayers);
				oos.writeObject(description);
				oos.flush();

				if (((String) ois.readObject()).equals("ok")) {
					onlineGame = new OnlineGame(numMaxPlayers - 1, ois, oos);
					
					Thread thread = new Thread(new ServerReplyThread(this, ois));
					thread.setDaemon(true);
					thread.start();
				}
				else {
					this.setErrorState();
				}
			} catch (Exception e) {
				this.setErrorState();
				e.printStackTrace();
			}
		}
		
		return onlineGame;
	}

	@SuppressWarnings({ "unchecked" })
	public List<String> getOnlineGamesInfo() {
		List<String> list = new ArrayList<String>();;
		
		if(!this.isErrorState()) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());

				oos.writeObject("get");
				oos.flush();

				list = (ArrayList<String>) ois.readObject();
				
				this.closeConnection();
			} catch (IOException e) {
				this.setErrorState();
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				this.setErrorState();
				e.printStackTrace();
			}
		}
	
		return list;
	}

	public OnlineGame connect(int id, int numMaxPlayers) {
		OnlineGame onlineGame = null;
		
		if(!this.isErrorState()) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());

				oos.writeObject("connect");
				oos.writeObject(id);
				oos.flush();

				if (((String) ois.readObject()).equals("ok")) {
					onlineGame = new OnlineGame(numMaxPlayers - 1, ois, oos);
					
					Thread thread = new Thread(new ServerReplyThread(this, ois));
					thread.setDaemon(true);
					thread.start();
				}
				else {
					this.setErrorState();
				}
			} catch (IOException e) {
				this.setErrorState();
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				this.setErrorState();
				e.printStackTrace();
			}
		}
		
		return onlineGame;
	}

	public boolean isStartState(){
		return this.state == 1;
	}

	public void setStartState(){
		this.state = 1;
	}

	public boolean isErrorState(){
		return this.state == 2;
	}

	public void setErrorState(){
		this.state = 2;
	}

	public void closeConnection(){
		try {
			if(this.socket != null) {
				this.socket.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			this.socket = null;
		}
	}
}

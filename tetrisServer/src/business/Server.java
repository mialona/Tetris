package business;

import java.util.HashMap;

import dataAccess.PropertiesAccess;
import domain.ServerGame;
import presentation.ServerConsole;

public class Server {

	private PropertiesAccess properties;
	private Thread serverThread;
	private HashMap<Integer, ServerGame> serverGames;
	
	public Server() {
		this.properties = new PropertiesAccess();
		
		this.serverGames = new HashMap<Integer, ServerGame>();
		
		this.serverThread = new Thread(new ServerThread(this.properties.getPort(),
				this.properties.getMaxRequests(), this.serverGames));
		this.serverThread.setDaemon(true);
	}

	public void start() {
		this.serverThread.start();
		ServerConsole.println("Server successfully started on port "+this.properties.getPort()+".");
	}

	public void stop() {
		this.serverThread.interrupt();
	}
}

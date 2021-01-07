package business;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import domain.ServerGame;
import presentation.ServerConsole;

public class ServerThread implements Runnable{

	private int port;
	private int maxRequests;
	private HashMap<Integer, ServerGame> serverGames;
	
	public ServerThread(int port, int maxRequests, HashMap<Integer, ServerGame> serverGames) {
		this.port = port;
		this.maxRequests = maxRequests;
		this.serverGames = serverGames;
	}

	@Override
	public void run() {
		try (ServerSocket ss = new ServerSocket(this.port);) {
			Executor pool = Executors.newFixedThreadPool(this.maxRequests,
					new ThreadFactory() {
			            public Thread newThread(Runnable runnable) {
			                Thread thread = Executors.defaultThreadFactory().newThread(runnable);
			                thread.setDaemon(true);
			                
			                return thread;
			            }
			        });
			
			Timer gameDeleterTimer = new Timer(true);
			gameDeleterTimer.scheduleAtFixedRate(new GameDeleterThread(this.serverGames), 0, 5000);
			
			while(true) {
				try {
					Socket s = ss.accept();
					ServerConsole.println(s.getInetAddress().getHostAddress()+" has connected.");
					
					RequestThread request = new RequestThread(s, this.serverGames);
					pool.execute(request);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

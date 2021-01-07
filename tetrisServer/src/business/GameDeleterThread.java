package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

import domain.ServerGame;
import presentation.ServerConsole;

public class GameDeleterThread extends TimerTask{

	private HashMap<Integer, ServerGame> serverGames;
	
	public GameDeleterThread(HashMap<Integer, ServerGame> serverGames) {
		this.serverGames = serverGames;
	}

	@Override
	public void run() {
		synchronized (this.serverGames) {
			ArrayList<Integer> toDelete = new ArrayList<Integer>();
			
			this.serverGames.forEach((key,value) -> {
				if(value.isEnded()) {
					toDelete.add(key);
				}
			});
			
			for(Integer key : toDelete) {
				this.serverGames.remove(key);
				ServerConsole.println("Game "+key+" eliminated.");
			}
		}
	}

}

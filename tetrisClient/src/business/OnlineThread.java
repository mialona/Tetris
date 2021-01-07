package business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import domain.Block;
import domain.OnlinePlayer;

public class OnlineThread extends TimerTask {

	private OnlineGame onlineGame;
	
	public OnlineThread(OnlineGame onlineGame) {
		this.onlineGame = onlineGame;
	}

	@Override
	public void run() {
		try
        {
			OnlinePlayer player = new OnlinePlayer((ArrayList<Block>) this.onlineGame.getPlayerBlocks(0), this.onlineGame.getPlayerScore(0),
            		this.onlineGame.isPlayerLose(0), true);
			this.onlineGame.getOutputStream().writeObject(player);
			
			ArrayList<OnlinePlayer> onlinePlayers = new ArrayList<OnlinePlayer>();
			for(int i = 0; i < this.onlineGame.getNumOnlinePlayers(); i++) {
				onlinePlayers.add((OnlinePlayer) this.onlineGame.getInputStream().readObject());
    		}
            this.onlineGame.setListOnlinePlayers(onlinePlayers);
            
            if(((String) this.onlineGame.getInputStream().readObject()).equals("ended")) {
            	this.onlineGame.setEnded();
            }
        } 
        catch (IOException ioe) {
        	this.onlineGame.setEnded();
            ioe.printStackTrace();
        }
		catch (ClassNotFoundException e) {
			this.onlineGame.setEnded();
			e.printStackTrace();
		}
	}

}

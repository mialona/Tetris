package business;

import domain.Player;

public class RotateThread implements Runnable {

	private Player player;
	
	public RotateThread(Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		this.player.rotate();
	}

}

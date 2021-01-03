package business;

import java.util.TimerTask;

public class LoopThread extends TimerTask {

	private Player player;
	
	public LoopThread(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(6);
		
		if(!this.player.moveDown() && !this.player.isOutOfLimit()) {
			this.player.setNextTetromino(TetrominoGenerator.getTetromino());
		}
	}

}

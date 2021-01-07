package business;

import domain.Player;

public class MoveDownThread implements Runnable {

	private Player player;
	
	public MoveDownThread(Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		if(!this.player.moveDown()) {
			this.player.setNextTetromino(TetrominoGenerator.getRandomTetromino());
		}
	}

}

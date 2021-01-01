package business;

public class MoveRightThread implements Runnable {

	private Player player;
	
	public MoveRightThread(Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		this.player.moveRight();
	}

}

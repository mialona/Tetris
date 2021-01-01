package business;

public class MoveLeftThread implements Runnable {

	private Player player;
	
	public MoveLeftThread(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		this.player.moveLeft();
	}
}

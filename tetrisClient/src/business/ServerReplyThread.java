package business;

import java.io.ObjectInputStream;

public class ServerReplyThread implements Runnable {

	private OnlineGameManager ogm;
	private ObjectInputStream ois;
	
	public ServerReplyThread(OnlineGameManager ogm, ObjectInputStream ois) {
		this.ogm = ogm;
		this.ois = ois;
	}

	@Override
	public void run() {
		try {
			String reply = ((String) ois.readObject());
			
			while (reply.equals("wait")) {
				reply = ((String) ois.readObject());
			}
			
			if(reply.equals("start")) {
				this.ogm.setStartState();
			}
			else {
				this.ogm.setErrorState();
			}
		} catch (Exception e) {
			this.ogm.setErrorState();
			e.printStackTrace();
		}
			
	}

}

package business;

public class IdGenerator {

	private static int lastId = 0;
	
	public static int getNextId() {
		IdGenerator.lastId += 1;
		
		return IdGenerator.lastId;
	}
}

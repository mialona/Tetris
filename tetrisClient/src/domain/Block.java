package domain;

public class Block {

	private int x;
	private int y;
	private int color;
	
	public Block(int x, int y, int color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getColor() {
		return this.color;
	}

	public boolean moveUp() {
		if(this.y-1 >= 0) {
			this.y = this.y-1;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean moveDown() {
		if(this.y+1 <= 20) {
			this.y = this.y+1;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean moveRight() {
		if(this.x+1 <= 10) {
			this.x = this.x+1;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean moveLeft() {
		if(this.x-1 >= 0) {
			this.x = this.x-1;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		
		Block other = (Block) obj;
		return (this.x == other.x) && (this.y == other.y);
	}
	
}

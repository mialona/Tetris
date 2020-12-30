package domain;

import org.eclipse.swt.SWT;

public class TetrominoJ extends Tetromino {

	private int state;
	
	public TetrominoJ(Structure structure) {
		super(new Block(3, 0, SWT.COLOR_BLUE), new Block(4, 0, SWT.COLOR_BLUE),
				new Block(5, 0, SWT.COLOR_BLUE), new Block(5, 1, SWT.COLOR_BLUE), structure);
		
		this.state = 0;
	}
	
	@Override
	public boolean rotate() {
		Block preBlock1 = new Block(block1.getX(),block1.getY(),block1.getColor());
		Block preBlock3 = new Block(block3.getX(),block3.getY(),block3.getColor());
		Block preBlock4 = new Block(block4.getX(),block4.getY(),block4.getColor());
		
		if(this.state == 0) {
			preBlock1.moveUp();
			preBlock3.moveDown();
			preBlock4.moveLeft();
			
			if(preBlock1.moveRight() && preBlock3.moveLeft() && preBlock4.moveLeft()) {
				if(!this.structure.containsBlock(preBlock1) &&
						!this.structure.containsBlock(preBlock3) &&
						!this.structure.containsBlock(preBlock4)) {
					this.block1 = preBlock1;
					this.block3 = preBlock3;
					this.block4 = preBlock4;

					this.state = 1;
					return true;
				}
			}
		}
		else if(this.state == 1) {
			preBlock1.moveRight();
			preBlock3.moveLeft();
			preBlock4.moveUp();
			
			if(preBlock1.moveDown() && preBlock3.moveUp() && preBlock4.moveUp()) {
				if(!this.structure.containsBlock(preBlock1) &&
						!this.structure.containsBlock(preBlock3) &&
						!this.structure.containsBlock(preBlock4)) {
					this.block1 = preBlock1;
					this.block3 = preBlock3;
					this.block4 = preBlock4;

					this.state = 2;
					return true;
				}
			}
		}
		else if(this.state == 2) {
			preBlock1.moveDown();
			preBlock3.moveUp();
			preBlock4.moveRight();
			
			if(preBlock1.moveLeft() && preBlock3.moveRight() && preBlock4.moveRight()) {
				if(!this.structure.containsBlock(preBlock1) &&
						!this.structure.containsBlock(preBlock3) &&
						!this.structure.containsBlock(preBlock4)) {
					this.block1 = preBlock1;
					this.block3 = preBlock3;
					this.block4 = preBlock4;

					this.state = 3;
					return true;
				}
			}
		}
		else {
			preBlock1.moveLeft();
			preBlock3.moveRight();
			preBlock4.moveDown();
			
			if(preBlock1.moveUp() && preBlock3.moveDown() && preBlock4.moveDown()) {
				if(!this.structure.containsBlock(preBlock1) &&
						!this.structure.containsBlock(preBlock3) &&
						!this.structure.containsBlock(preBlock4)) {
					this.block1 = preBlock1;
					this.block3 = preBlock3;
					this.block4 = preBlock4;

					this.state = 0;
					return true;
				}
			}
		}
		
		return false;
	}

}

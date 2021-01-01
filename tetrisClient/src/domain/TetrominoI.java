package domain;

import org.eclipse.swt.SWT;

public class TetrominoI extends Tetromino {

	private int state;
	
	public TetrominoI(Structure structure) {
		super(new Block(3, 0, SWT.COLOR_CYAN), new Block(4, 0, SWT.COLOR_CYAN),
				new Block(5, 0, SWT.COLOR_CYAN), new Block(6, 0, SWT.COLOR_CYAN), structure);
		
		this.state = 0;
	}
	
	@Override
	public boolean rotate() {
		Block preBlock1 = new Block(block1.getX(),block1.getY(),block1.getColor());
		Block preBlock2 = new Block(block2.getX(),block2.getY(),block2.getColor());
		Block preBlock4 = new Block(block4.getX(),block4.getY(),block4.getColor());
		
		if(this.state == 0) {
			preBlock1.moveUp();
			preBlock1.moveUp();
			preBlock1.moveRight();
			preBlock2.moveUp();
			preBlock4.moveDown();
			
			if(preBlock1.moveRight() && preBlock2.moveRight() && preBlock4.moveLeft()) {
				if(!this.structure.containsBlock(preBlock1) &&
						!this.structure.containsBlock(preBlock2) &&
						!this.structure.containsBlock(preBlock4)) {
					this.block1 = preBlock1;
					this.block2 = preBlock2;
					this.block4 = preBlock4;
					
					this.state = 1;
					return true;
				}
			}
		}
		else {
			preBlock1.moveLeft();
			preBlock1.moveLeft();
			preBlock1.moveDown();
			preBlock2.moveLeft();
			preBlock4.moveRight();
			
			if(preBlock1.moveDown() && preBlock2.moveDown() && preBlock4.moveUp()) {
				if(!this.structure.containsBlock(preBlock1) &&
						!this.structure.containsBlock(preBlock2) &&
						!this.structure.containsBlock(preBlock4)) {
					this.block1 = preBlock1;
					this.block2 = preBlock2;
					this.block4 = preBlock4;

					this.state = 0;
					return true;
				}
			}
		}
		
		return false;
	}

}

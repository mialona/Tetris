package domain;

import org.eclipse.swt.SWT;

public class TetrominoZ extends Tetromino {

	private int state;
	
	public TetrominoZ(Structure structure) {
		super(new Block(3, 0, SWT.COLOR_GREEN), new Block(4, 0, SWT.COLOR_GREEN),
				new Block(4, 1, SWT.COLOR_GREEN), new Block(5, 1, SWT.COLOR_GREEN), structure);
		
		this.state = 0;
	}
	
	@Override
	public boolean rotate() {
		if(this.state == 0) {
			Block preBlock1 = new Block(block1.getX(),block1.getY(),block1.getColor());
			Block preBlock4 = new Block(block4.getX(),block4.getY(),block4.getColor());
			
			preBlock1.moveUp();
			preBlock4.moveLeft();
			
			if(preBlock1.moveRight() && preBlock4.moveLeft()) {
				if(!this.structure.containsBlock(preBlock1) &&
						!this.structure.containsBlock(preBlock4)) {
					this.block3 = this.block1;
					this.block1 = preBlock1;
					this.block4 = preBlock4;
					
					this.state = 1;
					return true;
				}
			}
		}
		else {
			Block preBlock3 = new Block(block3.getX(),block3.getY(),block3.getColor());
			Block preBlock4 = new Block(block4.getX(),block4.getY(),block4.getColor());
			
			preBlock3.moveDown();
			preBlock4.moveRight();
			
			if(preBlock3.moveRight() && preBlock4.moveRight()) {
				if(!this.structure.containsBlock(preBlock3) &&
						!this.structure.containsBlock(preBlock4)) {
					this.block1 = this.block3;
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

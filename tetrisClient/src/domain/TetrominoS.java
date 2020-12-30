package domain;

import org.eclipse.swt.SWT;

public class TetrominoS extends Tetromino {

	private int state;
	
	public TetrominoS(Structure structure) {
		super(new Block(3, 1, SWT.COLOR_GREEN), new Block(4, 1, SWT.COLOR_GREEN),
				new Block(4, 0, SWT.COLOR_GREEN), new Block(5, 0, SWT.COLOR_GREEN), structure);
		
		this.state = 0;
	}
	
	@Override
	public boolean rotate() {
		if(this.state == 0) {
			Block preBlock1 = new Block(block1.getX(),block1.getY(),block1.getColor());
			Block preBlock2 = new Block(block2.getX(),block2.getY(),block2.getColor());
			
			preBlock1.moveUp();
			preBlock2.moveLeft();
			
			if(preBlock1.moveUp() && preBlock2.moveUp()) {
				if(!this.structure.containsBlock(preBlock1) &&
						!this.structure.containsBlock(preBlock2)) {
					this.block1 = preBlock1;
					this.block4 = this.block2;
					this.block2 = preBlock2;
					
					this.state = 1;
					return true;
				}
			}
		}
		else {
			Block preBlock1 = new Block(block1.getX(),block1.getY(),block1.getColor());
			Block preBlock4 = new Block(block4.getX(),block4.getY(),block4.getColor());
			
			preBlock1.moveDown();
			preBlock4.moveRight();
			
			if(preBlock1.moveDown() && preBlock4.moveUp()) {
				if(!this.structure.containsBlock(preBlock1) &&
						!this.structure.containsBlock(preBlock4)) {
					this.block1 = preBlock1;
					this.block2 = this.block4;
					this.block4 = preBlock4;

					this.state = 0;
					return true;
				}
			}
		}
		
		return false;
	}

}

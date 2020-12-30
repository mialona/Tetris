package domain;

import org.eclipse.swt.SWT;

public class TetrominoT extends Tetromino {

	private int state;
	
	public TetrominoT(Structure structure) {
		super(new Block(3, 0, SWT.COLOR_MAGENTA), new Block(4, 0, SWT.COLOR_MAGENTA),
				new Block(5, 0, SWT.COLOR_MAGENTA), new Block(4, 1, SWT.COLOR_MAGENTA), structure);
		
		this.state = 0;
	}
	
	@Override
	public boolean rotate() {
		Block preBlock3 = new Block(block3.getX(),block3.getY(),block3.getColor());
		
		if(this.state == 0) {
			preBlock3.moveUp();
			
			if(preBlock3.moveLeft()) {
				if(!this.structure.containsBlock(preBlock3)) {
					this.block3 = this.block4;
					this.block4 = this.block1;
					this.block1 = preBlock3;

					this.state = 1;
					return true;
				}
			}
		}
		else if(this.state == 1) {
			preBlock3.moveRight();
			
			if(preBlock3.moveUp()) {
				if(!this.structure.containsBlock(preBlock3)) {
					this.block3 = this.block4;
					this.block4 = this.block1;
					this.block1 = preBlock3;

					this.state = 2;
					return true;
				}
			}
		}
		else if(this.state == 2) {
			preBlock3.moveDown();
			
			if(preBlock3.moveRight()) {
				if(!this.structure.containsBlock(preBlock3)) {
					this.block3 = this.block4;
					this.block4 = this.block1;
					this.block1 = preBlock3;

					this.state = 3;
					return true;
				}
			}
		}
		else {
			preBlock3.moveLeft();
			
			if(preBlock3.moveDown()) {
				if(!this.structure.containsBlock(preBlock3)) {
					this.block3 = this.block4;
					this.block4 = this.block1;
					this.block1 = preBlock3;

					this.state = 0;
					return true;
				}
			}
		}
		
		return false;
	}

}

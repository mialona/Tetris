package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Tetromino {

	protected Block block1;
	protected Block block2;
	protected Block block3;
	protected Block block4;
	protected Structure structure;
	
	public Tetromino(Block block1, Block block2, Block block3, Block block4, Structure structure) {
		this.block1 = block1;
		this.block2 = block2;
		this.block3 = block3;
		this.block4 = block4;
		this.structure = structure;
	}
	
	public List<Block> getBlocks() {
		List<Block> blocks = new ArrayList<Block>();
		
		blocks.add(this.block1);
		blocks.add(this.block2);
		blocks.add(this.block3);
		blocks.add(this.block4);
		
		return blocks;
	}
	
	public boolean moveDown() {
		Block preBlock1 = new Block(block1.getX(),block1.getY(),block1.getColor());
		Block preBlock2 = new Block(block2.getX(),block2.getY(),block2.getColor());
		Block preBlock3 = new Block(block3.getX(),block3.getY(),block3.getColor());
		Block preBlock4 = new Block(block4.getX(),block4.getY(),block4.getColor());
		
		if(preBlock1.moveDown() && preBlock2.moveDown() && preBlock3.moveDown() && preBlock4.moveDown()) {
			if(!this.structure.containsBlock(preBlock1) && !this.structure.containsBlock(preBlock2) &&
					!this.structure.containsBlock(preBlock3) && !this.structure.containsBlock(preBlock4)) {
				this.block1 = preBlock1;
				this.block2 = preBlock2;
				this.block3 = preBlock3;
				this.block4 = preBlock4;
				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean moveRight() {
		Block preBlock1 = new Block(block1.getX(),block1.getY(),block1.getColor());
		Block preBlock2 = new Block(block2.getX(),block2.getY(),block2.getColor());
		Block preBlock3 = new Block(block3.getX(),block3.getY(),block3.getColor());
		Block preBlock4 = new Block(block4.getX(),block4.getY(),block4.getColor());
		
		if(preBlock1.moveRight() && preBlock2.moveRight() && preBlock3.moveRight() && preBlock4.moveRight()) {
			if(!this.structure.containsBlock(preBlock1) && !this.structure.containsBlock(preBlock2) &&
					!this.structure.containsBlock(preBlock3) && !this.structure.containsBlock(preBlock4)) {
				this.block1 = preBlock1;
				this.block2 = preBlock2;
				this.block3 = preBlock3;
				this.block4 = preBlock4;
				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean moveLeft() {
		Block preBlock1 = new Block(block1.getX(),block1.getY(),block1.getColor());
		Block preBlock2 = new Block(block2.getX(),block2.getY(),block2.getColor());
		Block preBlock3 = new Block(block3.getX(),block3.getY(),block3.getColor());
		Block preBlock4 = new Block(block4.getX(),block4.getY(),block4.getColor());
		
		if(preBlock1.moveLeft() && preBlock2.moveLeft() && preBlock3.moveLeft() && preBlock4.moveLeft()) {
			if(!this.structure.containsBlock(preBlock1) && !this.structure.containsBlock(preBlock2) &&
					!this.structure.containsBlock(preBlock3) && !this.structure.containsBlock(preBlock4)) {
				this.block1 = preBlock1;
				this.block2 = preBlock2;
				this.block3 = preBlock3;
				this.block4 = preBlock4;
				
				return true;
			}
		}
		
		return false;
	}
	
	public abstract boolean rotate();
	
	public void addToStructure() {
		this.structure.addBlock(this.block1);
		this.structure.addBlock(this.block2);
		this.structure.addBlock(this.block3);
		this.structure.addBlock(this.block4);
	}
	
}

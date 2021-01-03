package business;

import java.util.ArrayList;
import java.util.List;

import domain.*;

public class Player {

	private Structure structure;
	private Tetromino tetromino;
	private Tetromino nextTetromino;
	private long score;
	private boolean outOfLimit;
	private boolean modified;
	
	public Player(TetrominoType firstTetromino) {
		this.structure = new Structure();
		this.tetromino = this.buildTetromino(firstTetromino);
		this.score = 0;
		this.outOfLimit = false;
		this.modified = true;
	}
	
	public synchronized void setNextTetromino(TetrominoType tetromino) {
		this.nextTetromino = this.buildTetromino(tetromino);
		this.modified = true;
	}
	
	public synchronized List<Block> getBlocksNextTetromino() {
		return this.nextTetromino.getBlocks();
	}
	
	public synchronized List<Block> getBlocks() {
		List<Block> blocks = new ArrayList<Block>(this.structure.getBlocks());
		
		blocks.addAll(this.tetromino.getBlocks());
		
		return blocks;
	}
	
	public synchronized long getScore() {
		return this.score;
	}
	
	public synchronized boolean isOutOfLimit() {
		return this.outOfLimit;
	}
	
	public synchronized boolean isModified() {
		return this.modified;
	}
	
	public synchronized void updated() {
		this.modified = false;
	}
	
	public synchronized boolean moveDown() {
		if(!this.tetromino.moveDown()) {
			this.tetromino.addToStructure();
			this.tetromino = this.nextTetromino;
			
			long rows = this.structure.deleteRows();
			this.score += rows*(20+rows*10);
			if(rows > 0) {
				this.modified = true;
			}
			
			this.outOfLimit = this.structure.outOfLimit();
			
			return false;
		}
		
		this.modified = true;
		return true;
	}
	
	public synchronized void moveRight() {
		if(this.tetromino.moveRight()) {
			this.modified = true;
		}
	}
	
	public synchronized void moveLeft() {
		if(this.tetromino.moveLeft()) {
			this.modified = true;
		}
	}
	
	public synchronized void rotate() {
		if(this.tetromino.rotate()) {
			this.modified = true;
		}
	}
	
	private synchronized Tetromino buildTetromino(TetrominoType tetromino) {
		switch (tetromino) {
			case I:
				return new TetrominoI(structure);
			case O:
				return new TetrominoO(structure);
			case T:
				return new TetrominoT(structure);
			case L:
				return new TetrominoL(structure);
			case J:
				return new TetrominoJ(structure);
			case S:
				return new TetrominoS(structure);
			default:
				return new TetrominoZ(structure);
		}
	}
	
}

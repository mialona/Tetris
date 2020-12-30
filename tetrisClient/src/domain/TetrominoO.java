package domain;

import org.eclipse.swt.SWT;

public class TetrominoO extends Tetromino {

	public TetrominoO(Structure structure) {
		super(new Block(4, 0, SWT.COLOR_YELLOW), new Block(5, 0, SWT.COLOR_YELLOW),
				new Block(4, 1, SWT.COLOR_YELLOW), new Block(5, 1, SWT.COLOR_YELLOW), structure);
	}

	@Override
	public boolean rotate() {
		return true;
	}

}

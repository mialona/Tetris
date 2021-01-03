package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;

import business.SingleGame;
import domain.Block;

public class PlayerCanvas extends Canvas {

	private SingleGame singleGame;
	private Color orange;

	public PlayerCanvas(Composite parent, int style, SingleGame singleGame) {
		super(parent, style);
		this.singleGame = singleGame;
		this.orange = new Color(this.getParent().getDisplay(), 255, 165, 0);

		this.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				paintCanvas(e);
			}
		});
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				keyHandler(e);
			}
		});
	}

	private void paintCanvas(PaintEvent e) {
		e.gc.setBackground(this.getParent().getDisplay().getSystemColor(SWT.COLOR_BLACK));
		e.gc.fillRectangle(20, 20, 300, 600);

		int size = 30;
		for (Block block : this.singleGame.getPlayerBlocks(0)) {
			if (block.getColor() == SWT.COLOR_DARK_YELLOW) {
				e.gc.setBackground(this.orange);
			} else {
				e.gc.setBackground(this.getParent().getDisplay().getSystemColor(block.getColor()));
			}

			e.gc.fillRectangle(21 + size * block.getX(), 21 + size * block.getY(), size - 2, size - 2);
		}
	}

	private void keyHandler(KeyEvent e) {
		switch(e.keyCode) {
			case SWT.ARROW_UP:
				this.singleGame.rotatePlayer(0);
				break;
			case SWT.ARROW_DOWN:
				this.singleGame.moveDownPlayer(0);
				break;
			case SWT.ARROW_RIGHT:
				this.singleGame.moveRightPlayer(0);
				break;
			case SWT.ARROW_LEFT:
				this.singleGame.moveLeftPlayer(0);
				break;
			case SWT.ESC:
					MessageBox pause = new MessageBox(this.getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
						pause.setText("Paused");
						pause.setMessage("Continue game?");
						
						this.singleGame.stop();
						if(pause.open() == SWT.YES) {
							this.singleGame.start();
						}
				break;
		}
	}

	public Point computeSize(int widthHint, int heightHint, boolean changed) {
		Point initialSize = super.computeSize(widthHint, heightHint, changed);
		
		initialSize.x = 340;
		initialSize.y = 640;
		
		return initialSize;
	}

}

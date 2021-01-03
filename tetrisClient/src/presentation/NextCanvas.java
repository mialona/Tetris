package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import business.SingleGame;
import domain.Block;

public class NextCanvas extends Canvas {

	private SingleGame singleGame;
	private Color orange;

	public NextCanvas(Composite parent, int style, SingleGame singleGame) {
		super(parent, style);
		this.singleGame = singleGame;
		this.orange = new Color(this.getParent().getDisplay(), 255, 165, 0);

		this.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				paintCanvas(e);
			}
		});
	}

	private void paintCanvas(PaintEvent e) {
		int size = 30;
		
		e.gc.setBackground(this.getParent().getDisplay().getSystemColor(SWT.COLOR_BLACK));
		e.gc.fillRectangle(0, 0, 140, 100);

		for (Block block : this.singleGame.getPlayerBlocksNext(0)) {
			if (block.getColor() == SWT.COLOR_DARK_YELLOW) {
				e.gc.setBackground(this.orange);
			} else {
				e.gc.setBackground(this.getParent().getDisplay().getSystemColor(block.getColor()));
			}

			if (block.getColor() == SWT.COLOR_CYAN) {
				e.gc.fillRectangle(11 + size * (block.getX() - 3), 36 + size * block.getY(), size - 2, size - 2);
			} else if (block.getColor() == SWT.COLOR_YELLOW) {
				e.gc.fillRectangle(11 + size * (block.getX() - 3), 21 + size * block.getY(), size - 2, size - 2);
			} else {
				e.gc.fillRectangle(26 + size * (block.getX() - 3), 21 + size * block.getY(), size - 2, size - 2);
			}
		}
	}

	public Point computeSize(int widthHint, int heightHint, boolean changed) {
		Point initialSize = super.computeSize(widthHint, heightHint, changed);
		
		initialSize.x = 140;
		initialSize.y = 100;
		
		return initialSize;
	}

}

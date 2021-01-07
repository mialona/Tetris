package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import business.OnlineGame;
import domain.Block;

public class OnlinePlayerCanvas extends Canvas{

	private OnlineGame game;
	private int playerNumber;
	private Color orange;

	public OnlinePlayerCanvas(Composite parent, int style, OnlineGame game, int playerNumber) {
		super(parent, style);
		this.game = game;
		this.playerNumber = playerNumber;
		this.orange = new Color(this.getParent().getDisplay(), 255, 165, 0);

		this.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				paintCanvas(e);
			}
		});
	}

	private void paintCanvas(PaintEvent e) {
		e.gc.setBackground(this.getParent().getDisplay().getSystemColor(SWT.COLOR_BLACK));
		e.gc.fillRectangle(0, 0, 120, 240);

		int size = 12;
		for (Block block : this.game.getOnlinePlayerBlocks(this.playerNumber)) {
			if (block.getColor() == SWT.COLOR_DARK_YELLOW) {
				e.gc.setBackground(this.orange);
			} else {
				e.gc.setBackground(this.getParent().getDisplay().getSystemColor(block.getColor()));
			}

			e.gc.fillRectangle(1 + size * block.getX(), 1 + size * block.getY(), size - 2, size - 2);
		}
	}

	public Point computeSize(int widthHint, int heightHint, boolean changed) {
		Point initialSize = super.computeSize(widthHint, heightHint, changed);
		
		initialSize.x = 120;
		initialSize.y = 240;
		
		return initialSize;
	}

}

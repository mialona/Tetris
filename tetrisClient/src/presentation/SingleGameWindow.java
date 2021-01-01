package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import business.SingleGame;
import domain.Block;

public class SingleGameWindow {

	private Display display;
	private Shell shell;
	private Canvas canvas;
	private SingleGame singleGame;

	public SingleGameWindow(Display display) {
		this.display = display;
		
		this.shell = new Shell(this.display, SWT.SHELL_TRIM & (~SWT.RESIZE));
		shell.setText("Tetris");
        shell.setSize(560,700);
        shell.setLayout(new FillLayout());
        
        this.canvas = new Canvas(shell,SWT.NO_REDRAW_RESIZE);
        canvas.addPaintListener(new PaintListener(){
	        public void paintControl(PaintEvent e){
	        	paintCanvas(e);
	        }
	    });
        canvas.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				keyHandler(e);
			}
		});
        
        this.singleGame = new SingleGame();
	}
	
	public void launch() {
		this.singleGame.start();
		this.shell.open();
		
		while(!shell.isDisposed() && !this.singleGame.lose()) {
			display.readAndDispatch();
			
			if(this.singleGame.isPlayerModified()) {
				canvas.redraw();
				canvas.update();
				this.singleGame.updatedPlayer();
			}
		}
		
		this.singleGame.stop();
		this.shell.setVisible(false);
		this.shell.dispose();
	}
	
	private void paintCanvas(PaintEvent e) {
		e.gc.setFont(new Font(this.display,"Arial",14,SWT.BOLD | SWT.ITALIC));
    	e.gc.drawText("Next",360,20);
    	e.gc.drawText("Score",360,200);
    	e.gc.drawText("High score",360,270);
    	e.gc.setFont(new Font(this.display,"Arial",14,SWT.NONE));
    	e.gc.drawText("00:00",440,160);
    	e.gc.drawText(""+this.singleGame.getScore(),485,225);
    	e.gc.drawText("99999",435,295);
    	
    	e.gc.setBackground(this.display.getSystemColor(SWT.COLOR_BLACK));
        e.gc.fillRectangle(20,20,300,600);
        e.gc.fillRectangle(360,50,140,100); //+10 y +20 de borde al tetronimo
    	
        int size = 30;    	
    	for(Block block : this.singleGame.getPlayerBlocks()) {
    		e.gc.setBackground(this.display.getSystemColor(block.getColor()));
    		e.gc.fillRectangle(21+size*block.getX(),21+size*block.getY(),size-2,size-2);
    	}

    	for(Block block : this.singleGame.getPlayerBlocksNext()) {
    		e.gc.setBackground(this.display.getSystemColor(block.getColor()));
    		if(block.getColor() == SWT.COLOR_CYAN) {
    			e.gc.fillRectangle(371+size*(block.getX()-3),86+size*block.getY(),size-2,size-2);
    		}
    		else if(block.getColor() == SWT.COLOR_YELLOW) {
    			e.gc.fillRectangle(371+size*(block.getX()-3),71+size*block.getY(),size-2,size-2);
    		}
    		else {
    			e.gc.fillRectangle(386+size*(block.getX()-3),71+size*block.getY(),size-2,size-2);
    		}
    	}
	}
	
	private void keyHandler(KeyEvent e) {
		switch(e.keyCode) {
			case SWT.ARROW_UP:
				this.singleGame.rotatePlayer();;
				break;
			case SWT.ARROW_DOWN:
				this.singleGame.moveDownPlayer();
				break;
			case SWT.ARROW_RIGHT:
				this.singleGame.moveRightPlayer();
				break;
			case SWT.ARROW_LEFT:
				this.singleGame.moveLeftPlayer();
				break;
		}
	}
	
}

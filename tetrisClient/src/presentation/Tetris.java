package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public class Tetris {

	private Display display;
	private Shell shell;
	
	public Tetris() {
		this.display = new Display();
		
		this.shell = new Shell(display);
			this.shell.setSize(400,600);
			this.shell.setText("Tetris");
			
			Monitor primary = display.getPrimaryMonitor();
		    Rectangle screenBounds = primary.getBounds();
		    this.shell.setLocation(screenBounds.x+(screenBounds.width-400)/2,
		    		screenBounds.y+(screenBounds.height-600)/2);
		
		new MainMenu(this.shell, SWT.NONE);
	}
	
	public void launch() {
		this.shell.open();
		
		while(!this.shell.isDisposed()) {
			if(!this.display.readAndDispatch()) {
				this.display.sleep();
			}
		}
		
		this.display.dispose();
	}
	
}

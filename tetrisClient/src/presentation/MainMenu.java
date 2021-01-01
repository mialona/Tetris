package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MainMenu {

	private Display display;
	private Shell shell;
	private Composite composite; //setvisible false, dispose
	
	public MainMenu() {
		this.display = new Display();
		
		this.shell = new Shell(display);
		this.shell.setSize(new Point(400,600));
		shell.setText("Tetris");
		
		this.composite = this.setMainComposite(shell);
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
	
	private Composite setMainComposite(Shell shell) {
		Composite composite = new Composite(shell, SWT.NONE);
		
			Button buttonSingleGame = new Button(composite,SWT.PUSH);
			buttonSingleGame.setText("Un jugador");
			
			buttonSingleGame.addMouseListener(new MouseListener() {
				@Override
				public void mouseUp(MouseEvent e) {
					singleGameHandlerMouse();
				}

				@Override
				public void mouseDown(MouseEvent e) {}

				@Override
				public void mouseDoubleClick(MouseEvent e) {}
			});
			buttonSingleGame.pack();
		
		composite.pack();
		return composite;
	}
	
	private void singleGameHandlerMouse() {
		this.shell.setVisible(false);
		
		SingleGameWindow sgw = new SingleGameWindow(this.display);
		sgw.launch();
		
		this.shell.setVisible(true);
		this.shell.forceActive();
	}
	
}

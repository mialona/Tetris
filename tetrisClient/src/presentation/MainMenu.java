package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class MainMenu extends Composite {

	public MainMenu(Composite parent, int style) {
		super(parent, style);

		this.setLayout(new FormLayout());
		
		this.addPaintListener(new PaintListener(){
	        public void paintControl(PaintEvent e){
	        	paint(e);
	        }
	    });
		
		Label labelTetris = new Label(this,SWT.NONE);
			labelTetris.setBackground(this.getParent().getDisplay().getSystemColor(SWT.COLOR_BLACK));
			
	        FormData fdTetris = new FormData();
	        fdTetris.top = new FormAttachment(0, 50);
	        fdTetris.left = new FormAttachment(0, 40);
	        fdTetris.bottom = new FormAttachment(0, 140);
	        fdTetris.right = new FormAttachment(0, 345);
	        labelTetris.setLayoutData(fdTetris);

	        TextLayout layout = new TextLayout(this.getParent().getDisplay());
	        	layout.setText("TETRIS");
	        	
		        TextStyle textStyleT = new TextStyle(this.getParent().getDisplay().getSystemFont(),
		        		this.getParent().getDisplay().getSystemColor(SWT.COLOR_RED), null);
		        textStyleT.font = new Font(this.getParent().getDisplay(), "Arial", 50, SWT.BOLD | SWT.BORDER);
		        
		        TextStyle textStyleE = new TextStyle(this.getParent().getDisplay().getSystemFont(),
		        		new Color(this.getParent().getDisplay(), 255, 165, 0), null);
		        textStyleE.font = new Font(this.getParent().getDisplay(), "Arial", 50, SWT.BOLD);
		        
		        TextStyle textStyleT2 = new TextStyle(this.getParent().getDisplay().getSystemFont(),
		        		this.getParent().getDisplay().getSystemColor(SWT.COLOR_YELLOW), null);
		        textStyleT2.font = new Font(this.getParent().getDisplay(), "Arial", 50, SWT.BOLD);
		        
		        TextStyle textStyleR = new TextStyle(this.getParent().getDisplay().getSystemFont(),
		        		this.getParent().getDisplay().getSystemColor(SWT.COLOR_GREEN), null);
		        textStyleR.font = new Font(this.getParent().getDisplay(), "Arial", 50, SWT.BOLD);
		        
		        TextStyle textStyleI = new TextStyle(this.getParent().getDisplay().getSystemFont(),
		        		this.getParent().getDisplay().getSystemColor(SWT.COLOR_CYAN), null);
		        textStyleI.font = new Font(this.getParent().getDisplay(), "Arial", 50, SWT.BOLD);
		        
		        TextStyle textStyleS = new TextStyle(this.getParent().getDisplay().getSystemFont(),
		        		this.getParent().getDisplay().getSystemColor(SWT.COLOR_MAGENTA), null);
		        textStyleS.font = new Font(this.getParent().getDisplay(), "Arial", 50, SWT.BOLD);

	        labelTetris.addListener(SWT.Paint, new Listener() {
	            @Override
	            public void handleEvent(Event event) {
	                layout.setStyle(textStyleT, 0, 0);
	                layout.setStyle(textStyleE, 1, 1);
	                layout.setStyle(textStyleT2, 2, 2);
	                layout.setStyle(textStyleR, 3, 3);
	                layout.setStyle(textStyleI, 4, 4);
	                layout.setStyle(textStyleS, 5, 5);
	                
	                layout.draw(event.gc, event.x, event.y);
	            }
	        });
		
		Button buttonSingleGame = new Button(this, SWT.PUSH);
			buttonSingleGame.setFont(new Font(this.getParent().getDisplay(), "Arial", 14, SWT.NONE));
			buttonSingleGame.setText("Single game");
			buttonSingleGame.setAlignment(SWT.CENTER);
			
			FormData fdSingleGame = new FormData();
	        fdSingleGame.top = new FormAttachment(0, 300);
	        fdSingleGame.left = new FormAttachment(0, 85);
	        fdSingleGame.bottom = new FormAttachment(0, 345);
	        fdSingleGame.right = new FormAttachment(0, 300);
	        buttonSingleGame.setLayoutData(fdSingleGame);
			
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
			
		Button buttonCoopGame = new Button(this, SWT.PUSH);
			buttonCoopGame.setFont(new Font(this.getParent().getDisplay(), "Arial", 14, SWT.NONE));
			buttonCoopGame.setText("Co-op game");
			buttonCoopGame.setAlignment(SWT.CENTER);
			
			FormData fdCoopGame = new FormData();
	        fdCoopGame.top = new FormAttachment(0, 365);
	        fdCoopGame.left = new FormAttachment(0, 85);
	        fdCoopGame.bottom = new FormAttachment(0, 410);
	        fdCoopGame.right = new FormAttachment(0, 300);
	        buttonCoopGame.setLayoutData(fdCoopGame);
			
			buttonCoopGame.addMouseListener(new MouseListener() {
				@Override
				public void mouseUp(MouseEvent e) {
					coopGameHandlerMouse();
				}
	
				@Override
				public void mouseDown(MouseEvent e) {}
	
				@Override
				public void mouseDoubleClick(MouseEvent e) {}
			});
			buttonCoopGame.pack();
	
		this.pack();
	}
	
	private void paint(PaintEvent e) {
		e.gc.setBackground(this.getParent().getDisplay().getSystemColor(SWT.COLOR_BLACK));
        e.gc.fillRectangle(30,50,316,96);
        e.gc.fillRectangle(145,146,96,96);
	}
	
	private void singleGameHandlerMouse() {
		this.getParent().setVisible(false);
		
		SingleGameWindow sgw = new SingleGameWindow(this.getParent().getDisplay());
		sgw.launch();
		
		this.getParent().setVisible(true);
		((Shell)this.getParent()).forceActive();
	}
	
	private void coopGameHandlerMouse() {
		this.getParent().setVisible(false);
		
		CoopGameWindow cgw = new CoopGameWindow(this.getParent().getDisplay());
		cgw.launch();
		
		this.getParent().setVisible(true);
		((Shell)this.getParent()).forceActive();
	}

}

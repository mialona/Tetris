package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class OnlineMenu extends Composite {
	
	public OnlineMenu(Composite parent, int style) {
		super(parent, style);

		this.setLayout(new FormLayout());

		Label labelOnline = new Label(this,SWT.NONE);
			labelOnline.setFont(new Font(this.getParent().getDisplay(), "Arial", 50, SWT.BOLD));
			labelOnline.setText("Online");
			
	        FormData fdOnline = new FormData();
	        fdOnline.top = new FormAttachment(0, 60);
	        fdOnline.left = new FormAttachment(0, 60);
	        fdOnline.bottom = new FormAttachment(0, 140);
	        fdOnline.right = new FormAttachment(0, 345);
	        labelOnline.setLayoutData(fdOnline);
	        
		Label labelGame = new Label(this,SWT.NONE);
			labelGame.setFont(new Font(this.getParent().getDisplay(), "Arial", 50, SWT.BOLD));
			labelGame.setText("game");
			
	        FormData fdGame = new FormData();
	        fdGame.top = new FormAttachment(0, 135);
	        fdGame.left = new FormAttachment(0, 82);
	        fdGame.bottom = new FormAttachment(0, 250);
	        fdGame.right = new FormAttachment(0, 345);
	        labelGame.setLayoutData(fdGame);
		
		Button buttonCreateGame = new Button(this, SWT.PUSH);
			buttonCreateGame.setFont(new Font(this.getParent().getDisplay(), "Arial", 14, SWT.NONE));
			buttonCreateGame.setText("Create game");
			buttonCreateGame.setAlignment(SWT.CENTER);
			
			FormData fdCreateGame = new FormData();
	        fdCreateGame.top = new FormAttachment(0, 300);
	        fdCreateGame.left = new FormAttachment(0, 85);
	        fdCreateGame.bottom = new FormAttachment(0, 345);
	        fdCreateGame.right = new FormAttachment(0, 300);
	        buttonCreateGame.setLayoutData(fdCreateGame);
			
			buttonCreateGame.addMouseListener(new MouseListener() {
				@Override
				public void mouseUp(MouseEvent e) {
					createGameHandlerMouse();
				}
	
				@Override
				public void mouseDown(MouseEvent e) {}
	
				@Override
				public void mouseDoubleClick(MouseEvent e) {}
			});
			buttonCreateGame.pack();
			
		Button buttonJoinGame = new Button(this, SWT.PUSH);
			buttonJoinGame.setFont(new Font(this.getParent().getDisplay(), "Arial", 14, SWT.NONE));
			buttonJoinGame.setText("Join game");
			buttonJoinGame.setAlignment(SWT.CENTER);
			
			FormData fdJoinGame = new FormData();
	        fdJoinGame.top = new FormAttachment(0, 365);
	        fdJoinGame.left = new FormAttachment(0, 85);
	        fdJoinGame.bottom = new FormAttachment(0, 410);
	        fdJoinGame.right = new FormAttachment(0, 300);
	        buttonJoinGame.setLayoutData(fdJoinGame);
			
			buttonJoinGame.addMouseListener(new MouseListener() {
				@Override
				public void mouseUp(MouseEvent e) {
					joinGameHandlerMouse();
				}
	
				@Override
				public void mouseDown(MouseEvent e) {}
	
				@Override
				public void mouseDoubleClick(MouseEvent e) {}
			});
			buttonJoinGame.pack();
			
		Button buttonBack = new Button(this, SWT.PUSH);
			buttonBack.setFont(new Font(this.getParent().getDisplay(), "Arial", 14, SWT.NONE));
			buttonBack.setText("Back");
			buttonBack.setAlignment(SWT.CENTER);
			buttonBack.setBackground(this.getParent().getDisplay().getSystemColor(SWT.COLOR_GRAY));
			
			FormData fdBack = new FormData();
	        fdBack.top = new FormAttachment(0, 430);
	        fdBack.left = new FormAttachment(0, 85);
	        fdBack.bottom = new FormAttachment(0, 475);
	        fdBack.right = new FormAttachment(0, 300);
	        buttonBack.setLayoutData(fdBack);
			
			buttonBack.addMouseListener(new MouseListener() {
				@Override
				public void mouseUp(MouseEvent e) {
					backHandlerMouse();
				}
	
				@Override
				public void mouseDown(MouseEvent e) {}
	
				@Override
				public void mouseDoubleClick(MouseEvent e) {}
			});
			buttonBack.pack();
	
		this.pack();
	}
	
	private void createGameHandlerMouse() {
		this.getParent().setVisible(false);
		
		CreateOnlineWindow cow = new CreateOnlineWindow(this.getParent().getDisplay(),
				this.getShell().getLocation().x, this.getShell().getLocation().y);
		cow.launch();
		
		this.getParent().setVisible(true);
		((Shell)this.getParent()).forceActive();
	}
	
	private void joinGameHandlerMouse() {
		this.getParent().setVisible(false);
		
		JoinOnlineWindow jow = new JoinOnlineWindow(this.getParent().getDisplay(),
				this.getShell().getLocation().x, this.getShell().getLocation().y);
		jow.launch();
		
		this.getParent().setVisible(true);
		((Shell)this.getParent()).forceActive();
	}
	
	private void backHandlerMouse() {
		this.setVisible(false);
		
		new MainMenu(this.getParent(), SWT.NONE);
		
		this.dispose();
	}

}
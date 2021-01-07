package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import business.OnlineGame;
import business.OnlineGameManager;

public class CreateOnlineWindow{

	private Display display;
	private Shell shell;
	private Text textAddress;
	private Combo comboNumPlayers;
	private Text textDescription;
	private Button buttonCreate;
	private OnlineGameManager ogm;
	private OnlineGame onlineGame;
	
	public CreateOnlineWindow(Display display, int xLocation, int yLocation) {
		this.display = display;
		
		this.shell = new Shell(this.display, SWT.TITLE);
			this.shell.setSize(400,600);
			this.shell.setText("Tetris");
			this.shell.setLayout(new FormLayout());
			this.shell.setLocation(xLocation,yLocation);

		Label labelCreateGame = new Label(this.shell,SWT.NONE);
			labelCreateGame.setFont(new Font(this.display, "Arial", 20, SWT.BOLD));
			labelCreateGame.setText("Create game");
			labelCreateGame.setAlignment(SWT.CENTER);
			
	        FormData fdCreateGame = new FormData();
	        fdCreateGame.top = new FormAttachment(0, 30);
	        fdCreateGame.left = new FormAttachment(0, 45);
	        fdCreateGame.bottom = new FormAttachment(0, 70);
	        fdCreateGame.right = new FormAttachment(0, 345);
	        labelCreateGame.setLayoutData(fdCreateGame);
	        
		Label labelAddress = new Label(this.shell,SWT.NONE);
			labelAddress.setFont(new Font(this.display, "Arial", 12, SWT.BOLD));
			labelAddress.setText("Address:");
			
	        FormData fdLabelAddress = new FormData();
	        fdLabelAddress.top = new FormAttachment(0, 135);
	        fdLabelAddress.left = new FormAttachment(0, 60);
	        fdLabelAddress.bottom = new FormAttachment(0, 160);
	        fdLabelAddress.right = new FormAttachment(0, 150);
	        labelAddress.setLayoutData(fdLabelAddress);
	        
		this.textAddress = new Text(this.shell,SWT.NONE);
			textAddress.setFont(new Font(this.display, "Arial", 12, SWT.NONE));
			
	        FormData fdTextAddress = new FormData();
	        fdTextAddress.top = new FormAttachment(0, 135);
	        fdTextAddress.left = new FormAttachment(0, 150);
	        fdTextAddress.bottom = new FormAttachment(0, 160);
	        fdTextAddress.right = new FormAttachment(0, 345);
	        textAddress.setLayoutData(fdTextAddress);
	        
		Label labelNumPlayers = new Label(this.shell,SWT.NONE);
			labelNumPlayers.setFont(new Font(this.display, "Arial", 12, SWT.BOLD));
			labelNumPlayers.setText("Players:");
			
	        FormData fdLabelNumPlayers = new FormData();
	        fdLabelNumPlayers.top = new FormAttachment(0, 200);
	        fdLabelNumPlayers.left = new FormAttachment(0, 67);
	        fdLabelNumPlayers.bottom = new FormAttachment(0, 225);
	        fdLabelNumPlayers.right = new FormAttachment(0, 150);
	        labelNumPlayers.setLayoutData(fdLabelNumPlayers);
	        
		this.comboNumPlayers = new Combo(this.shell,SWT.NONE);
			comboNumPlayers.setFont(new Font(this.display, "Arial", 12, SWT.NONE));

			comboNumPlayers.add("2");
			comboNumPlayers.add("3");
			comboNumPlayers.add("4");
			comboNumPlayers.select(0);
			
	        FormData fdComboNumPlayers = new FormData();
	        fdComboNumPlayers.top = new FormAttachment(0, 200);
	        fdComboNumPlayers.left = new FormAttachment(0, 150);
	        fdComboNumPlayers.bottom = new FormAttachment(0, 225);
	        fdComboNumPlayers.right = new FormAttachment(0, 200);
	        comboNumPlayers.setLayoutData(fdComboNumPlayers);
	        
		Label labelDescription = new Label(this.shell,SWT.NONE);
			labelDescription.setFont(new Font(this.display, "Arial", 12, SWT.BOLD));
			labelDescription.setText("Description:");
			
	        FormData fdLabelDescription = new FormData();
	        fdLabelDescription.top = new FormAttachment(0, 265);
	        fdLabelDescription.left = new FormAttachment(0, 30);
	        fdLabelDescription.bottom = new FormAttachment(0, 290);
	        fdLabelDescription.right = new FormAttachment(0, 150);
	        labelDescription.setLayoutData(fdLabelDescription);
	        
		this.textDescription = new Text(this.shell,SWT.NONE);
			textDescription.setFont(new Font(this.display, "Arial", 12, SWT.NONE));
			
	        FormData fdTextDescription = new FormData();
	        fdTextDescription.top = new FormAttachment(0, 265);
	        fdTextDescription.left = new FormAttachment(0, 150);
	        fdTextDescription.bottom = new FormAttachment(0, 290);
	        fdTextDescription.right = new FormAttachment(0, 345);
	        textDescription.setLayoutData(fdTextDescription);
			
		this.buttonCreate = new Button(this.shell, SWT.PUSH);
			buttonCreate.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
			buttonCreate.setText("Create");
			buttonCreate.setAlignment(SWT.CENTER);
			
			FormData fdCreate = new FormData();
	        fdCreate.top = new FormAttachment(0, 365);
	        fdCreate.left = new FormAttachment(0, 85);
	        fdCreate.bottom = new FormAttachment(0, 410);
	        fdCreate.right = new FormAttachment(0, 300);
	        buttonCreate.setLayoutData(fdCreate);
			
			buttonCreate.addMouseListener(new MouseListener() {
				@Override
				public void mouseUp(MouseEvent e) {
					createHandlerMouse();
				}
	
				@Override
				public void mouseDown(MouseEvent e) {}
	
				@Override
				public void mouseDoubleClick(MouseEvent e) {}
			});
			buttonCreate.pack();
			
		Button buttonBack = new Button(this.shell, SWT.PUSH);
			buttonBack.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
			buttonBack.setText("Back");
			buttonBack.setAlignment(SWT.CENTER);
			buttonBack.setBackground(this.display.getSystemColor(SWT.COLOR_GRAY));
			
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
	}
	
	public void launch() {
		this.shell.open();
		
		while(!this.shell.isDisposed()) {
			if(!this.display.readAndDispatch()) {
				this.display.sleep();
			}
			
			if(this.ogm != null) {
				if(this.ogm.isStartState()) {
					if(this.onlineGame != null) {
						this.shell.setVisible(false);
						
						OnlineGameWindow sgw = new OnlineGameWindow(this.display, this.onlineGame,
								this.shell.getLocation().x+this.shell.getSize().x/2,
								this.shell.getLocation().y+this.shell.getSize().y/2);
						sgw.launch();
						
						try {
							this.ogm.closeConnection();
						} catch (Exception e) {
							e.printStackTrace();
						}
						this.shell.dispose();
					}
					else {
						showError();
					}
				}
				else if(this.ogm.isErrorState()) {
					showError();
				}
			}
		}
	}
	
	private void createHandlerMouse() {
		if(this.ogm == null) {
			if(this.textAddress.getText().contains(":")) {
				String[] slices = this.textAddress.getText().split(":");
				this.ogm = new OnlineGameManager(slices[0], Integer.parseInt(slices[1]));
			}
			else {
				this.ogm = new OnlineGameManager(this.textAddress.getText(), 3300);
			}
			this.onlineGame = this.ogm.create(Integer.parseInt(this.comboNumPlayers.getText()), this.textDescription.getText());
			
			if(this.onlineGame != null) {
				this.buttonCreate.setText("Waitting...");
				
			}
		}
	}

	private void backHandlerMouse() {
		if(this.ogm != null) {
			try {
				this.ogm.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		this.shell.dispose();
	}
	
	private void showError() {
		if(!this.shell.isDisposed()) {
			MessageBox error = new MessageBox(this.shell, SWT.ICON_ERROR | SWT.OK);
			error.setText("Error");
			error.setMessage("An error has occurred.");
			
			error.open();
		
			this.buttonCreate.setText("Create");
		}
		
		this.onlineGame = null;
		
		if(this.ogm != null) {
			try {
				this.ogm.closeConnection();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			this.ogm = null;
		}
	}

}
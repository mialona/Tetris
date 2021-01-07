package presentation;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import business.OnlineGame;
import business.OnlineGameManager;

public class JoinOnlineWindow{

	private Display display;
	private Shell shell;
	private Text textAddress;
	private Table table;
	private Button buttonJoin;
	private OnlineGameManager ogm;
	private OnlineGame onlineGame;
	
	public JoinOnlineWindow(Display display, int xLocation, int yLocation) {
		this.display = display;
		
		this.shell = new Shell(this.display, SWT.TITLE);
			this.shell.setSize(400,600);
			this.shell.setText("Tetris");
			this.shell.setLayout(new FormLayout());
			this.shell.setLocation(xLocation, yLocation);

		Label labelJoinGame = new Label(this.shell,SWT.NONE);
			labelJoinGame.setFont(new Font(this.display, "Arial", 20, SWT.BOLD));
			labelJoinGame.setText("Join game");
			labelJoinGame.setAlignment(SWT.CENTER);
			
	        FormData fdJoinGame = new FormData();
	        fdJoinGame.top = new FormAttachment(0, 30);
	        fdJoinGame.left = new FormAttachment(0, 45);
	        fdJoinGame.bottom = new FormAttachment(0, 70);
	        fdJoinGame.right = new FormAttachment(0, 345);
	        labelJoinGame.setLayoutData(fdJoinGame);
	        
		this.textAddress = new Text(this.shell,SWT.NONE);
			textAddress.setFont(new Font(this.display, "Arial", 12, SWT.NONE));
			
	        FormData fdTextAddress = new FormData();
	        fdTextAddress.top = new FormAttachment(0, 100);
	        fdTextAddress.left = new FormAttachment(0, 50);
	        fdTextAddress.bottom = new FormAttachment(0, 125);
	        fdTextAddress.right = new FormAttachment(0, 245);
	        textAddress.setLayoutData(fdTextAddress);
			
		Button buttonSearch = new Button(this.shell, SWT.PUSH);
			buttonSearch.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
			buttonSearch.setText("Search");
			buttonSearch.setAlignment(SWT.CENTER);
			
			FormData fdSearch = new FormData();
	        fdSearch.top = new FormAttachment(0, 100);
	        fdSearch.left = new FormAttachment(0, 245);
	        fdSearch.bottom = new FormAttachment(0, 125);
	        fdSearch.right = new FormAttachment(0, 350);
	        buttonSearch.setLayoutData(fdSearch);
			
			buttonSearch.addMouseListener(new MouseListener() {
				@Override
				public void mouseUp(MouseEvent e) {
					searchHandlerMouse();
				}
	
				@Override
				public void mouseDown(MouseEvent e) {}
	
				@Override
				public void mouseDoubleClick(MouseEvent e) {}
			});
			buttonSearch.pack();
			
		this.table = new Table(this.shell, SWT.BORDER | SWT.H_SCROLL);
			table.setHeaderVisible(true);
	
			TableColumn columnDate = new TableColumn(table, SWT.NULL);
			columnDate.setText("Id");
			TableColumn columnScore = new TableColumn(table, SWT.NULL);
			columnScore.setText("Current players");
			TableColumn columnTime = new TableColumn(table, SWT.NULL);
			columnTime.setText("Max players");
			TableColumn columnDescription = new TableColumn(table, SWT.NULL);
			columnDescription.setText("Description");
	
			FormData fdTable = new FormData();
			fdTable.top = new FormAttachment(0, 130);
			fdTable.left = new FormAttachment(0, 20);
			fdTable.bottom = new FormAttachment(0, 350);
			fdTable.right = new FormAttachment(0, 360);
			table.setLayoutData(fdTable);

			for (int i = 0; i < 4; i++) {
				this.table.getColumn(i).pack();
			}
			
		this.buttonJoin = new Button(this.shell, SWT.PUSH);
			buttonJoin.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
			buttonJoin.setText("Join");
			buttonJoin.setAlignment(SWT.CENTER);
			
			FormData fdJoin = new FormData();
	        fdJoin.top = new FormAttachment(0, 365);
	        fdJoin.left = new FormAttachment(0, 85);
	        fdJoin.bottom = new FormAttachment(0, 410);
	        fdJoin.right = new FormAttachment(0, 300);
	        buttonJoin.setLayoutData(fdJoin);
			
	        buttonJoin.addMouseListener(new MouseListener() {
				@Override
				public void mouseUp(MouseEvent e) {
					joinHandlerMouse();
				}
	
				@Override
				public void mouseDown(MouseEvent e) {}
	
				@Override
				public void mouseDoubleClick(MouseEvent e) {}
			});
			buttonJoin.pack();
			
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
	
	private void searchHandlerMouse() {
		OnlineGameManager ogm = null;
		
		if(this.textAddress.getText().contains(":")) {
			String[] slices = this.textAddress.getText().split(":");
			ogm = new OnlineGameManager(slices[0], Integer.parseInt(slices[1]));
		}
		else {
			ogm = new OnlineGameManager(this.textAddress.getText(), 3300);
		}
		
		List<String> infoList = ogm.getOnlineGamesInfo();
		
		this.table.removeAll();
		for(String info : infoList) {
			String[] splices = info.split(",");
			TableItem item = new TableItem(this.table, SWT.NULL);
				item.setText(0, splices[0]);
				item.setText(1, splices[1]);
				item.setText(2, splices[2]);
				item.setText(3, splices[3]);
		}

		for (int i = 0; i < 4; i++) {
			this.table.getColumn(i).pack();
		}
		
		if(ogm.isErrorState()) {
			showError();
		}
	}
	
	private void joinHandlerMouse() {
		int selection = this.table.getSelectionIndex();
		
		if((this.ogm == null)&&(selection != -1)) {
			if(this.textAddress.getText().contains(":")) {
				String[] slices = this.textAddress.getText().split(":");
				this.ogm = new OnlineGameManager(slices[0], Integer.parseInt(slices[1]));
			}
			else {
				this.ogm = new OnlineGameManager(this.textAddress.getText(), 3300);
			}
			this.onlineGame = this.ogm.connect(Integer.parseInt(this.table.getItem(selection).getText(0)),
					Integer.parseInt(this.table.getItem(selection).getText(2)));
			
			if(this.onlineGame != null) {
				this.buttonJoin.setText("Waitting...");
				
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
		
			this.buttonJoin.setText("Join");
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
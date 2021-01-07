package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import business.OnlineGame;

public class OnlineGameWindow {
	private Display display;
	private Shell shell;
	private OnlineGame onlineGame;
	private PlayerCanvas playerCanvas;
	private NextCanvas nextCanvas;
	private Label time;
	private Label score;
	private OnlinePlayerCanvas onlineplayer0Canvas;
	private Label scoreOnlinePlayer0;
	private OnlinePlayerCanvas onlineplayer1Canvas;
	private Label scoreOnlinePlayer1;
	private OnlinePlayerCanvas onlineplayer2Canvas;
	private Label scoreOnlinePlayer2;

	public OnlineGameWindow(Display display, OnlineGame onlineGame, int xCenteredLocation, int yCenteredLocation) {
		this.display = display;
        this.onlineGame = onlineGame;
		
		this.shell = new Shell(this.display, SWT.TITLE);
			this.shell.setText("Tetris - Online game");
			this.shell.setLayout(new FormLayout());
			
			if(this.onlineGame.getNumOnlinePlayers()>1) {
				this.shell.setSize(720,700);
				this.shell.setLocation(xCenteredLocation-360, yCenteredLocation-350);
			}
			else {
				this.shell.setSize(560,700);
				this.shell.setLocation(xCenteredLocation-280, yCenteredLocation-350);
			}
        
        this.playerCanvas = new PlayerCanvas(this.shell, SWT.NO_REDRAW_RESIZE, this.onlineGame, 0);
	        FormData fdPlayerCanvas = new FormData();
	        fdPlayerCanvas.top = new FormAttachment(0, 0);
	        fdPlayerCanvas.left = new FormAttachment(0, 0);
	        fdPlayerCanvas.bottom = new FormAttachment(0, 640);
	        fdPlayerCanvas.right = new FormAttachment(0, 340);
	        this.playerCanvas.setLayoutData(fdPlayerCanvas);
	        
		    this.playerCanvas.addKeyListener(new KeyListener() {

				@Override
				public void keyReleased(KeyEvent e) {}

				@Override
				public void keyPressed(KeyEvent e) {
					keyHandler(e);
				}
			});

        Label labelNext = new Label(this.shell,SWT.NONE);
        	labelNext.setFont(new Font(this.display, "Arial", 14, SWT.BOLD | SWT.ITALIC));
        	labelNext.setText("Next");
	        FormData fdNext = new FormData();
	        fdNext.top = new FormAttachment(0, 20);
	        fdNext.left = new FormAttachment(0, 360);
	        fdNext.bottom = new FormAttachment(0, 50);
	        fdNext.right = new FormAttachment(0, 500);
	        labelNext.setLayoutData(fdNext);
	        
        this.nextCanvas = new NextCanvas(this.shell, SWT.NO_REDRAW_RESIZE, this.onlineGame, 0);
	        FormData fdNextCanvas = new FormData();
	        fdNextCanvas.top = new FormAttachment(0, 50);
	        fdNextCanvas.left = new FormAttachment(0, 360);
	        fdNextCanvas.bottom = new FormAttachment(0, 150);
	        fdNextCanvas.right = new FormAttachment(0, 500);
	        this.nextCanvas.setLayoutData(fdNextCanvas);
        
	    this.time = new Label(this.shell,SWT.LEFT);
	    	this.time.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
	    	this.time.setAlignment(SWT.RIGHT);
	    	this.time.setText(this.onlineGame.getTime().toString());
	        FormData fdTime = new FormData();
	        fdTime.top = new FormAttachment(0, 160);
	        fdTime.left = new FormAttachment(0, 360);
	        fdTime.bottom = new FormAttachment(0, 190);
	        fdTime.right = new FormAttachment(0, 500);
	        this.time.setLayoutData(fdTime);
	        
        Label labelScore = new Label(this.shell,SWT.NONE);
        	labelScore.setFont(new Font(this.display, "Arial", 14, SWT.BOLD | SWT.ITALIC));
        	labelScore.setText("Score");
	        FormData fdScore = new FormData();
	        fdScore.top = new FormAttachment(0, 260);
	        fdScore.left = new FormAttachment(0, 360);
	        fdScore.bottom = new FormAttachment(0, 290);
	        fdScore.right = new FormAttachment(0, 500);
	        labelScore.setLayoutData(fdScore);
	        
	    this.score = new Label(this.shell,SWT.LEFT | SWT.BORDER);
	    	this.score.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
	    	this.score.setAlignment(SWT.RIGHT);
	    	this.score.setText(""+this.onlineGame.getPlayerScore(0));
	        FormData fdScoreValue = new FormData();
	        fdScoreValue.top = new FormAttachment(0, 300);
	        fdScoreValue.left = new FormAttachment(0, 360);
	        fdScoreValue.bottom = new FormAttachment(0, 330);
	        fdScoreValue.right = new FormAttachment(0, 500);
	        this.score.setLayoutData(fdScoreValue);
	        
	    this.scoreOnlinePlayer0 = new Label(this.shell,SWT.LEFT | SWT.BORDER);
	    	this.scoreOnlinePlayer0.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
	    	this.scoreOnlinePlayer0.setAlignment(SWT.RIGHT);
	    	this.scoreOnlinePlayer0.setText(""+this.onlineGame.getOnlinePlayerScore(0));
	        FormData fdScoreOnlinePlayer0Value = new FormData();
	        fdScoreOnlinePlayer0Value.top = new FormAttachment(0, 340);
	        fdScoreOnlinePlayer0Value.left = new FormAttachment(0, 360);
	        fdScoreOnlinePlayer0Value.bottom = new FormAttachment(0, 370);
	        fdScoreOnlinePlayer0Value.right = new FormAttachment(0, 500);
	        this.scoreOnlinePlayer0.setLayoutData(fdScoreOnlinePlayer0Value);

        this.onlineplayer0Canvas = new OnlinePlayerCanvas(this.shell, SWT.NO_REDRAW_RESIZE, this.onlineGame, 0);
	        FormData fdPlayer0Canvas = new FormData();
	        fdPlayer0Canvas.top = new FormAttachment(0, 380);
	        fdPlayer0Canvas.left = new FormAttachment(0, 370);
	        fdPlayer0Canvas.bottom = new FormAttachment(0, 620);
	        fdPlayer0Canvas.right = new FormAttachment(0, 490);
	        this.onlineplayer0Canvas.setLayoutData(fdPlayer0Canvas);
	        
	    if(this.onlineGame.getNumOnlinePlayers()>1) {
	    	this.onlineplayer1Canvas = new OnlinePlayerCanvas(this.shell, SWT.NO_REDRAW_RESIZE, this.onlineGame, 1);
		        FormData fdPlayer1Canvas = new FormData();
		        fdPlayer1Canvas.top = new FormAttachment(0, 20);
		        fdPlayer1Canvas.left = new FormAttachment(0, 540);
		        fdPlayer1Canvas.bottom = new FormAttachment(0, 260);
		        fdPlayer1Canvas.right = new FormAttachment(0, 660);
		        this.onlineplayer1Canvas.setLayoutData(fdPlayer1Canvas);
		        
	        this.scoreOnlinePlayer1 = new Label(this.shell,SWT.LEFT | SWT.BORDER);
		    	this.scoreOnlinePlayer1.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
		    	this.scoreOnlinePlayer1.setAlignment(SWT.RIGHT);
		    	this.scoreOnlinePlayer1.setText(""+this.onlineGame.getOnlinePlayerScore(1));
		        FormData fdScoreOnlinePlayer1Value = new FormData();
		        fdScoreOnlinePlayer1Value.top = new FormAttachment(0, 300);
		        fdScoreOnlinePlayer1Value.left = new FormAttachment(0, 530);
		        fdScoreOnlinePlayer1Value.bottom = new FormAttachment(0, 330);
		        fdScoreOnlinePlayer1Value.right = new FormAttachment(0, 670);
		        this.scoreOnlinePlayer1.setLayoutData(fdScoreOnlinePlayer1Value);
	    }
	    
	    if(this.onlineGame.getNumOnlinePlayers()>2) {
	        this.scoreOnlinePlayer2 = new Label(this.shell,SWT.LEFT | SWT.BORDER);
		    	this.scoreOnlinePlayer2.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
		    	this.scoreOnlinePlayer2.setAlignment(SWT.RIGHT);
		    	this.scoreOnlinePlayer2.setText(""+this.onlineGame.getOnlinePlayerScore(2));
		        FormData fdScoreOnlinePlayer2Value = new FormData();
		        fdScoreOnlinePlayer2Value.top = new FormAttachment(0, 340);
		        fdScoreOnlinePlayer2Value.left = new FormAttachment(0, 530);
		        fdScoreOnlinePlayer2Value.bottom = new FormAttachment(0, 370);
		        fdScoreOnlinePlayer2Value.right = new FormAttachment(0, 670);
		        this.scoreOnlinePlayer2.setLayoutData(fdScoreOnlinePlayer2Value);
		        
	    	this.onlineplayer2Canvas = new OnlinePlayerCanvas(this.shell, SWT.NO_REDRAW_RESIZE, this.onlineGame, 2);
		        FormData fdPlayer2Canvas = new FormData();
		        fdPlayer2Canvas.top = new FormAttachment(0, 380);
		        fdPlayer2Canvas.left = new FormAttachment(0, 540);
		        fdPlayer2Canvas.bottom = new FormAttachment(0, 620);
		        fdPlayer2Canvas.right = new FormAttachment(0, 660);
		        this.onlineplayer2Canvas.setLayoutData(fdPlayer2Canvas);
	    }
	}
	
	public void launch() {
		this.onlineGame.start();
		this.shell.open();
		
		while(!this.onlineGame.isEnded() && this.onlineGame.isRunning()) {
			this.display.readAndDispatch();
			
	    	this.time.setText(this.onlineGame.getTime().toString());
			
			if(this.onlineGame.isOnlinePlayerModified(0)) {
				this.onlineplayer0Canvas.redraw();
				this.onlineplayer0Canvas.update();
				
				this.scoreOnlinePlayer0.setText(""+this.onlineGame.getOnlinePlayerScore(0));
				
				this.onlineGame.updatedOnlinePlayer(0);
			}
			
			if(this.onlineGame.getNumOnlinePlayers()>1) {
				if(this.onlineGame.isOnlinePlayerModified(1)) {
					this.onlineplayer1Canvas.redraw();
					this.onlineplayer1Canvas.update();
					
					this.scoreOnlinePlayer1.setText(""+this.onlineGame.getOnlinePlayerScore(1));
					
					this.onlineGame.updatedOnlinePlayer(1);
				}
			}
			
			if(this.onlineGame.getNumOnlinePlayers()>2) {
				if(this.onlineGame.isOnlinePlayerModified(2)) {
					this.onlineplayer2Canvas.redraw();
					this.onlineplayer2Canvas.update();
					
					this.scoreOnlinePlayer2.setText(""+this.onlineGame.getOnlinePlayerScore(2));
					
					this.onlineGame.updatedOnlinePlayer(2);
				}
			}
			
			if(this.onlineGame.isPlayerModified(0)) {
				this.playerCanvas.redraw();
				this.playerCanvas.update();
				
				this.nextCanvas.redraw();
				this.nextCanvas.update();
				
				this.score.setText(""+this.onlineGame.getPlayerScore(0));
				
				this.onlineGame.updatedPlayer(0);
			}
		}
		
		if(this.onlineGame.isRunning()) {
			this.onlineGame.stop();
		}
		
		if(this.onlineGame.isEnded()) {
			if(this.onlineGame.isAllPlayersLose()) {
				MessageBox winner = new MessageBox(this.shell, SWT.ICON_INFORMATION | SWT.OK);
				winner.setText("Winner");
				int numWinner = this.onlineGame.actualWinner();
				if(numWinner == -1) {
					winner.setMessage("It's a tie.");
				}
				else if(numWinner == 0) {
					winner.setMessage("You won the game.");
				}
				else {
					winner.setMessage("Player "+(numWinner+1)+" won the game.");
				}
				
				winner.open();
			}
			else {
				MessageBox error = new MessageBox(this.shell, SWT.ICON_ERROR | SWT.OK);
				error.setText("Error");
				error.setMessage("An error has occurred.");
				
				error.open();
			}
		}
		
		this.shell.setVisible(false);
		this.shell.dispose();
	}

	private void keyHandler(KeyEvent e) {
		switch(e.keyCode) {
			case SWT.ARROW_UP:
				this.onlineGame.rotatePlayer(0);
				break;
			case SWT.ARROW_DOWN:
				this.onlineGame.moveDownPlayer(0);
				break;
			case SWT.ARROW_RIGHT:
				this.onlineGame.moveRightPlayer(0);
				break;
			case SWT.ARROW_LEFT:
				this.onlineGame.moveLeftPlayer(0);
				break;
			case SWT.ESC:
					MessageBox pause = new MessageBox(this.shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
						pause.setText("Paused");
						pause.setMessage("Continue game?");
						
						this.onlineGame.stop();
						if(pause.open() == SWT.YES) {
							this.onlineGame.start();
						}
				break;
		}
	}
	
}

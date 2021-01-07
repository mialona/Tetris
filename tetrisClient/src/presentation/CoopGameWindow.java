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

import business.CoopGame;

public class CoopGameWindow {

	private Display display;
	private Shell shell;
	private CoopGame coopGame;
	private PlayerCanvas player0Canvas;
	private NextCanvas next0Canvas;
	private PlayerCanvas player1Canvas;
	private NextCanvas next1Canvas;
	private Label time;
	private Label score;
	private Label highscore;
	
	public CoopGameWindow(Display display, int xCenteredLocation, int yCenteredLocation) {
		this.display = display;
		
		this.shell = new Shell(this.display, SWT.TITLE);
			this.shell.setSize(880,700);
			this.shell.setText("Tetris - Co-op game");
			this.shell.setLayout(new FormLayout());
			this.shell.setLocation(xCenteredLocation-440, yCenteredLocation-350);
		    
        this.coopGame = new CoopGame();
        
        this.player0Canvas = new PlayerCanvas(this.shell, SWT.NO_REDRAW_RESIZE, this.coopGame, 0);
	        FormData fdPlayer0Canvas = new FormData();
	        fdPlayer0Canvas.top = new FormAttachment(0, 0);
	        fdPlayer0Canvas.left = new FormAttachment(0, 0);
	        fdPlayer0Canvas.bottom = new FormAttachment(0, 640);
	        fdPlayer0Canvas.right = new FormAttachment(0, 340);
	        this.player0Canvas.setLayoutData(fdPlayer0Canvas);
	        
	        this.player0Canvas.addKeyListener(new KeyListener() {

				@Override
				public void keyReleased(KeyEvent e) {}

				@Override
				public void keyPressed(KeyEvent e) {
					keyHandler(e);
				}
			});

        Label label0Next = new Label(this.shell,SWT.NONE);
        	label0Next.setFont(new Font(this.display, "Arial", 14, SWT.BOLD | SWT.ITALIC));
        	label0Next.setText("Next");
	        FormData fd0Next = new FormData();
	        fd0Next.top = new FormAttachment(0, 20);
	        fd0Next.left = new FormAttachment(0, 360);
	        fd0Next.bottom = new FormAttachment(0, 50);
	        fd0Next.right = new FormAttachment(0, 500);
	        label0Next.setLayoutData(fd0Next);
	        
        this.next0Canvas = new NextCanvas(this.shell, SWT.NO_REDRAW_RESIZE, this.coopGame, 0);
	        FormData fdNext0Canvas = new FormData();
	        fdNext0Canvas.top = new FormAttachment(0, 50);
	        fdNext0Canvas.left = new FormAttachment(0, 360);
	        fdNext0Canvas.bottom = new FormAttachment(0, 150);
	        fdNext0Canvas.right = new FormAttachment(0, 500);
	        this.next0Canvas.setLayoutData(fdNext0Canvas);
        
	    this.time = new Label(this.shell,SWT.LEFT);
	    	this.time.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
	    	this.time.setAlignment(SWT.RIGHT);
	    	this.time.setText(this.coopGame.getTime().toString());
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
	        fdScore.top = new FormAttachment(0, 200);
	        fdScore.left = new FormAttachment(0, 360);
	        fdScore.bottom = new FormAttachment(0, 230);
	        fdScore.right = new FormAttachment(0, 500);
	        labelScore.setLayoutData(fdScore);
	        
	    this.score = new Label(this.shell,SWT.LEFT | SWT.BORDER);
	    	this.score.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
	    	this.score.setAlignment(SWT.RIGHT);
	    	this.score.setText(""+this.coopGame.getScore());
	        FormData fdScoreValue = new FormData();
	        fdScoreValue.top = new FormAttachment(0, 230);
	        fdScoreValue.left = new FormAttachment(0, 360);
	        fdScoreValue.bottom = new FormAttachment(0, 260);
	        fdScoreValue.right = new FormAttachment(0, 500);
	        this.score.setLayoutData(fdScoreValue);
	        
        Label labelHighscore = new Label(this.shell,SWT.NONE);
        	labelHighscore.setFont(new Font(this.display, "Arial", 14, SWT.BOLD | SWT.ITALIC));
        	labelHighscore.setText("Highscore");
	        FormData fdHighscore = new FormData();
	        fdHighscore.top = new FormAttachment(0, 280);
	        fdHighscore.left = new FormAttachment(0, 360);
	        fdHighscore.bottom = new FormAttachment(0, 310);
	        fdHighscore.right = new FormAttachment(0, 500);
	        labelHighscore.setLayoutData(fdHighscore);
	        
	    this.highscore = new Label(this.shell,SWT.LEFT | SWT.BORDER);
	    	this.highscore.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
	    	this.highscore.setAlignment(SWT.RIGHT);
	    	this.highscore.setText(""+this.coopGame.getHighscore());
	        FormData fdHighscoreValue = new FormData();
	        fdHighscoreValue.top = new FormAttachment(0, 310);
	        fdHighscoreValue.left = new FormAttachment(0, 360);
	        fdHighscoreValue.bottom = new FormAttachment(0, 340);
	        fdHighscoreValue.right = new FormAttachment(0, 500);
	        this.highscore.setLayoutData(fdHighscoreValue);

        Label label1Next = new Label(this.shell,SWT.NONE);
        	label1Next.setFont(new Font(this.display, "Arial", 14, SWT.BOLD | SWT.ITALIC));
        	label1Next.setText("Next");
	        FormData fd1Next = new FormData();
	        fd1Next.top = new FormAttachment(0, 380);
	        fd1Next.left = new FormAttachment(0, 360);
	        fd1Next.bottom = new FormAttachment(0, 410);
	        fd1Next.right = new FormAttachment(0, 500);
	        label1Next.setLayoutData(fd1Next);
	        
        this.next1Canvas = new NextCanvas(this.shell, SWT.NO_REDRAW_RESIZE, this.coopGame, 1);
	        FormData fdNext1Canvas = new FormData();
	        fdNext1Canvas.top = new FormAttachment(0, 410);
	        fdNext1Canvas.left = new FormAttachment(0, 360);
	        fdNext1Canvas.bottom = new FormAttachment(0, 510);
	        fdNext1Canvas.right = new FormAttachment(0, 500);
	        this.next1Canvas.setLayoutData(fdNext1Canvas);
	        
        this.player1Canvas = new PlayerCanvas(this.shell, SWT.NO_REDRAW_RESIZE, this.coopGame, 1);
	        FormData fdPlayer1Canvas = new FormData();
	        fdPlayer1Canvas.top = new FormAttachment(0, 0);
	        fdPlayer1Canvas.left = new FormAttachment(0, 520);
	        fdPlayer1Canvas.bottom = new FormAttachment(0, 640);
	        fdPlayer1Canvas.right = new FormAttachment(0, 860);
	        this.player1Canvas.setLayoutData(fdPlayer1Canvas);
	}
	
	public void launch() {
		this.coopGame.start();
		this.shell.open();
		
		while(!this.coopGame.isLose() && this.coopGame.isRunning()) {
			this.display.readAndDispatch();
			
	    	this.time.setText(this.coopGame.getTime().toString());
			
			if(this.coopGame.isPlayerModified(0)) {
				this.player0Canvas.redraw();
				this.player0Canvas.update();
				
				this.next0Canvas.redraw();
				this.next0Canvas.update();
				
				this.score.setText(""+this.coopGame.getScore());
				
				this.coopGame.updatedPlayer(0);
			}
			
			if(this.coopGame.isPlayerModified(1)) {
				this.player1Canvas.redraw();
				this.player1Canvas.update();
				
				this.next1Canvas.redraw();
				this.next1Canvas.update();
				
				this.score.setText(""+this.coopGame.getScore());
				
				this.coopGame.updatedPlayer(1);
			}
		}
		
		this.shell.setVisible(false);
		this.shell.dispose();
		
		if(this.coopGame.isRunning()) {
			this.coopGame.stop();
			this.coopGame.saveScore();
			
			ScoresWindow scores = new ScoresWindow(this.display, this.coopGame.getHighscore(), this.coopGame.getScores());
			scores.launch();
		}
	}

	private void keyHandler(KeyEvent e) {
		switch(e.keyCode) {
			case 'w':
				this.coopGame.rotatePlayer(0);
				break;
			case 's':
				this.coopGame.moveDownPlayer(0);
				break;
			case 'd':
				this.coopGame.moveRightPlayer(0);
				break;
			case 'a':
				this.coopGame.moveLeftPlayer(0);
				break;
			case SWT.ARROW_UP:
				this.coopGame.rotatePlayer(1);
				break;
			case SWT.ARROW_DOWN:
				this.coopGame.moveDownPlayer(1);
				break;
			case SWT.ARROW_RIGHT:
				this.coopGame.moveRightPlayer(1);
				break;
			case SWT.ARROW_LEFT:
				this.coopGame.moveLeftPlayer(1);
				break;
			case SWT.ESC:
					MessageBox pause = new MessageBox(this.shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
						pause.setText("Paused");
						pause.setMessage("Continue game?");
						
						this.coopGame.stop();
						if(pause.open() == SWT.YES) {
							this.coopGame.start();
						}
				break;
		}
	}
}

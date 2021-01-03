package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import business.SingleGame;

public class SingleGameWindow {

	private Display display;
	private Shell shell;
	private SingleGame singleGame;
	private PlayerCanvas playerCanvas;
	private NextCanvas nextCanvas;
	private Label time;
	private Label score;
	private Label highscore;

	public SingleGameWindow(Display display) {
		this.display = display;
		
		this.shell = new Shell(this.display, SWT.TITLE);
			this.shell.setSize(560,700);
			this.shell.setText("Tetris - Single game");
			this.shell.setLayout(new FormLayout());
			
			Monitor primary = display.getPrimaryMonitor();
		    Rectangle screenBounds = primary.getBounds();
		    this.shell.setLocation(screenBounds.x+(screenBounds.width-560)/2,
		    		screenBounds.y+(screenBounds.height-700)/2);
		    
        this.singleGame = new SingleGame();
        
        this.playerCanvas = new PlayerCanvas(this.shell, SWT.NO_REDRAW_RESIZE, this.singleGame);
	        FormData fdPlayerCanvas = new FormData();
	        fdPlayerCanvas.top = new FormAttachment(0, 0);
	        fdPlayerCanvas.left = new FormAttachment(0, 0);
	        fdPlayerCanvas.bottom = new FormAttachment(0, 640);
	        fdPlayerCanvas.right = new FormAttachment(0, 340);
	        this.playerCanvas.setLayoutData(fdPlayerCanvas);

        Label labelNext = new Label(this.shell,SWT.NONE);
        	labelNext.setFont(new Font(this.display, "Arial", 14, SWT.BOLD | SWT.ITALIC));
        	labelNext.setText("Next");
	        FormData fdNext = new FormData();
	        fdNext.top = new FormAttachment(0, 20);
	        fdNext.left = new FormAttachment(0, 360);
	        fdNext.bottom = new FormAttachment(0, 50);
	        fdNext.right = new FormAttachment(0, 500);
	        labelNext.setLayoutData(fdNext);
	        
        this.nextCanvas = new NextCanvas(this.shell, SWT.NO_REDRAW_RESIZE, this.singleGame);
	        FormData fdNextCanvas = new FormData();
	        fdNextCanvas.top = new FormAttachment(0, 50);
	        fdNextCanvas.left = new FormAttachment(0, 360);
	        fdNextCanvas.bottom = new FormAttachment(0, 150);
	        fdNextCanvas.right = new FormAttachment(0, 500);
	        this.nextCanvas.setLayoutData(fdNextCanvas);
        
	    this.time = new Label(this.shell,SWT.LEFT);
	    	this.time.setFont(new Font(this.display, "Arial", 14, SWT.NONE));
	    	this.time.setAlignment(SWT.RIGHT);
	    	this.time.setText(this.singleGame.getTime().toString());
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
	    	this.score.setText(""+this.singleGame.getScore(0));
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
	    	this.highscore.setText(""+this.singleGame.getHighscore());
	        FormData fdHighscoreValue = new FormData();
	        fdHighscoreValue.top = new FormAttachment(0, 310);
	        fdHighscoreValue.left = new FormAttachment(0, 360);
	        fdHighscoreValue.bottom = new FormAttachment(0, 340);
	        fdHighscoreValue.right = new FormAttachment(0, 500);
	        this.highscore.setLayoutData(fdHighscoreValue);
	        
	}
	
	public void launch() {
		this.singleGame.start();
		this.shell.open();
		
		while(!this.singleGame.isLose(0) && this.singleGame.isRunning()) {
			this.display.readAndDispatch();
			
	    	this.time.setText(this.singleGame.getTime().toString());
			
			if(this.singleGame.isPlayerModified(0)) {
				this.playerCanvas.redraw();
				this.playerCanvas.update();
				
				this.nextCanvas.redraw();
				this.nextCanvas.update();
				
				this.score.setText(""+this.singleGame.getScore(0));
				
				this.singleGame.updatedPlayer(0);
			}
		}
		
		this.shell.setVisible(false);
		this.shell.dispose();
		
		if(this.singleGame.isRunning()) {
			this.singleGame.stop();
			this.singleGame.saveScore();
			
			ScoresWindow scores = new ScoresWindow(this.display, this.singleGame);
			scores.launch();
		}
	}
	
}

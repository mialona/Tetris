package presentation;

import java.util.List;

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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import domain.RegisterScore;

public class ScoresWindow {

	private Display display;
	private Shell shell;
	private long highscore;
	private List<RegisterScore> scores;

	public ScoresWindow(Display display, long highscore, List<RegisterScore> scores) {
		this.display = display;

		this.shell = new Shell(this.display, SWT.TITLE | SWT.CLOSE);
		this.shell.setSize(560, 700);
		this.shell.setText("Tetris - Scores");
		this.shell.setLayout(new FormLayout());

		Monitor primary = display.getPrimaryMonitor();
		Rectangle screenBounds = primary.getBounds();
		this.shell.setLocation(screenBounds.x + (screenBounds.width - 560) / 2,
				screenBounds.y + (screenBounds.height - 700) / 2);

		this.highscore = highscore;
		
		this.scores = scores;

		Label labelHighscore = new Label(this.shell, SWT.NONE);
			labelHighscore.setFont(new Font(this.display, "Arial", 14, SWT.BOLD | SWT.ITALIC));
			labelHighscore.setText("Highscore:");
			FormData fdHighscore = new FormData();
			fdHighscore.top = new FormAttachment(0, 70);
			fdHighscore.left = new FormAttachment(0, 60);
			fdHighscore.bottom = new FormAttachment(0, 100);
			fdHighscore.right = new FormAttachment(0, 200);
			labelHighscore.setLayoutData(fdHighscore);

		Label labelHighscoreValue = new Label(this.shell, SWT.NONE);
			labelHighscoreValue.setFont(new Font(this.display, "Arial", 30, SWT.NONE));
			labelHighscoreValue.setText("" + this.highscore);
			FormData fdHighscoreValue = new FormData();
			fdHighscoreValue.top = new FormAttachment(0, 50);
			fdHighscoreValue.left = new FormAttachment(0, 200);
			fdHighscoreValue.bottom = new FormAttachment(0, 100);
			fdHighscoreValue.right = new FormAttachment(0, 700);
			labelHighscoreValue.setLayoutData(fdHighscoreValue);

		Table table = new Table(shell, SWT.BORDER | SWT.H_SCROLL);
			table.setHeaderVisible(true);
	
			TableColumn columnDate = new TableColumn(table, SWT.NULL);
			columnDate.setText("Date");
	
			TableColumn columnScore = new TableColumn(table, SWT.NULL);
			columnScore.setText("Score                    ");
			columnScore.setAlignment(SWT.RIGHT);
	
			TableColumn columnTime = new TableColumn(table, SWT.NULL);
			columnTime.setText("Time");
	
			for (RegisterScore score : this.scores) {
				TableItem item = new TableItem(table, SWT.NULL);
				item.setText(0, score.getDate().toString());
				item.setText(1, "" + score.getScore());
				item.setText(2, score.getTime().toString());
			}
	
			for (int i = 0; i < 3; i++) {
				table.getColumn(i).pack();
			}
	
			FormData fdScoresTable = new FormData();
			fdScoresTable.top = new FormAttachment(0, 140);
			fdScoresTable.left = new FormAttachment(0, 65);
			fdScoresTable.bottom = new FormAttachment(0, 590);
			fdScoresTable.right = new FormAttachment(0, 485);
			table.setLayoutData(fdScoresTable);
	}

	public void launch() {
		this.shell.open();

		while (!this.shell.isDisposed()) {
			if (!this.display.readAndDispatch()) {
				this.display.sleep();
			}
		}
	}

}

package presentation;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import business.Server;

public class ServerConsole {

	private static Display display;
	private static Shell shell;
	private static Table table;
	private static Queue<String> strings;
	
	public ServerConsole() {
		ServerConsole.display = new Display();
		
		ServerConsole.shell = new Shell(display);
			ServerConsole.shell.setSize(700,575);
			ServerConsole.shell.setText("Tetris server");
			ServerConsole.shell.setLayout(new FormLayout());
			
			Monitor primary = display.getPrimaryMonitor();
		    Rectangle screenBounds = primary.getBounds();
		    ServerConsole.shell.setLocation(screenBounds.x+(screenBounds.width-700)/2,
		    		screenBounds.y+(screenBounds.height-575)/2);
		
		table = new Table(shell, SWT.BORDER | SWT.H_SCROLL);
			table.setHeaderVisible(true);
	
			TableColumn columnDate = new TableColumn(table, SWT.NULL);
			columnDate.setText("Date");
	
			TableColumn columnScore = new TableColumn(table, SWT.FILL);
			columnScore.setText("Event");
	
			for (int i = 0; i < 2; i++) {
				table.getColumn(i).pack();
			}
	
			FormData fdScoresTable = new FormData();
			fdScoresTable.top = new FormAttachment(0, 20);
			fdScoresTable.left = new FormAttachment(0, 20);
			fdScoresTable.bottom = new FormAttachment(0, 510);
			fdScoresTable.right = new FormAttachment(0, 660);
			table.setLayoutData(fdScoresTable);
		
		table.pack();
		
		ServerConsole.strings = new LinkedList<String>();
	}
	
	public void launch() {
		ServerConsole.shell.open();
		
		Server server = new Server();
		server.start();
		
		while(!ServerConsole.shell.isDisposed()) {
			if(!ServerConsole.display.readAndDispatch()) {
				ServerConsole.display.sleep();
			}
			
			if(!ServerConsole.strings.isEmpty()) {
				for(int i = 0; i < ServerConsole.strings.size(); i++) {
					TableItem item = new TableItem(table, SWT.NULL);
					item.setText(0, (new Date()).toString());
					item.setText(1, ServerConsole.strings.poll());
					
					for (int j = 0; j < 2; j++) {
						table.getColumn(j).pack();
					}
				}
			}
		}
		
		server.stop();
		
		ServerConsole.display.dispose();
	}
	
	public static void println(String string) {
		ServerConsole.strings.add(string);
	}
}

package Controller;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class MediaBarKeyEventHandler implements EventHandler<KeyEvent>{

	private StackPane mediaBar;
	private Label timeLabel;
	private Rectangle timeBox;
	private StackPane panel;
	
	public MediaBarKeyEventHandler(StackPane m, Label l, Rectangle r, StackPane s)
	{
		mediaBar = m;
		timeLabel = l;
		timeBox = r;
		panel = s;
	}
	@Override
	public void handle(KeyEvent arg0) {
		if(arg0.getCode() == KeyCode.M)
		{
			if(mediaBar.getOpacity() == 1)
			{
				mediaBar.setOpacity(0);
				timeLabel.setOpacity(0);
				timeBox.setOpacity(0);
				panel.setCursor(Cursor.NONE);
			}
			else
			{
				mediaBar.setOpacity(1);
				timeLabel.setOpacity(1);
				timeBox.setOpacity(1);
				panel.setCursor(Cursor.DEFAULT);
			}
		}
	}

}

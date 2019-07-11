package Controller;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class VideoKeyEventHandler implements EventHandler<KeyEvent>{

	public VideoKeyEventHandler()
	{
		
	}
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			MainController.getInstance().changeView(ViewType.VIDEOPLAYERVIEW, Optional.empty(), Optional.empty());
		}
		
	}

}

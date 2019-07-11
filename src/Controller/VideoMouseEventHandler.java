package Controller;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class VideoMouseEventHandler implements EventHandler<MouseEvent>{

	@Override
	public void handle(MouseEvent arg0) {
		if(arg0.isPrimaryButtonDown() && arg0.getClickCount() == 2)
		{
			MainController.getInstance().changeView(ViewType.VIDEOPLAYERVIEW, Optional.empty(), Optional.empty());
		}
	}

}

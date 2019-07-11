package Controller;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;

public class MediaPlayerKeyEventHandler implements EventHandler<KeyEvent>{
	
	private StackPane panel;
	private MediaPlayer player;
	
	public MediaPlayerKeyEventHandler(StackPane p, Optional<MediaPlayer> mp)
	{
		panel = p;
		player = mp.get();
	}
	
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.BACK_SPACE)
		{
			player.stop();
			MainController.getInstance().changeView(ViewType.HOMEVIEW, Optional.empty(), Optional.empty());
		}
	}

}

package Controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;

public class VideoPlayerInvalidationListener implements InvalidationListener{

	private Slider slider;
	private MediaPlayer player;
	public VideoPlayerInvalidationListener(Slider s, MediaPlayer p) {
		slider = s;
		player = p;
	}
	
	@Override
	public void invalidated(Observable arg0) {
		if(slider.isPressed()) {
			player.seek(player.getMedia().getDuration().multiply(slider.getValue() / 100)); 
		}
	}

}

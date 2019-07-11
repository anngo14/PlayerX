package Controller;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.util.Duration;

public class VideoPlayerChangeListener implements ChangeListener<Boolean>{

	FadeTransition fade;
	private Node node;
	public VideoPlayerChangeListener(Node n)
	{
		node = n;
	}
	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
		if(arg2)
		{
			fade = new FadeTransition(Duration.millis(500), node);
			fade.setFromValue(1.0);
			fade.setToValue(0.5);
			
			fade.setCycleCount(Timeline.INDEFINITE);
			fade.setAutoReverse(true);
			fade.play();
		}
		else
		{
			fade.stop();
			node.setOpacity(1);
		}
	}

}

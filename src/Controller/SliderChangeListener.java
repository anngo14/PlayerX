package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;

public class SliderChangeListener implements ChangeListener<Number>{

	private Slider slider;
	private MediaPlayer player;
	
	public SliderChangeListener(Slider s, MediaPlayer p) {
		slider = s;
		player = p;
	}
	@Override
	public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
		if(Math.abs(arg2.intValue() -  arg1.intValue()) == slider.getBlockIncrement())
		{
			slider.setValue(arg2.doubleValue());
			player.seek(player.getMedia().getDuration().multiply(slider.getValue() / 100));
		}
	}

}

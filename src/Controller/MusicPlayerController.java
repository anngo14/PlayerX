package Controller;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MusicPlayerController implements Initializable, Controller{

    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	@FXML
	StackPane panel;
	@FXML
	Label timeLabel;
	@FXML
	Label songLabel;
	
	public MusicPlayerController()
	{
		
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.HOMEVIEW);
	}
	@FXML
	public void backToList()
	{
		MainController.getInstance().changeView(ViewType.MUSICVIEW);
	}
	@FXML
	public void playSong()
	{
		
	}
	@FXML
	public void reverseTrack()
	{
		
	}
	@FXML
	public void pauseTrack()
	{
		
	}
	@FXML
	public void fastForwardTrack()
	{
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

}

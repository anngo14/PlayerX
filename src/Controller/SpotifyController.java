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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SpotifyController implements Controller, Initializable{

    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	@FXML 
	StackPane panel;
	@FXML
	ListView playlistList;
	@FXML
	ListView trackList;
	@FXML
	Label timeLabel;
	@FXML
	Label playlistLabel;
	@FXML
	Label usernameLabel;
	@FXML
	Button playButton;
	
	public SpotifyController()
	{
		
	}
	
	@FXML
	public void backToHome()
	{
		
	}
	@FXML
	public void loginAction()
	{
		
	}
	@FXML
	public void shufflePlaylist()
	{
		
	}
	@FXML
	public void playTrack()
	{
		
	}
	@FXML
	public void reverseTrack()
	{
		
	}
	@FXML
	public void forwardTrack()
	{
		
	}
	@FXML
	public void shuffleTrack()
	{
		
	}
	@FXML
	public void repeatTrack()
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

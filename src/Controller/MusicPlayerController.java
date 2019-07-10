package Controller;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.MediaItem;
import Model.Music;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicPlayerController implements Initializable, Controller{

    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private MediaPlayer player;
    private Music selectedMusic;
    private ArrayList<Music> musicList;
	@FXML
	StackPane panel;
	@FXML
	Label timeLabel;
	@FXML
	Label songLabel;
	
	public MusicPlayerController()
	{
		player = new MediaPlayer(null);
	}
	public MusicPlayerController(MediaItem m, ArrayList<Music> list)
	{
		selectedMusic = (Music) m;
		musicList = list;
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.HOMEVIEW, Optional.empty(), Optional.empty());
	}
	@FXML
	public void backToList()
	{
		MainController.getInstance().changeView(ViewType.MUSICVIEW, Optional.empty(), Optional.empty());
	}
	@FXML
	public void playSong()
	{
		player.play();
	}
	@FXML
	public void reverseTrack()
	{
		
	}
	@FXML
	public void pauseTrack()
	{
		player.pause();
	}
	@FXML
	public void fastForwardTrack()
	{
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		songLabel.setText(selectedMusic.getfileName());
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

}

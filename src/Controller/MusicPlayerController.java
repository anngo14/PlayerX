package Controller;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.MediaItem;
import Model.Music;
import Model.User;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicPlayerController implements Initializable, Controller{

    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private MediaPlayer player;
    private Media media;
    private Music selectedMusic;
    private ArrayList<Music> musicList;
    private User user;
	@FXML
	StackPane panel;
	@FXML
	Label timeLabel;
	@FXML
	Label songLabel;
	@FXML
	ImageView playImg;
	@FXML
	ImageView pauseImg;
	@FXML
	ImageView reverseImg;
	@FXML 
	ImageView forwardImg;
	@FXML
	Button playButton;
	@FXML
	Button pauseButton;
	@FXML
	Button reverseButton;
	@FXML
	Button forwardButton;
	
	public MusicPlayerController()
	{
		player = new MediaPlayer(null);
	}
	public MusicPlayerController(User u, MediaItem m, ArrayList<Music> list)
	{
		user = u;
		selectedMusic = (Music) m;
		musicList = list;
		media = new Media(new File(selectedMusic.getPath()).toURI().toString());
		player = new MediaPlayer(media);
		player.setAutoPlay(true);
		player.setOnEndOfMedia(() -> fastForwardTrack());
	}

	@FXML
	public void backToHome()
	{
		player.stop();
		MainController.getInstance().changeView(ViewType.HOMEVIEW, user, Optional.empty(), Optional.empty());
	}
	@FXML
	public void backToList()
	{
		player.stop();
		MainController.getInstance().changeView(ViewType.MUSICVIEW, user, Optional.empty(), Optional.empty());
	}
	@FXML
	public void playSong()
	{
		player.play();
	}
	@FXML
	public void reverseTrack()
	{
		player.stop();
		int index = getIndex(selectedMusic, musicList);
		if(index == 0)
		{
			media = new Media(new File(musicList.get(musicList.size()-1).getPath()).toURI().toString());
			songLabel.setText(musicList.get(musicList.size()-1).getfileName());
			selectedMusic = musicList.get(musicList.size()-1);
		}
		else
		{
			media = new Media(new File(musicList.get(index-1).getPath()).toURI().toString());
			songLabel.setText(musicList.get(index-1).getfileName());
			selectedMusic = musicList.get(index-1);
		}
		player = new MediaPlayer(media);
		player.setAutoPlay(true);
		player.setOnEndOfMedia(() -> fastForwardTrack());
	}
	@FXML
	public void pauseTrack()
	{
		player.pause();
	}
	@FXML
	public void fastForwardTrack()
	{
		player.stop();
		int index = getIndex(selectedMusic, musicList);
		if(index == musicList.size()-1)
		{
			media = new Media(new File(musicList.get(0).getPath()).toURI().toString());
			songLabel.setText(musicList.get(0).getfileName());
			selectedMusic = musicList.get(0);
		}
		else
		{
			media = new Media(new File(musicList.get(index+1).getPath()).toURI().toString());
			songLabel.setText(musicList.get(index+1).getfileName());
			selectedMusic = musicList.get(index+1);

		}
		player = new MediaPlayer(media);
		player.setAutoPlay(true);
		player.setOnEndOfMedia(() -> fastForwardTrack());
	}
	public int getIndex(Music m, ArrayList<Music> list)
	{
		int index = -1;
		for(int i = 0; i < list.size(); i++)
		{
			if(m.getPath().equals(list.get(i).getPath()))
			{
				index = i;
			}
		}
		return index;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		panel.setOnKeyPressed(new MediaPlayerKeyEventHandler(panel, user, Optional.of(player)));
		songLabel.setText(selectedMusic.getfileName());
		playButton.focusedProperty().addListener(new MusicPlayerChangeListener(playImg));
		pauseButton.focusedProperty().addListener(new MusicPlayerChangeListener(pauseImg));
		reverseButton.focusedProperty().addListener(new MusicPlayerChangeListener(reverseImg));
		forwardButton.focusedProperty().addListener(new MusicPlayerChangeListener(forwardImg));
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

}

package Controller;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.MediaItem;
import Model.Video;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class VideoPlayerController implements Initializable, Controller{

    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Video selected;
    private MediaPlayer player;
    private Media media;
    
	@FXML
	MediaView viewer;
	@FXML
	Rectangle timeBox;
	@FXML
	Label timeLabel;
	@FXML
	Button listButton;
	@FXML
	Button hideButton;
	@FXML
	Button playButton;
	@FXML
	Button reverseButton;
	@FXML
	Button forwardButton;
	@FXML
	ImageView playImg;
	@FXML
	ImageView reverseImg;
	@FXML
	ImageView forwardImg;
	@FXML
	Slider slider;
	@FXML
	StackPane panel;
	@FXML
	StackPane mediaBar;
	
	
	public VideoPlayerController()
	{
		
	}
	
	public VideoPlayerController(MediaItem m)
	{
		selected = (Video) m;
		media = new Media(new File(selected.getPath()).toURI().toString());
		player = new MediaPlayer(media);
	}
	@FXML
	public void playMedia()
	{
		if(playButton.getText().equals("||"))
		{
			player.pause();
			playButton.setText(">");
			playImg.setImage(new Image("Resources/playicon.png"));
		}
		else
		{
			player.play();
			playButton.setText("||");
			playImg.setImage(new Image("Resources/pauseicon.png"));
		}
	}
	@FXML
	public void reverseMedia()
	{
		player.pause();
		Double seekValue = player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100 - 2;
		if(seekValue <= 0)
		{
			return;
		}
		slider.setValue(seekValue);
		player.seek(player.getMedia().getDuration().multiply(slider.getValue() / 100)); 
		player.play();
	}
	@FXML
	public void forwardMedia()
	{
		player.pause();
		Double seekValue = player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100 + 2;
		if(seekValue >= player.getTotalDuration().toMillis() * 100)
		{
			return;
		}
		slider.setValue(seekValue);
		player.seek(player.getMedia().getDuration().multiply(slider.getValue() / 100)); 
		player.play();
	}
	@FXML
	public void backToList()
	{
		player.stop();
		MainController.getInstance().changeView(ViewType.VIDEOVIEW, Optional.empty(), Optional.empty());
	}
	@FXML
	public void hideMediaBar()
	{
		if(mediaBar.getOpacity() == 1)
		{
			mediaBar.setOpacity(0);
			timeLabel.setOpacity(0);
			timeBox.setOpacity(0);
		}
		else
		{
			mediaBar.setOpacity(1);
			timeLabel.setOpacity(1);
			timeBox.setOpacity(1);
		}
	}
	protected void updatesValues()
	{
		Platform.runLater(new Runnable() {
			public void run()
			{
				slider.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100); 
			}
		});
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		viewer.setMediaPlayer(player);
		viewer.fitHeightProperty().bind(panel.heightProperty());
		viewer.fitWidthProperty().bind(panel.widthProperty());
		player.setAutoPlay(true);
		player.currentTimeProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable arg0) {
				updatesValues();
			}
			
		});
		slider.valueProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable arg0) {
				if(slider.isPressed()) {
					player.seek(player.getMedia().getDuration().multiply(slider.getValue() / 100)); 
				}
			}
			
		});
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				if(Math.abs(arg2.intValue() -  arg1.intValue()) == slider.getBlockIncrement())
				{
					slider.setValue(arg2.doubleValue());
					player.seek(player.getMedia().getDuration().multiply(slider.getValue() / 100));
				}
			}
			
		});
		playButton.focusedProperty().addListener(new VideoPlayerChangeListener(playImg));
		reverseButton.focusedProperty().addListener(new VideoPlayerChangeListener(reverseImg));
		forwardButton.focusedProperty().addListener(new VideoPlayerChangeListener(forwardImg));
		mediaBar.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {
				if(arg0.getCode() == KeyCode.M)
				{
					hideMediaBar();
				}
			}
			
		});
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

}

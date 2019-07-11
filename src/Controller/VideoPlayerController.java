package Controller;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VideoPlayerController implements Initializable, Controller{

    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

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
	StackPane panel;
	@FXML
	StackPane mediaBar;
	
	
	public VideoPlayerController()
	{
		
	}
	@FXML
	public void playMedia()
	{
		if(playButton.getText().equals(">"))
		{
			playButton.setText("||");
			playImg.setImage(new Image("Resources/pauseicon.png"));
		}
		else
		{
			playButton.setText(">");
			playImg.setImage(new Image("Resources/playicon.png"));
		}
	}
	@FXML
	public void reverseMedia()
	{
		
	}
	@FXML
	public void forwardMedia()
	{
		
	}
	@FXML
	public void backToList()
	{
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		
		viewer.fitHeightProperty().bind(panel.heightProperty());
		viewer.fitWidthProperty().bind(panel.widthProperty());
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

}

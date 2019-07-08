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
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class MusicController implements Initializable, Controller{

    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	@FXML
	Label titleLabel;
	@FXML
	Label timeLabel;
	@FXML
	TextField pathTextField;
	@FXML
	Button fileButton;
	@FXML
	ListView list1;
	@FXML
	ListView list2;
	@FXML
	Button homeButton;
	
	public MusicController()
	{
		
	}
	
	@FXML
	public void uploadDir()
	{
		
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.HOMEVIEW);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titleLabel.setText("Music");
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

}

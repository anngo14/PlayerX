package Controller;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class HomeController implements Controller, Initializable{
	
	StackPane panel;
	@FXML
	Label timeLabel;
	@FXML
	Label userLabel;
	@FXML
	Button youtubeButton;
	@FXML
	Button spotifyButton;
	@FXML
	Button videoButton;
	@FXML
	Button musicButton;
	
	FadeTransition fade;
	private final int cid = 2;
    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	public HomeController()
	{
		
	}
	public int getId()
	{
		return cid;
	}
	@FXML
	public void logoutAction()
	{
		fade = new FadeTransition(Duration.millis(500), panel);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.play();
		MainController.getInstance().changeView(ViewType.WELCOMEVIEW);
		fade.stop();
		fade = new FadeTransition(Duration.millis(750), panel);
		fade.setFromValue(0);
		fade.setToValue(1);
		
		fade.play();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		youtubeButton.setStyle("-fx-font-family: 'Syncopate', sans-serif;  -fx-font-size: 25;");
		spotifyButton.setStyle("-fx-font-family: 'Syncopate', sans-serif;  -fx-font-size: 25;");
		videoButton.setStyle("-fx-font-family: 'Syncopate', sans-serif;  -fx-font-size: 25;");
		musicButton.setStyle("-fx-font-family: 'Syncopate', sans-serif;  -fx-font-size: 25;");
		
		youtubeButton.requestFocus();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		 
	}
}

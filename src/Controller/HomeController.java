package Controller;

import java.awt.Desktop;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.User;
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
	
	private User user;
	
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

	public HomeController(User u)
	{
		user = u;
	}
	public int getId()
	{
		return cid;
	}
	@FXML
	public void musicSwitch()
	{
		MainController.getInstance().changeView(ViewType.MUSICVIEW, user, Optional.empty(), Optional.empty(), Optional.empty());
	}
	@FXML
	public void videoSwitch()
	{
		MainController.getInstance().changeView(ViewType.VIDEOVIEW, user, Optional.empty(), Optional.empty(), Optional.empty());
	}
	@FXML
	public void spotifySwitch()
	{
		try {
	        Desktop.getDesktop().browse(new URL("https://open.spotify.com/").toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	@FXML
	public void youtubeSwitch()
	{
		try {
	        Desktop.getDesktop().browse(new URL("https://www.youtube.com/").toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	@FXML
	public void logoutAction()
	{
		fade = new FadeTransition(Duration.millis(500), panel);
		fade.setFromValue(1);
		fade.setToValue(0);
		fade.play();
		MainController.getInstance().changeView(ViewType.WELCOMEVIEW, user, Optional.empty(), Optional.empty(), Optional.empty());
		fade.stop();
		fade = new FadeTransition(Duration.millis(750), panel);
		fade.setFromValue(0);
		fade.setToValue(1);
		
		fade.play();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userLabel.setText(user.getUserName());
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

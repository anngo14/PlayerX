package Controller;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import com.wrapper.spotify.SpotifyHttpManager;

import Model.User;
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
    private User user;
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
	
	public SpotifyController(User u)
	{
		user = u;
	}
	
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.HOMEVIEW, user, Optional.empty(), Optional.empty());
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
		try {
	        Desktop.getDesktop().browse(new URL("https://open.spotify.com/").toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}

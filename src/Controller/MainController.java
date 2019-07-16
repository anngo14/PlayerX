package Controller;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import Model.MediaItem;
import Model.Music;
import Model.User;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MainController implements Initializable, Controller{
	
	private final int cid = 1;
	private static MainController instance = null;
	private Controller currentController = null;
	FadeTransition fade;
	private User u;
	
	@FXML
	ImageView userImg;
	@FXML
	ImageView powerImg;
	@FXML
	StackPane panel;
	@FXML
	Text welcomeText;
	@FXML
	Text userNameText;
	
	public MainController()
	{
		
	}
	public MainController(User user)
	{
		u = user;
	}
	
	public static MainController getInstance() 
	{
		if (instance == null)
			instance = new MainController();
		return instance;
	}
	public void setUser(User user)
	{
		u = user;
	}
	public User getUser()
	{
		return u;
	}
	public int getId()
	{
		return cid;
	}
	@FXML
	public void loginAction()
	{
		fade.stop();
		
		fade = new FadeTransition(Duration.millis(750), panel);
		fade.setFromValue(1.0);
		fade.setToValue(0);
		fade.play();
		changeView(ViewType.HOMEVIEW, u, Optional.empty(), Optional.empty(), Optional.empty());
	}
	@FXML
	public void exitApplication()
	{
		int input = confirmationBox();
		if(input == 0)
		{
			System.exit(0);
		}
		else {
			return;
		}
	}
	@FXML
	public void switchUser()
	{
		changeView(ViewType.SWITCHUSER, u, Optional.empty(), Optional.empty(), Optional.empty());
	}
	public int confirmationBox()
	{
		JFrame jf=new JFrame();
        jf.setAlwaysOnTop(true);
		JLabel label = new JLabel("Are You Sure You Want to Exit?");
		label.setFont(new Font("Arial", Font.BOLD, 24));
		UIManager.put("OptionPane.minimumSize",new Dimension(150,100)); 
		int input = JOptionPane.showConfirmDialog(jf, label);
		return input;
	}

	public void changeView(ViewType viewType, User user, Optional<MediaItem> item, Optional<ArrayList<Music>> itemList, Optional<ArrayList<User>> userList) 
	{
		
		String viewName = "";
		Controller controller = null;
		switch(viewType) 
		{
			case HOMEVIEW:
				viewName = "/View/HomeView.fxml";
				controller = new HomeController(user);
				break;
			case WELCOMEVIEW:
				viewName = "/View/WelcomeView.fxml";
				controller = new MainController(user);
				break;
			case VIDEOVIEW:
				viewName = "/View/MediaListView.fxml";
				controller = new VideoController(user);
				break;
			case MUSICPLAYERVIEW:
				viewName = "/View/MusicPlayerView.fxml";
				controller = new MusicPlayerController(user, item.get(), itemList.get());
				break;
			case MUSICVIEW:
				viewName = "/View/MediaListView.fxml";
				controller = new MusicController(user);
				break;
			case VIDEOPLAYERVIEW:
				viewName = "/View/VideoPlayerView.fxml";
				controller = new VideoPlayerController(user, item.get());
				break;
			case SPOTIFYVIEW:
				viewName = "/View/SpotifyView.fxml";
				controller = new SpotifyController(user);
			case SWITCHUSER:
				viewName = "/View/SwitchUserView.fxml";
				controller = new SwitchUserController(user);
				break;
			case ADDUSER: 
				viewName = "/View/NewUserView.fxml";
				controller = new NewUserController(user, userList.get());
				break;
		}
		try {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource(viewName));
			loader.setController(controller);
			StackPane pane = loader.load();
			panel.getChildren().setAll(pane);
			fade.stop();
			fade = new FadeTransition(Duration.millis(750), panel);
			fade.setFromValue(0);
			fade.setToValue(1);
			
			fade.play();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(!u.getImgSrc().equals("Resources/rounded-512.png"))
		{
			try {
				userImg.setImage(new Image(new File(u.getImgSrc()).toURI().toURL().toExternalForm()));
			} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		userNameText.setText(u.getUserName());
		welcomeText.setStyle("-fx-font-family: 'Rubik Mono One', sans-serif; -fx-font-size: 140;");
		welcomeText.setWrappingWidth(400);
		fade = new FadeTransition(Duration.millis(1450), panel);
		fade.setFromValue(1.0);
		fade.setToValue(0.95);
		
		fade.setCycleCount(Timeline.INDEFINITE);
		fade.setAutoReverse(true);
		fade.play();
		
	}
}

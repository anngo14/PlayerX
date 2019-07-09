package Controller;

import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MainController implements Initializable, Controller{
	
	private final int cid = 1;
	private static MainController instance = null;
	private Controller currentController = null;
	FadeTransition fade;
	
	@FXML
	ImageView userImg;
	@FXML
	ImageView powerImg;
	@FXML
	StackPane panel;
	@FXML
	Text welcomeText;
	
	public MainController()
	{
		
	}
	
	public static MainController getInstance() 
	{
		if (instance == null)
			instance = new MainController();
		return instance;
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
		changeView(ViewType.HOMEVIEW);
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

	public void changeView(ViewType viewType) 
	{
		
		String viewName = "";
		Controller controller = null;
		switch(viewType) 
		{
			case HOMEVIEW:
				viewName = "/View/HomeView.fxml";
				controller = new HomeController();
				break;
			case WELCOMEVIEW:
				viewName = "/View/WelcomeView.fxml";
				controller = new MainController();
				break;
			case VIDEOVIEW:
				viewName = "/View/MediaListView.fxml";
				controller = new VideoController();
				break;
			case MUSICPLAYERVIEW:
				viewName = "/View/MusicPlayerView.fxml";
				controller = new MusicPlayerController();
				break;
			case MUSICVIEW:
				viewName = "/View/MediaListView.fxml";
				controller = new MusicController();
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

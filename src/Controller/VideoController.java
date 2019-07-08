package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class VideoController implements Controller, Initializable{

	FadeTransition fade;
	
	@FXML
	StackPane panel;
	@FXML
	Label titleLabel;
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
	
	public VideoController()
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
	public void initialize(URL location, ResourceBundle resources) {
		titleLabel.setText("Video");
		
	}

}

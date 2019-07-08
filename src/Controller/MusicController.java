package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MusicController implements Initializable, Controller{

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
	}

}

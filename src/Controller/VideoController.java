package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class VideoController implements Controller, Initializable{

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
	
	public VideoController()
	{
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		titleLabel.setText("Video");
		
	}
	@FXML
	public void uploadDir()
	{
		
	}

}

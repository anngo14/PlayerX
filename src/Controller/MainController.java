package Controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class MainController {

	private static MainController instance = null;
	
	@FXML
	ImageView userImg;
	@FXML
	ImageView powerImg;
	@FXML
	StackPane panel;
	
	public MainController()
	{
		
	}
	
	public static MainController getInstance() 
	{
		if (instance == null)
			instance = new MainController();
		return instance;
	}
	
	@FXML
	public void loginAction()
	{
		
	}
	@FXML
	public void exitApplication()
	{
		System.exit(0);
	}
	@FXML
	public void switchUser()
	{
		
	}
	@FXML
	public void initialize()
	{
	}
}

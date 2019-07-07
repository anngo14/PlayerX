package Controller;

import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class MainController implements Initializable{

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FadeTransition fade = new FadeTransition(Duration.millis(1450), panel);
		fade.setFromValue(1.0);
		fade.setToValue(0.95);
		
		fade.setCycleCount(Timeline.INDEFINITE);
		fade.setAutoReverse(true);
		fade.play();
		
	}
}

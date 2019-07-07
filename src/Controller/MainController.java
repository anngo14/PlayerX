package Controller;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

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
	@FXML
	public void initialize()
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
}

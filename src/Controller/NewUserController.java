package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NewUserController implements Controller, Initializable{

    private final File defaultFileName = new File("C:\\PlayerX\\Users\\users.txt");
    private final File defaultUser = new File("C:\\PlayerX\\Users\\lastUser.txt");

	private User user;
	private DirectoryChooser dirChooser = new DirectoryChooser();
	private FileChooser fileChooser = new FileChooser();
	private FileOutputStream output;
	private ArrayList<User> uList;
	
	@FXML
	TextField nameText;
	@FXML
	TextField imageText;
	@FXML
	TextField videoText;
	@FXML
	TextField musicText;
	@FXML
	StackPane panel;
	
	public NewUserController(User u, ArrayList users)
	{
		user = u;
		uList = users;
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.WELCOMEVIEW, user, Optional.empty(), Optional.empty(), Optional.empty());
	}
	@FXML
	public void saveUser()
	{
		User temp = null;
		if(!nameText.getText().equals(""))
		{
			temp = new User(nameText.getText());
		}
		if(!imageText.getText().equals(""))
		{
			temp.setImgSrc(imageText.getText());
		}
		if(!videoText.getText().equals(""))
		{
			temp.setDefaultVideo(videoText.getText());
		}
		if(!musicText.getText().equals(""))
		{
			temp.setDefaultMusic(musicText.getText());
		}
		for(User u: uList)
		{
			if(u.getUserName().equals(temp.getUserName()))
			{
				MainController.getInstance().changeView(ViewType.WELCOMEVIEW, temp, Optional.empty(), Optional.empty(), Optional.empty());
				return;
			}
		}
		try {
			output = new FileOutputStream(defaultFileName, true);
			byte[] content = temp.toString().getBytes();
			output.write(content);
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		MainController.getInstance().changeView(ViewType.WELCOMEVIEW, temp, Optional.empty(), Optional.empty(), Optional.empty());
	}
	@FXML
	public void uploadImg()
	{
		Stage stage = (Stage) panel.getScene().getWindow();
		File file = fileChooser.showOpenDialog(stage);
		
		if(file != null)
		{
			imageText.setText(file.getAbsolutePath());
		}
	}
	@FXML
	public void uploadVideoDir()
	{
		Stage stage = (Stage) panel.getScene().getWindow();
		File file = dirChooser.showDialog(stage);
		
		if(file != null)
		{
			videoText.setText(file.getAbsolutePath());
		}
	}
	@FXML
	public void uploadMusicDir()
	{
		Stage stage = (Stage) panel.getScene().getWindow();
		File file = dirChooser.showDialog(stage);
		
		if(file != null)
		{
			musicText.setText(file.getAbsolutePath());
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Music;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class SwitchUserController implements Initializable, Controller{

	private ArrayList<User> userList;
    private final File defaultUser = new File("C:\\PlayerX\\Users\\lastUser.txt");
    private final File defaultFileName = new File("C:\\PlayerX\\Users\\users.txt");
    private User user;

	
	@FXML
	ListView<User> list;
	
	public SwitchUserController(User u)
	{
		user = u;
		userList = new ArrayList<User>();
		getUsers();
	}
	public void getUsers()
	{
		FileInputStream input = null;	
		try {
			input = new FileInputStream(defaultFileName);
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader buffer = new BufferedReader(isr);
            StringBuilder builder = new StringBuilder();
            String text;
            
            while((text = buffer.readLine()) != null) {
                builder.append(text);
            }
            
            String[] users = builder.toString().split("@@");
            for(String s: users)
            {
            	String[] individual = s.split("!!");
            	User temp = new User(individual[0], individual[1]);
            	temp.setVideo(individual[2]);
            	temp.setMusic(individual[3]);
            	userList.add(temp);
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateList()
	{
		list.getItems().clear();
		ObservableList<User> uList = FXCollections.observableArrayList(userList);
		list.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
			@Override
			public ListCell<User> call(ListView<User> arg0) {
				return new UserFormatCell();
			}
			
		}); 
		list.setItems(uList);
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.WELCOMEVIEW, user, Optional.empty(), Optional.empty(), Optional.empty());
	}
	@FXML
	public void addUser()
	{
		MainController.getInstance().changeView(ViewType.ADDUSER, user, Optional.empty(), Optional.empty(), Optional.of(userList));
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list.setOnKeyPressed(new SwitchUserKeyEventHandler(list));
		list.setOnMousePressed(new SwitchUserMouseEventHandler(list));
		updateList();
	}

}

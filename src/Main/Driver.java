package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;

import Controller.MainController;
import Controller.ViewType;
import Model.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Driver extends Application{

	private static int count = 0;
    private final File directory = new File ("C:\\PlayerX\\Users\\Video");
    private final File directory2 = new File("C:\\PlayerX\\Users\\Music");
    private final File defaultFileName = new File("C:\\PlayerX\\Users\\users.txt");
    private final File defaultUser = new File("C:\\PlayerX\\Users\\lastUser.txt");
	private User lastUser;
    
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		checkDirectory();
		lastUser = getLastUser();
		try {
			URL url = this.getClass().getResource("/View/WelcomeView.fxml");
			FXMLLoader loader = new FXMLLoader(url);
			
			MainController mainController = MainController.getInstance();
			loader.setController(mainController);
			MainController.getInstance().setUser(lastUser);
			
			Parent root = loader.load();

			final Scene scene = new Scene(root);
			scene.setFill(null);
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		        public void handle(KeyEvent e) {
		            if (e.getCode() == KeyCode.ESCAPE) {
		            	count++;
		            	if(count == 2)
		            	{
		            		int input = MainController.getInstance().confirmationBox();
		            		if(input == 0)
		            		{
		            			primaryStage.close();
		            		}
		            		else
		            		{
		            			count = 0;
		            		}
		            	}
		            }
		            if(e.getCode() == KeyCode.CONTROL) {
		            	if(primaryStage.isFullScreen() == false)
		            	{
		            		primaryStage.setFullScreen(true);
		            	}
		            }
		            if(e.getCode() == KeyCode.BACK_SPACE)
		            {
		            	MainController.getInstance().changeView(ViewType.HOMEVIEW, lastUser, Optional.empty(), Optional.empty());
		            }
		        }
		    });
			scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Rubik+Mono+One&display=swap");
			scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Syncopate&display=swap");
			primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("`"));
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			primaryStage.setTitle("PlayerX");
			primaryStage.getIcons().add(new Image("Resources/Mainicon.png"));
			primaryStage.show();
						
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void checkDirectory()
	{
		FileOutputStream output = null;
		if(!directory.exists())
		{
			directory.mkdirs();
		}
		if(!directory2.exists())
		{
			directory2.mkdirs();
		}
		try {
			if(!defaultFileName.exists())
			{
				User initial = new User();
				defaultFileName.createNewFile();
				output = new FileOutputStream(defaultFileName, true);	
				byte[] content = initial.toString().getBytes();
				output.write(content);
				output.flush();
				output.close();
			}
			if(!defaultUser.exists())
			{
				User initial = new User();
				defaultUser.createNewFile();
				output = new FileOutputStream(defaultUser);	
				byte[] content = initial.toString().getBytes();
				output.write(content);
				output.flush();
				output.close();
			}
		}catch (IOException e)
		{
			e.printStackTrace();
		} finally {
			try {
				if(output != null)
				{
					output.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	public User getLastUser()
	{
		User temp = null;
		FileInputStream input = null;
		try {
			
			input = new FileInputStream(defaultUser);
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
            	temp = new User(individual[0], individual[1]);
            	temp.setVideo(individual[2]);
            	temp.setMusic(individual[3]);
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

}

package Main;

import java.net.URL;

import Controller.MainController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Driver extends Application{

	private static int count = 0;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			URL url = this.getClass().getResource("/View/WelcomeView.fxml");
			FXMLLoader loader = new FXMLLoader(url);
			
			MainController mainController = MainController.getInstance();
			loader.setController(mainController);
			
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
		            if(e.getCode() == KeyCode.F) {
		            	if(primaryStage.isFullScreen() == false)
		            	{
		            		primaryStage.setFullScreen(true);
		            	}
		            }
		            if(e.getCode() == KeyCode.ENTER)
		            {
		            	MainController.getInstance().loginAction();
		            }
		        }
		    });
			scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Rubik+Mono+One&display=swap");
			primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("`"));
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);;
			primaryStage.setTitle("PlayerX");
			primaryStage.getIcons().add(new Image("Resources/Mainicon.png"));
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import Model.Music;
import Model.User;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class SwitchUserMouseEventHandler implements EventHandler<MouseEvent>{
	private ListView<User> node;
	private FileOutputStream output;
    private final File defaultUser = new File("C:\\PlayerX\\Users\\lastUser.txt");

	
	public SwitchUserMouseEventHandler(Node n)
	{
		node = (ListView) n;
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			User select = node.getSelectionModel().getSelectedItem();
			MainController.getInstance().changeView(ViewType.WELCOMEVIEW, select, Optional.empty(), Optional.empty(), Optional.empty());
			try {
				output = new FileOutputStream(defaultUser);
				byte[] content = select.toString().getBytes();
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
			
		}
	}

}

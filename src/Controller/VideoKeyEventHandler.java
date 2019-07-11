package Controller;

import java.util.Optional;

import Model.Video;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class VideoKeyEventHandler implements EventHandler<KeyEvent>{

	private ListView<Video> node;
	private Video select;
	
	public VideoKeyEventHandler(Node n)
	{
		node = (ListView) n;
	}
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			select = node.getSelectionModel().getSelectedItem();
			MainController.getInstance().changeView(ViewType.VIDEOPLAYERVIEW, Optional.of(select), Optional.empty());
		}
		
	}

}

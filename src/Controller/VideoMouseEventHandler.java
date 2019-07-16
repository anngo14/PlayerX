package Controller;

import java.util.Optional;

import Model.User;
import Model.Video;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class VideoMouseEventHandler implements EventHandler<MouseEvent>{

	private ListView<Video> node;
	private Video select;
	private User user;
	
	public VideoMouseEventHandler(Node n, User u)
	{
		user = u;
		node = (ListView) n;
	}
	@Override
	public void handle(MouseEvent arg0) {
		if(arg0.isPrimaryButtonDown() && arg0.getClickCount() == 2)
		{
			select = node.getSelectionModel().getSelectedItem();
			MainController.getInstance().changeView(ViewType.VIDEOPLAYERVIEW, user, Optional.of(select), Optional.empty(), Optional.empty());
		}
	}

}

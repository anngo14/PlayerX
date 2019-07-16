package Controller;

import java.util.ArrayList;
import java.util.Optional;

import Model.Music;
import Model.User;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class MusicMouseEventHandler implements EventHandler<MouseEvent>{

	private ListView<Music> node;
	private ArrayList<Music> musicList;
	private User user;
	public MusicMouseEventHandler(Node n, User u, ArrayList<Music> mList)
	{
		user = u;
		node = (ListView) n;
		musicList = mList;
	}
	@Override
	public void handle(MouseEvent event) {
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			Music select = node.getSelectionModel().getSelectedItem();
			MainController.getInstance().changeView(ViewType.MUSICPLAYERVIEW, user, Optional.of(select), Optional.of(musicList), Optional.empty());	
		}
	}

}

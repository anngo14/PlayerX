package Controller;

import java.util.ArrayList;
import java.util.Optional;

import Model.Music;
import Model.User;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MusicKeyEventHandler implements EventHandler<KeyEvent>{

	private ListView<Music> node;
	private ArrayList<Music> musicList;
	private User user;
	
	public MusicKeyEventHandler(Node n, User u, ArrayList<Music> mList)
	{
		user = u;
		node = (ListView) n;
		musicList = mList;
	}
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER)
		{
			Music select = node.getSelectionModel().getSelectedItem();
			MainController.getInstance().changeView(ViewType.MUSICPLAYERVIEW, user,  Optional.of(select), Optional.of(musicList));
		}
	}

}

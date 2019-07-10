package Controller;

import java.util.ArrayList;
import java.util.Optional;

import Model.Music;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MusicKeyEventHandler implements EventHandler<KeyEvent>{

	private ListView<Music> node;
	private ArrayList<Music> musicList;
	
	public MusicKeyEventHandler(Node n, ArrayList<Music> mList)
	{
		node = (ListView) n;
		musicList = mList;
	}
	@Override
	public void handle(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER)
		{
			Music select = node.getSelectionModel().getSelectedItem();
			MainController.getInstance().changeView(ViewType.MUSICPLAYERVIEW, Optional.of(select), Optional.of(musicList));
		}
	}

}

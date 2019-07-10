package Controller;

import java.util.ArrayList;
import java.util.Optional;

import Model.Music;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class MusicMouseEventHandler implements EventHandler<MouseEvent>{

	private ListView<Music> node;
	private ArrayList<Music> musicList;
	public MusicMouseEventHandler(Node n, ArrayList<Music> mList)
	{
		node = (ListView) n;
		musicList = mList;
	}
	@Override
	public void handle(MouseEvent event) {
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			Music select = node.getSelectionModel().getSelectedItem();
			MainController.getInstance().changeView(ViewType.MUSICPLAYERVIEW, Optional.of(select), Optional.of(musicList));	
		}
	}

}

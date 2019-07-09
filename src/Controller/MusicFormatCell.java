package Controller;

import Model.Music;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MusicFormatCell extends ListCell<Music>{
	public MusicFormatCell() { }
	
	@Override
	protected void updateItem(Music m, boolean b) {
		super.updateItem(m, b);
		if(m == null)
		{
			setText(null);
		}
		else{
			Image img = new Image(m.getPreviewImg(), 80, 80, false, false);
			ImageView imgView = new ImageView(img);
			setGraphic(imgView);
			Label fileLabel = new Label(m.getfileName());
			setText(m.getfileName());
		}
		
	}

}

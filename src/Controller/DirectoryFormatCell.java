package Controller;

import Model.Video;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DirectoryFormatCell extends ListCell<String>{
	public DirectoryFormatCell() {}
	@Override
	protected void updateItem(String s, boolean b) {
		super.updateItem(s, b);
		if(s == null)
		{
			setText(null);
		}
		else{
			Image img = new Image("Resources/directoryicon.png", 80, 80, false, false);
			ImageView imgView = new ImageView(img);
			setGraphic(imgView);
			Label fileLabel = new Label();
			setText(s);
		}
		
	}
}

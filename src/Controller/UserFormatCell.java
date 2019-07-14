package Controller;

import Model.Music;
import Model.User;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserFormatCell extends ListCell<User>{
	public UserFormatCell() {
		
	}
	
	@Override
	protected void updateItem(User u, boolean b) {
		super.updateItem(u, b);
		if(u == null)
		{
			setText(null);
		}
		else{
			Image img = new Image(u.getImgSrc(), 80, 80, false, false);
			ImageView imgView = new ImageView(img);
			setGraphic(imgView);
			Label fileLabel = new Label(u.getUserName());
			setText(u.getUserName());
		}
		
	}
}

package Controller;

import Model.Video;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VideoFormatCell extends ListCell<Video>{
	public VideoFormatCell() { }
		@Override
		protected void updateItem(Video v, boolean b) {
			super.updateItem(v, b);
			if(v == null)
			{
				setText(null);
			}
			else{
				Image img = new Image(v.getPreviewImg(), 80, 80, false, false);
				ImageView imgView = new ImageView(img);
				setGraphic(imgView);
				Label fileLabel = new Label(v.getfileName());
				setText(v.getfileName());
			}
			
		}
}

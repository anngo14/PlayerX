package Controller;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Model.Music;
import Model.Video;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class MusicController implements Initializable, Controller{

    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final File defaultDir = new File("C:\\Users\\Andrew\\Music");
	private DirectoryChooser dirChooser = new DirectoryChooser();
	private ArrayList<Music> musicList;
	
	@FXML
	Label titleLabel;
	@FXML
	Label timeLabel;
	@FXML
	TextField pathTextField;
	@FXML
	Button fileButton;
	@FXML
	ListView<Music> list1;
	@FXML
	ListView<Music> list2;
	@FXML
	Button homeButton;
	@FXML
	StackPane panel;
	
	public MusicController()
	{
		musicList = new ArrayList<Music>();
	}
	
	@FXML
	public void uploadDir()
	{
		Stage stage = (Stage) panel.getScene().getWindow();
		File file = dirChooser.showDialog(stage);
		
		if(file != null)
		{
			pathTextField.setText(file.getAbsolutePath());
		}
		
		listAllFiles(file.getAbsolutePath());
		updateListView();	
	}
	public void listAllFiles(String path)
	{
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (File f : files)
	    {
			if (f.isFile())
	        {
				Music temp = new Music(f.getName());
				temp.setPath(f.getAbsolutePath());
				musicList.add(temp);
	        }
	        else if (f.isDirectory())
	        {
	            listAllFiles(f.getAbsolutePath());
	        }
	    }
	}
	public void updateListView()
	{
		list1.getItems().clear();
		ObservableList<Music> fLists = FXCollections.observableArrayList(musicList);
		list1.setCellFactory(new Callback<ListView<Music>, ListCell<Music>>() {

			@Override
			public ListCell<Music> call(ListView<Music> arg0) {
				ListCell<Music> cell = new ListCell<Music>() {
					@Override
					protected void updateItem(Music m, boolean b) {
						super.updateItem(m, b);
						if(m == null)
						{
							setText(null);
						}
						else{
							Image img = new Image("Resources/mediafile.png", 100, 100, false, false);
							ImageView imgView = new ImageView(img);
							setGraphic(imgView);
							Label fileLabel = new Label(m.getfileName());
							setText(m.getfileName());
						}
						
					}
				}; 
				return cell;
			}
			
		});  	
		list1.setItems(fLists);
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.HOMEVIEW);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dirChooser.setInitialDirectory(defaultDir);
		pathTextField.setText(defaultDir.getAbsolutePath());
		listAllFiles(defaultDir.getAbsolutePath());
		titleLabel.setText("Music");
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		updateListView();
	}

}

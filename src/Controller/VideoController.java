package Controller;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Video;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
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

public class VideoController implements Controller, Initializable{

	FadeTransition fade;
    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private File defaultDir = new File("C:\\Users\\Andrew\\Videos");
	private DirectoryChooser dirChooser = new DirectoryChooser();
	private ArrayList<Video> videoList;
	
	@FXML
	StackPane panel;
	@FXML
	Label titleLabel;
	@FXML
	Label timeLabel;
	@FXML
	TextField pathTextField;
	@FXML
	Button fileButton;
	@FXML
	ListView<Video> list1;
	@FXML
	ListView<Video> list2;
	@FXML
	Button homeButton;
	
	public VideoController()
	{
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
		videoList.clear();
		File folder = new File(path);
		File[] files = folder.listFiles();
		for (File f : files)
	    {
			if (f.isFile())
	        {
				Video temp = new Video(f.getName());
				temp.setPath(f.getAbsolutePath());
				videoList.add(temp);
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
		ObservableList<Video> ovList = FXCollections.observableArrayList(videoList);
		list1.setCellFactory(new Callback<ListView<Video>, ListCell<Video>>() {

			@Override
			public ListCell<Video> call(ListView<Video> arg0) {
				ListCell<Video> cell = new ListCell<Video>() {
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
				}; 
				return cell;
			}
			
		});  	
		list1.setItems(ovList);
	}
	@FXML
	public void changeDefault()
	{
		
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.HOMEVIEW);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		videoList = new ArrayList<Video>();
		dirChooser.setInitialDirectory(defaultDir);
		pathTextField.setText(defaultDir.getAbsolutePath());
		listAllFiles(defaultDir.getAbsolutePath());
		titleLabel.setText("Video");
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		updateListView();

	}

}

package Controller;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Music;
import Model.Video;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	private ArrayList<Video> videoList1;
	private ArrayList<Video> videoList2;
	
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
	@FXML
	ImageView defaultIcon;
	
	public VideoController()
	{
		videoList = new ArrayList<Video>();
		listAllFiles(defaultDir.getAbsolutePath());
		dirChooser.setInitialDirectory(defaultDir);
		dirChooser.setTitle("Select Video Directory");

	}
	
	@FXML
	public void uploadDir()
	{
		videoList.clear();
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
				Video temp = new Video(f.getName());
				temp.setPath(f.getAbsolutePath());
				videoList.add(temp);
	        }
	        else if (f.isDirectory())
	        {
	            listAllFiles(f.getAbsolutePath());
	        }
	    }
		videoList = sanitizeVideoList();
	}
	public ArrayList<Video> sanitizeVideoList()
	{
		ArrayList<Video> temp = new ArrayList<Video>();
		for(Video m: videoList)
		{
			if(m.getfileName().endsWith(".mp4") || m.getfileName().endsWith(".flv"))
			{
				temp.add(m);
			}
		}
		return temp;
	}
	public void updateListView()
	{
		int size = videoList.size();
		if(size != 0)
		{
			videoList1 = new ArrayList<Video>(videoList.subList(0, (size + 1)/2));
			videoList2 = new ArrayList<Video>(videoList.subList((size + 1)/2, size));
		}
		else
		{
			videoList1 = new ArrayList<Video>();
			videoList2 = new ArrayList<Video>();
		}
		list1.getItems().clear();
		list2.getItems().clear();
		ObservableList<Video> ovList = FXCollections.observableArrayList(videoList1);
		ObservableList<Video> ovList2 = FXCollections.observableArrayList(videoList2);
		list1.setCellFactory(new Callback<ListView<Video>, ListCell<Video>>() {
			@Override
			public ListCell<Video> call(ListView<Video> arg0) {
				return new VideoFormatCell();
			}
			
		});
		list2.setCellFactory(new Callback<ListView<Video>, ListCell<Video>>() {
			@Override
			public ListCell<Video> call(ListView<Video> arg0) {
				return new VideoFormatCell();
			}
			
		});  	
		list1.setItems(ovList);
		list2.setItems(ovList2);
	}
	@FXML
	public void changeDefault()
	{
		defaultDir = new File(pathTextField.getText());
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.HOMEVIEW, Optional.empty(), Optional.empty());
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		titleLabel.setText("Video");
		pathTextField.setText(defaultDir.getAbsolutePath());
		
		list1.focusedProperty().addListener(new ListChangeListener(list1));
		list2.focusedProperty().addListener(new ListChangeListener(list2));
		
		list1.setOnMousePressed(new VideoMouseEventHandler());
		list2.setOnMousePressed(new VideoMouseEventHandler());
		
		list1.setOnKeyPressed(new VideoKeyEventHandler());
		list2.setOnKeyPressed(new VideoKeyEventHandler());
		
		updateListView();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

	}

}

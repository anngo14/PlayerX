package Controller;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import Model.Music;
import javafx.animation.Animation;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class MusicController implements Initializable, Controller{

    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private File defaultDir = new File("C:\\Users\\Andrew\\Music");
	private DirectoryChooser dirChooser = new DirectoryChooser();
	private ArrayList<Music> musicList;
	private ArrayList<Music> musicList1;
	private ArrayList<Music> musicList2;
	
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
		listAllFiles(defaultDir.getAbsolutePath());
		dirChooser.setInitialDirectory(defaultDir);
		dirChooser.setTitle("Select Music Directory");
	}
	@FXML
	public void uploadDir()
	{
		musicList.clear();
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
		musicList = sanitizeMusicList();
	}
	public void updateListView()
	{
		int size = musicList.size();
		if(size != 0)
		{
			musicList1 = new ArrayList<Music>(musicList.subList(0, (size + 1)/2));
			musicList2 = new ArrayList<Music>(musicList.subList((size + 1)/2, size));
		}
		else
		{
			musicList1 = new ArrayList<Music>();
			musicList2 = new ArrayList<Music>();
		}
		list1.getItems().clear();
		list2.getItems().clear();
		ObservableList<Music> fLists = FXCollections.observableArrayList(musicList1);
		ObservableList<Music> flists2 = FXCollections.observableArrayList(musicList2);
		
		list1.setCellFactory(new Callback<ListView<Music>, ListCell<Music>>() {
			@Override
			public ListCell<Music> call(ListView<Music> arg0) {
				return new MusicFormatCell();
			}
			
		});  
		list2.setCellFactory(new Callback<ListView<Music>, ListCell<Music>>() {
			@Override
			public ListCell<Music> call(ListView<Music> arg0) {
				return new MusicFormatCell();
			}
			
		});  
		list1.setItems(fLists);
		list2.setItems(flists2);
	}
	public ArrayList<Music> sanitizeMusicList()
	{
		ArrayList<Music> temp = new ArrayList<Music>();
		for(Music m: musicList)
		{
			if(m.getfileName().endsWith(".mp3") || m.getfileName().endsWith(".wav") ||m.getfileName().endsWith(".aac"))
			{
				temp.add(m);
			}
		}
		return temp;
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		titleLabel.setText("Music");
		pathTextField.setText(defaultDir.getAbsolutePath());
		
		list1.focusedProperty().addListener(new ListChangeListener(list1));
		list2.focusedProperty().addListener(new ListChangeListener(list2));
		
		list1.setOnMousePressed(new MusicMouseEventHandler(list1, musicList));
		list2.setOnMousePressed(new MusicMouseEventHandler(list2, musicList));

		list1.setOnKeyPressed(new MusicKeyEventHandler(list1, musicList));
		list2.setOnKeyPressed(new MusicKeyEventHandler(list2, musicList));

		updateListView();

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
	}

}

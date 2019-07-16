package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import Model.Music;
import Model.User;
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
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class MusicController implements Initializable, Controller{

    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
	private DirectoryChooser dirChooser = new DirectoryChooser();
	private ArrayList<Music> musicList;
	private ArrayList<Music> musicList1;
	private ArrayList<Music> musicList2;
	private User user;
	
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
	
	public MusicController(User u)
	{
		user = u;
		musicList = new ArrayList<Music>();
		dirChooser.setTitle("Select Music Directory");
		File defaultDirectory = new File(u.getMusic());
		try {
			if(!defaultDirectory.exists())
			{
				defaultDirectory.createNewFile();
				createDefaults();
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		listAllFiles(getDefaultDir().getAbsolutePath());
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
		dirChooser.setInitialDirectory(file.getAbsoluteFile());
		updateListView();	
	}
	public void listAllFiles(String path)
	{
		File folder = new File(path);
		File[] files = folder.listFiles();
		if(files == null)
		{
			return;
		}
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
	public File getDefaultDir() {
		FileInputStream input = null;
		File defaultDirectory = null;
		
		try {
			input = new FileInputStream(user.getMusic());
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader buffer = new BufferedReader(isr);
            StringBuilder builder = new StringBuilder();
            String text;
            
            while((text = buffer.readLine()) != null) {
                builder.append(text);
            }
    		defaultDirectory = new File(builder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return defaultDirectory;
	}
	public void createDefaults()
	{
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(user.getMusic());	
			byte[] content = user.getDefaultMusic().getBytes();
			output.write(content);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(output != null)
				{
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@FXML
	public void changeDefault()
	{
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(user.getMusic());	
			byte[] content = pathTextField.getText().getBytes();
			output.write(content);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(output != null)
				{
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.HOMEVIEW, user, Optional.empty(), Optional.empty(), Optional.empty());
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		titleLabel.setText("Music");
		pathTextField.setText(getDefaultDir().getAbsolutePath());
		
		list1.focusedProperty().addListener(new ListChangeListener(list1));
		list2.focusedProperty().addListener(new ListChangeListener(list2));
		
		list1.setOnMousePressed(new MusicMouseEventHandler(list1, user, musicList));
		list2.setOnMousePressed(new MusicMouseEventHandler(list2, user, musicList));

		list1.setOnKeyPressed(new MusicKeyEventHandler(list1, user, musicList));
		list2.setOnKeyPressed(new MusicKeyEventHandler(list2, user, musicList));

		updateListView();

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
	}

}

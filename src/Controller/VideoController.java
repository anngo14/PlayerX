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
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.MediaItem;
import Model.User;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

public class VideoController implements Controller, Initializable{

	FadeTransition fade;
    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
	private DirectoryChooser dirChooser = new DirectoryChooser();
	private ArrayList<Video> videoList;
	private ArrayList<String> dirList;
	
	private User user;
	
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
	ListView<String> list1;
	@FXML
	ListView<Video> list2;
	@FXML
	Button homeButton;
	@FXML
	ImageView defaultIcon;
	
	public VideoController(User u)
	{
		user = u;
		videoList = new ArrayList<Video>();
		dirList = new ArrayList<String>();
		dirList.add("All Videos");
		dirChooser.setTitle("Select Video Directory");
		File defaultDirectory = new File(u.getVideo());
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
		videoList.clear();
		dirList.clear();
		dirList.add("All Videos");
		Stage stage = (Stage) panel.getScene().getWindow();
		File file = dirChooser.showDialog(stage);
		
		if(file != null)
		{
			pathTextField.setText(file.getAbsolutePath());
		}
		listAllFiles(file.getAbsolutePath());
		dirChooser.setInitialDirectory(file.getAbsoluteFile());
		updateListView();
		list1.getSelectionModel().selectedItemProperty().addListener(new DirectoryChangeListener(list2, pathTextField.getText()));

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
				Video temp = new Video(f.getName());
				temp.setPath(f.getAbsolutePath());
				videoList.add(temp);
	        }
	        else if (f.isDirectory())
	        {
	        	dirList.add(f.getName());
	            //listAllFiles(f.getAbsolutePath());
	        }
	    }
	}
	public ArrayList<Video> sanitizeVideoList()
	{
		ArrayList<Video> temp = new ArrayList<Video>();
		for(Video m: videoList)
		{
			if(m.getfileName().endsWith(".mp4") || m.getfileName().endsWith(".flv") || m.getfileName().endsWith(".avi") || m.getfileName().endsWith(".mkv") || m.getfileName().endsWith(".mov"))
			{
				temp.add(m);
			}
		}
		return temp;
	}
	public ArrayList<Video> reverseVideoList(){
		ArrayList<Video> reverse = new ArrayList<Video>();
		int count = this.videoList.size() - 1;
		for(int i = 0; i < this.videoList.size(); i++) {
			reverse.add(this.videoList.get(count));
			count--;
		}
		return reverse;
	}
	public void updateListView()
	{
		list1.getItems().clear();
		list2.getItems().clear();
		ObservableList<String> ovList = FXCollections.observableArrayList(dirList);
		ObservableList<Video> ovList2 = FXCollections.observableArrayList(videoList);
		list1.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> arg0) {
				return new DirectoryFormatCell();
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
	public File getDefaultDir() {
		FileInputStream input = null;
		File defaultDirectory = null;
		
		try {
			input = new FileInputStream(user.getVideo());
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
			output = new FileOutputStream(user.getVideo());	
			byte[] content = user.getDefaultVideo().getBytes();
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
			
			output = new FileOutputStream(user.getVideo());	
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
		updateListView();
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.HOMEVIEW, user, Optional.empty(), Optional.empty(), Optional.empty());
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		titleLabel.setText("Video");
		pathTextField.setText(getDefaultDir().getAbsolutePath());
		
		list1.focusedProperty().addListener(new ListChangeListener(list1));
		list2.focusedProperty().addListener(new ListChangeListener(list2));
		
		list1.getSelectionModel().selectedItemProperty().addListener(new DirectoryChangeListener(list2, pathTextField.getText()));
		list2.setOnMousePressed(new VideoMouseEventHandler(list2, user));
		
		list2.setOnKeyPressed(new VideoKeyEventHandler(list2, user));
		
		updateListView();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
				event -> timeLabel.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
				new KeyFrame(Duration.seconds(1)));

		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

	}

}

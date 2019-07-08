package Controller;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VideoController implements Controller, Initializable{

	FadeTransition fade;
    private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    HashMap<String, String> fileMap;
    private final File defaultDir = new File("C:\\Users\\Andrew\\Videos");
	final DirectoryChooser dirChooser = new DirectoryChooser();
	
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
	ListView<String> list2;
	@FXML
	Button homeButton;
	
	public VideoController()
	{
		fileMap = new HashMap<String, String>();
	}
	
	@FXML
	public void uploadDir()
	{
		fileMap = new HashMap<String, String>();
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
				fileMap.put(f.getName(), f.getAbsolutePath());
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
		ObservableList<String> fLists = FXCollections.observableArrayList(fileMap.keySet());
		list1.getItems().addAll(fLists);
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.HOMEVIEW);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
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

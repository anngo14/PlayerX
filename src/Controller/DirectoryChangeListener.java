package Controller;

import java.io.File;
import java.util.ArrayList;

import Model.Video;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class DirectoryChangeListener implements ChangeListener<String>{

	private ArrayList<Video> videoList;
	private String directory;
	ListView<Video> list2;
	
	public DirectoryChangeListener(Node n, String dir)
	{
		list2 = (ListView) n;
		videoList = new ArrayList<Video>();
		directory = dir;
	}
	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if(!newValue.equals(oldValue))
		{
			videoList.clear();
			list2.getItems().clear();

			if(newValue.equals("All Videos"))
			{
				listAllFiles(directory);
			}
			else
			{
				listAllFiles(directory.concat("\\" + newValue));
			}
			ObservableList<Video> ovList2 = FXCollections.observableArrayList(videoList);
			list2.setCellFactory(new Callback<ListView<Video>, ListCell<Video>>() {
				@Override
				public ListCell<Video> call(ListView<Video> arg0) {
					return new VideoFormatCell();
				}
			});  	
			list2.setItems(ovList2);
		}
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
			if(m.getfileName().endsWith(".mp4") || m.getfileName().endsWith(".flv") || m.getfileName().endsWith(".avi") || m.getfileName().endsWith(".mkv") || m.getfileName().endsWith(".mov"))
			{
				temp.add(m);
			}
		}
		return temp;
	}

}

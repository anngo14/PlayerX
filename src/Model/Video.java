package Model;

import java.io.File;
import java.util.ArrayList;

public class Video extends MediaItem{

	private static ArrayList<String> videoSrc;
	
	public Video()
	{
		super("video");
		videoSrc = new ArrayList<String>();
	}
	@Override
	public void playMedia(String location) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addFolder(String location) {
		File directory = new File(location);
		File[] content = directory.listFiles();
		for(File f: content) {
			videoSrc.add(f.getAbsolutePath());
		}
	}
	@Override
	public void addFile(String location) {
		videoSrc.add(location);
	}

}

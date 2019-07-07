package Model;

import java.io.File;
import java.util.ArrayList;


public class Music extends MediaItem{

	private ArrayList<String> musicSrc;
	
	public Music()
	{
		super("music", "UNKNOWN");
		musicSrc = new ArrayList<String>();
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
			musicSrc.add(f.getAbsolutePath());
		}
	}

	@Override
	public void addFile(String location) {
		musicSrc.add(location);
	}
}

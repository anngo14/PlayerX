package Model;

import java.util.ArrayList;

public class Music extends MediaItem{

	private static ArrayList<String> musicSrc = new ArrayList<String>();
	
	public Music()
	{
		super("music", musicSrc);
	}

	@Override
	public void playMedia(String location) {
		// TODO Auto-generated method stub
		
	}
}

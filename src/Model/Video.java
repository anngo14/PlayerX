package Model;

import java.util.ArrayList;

public class Video extends MediaItem{

	private static ArrayList<String> videoSrc = new ArrayList<String>();
	
	public Video()
	{
		super("video", videoSrc);
	}
	@Override
	public void playMedia(String location) {
		// TODO Auto-generated method stub
		
	}

}

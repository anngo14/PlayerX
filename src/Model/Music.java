package Model;

import java.io.File;
import java.util.ArrayList;


public class Music extends MediaItem{
	
	public Music()
	{
		super("MUSIC", "UNKNOWN");
	}
	public Music(String name)
	{
		super("MUSIC", name);
	}
	public Music(String name, String img)
	{
		super("MUSIC", name, img);
	}
	public Music(String name, String img, String path)
	{
		super("MUSIC", name, img, path);
	}


}

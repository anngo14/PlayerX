package Model;

import java.io.File;
import java.util.ArrayList;

public class Video extends MediaItem{
	
	public Video()
	{
		super("VIDEO", "UNKNOWN");
	}
	public Video(String name)
	{
		super("VIDEO", name);
	}
	public Video(String name, String path)
	{
		super("VIDEO", name, path);
	}
	public Video(String name, String img, String path)
	{
		super("VIDEO", name, img, path);
	}

}

package Model;

import java.util.ArrayList;

public abstract class MediaItem {

	private String mediaType;
	
	public MediaItem()
	{
		mediaType = "UNKNOWN";
	}
	public MediaItem(String type)
	{
		mediaType = type;
	}
	
	public abstract void playMedia(String location);
	public abstract void addFolder(String location);
	public abstract void addFile(String location);
	
	public String getMediaType()
	{
		return mediaType;
	}
	public void setMediaType(String type)
	{
		mediaType = type;
	}
}

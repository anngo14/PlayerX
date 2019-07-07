package Model;

import java.util.ArrayList;

public abstract class MediaItem {

	private String mediaType;
	private String fileName;
	private String previewImg;
	
	public MediaItem()
	{
		mediaType = "UNKNOWN";
		fileName = "UNKNOWN";
		previewImg = "";
	}
	public MediaItem(String type, String name)
	{
		mediaType = type;
		fileName = name;
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

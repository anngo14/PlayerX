package Model;

import java.util.ArrayList;

public abstract class MediaItem {

	private String mediaType;
	private String fileName;
	private String previewImg;
	private String path;
	
	public MediaItem()
	{
		mediaType = "UNKNOWN";
		fileName = "UNKNOWN";
		previewImg = "";
		path = "";
	}
	public MediaItem(String type)
	{
		mediaType = type;
		fileName = "UNKNOWN";
		previewImg = "";
		path = "";
	}
	public MediaItem(String type, String name)
	{
		mediaType = type;
		fileName = name;
		previewImg = "";
		path = "";
	}
	public MediaItem(String type, String name, String path)
	{
		mediaType = type;
		fileName = name;
		previewImg = path;
		this.path = "";
	}
	public MediaItem(String type, String name, String img, String location)
	{
		mediaType = type;
		fileName = name;
		previewImg = img;
		path = location;
	}
	
	public String getMediaType()
	{
		return mediaType;
	}
	public void setMediaType(String type)
	{
		mediaType = type;
	}
	public String getfileName()
	{
		return fileName;
	}
	public void setFileName(String name)
	{
		fileName = name;
	}
	public String getPreviewImg()
	{
		return previewImg;
	}
	public void setPreviewImg(String path)
	{
		previewImg = path;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String location)
	{
		path = location;
	}
}

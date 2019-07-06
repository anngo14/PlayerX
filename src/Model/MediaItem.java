package Model;

import java.util.ArrayList;

public abstract class MediaItem {

	private ArrayList<String> srcLinks;
	private String mediaType;
	
	public MediaItem()
	{
		mediaType = "";
		srcLinks = null;
	}
	public MediaItem(String type, ArrayList<String> links)
	{
		mediaType = type;
		srcLinks = links;
	}
	public abstract void playMedia(String location);
	
	public ArrayList<String> getSrcLinks()
	{
		return srcLinks;
	}
	public void setSrcLinks(ArrayList<String> links)
	{
		srcLinks = links;
	}
	public void addSrc(String location)
	{
		srcLinks.add(location);
	}
	public void deleteSrc(String location)
	{
		srcLinks.remove(location);
	}
	public void replaceSrc(String oldSrc, String newSrc)
	{
		srcLinks.remove(oldSrc);
		srcLinks.add(newSrc);
	}
	public String getMediaType()
	{
		return mediaType;
	}
	public void setMediaType(String type)
	{
		mediaType = type;
	}
}

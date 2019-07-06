package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

	private String userName;
	private String imgSrc;
	private HashMap<String, ArrayList<String>> srcLinks;
	
	public User()
	{
		userName = "Default";
		imgSrc = "Resources/Duser.png";
		
		ArrayList<String> videoSrc = new ArrayList<String>();
		ArrayList<String> musicSrc = new ArrayList<String>();
		srcLinks.put("video", videoSrc);
		srcLinks.put("music", musicSrc);
	}
	
	public User(String name, String img, HashMap<String, ArrayList<String>> links)
	{
		userName = name;
		imgSrc = img;
		srcLinks = links;
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	public String getImgSrc()
	{
		return this.imgSrc;
	}
	public ArrayList<String> getSrcLinks(String type)
	{
		return srcLinks.get(type);
	}
	public void setUserName(String name)
	{
		userName = name;
	}
	public void setImgSrc(String location)
	{
		imgSrc = location;
	}
	public void setSrcLinks(String type, ArrayList<String> location)
	{
		srcLinks.put(type, location);
	}
	
}

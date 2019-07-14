package Model;


public class User {

	private String userName;
	private String imgSrc;
	private String videoDirectory;
	private String musicDirectory;
	private String first;
	
	public User()
	{
		userName = "John Smith";
		first = getFirst(userName);
		imgSrc = "Resources/rounded-512.png";
		videoDirectory = "C:\\PlayerX\\Users\\Video\\" + first + "videoDirectory.txt";
		musicDirectory = "C:\\PlayerX\\Users\\Music\\" + first + "musicDirectory.txt";
	}
	
	public User(String name, String img)
	{
		userName = name;
		imgSrc = img;
		first = getFirst(userName);
		videoDirectory = "C:\\PlayerX\\Users\\Videos\\" + first + "videoDirectory.txt";
		musicDirectory = "C:\\PlayerX\\Users\\Music\\" + first + "musicDirectory.txt";
	}
	public String getFirst(String name)
	{
		String output = name.replaceAll("\\s" , "");
		return output;
	}
	public String getUserName()
	{
		return this.userName;
	}
	public String getImgSrc()
	{
		return this.imgSrc;
	}
	public void setUserName(String name)
	{
		userName = name;
	}
	public void setImgSrc(String location)
	{
		imgSrc = location;
	}
	public String getVideo()
	{
		return videoDirectory;
	}
	public void setVideo(String dir)
	{
		videoDirectory = dir;
	}
	public String getMusic()
	{
		return musicDirectory;
	}
	public void setMusic(String dir)
	{
		musicDirectory = dir;
	}
	public String toString()
	{
		String output = userName+ "!!" + imgSrc + "!!" + videoDirectory + "!!" + musicDirectory + "@@";
		return output;
	}
}

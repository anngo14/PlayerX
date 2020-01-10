package Model;

import java.util.ArrayList;
import java.util.Comparator;

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
	public int compareTo(MediaItem i2) {
		return this.fileName.compareTo(i2.fileName);
	}
	public static Comparator<MediaItem> nameComparator = new Comparator<MediaItem>() {         
		@Override         
		public int compare(MediaItem m1, MediaItem m2) {
			return (int) (m1.getfileName().compareTo(m2.getfileName()));         
		}     
	};
	public static Comparator<MediaItem> naturalComparator = new Comparator<MediaItem>() {
		
		public boolean hasDigits(String s) {
			if(s.matches("^.*\\d+.mp4$")) {
				return true;
			}
			return false;
		}

		@Override
		public int compare(MediaItem m1, MediaItem m2) {
			String d1 = null;
			String d2 = null;
			if(hasDigits(m1.getfileName())){
				d1 = m1.getfileName();
			}
			if(hasDigits(m2.getfileName())) {
				d2 = m2.getfileName();
			}
			if(d1 == null && d2 != null) {
				return 1;
			}
			else if(d1 != null && d2 == null) {
				return -1;
			}
			else if(d1 == null && d2 == null) {
				return (int) m1.getfileName().compareTo(m2.getfileName());
			}
			else {
				String[] temp1 = d1.split("\\s");
				String[] temp2 = d2.split("\\s");
				String[] num1 = temp1[temp1.length - 1].split("\\.");
				String[] num2 = temp2[temp2.length - 1].split("\\.");

				if(Integer.parseInt(num1[0]) > Integer.parseInt(num2[0])) {
					return -1;
				}
				else if(Integer.parseInt(num1[0]) < Integer.parseInt(num2[0])) {
					return 1;
				}
				else {
					return (int) m1.getfileName().compareTo(m2.getfileName());
				}
			}
		}
	};

}

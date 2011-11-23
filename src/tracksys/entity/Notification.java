package tracksys.entity;
import java.util.Date;
public class Notification {
	private Date timestamp;
	private String title;
	private String message;
	private int id;
	
	public Notification() { }
	public Notification(Date d, String t, String m, int id)
	{
		this.timestamp = d;
		this.title = t;
		this.message = m;
		this.id = id;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public String title()
	{
		return this.title;
	}
	
	public Date getTimeStamp()
	{
		return this.timestamp;
	}
	
	public int getID()
	{
		return this.id;
	}
}

package tracksys.entity;
import java.util.Date;
public class Notification {
	private Date timestamp;
	private String title;
	private String message;
	
	public Notification() { }
	public Notification(Date d, String t, String m)
	{
		this.timestamp = d;
		this.title = t;
		this.message = m;
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
}

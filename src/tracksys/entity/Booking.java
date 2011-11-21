package tracksys.entity;
import java.util.Date;

public class Booking {
	private int id;
	private int clubID;
	private String clubName;
	private int trackID;
	private Date startTime;
	private Date endTime;
	private Date bookedTime;
	private String comment;
	
	public Booking(int id, int clubID, String clubName, int trackID, Date start, Date end, Date booked, String comment)
	{
		this.id = id;
		this.clubName = clubName;
		this.clubID = clubID;
		this.trackID = trackID;
		this.startTime = start;
		this.endTime = end;
		this.bookedTime = booked;
		this.comment = comment;
	}
	
	public Booking(int clubID, String clubName, int trackID, Date start, Date end, Date booked, String comment)
	{
		this.clubID = clubID;
		this.clubName = clubName;
		this.trackID = trackID;
		this.startTime = start;
		this.endTime = end;
		this.bookedTime = booked;
		this.comment = comment;
	}
	
	public int getID()
	{
		return id;
	}
	
	public int getTrackID()
	{
		return trackID;
	}
	
	public int getClubID()
	{
		return clubID;
	}
	
	public String getClubName()
	{
		return clubName;
	}
	
	public Date getStartTime()
	{
		return startTime;
	}
	
	public Date getEndTime()
	{
		return endTime;
	}
	
	public Date getBookedTime()
	{
		return bookedTime;
	}
	
	public String getComment()
	{
		return comment;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public void setClubID(int clubID)
	{
		this.clubID = clubID;
	}
	
	public void setClubName(String clubName)
	{
		this.clubName = clubName;
	}
	
	public void setTrackID(int trackID)
	{
		this.trackID = trackID;
	}
	
	public void setStartTime(Date start)
	{
		this.startTime = start;
	}
	
	public void setEndTime(Date end)
	{
		this.endTime = end;
	}
	
	public void setBookedTime(Date booked)
	{
		this.bookedTime = booked;
	}
	
	public void setComment(String comment)
	{
		this.comment = comment;
	}
}
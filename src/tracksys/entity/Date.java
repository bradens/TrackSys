package tracksys.entity;
import java.util.*;
public class Date {
	private int day;
	private int month;
	private int year;
	private int minute;
	private int hour;
	
	public Date(int day, int month, int year, int minute, int hour)
	{
		this.day = day;
		this.month = month;
		this.year = year;
		this.minute = minute;
		this.hour = hour;
	}
	
	public Date(String day, String month, String year, String minute, String hour)
	{
		this.day = Integer.parseInt(day);
		this.month = Integer.parseInt(month);
		this.year = Integer.parseInt(year);
		this.minute = Integer.parseInt(minute);
		this.hour = Integer.parseInt(hour);
	}
	
	public Date(int day, int month, int year)
	{
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public int getDay()
	{
		return day;
	}
	
	public int getMonth()
	{
		return month;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public int getMinute()
	{
		return minute;
	}
	
	public int getHour()
	{
		return hour;
	}
	
	@SuppressWarnings("deprecation")
	public static tracksys.entity.Date convertDate(java.util.Date d)
	{
		return new tracksys.entity.Date(d.getDay(), d.getMonth(), d.getYear(), d.getHours(), d.getMinutes());
	}
	
	public String getDate()
	{
		return Integer.toString(day) + ":" + Integer.toString(month) + 
			":" + Integer.toString(year) + ":" + Integer.toString(minute) +
			":" + Integer.toString(hour);
		
	}
}

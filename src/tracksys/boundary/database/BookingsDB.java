package tracksys.boundary.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tracksys.Resources;
import tracksys.entity.Booking;

public class BookingsDB {
	private Connection conn = null;
	private String table = "tracksys.bookings";
	private static BookingsDB ref;
	private BookingsDB() {
		try
		{
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			conn = DriverManager.getConnection(Resources.database, Resources.username, Resources.password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static BookingsDB getInstance() 
	{
		if (ref == null)
			ref = new BookingsDB();
		return ref;
	}
	
	public void closeConnection()
	{
		try
		{
			conn.close();
		}
		catch (Exception e)
		{
			System.out.println("Could not close bookings connection");
		}
	}
	
	/* The insert functionality */
	private String BuildInsert(Booking booking)
	{
		String query = "INSERT INTO " + table + " (";
		
		query += "clubid, trackid, startTime, endTime, bookedTime, comment) VALUES (";
		
		query += "\'" + booking.getClubID() + "\', ";
		query += "\'" + booking.getTrackID() + "\', ";
		query += "\'" + Resources.DATE_FORMAT.format(booking.getStartTime()) + "\', ";
		query += "\'" + Resources.DATE_FORMAT.format(booking.getEndTime()) + "\', ";
		query += "\'" + Resources.DATE_FORMAT.format(booking.getBookedTime()) + "\', ";
		query += "\'" + booking.getComment() + "\')";
		
		return query;
	}
	
	public void insertBooking(Booking booking)
	{
		String query = BuildInsert(booking);
		
		try
		{
			Statement s = conn.createStatement();
			s.executeUpdate(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the last 50 bookings ordered by date.
	 * regardless of clubid
	 * @return
	 */
	public List<Booking> getRecentBookings()
	{
		List<Booking> bookings = new ArrayList<Booking>();
		String query = "SELECT * FROM tracksys.bookings JOIN tracksys.club on bookings.clubid=club.id ORDER BY bookedTime DESC LIMIT 50";
		try {
			Booking tB;
			Statement s = conn.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			while(rs.next())
			{
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				String bookedTime = rs.getString("bookedTime");
				tB = new Booking(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("clubid")), rs.getString("name"), Integer.parseInt(rs.getString("trackid")),
						Resources.DATE_FORMAT.parse(startTime.substring(0, startTime.length() - 2)), Resources.DATE_FORMAT.parse(endTime.substring(0, endTime.length() - 2)),
						Resources.DATE_FORMAT.parse(bookedTime.substring(0, bookedTime.length() -2)), rs.getString("comment"));
				bookings.add(tB);
			}
			return bookings;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Booking> getDayBookings(Date dateStart, Date dateEnd)
	{
		List<Booking> bookings = new ArrayList<Booking>();
		String query = "SELECT * FROM tracksys.bookings JOIN tracksys.club on bookings.clubid=club.id WHERE startTime >= '" + Resources.DATE_FORMAT.format(dateStart) + "' and startTime < '" + Resources.DATE_FORMAT.format(dateEnd) + "'";
		try {
			Booking tB;
			Statement s = conn.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			while(rs.next())
			{
				
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				String bookedTime = rs.getString("bookedTime");
				tB = new Booking(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("clubid")), rs.getString("name"), Integer.parseInt(rs.getString("trackid")),
						Resources.DATE_FORMAT.parse(startTime.substring(0, startTime.length() - 2)), Resources.DATE_FORMAT.parse(endTime.substring(0, endTime.length() - 2)),
						Resources.DATE_FORMAT.parse(bookedTime.substring(0, bookedTime.length() -2)), rs.getString("comment"));
				bookings.add(tB);
			}
			return bookings;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/* Retrieve a list of bookings from the database by ID */
	public List<Booking> getBookingsByClubID(int ID)
	{
		String query = "SELECT * FROM tracksys.bookings JOIN tracksys.club on bookings.clubid=club.id WHERE clubid=\'" + ID + "\' ORDER BY bookedTime";
		List<Booking> bookings = new ArrayList<Booking>();
		try
		{
			Statement s = conn.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			Booking tB;
			
			while(rs.next())
			{
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				String bookedTime = rs.getString("bookedTime");
				tB = new Booking(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("clubid")), rs.getString("name"), Integer.parseInt(rs.getString("trackid")),
						Resources.DATE_FORMAT.parse(startTime.substring(0, startTime.length() - 2)), Resources.DATE_FORMAT.parse(endTime.substring(0, endTime.length() - 2)),
						Resources.DATE_FORMAT.parse(bookedTime.substring(0, bookedTime.length() -2)), rs.getString("comment"));
				bookings.add(tB);
			}
			
			return bookings;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/* Retrieve a list of future bookings from the database by ID */
	public List<Booking> getFutureBookingsByClubID(int ID)
	{
		String query = "SELECT * FROM tracksys.bookings JOIN tracksys.club on bookings.clubid=club.id WHERE clubid=\'" + ID + "\' AND startTime>=now() ORDER BY bookedTime";
		List<Booking> bookings = new ArrayList<Booking>();
		try
		{
			Statement s = conn.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			Booking tB;
			
			while(rs.next())
			{
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				String bookedTime = rs.getString("bookedTime");
				tB = new Booking(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("clubid")), rs.getString("name"), Integer.parseInt(rs.getString("trackid")),
						Resources.DATE_FORMAT.parse(startTime.substring(0, startTime.length() - 2)), Resources.DATE_FORMAT.parse(endTime.substring(0, endTime.length() - 2)),
						Resources.DATE_FORMAT.parse(bookedTime.substring(0, bookedTime.length() -2)), rs.getString("comment"));
				bookings.add(tB);
			}
			
			return bookings;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/* Retrieve a list of future bookings from the database by ID */
	public List<Booking> getHistoricBookingsByClubID(int ID)
	{
		String query = "SELECT * FROM tracksys.bookings JOIN tracksys.club on bookings.clubid=club.id WHERE clubid=\'" + ID + "\' AND startTime<now() ORDER BY bookedTime";
		List<Booking> bookings = new ArrayList<Booking>();
		try
		{
			Statement s = conn.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			Booking tB;
			
			while(rs.next())
			{
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				String bookedTime = rs.getString("bookedTime");
				tB = new Booking(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("clubid")), rs.getString("name"), Integer.parseInt(rs.getString("trackid")),
						Resources.DATE_FORMAT.parse(startTime.substring(0, startTime.length() - 2)), Resources.DATE_FORMAT.parse(endTime.substring(0, endTime.length() - 2)),
						Resources.DATE_FORMAT.parse(bookedTime.substring(0, bookedTime.length() -2)), rs.getString("comment"));
				bookings.add(tB);
			}
			
			return bookings;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/* Retrieve a list of future bookings that could conflict with the given date and track */
	public List<Booking> getFutureBookingConflicts(Date start, Date end, int track)
	{
		String query = "SELECT * FROM tracksys.bookings JOIN tracksys.club on bookings.clubid=club.id WHERE trackID=\'" + track +
				    "\' AND (startTime>=\'" + Resources.DATE_FORMAT.format(start) +
					"\' AND startTime<\'"   + Resources.DATE_FORMAT.format(end) +
					"\') OR (endTime>\'" + Resources.DATE_FORMAT.format(start) +
					"\' AND endTime<=\'"    + Resources.DATE_FORMAT.format(end) + "\')";
		List<Booking> bookings = new ArrayList<Booking>();
		
		try
		{
			Statement s = conn.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			Booking tB;
			
			while(rs.next())
			{
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				String bookedTime = rs.getString("bookedTime");
				tB = new Booking(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("clubid")), rs.getString("name"), Integer.parseInt(rs.getString("trackid")),
						Resources.DATE_FORMAT.parse(startTime.substring(0, startTime.length() - 2)), Resources.DATE_FORMAT.parse(endTime.substring(0, endTime.length() - 2)),
						Resources.DATE_FORMAT.parse(bookedTime.substring(0, bookedTime.length() -2)), rs.getString("comment"));
				bookings.add(tB);
			}
			
			return bookings;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/* Deletes a booking */
	public void cancelBooking(int ID)
	{
		String query = "DELETE FROM " + table + " WHERE id=\'" + ID + "\'";
		
		try
		{
			Statement s = conn.createStatement();
			s.executeUpdate(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

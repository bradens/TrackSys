package tracksys.boundary.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import tracksys.Resources;
import tracksys.entity.Booking;
import tracksys.entity.Date;
import tracksys.entity.Notification;

public class BookingsDB {
	private Connection conn = null;
	private String table = "tracksys.bookings";
	public BookingsDB() {
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
	
	/* The insert functionality */
	private String BuildInsert(Booking booking)
	{
		String query = "INSERT INTO " + table + " (";
		
		query += "clubid, trackid, startTime, endTime, bookedTime, comment) VALUES (";
		
		query += "\'" + booking.getClubID() + "\', ";
		query += "\'" + booking.getTrackID() + "\', ";
		query += "\'" + booking.getStartTime() + "\', ";
		query += "\'" + booking.getEndTime() + "\', ";
		query += "\'" + booking.getBookedTime() + "\', ";
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
			System.err.println("Error running database query");
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
		String query = "SELECT tracksys.club.name, tracksys.bookings.id, tracksys.bookings.clubid, tracksys.bookings.trackid, " +
				"tracksys.bookings.startTime, tracksys.bookings.endTime, tracksys.bookings.bookTime, tracksys.bookings.comment" +
				" FROM tracksys.bookings FULL JOIN tracksys.club ON tracksys.club.id=tracksys.bookings.clubid ORDER BY bookedTime DESC LIMIT 50";
		DateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			Booking tB;
			Statement s = conn.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			while(rs.next())
			{
				tB = new Booking(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("clubid")), rs.getString("name"), Integer.parseInt(rs.getString("trackid")),
						Date.convertDate(d.parse(rs.getString("startTime"))), Date.convertDate(d.parse(rs.getString("endTime"))), Date.convertDate(d.parse(rs.getString("bookTime"))), rs.getString("comment"));
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
		String query = "SELECT tracksys.club.name, tracksys.bookings.id, tracksys.bookings.clubid, tracksys.bookings.trackid, " +
		"tracksys.bookings.startTime, tracksys.bookings.endTime, tracksys.bookings.bookTime, tracksys.bookings.comment" +
		" FROM tracksys.bookings FULL JOIN tracksys.club ON tracksys.club.id=tracksys.bookings.clubid WHERE clubid=\'" + ID + "\' ORDER BY bookedTime";
		List<Booking> bookings = new ArrayList<Booking>();
		DateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		try
		{
			Statement s = conn.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			Booking tB;
			
			while(rs.next())
			{
				tB = new Booking(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("clubid")), rs.getString("name"), Integer.parseInt(rs.getString("trackid")),
						Date.convertDate(d.parse(rs.getString("startTime"))), Date.convertDate(d.parse(rs.getString("endTime"))), Date.convertDate(d.parse(rs.getString("bookTime"))), rs.getString("comment"));
				bookings.add(tB);
			}
			
			return bookings;
		}
		catch (Exception e)
		{
			System.err.println("Error running database query");
			return null;
		}
	}
}

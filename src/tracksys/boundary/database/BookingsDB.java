package tracksys.boundary.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import tracksys.Resources;
import tracksys.entity.Booking;
import tracksys.entity.Date;

public class BookingsDB {
	private Connection conn = null;
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
	
	/**
	 * Get the last 50 bookings ordered by date.
	 * regardless of clubid
	 * @return
	 */
	public List<Booking> getRecentBookings()
	{
		List<Booking> bookings = new ArrayList<Booking>();
		String query = "SELECT * FROM tracksys.bookings ORDER BY bookedTime DESC LIMIT 50";
		DateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			Booking tB;
			Statement s = conn.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			while(rs.next())
			{
				tB = new Booking(Integer.parseInt(rs.getString("id")), Integer.parseInt(rs.getString("clubid")), Integer.parseInt(rs.getString("trackid")),
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
}

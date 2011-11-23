package tracksys.boundary.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tracksys.Resources;
import tracksys.entity.Track;

public class TracksDB {
	private Connection conn = null;
	public TracksDB() {
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
	
	public void closeConnection()
	{
		try
		{
			conn.close();
		}
		catch (Exception e)
		{
			System.out.println("Could not close tracks connection");
		}
	}
	
	public boolean editTrack(int trackID, boolean isMaintained)
	{
		int i = 1;
		if(isMaintained)
			i = 0;
		String query = "UPDATE tracksys.tracks SET isMaintenance=\'" + i + "\'" + 
			" WHERE trackid=\'" + trackID + "\'";
		try
		{
			Statement s = conn.createStatement();
			s.executeUpdate(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean getTrackMaintenance(int trackID)
	{
		String query = "SELECT * FROM tracksys.tracks WHERE trackid=\'" + trackID + "\'";
		try
		{
			Statement s = conn.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			rs.next();
			int maint = Integer.parseInt(rs.getString("isMaintenance"));
			if(maint > 0)
				return true;
			else
				return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Get all tracks.
	 * @return
	 */
	public List<Track> getTracks()
	{
		List<Track> tracks = new ArrayList<Track>();
		String query = "SELECT * FROM tracksys.tracks";
		
		try
		{
			Statement s = conn.createStatement();
			s.executeQuery(query);
			
			ResultSet rs = s.getResultSet();
			while(rs.next())
			{
				Track tempTrack = new Track(rs.getInt("trackid"), rs.getBoolean("isMaintenance"));
				tracks.add(tempTrack);
			}
			return tracks;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}

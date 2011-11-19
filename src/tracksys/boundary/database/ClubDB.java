package tracksys.boundary.database;
import tracksys.entity.*;
import java.sql.*;

public class ClubDB {
	private String username = "modus";
	private String password = "pwnens";
	private String database = "bradensimpson.com";
	
	private String table = "club";
	
	private Connection conn = null;
	
	public ClubDB()
	{
		try
		{
			conn = DriverManager.getConnection(database, username, password);
		}
		catch (Exception e)
		{
			System.err.println("Error connecting to the datase from ClubDB");
		}
	}
	
	/* The update functionality */
	private String BuildUpdate(Club club)
	{
		String query = "UPDATE " + table + " WHERE id=\'" + club.getID() + "\' SET ";
		
		query += "name=\'" + club.getName() + "\' ";
		query += "passwd=\'" + club.getPassword() + "\' ";
		query += "street=\'" + club.getAddress().getStreet() + "\' ";
		query += "city=\'" + club.getAddress().getCity() + "\' ";
		query += "province=\'" + club.getAddress().getProvince() + "\' ";
		query += "postal=\'" + club.getAddress().getPostal() + "\' ";
		query += "email=\'" + club.getEmail() + "\' ";
		query += "phone=\'" + club.getPhoneNumber() + "\'";
		
		return query;
	}
	public void updateClub(Club club)
	{
		String query = BuildUpdate(club);
		
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
	
	/* The insert functionality */
	private String BuildInsert(Club club)
	{
		String query = "INSERT INTO " + table + " (";
		
		query += "name, street, city, province, postal, email, phone) VALUES (";
		
		query += club.getName() + ", ";
		query += club.getAddress().getStreet() + ", ";
		query += club.getAddress().getCity() + ", ";
		query += club.getAddress().getProvince() + ", ";
		query += club.getAddress().getPostal() + ", ";
		query += club.getEmail() + ", ";
		query += club.getPhoneNumber() + ")";
		
		return query;
	}
	
	public void insertClub(Club club)
	{
		String query = BuildInsert(club);
		
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
	
	/* Retrieve a club from the database by ID */
	public Club getClubFromID(int ID)
	{
		String query = "SELECT * FROM " + table + " WHERE id=\'" + ID + "\'";
		
		try
		{
			Statement s = conn.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			
			Address address = new Address(rs.getString("street"), rs.getString("city"), rs.getString("province"), "", rs.getString("postal"));
			Club club = new Club(rs.getInt("id"), rs.getString("name"), rs.getString("passwd"), address, rs.getString("name"), rs.getString("name"), false, false);
			return club;
			
		}
		catch (Exception e)
		{
			System.err.println("Error running database query");
			return null;
		}
	}
	
	/* Retrieve a club from the database by ID */
	public Club getClubFromName(String ID)
	{
		String query = "SELECT * FROM " + table + " WHERE name=\'" + ID + "\'";
		
		try
		{
			Statement s = conn.createStatement();
			s.executeQuery(query);
			ResultSet rs = s.getResultSet();
			
			Address address = new Address(rs.getString("street"), rs.getString("city"), rs.getString("province"), "", rs.getString("postal"));
			Club club = new Club(rs.getInt("id"), rs.getString("name"), rs.getString("passwd"), address, rs.getString("name"), rs.getString("name"), false, false);
			return club;
			
		}
		catch (Exception e)
		{
			System.err.println("Error running database query");
			return null;
		}
	}
	
	
}
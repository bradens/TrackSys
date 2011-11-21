package tracksys.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tracksys.Resources;
import tracksys.boundary.database.BookingsDB;
import tracksys.boundary.database.ClubDB;
import tracksys.boundary.database.NotificationsDB;
import tracksys.boundary.database.TransactionsDB;
import tracksys.boundary.views.HomeView;
import tracksys.boundary.views.LoginView;
import tracksys.boundary.views.RegistrationView;
import tracksys.entity.Booking;
import tracksys.entity.Club;
import tracksys.entity.Notification;
import tracksys.entity.Transaction;
import tracksys.servletHandler.ServletHandler;

public class ArenaManager {
	public enum Views {
		ROOT, LOGIN, REGISTRATION, HOME
	}
	private static ArenaManager ref;
	
	public LoginView loginView;
	public RegistrationView registrationView;
	public HomeView homeView;
	
	public boolean isAdmin(HttpServletRequest req)
	{	
		Cookie c = ArenaManager.getCookie(Resources.COOKIE_CLUBID, req);
		if (c != null)
		{
			Club reqClub = this.getClubID(Integer.parseInt(c.getValue()));
			if (reqClub != null && reqClub.getAdmin() == 1)
				return true;
		}
		return false;
	}
	public boolean isClub(HttpServletRequest req)
	{
		Cookie c = ArenaManager.getCookie(Resources.COOKIE_CLUBID, req);
		if (c != null)
		{
			Club reqClub = this.getClubID(Integer.parseInt(c.getValue()));
			if (reqClub != null && reqClub.getAdmin() != 1)
				return true;
		}
		return false;
	}

	/**
	 * Retrieve the cookie object with a specified name.
	 * @param cookieName
	 * @param req
	 */
	public static Cookie getCookie(String cookieName, HttpServletRequest req)
	{
		Cookie[] cookies = req.getCookies();
		if (cookies == null)
			return null;
		for (int i = 0; i < cookies.length;i++)
		{
			if (cookies[i].getName().equals(cookieName))
			{
				return cookies[i];
			}
		}
		return null;
	}
	
	/**
	 * Performs a request on the core view, used for redirects mainly.
	 * @param req
	 * @param resp
	 * @param target
	 */
	public void doRoot(HttpServletRequest req, HttpServletResponse resp, String target)
	{
		if (target.endsWith("/isLoggedIn"))
		{
			Cookie usr = getCookie(Resources.COOKIE_USERNAME, req);
			if (usr != null)
				ServletHandler.writeResponse("true", resp);
			else
				ServletHandler.writeResponse("false", resp);
		}
	}
	
	/**
	 * Singleton methods
	 */
	private ArenaManager()
	{
		loginView = new LoginView(this);
		homeView = new HomeView(this);
		registrationView = new RegistrationView(this);
	}
	
	public static ArenaManager getInstance() 
	{
		if (ref == null)
			ref = new ArenaManager();
		return ref;
	}
	
	public int getClubIDFromCookie(HttpServletRequest req)
	{
		Cookie c = ArenaManager.getCookie(Resources.COOKIE_CLUBID, req);
		Club reqClub = this.getClubID(Integer.parseInt(c.getValue()));
		return reqClub.getID();
	}
	
	/**
	 * Club methods.
	 * @param club
	 */
	
	public void AddNewClub(Club club)
	{
		ClubDB db = new ClubDB();
		db.insertClub(club);
	}
	
	public Club getClubID(int ID)
	{
		ClubDB db = new ClubDB();
		return db.getClubFromID(ID);
	}
	
	public Club getClubName(String name)
	{
		ClubDB db = new ClubDB();
		return db.getClubFromName(name);
	}
	
	/**
	 * Notification methods
	 */
	public List<Notification> getNotifications()
	{
		NotificationsDB ndb = new NotificationsDB();
		List<Notification> notifications = ndb.getNotifications();
		return notifications;
	}
	
	public void addNotification(String title, String message)
	{
		NotificationsDB ndb = new NotificationsDB();
		Date date = new Date();
		ndb.addNotification(title, message, Resources.DATE_FORMAT.format(date));
	}
	
	/**
	 * Transaction methods
	 */
	public List<Transaction> getTransactions(int clubID)
	{
		TransactionsDB tdb = new TransactionsDB();
		List<Transaction> transactions = tdb.getTransactions(clubID);
		return transactions;
	}
	/**
	 * Bookings methods
	 */
	public List<Booking> getRecentBookings()
	{
		BookingsDB bdb = new BookingsDB();
		List<Booking> bookings = bdb.getRecentBookings();
		return bookings;
	}
}
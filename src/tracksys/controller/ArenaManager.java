package tracksys.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tracksys.Resources;
import tracksys.boundary.database.BookingsDB;
import tracksys.boundary.database.ClubDB;
import tracksys.boundary.database.NotificationsDB;
import tracksys.boundary.database.TracksDB;
import tracksys.boundary.database.TransactionsDB;
import tracksys.boundary.views.HomeView;
import tracksys.boundary.views.LoginView;
import tracksys.boundary.views.RegistrationView;
import tracksys.entity.Booking;
import tracksys.entity.Club;
import tracksys.entity.Notification;
import tracksys.entity.Track;
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
		return Integer.parseInt(c.getValue());
	}
	
	/**
	 * Club methods.
	 * @param club
	 */
	
	public void AddNewClub(Club club)
	{
		ClubDB db= ClubDB.getInstance();
		db.insertClub(club);
	}
	
	public Club getClubID(int ID)
	{
		ClubDB db = ClubDB.getInstance();
		Club club = db.getClubFromID(ID);
		return club;
	}
	
	public Club getClubName(String name)
	{
		ClubDB db = ClubDB.getInstance();
		Club club = db.getClubFromName(name);
		return club;
	}
	
	public List<Club> getAllClubs()
	{
		ClubDB db = ClubDB.getInstance();
		List<Club> list = db.getAllClubs();
		return list;
	}
	
	public Club getCurrentLoginClub(HttpServletRequest req)
	{
		int clubID = getClubIDFromCookie(req);
		Club currentClub = getClubID(clubID);
		
		return currentClub;
	}

	/**
	 * Notification methods
	 */
	public List<Notification> getNotifications()
	{
		NotificationsDB ndb = NotificationsDB.getInstance();
		List<Notification> notifications = ndb.getNotifications();
		return notifications;
	}
	
	public void addNotification(String title, String message)
	{
		NotificationsDB ndb = NotificationsDB.getInstance();
		Date date = new Date();
		ndb.addNotification(title, message, Resources.DATE_FORMAT.format(date));
	}
	
	public void removeNotification(int notificationID)
	{
		NotificationsDB ndb = NotificationsDB.getInstance();
		ndb.removeNotification(notificationID);
	}
	/**
	 * Transaction methods
	 */
	public List<Transaction> getTransactions(int clubID)
	{
		TransactionsDB tdb = TransactionsDB.getInstance();
		List<Transaction> transactions = tdb.getTransactions(clubID);
		return transactions;
	}
	
	public void addTransactions(Transaction newTrans)
	{
		// Create transaction
		TransactionsDB tdb = TransactionsDB.getInstance();
		tdb.insertTransactions(newTrans);	
		
		// Update club balance
		ClubDB cdb = ClubDB.getInstance();
		Club club = cdb.getClubFromID(newTrans.getClubID());
		club.creditBalanceBy(newTrans.getPaymentFee());
		cdb.updateClub(club);
	}
	/**
	 * Tracks methods
	 */
	public List<Track> getTracks()
	{
		TracksDB trackdb = TracksDB.getInstance();
		List<Track> tracks = trackdb.getTracks();
		return tracks;
	}
	
	/**
	 * Bookings methods
	 */
	public List<Booking> getRecentBookings()
	{
		BookingsDB bdb = BookingsDB.getInstance();
		List<Booking> bookings = bdb.getRecentBookings();
		return bookings;
	}
	
	public List<Booking> getFutureBookings(int ID)
	{
		BookingsDB bdb = BookingsDB.getInstance();
		List<Booking> bookings = bdb.getFutureBookingsByClubID(ID);
		return bookings;
	}
	
	public List<Booking> getHistoricBookings(int ID)
	{
		BookingsDB bdb = BookingsDB.getInstance();
		List<Booking> bookings = bdb.getHistoricBookingsByClubID(ID);
		return bookings;
	}
	
	public List<Booking> getDayBookings(Date dateStart, Date dateEnd)
	{
		BookingsDB bdb = BookingsDB.getInstance();
		List<Booking> bookings = bdb.getDayBookings(dateStart, dateEnd);
		return bookings;
	}
	
	public boolean addBooking(Booking booking)
	{
		
		BookingsDB db = BookingsDB.getInstance();
		try 
		{
			if(bookingIsValid(db, booking))
			{
				db.insertBooking(booking);
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			System.out.println("Error inserting new booking");
			return false;
		}
	}
	
	private boolean bookingIsValid(BookingsDB db, Booking booking)
	{
		List<Booking> conflicts = db.getFutureBookingConflicts(booking.getStartTime(), booking.getEndTime(), booking.getTrackID());
		
		if(conflicts.isEmpty())
			return true;
		else
		{
			for(int i=0; i < 8; i++)
			{
				booking.setTrackID(booking.getTrackID()+1);
				if(booking.getTrackID() > 8)
					booking.setTrackID(1);
				
				conflicts = db.getFutureBookingConflicts(booking.getStartTime(), booking.getEndTime(), booking.getTrackID());
				if(conflicts.isEmpty())
					return true;
			}
			
			return false;
		}
	}
}
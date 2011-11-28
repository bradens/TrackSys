package tracksys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Static Resources class.  Put your global strings and enums here.
 */
public class Resources {
	public static String COOKIE_LOGIN = "loggedIn";
	public static String COOKIE_PASSWORD = "password";
	public static String COOKIE_USERNAME = "username";
	public static String COOKIE_CLUBID = "clubID";
	public static int ADMIN_ID = 0;
	public static int LOGGED_OUT = -1;
	
//	public static String username = "modus";
//	public static String password = "pwnens";
//	public static String database = "jdbc:mysql://bradensimpson.com";
	
	// Comment these out if you want to use online db
	public static String username = "root";
	public static String password = "root";
	public static String database = "jdbc:mysql://localhost";
	
	public static String NOTIFICATION_TITLE_PARAM = "title";
	public static String NOTIFICATION_MESSAGE_PARAM = "message";
	public static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}

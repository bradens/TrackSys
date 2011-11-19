package tracksys.boundary.views;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tracksys.Resources;
import tracksys.controller.ArenaManager; 

public class LoginView {
	public ArenaManager manager;
	
	public LoginView(ArenaManager manager)
	{
		this.manager = manager;
	}
	
	/**
	 * Performs some logic on the request and calls the specific
	 * operation in the login view.
	 * @param req
	 * @param resp
	 * @param target
	 */
	public boolean handle(HttpServletRequest req, HttpServletResponse resp, String target)
	{
		if (target.endsWith("submitlogin"))
		{
			boolean success = this.submitLogin(req, resp);
			return success;
		}
		return false;
	}
	
	public boolean submitLogin(HttpServletRequest req, HttpServletResponse resp)
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (username == null || password == null)
		{
			ArenaManager.writeResponse("false", resp);
			return false;
		}
		// Need to do an actual lookup here......this is temp
		if (username.equals("admin") && password.equals("tracksys"))
		{
			Cookie c = new Cookie(Resources.COOKIE_USERNAME, username);
			c.setMaxAge(60*3600*24);
			//c.setDomain("t.s.local");
			c.setPath("/");
			resp.addCookie(c);
			ArenaManager.writeResponse("true", resp);
			return true;
		}
		else if (username.equals("babyseals") && password.equals("clubbin"))
		{
			Cookie c = new Cookie(Resources.COOKIE_USERNAME, username);
			c.setMaxAge(60*3600*24);
			//c.setDomain("");
			c.setPath("/");
			resp.addCookie(c);
			ArenaManager.writeResponse("true", resp);
			return true;
		}
		else
			ArenaManager.writeResponse("false", resp);
		return false;
	}
}
package tracksys.boundary.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tracksys.controller.ArenaManager; 
import tracksys.entity.Club;

public class LoginView {
	public int activeClub; 
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
			return false;
		if (username.equals("admin") && password.equals("tracksys"))
		{
			ArenaManager.writeResponse("true", resp);
			manager.activeClub = -1; // temporary manager id.
			return true;
		}
		return false;
	}
}
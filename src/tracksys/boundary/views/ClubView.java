package tracksys.boundary.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tracksys.controller.ArenaManager;

public class ClubView {
	public ArenaManager manager;
	public ClubView() { }
	public ClubView(ArenaManager manager)
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
		if (target.endsWith("homeLocation"))
		{
			this.directToHome(req, resp);
			return true;
		}
		return false;
	}
	
	public void directToHome(HttpServletRequest req, HttpServletResponse resp)
	{	
		if (manager.isAdmin(req))
		{
			ArenaManager.writeResponse("/home/admin.html", resp);
		}
		else if (manager.isClub(req))
			ArenaManager.writeResponse("/home/club.html", resp);
		else
			ArenaManager.writeResponse("/login/", resp);
	}
}

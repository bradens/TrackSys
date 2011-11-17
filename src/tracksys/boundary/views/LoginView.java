package tracksys.boundary.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tracksys.controller.ArenaManager; 

public class LoginView {
	public static String username;
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
	public void handle(HttpServletRequest req, HttpServletResponse resp, String target)
	{
		if (null == null)
		{
			
		}
	}
}
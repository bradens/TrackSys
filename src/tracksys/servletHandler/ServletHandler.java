package tracksys.servletHandler;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
 
import java.io.IOException;

import tracksys.controller.ArenaManager;
import tracksys.controller.ArenaManager.Views;

public class ServletHandler extends HttpServlet
{
	private static ArenaManager manager;

	public ServletHandler () {
		manager = ArenaManager.getInstance();
	}
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws 
		ServletException, IOException
		{
			String target = req.getServletPath();
			Views baseView = getBaseView(target);
			switch (baseView)
			{
				case ROOT:
					manager.doRoot(req, resp, target);
					break;
				case LOGIN:
					manager.loginView.handle(req, resp, target);
					break;
				case HOME:
					manager.homeView.handle(req, resp, target);
					break;
			}
			resp.setContentType("text/html");
			resp.addHeader("Access-Control-Allow-Origin", "http://localhost");	// This is just used to debug, it allows ajax calls from localhost -> localhost
			resp.addHeader("Access-Control-Allow-Credentials", "true");	// This is just used to debug, it allows ajax calls from localhost -> localhost
		}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws 
		ServletException, IOException
	{
		this.doGet(req, resp);
	}
	/**
	 * Gets the base view from the request 
	 * ex. a request to url:8080/login/ will return Views.LoginView
	 * @param target
	 * @return
	 */
	public static Views getBaseView(String target)
	{
		if (target == "/")
			return ArenaManager.Views.ROOT;
		else {
			String viewStr = target.split("/")[1];
			if (viewStr.equals("login"))
				return ArenaManager.Views.LOGIN;
			else if (viewStr.equals("home"))
				return ArenaManager.Views.HOME;
			else
				return ArenaManager.Views.ROOT;
		}
	}
}
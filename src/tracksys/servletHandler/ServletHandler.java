package tracksys.servletHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
 
import java.io.IOException;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import tracksys.controller.ArenaManager;
import tracksys.controller.ArenaManager.Views;

public class ServletHandler extends AbstractHandler {
	public void handle(String target,
					   Request baseReq,
					   HttpServletRequest request,
					   HttpServletResponse response)
	throws IOException, ServletException
	{
		Views baseView = getBaseView(target);
		switch (baseView)
		{
			case Root:
				ArenaManager.doRoot(request, response, target);
				break;
			case LoginView:
				ArenaManager.doLogin(request, response, target);
				break;
		}
		response.setContentType("text/html;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");	// This is just used to debug, it allows ajax calls from localhost -> localhost
		response.setStatus(HttpServletResponse.SC_OK);
        baseReq.setHandled(true);
        response.getWriter().println("<h1>This is a text response</h1>");	
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
			return ArenaManager.Views.Root;
		else {
			String viewStr = target.split("/")[1];
			if (viewStr.equals("login"))
				return ArenaManager.Views.LoginView;
			else
				return ArenaManager.Views.Root;
		}
	}
}
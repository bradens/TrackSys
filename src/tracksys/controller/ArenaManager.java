package tracksys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
 
import java.io.IOException;
 
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class ArenaManager extends AbstractHandler {
	public void handle(String target,
					   Request baseReq,
					   HttpServletRequest request,
					   HttpServletResponse response)
	throws IOException, ServletException
	{
		response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseReq.setHandled(true);
        response.getWriter().println("<h1>This is a plain html response.</h1>");	
	}
}

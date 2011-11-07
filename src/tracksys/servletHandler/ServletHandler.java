package tracksys.servletHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
 
import java.io.IOException;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class ServletHandler extends AbstractHandler {
	public void handle(String target,
					   Request baseReq,
					   HttpServletRequest request,
					   HttpServletResponse response)
	throws IOException, ServletException
	{
		response.setContentType("text/html;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");	// This is just used to debug, it allows ajax calls from localhost -> localhost
        response.setStatus(HttpServletResponse.SC_OK);
        baseReq.setHandled(true);
        response.getWriter().println("<h1>This is a text response</h1>");	
	}
}
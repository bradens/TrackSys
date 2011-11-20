package tracksys;


import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.ServletHttpContext;


public class TrackSysApplication {
	public static void main(String[] args) throws Exception
	{
		Server server = new Server();
        server.addListener(":1234");        
		ServletHttpContext context = (ServletHttpContext) server.getContext("/");
		context.addServlet("/", "tracksys.servletHandler.ServletHandler");
        server.start();
	}
}

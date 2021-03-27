package pv260.solid.srp.original;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import pv260.solid.srp.original.servlets.ConfirmationServlet;
import pv260.solid.srp.original.servlets.OrderServlet;

public class Main {

    public static void main(String[] args) throws Exception {
        //normally the servlet would be deployed to a running container
        //here we manage the whole lifecycle to make running the example as simple as possible
        Server server = new Server(8080);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);

        handler.addServletWithMapping(OrderServlet.class,
                "/");
        handler.addServletWithMapping(ConfirmationServlet.class,
                "/confirm");

        server.start();
        server.join();
    }
}

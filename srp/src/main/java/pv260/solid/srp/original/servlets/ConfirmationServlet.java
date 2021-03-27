package pv260.solid.srp.original.servlets;

import pv260.solid.srp.original.pages.ConfirmationPageConstructor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println(ConfirmationPageConstructor.constructConfirmationPage(request.getQueryString()));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }


}

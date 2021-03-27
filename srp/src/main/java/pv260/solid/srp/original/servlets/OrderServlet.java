package pv260.solid.srp.original.servlets;

import pv260.solid.srp.original.Product;
import pv260.solid.srp.original.pages.OrderPageConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.String.format;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.compile;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderServlet extends HttpServlet {

    private static final Pattern ID_COST_REGEX = compile("user=([\\w]+)&cost=([\\w]+)");

    private long orderIdGen = 0;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
            IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println(OrderPageConstructor.constructOrderPage(Product.obtainProducts()));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException,
            IOException {
        String content;
        try (InputStream in = request.getInputStream(); Scanner scan = new Scanner(in).useDelimiter("\\A")) {
            content = scan.next();
        }
        response.setContentType("text/html");
        try {
            Matcher idCostMatcher = ID_COST_REGEX.matcher(content);
            idCostMatcher.find();

            long orderId = this.orderIdGen++;
            long userId = Long.valueOf(idCostMatcher.group(1));
            long orderPrice = Long.valueOf(idCostMatcher.group(2));
            System.out.println(format("Saving to DB: user %s, order %s, cost %s",
                    userId,
                    orderId,
                    orderPrice));

            response.sendRedirect(format("%s/confirm?order=%s&cost=%s",
                    //this would not be hardcoded but rather come from context
                    //because of how we deployed the servlet, context is null
                    "http://localhost:8080",
                    orderId,
                    orderPrice));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}

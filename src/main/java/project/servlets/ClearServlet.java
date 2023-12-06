package project.servlets;

import jakarta.persistence.Enumerated;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
@WebServlet("/clearTable")
public class ClearServlet extends HttpServlet {
    private ServletContext context;
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        context = session.getServletContext();
        context.log("Clearing");
        Enumeration<String> table_row = session.getAttributeNames();
        while (table_row.hasMoreElements()){
            session.removeAttribute(table_row.nextElement());
        }
        resp.setContentType("text/html");
        resp.getWriter().println("Done");
        resp.setStatus(HttpServletResponse.SC_OK);
        context.log("Success");
    }
}

package project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "controllerServlet", value = "/check")
public class ControllerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Map<String, String[]> x1 = request.getParameterMap();
//        String x2 = (String) request.getAttribute("x");
//        response.getWriter().println(x2);
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String r = request.getParameter("r");
//        String x_1 = request
        if(x == null || y == null || r == null){
//            response.getWriter().println("w");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else{
//            response.getWriter().println("r");
            request.getRequestDispatcher("/areacheck").forward(request, response);
        }
    }
}

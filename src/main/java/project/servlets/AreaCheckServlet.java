package project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name="areaChecker", value ="/areacheck")
public class AreaCheckServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String r = request.getParameter("r");

        boolean x_val = isValidX(x);
        boolean y_val = isValidY(y);
        boolean r_val = isValidR(r);

        if (x_val && y_val && r_val){
            print_final_page(x, y, r, response, request);
        }else{
            List<String> errors = new ArrayList<>();
            if(!x_val){
                String error_x = "x isn't valid";
                errors.add(error_x);
            }
            if(!y_val){
                String error_y = "y isn't valid";
                errors.add(error_y);
            }
            if(!r_val){
                String error_r = "r isn't valid";
                errors.add(error_r);
            }
            print_not_valid_page(errors, response);
        }
    }
    private boolean isValidX(String x){
        return true;
    }
    private boolean isValidY(String y){
        return true;
    }
    private boolean isValidR(String r){
        return true;
    }
    private void addToSession(HttpServletRequest request, String x, String y, String r){
        int id = 0;
        HttpSession session = request.getSession();
        Object Id = session.getAttribute("id");
        if (Id == null){
            session.setAttribute("id", id);
        }else{
            id = (int)Id + 1;
            session.setAttribute("id", id);
        }
        JSONObject table_row = new JSONObject();
        table_row.put("x", x);
        table_row.put("y", y);
        table_row.put("r", r);
        table_row.put("result", "попал");
        table_row.put("date", "04.12.2023");
        table_row.put("exe-time", "23sec");
//        String data_json = "{" + "\"x\": " + x + "\n" +
//                "\"y\": " + y + "\n" +
//                "\"r\": " + r + "}";

        session.setAttribute("table_row" + id, table_row);
    }
    private void print_final_page(String x, String y, String r, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        addToSession(request, x, y, r);
        request.getRequestDispatcher("./Result.jsp").forward(request, response);
    }
    private void print_not_valid_page(List<String> errors, HttpServletResponse response) throws IOException{
        response.getWriter().println("We have any problems");
        for(String error: errors){
            response.getWriter().println(error);
        }
    }
    private int getNewIdForSessionDate(HttpSession session){
        int id = 0;
        Enumeration<String> names = session.getAttributeNames();
        while(names.hasMoreElements()){
            id++;
        }
       return id;
    }
}

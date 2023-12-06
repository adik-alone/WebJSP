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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name="areaChecker", value ="/areacheck")
public class AreaCheckServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String x_str = request.getParameter("x");
        String y_str = request.getParameter("y");
        String r_str = request.getParameter("r");

        long start_time = System.nanoTime();

        double x = Double.parseDouble(x_str);
        double y = Double.parseDouble(y_str);
        double r = Double.parseDouble(r_str);
        boolean result = false;
//        String result_str;

        if(x > 0 || y > 0){
            if(y <= r / 2){
                if(x <= r){
                    result = true;
                }
            }
        }
        if(x < 0 || y > 0){
            if(x*x + y*y <= r*r){
                result = true;
            }
        }
        if(x < 0 || y < 0) {
            result = false;
        }
        if(x > 0 || y < 0){
            if (x - r <= y){
                result = true;
            }
        }
        String result_str = parseResultToStr(result);
        request.setAttribute("result", result_str);
        long end_time = System.nanoTime();
        long execution_time = (end_time - start_time)/1000;
        String exec_time = String.valueOf(execution_time) + " mc";
        addToSession(request, x_str, y_str, r_str, result_str, exec_time);
        print_final_page(request, response);

    }
    private void addToSession(HttpServletRequest request, String x, String y, String r, String result, String exec_time){
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
        table_row.put("result", result);
        table_row.put("date", String.valueOf(new org.joda.time.DateTime()));
        table_row.put("exe-time", exec_time);
//        String data_json = "{" + "\"x\": " + x + "\n" +
//                "\"y\": " + y + "\n" +
//                "\"r\": " + r + "}";

        session.setAttribute("table_row" + id, table_row);
    }
    private void print_final_page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("./Result.jsp").forward(request, response);
    }
    private void print_not_valid_page(List<String> errors, HttpServletResponse response) throws IOException{
        response.getWriter().println("We have any problems");
        for(String error: errors){
            response.getWriter().println(error);
        }
    }
    private String parseResultToStr(boolean result){
        if(result){
            return "Попал";
        }else{
            return "Промах";
        }
    }
}

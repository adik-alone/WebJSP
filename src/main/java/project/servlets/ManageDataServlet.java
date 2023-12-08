package project.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet("/dataManage")
public class ManageDataServlet extends HttpServlet {
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        HttpSession session = req.getSession();
        context = session.getServletContext();
        context.log("it give start points");
        JSONObject json_resp = new JSONObject();


        List<String> names_list = new ArrayList<>();
        Enumeration<String> names = session.getAttributeNames();
        while(names.hasMoreElements()){
            names_list.add(names.nextElement());
        }
        Pattern pattern = Pattern.compile("^table_row");
        names_list.sort(String::compareTo);
        List<String> points2 = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < names_list.size(); i++){
                try {
                    JSONObject row = (JSONObject) session.getAttribute(names_list.get(i));
                    Point point = new Point();
                    point.setX((String) row.get("x"));
                    point.setY((String) row.get("y"));
//                    json_resp.put("x", row.get("x"));
//                    json_resp.put("y", row.get("y"));
                    points2.add(point.toString());
                }catch (Exception e){
                    context.log(String.valueOf(e));
                }
        }
        json_resp.put("points", points2);
        resp.getWriter().println(json_resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}

class Point{
    String x;
    String y;

    @Override
    public String toString() {
        return x + " " + y;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
}

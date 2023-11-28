package project.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

public class AreaCheckServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String r = request.getParameter("r");

        boolean x_val = isValidX(x);
        boolean y_val = isValidY(y);
        boolean r_val = isValidR(r);

        if (x_val && y_val && r_val){
            print_final_page(x, y, r);
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
            print_not_valid_page(errors);
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
    private void print_final_page(String x, String y, String r){

    }
    private void print_not_valid_page(List<String> errors){

    }
}

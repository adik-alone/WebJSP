package project.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(value = "/check")
public class MyFilter extends HttpFilter {
    private ServletContext context;
    @Override
    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String r = request.getParameter("r");
        this.context.log(x + " " + y + " " + r);
        List<String> errors = new ArrayList<>();
        if (notString(x)){
            if(!validX(x)){
                String error = "Wrong X";
                errors.add(error);
            }
        }else{
            String error = "X should be not string";
            errors.add(error);
        }
        if (notString(y)){
            if(!validY(y)){
                String error = "Wrong Y";
                errors.add(error);
            }
        }else{
            String error = "Y should be not string";
            errors.add(error);
        }
        if (notString(r)){
            if(!validR(r)){
                String error = "Wrong R";
                errors.add(error);
            }
        }else{
            String error = "R should be not string";
            errors.add(error);
        }
        if(!errors.isEmpty()){
            this.context.log("Find Error, Print...");
            HttpServletResponse response = (HttpServletResponse) res;
            response.setContentType("text/html");
            for(int i = 0; i < errors.size(); i++){
                context.log(errors.get(i));
                response.getWriter().println(errors.get(i));
            }
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }else{
            chain.doFilter(req, res);
        }
    }
    private boolean validX(String x){
        double x_num = Double.parseDouble(x);
        if (x_num <= -5 || x_num >= 3){
            return false;
        }else{
            return true;
        }
    }
    private boolean validY(String y){
        double y_num = Double.parseDouble(y);
        if (y_num < -3 || y_num > 5){
            return false;
        }else{
            return true;
        }
    }
    private boolean validR(String r){
        double r_num = Double.parseDouble(r);
        if(r_num <= 0 || r_num > 3){
            return false;
        }else{
            return true;
        }
    }
    private boolean notString(String s){
        boolean isNumber = Pattern.matches("-?[1-90]+[.]?[1-90]*", s);
        context.log(String.valueOf(isNumber));
        return isNumber;
    }
}

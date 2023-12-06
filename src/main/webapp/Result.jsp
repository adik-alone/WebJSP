<%@ page import="java.io.IOException" %>
<%@ page import="java.util.*" %>
<%@ page import="org.json.simple.JSONObject" %><%--
  Created by IntelliJ IDEA.
  User: vstsa
  Date: 03.12.2023
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result of shot</title>
</head>
<body>
    <%!
        public String getParam(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            String x = request.getParameter("x");
            String y = request.getParameter("y");
            String r = request.getParameter("r");
            String result = (String) request.getAttribute("result");
            String result_str = "Координаты: x = " + x + ", y = " + y + ", r = " + r + " Результат: " + result;
            return result_str;
        }
    %>
    <p>
    <%=getParam(request, response)%>
    </p>
    <button onclick="ComeHome();">Вернутся</button>
    <%!
       HttpSession session = null;
    %>
    <%
        List<String> names_list = new ArrayList<>();
        session = request.getSession();
        Enumeration<String> names = session.getAttributeNames();
        while(names.hasMoreElements()){
            names_list.add(names.nextElement());
        }

        names_list.sort(String::compareTo);


//        List<String> names = (ArrayList<String>)session.getAttributeNames();
    %>
    <table>
        <thead>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Result</th>
                <th>Date</th>
                <th>Execution time</th>
            </tr>
        </thead>
        <tbody>
        <%
            for(int i = 1; i < names_list.size(); i++){
               JSONObject row = (JSONObject) session.getAttribute(names_list.get(i));
               String x = (String) row.get("x");
               String y = (String) row.get("y");
               String r = (String) row.get("r");
               String result = (String) row.get("result");
               String date = (String) row.get("date");
               String exeTime = (String) row.get("exe-time");
                %>
        <tr>
            <td><%= x %></td>
            <td><%= y %></td>
            <td><%= r %></td>
            <td><%= result %></td>
            <td><%= date %></td>
            <td><%= exeTime %></td>
        </tr>
<%--        <p><%=session.getAttribute(names_list.get(i))%></p>--%>
        <%
            }
        %>
        </tbody>
    </table>


<%--    <p><%= session.getAttribute("table_row")%></p>--%>
<script>
    function ComeHome(){
        location.assign("./index.jsp");
    }
</script>
</body>
</html>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<%--    <link rel="stylesheet" href="main.css">--%>
</head>
<body>
<div id="header">Цалов Василий Сергеевич Группа: P3207 Вариант: 171125</div>
<div id="image">
    <canvas id="MyCanvas" width="599" height="400" style="border: 3px black solid"></canvas>
</div>
<div id="main">
    <!--        <h1>Попади в синюю область!</h1>-->
    <form method="POST" action="" id="form" onclick="" onsubmit="CheckForm(); return false;">
        <div>
            <label for="x">Choose X</label>
            <input type="text" name="x" placeholder="(-5..3)" id="x" onchange="CheckX(this)">
        </div>

        <div>
            <label for="y">Choose Y</label>
            <select id="y" name="y" onchange="CheckY(this)">
                <option value="-3" selected>-3</option>
                <option value="-2">-2</option>
                <option value="-1">-1</option>
                <option value="0">0</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
            </select>
        </div>
        <div>
            <label for="r-1">Choose R</label>
            <button value="1" id="r-1" type="button" name="r" class="r-button" onclick="DrawArea(this);">r = 1</button>
            <button value="1.5" id="r-1.5" name="r" type="button" class="r-button" onclick="DrawArea(this)">r = 1.5</button>
            <button value="2" id="r-2"name="r" type="button" class="r-button" onclick="DrawArea(this)">r = 2</button>
            <button value="2.5" id="r-2.5" name="r" type="button" class="r-button" onclick="DrawArea(this)">r = 2.5</button>
            <button value="3" id="r-3" name="r" type="button" class="r-button" onclick="DrawArea(this)">r = 3</button>
        </div>

        <div hidden id="error" style="color: #ff0000"></div>
        <p><input id="button" class="ui-button" type="submit" onclick=""></p>
    </form>
    <form onsubmit="clearTable(); return false;">
        <button id="button-clear" class="ui-button">Очистить таблицу</button>
    </form>
</div>
<div id="table-div">
    <table class="result-table" id="data-table">
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
                <td><%= result%></td>
                <td><%= date %></td>
                <td><%= exeTime %></td>
            </tr>
            <%--        <p><%=session.getAttribute(names_list.get(i))%></p>--%>
            <%
                }
            %>
            </tbody>
        </table>
</div>


<%--<script src="https://polyfill.io/v3/polyfill.min.js?features=WeakRef,BigInt"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/superagent"></script>--%>
<script src="main.js"></script>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<%--    <link rel="stylesheet" href="main.css">--%>
</head>
<body>
<div id="header">Цалов Василий Сергеевич Группа: P3207 Вариант: 1719</div>
<div id="image">
    <canvas id="MyCanvas" width="599" height="400" style="border: 3px black solid"></canvas>
</div>
<div id="table-div">
    <table class="result-table" id="data-table">
        <thead>
        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Результат</th>
            <th>Время исполнения</th>
            <th>Дата</th>
        </tr>
        </thead>

        <tbody id="Data">
        <!--            <tr>-->
        <!--                <td>1</td>-->
        <!--                <td>1</td>-->
        <!--                <td>1</td>-->
        <!--                <td>Попал</td>-->
        <!--                <td>0.2</td>-->
        <!--                <td>2.09.2023</td>-->
        <!--            </tr>-->
        </tbody>

    </table>
</div>
<div id="main">
    <!--        <h1>Попади в синюю область!</h1>-->
    <form method="POST" action = "" id="form" onclick="" onsubmit="checkForm(); return false;">

        <!--            <p class="Choosing">-->

        <div class="Checkbox">
            <div class="text">
                <p class="Choosing"><b>Выберите X</b></p>
            </div>
            <div class = checkbox_div>
                <label for="x-2">
                    <input type="checkbox" class="checkbox_input" name="X" value="-2" id="x-2">
                    <span>-2</span>
                </label><br>

                <label for="x-1.5">
                    <input type="checkbox" name="X" class="checkbox_input" value="-1.5" id="x-1.5">
                    <span>-1.5</span>
                </label><br>

                <label for="x-1">
                    <input type="checkbox" name="X" class="checkbox_input" value="-1" id="x-1">
                    <span>-1</span>
                </label>
            </div>
            <div class = checkbox_div>
                <label for="x-0.5">
                    <input type="checkbox" name="X" class="checkbox_input" value="-0.5" id="x-0.5">
                    <span>-0.5</span>
                </label><br>

                <label for="x0">
                    <input type="checkbox" name="X" class="checkbox_input" value="0" id="x0">
                    <span>0</span>
                </label><br>

                <label for="x+0.5">
                    <input type="checkbox" name="X" class="checkbox_input" value="0.5" id="x+0.5">
                    <span>0.5</span>
                </label>
            </div>
            <div class = checkbox_div>
                <label for="x+1">
                    <input type="checkbox" name="X" class="checkbox_input" value="1" id="x+1">
                    <span>1</span>
                </label><br>

                <label for="x+1.5">
                    <input type="checkbox" name="X" class="checkbox_input" value="1.5" id="x+1.5">
                    <span>1.5</span>
                </label><br>

                <label for="x+2">
                    <input type="checkbox" name="X" class="checkbox_input" value="2" id="x+2">
                    <span>2</span>
                </label>
            </div>
        </div>

        <div id="div-y">
            <div class="text">
                <p class="Choosing"><b>Введите Y</b></p>
            </div>
            <lable for="choose-y"></lable><br>
            <input type="text" name="Y" placeholder="-5..5" id="choose-y">
        </div>
        <div id="div-r">
            <div class="text">
                <p class="Choosing"><b>Выберете R</b></p>
            </div>
            <p><label for="select-r"></label><br>
                <select id="select-r" name = "R" onclick="DrawArea()">
                    <!--                        <option disabled selected>-&#45;&#45;Number-&#45;&#45;</option>-->
                    <option value="1" selected>1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select></p>
        </div>

        <div hidden id="error" style="color: #ff0000"></div>
        <p><input id="button" class="ui-button" type="submit" onclick=""></p>
    </form>
    <form onsubmit="clearTable()">
        <button id="button-clear" class="ui-button">Очистить таблицу</button>
    </form>
</div>


<%--<script src="https://polyfill.io/v3/polyfill.min.js?features=WeakRef,BigInt"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/superagent"></script>--%>
<script src="main.js"></script>
</body>
</html>
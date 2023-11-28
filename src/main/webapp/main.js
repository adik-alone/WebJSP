function checkForm(qualifiedName, value){

    var fail = "";
    var x_arr = document.getElementsByName("X");
    var y = document.getElementById("choose-y").value;
    var r = document.getElementById("select-r").value;
    var x = null;
    var count = 0;

    for(var i = 0; i < x_arr.length; i++ ){
        if(x_arr.item(i).checked){
            count++;
            x = x_arr.item(i).value;
        }
    }

    console.log("Значение Х: " + x);
    console.log("Значение y: " + y);
    console.log("Значение радиуса R: " + r);

    if(count > 1){
        fail = "Слишком много значений X, выберете что-то одно";
    }else if(count == 0){
        fail = "Вы не выбрали значение X";
    }else if(y == ""){
        fail = "Вы не выбрали Y";
    }else if(!/^[0-9+-.]+$/.test(y)){
        fail = "Введены некорректные символы";
    }else if(y >= 5 || y <= -5){
        fail = "Y не попадает в ограничение";
    }

    if(fail !== ""){
        console.log(fail);
        document.getElementById("error").innerHTML = fail;
        error.removeAttribute("hidden");
        // return false;
    }else{
        drawPoint(x, y);
        PostToServer(x, y, r);
        error.setAttribute("hidden", value);
    }
}


function PostToServer(x, y, r) {

    // var y = document.getElementById("choose-y").value;
    // var r = document.getElementById("select-r").value;
    // var x = null;
    // var x_arr = document.getElementsByName("X");
    // for(var i = 0; i < x_arr.length; i++ ){
    //     if(x_arr.item(i).checked){
    //         x = x_arr.item(i).value;
    //     }
    // }

    // var data = new FormData();
    // data.append("X", x);
    // data.append("Y", y);
    // data.append("R", r);

    const data = {
        "X": x,
        "Y": y,
        "R": r
    }


    // for (let [name, value] of data) {
    //     console.log(`${name} = ${value}`); // key1=value1, потом key2=value2
    // }
    console.log("Выполнили это");

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE && xhr.status === 200) {
            newData(xhr.responseText);

        }
    };
    xhr.open('POST', 'handler.php', true);
    // xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    console.log(JSON.stringify(data));
    xhr.send(JSON.stringify(data));


    // for (var key of data.keys()) {
    //     console.log(key, data.get(key));
    // }
    // xhr.send(data);
    // alert("there");

}



function newData(text){
    var i = localStorage.length;
    try{

        txt = parseJSON(text, i);
    }catch (err){
        console.log(err);
    }
    localStorage.setItem('table_row' + i, text);
    // console.log(txt);
    addToTable(txt);
}

function addToTable(txt){
    const table = document.getElementById("Data");
    table.innerHTML += txt;
}

function clearTable(){
    localStorage.clear();
}

function fillThetabel(){
    var lenth = localStorage.length;
    for (var i = 0; i < lenth; i++){
        var keey = localStorage.key(i);
        console.log(keey);
        var row = localStorage.getItem(keey);
        JSON.stringify(row);
        var txt = parseJSON(row);
        // console.log(txt);
        addToTable(txt);
    }
}


function parseJSON(text, id = -1) {
    try {
        console.log(text);
        var row = JSON.parse(text);
        console.log(row);
        console.log(row.X);
        txt = "<tr>\n" +
            "                <td>" + row.X + "</td>\n" +
            "                <td>" + row.Y + "</td>\n" +
            "                <td>" + row.R + "</td>\n" +
            "                <td>" + row.Result + "</td>\n" +
            "                <td>" + row.Execute_time + "</td>\n" +
            "                <td>" + row.Date_now + "</td>\n" +
            "            </tr>";
        console.log(txt);
        return txt;
    }catch (err){
        console.log(err);
        localStorage.removeItem("table_row" + id);
    }
}



function drawPoint(x, y){
    ctx.fillcolor = "rgb(206, 0, 21)";
    ctx.fillStyle = "rgb(206, 0, 21)";

    ctx.beginPath();
    ctx.arc(x * size, y * size, 2, 0, 2 * Math.PI);
    ctx.fill();

    ctx.fillcolor = "#000";
    ctx.fillStyle = "#000";
}










function DrawArea(){
    BackGroundColor("#fff");
    DrawXandY();
    drawFigures();
}

function Changecenter(){
    ctx.translate(w/2, h/2);
    ctx.scale(1, -1);
}

function BackGroundColor(color){
    ctx.fillcolor = color;
    ctx.fillStyle = color;
    ctx.fillRect(-w/2, -h/2, w, h);

    ctx.fillcolor = "#000";
    ctx.fillStyle = "#000";
}

function DrawXandY(){

    ctx.fillRect(-1, h/2 , 1, -h);

    ctx.fillRect(-w/2, 1, w, -1);

    //стерлочки

    ctx.beginPath();
    ctx.moveTo(w/2 - 15, 5);
    ctx.lineTo(w/2, 1);
    ctx.lineTo(w/2, -1);
    ctx.lineTo(w/2 - 15, -5);
    ctx.closePath();
    ctx.fill();


    ctx.font = "20px serif"
    ctx.fillText("X", w/2 - 15, -10);
    ctx.scale(1, -1);
    ctx.fillText("Y", -35, -h/2 + 18);
    ctx.scale(1, -1);

    ctx.beginPath();
    ctx.moveTo(5, h/2 - 15);
    ctx.lineTo(1, h/2);
    ctx.lineTo(-1, h/2);
    ctx.lineTo(-5, h/2 - 15);
    ctx.closePath();
    ctx.fill();
    drawLines();
}
function drawFigures(){
    var R = document.getElementById("select-r").value;

    // ctx.fillcolor = "#43aeef";
    // ctx.fillStyle = "#43aeef";
    ctx.fillcolor = "rgba(67, 174, 239, 0.7)";
    ctx.fillStyle = "rgba(67, 174, 239, 0.7)";

    ctx.fillRect(0, R * size/2, R * size, -R * size/2);

    ctx.beginPath();
    ctx.moveTo(0, R * size/2);
    ctx.lineTo(-R * size, 0);
    ctx.lineTo(0, 0);
    ctx.closePath();
    ctx.fill();
    ctx.beginPath();
    ctx.arc(0, 0, R * size/2, Math.PI, 3 * Math.PI / 2);
    ctx.lineTo(0,0);
    ctx.closePath();
    ctx.fill();
}
function drawLines(){
    ctx.font = "12px serif";
    for (var i = size - 1; i < w/2 - 20; i += size){
        ctx.fillRect(i, size/4, 1, -1 * size/2);
        ctx.scale(1, -1);
        ctx.fillText(Math.floor(i/size + 1), i, size/4 + 15);
        ctx.scale(1, -1);
    }
    for (var i = -size - 1; i > -w/2 - 20; i -= size){
        ctx.fillRect(i, size/4, 1, -1 * size/2);
    }

    for (var j = size -1; j < h/2 - 20; j += size){
        ctx.fillRect(-size/4, j, size/2, 1);
        ctx.scale(1, -1);
        ctx.fillText(Math.floor(j/size + 1), -size/4 - 10, -j);
        ctx.scale(1, -1);
    }
    for (var j = -size - 1; j > -h/2 - 20; j -= size){
        ctx.fillRect(-size/4, j, size/2, 1);
    }

}

fillThetabel();

var error = document.getElementById("error");
// error.setAttribute("hidden");


var example = document.getElementById("MyCanvas"),
    ctx = example.getContext('2d');
example.width = 300;
example.hieght = 400;


// ctx.beginPath();
// ctx.moveTo(150, 0);
// ctx.lineTo(150, 405);
// ctx.moveTo(0, 202.5);
// ctx.lineTo(300, 202.5);

ctx.fillStyle = "#ffffff";
ctx.fillcolor = "#ffffff";
ctx.fillRect(0, 0, 300, 400);

ctx.fillStyle = "black";
ctx.fillcolor = "black";

const w = 300;
const h = 400;
const size = 25;

Changecenter();
BackGroundColor("#fff");
DrawXandY();
drawFigures();
// ctx.fillRect(0, 199, 300, 1);
// ctx.fillRect(149, 0, 1, 400);
// ctx.beginPath();
// ctx.moveTo(285, 194.5);
// ctx.lineTo(300, 199.5);
// ctx.lineTo(285, 205);
// ctx.closePath();
// ctx.fill();
//
// ctx.beginPath();
// ctx.moveTo(145, 15);
// ctx.lineTo(150, 0);
// ctx.lineTo(155, 15);
// ctx.closePath();
// ctx.fill();
// ctx.translate(150, 200);
// ctx.scale(1, -1);

// ctx.fillRect(0, 0, 10, -10);

// for(var i = -141; i < 130; i += 20){
//     ctx.fillRect(i, 5, 1, -9);
// }
// for(var i = -191; i < 170; i += 20){
//     ctx.fillRect(-5, i, 9, -1);
// }
// drawLines(ctx, size, 300, 400);
drawFigures(ctx, size);
// drawFigures(ctx, -100);





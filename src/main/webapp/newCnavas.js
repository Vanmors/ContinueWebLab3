// Получаем canvas элемент
let canvas = document.getElementById('canvas');

// Указываем элемент для 2D рисования
let ctx = canvas.getContext('2d');

ctx.fillStyle = '#3399ff'; // Задаём чёрный цвет для линий
// ctx.fillRect(10, 10, 100, 100);
ctx.lineWidth = 2.0; // Ширина линии
ctx.beginPath(); // Запускает путь
ctx.moveTo(255, 10); // Рисуем ось
ctx.lineTo(255, 490);
ctx.moveTo(10, 255);
ctx.lineTo(490, 255);

ctx.fillRect(255, 255, 195, -100) // Рисуем и закршиваем прямоугльник
ctx.stroke(); // Делаем контур

ctx.beginPath();
ctx.moveTo(450, 255); // Рисуем и закршиваем треугольник
ctx.lineTo(255, 445);
ctx.lineTo(255, 255);
ctx.lineTo(450, 255);
ctx.fill();
ctx.stroke();


ctx.beginPath();
ctx.arc(255, 255, 200, Math.PI, -Math.PI / 2) // Рисуем и закршиваем четверть круга
ctx.moveTo(255, 55);
ctx.lineTo(255, 255);
ctx.lineTo(55, 255);

ctx.fill();
ctx.stroke();


ctx.moveTo(250, 55); // Вставляем R на график
ctx.lineTo(260, 55);
ctx.fillStyle = 'black'; // Задаём чёрный цвет для линий
ctx.font = "30px Verdana";
ctx.fillText("R", 260, 50)
ctx.moveTo(450, 250);
ctx.lineTo(450, 260);
ctx.font = "30px Verdana";
ctx.fillText("R", 455, 250)
ctx.moveTo(250, 445);
ctx.lineTo(260, 445);
ctx.fillText("-R", 270, 460)
ctx.moveTo(55, 250);
ctx.lineTo(55, 260);
ctx.fillText("-R", 15, 250)
ctx.stroke(); // Делаем контур
console.log("I'm ok")

function NULL() {
    if (document.getElementById('form:inputTextId').value === "" || document.getElementById('form:r').value === "") {
        swal("Введите все данные!!!", "")
    }
}

// Swal.fire({
//     title: 'hello',
//     width: 600,
//     padding: '3em',
//     color: '#716add',
//     background: '#fff url(/images/trees.png)',
//     backdrop: `
//     rgba(0,0,123,0.4)
//     url("/images/nyan-cat.gif")
//     left top
//     no-repeat
//   `
// })


function validateXY() {
    let x = document.getElementById("x-textinput").value;
    let y = document.getElementById("inputTextId").value;
    if (x < -2 || x > 2 || y < -5 || y > 3) {
        alert("Некорректно введённые данные")
        document.getElementById("x-textinput").value = "";
        document.getElementById("inputTextId").value = "";
    }
}

function mathFloor(n) {
    if (n < -1.5) {
        return -1.5;
    } else if (n > -1.5 && n < -1) {
        return -1;
    } else if (n > -1 && n < -0.5) {
        return -0.5;
    } else if (n > -0.5 && n < 0) {
        return 0;
    } else if (n > 0 && n < 0.5) {
        return 0.5;
    } else if (n > 0.5 && n < 1) {
        return 1;
    } else if (n > 1 && n < 1.5) {
        return 1.5;
    } else if (n > 1.5 && n < 2) {
        return 2;
    }
}

function pos(e) {
    //let r = $('input[id="r"]:checked').val();
    //console.log(r)
    let r = document.getElementById('form:r').value
    console.log(r)
    if (r === "") {
        swal("Введите R", "")
    } else {
        let x = e.pageX;
        let y = e.pageY;
        console.log(x)
        console.log(y)
        const plot_canvas = document.getElementById("canvas").getBoundingClientRect();
        // if (x > 644 && x < 844 && y < 306 && y > 106) {
        let outX = (x - plot_canvas.left) * 2.5;
        let outY = (y - plot_canvas.top) * 2.5;
        const plot_canvass = document.getElementById("canvas");
        const plot_context = plot_canvass.getContext("2d");
        // plot_context.beginPath();
        // plot_context.fillRect(outX, outY, 5, 5);
        // plot_context.fill();
        // plot_context.stroke(); // Делаем контур
        ctx.beginPath();
        ctx.fillRect(outX, outY, 5, 5);
        //ctx.fill();
        ctx.stroke(); // Делаем контур
        let resX;
        let resY

        if (outX > 255) {
            resX = (outX - 250) * (r / 200);
        } else if (outX === 255) {
            resX = 0;
        } else {
            resX = (outX - 250) * (r / 200);
        }
        if (outY > 255) {
            resY = (outY - 250) * (r / 200) * -1;
        } else if (outY === 255) {
            resY = 0;
        } else {
            resY = (outY - 250) * (r / 200) * -1;
        }

        resX = mathFloor(resX)

        // $("inputText[id*='inputTextId']").val("1");
        document.getElementById('form:inputTextId').value = resY;
        // document.getElementById('form:x-textinput').value = resX;
        // PrimeFaces.widget['widgetID'].id.value = resX
        let xxx = document.getElementById('form:x-textinput').value = resX;
        alert(PF('widgetID').show());
        // PrimeFaces.widget['widgetID'].value = resX;
        // $("inputText[id*='list1SortOrder']").val("1");
        validateXY()

        // }
    }
}


document.addEventListener('DOMContentLoaded', () => {
    document.getElementById("canvas").addEventListener('click', pos);
})
function generateRestaurantStatistics() {
    callForFirstStatistics();
    callForSecondStatistics();
    callForThirdStatistics();
}

function callForFirstStatistics(){
    $.ajax({
        type: "GET",
        url: "/statistics/orders-per-day",
    }).then(function (response) {
        generateChart(response, "bar","Orders per day", 'Number of orders per day', "bar-chart-1");
    });
}

function callForSecondStatistics(){
    $.ajax({
        type: "GET",
        url: "/statistics/users-visited",
    }).then(function (response) {
        generateChart(response, "bar","Users' visits", "Number of users' visits", "bar-chart-2");
    });
}

function callForThirdStatistics(){
    $.ajax({
        type: "GET",
        url: "/statistics/income-per-day",
    }).then(function (response) {
        generateChart(response, "line","Income per day", "Income per day in RON", "bar-chart-3");
    });
}

function exportChart(chart, title) {
    let months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    let a = document.createElement('a');
    a.href = chart.toBase64Image();
    a.download = months[new Date().getMonth()] + " " + new Date().getFullYear() + " " + title + ".png";
    a.click();
}

function generateChart(data, type, title, subtitle, elementId) {
    let canvas = document.createElement('canvas');
    canvas.id = elementId;
    document.getElementById('charts').appendChild(canvas)
    let myChart = new Chart(document.getElementById(elementId), {
        type: type,
        data: {
            labels: Object.keys(data),
            datasets: [
                {
                    label: title,
                    backgroundColor: ["#3cba9f"],
                    data: Object.values(data),
                }
            ]
        },
        options: {
            legend: {display: false},
            title: {
                display: true,
                text: subtitle
            },
            animation: {
                onComplete: function () {
                    exportChart(myChart, title);
                    document.getElementById('charts').removeChild(canvas);
                },
            }
        },
        plugins: [{
            id: 'custom_canvas_background_color',
            beforeDraw: (chart, args, options) => {
                const {ctx} = chart;
                ctx.save();
                ctx.globalCompositeOperation = 'destination-over';
                ctx.fillStyle = options.color;
                ctx.fillRect(0, 0, chart.width, chart.height);
                ctx.restore();
            },
            defaults: {
                color: 'white'
            }
        }],
    });
}
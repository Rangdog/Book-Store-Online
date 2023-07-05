google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
    var data = google.visualization.arrayToDataTable([
        ['Sách', 'Top 5 sách theo lượt mua'],
        ['Ánh Trăng Hiểu Lòng Em', 562],
        ['Thanh Am Tình', 510],
        ['Vì Em', 410],
        ['Làm Cách Nào Để Tài Quay Về', 2],
        ['Tôi Buồn Ngủ', 1]
    ]);

    var options = {
        title: 'Sách bán chạy',
        legend: { position: 'none' },
        'width': 1000,
        'height': 500
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('chart_div_1'));
    chart.draw(data, options);
}

google.charts.setOnLoadCallback(drawChart2);
function drawChart2() {
    var data = google.visualization.arrayToDataTable([
        ['Sách', 'Điểm'],
        ['Ánh Trăng Hiểu Lòng Em', 9.0],
        ['Thanh Am Tình', 8.2],
        ['Vì Em', 8.0],
        ['Làm Cách Nào Để Tài Quay Về', 7.5],
        ['Tôi Buồn Ngủ', 7.0]
    ]);

    var options = {
        title: 'Top 5 sách Có lượt đánh giá cao',
        legend: { position: 'none' },
        'width': 1000,
        'height': 500
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('chart_div_2'));
    chart.draw(data, options);
}
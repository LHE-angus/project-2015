<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html lang="en">
<head>
  <link href="${ctx}/manager/leader/style/bootstrap.css" rel="stylesheet" />
  <link rel="stylesheet" href="${ctx}/manager/leader/style/font-awesome.css" />
  <link rel="stylesheet" href="${ctx}/manager/leader/style/jquery-ui.css" /> 
  <link rel="stylesheet" href="${ctx}/manager/leader/style/fullcalendar.css" />
  <link rel="stylesheet" href="${ctx}/manager/leader/style/prettyPhoto.css" />  
  <link rel="stylesheet" href="${ctx}/manager/leader/style/rateit.css" />
  <link rel="stylesheet" href="${ctx}/manager/leader/style/bootstrap-datetimepicker.min.css" />
  <link rel="stylesheet" href="${ctx}/manager/leader/style/jquery.cleditor.css" /> 
  <link rel="stylesheet" href="${ctx}/manager/leader/style/uniform.default.css" /> 
  <link rel="stylesheet" href="${ctx}/manager/leader/style/bootstrap-switch.css" />
  <link rel="stylesheet" href="${ctx}/manager/leader/style/bootstrap.css" />
  <link href="${ctx}/manager/leader/style/style_not_bi.css" rel="stylesheet" />
  <link href="${ctx}/manager/leader/style/widgets.css" rel="stylesheet" />   
  
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="js/html5shim.js"></script>
  <![endif]-->

  <!-- Favicon -->
  <link rel="shortcut icon" href="${ctx}/manager/leader/img/favicon/favicon.png">
</head>

<body>

<script src="${ctx}/manager/leader/js/jquery.js"></script> <!-- Bootstrap -->
<script src="${ctx}/manager/leader/js/bootstrap.js"></script> <!-- Bootstrap -->
<script src="${ctx}/manager/leader/js/jquery-ui-1.9.2.custom.min.js"></script> <!-- jQuery UI -->
<script src="${ctx}/manager/leader/js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
<script src="${ctx}/manager/leader/js/jquery.rateit.min.js"></script> <!-- RateIt - Star rating -->
<script src="${ctx}/manager/leader/js/jquery.prettyPhoto.js"></script> <!-- prettyPhoto -->

<!-- jQuery Flot -->
<script src="${ctx}/manager/leader/js/excanvas.min.js"></script>
<script src="${ctx}/manager/leader/js/jquery.flot.js"></script>
<script src="${ctx}/manager/leader/js/jquery.flot.resize.js"></script>
<script src="${ctx}/manager/leader/js/jquery.flot.pie.js"></script>
<script src="${ctx}/manager/leader/js/jquery.flot.stack.js"></script>

<!-- jQuery Notification - Noty -->
<script src="${ctx}/manager/leader/js/jquery.noty.js"></script> <!-- jQuery Notify -->
<script src="${ctx}/manager/leader/js/themes/default.js"></script> <!-- jQuery Notify -->
<script src="${ctx}/manager/leader/js/layouts/bottom.js"></script> <!-- jQuery Notify -->
<script src="${ctx}/manager/leader/js/layouts/topRight.js"></script> <!-- jQuery Notify -->
<script src="${ctx}/manager/leader/js/layouts/top.js"></script> <!-- jQuery Notify -->
<!-- jQuery Notification ends -->

<script src="${ctx}/manager/leader/js/sparklines.js"></script> <!-- Sparklines -->
<script src="${ctx}/manager/leader/js/jquery.cleditor.min.js"></script> <!-- CLEditor -->
<script src="${ctx}/manager/leader/js/bootstrap-datetimepicker.min.js"></script> <!-- Date picker -->
<script src="${ctx}/manager/leader/js/jquery.uniform.min.js"></script> <!-- jQuery Uniform -->
<script src="${ctx}/manager/leader/js/bootstrap-switch.min.js"></script> <!-- Bootstrap Toggle -->
<script src="${ctx}/manager/leader/js/filter.js"></script> <!-- Filter for support page -->
<script src="${ctx}/manager/leader/js/custom.js"></script> <!-- Custom codes -->
<script src="${ctx}/manager/leader/js/charts.js"></script> <!-- Charts & Graphs -->
<script type="text/javascript">
$(function () {

    /* Bar Chart starts */

    var d1 = [];
    for (var i = 0; i <= 20; i += 1)
        d1.push([i, parseInt(Math.random() * 30)]);

    var d2 = [];
    for (var i = 0; i <= 20; i += 1)
        d2.push([i, parseInt(Math.random() * 30)]);


    var stack = 0, bars = true, lines = false, steps = false;
    
    function plotWithOptions() {
        $.plot($("#bar-chart"), [ d1, d2 ], {
            series: {
                stack: stack,
                lines: { show: lines, fill: true, steps: steps },
                bars: { show: bars, barWidth: 0.8 }
            },
            grid: {
                borderWidth: 0, hoverable: true, color: "#777"
            },
            colors: ["#ff6c24", "#ff2424"],
            bars: {
                  show: true,
                  lineWidth: 0,
                  fill: true,
                  fillColor: { colors: [ { opacity: 0.9 }, { opacity: 0.8 } ] }
            }
        });
    }

    plotWithOptions();
    
    $(".stackControls input").click(function (e) {
        e.preventDefault();
        stack = $(this).val() == "With stacking" ? true : null;
        plotWithOptions();
    });
    $(".graphControls input").click(function (e) {
        e.preventDefault();
        bars = $(this).val().indexOf("Bars") != -1;
        lines = $(this).val().indexOf("Lines") != -1;
        steps = $(this).val().indexOf("steps") != -1;
        plotWithOptions();
    });

    /* Bar chart ends */

});


/* Curve chart starts */

$(function () {
    var sin = [], cos = [];
    for (var i = 0; i < 14; i += 0.5) {
        sin.push([i, Math.sin(i)]);
        cos.push([i, Math.cos(i)]);
    }

    var plot = $.plot($("#curve-chart"),
           [ { data: sin, label: "sin(x)"}, { data: cos, label: "cos(x)" } ], {
               series: {
                   lines: { show: true, fill: true},
                   points: { show: true }
               },
               grid: { hoverable: true, clickable: true, borderWidth:0 },
               yaxis: { min: -1.2, max: 1.2 },
               colors: ["#1eafed", "#1eafed"]
             });

    function showTooltip(x, y, contents) {
        $('<div id="tooltip">' + contents + '</div>').css( {
            position: 'absolute',
            display: 'none',
            top: y + 5,
            left: x + 5,
            border: '1px solid #000',
            padding: '2px 8px',
            color: '#ccc',
            'background-color': '#000',
            opacity: 0.9
        }).appendTo("body").fadeIn(200);
    }

    var previousPoint = null;
    $("#curve-chart").bind("plothover", function (event, pos, item) {
        $("#x").text(pos.x.toFixed(2));
        $("#y").text(pos.y.toFixed(2));

        if ($("#enableTooltip:checked").length > 0) {
            if (item) {
                if (previousPoint != item.dataIndex) {
                    previousPoint = item.dataIndex;
                    
                    $("#tooltip").remove();
                    var x = item.datapoint[0].toFixed(2),
                        y = item.datapoint[1].toFixed(2);
                    
                    showTooltip(item.pageX, item.pageY, 
                                item.series.label + " of " + x + " = " + y);
                }
            }
            else {
                $("#tooltip").remove();
                previousPoint = null;            
            }
        }
    }); 

    $("#curve-chart").bind("plotclick", function (event, pos, item) {
        if (item) {
            $("#clickdata").text("You clicked point " + item.dataIndex + " in " + item.series.label + ".");
            plot.highlight(item.series, item.datapoint);
        }
    });

});




/* Curve chart ends */
</script>
</body>
</html>
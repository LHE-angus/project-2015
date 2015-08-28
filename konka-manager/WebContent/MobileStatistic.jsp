<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<title>销售数据汇总分析</title>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="../excanvas.min.js"></script><![endif]-->
<script language="javascript" type="text/javascript" src="${ctx}/scripts/flot/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/scripts/flot/jquery.flot.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/scripts/flot/jquery.flot.pie.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/flot/css/global.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/flot/css/statistics.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/styles/flot/css/flot-rewrite.css" />
<style type="text/css">
.hovercol { background-color:#009; color:#C30;}
.tdTab {word-break: break-all;}
.tdTab td {width:20px;}
</style>
</head>
<body bgcolor="363080" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" ondblclick="do_big()">
<table width="100%" height="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="stabox2">
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="statt1">
        <tr>
          <td nowrap="nowrap"><h2 class="statt1h22">销售数据汇总分析</h2></td>
        </tr>
		<tr><td valign="bottom">
		<select id="sel_type" name="sel_type" class="select1">
              <option value="1" selected="selected">按型号汇总</option>
              <option value="2">按尺寸汇总</option>
              <option value="3">按月份汇总</option>
              </select>
              </td>
		</tr>
      </table></td>
  </tr>
  <tr>
    <td valign="top"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="mar2">
    <!--    <tr>
          <td valign="top"><h3 class="statt1h3"></h3></td>
        </tr> -->
        <tr>
          <td align="center" class="tdbgc4" style="border:2px solid #fff;"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td align="center" style=
				"padding-top:5px;"><div id="placeholder" style="width:90%;height:270px;color:#FFF;"></div></td>
              </tr>
			  <tr>
				 <td valign="top" style="padding:5px 0px;"><h3 class="statt1h3"></h3></td>
			  </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
<script id="source" type="text/javascript">
/**
 * @param   d the delimiter
 * @param   p the pattern of your date
 * @author  meizz
 * @author  kimsoft add w+ pattern
 */
Date.prototype.format = function(style) {
	var o = {
		"M+" : this.getMonth() + 1, //month
		"d+" : this.getDate(),      //day
		"h+" : this.getHours(),     //hour
		"m+" : this.getMinutes(),   //minute
		"s+" : this.getSeconds(),   //second
		"w+" : "\u65e5\u4e00\u4e8c\u4e09\u56db\u4e94\u516d".charAt(this.getDay()),   //week
		"q+" : Math.floor((this.getMonth() + 3) / 3),  //quarter
		"S"  : this.getMilliseconds() //millisecond
	}
	if (/(y+)/.test(style)) {
		style = style.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for(var k in o){
		if (new RegExp("("+ k +")").test(style)){
			style = style.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return style;
};

$(function () {
	function plotWithOptions(r_month, type) {
		$.ajax({
			 url:'http://localhost:8080/konka-wd/MobileStatistic.do?username=cxtest&userpass=10&type=1&method=GetStatistic',
			 //url:'http://localhost:8080/swsjjc/flot/ScSixProvSalesPie.do',
			 //data : {"r_month" : r_month, "type" : type },
			 dataType:"jsonp",
			 jsonp:"jsonpcallback",
			 success:function(data){
				if(data.length == 0){
				   $("#placeholder").html("<table width=\"98%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"><tr class=\"tt\"><td height=\"395\" align=\"center\">暂无数据</td></tr></table>");
				   return;
				}

				$.plot($("#placeholder"), data, {
					series: {
						 pie: {
								//innerRadius: 0.2, //里面空白圆的显示百分比
								show: true,//显示饼状图
								radius: 1,//饼状图向外延伸的半径（只能是0～1，表示百分数；大于1，表示半径实际长）
								tilt: 0.5,
								label: { //控制外面显示的百分比标签
										show: true,//是
										radius: 0.8,//控制标签距离圆心的距离（只能是0～1，表示百分数；大于1，表示半径实际长）
										formatter: function(label, slice){
												return '<div id=\"' + label + '_div\" style="font-size:x-small;text-align:center;padding:2px;color:white;">'+label+'<br/>'+Math.round(slice.percent)+'%</div>';;
										},
										threshold: -1,//标签可以隐藏，如果
										combine: {
													color: '#999',
													threshold: 1
												}
										}
								}
					 },
					 colors:["#666699","#663399","#8866CC","#333366","#CC99FF","#9966CC","#330099"],
					 legend: {
						 show: false //显示图示；如果是false的话，就是在圆附近列出每部分的百分比
					 },
					grid: {
						hoverable: true,
						clickable: true
					}
				});
			 }
		});

	}
	
	var previousPoint = null;
	$("#placeholder").bind("plothover", function pieHover(event, pos, item) {
	  //console.log(item);
		if (item) {
            if (previousPoint != item.datapoint) {
                previousPoint = item.datapoint;
                
                $("#tooltip").remove();
                var x = item.datapoint[0].toFixed(2),
                    y = item.datapoint[1].toString().split(",")[1]; 
					var unit = "亿美元";
					if($("#sel_type").val() == 402){
						y = (y / 10000).toFixed(2);
					}
					if($("#sel_type").val() == 102){
						unit = "亿元";
					}else{
						unit = "亿美元";
					}
					y = item.series.label + "：" + y + unit;
					$("#" + item.series.label + "_div").parent().parent().attr("title",y).css("cursor","pointer");
                
            }
        }  else {
         //   $("#tooltip").remove();
            previousPoint = null;            
        }

	});
	
	var cur = new Date("2013/04");
	var opts = [];
	var default_selected = "201303";
	for(var i = 0; i < 12 ; i++ ) {
		var mm = cur.format("yyyyMM");
		if(mm.substring(4,6) != "01"){
		    opts[opts.length] = "<option value='" + mm + "'" + (mm == default_selected ? "selected='selected'" : "") + ">" + cur.format("yyyy年1-M月") + "</option>";
		}else{
			opts[opts.length] = "<option value='" + mm + "'" + (mm == default_selected ? "selected='selected'" : "") + ">" + cur.format("yyyy年1月") + "</option>";
		}
		cur.setMonth(cur.getMonth() - 1);
	}
	$("#sel_month").append(opts.join("")).change(function(){
		plotWithOptions($(this).val(), $("#sel_type").val());
		var mm = $(this).val().substring(4,6);
		if(mm != "01"){
		   $("#month").html("1-" + mm.trim('0'));
		}else{
		   $("#month").html("1");
		}
	});

	$("#sel_type").change(function(){
		plotWithOptions($("#sel_month").val(), $(this).val());
	    $(".statt1h3").html($(this).find("option:selected").text());
		var mm =$("#sel_month").val().substring(4,6);
		if(mm != "01"){
		   $("#month").html("1-" + mm.trim('0'));
		}else{
		   $("#month").html("1");
		}
	});
	
	$("#sel_type").change();
	$("#sel_month").change();
	
	function changeSelect(){
		var intervals = arguments[0];
		var registed_selects = [];
		var split_char = ",";
		for (var i = 1; i < arguments.length ; i++ ) {
			var sel_id_cur = arguments[i];
			$("#" + arguments[i]).children().each(function(){
				if($(this).val() != "101"){
					registed_selects[registed_selects.length] = sel_id_cur + split_char + $(this).attr("value");
				}
			});
		}
	
		var count = 0;
		var intervalId = setInterval(function(){
			var sel_id  = registed_selects[count].split(split_char)[0];
			var sel_val = registed_selects[count].split(split_char)[1];
			$("#" + sel_id).val(sel_val); 
			$("#" + sel_id).change();
	
			count++;
			if (count > registed_selects.length - 1) count = 0;
		}, intervals);

		$("body").mouseover(function(){
			window.clearInterval(intervalId);
	    }).mouseout(function(){
				intervalId = setInterval(function(){
				var sel_id  = registed_selects[count].split(split_char)[0];
				var sel_val = registed_selects[count].split(split_char)[1];
				$("#" + sel_id).val(sel_val); 
				$("#" + sel_id).change();
		
				count++;
				if (count > registed_selects.length - 1) count = 0;
		    }, intervals);
		});
	};
	
	var intervals = 1000 * 10;
	changeSelect(intervals, "sel_type");
	//changeSelect(intervals * ($("#sel_type option").length + 0.5), "sel_month");
});
String.prototype.trim = function(chr){ return this.replace((!chr) ? new RegExp('^\\s+|\\s+$', 'g') : new RegExp('^' + chr + '+|' + chr + '+$', 'g'), ""); };

function do_big(){
 top.location.href = location.href.replace(".html","-big.html");
}
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<title>康佳渠道信息管理系统</title>
<link rel=stylesheet type=text/css  href="${ctx}/styles/frame3/css/css.css" />
<link rel=stylesheet type=text/css  href="${ctx}/styles/frame3/css/global.css" />
<script language="javascript" type="text/javascript" src="${ctx}/styles/frame/js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/styles/frame/js/jquery.flot.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/styles/frame/js/jquery.flot.pie.js"></script>
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="${ctx}/styles/frame/js/excanvas.min.js"></script><![endif]-->
<style type="text/css">
#placeholder {
	width: 80px;
}
.demo-container {
	width: 80px;
	height: 80px;
	padding: 0px;
	margin: 0px;
	border: 0px;
	background: #fff;
	margin-left: auto;
	margin-right: auto;
	padding-top: 5px;
}
.demo-placeholder {
	width: 100%;
	height: 100%;
	font-size: 14px;
	line-height: 1.2em;
}
</style>
</head>
<body>
<div class="right_contcontleft"  style=" border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #C9CFCD;">
  <table  border="0" class="k_tab" style="_width:100%;">
    <tr>
      <td><img src="${ctx}/styles/frame3/css/images/k_tup.jpg" width="33" height="32" /></td>
      <td nowrap="nowrap" width="97%">&nbsp; 当前位置：首页 &gt; 决策分析</td>
    </tr>
  </table>
</div>
<div style=" width:99%">
  <div class="m_l1">
    <div style="width:99%">
      <div class="k_tongzhi111"><font class="">当年截止到昨日总体情况</font></div>
      <div class="right_contcont2">
        <div style="float:left;">
          <div class="t1" style="position:relative;" >
            <div style="margin:10px; margin-top: 0px; height: 89px;">
              <div class="demo-container">
                <div id="placeholder" class="demo-placeholder"></div>
              </div>
              <div style="position:absolute;top:5px;left:45px;width:80px;height:80px;line-height:80px;text-align:center;color:#888;">
                <p><span id="wc_jd"></span></p>
              </div>
            </div>
            <div style="margin:10px; margin-top: 10px; color: #4b4b4b;">当月销售任务完成进度 </div>
          </div>
          <div class="t1" >
            <div  class="f_dangnian">
              <p style="color:#888;"><strong id="s_dy_xse"></strong></p>
              <p><strong>亿元</strong></p>
            </div>
            <div style="margin:10px; margin-top: 10px; color: #4b4b4b;">当月销售额</div>
          </div>
          <div class="t111" style="margin-left:2px; float: left;" >
            <div  class="lingshouliang">
              <p style="color:#888;"><strong id="s_dy_lsl"></strong></p>
              <p><strong>台</strong></p>
            </div>
            <div style="margin:10px; margin-top: 10px; color: #4b4b4b;">销售量</div>
          </div>
          <div class="t1"  style="margin-left:5px">
            <div  class="f_dangnian">
              <p style="color:#888;"><strong id="s_dy_lse"></strong></p>
              <p><strong>万元</strong></p>
            </div>
            <div style="margin:10px; margin-top: 10px; color: #4b4b4b;">销售额</div>
          </div>
          <div class="t111" style="margin-left:2px; float: left;" >
            <div  class="lingshouliang">
              <p><strong style="color:#888;">${back_money}</strong></p>
              <p><strong>万元</strong></p>
            </div>
            <div style="margin:10px; margin-top: 10px; color: #4b4b4b;">当月回款金额</div>
          </div>
        </div>
        <div class="clear"></div>
        <div> </div>
      </div>
      <div class="k_tongzhi111"><font class="">当月截止到昨日分公司任务进度</font></div>
      <div class="right_contcont2" style="margin-bottom: 24px;height:350px;">
        <div id="chartdiv"> </div>
      </div>
      <div class="clear"></div>
    </div>
  </div>
</div>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

	var year = '${this_year}';
	var month = '${this_month}';
	$.ajax( {
		type : "POST",
		cache : false,
		url : "${ctx}/webservice/KonkaR3RankInterface.do",
		data : "method=getKonkaR3SellToJson&user_id=" + '${user_id}'+"&year="+year+"&month="+month,
		//data : "method=getKonkaR3SellToJson&user_id=" + '${user_id}'+"&year=2012&month=5",
		dataType: "json",
		error : function(data) {/* alert("Sorry! Error Code :" + data.status); */ },
		success : function(data) {
		if (data){
		  if(data.status == '0'){
			//任务进度
			//var flash = "MSColumn3D.swf";
			
			var flash = "MSStackedColumn2DLineDY.swf";
			var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "96%", "320", "0", "0");

			/*
			var xmldata = "<chart palette='2' shownames='1' showvalues='1' decimals='2'";
    		xmldata += "numberSuffix='%' formatNumberScale='0' useRoundEdges='1' legendBorderAlpha='0' baseFontColor='666666' BaseFontSize ='12' showBorder='1' bgSWFAlpha='0' canvasBgAlpha='0'>";
    		xmldata += "<categories>";
    		var xml_data_set = "<dataset seriesName='任务完成%' showValues='1' color='1D8BD1'>";
    		for(var i=0; i<data.list.length; i++){		
    			xmldata += "<category label='"+data.list[i].dept_name+"' />";
    			xml_data_set +="<set value='"+data.list[i].sale+"' />";
        	}
    		
    		xmldata += "</categories>";
    		xml_data_set +="</dataset>";
    		xmldata += xml_data_set;
			xmldata += "</chart>";*/
			
			var xmldata = "<chart palette='2' shownames='1' showvalues='1' decimals='2' "; 
	        xmldata += "sNumberSuffix='%' setAdaptiveSYMin='1' showPlotBorder='1' showBorder='0' bgColor='FFFFFF' >";
	        xmldata += "<categories>";
     		var xml_data_set1 = "<dataset><dataset seriesName='任务销售额' showValues='0' color='F1683C'>";
     		var xml_data_set2 = "<dataset><dataset seriesName='实际销售额' showValues='0' color='3366FF'>";
     		var xml_data_set3 = "<lineSet seriesName='任务完成%' showValues='1' color='FFFF31' lineThickness='4'>";
     		for(var i=0; i<data.list.length; i++){		
     			xmldata += "<category label='"+data.list[i].dept_name+"' />";
     			xml_data_set1 +="<set value='"+data.list[i].rw_money+"' />";
     			xml_data_set2 +="<set value='"+data.list[i].all_price+"' />";
     			xml_data_set3 +="<set value='"+data.list[i].sale+"' />";
	       	}
     		xmldata += "</categories>";
     		xml_data_set1 +="</dataset></dataset>";
     		xml_data_set2 +="</dataset></dataset>";
     		xml_data_set3 +="</lineSet>";
     		xmldata += xml_data_set1;
     		xmldata += xml_data_set2;
     		xmldata += xml_data_set3;
     		xmldata += "</chart>";

            chart.setDataXML(xmldata);
            chart.render("chartdiv");
			
			$("#s_dy_xse").text((Number(data.total_price)/10000).toFixed(2));
			$("#s_dy_lsl").text(data.sell_num);
			$("#s_dy_lse").text((Number(data.sell_money)/10000).toFixed(2));
			$("#wc_jd").text(data.rw_sale > 0 ? parseInt(data.rw_sale) : '-');
			var placeholder = $("#placeholder");
			var ratio = Number(data.rw_sale); // unit : %
			$.plot(placeholder, [{'data' : ratio}, {'data' : 100 - ratio}], 
			   {
				series: {
					pie: { 
						innerRadius: 0.4,
						show: true
						}
				}
			});
		  } else {
      		// alert("数据加载请求失败");
      		}
      	  }
    	}
	});
});

function view_and_print(id) {
    window.showModalDialog("${ctx}/manager/oa/AuditIngFiles.do?azaz=" + Math.random() + "&method=view&id=" +id, window, "dialogWidth:800px;status:no;dialogHeight:600px");
}
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/styles/frame/css/css.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/styles/frame/css/global.css" />
<script language="javascript" type="text/javascript"
	src="${ctx}/styles/frame/js/jquery.js"></script>
<script language="javascript" type="text/javascript"
	src="${ctx}/styles/frame/js/jquery.flot.js"></script>
<script language="javascript" type="text/javascript"
	src="${ctx}/styles/frame/js/jquery.flot.pie.js"></script>
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar/WdatePicker.js"></script>
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
.but1 {width:63px;height:20px;background:url(${ctx}/images/manager/but_search.gif) no-repeat;font:normal 12px/20px "宋体";text-align:left;color:#fff;padding-left:27px;border:1px #ccc solid;border-left:0;cursor:pointer;}

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
  <table  border="0" class="k_tab" style="width:100%;">
    <tr>
      <td><img src="${ctx}/styles/frame3/images/k_tup.jpg" width="33" height="32" /></td>
      <td width="97%">&nbsp; 当前位置：首页 &gt; 触网平台</td>
    </tr>
  </table>
</div>
<div class="m_l" style="width: 100%">
			<div style="width: 99%">
				<div class="k_tongzhi111">
					<span style="float: right; font-size: 12px;"></span><font class="">当月截止到今日总体情况</font>
				</div>
				<table width="100%">
					<tr><td>
						<!-- >div class="right_contcont2" -->
							<div class="t1" style="position: relative;margin-left: 15px;">
								<div style="margin: 5px; margin-left: 10px; height: 89px;">
									<div class="demo-container">
										<div id="placeholder" class="demo-placeholder"></div>
									</div>
									<div
										style="position: absolute; top: 5px; left: 40px; width: 80px; height: 80px; line-height: 80px; text-align: center; color: #888;">
										<p>
											<span id="wc_jd"></span>
										</p>
									</div>
								</div>
								<div style="margin: 10px; margin-top: 10px; color: #4b4b4b;">任务完成进度
								</div>
								</div>
							<!--  >/div-->
							</td>
							<td>
								<div class="t1">
									<div class="f_dangnian">
										<p style="color: #888;">
											<span id="s_dy_xse"></span>万元
										</p>
									</div>
									<div style="margin: 10px; margin-top: 10px; color: #4b4b4b;">当月销售额</div>
								</div>
							</td>
							<td>
								<div class="t1">
									<div class="lingshouliang">
										<p style="color: #888;">
											<span id="s_dy_lsl"></span>台
										</p>
									</div>
									<div style="margin: 10px; margin-top: 10px; color: #4b4b4b;">零售量</div>
								</div>
							</td>
							<td>
								<div class="t1">
									<div class="lingshouliang">
										<p style="color: #888;">
											<span id="s_dy_hys"></span>个
										</p>
									</div>
									<div style="margin: 10px; margin-top: 10px; color: #4b4b4b;">新增会员数</div>
								</div>
							</td>
					</tr>
				</table>
			</div>
			<div class="k_tongzhi111">
				<span style="float:right;">选择日期：<fmt:formatDate value="${af.map.day_time_start}" pattern="yyyy-MM" var="_day_time_start" />
             <html-el:text styleId="day_time_start" property="day_time_start" size="20" maxlength="15" readonly="true" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" styleClass="Wdate" style="cursor:pointer;text-align:center;" value="${_day_time_start}" />&nbsp;&nbsp;
             <input name="button" type="submit" class="but1" id="button" value="搜 索" style="font-family:Microsoft YAHEI;" /></span> <font
					class="">当月截止到今日月度报表</font>
			</div>
			<div class="right_contcont2"
				style="margin-bottom: 24px; height: 450px;">     
				<div id="chartdiv"></div>
			</div>
			<div class="clear"></div>
		</div> 
<div class="m_l1" style="margin-top:750px;">
      <div class="k_tongzhi111"><font class="">库存预警</font><span style="float: right; font-size: 12px;"><a href="PdShowHomePage.do?method=listAll">更多</a></span></div>
      <div class="right_contcont2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtab5">
          <tr>
            <th style="text-align:center;" width="50%">商品型号</th>
            <th style="text-align:center;" width="30%">R3库存</th>
            <th style="text-align:center;" width="20%">操作</th>
          </tr>
           <c:forEach items="${entityList}" var="cur" varStatus="vs">
            <tr >
              <td align="center">${cur.pd_sn}</td>
              <td align="center">${cur.map.r3_stock}</td>
              <td align="center"><span style="cursor:pointer;" class="fblue"  onclick="location.href='PdShowHomePage.do?method=view&mod_id=${af.map.mod_id}&id=${cur.id}';">查看</span></td>
           </tr>
           <c:if test="${vs.last eq true}">
			<c:set var="i" value="${vs.count}" />
			</c:if>
            </c:forEach>
            <c:forEach begin="${i}" end="4">
						<tr align="center">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
			</c:forEach>
        </table>
      </div> 
       <div class="k_tongzhi111"><font class="">待审核订单</font><span style="float: right; font-size: 12px;"><a href="${ctx}/manager/spgl/PshowOrderAudit.do?method=audit&mod_id=905604">更多</a></span></div>
      <div class="right_contcont2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtab5">
          <tr>
            <th style="text-align:center;" width="25%">订单流水号</th>
            <th style="text-align:center;" width="25%">购买人姓名</th>
            <th style="text-align:center;" width="25%">下单时间</th>
            <th style="text-align:center;" width="25%">操作</th>   
          </tr>
           <c:forEach items="${entityList2}" var="cur" varStatus="vs">
            <tr >
              <td align="center"><c:out value="${cur.trade_index}" /></td>
              <td align="center"><c:out value="${cur.buyer_name}" /></td>
              <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yy-MM-dd HH:mm:ss" /></td>
              <td align="center"><span style="cursor:pointer;" class="fblue"  onclick="location.href='${ctx}/manager/spgl/PshowOrderAudit.do?method=audit&mod_id=905604&id=${cur.id}';">审核</span></td>
           </tr>
           <c:if test="${vs.last eq true}">
			<c:set var="ii" value="${vs.count}" />
			</c:if>
            </c:forEach>
            <c:forEach begin="${ii}" end="4">
						<tr align="center">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
			</c:forEach>
        </table>
      </div>
      <div class="k_tongzhi111"><font class="">待审核会员</font><span style="float: right; font-size: 12px;"><a href="${ctx}/manager/spgl/EcUser.do?is_act=2&mod_id=905901">更多</a></span></div>
      <div class="right_contcont2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtab5">
          <tr>
            <th style="text-align:center;" width="25%">登录名</th>
            <th style="text-align:center;"width="25%">姓名</th>
            <th style="text-align:center;" width="25%">注册时间</th>
            <th style="text-align:center;" width="25%">操作</th>
          </tr>
           <c:forEach items="${entityList3}" var="cur" varStatus="vs">
            <tr >
              <td align="center"><c:out value="${cur.user_name}" /></td>
              <td align="center"><c:out value="${cur.real_name}" /></td>
              <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yy-MM-dd HH:mm:ss" /></td>
              <td align="center"><span style="cursor:pointer;" class="fblue"  onclick="location.href='${ctx}/manager/spgl/EcUser.do?method=audit&mod_id=905901&user_id=${cur.id}';">审核</span></td>
           </tr>
           <c:if test="${vs.last eq true}">
			<c:set var="iii" value="${vs.count}" />
			</c:if>
            </c:forEach>
            <c:forEach begin="${iii}" end="4">
						<tr align="center">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td> 
						</tr>
			</c:forEach>
        </table>
      </div>  
       <div class="k_tongzhi111"><font class="">待发放佣金</font><span style="float: right; font-size: 12px;"><a href="${ctx}/manager/spgl/EcJieSuanRebatesPay.do?mod_id=905509">更多</a></span></div>
      <div class="right_contcont2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtab5">
          <tr>
            <th style="text-align:center;" width="25%">交易流水号</th>
            <th style="text-align:center;" width="25%">员工姓名</th>
            <th style="text-align:center;" width="25%">佣金</th>
            <th style="text-align:center;" width="25%">下单时间</th>
          </tr> 
           <c:forEach items="${entityList4}" var="cur" varStatus="vs">
            <tr >  
              <td align="center"><c:out value="${cur.map.trade_index}" /></td>
              <td align="center"><c:out value="${cur.ecUser.real_name}" /></td>
              <td align="center"><c:out value="${cur.rebates}" /></td>
              <td align="center"><fmt:formatDate value="${cur.map.add_date}" pattern="yy-MM-dd HH:mm:ss" /></td>
           </tr>
           <c:if test="${vs.last eq true}">
			<c:set var="iiii" value="${vs.count}" />
			</c:if>
            </c:forEach>
            <c:forEach begin="${iiii}" end="4">
						<tr align="center">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
			</c:forEach>
        </table>
      </div>
        <div class="k_tongzhi111"><font class="">大宗采购</font><span style="float: right; font-size: 12px;"><a href="${ctx}/manager/spgl/EcBuyInfo.do?mod_id=907210">更多</a></span></div>
      <div class="right_contcont2">
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtab5">
          <tr>
            <th style="text-align:center;" width="25%">客户名称</th>
            <th style="text-align:center;" width="25%">客户单位</th>
            <th style="text-align:center;" width="25%">添加时间</th>
            <th style="text-align:center;" width="25%">操作</th>
          </tr> 
           <c:forEach items="${entityList5}" var="cur" varStatus="vs">
            <tr >  
              <td align="center"><c:out value="${cur.c_name}" /></td>
              <td align="center"><c:out value="${cur.c_dw_name}" /></td>
              <td align="center"><fmt:formatDate value="${cur.add_date}" pattern="yy-MM-dd HH:mm:ss" /></td>
              <td align="center"><span style="cursor:pointer;" class="fblue"  onclick="location.href='${ctx}/manager/spgl/EcBuyInfo.do?method=view&mod_id=907210&id=${cur.id}';">查看</span></td>
           </tr>
           <c:if test="${vs.last eq true}">
			<c:set var="iiiii" value="${vs.count}" />
			</c:if>
            </c:forEach>
            <c:forEach begin="${iiiii}" end="4">
						<tr align="center">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
			</c:forEach>
        </table>
      </div>
      <div class="clear"></div> 
</div>

<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script src="${ctx}/commons/scripts/echarts/esl.js"></script>
<script type="text/javascript">//<![CDATA[
   $(document).ready(function() {
	 //总体情况
		$("#s_dy_xse").text((Number('${total_p}')/10000).toFixed(2));
		$("#s_dy_lsl").text('${total_num}');
		$("#wc_jd").text('${rw_wcl}' > 0 ? parseInt('${rw_wcl}') : '-');
		$("#s_dy_hys").text('${new_ec_user}'); 
		
		var placeholder = $("#placeholder");
		
		var ratio = Number('${rw_wcl}'); // unit : %
		$.plot(placeholder, [{'data' : ratio}, {'data' : 100 - ratio}], 
		   {
			series: {
				pie: { 
					innerRadius: 0.4,
					show: true
					}
			}
		});

		var start_time = $("#day_time_start").val();
		fusionchartsShow(start_time);
		
		$("#button").click(function(){
			start_time = $("#day_time_start").val();
			fusionchartsShow(start_time);
		});
		
   }); 

	function fusionchartsShow(start_time){
		//var flash = "Line.swf";
		//var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "96%", "320", "0", "0");
		//var xmldata ="<graph caption='Monthly Sales Summary' subcaption='For the year 2006' xAxisName='Month' yAxisMaxValue='45000' yAxisMinValue='15000' yAxisName='Sales' numberPrefix='$' decimalPrecision='0'><set name='Jan' value='17400' /><set name='Feb' value='18100' /><set name='Mar' value='21800' /><set name='Apr' value='23800' /><set name='May' value='29600' /><set name='Jun' value='27600' /><set name='Jul' value='31800' /><set name='Aug' value='39700' /><set name='Sep' value='37800' /><set name='Oct' value='21900' /><set name='Nov' value='32900' /><set name='Dec' value='39800' /></graph>";
		//chart.setDataXML(xmldata);
       // chart.render("chartdiv");
	   $.ajax({
		   type : "POST",
			cache : false,
			url : "${ctx}/manager/admin/PdShowHomePage.do",
			data : "method=getDaySellForCharts&day_time_start="+start_time,
			dataType: "json",
			error : function(data) { /* alert("Sorry! Error Code :" + data.status); */ },
			success : function(data) {
				var flash = "MSLine.swf";//MSLine.swf,MSBar2D.swf
				var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "96%", "450", "0", "0");
				var xmldata = "<chart palette='4' caption='"+start_time+"月度报表' yaxisname='月度报表' hovercapbg='FFFFFF' toolTipBorder='889E6D' divLineColor='999999' divLineAlpha='80' showShadow='0' canvasBgColor='FEFEFE' canvasBaseColor='FEFEFE' canvasBaseAlpha='50' divLineIsDashed='1' divLineDashLen='1' divLineDashGap='2'  chartRightMargin='30' useRoundEdges='1' legendBorderAlpha='0' >"; 
		        xmldata += "<categories>";
	     		var xml_data_set1 = "<dataset seriesName='销售额(K)' showValues='0' color='F1683C'>";
	     		var xml_data_set2 = "<dataset seriesName='销售量(个)' showValues='0' color='AFD8F8'>";
	     		var xml_data_set3 = "<dataset seriesName='订单数(个)' showValues='0' color='F6BD0F'>";
	     		var xml_data_set4 = "<dataset seriesName='新增会员数(个)' showValues='0' color='8BBA00'>";
	     		
	     		for(var i=0; i<data.length; i++){		
	     			xmldata += "<category label='"+data[i].day_time+"' />";
	     			xml_data_set1 +="<set value='"+data[i].day_order_price+"' />";
	     			xml_data_set2 +="<set value='"+data[i].day_num+"' />";
	     			xml_data_set3 +="<set value='"+data[i].day_order_Count+"' />";
	     			xml_data_set4 +="<set value='"+data[i].day_user_add_count+"' />";
		       	}
	     		xmldata += "</categories>";
	     		xml_data_set1 +="</dataset>";
	     		xml_data_set2 +="</dataset>";
	     		xml_data_set3 +="</dataset>";
	     		xml_data_set4 +="</dataset>";
	     		xmldata += xml_data_set1;   
	     		xmldata += xml_data_set2;
	     		xmldata += xml_data_set3;
	     		xmldata += xml_data_set4;
	     		xmldata += "</chart>";

	            chart.setDataXML(xmldata);
	            chart.render("chartdiv");
			}
	   });    

	}
   
	
	//正则表达式：只能输入整数
	function setOnlyInt(obj) {
		$(obj).css("ime-mode", "disabled");
		$(obj).attr("t_value", "");
		$(obj).attr("o_value", "");
		$(obj).bind("dragenter",function(){
			return false;
		});
		$(obj).keypress(function (){
			if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
		}).keyup(function (){
			if(!obj.value.match(/^\d*$/))obj.value=obj.t_value;else obj.t_value=obj.value;if(obj.value.match(/^(?:\d+(?:\d+)?)?$/))obj.o_value=obj.value;
		}).blur(function (){
			if(!obj.value.match(/^(?:\d+(?:\d+)?|\d*?)?$/))obj.value=obj.o_value;else{if(obj.value.match(/^\d+$/))obj.value=obj.value;if(obj.value.match(/^\.$/))obj.value=0;obj.o_value=obj.value;}
			if(obj.value.length == 0 || isNaN(obj.value) || obj.value == 0) obj.value = "";
		});
	}
   
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  	<html-el:form action="/admin/KonkaR3PdNameForCx">
  		<html-el:hidden property="method" value="list" />
  		<html-el:hidden property="mod_id" />
      	<html-el:hidden property="tj_type" styleId="tj_type" value="${af.map.tj_type}" />
      	<table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
	        <tr>
	          <td width="15"></td>
	          <td><strong class="fb">销售时间：</strong>
				      <html-el:text property="sell_date_start" styleId="sell_date_start"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				              至
				      <html-el:text property="sell_date_end" styleId="sell_date_end"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
				&nbsp;<strong class="fb">排名显示：</strong>
	          	<html-el:select property="show_top" styleId="show_top">
	          		<html-el:option value="">全部</html-el:option>
	          		<html-el:option value="5">Top5</html-el:option>
	          		<html-el:option value="10">Top10</html-el:option>
	          		<html-el:option value="20">Top20</html-el:option>
	          		<html-el:option value="30">Top30</html-el:option>
	          		<html-el:option value="40">Top40</html-el:option>
	          		<html-el:option value="50">Top50</html-el:option>
	          	</html-el:select>
	          	&nbsp;<strong class="fb">分公司　：</strong>
	            <c:set var="disabled" value="false" />
	            <c:if test="${af.map.dept_type eq 3}">
	            	<c:set var="disabled" value="true" />
	            </c:if>
	          	<html-el:select property="dept_id" styleId="dept_id" disabled="${disabled}">
	          		<html-el:option value="">-请选择-</html-el:option>
	          	</html-el:select>
	          	&nbsp;
	          	<html-el:select property="l4_dept_id" styleId="l4_dept_id">
	          		<html-el:option value="">-请选择-</html-el:option>
	          	</html-el:select>
	          	&nbsp;
	          	<html-el:select property="l5_dept_id" styleId="l5_dept_id">
	          		<html-el:option value="">-请选择-</html-el:option>
	          	</html-el:select>
			  </td> 
	        </tr>
	        <tr>
	        	<td width="15"></td>
	        	<td>
		        	<strong class="fb">产品型号：</strong>
		        	<html-el:text property="pd_name" size="15" maxlength="40" styleId="pd_name"  />
	        	</td>
	        </tr>
	        <tr>
	          <td width="15"></td>
	          <td><html-el:submit styleClass="but1" value="搜索" /> </td>
	        </tr>
      </table>
  	</html-el:form>
  </div>
  <div align="right">
		<a href="javascript:setTjType(1);" style="color:${af.map.tj_type eq 1 ? '#B11212':'#0072bc'};">按销售额排名</a>&nbsp;&nbsp;<a href="javascript:setTjType(2);" style="color:${af.map.tj_type eq 2 ? '#B11212':'#0072bc'};">按销售量排名</a>&nbsp;
  </div>
  <div class="rtabcont1">
  	<table width="100%">
  		<tr>
  			<td width="48%" valign="top" >
  				<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable6">
			      <tr class="tabtt6">
			        <td width="5%" nowrap="nowrap">序号</td>
			        <td nowrap="nowrap">产品型号</td>
			        <td width="20%" nowrap="nowrap">销售额</td>
			        <td width="20%" nowrap="nowrap">销售量</td>
			        <td width="20%" nowrap="nowrap">平均单价</td>
			      </tr>
			      <c:set var="total_money" value="0" />
	  			  <c:set var="total_num" value="0" />
			      <c:forEach items="${entityList}" var="cur" varStatus="vs">
			        <tr>
			          <td align="center">${vs.count}</td>
			          <td align="left" nowrap="nowrap">${cur[0]}</td>
			          <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur[1]}" type="currency" /></span></td>
			          <td align="right" nowrap="nowrap">${cur[2]}</td>
			          <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur[3]}" type="currency" /></span></td>
			        </tr>
			        <c:set var="total_money" value="${total_money+cur[1] }" />
	  			    <c:set var="total_num" value="${total_num+cur[2] }" />
			      </c:forEach>
			      <tr>
			      	<td align="right" colspan="2"><span style="font-size:14px;font-family:verdana;font-weight:700;">合计</span></td>
			      	<td align="right"><span class="kz-price"><fmt:formatNumber value="${total_money}" type="currency" /></span></td>
			      	<td align="right"><span style="font-size:14px;font-family:verdana;font-weight:700;">${total_num}</span></td>
			      	<td align="right"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
			      </tr>
			    </table>
  			</td>
  			<td width="2%">&nbsp;</td>
  			<td width="50%" valign="top">
				<table align="center" width="100%" cellpadding="0" cellspacing="0">
	       			<tr align="center">
	       				<td>
	       					<div id="chartdiv"></div>
	       				</td>
	       			</tr>
    			</table>
			</td>
  		</tr>
  	</table>

  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[    
$(document).ready(function(){
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
	
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId&isNotEpp=isNotEpp", "par_id", "0", false);
	$("#dept_id").change();
	
	if(${not empty entityList}){
		var flash = "MSBar3D.swf";
		
		var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "100%", "${char_x_heigth+330}", "0", "0");
		var dataStr = "KonkaR3PdNameForCx.do?method=chart";
		dataStr += "&tj_type=${af.map.tj_type}";
		dataStr += "&show_top=${af.map.show_top}";
		dataStr += "&sell_date_start=${af.map.sell_date_start}";
		dataStr += "&sell_date_end=${af.map.sell_date_end}";
		dataStr += "&dept_id=${af.map.dept_id}";
		dataStr += "&l4_dept_id=${af.map.l4_dept_id}";
		dataStr += "&l5_dept_id=${af.map.l5_dept_id}";
		dataStr += "&pd_name=${af.map.pd_name}";
		$.ajax({
		    type: "POST",
		    url: dataStr,
		    error: function(request, settings) {alert("数据加载请求失败"); },
		    success: function(data) {
		        if (data != ''){
		            chart.setDataXML(data);
		            chart.render("chartdiv");
		        }
		    }
		});
		
	}
});

function setTjType(type){
	document.getElementById("tj_type").value = type
	//$("#tj_type").val(type);
	document.forms[0].submit();
}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>



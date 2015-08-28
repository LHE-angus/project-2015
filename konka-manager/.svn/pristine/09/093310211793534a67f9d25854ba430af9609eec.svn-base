<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont" style="position:relative;overflow:hidden;">

<div class="rtabcont1">
  <html-el:form action="/admin/KonkaR3StoreHomePage">
    <html-el:hidden property="method" value="view" />
    <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
    <html-el:hidden property="province" styleId="province" />
    <html-el:hidden property="city" styleId="city" />
    <html-el:hidden property="country" styleId="country" />
    <html-el:hidden property="town" styleId="town" />
    <html-el:hidden property="add_date_start" styleId="add_date_start" />
    <html-el:hidden property="add_date_end" styleId="add_date_end" />
    <html-el:hidden property="sale_money_gt" styleId="sale_money_gt" />
    <html-el:hidden property="sale_money_lt" styleId="sale_money_lt" />
    <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
      <tr>
          <td width="10%"><strong class="fb">地市：</strong>
          </td>
          <td>
          	<c:forEach items="${list_city}" var="cur" varStatus="vs">
          		<a href="#" class="city_class" title="${cur.map.p_index}" ${cur.map.p_index eq af.map.city ? "style='background: #FF6600;color:#fff;padding:2px;'":""}>${cur.map.p_name}（${cur.map.num}）</a>&nbsp;
          		<c:if test="${(vs.count mod 5) eq 0}"><br/></c:if>
          	</c:forEach>
          </td>
      </tr>
      <tr>
          <td width="10%"><strong class="fb">区县：</strong>
          </td>
          <td>
          	<c:forEach items="${list_country}" var="cur" varStatus="vs">
          		<a href="#" class="country_class" title="${cur.map.p_index}" ${cur.map.p_index eq af.map.country ? "style='background: #FF6600;color:#fff;padding:2px;'":""}>${cur.map.p_name}（${cur.map.num}）</a>&nbsp;
          		<c:if test="${(vs.count mod 5) eq 0}"><br/></c:if>
          	</c:forEach>
          </td>
      </tr>
      <tr>
          <td width="10%"><strong class="fb">乡镇：</strong>
          </td>
          <td>
          	<c:forEach items="${list_town}" var="cur" varStatus="vs">
          		<a href="#" class="town_class" title="${cur.map.p_index}" ${cur.map.p_index eq af.map.town ? "style='background: #FF6600;color:#fff;padding:2px;'":""}>${cur.map.p_name}（${cur.map.num}）</a>&nbsp;
          		<c:if test="${(vs.count mod 5) eq 0}"><br/></c:if>
          	</c:forEach>
          </td>
      </tr>
    </table>
  </html-el:form>
  </div>
  <div class="rtabcont1">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
      		<tr class="tabtt1">
      			<td width="10%" nowrap="nowrap" align="center">序号</td>
		    	<td nowrap="nowrap" align="center">门店名称</td>
		    	<td width="20%" nowrap="nowrap" align="center">${(not empty af.map.sale_money_gt or not empty af.map.sale_money_lt)?"当年":""}零售额（元）</td>
      		</tr>
      		<c:forEach var="cur" items="${entityList}" varStatus="vs">
      			<tr>
      				<td align="center">${vs.count}</td>
      				<td align="left">${cur.store_name}</td>
      				<td align="right"><fmt:formatNumber value="${cur.map.sale_money}" pattern="#,##0.00"/></td>
      			</tr>
      		</c:forEach>
      </table>
  </div>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script src="${ctx}/commons/scripts/echarts/esl.js"></script>
<script type="text/javascript">//<![CDATA[
   $(document).ready(function() {
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
   }); 
   
   var f = document.forms[0];
   
   $(".city_class").click(function(){
	    $(".city_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		var str = $(this).attr("title");
		$("#city").val(str);
		$("#country").val("");
		$("#town").val("");
		f.submit();
	});
   
   $(".country_class").click(function(){
	    $(".country_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		var str = $(this).attr("title");
		$("#country").val(str);
		$("#town").val("");
		f.submit();
	});
   
   $(".town_class").click(function(){
	    $(".town_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		var str = $(this).attr("title");
		$("#town").val(str);
		f.submit();
	});
   
   
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/jquery-ui/themes/blitzer/jquery-ui.custom.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/scripts/tip/jquery.qtip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	select{font-family:Microsoft YAHEI;font-size:12px;}
	input{font-family:Microsoft YAHEI;font-size:12px;}
</style>
<title>销售库</title>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
</head>
<body style="font-family:Microsoft Yahei;">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
	<html-el:form action="/manager/JsSells">
      <html-el:hidden property="method" value="list_ml" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
	  <div class="rtabcont1">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
	      <tr>
	        <td height="36" align="left" style="padding-left:5px;">
	          &nbsp;<strong class="fb">产品型号：</strong>
              <html-el:text property="md_name_like" styleClass="webinput" styleId="md_name_like" size="20"/>
	          &nbsp;<strong class="fb">销售时间：</strong>
			  <input type="text" name="sell_date_ge" id="sell_date_ge" class="webinput" value="${af.map.sell_date_ge}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;" />
            至
              <input type="text" name="sell_date_le" id="sell_date_le" class="webinput" value="${af.map.sell_date_le}" readonly="readonly" onclick="new Calendar(1990, 2020, 0).show(this);" style="cursor:pointer;text-align:center;width:80px;"/>
	          &nbsp;
	          <input name="button" type="submit" class="bgSearch" id="button" value="搜 索" />
	        </td>
	      </tr>
	    </table>
	  </div>
	</html-el:form>
	<div class="rtabcont1">
	  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
	    <tr>
	      <th width="5%">序号</th>
	    
	      <th width="10%">产品型号</th>
	      <th width="10%">单价</th>
	      <th width="10%">成本</th>
	      <th width="10%">销售量</th>
	      <th width="10%">销售额</th>
	      <th width="10%">毛利</th>
	    </tr>
	    <c:forEach items="${entityList}" var="cur" varStatus="vs">
	      <tr>
	        <td align="center" bgcolor="#fff2dc">${vs.count}</td>
	      
	        <td align="left" nowrap="nowrap">${cur.md_name}</td>
	        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.sell_count eq '0'?'0' : (cur.sell_money/cur.sell_count)}" pattern="0.00" /></td>
	        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.sell_count eq '0'?'0' : (cur.total_cost/cur.sell_count)}" pattern="0.00" /></td>
	        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.sell_count}" pattern="0" /></td>   
	        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.sell_money}" pattern="0.00" /></td>
	        <td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.sell_money - cur.total_cost}" pattern="0.00" /></td>
	      </tr>
	    </c:forEach>
	  </table>
	</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/customer/script/rowEffect.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){

});	
//]]></script>
<jsp:include page="/customer/__analytics.jsp" />
</body>
</html>
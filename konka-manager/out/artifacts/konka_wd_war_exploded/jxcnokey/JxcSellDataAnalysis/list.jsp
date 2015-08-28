<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>销售数据统计分析</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：财务报表 &gt; 销售数据统计分析</div>
<html-el:form action="/JxcSellDataAnalysis.do">
<html-el:hidden property="keySeq"/>
<html-el:hidden property="method" value="list" />
<div class="rtabcont1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
  <tr>
    <td height="36" align="left" style="padding-left:5px;">
       产品类型：
        <html-el:select property="pd_type" styleId="pd_type" styleClass="bdfont" style="width:125px">
        	<html-el:option value="">=请选择产品类型=</html-el:option>
        	<c:forEach items="${basePdTypeList}" var="cur">
      	  	<html-el:option value="${cur.pd_type}">${cur.pd_name}</html-el:option>
      	  	</c:forEach>
      	  	<html-el:option value="0">其他</html-el:option>
        </html-el:select>&nbsp;&nbsp;
        年份：
        <html-el:select property="year" styleId="year" styleClass="bdfont" style="width:100px">
        	<html-el:option value="">=请选择年份=</html-el:option>
        	<html-el:option value="${years - 4}">${years - 4}年</html-el:option>
        	<html-el:option value="${years - 3}">${years - 3}年</html-el:option>
        	<html-el:option value="${years - 2}">${years - 2}年</html-el:option>
        	<html-el:option value="${years - 1}">${years - 1}年</html-el:option>
        	<html-el:option value="${years}">${years}年</html-el:option>
        </html-el:select>
        &nbsp;月份：
        <html-el:select property="month" styleId="month" styleClass="bdfont" style="width:100px">
        	<html-el:option value="">=请选择月份=</html-el:option>
        	<html-el:option value="0">1月</html-el:option>
        	<html-el:option value="1">2月</html-el:option>
        	<html-el:option value="2">3月</html-el:option>
        	<html-el:option value="3">4月</html-el:option>
        	<html-el:option value="4">5月</html-el:option>
        	<html-el:option value="5">6月</html-el:option>
        	<html-el:option value="6">7月</html-el:option>
        	<html-el:option value="7">8月</html-el:option>
        	<html-el:option value="8">9月</html-el:option>
        	<html-el:option value="9">10月</html-el:option>
        	<html-el:option value="10">11月</html-el:option>
        	<html-el:option value="11">12月</html-el:option>
        </html-el:select>
        &nbsp;&nbsp;<input name="button" type="submit" class="bgSearch" id="button" value="提 交 " />
      </td>
  </tr>
</table>
</div>
</html-el:form>
<div class="rtabcont1">
  <%@ include file="/commons/pages/messages.jsp" %>
</div>
<div class="rtabcont1">
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
  <tr>
    <th colspan="3" width="18%" align="center">基本信息</th>
    <th colspan="4" width="26%" align="center" >当月销售情况</th>
    <th colspan="8" width="56%" height="30" nowrap="nowrap" align="center">环比、同比销售分析统计</th>
  </tr>
  <tr>
  	<th rowspan="2" width="3%" align="center" height="60" nowrap="nowrap" bgcolor="#fff2dc">序号</th>
  	<th rowspan="2" width="7%" align="center" height="60" nowrap="nowrap" bgcolor="#fff2dc">产品类型</th>
  	<th rowspan="2" width="8%" align="center" height="60" nowrap="nowrap" bgcolor="#fff2dc">品牌</th>
  	<th rowspan="2" width="4%" align="center" height="60" nowrap="nowrap" bgcolor="#fff2dc">当月销量</th>
  	<th rowspan="2" width="4%" align="center" height="60" nowrap="nowrap" bgcolor="#fff2dc">当月销额</th>
  	<th rowspan="2" width="4%" align="center" height="60" nowrap="nowrap" bgcolor="#fff2dc">销量占比</th>
  	<th rowspan="2" width="4%" align="center" height="60" nowrap="nowrap" bgcolor="#fff2dc">销额占比</th>
  	<th colspan="4" width="28%" align="center" nowrap="nowrap">销售量</th>
  	<th colspan="4" width="28%" align="center" nowrap="nowrap">销售额</th>
  </tr>
  <tr>
  	<th width="7%" align="center" nowrap="nowrap">上月</th>
  	<th width="7%" align="center" nowrap="nowrap">环比</th>
  	<th width="7%" align="center" nowrap="nowrap">上年同期</th>
  	<th width="7%" align="center" nowrap="nowrap">同比</th>
  	<th width="7%" align="center" nowrap="nowrap">上月</th>
  	<th width="7%" align="center" nowrap="nowrap">环比</th>
  	<th width="7%" align="center" nowrap="nowrap">上年同期</th>
  	<th width="7%" align="center" nowrap="nowrap">同比</th>
  </tr>
  <c:if test="${search eq 'search'}">
  	<c:if test="${not empty entityList}">
	  <c:forEach items="${entityList}" var="cur" varStatus="vs">
	  	<tr>
	  	  <td height="30" align="center" nowrap="nowrap">${vs.count}</td>
	  	  <td height="30" align="center" nowrap="nowrap" class="pd_type_name">${cur.pd_type_name}</td>
	  	  <td height="30" align="center" nowrap="nowrap">${cur.brand_name}</td>
	  	  <td height="30" align="right" nowrap="nowrap">${cur.map.b_count}</td>
	  	  <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber type="Currency" value="${cur.map.b_m}" /></td>
	  	  <td height="30" align="right" nowrap="nowrap">
	  	  	<c:if test="${empty cur.map.count_rate}">--</c:if>
	  	  	<c:if test="${not empty cur.map.count_rate}">
		  	  	<fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.count_rate}" />
	  	  	</c:if>
	  	  </td>
	  	  <td height="30" align="right" nowrap="nowrap">
	  	  	<c:if test="${empty cur.map.money_rate}">--</c:if>
	  	  	<c:if test="${not empty cur.map.money_rate}">
		  	  <fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.money_rate}" />
	  	  	</c:if>
	  	  </td>
	  	  <td height="30" align="right" nowrap="nowrap">${cur.map.last_month_count}</td>
	  	  <td height="30" align="right" nowrap="nowrap">
	  	  	<c:if test="${empty cur.map.hb_count_rate}">--</c:if>
	  	  	<c:if test="${not empty cur.map.hb_count_rate}">
	  	  		<fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.hb_count_rate}" />
	  	  	</c:if>
	 	  </td>
	  	  <td height="30" align="right" nowrap="nowrap">${cur.map.last_year_count}</td>
	  	  <td height="30" align="right" nowrap="nowrap">
	  	  	<c:if test="${empty cur.map.tb_count_rate}">--</c:if>
	  	  	<c:if test="${not empty cur.map.tb_count_rate}">
	  	  		<fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.tb_count_rate}" />
	  	  	</c:if>
	  	  </td>
	  	  <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber type="Currency" value="${cur.map.last_month_money}" /></td>
	  	  <td height="30" align="right" nowrap="nowrap">
	  	  	<c:if test="${empty cur.map.hb_money_rate}">--</c:if>
	  	  	<c:if test="${not empty cur.map.hb_money_rate}">
	  	  		<fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.hb_money_rate}" />
	  	  	</c:if>
	  	  </td>
	  	  <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber type="Currency" value="${cur.map.last_year_money}" /></td>
	  	  <td height="30" align="right" nowrap="nowrap">
	  	  	<c:if test="${empty cur.map.tb_money_rate}">--</c:if>
	  	  	<c:if test="${not empty cur.map.tb_money_rate}">
	  	  		<fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.tb_money_rate}" />
	  	  	</c:if>
	  	  </td>
	  	</tr>
	  </c:forEach>
	</c:if>
	<c:if test="${empty entityList}">
		<tr>
			<td height="30" align="center" nowrap="nowrap" colspan="15"><font color="red">无销售数据</font></td>
		</tr>
	</c:if>
  </c:if>
</table>
<c:url value="" var="expPara">
	<c:param name="method" value="toExcelForList"/>
	<c:param name="type" value="xssjtjfxlist"/>
	<c:param name="keySeq" value="${af.map.keySeq}"/>
	<c:param name="pd_type" value="${af.map.pd_type}"/>
	<c:param name="year" value="${af.map.year}"/>
	<c:param name="month" value="${af.map.month}"/>
</c:url>

	<div class="rtabcont3">
	<html-el:form action="/JxcSellDataAnalysis.do${expPara}">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:5px;padding-top:10px">
		  <tr>
		    <td height="26" align="center">
		      <div class="center">
		        <input name="button" type="button" class="bgButtonExport" id="toExcel" value="导出" />
		        <input name="button" type="button" class="bgButtonPrint" id="button" value="打印" onclick="openChild()" />
		      </div>
		    </td>
		  </tr>
		</table>
	</html-el:form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	trMerge("pd_type_name");
	
	$("#year").attr("dataType","Require").attr("msg","请选择年份！");
	$("#month").attr("dataType","Require").attr("msg","请选择月份！");
	$("form").eq(0).submit(function(){
		var isSubmit = Validator.Validate(this, 1);
		if (isSubmit) {
			$('#loading').show();
			$(":submit").attr("disabled", "true");
		}
		return isSubmit;
	});	
});

function  trMerge(className){
   var that;
	$("." + className).each(function(){
		// alert("$(this).html(): " + $(this).html() + " $(that).html():" + $(that).html());
		if ((that != undefined) && ($(this).html() == $(that).html())) {
			rowspan = $(that).attr("rowSpan");
			if (rowspan == undefined) {
				$(that).attr("rowSpan", 1);   
				rowspan = $(that).attr("rowSpan");   
			}
			rowspan = Number(rowspan) + 1;
			$(that).attr("rowSpan", rowspan); // do your action for the colspan cell here
			$(this).remove(); // .remove(); // do your action for the old cell here
	    } else {
			that = this;
	    }
	});
}

var type = "xssjtjfxlist";
var keySeq = "${af.map.keySeq}";
var pd_type = "${af.map.pd_type}";
var year = "${af.map.year}";
var month = "${af.map.month}";
var param = "type=" + type + "&keySeq=" + keySeq + "&pd_type=" + pd_type + "&year="+ year + "&month=" + month + "&random=" + Math.random();
function openChild(){
	window.showModalDialog("?method=print&" + encodeURI(param), window, "dialogWidth:900px;status:no;dialogHeight:580px");
}

$("#toExcel").click(function(){
	this.form.submit();
	//window.open("?method=toExcelForList&" + param, "下载页面", "height=100,width=100");
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
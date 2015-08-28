<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>分销客户毛利分析</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：财务报表 &gt; 分销客户毛利分析</div>
<html-el:form action="/JxcCustomerAnalysis.do">
	<html-el:hidden property="keySeq"/>
	<html-el:hidden property="method" value="list" />
	<div class="rtabcont1">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
		  <tr>
		    <td height="36" align="left" style="padding-left:5px;">
		        客户姓名：
		        <html-el:text property="name_like" style="width:200px" maxlength="100"/>&nbsp;
		        销售日期：
		        <html-el:text property="add_date_st" styleId="add_date_st" value="${af.map.add_date_st}" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:left;width:85px;" styleClass="bd" />&nbsp;至
		        <html-el:text property="add_date_en" styleId="add_date_en" value="${af.map.add_date_en}" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:left;width:85px;" styleClass="bd" />&nbsp;
		        <input name="button" type="submit" class="bgSearch" id="button" value="提交" />
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
	    <th width="10%">客户名称</th>
	    <th width="5%">商品数量</th>
	    <th width="10%">销售成本</th>
	    <th width="10%">销售收入</th>
	    <th width="10%">毛利</th>
	    <th width="10%">毛利率</th>
	  </tr>
	  <c:if test="${search eq 'search'}">
	  	<c:if test="${not empty entityList}">
		  <c:forEach items="${entityList}" var="cur" varStatus="vs">
		  	<tr>
		  	  <td height="30" align="center" nowrap="nowrap" class="pd_type_name">${cur.map.name}</td>
		  	  <td height="30" align="center" nowrap="nowrap">${cur.map.xssl_cus}</td>
		  	  <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber type="Currency" value="${cur.map.xscb_cus}" /></td>
		  	  <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber type="Currency" value="${cur.map.xssr_cus}" /></td>
		  	  <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber type="Currency" value="${cur.map.ml}" /></td>
		  	  <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber type="percent" maxFractionDigits="2" value="${cur.map.rate}" /></td>
		  	</tr>
		  </c:forEach>
	  	</c:if>
	  	<c:if test="${empty entityList}">
	  		<tr>
	  			<td height="30" align="center" nowrap="nowrap" colspan="6"><font color="red">无销售数据</font></td>
	  		</tr>
	  	</c:if>
	  </c:if>
	</table>
	<c:url value="" var="expPara">
		<c:param name="method" value="toExcelForList"/>
		<c:param name="keySeq" value="${af.map.keySeq}"/>
		<c:param name="add_date_lt" value="${af.map.add_date_st}"/>
		<c:param name="add_date_gt" value="${af.map.add_date_en}"/>
		<c:param name="name_like" value="${af.map.name_like}" />
	</c:url>
	
	<div class="rtabcont3">
		<html-el:form action="/JxcCustomerAnalysis.do${expPara}">
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

	$("#add_date_st").attr("dataType","Require").attr("msg","请选择开始日期！");
	$("#add_date_en").attr("dataType","Require").attr("msg","请选择结束日期！");
	
	$("form").eq(0).submit(function(){
		var s = $("#add_date_st").val();
		var e = $("#add_date_en").val();
		if($.trim(s).length > 0 && $.trim(e).length > 0){
			if (s > e) {
				alert("开始时间必须小于结束时间！");
				return false;
			}
		}
		
		var isSubmit = Validator.Validate(this, 1);
		if (isSubmit) {
			$('#loading').show();
			$("input[type='text']").attr("readonly", "true");
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

var type = "khhzmlfxlist";
var keySeq = "${af.map.keySeq}";
var add_date_st = "${af.map.add_date_st}";
var add_date_en = "${af.map.add_date_en}";
var pd_type = "${af.map.pd_type}";
var name_like = "${af.map.name_like}";
var param = "keySeq=" + keySeq + "&add_date_lt=" + add_date_st + "&add_date_gt=" + add_date_en + "&name_like=" + name_like;
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
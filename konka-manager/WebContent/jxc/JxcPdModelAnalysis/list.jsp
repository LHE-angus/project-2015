<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
<title>分型号毛利分析</title>
</head>
<body>
<div class="oartop"><img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align:text-top;" /> 当前位置：财务报表 &gt; 分型号毛利分析</div>
<html-el:form action="/JxcPdModelAnalysis.do"> 
	<html-el:hidden property="keySeq"/>
	<html-el:hidden property="method" value="list" />
	<div class="rtabcont1">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableTop">
		  <tr>
		    <td height="36" align="left" style="padding-left:5px;">
		         销售日期：
		        <html-el:text property="sell_date_ge" styleId="sell_date_ge" value="${af.map.sell_date_ge}" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:left;width:85px;" styleClass="bd" />&nbsp;至
		        <html-el:text property="sell_date_le" styleId="sell_date_le" value="${af.map.sell_date_le}" readonly="true" onclick="new Calendar(2000, 2030, 0).show(this);" style="cursor:pointer;text-align:left;width:85px;" styleClass="bd" />&nbsp;
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
    <th width="15%">型号</th>
    <th width="15%">品类</th>
    <th width="15%">品牌</th>
    <th width="10%">商品数量</th>
    <th width="10%">销售成本</th>
    <th width="10%">销售收入</th>
    <th width="10%">毛利</th>
    <th width="10%">毛利率</th>
  </tr>
  		<c:forEach items="${entityList}" var="cur" varStatus="status">
  			 <tr>
			    <td height="30" align="center" nowrap="nowrap">${cur.map.pd_name}</td>
			    <td height="30" align="center" nowrap="nowrap" class="mergeCell">${cur.map.pd_type_name}</td>
			    <td height="30" align="center" nowrap="nowrap">${cur.map.brand_name}</td>
				<td height="30" align="center" nowrap="nowrap">${cur.map.count}</td>
			    <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber type="currency" value="${cur.map.cost_sales}" /></td>
			    <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber type="currency" value="${cur.map.sales_revenue}" /></td>
			    <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.maori}" type="currency" /></td>
			    <td height="30" align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.maori_rate}" pattern="0.00"/>%</td>
			  </tr>
  		</c:forEach>
	</table>
	<c:url value="" var="expPara">
	<c:param name="method" value="toExcelForList"/>
	<c:param name="type" value="xhhzlist"/>
	<c:param name="keySeq" value="${af.map.keySeq}"/>
	<c:param name="add_date_lt" value="${af.map.sell_date_ge}"/>
	<c:param name="add_date_gt" value="${af.map.sell_date_le}"/>
	</c:url>
	
	<div class="rtabcont3">
		<html-el:form action="/JxcPdModelAnalysis.do${expPara}">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-left:5px;padding-top:10px">
			  <tr>
			    <td height="26" align="center">
			      <div class="center">
			        <input name="button" type="button" class="bgButtonExport" id="toExcel" value="导出" />
			        <input name="button" type="button" class="bgButtonPrint" id="print" value="打印" onclick="openChild()" />
			      </div>
			    </td>
			  </tr>
			</table>
		</html-el:form>
	</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript"><!--//<![CDATA[
$(document).ready(function(){
	trMerge("pd_type_name");
	$("#sell_date_ge").attr("dataType","Require").attr("msg","请选择开始日期！");
	$("#sell_date_le").attr("dataType","Require").attr("msg","请选择结束日期！");
	
	$("form").eq(0).submit(function(){
		var s = $("#sell_date_ge").val();
		var e = $("#sell_date_le").val();
		if($.trim(s).length > 0 && $.trim(e).length > 0){
			if (s > e) {
				alert("开始时间必须小于结束时间！");
				return false;
			}
		}
		
		var isSubmit = Validator.Validate(this, 1);
		if (isSubmit) {
			//$('#loading').show();
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


	$(document).ready(function(){
		trMerge("mergeCell");
	});


//屏蔽右键
document.oncontextmenu=function(){
	return false;
};

var type = "fxhmlfxlist";
var keySeq = "${af.map.keySeq}";
var add_date_lt = "${af.map.sell_date_ge}";
var add_date_gt = "${af.map.sell_date_le}";
var param ="keySeq=" + keySeq + "&type=" + type + "&add_date_lt=" + add_date_lt + "&add_date_gt=" + add_date_gt + "&random=" + Math.random();

$("#toExcel").click(function(){
	this.form.submit();
	//window.open("${ctx}/client/manager/JxcSellBill.do?method=toExcel&" + param, "下载页面", "height=100,width=100");
});

function openChild(){
	window.showModalDialog("?method=print&" + encodeURI(param), window, "dialogWidth:900px;status:no;dialogHeight:580px"); 
}
//]]>--></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
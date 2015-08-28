<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${ctx}/m/jqm/jquery.mobile-1.3.1.min.css" />
<script src="${ctx}/m/jqm/jquery-1.9.1.min.js"></script>
<script src="${ctx}/m/jqm/jquery.mobile-1.3.1.min.js"></script>
</head>
<body>
<div data-role="page">
  <div data-role="header" data-position="inline" data-theme="b">
    <h1>销售数据上报</h1>
    <a href="${ctx}/mobile/admin/Frames.do" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-left jqm-home">返回</a>
	<a onclick="logout();" data-icon="forward" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">退出</a>
  </div>
  
<form id="iForm" name="iForm">
  <div data-role="fieldcontain">
      <div data-role="fieldcontain">
            <select name="select-choice-1" id="select-choice-1" data-native-menu="false" data-theme="b" data-form="ui-btn-up-a">
		        <option value="">销售门店</option>
		        <c:forEach var="cur" items="${baseList}" varStatus="vs">
		        	<option value="${cur.id}">${cur.customer_name}</option>
		        </c:forEach>
		      </select>
        </div>
        <div data-role="fieldcontain">
      <select name="select-choice-2" id="select-choice-2" data-native-menu="false" data-theme="b" data-form="ui-btn-up-a">
        <option value="">选择产品型号</option>
        <c:forEach var="cur" items="${kpmList}" varStatus="vs">
        	<option value="${cur.pd_id}">${cur.md_name}</option>
        </c:forEach>
      </select>
      </div>
        <div data-role="fieldcontain">
            <input name="pct_code" id="pct_code" placeholder="扫描商品条码 二维码" value="" type="text">
            <div>47寸彩电</div>
        </div>
        <div data-role="fieldcontain">
            <input name="mastercode" id="mastercode" placeholder="购买者身份证号" value="" type="text">
        </div>
        <div data-role="fieldcontain">
            <input name="realname" id="realname" placeholder="购买者姓名" value="" type="text">
        </div>
        <div data-role="fieldcontain">
            <input name="phonenum" id="phonenum" placeholder="购买者电话" value="" type="tel">
        </div>
        <div data-role="fieldcontain">
            <input name="addresss" id="addresss" placeholder="购买者详细地址" value="" type="text">
        </div>
        <div data-role="fieldcontain">
            <input name="report_date" id="report_date" placeholder="购买日期" value="" type="date">
        </div>
        <div data-role="fieldcontain">
            <input name="sales_price" id="sales_price" placeholder="成交价格" value="" type="number" data-form="ui-body-b" onkeyup="javascript:setOnlyDouble(this);" />
        </div>
        <div data-role="fieldcontain">
            <input name="sales_count" id="sales_count" placeholder="成交数量" value="" type="number" data-form="ui-body-a" onkeyup="javascript:setOnlyInter(this);" />
        </div>
        <div data-role="fieldcontain">
            <textarea name="" id="textarea1" placeholder="礼品等备注信息"></textarea>
        </div>
        <div data-role="fieldcontain">
            <input name="" id="textinput5" placeholder="购买者地址" value="" type="text">
        </div>
      <input type="hidden" id="method" name="method" />
      <div style="margin-top:10px;color:#888;">
      	注：价格单位：元。
      </div>
      
    </div>
</form>

<div data-role="footer" class="ui-bar" data-position="fixed" data-theme="b">
   	<div data-role="controlgroup"  data-type="horizontal" style="text-align:center;margin:0px;padding:0px;">
		<a onclick="refresh();" data-role="button" data-icon="refresh" data-theme="b" data-inline="true">重置</a>
		<a id="submitBtn" data-role="button" data-icon="plus" data-theme="b" data-inline="true">提交</a>
	</div>
</div>

<script type="text/javascript">//<![CDATA[
$(document).ready(function() {

$('#date').scroller({theme: "default",mode:"clickpick"});

var nowDate = new Date();
$('#date').val((nowDate.getMonth() + 1) + "/" + nowDate.getDate() + "/" +nowDate.getFullYear());
	
$("#submitBtn").click(function() {
	$("#method").val("save");
	var options = { 
			url:"SalesReports.do",
			type:"POST",
			success:function(msg){
				if(msg == "success"){
					location.href = "Frames.do";
				}else{
					alert(msg);
				}
			}
	    };
	$("#iForm").ajaxSubmit(options);
});
});
//]]></script>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
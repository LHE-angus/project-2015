<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<META http-equiv=Expires content=0>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Pragma content=no-cache>
<title>零售通-康佳电器</title>
<link rel="stylesheet" href="${ctx}/mobile/themes/konka.min.css" />
<link rel="stylesheet" href="${ctx}/m/jqm/jquery.mobile-1.3.1.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/mobile/admin/Frames/css/common.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/mobile/admin/Frames/css/head.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/mobile/admin/Frames/css/nmnetwork.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/mobile/admin/Frames/css/chanp.css" />
<link href="${ctx}/styles/mobiscroll-1.5.2.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/m/jqm/jquery-1.9.1.min.js"></script>
<script src="${ctx}/m/jqm/jquery.mobile-1.3.1.min.js"></script>
<script src="${ctx}/commons/scripts/jquery.form.js" ></script>
<script src="${ctx}/commons/scripts/mobiscroll-1.5.2.js" ></script>
</head>

<body>
<div class="theme-preview">
<div class="headtab3" >
  <table width="100%" class="tab1">
  <tr style="padding-top:10px">
    <td class="p10"><a href="${ctx}/m/admin/SalesReport.do"><img src="${ctx}/mobile/admin/Frames/images/g1.png" width="64" height="64" /></a></td>
    <td class="p10"><a href="${ctx}/m/admin/SalesReport.do"><img src="${ctx}/mobile/admin/Frames/images/g2.png" alt="" width="64" height="64" /></a></td>
    <td class="p10"><a href="${ctx}/m/admin/SalesReport.do"><img src="${ctx}/mobile/admin/Frames/images/g3.png" alt="" width="64" height="64" /></a></td>
  </tr>
  <tr>
    <td class="pb15"><a href="${ctx}/m/admin/SalesReport.do" style="color:#666666;">销量上报</a></td>
    <td class="pb15"><a href="${ctx}/m/admin/SalesReport.do" style="color:#666666;">门店库存</a></td>
    <td class="pb15"><a href="${ctx}/m/admin/SalesReports.do" style="color:#666666;">退货统计</a></td>
  </tr>
    <tr style="padding-top:10px">
    <td class="p10"><img src="${ctx}/mobile/admin/Frames/images/g4.png" width="64" height="64" /></td>
    <td class="p10"><img src="${ctx}/mobile/admin/Frames/images/g5.png" alt="" width="64" height="64" /></td>
    <td class="p10"><img src="${ctx}/mobile/admin/Frames/images/g6.png" alt="" width="64" height="64" /></td>
  </tr>
  <tr>
    <td class="pb15"><a href="${ctx}/m/admin/SalesReports.do" style="color:#666666;">样机管理</a></td>
    <td class="pb15"><a href="${ctx}/m/admin/SalesReports.do" style="color:#666666;">物料管理</a></td>
    <td class="pb15"><a href="${ctx}/m/admin/SalesReports.do" style="color:#666666;">竞品信息</a></td>
  </tr>
    <tr style="padding-top:10px">
    <td class="p10"><img src="${ctx}/mobile/admin/Frames/images/g7.png" width="64" height="64" /></td>
    <td class="p10"><img src="${ctx}/mobile/admin/Frames/images/g8.png" alt="" width="64" height="64" /></td>
    <td class="p10"><a href="${ctx}/m/admin/RestReport.do"><img src="${ctx}/mobile/admin/Frames/images/g9.png" width="64" height="64" /></a></td>
  </tr>
  <tr>
    <td class="pb15"><a href="${ctx}/m/admin/SalesReports.do" style="color:#666666;">公司新闻</a></td>
    <td class="pb15"><a href="${ctx}/m/admin/SalesReports.do" style="color:#666666;">上报历史</a></td>
    <td class="pb15"><a href="${ctx}/m/admin/RestReport.do" style="color:#666666;">请假申请</a></td>
  </tr>
    <tr style="padding-top:10px">
    <td class="p10"><img src="${ctx}/mobile/admin/Frames/images/g10.png" width="64" height="64" /></td>
    <td class="p10">&nbsp;</td>
    <td class="p10">&nbsp;</td>
  </tr>
  <tr>
    <td class="pb15"><a href="${ctx}/m/admin/SalesReports.do" style="color:#666666;">信息反馈</a></td>
    <td class="pb15">&nbsp;</td>
    <td class="pb15">&nbsp;</td>
  </tr>
</table>
</div>
</div>
<script type="text/javascript">
function setOnlyInter(obj) {
	var v = obj.value.replace(/[^\d]/gi,'');
	if(v == 0){
		obj.value = '0';
	}else{
		obj.value = v;
	}
}

function setOnlyDouble(obj) {
	var v = obj.value.replace(/[^\d+(\.\d+)?-]/gi,'');
	if(v == 0){
		obj.value = '0';
	}else{
		obj.value = v;
	}
}

function logout() {
	location.href="${ctx}/mobile/login.do?method=logout";
}


function selectItem(li) {
	findValue(li);
}

function localhere(){
	$.mobile.loadingMessageTextVisible = true;
	$.mobile.showPageLoadingMsg("a","定位中...");
}

function formatItem(row) {
	return row[0] + " (id: " + row[1] + ")";
}

function goPage(url,method,page,forward,pagelimit){
	if(page == 1 && forward == -1)
		alert("已到首页！");
	else if(page == pagelimit && forward == 1)
		alert("已到尾页！");
	else{
		url = url.split('?')[0];
		if(method == null)
			location.href = url + "?page=" + page + "&forward=" + forward;
		else
			location.href = url + "?method=" + method + "&page=" + page + "&forward=" + forward;
	}
}
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
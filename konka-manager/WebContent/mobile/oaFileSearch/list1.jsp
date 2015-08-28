<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
<title>康佳电器</title>
<link rel="stylesheet" type="text/css" href="${ctx}/mobile/css/common.css" />
<style type="text/css"></style>
</head>
<body>
<div id="fileContent">
  <div class="headtab31" ></div>
  <table width="100%" border="0" class="rtab1">
    <tr>
      <th width="80%" align="left" colspan="2" >标题</th>
      <th width="20%" style="background-image:none">操作</th>
    </tr>
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
       <tr style="cursor: pointer;" class="db_tr" onclick="goview('${cur.map.url}')">
        <td align="center">${vs.count}</td>
        <td align="left">${cur.eventiltle}<br />
          <span class="leixi">类型：${cur.eventType}</span><span class="leixi">发起人：${cur.fromPerson}</span> <span class="leixi">时间：
          <fmt:formatDate value="${cur.enterDate}" pattern="yyyy-MM-dd" />
          </span> <br /></td>
        <td align="center"><span class="xt_sp">${cur.eventDo}</span></td>
      </tr>
    </c:forEach>
    <c:forEach begin="${fn:length(entityList)}" step="1" end="4">
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </c:forEach>
  </table>
  <div class="xt_fenye"> <a onclick="goback(${af.map.currentPage},null,null);"><img src="${ctx}/mobile/images/xt_shagnyiye.jpg" width="66" height="24" /></a> <a onclick="goforward(${af.map.currentPage},null,null);"><img src="${ctx}/mobile/images/xt_xiayye.jpg" width="66" height="24" /></a> </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript">
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]);
    return null;
}

var recordNum = 0;
$(document).ready(function(){
	$.ajax({//ajax 取记录数：
		type: "POST",
		url: "OaFileSearch.do",
		data: "method=getCount1&username="+getQueryString("username")+"&userpass="+getQueryString("userpass"),
		dataType: "text",
		error: function(request, settings) {},
		success: function(Datas) {
			recordNum = Datas;
		}
	});

});

function goview(url){
	var href_value = "${ctx}/" + url;
	window.location.href = href_value ;
}

function goPage(method,page,forward,pagelimit){
	pagelimit = Math.ceil(recordNum / pagelimit);
	if(page == 1 && forward == -1){
		alert("已到首页！");
		return false;
	}
	else if(page == pagelimit && forward == 1){
		alert("已到尾页！");
		return false;
	}
	else if(pagelimit == 0){
		alert("无翻页信息！");
		return false;
	}
	else
		return true;
}

function goback(page,username,userpass) {
	if(goPage(null,page,-1,5)){
		window.location.href="OaFileSearch.do?"+"method=list1&username="+getQueryString("username")+"&userpass="+getQueryString("userpass")+"&page="+page+"&forward=-1";
	}
}

function goforward(page,username,userpass) {
	if(goPage(null,page,1,5)){
		window.location.href="OaFileSearch.do?"+"method=list1&username="+getQueryString("username")+"&userpass="+getQueryString("userpass")+"&page="+page+"&forward=1";
	}
}
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
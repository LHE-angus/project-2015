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
      <th width="75%" align="center" >订单</th>
      <th width="25%" align="center" >审核状态</th>
    </tr>
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr>
        <td align="center"><span class="leixi">客户名称：${cur.user_shop_name}</span>
          <br/><span class="leixi">下单日期：<fmt:formatDate value="${cur.order_date}" pattern="yyyy-MM-dd" />
          </span>
          <br/>
          <span class="leixi">订单数量：${cur.order_num}</span>
		  <br/><span class="leixi">订单金额：<fmt:formatNumber value="${cur.money}" type="currency" />
          元</span></td>
        <td align="left"><c:choose>
            <c:when test="${cur.audit_state eq 3}">审核通过</c:when>
            <c:otherwise>待审核</c:otherwise>
          </c:choose></td>
      </tr>
    </c:forEach>
    <c:forEach begin="${fn:length(entityList)}" end="4">
      <tr>
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
    if (r != null) return unescape(r[2]);
    return null;
}

var recordNum = 0;
$(document).ready(function(){
	$.ajax({//ajax 取记录数：
		type: "POST",
		url: "KonkaOrderAudit.do",
		data: "method=getCount&audit_state=3&user_id="+getQueryString("user_id")+"&username="+getQueryString("username")+"&userpass="+getQueryString("userpass"),
		dataType: "text",
		error: function(request, settings) {},
		success: function(Datas) {
			recordNum = Datas;
			
		}
	});

});


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
		window.location.href="KonkaOrderAudit.do?method=listaudit&user_id="+getQueryString("user_id")+"&username="+getQueryString("username")+"&userpass="+getQueryString("userpass")+"&page="+page+"&forward=-1";
	}
}

function goforward(page,username,userpass) {
	if(goPage(null,page,1,5)){
		window.location.href="KonkaOrderAudit.do?method=listaudit&user_id="+getQueryString("user_id")+"&username="+getQueryString("username")+"&userpass="+getQueryString("userpass")+"&page="+page+"&forward=1";
	}
}
</script>
</body>
</html>

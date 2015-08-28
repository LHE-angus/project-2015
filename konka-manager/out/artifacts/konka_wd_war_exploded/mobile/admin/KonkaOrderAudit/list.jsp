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
      <th width="80%" align="center" >订单</th>
      <th width="20%" style="background-image:none">操作</th>
    </tr>
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr style="cursor: pointer;" class="db_tr" onclick="goaudit('${cur.id}')">
        <td align="left">
          <span class="leixi">订单号：${cur.trade_index}</span>
          <br/>
          <span class="leixi">客户名称：${cur.user_shop_name}</span>
          <br/><span class="leixi">订单金额：<fmt:formatNumber value="${cur.money}" type="currency" />元
          </span> 
          <br/><span class="leixi">下单日期：<fmt:formatDate value="${cur.order_date}" pattern="yyyy-MM-dd" />
          </span> 
          <br/><span class="leixi">审核状态：
          <c:choose>
            <c:when test="${cur.audit_state eq 3}">审核通过</c:when>
            <c:otherwise>待审核</c:otherwise>
          </c:choose>
          </span></td>
        <td align="center"><a href="javascript:goaudit('${cur.id}')" data-role="button" data-icon="plus" data-theme="b" data-inline="true">审核</a></td>
      </tr>
    </c:forEach>
    <c:forEach begin="${fn:length(entityList)}" end="4">
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </c:forEach>
  </table>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaOrderAudit.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:20px 0px;">
        <tr>
          <td align="left">
          <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
                var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "list");
	            document.write(pager.toString());
            </script></td>
        </tr>
      </table>
    </form>
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
});
function goaudit(id){
	window.location.href= "${ctx}/KonkaOrderAudit.do?method=audit&id="+ id +"&user_id="+getQueryString("user_id")+"&username="+getQueryString("username")+"&userpass="+getQueryString("userpass");
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
		window.location.href="KonkaOrderAudit.do?method=list&user_id="+getQueryString("user_id")+"&username="+getQueryString("username")+"&userpass="+getQueryString("userpass")+"&page="+page+"&forward=-1";
	}
}
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>

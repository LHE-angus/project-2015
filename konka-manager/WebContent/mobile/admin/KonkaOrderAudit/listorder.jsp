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
<div style="line-height:20px;color:#F00;padding:3px;">注：以下是过去30天（${af.map.order_date_start}～${af.map.order_date_end}）的订单结果，更多订单请登录电脑端（qdgl.konka.com）查询。</div>
<div id="fileContent">
  <div class="headtab31" ></div>
  <table width="100%" border="0" class="rtab1">
    <tr>
      <th width="70%" align="center" >订单</th>
      <c:if test="${af.map.dept_type eq 1}"> 
        <!-- 系统管理员 -->
        <th width="15%" nowrap="nowrap" align="center">订单审核状态</th>
        <th width="15%" nowrap="nowrap" align="center">待审核角色</th>
      </c:if>
      <c:if test="${af.map.dept_type eq 2}"> 
        <!-- 非系统管理员 -->
        <th width="30%" nowrap="nowrap" align="center">订单审核状态</th>
      </c:if>
    </tr>
    <c:forEach items="${entityList}" var="cur" varStatus="vs">
      <tr onclick="location.href='${ctx}/KonkaOrderAudit.do?method=view&id=${cur.id}';">
        <td align="left">
          <span class="leixi">订单号：${cur.trade_index}</span>
          <br/>
          <span class="leixi">客户名称：${cur.user_shop_name}</span>
		  <br/><span class="leixi">下单日期：<fmt:formatDate value="${cur.order_date}" pattern="yyyy-MM-dd" />
          </span>
          <br/>
          <span class="leixi">订单数量：${cur.order_num}</span>
          <br/><span class="leixi">订单金额：<fmt:formatNumber value="${cur.money}" type="currency" />
          元</span></td>
        <c:if test="${af.map.dept_type eq 1}">
          <td align="center"><c:choose>
              <c:when test="${cur.audit_state eq 3}">审核完成</c:when>
              <c:otherwise>审核中</c:otherwise>
            </c:choose></td>
          <td align="center"><c:choose>
              <c:when test="${empty cur.map.next_audit_role_name}">无</c:when>
              <c:when test="${not empty cur.map.next_audit_role_name}">${cur.map.next_audit_role_name}</c:when>
            </c:choose></td>
        </c:if>
        <c:if test="${af.map.dept_type eq 2}">
          <td align="center"><c:choose>
              <c:when test="${cur.map.states eq 0}">待审核</c:when>
              <c:when test="${cur.map.states eq 1}">已审核</c:when>
            </c:choose></td>
        </c:if>
      </tr>
    </c:forEach>
    <c:if test="${af.map.dept_type eq 1}">
      <c:forEach begin="${fn:length(entityList)}" end="4">
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </c:if>
    <c:if test="${af.map.dept_type eq 2}">
      <c:forEach begin="${fn:length(entityList)}" end="4">
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </c:forEach>
    </c:if>
  </table>
  <form id="bottomPageForm" name="bottomPageForm" method="post" action="KonkaOrderAudit.do">
      <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin:20px 0px;">
        <tr>
          <td align="left">
          <script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script>
            <script type="text/javascript">
                var pager = new Pager(document.bottomPageForm, ${af.map.pager.recordCount}, ${af.map.pager.pageSize}, ${af.map.pager.currentPage});
	            pager.addHiddenInputs("method", "listorder");
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

function goback(page,username,userpass) {
	if(goPage(null,page,-1,5)){
		window.location.href="KonkaOrderAudit.do?method=listorder&user_id="+getQueryString("user_id")+"&username="+getQueryString("username")+"&userpass="+getQueryString("userpass")+"&page="+page+"&forward=-1";
	}
}

</script>
</body>
</html>

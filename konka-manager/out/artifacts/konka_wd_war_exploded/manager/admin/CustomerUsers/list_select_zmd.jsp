<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString} &gt; ${konkaR3Shop.customer_name} &gt; 用户管理</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <div class="rtabcont1">
  <div>
   客户编码：<c:out value="${konkaR3Shop.r3_code}" />
   客户名称：[${empty konkaCategory.c_name ? '无客户类型' : konkaCategory.c_name}]<c:out value="${konkaR3Shop.customer_name}" />
   <br /> <br />
   请选择一个用户作为“${konkaXxZmd.zmd_sn}”专卖店的管理员
   </div>
    <form id="listForm" name="listForm" method="post" action="CustomerUsers.do">
      <input type="hidden" name="method" id="method" value="delete" />
      <input type="hidden" name="mod_id" id="mod_id" value="${af.map.mod_id}" />
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
        <tbody>
          <tr class="tabtt1">
            <td width="5%" nowrap="nowrap" ><font class="blue">选择</font></td>
            <td nowrap="nowrap" ><font class="blue">登录名</font></td>
            <td width="10%" nowrap="nowrap" ><font class="blue">姓名</font></td>
            <td width="20%" nowrap="nowrap" align="center"><font class="blue">手机/电话</font></td>
            <td width="10%" nowrap="nowrap" align="center"><font class="blue">邮箱</font></td>
          </tr>
          <c:forEach var="cur" items="${entityList}" varStatus="vs">
            <tr>
              <td height="28"  align="center">
              <c:if test="${empty cur.map.konkaXxZmd}">
              <c:if test="${af.map.select_id eq cur.id}"><input type="checkbox" name="pk" checked="checked" id="${cur.id}" /></c:if>
              <c:if test="${af.map.select_id ne cur.id}"><input type="checkbox" name="pk" id="${cur.id}" /></c:if>
              </c:if>
              <c:if test="${not empty cur.map.konkaXxZmd}"><input type="checkbox" name="pk" id="${cur.id}" disabled="disabled" title="该用户已被分配为专卖店（专卖店编号：${cur.map.konkaXxZmd.zmd_sn}）管理员" /></c:if>
              </td>
              <td align="left"><c:out value="${cur.user_name}" /></td>
              <td align="left" valign="middle"><a style="text-decoration:underline;" href="CustomerUsers.do?method=view&user_id=${cur.id}&role_id=${cur.map.role_id}&mod_id=${af.map.mod_id}&tree_param=${tree_param}">${cur.real_name}</a></td>
              <td align="center"><c:out value="${not empty cur.link_phone ? cur.link_phone : (not empty cur.link_tel ? cur.link_tel : '')}" /></td>
              <td align="center">${cur.email}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </form>
	  <div><br /><input name="next" id="next" type="button" value=" 下一步 " />
	  <input type="button" name="Submit2" value=" 创建一个新的专卖店管理员用户 " onclick="location.href='CustomerUsers.do?method=add&mod_id=${af.map.mod_id}&cust_id=${af.map.cust_id}&request_from=zmd&zmd_id=${konkaXxZmd.zmd_id}';" />
	  </div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js">;</script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	var $last_cb;
	$('input[type="checkbox"]').click(function() {
		if (!!$last_cb) $last_cb.removeAttr('checked');
		$last_cb = $(this);
	});
	
	$("#next").click(function(){
		var $cbx = $('input[type="checkbox"]:checked');
		if ($cbx.length == 0) {
			alert("请选择一个用户");
			return;
		} else if ($cbx.length > 1) {
			alert("请选择一个用户，您只能选择一个用户");
			return;
		}
		
		location.href = "${ctx}/manager/admin/CustomerUsers.do?method=confirmZmdUser&zmd_id=${konkaXxZmd.zmd_id}&cust_id=${konkaR3Shop.id}&mod_id=${af.map.mod_id}&user_id=" + $cbx.attr('id');
	});
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>

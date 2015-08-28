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
  <html-el:form action="/admin/CustomerUsers.do">
      <html-el:hidden property="mod_id" />
      <html-el:hidden property="user_id" />
      <html-el:hidden property="cust_id" />
      <html-el:hidden property="zmd_id" />
      <html-el:hidden property="method" value="saveZmdUser" />
  <table width="100%" border="0" cellpadding="5" cellspacing="1" class="datagrid">
   <tr class="oartop">
     <td colspan="2">客户资料</td>
   </tr>
   <tr>
     <td width="15%" class="title_item" align="right">客户编码：</td>
     <td><c:out value="${konkaR3Shop.r3_code}" /></td>
   </tr>
   <tr>
     <td class="title_item" align="right">客户名称：</td>
     <td>[${empty konkaCategory.c_name ? '无客户类型' : konkaCategory.c_name}]<c:out value="${konkaR3Shop.customer_name}" /></td>
   </tr>
   <tr class="oartop">
     <td colspan="2">专卖店资料</td>
   </tr>
   <tr>
     <td class="title_item" align="right">专卖店编码：</td>
     <td>${konkaXxZmd.zmd_sn}</td>
   </tr>
   <tr>
     <td class="title_item" align="right">专卖店地址：</td>
     <td>${konkaXxZmd.addr}</td>
   </tr>
   <tr class="oartop">
     <td colspan="2">用户资料</td>
   </tr>
   <tr>
     <td class="title_item" align="right">用户登录名：</td>
     <td>${peProdUser.user_name}</td>
   </tr>
   <tr>
     <td class="title_item" align="right">用户姓名：</td>
     <td>${peProdUser.real_name}</td>
   </tr>
   <tr>
     <td>&nbsp;</td>
     <td><input name="next" id="next" type="submit" value=" 创建专卖店登录帐号 " /></td>
   </tr>
   </table>
  <div>
   </div>
   </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>

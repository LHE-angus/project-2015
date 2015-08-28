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
<style type="text/css">
<!--
.red{ color:#F00;}
.bla{ color:#000; font-size:12px; font-weight:bold;}
.note {color:#777;margin-left:10px;}
span.required {color:#FF0000;font-weight:normal;background-color:#F4FAFF;}
-->
</style>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>

  <div class="rtabcont2">
    <html-el:form action="/spgl/EcUser2.do" enctype="multipart/form-data">
      <html-el:hidden property="user_id" value="${af.map.user_id}" />
      <html-el:hidden property="method" value="save" />
      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
      <html-el:hidden property="tree_param" value="${tree_param}" />
      <html-el:hidden property="queryString" />
      <html-el:hidden property="returnUrl" />
      <c:if test="${pe_prod_user_session.user_type eq 0}"><c:set value="true" var="is_admin" /></c:if>
     <div align="left">
      <table width="100%" border="0" cellpadding="0" cellspacing="1" class="datagrid">
                  <tr class="oartop">
                  	<td colspan="4">组织信息</td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">用户类型：</td>
                    <td colspan="3">
                    <c:if test="${af.map.user_type eq 1}">工卡会员</c:if>
                    <c:if test="${af.map.user_type eq 2}">触网会员</c:if>
                    <c:if test="${af.map.user_type eq 3}">其他</c:if>
                    </td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap" >部门：</td>
                    <td colspan="3"><c:out value="${dept_name}"/>
                    </td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">客户名称：</td>
                    <td colspan="3"><c:out value="${real_name}" /> 关联码 <c:out value="${{af.map.link_code}" />
                      </td>
                  </tr>
                  <tr class="oartop">
                  	<td height="28" colspan="4">登录信息</td>
                  </tr>                 
                  <tr>
                    <td nowrap="nowrap">登录用户名：</td>
                    <td colspan="3"><c:out value="${af.map.user_name}" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">登录密码：</td>
                    <td colspan="3">****************</td>
                  </tr>
                  <tr class="oartop">
                  	<td colspan="4">在岗人员信息</td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">姓名：</td>
                    <td colspan="3"><c:out value="${af.map.real_name}" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">性别：</td>
                    <td><c:out value="${af.map.sex eq 0 ? '男' : (af.map.sex eq 1 ? '女' : '保密')}" /></td>
                    <td nowrap="nowrap">出生日期：</td>
                  	<td><fmt:formatDate value="${af.map.birthday}" pattern="yyyy-MM-dd" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">居住地：</td>
                    <td colspan="3"><c:out value="${p_index_name}" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">联系地址：</td>
                    <td colspan="3"><c:out value="${af.map.link_addr}" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">手机：</td>
                    <td width="35%"><c:out value="${af.map.link_phone}" /></td>
                    <td width="15%">电话：</td>
                    <td width="35%" colspan="3"><c:out value="${af.map.link_tel}" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">邮编：</td>
                    <td><c:out value="${af.map.link_post}" /></td>
                    <td nowrap="nowrap">邮箱：</td>
                    <td><c:out value="${af.map.email}" /></td>
                  </tr>
                  <tr>
                    <td nowrap="nowrap">QQ：</td>
                    <td><c:out value="${af.map.link_qq}" /></td>
                    <td nowrap="nowrap">MSN：</td>
                    <td><c:out value="${af.map.link_msn}" /></td>
                  </tr>
                  <tr class="oartop">
                  	<td height="28" colspan="4">其他信息</td>
                  </tr>  
                  <tr>
                    <td nowrap="nowrap">排序值：</td>
                    <td colspan="3"><c:out value="${af.map.order_value}" /></td>
                  </tr>
          <tr>
          <td colspan="6" height="40"  align="center">
                <input class="but5" type="button" name="Submit5" value="返回" onclick="history.back();return false;" />
          </td>
        </tr>
      </table>
    </div>
    </html-el:form>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
	$(document).ready(function(){
		
		
	});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
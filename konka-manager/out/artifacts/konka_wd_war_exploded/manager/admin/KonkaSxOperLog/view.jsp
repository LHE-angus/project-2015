<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
</head>
<body>
<div class="oarcont">
  <div class="oartop">
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" alt="" style="vertical-align:middle;" /></td>
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
 <div>
  <h3 align="center" ><strong class="fb">操作日志信息查看</strong></h3>
  </div>
   <div class="rtabcont2">
	  <table width="80%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">操作数据表：</td>
			<td width="88%" align="left"><c:out value="${af.map.link_tab}" /></td>
		</tr>
		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">操作数据表ID：</td>
			<td width="88%" align="left"><c:out value="${af.map.link_id}" /></td>
		</tr>
		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">操作时间：</td>
			<td width="88%" align="left">
			<fmt:formatDate value="${af.map.oper_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">操作人：</td>
			<td width="88%" align="left"><c:out value="${af.map.oper_uname}" /></td>
		</tr>
		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">操作人IP：</td>
			<td width="88%" align="left"><c:out value="${af.map.oper_ip}" /></td>
		</tr>
		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">操作方式：</td>
			<td width="88%" align="left">
			账户名：<c:out value="${af.map.oper_uname}"/>
			<!--<c:out value="${af.map.oper_type}" />-->
			</td>
		</tr>
		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">操作页面路径：</td>
			<td width="88%" align="left"><c:out value="${af.map.ppdm_name}" /></td>
		</tr>
		<tr>
			<td width="12%" nowrap="nowrap" class="title_item" align="right">操作内容：</td>
			<td width="88%" align="left"><c:out value="${af.map.oper_desc}" /></td>
		</tr>
</table>
 <div>
     <br />
        <label >
            <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
          </label>
  </div>
</div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
});
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>

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
  <h3 align="center" ><strong class="fb">样机及终端物料查看</strong></h3>
  </div>
    <div class="rtabcont2">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="list">
			<tr>
				<td width="12%" nowrap="nowrap" class="title_item" align="right">物料或样机名称：</td>
				<td width="88%" align="left"><c:out value="${af.map.thing_name}" /></td>
			</tr>
			<tr>
				<td width="12%" nowrap="nowrap" class="title_item" align="right">物料或样机类别：</td>
				<td width="88%" align="left"><c:out value="${af.map.thing_type}" /></td>
			</tr>
			<tr>
				<td width="12%" nowrap="nowrap" class="title_item" align="right">启用时间：</td>
				<td width="88%" align="left"><c:out value="${af.map.done_start}" /></td>
			</tr>
			<tr>
				<td width="12%" nowrap="nowrap" class="title_item" align="right">报废时间：</td>
				<td width="88%" align="left"><c:out value="${af.map.node_end}" /></td>
			</tr>
			 </table>
	    <div>
	     <br />
	        <label >
	            <input class="but5" type="button"  value="返回" onclick="history.back();return false;" />
	          </label>
	  </div>
  </div>
<div class="clear"></div>
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

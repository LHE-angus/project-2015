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
        <td>当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div align="center" class="rtabcont1">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
	    <tr class="tabtt1">
	    	<td width="5%" nowrap="nowrap" align="center">序号</td>
	    	<td nowrap="nowrap" align="center">分公司</td>
	    	<td width="25%" nowrap="nowrap" align="center">操作</td>
	    </tr>
	    <c:forEach var="cur" items="${entityList}" varStatus="vs">
	    	<tr>
	    		<td align="center" height="28">${vs.count}</td>
	    		<td align="left">${cur.dept_name}</td>
	    		<td align="center"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaDeptImp.do', 'list','mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">基础数据导入</span>
	    						   | <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaDeptImp.do', 'next','mod_id=${af.map.mod_id}&dept_id=${cur.dept_id}&'+$('#bottomPageForm').serialize())">初始化系统数据</span>
	    						   | <span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaDeptImp.do', 'list','mod_id=${af.map.mod_id}&'+$('#bottomPageForm').serialize())">历史数据</span>
	    		</td>
	    	</tr>
	    </c:forEach>
	  </table>
  </div>
</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/pager.js"></script>
<script type="text/javascript">//<![CDATA[
//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
  
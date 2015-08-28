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
  <div style="padding-left: 5px;padding-top: 3px;">
  	<input class="but5" type="button" value="返回" id ="ret_btn" onclick="history.back()" />
  </div>
  <div align="center" class="rtabcont1">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
	    <tr class="tabtt1">
	    	<td width="8%" nowrap="nowrap" align="center">操作步骤</td>
	    	<td nowrap="nowrap" align="center">操作项</td>
	    	<td width="10%" nowrap="nowrap" align="center">操作</td>
	    </tr>
	    <tr>
	    	<td align="center" height="28">1</td>
	    	<td align="left">初始化系统数据<span style="color: red;">${af.map.dept_name}</span>分公司部门信息</td>
	    	<td align="center"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaDeptImp.do', 'add','mod_id=${af.map.mod_id}&dept_id=${af.map.dept_id}&type_id=1&'+$('#bottomPageForm').serialize())">进入</span></td>
	    </tr>
	    <tr>
	    	<td align="center" height="28">2</td>
	    	<td align="left">初始化系统数据<span style="color: red;">${af.map.dept_name}</span>分公司角色信息</td>
	    	<td align="center"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaDeptImp.do', 'add','mod_id=${af.map.mod_id}&dept_id=${af.map.dept_id}&type_id=2&'+$('#bottomPageForm').serialize())">进入</span></td>
	    </tr>
	    <tr>
	    	<td align="center" height="28">3</td>
	    	<td align="left">初始化系统数据<span style="color: red;">${af.map.dept_name}</span>分公司门店信息</td>
	    	<td align="center"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaDeptImp.do', 'add','mod_id=${af.map.mod_id}&dept_id=${af.map.dept_id}&type_id=3&'+$('#bottomPageForm').serialize())">进入</span></td>
	    </tr>
	    <tr>
	    	<td align="center" height="28">4</td>
	    	<td align="left">初始化系统数据<span style="color: red;">${af.map.dept_name}</span>分公司用户信息</td>
	    	<td align="center"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaDeptImp.do', 'add','mod_id=${af.map.mod_id}&dept_id=${af.map.dept_id}&type_id=4&'+$('#bottomPageForm').serialize())">进入</span></td>
	    </tr>
	    <tr>
	    	<td align="center" height="28">5</td>
	    	<td align="left">初始化系统数据<span style="color: red;">${af.map.dept_name}</span>分公司直销员信息</td>
	    	<td align="center"><span style="cursor:pointer;" class="fblue" onclick="doNeedMethod(null, 'KonkaDeptImp.do', 'add','mod_id=${af.map.mod_id}&dept_id=${af.map.dept_id}&type_id=5&'+$('#bottomPageForm').serialize())">进入</span></td>
	    </tr>
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
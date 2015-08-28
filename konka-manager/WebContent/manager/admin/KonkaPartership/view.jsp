<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
select{font-family:Microsoft YAHEI;font-size:12px;}
input{font-family:Microsoft YAHEI;font-size:12px;}
label {cursor:pointer;}
</style>
</head>
<body style="font-family:Microsoft Yahei;">
<div style="width:100%">
	<div class="oartop">
	    <table width="400" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	      </tr>
	    </table>
  	</div>
  	<div class="rtabcont2">
  		<c:if test="${not empty no_user_tip}">
  		<div>
  		<table cellpadding="5" width="100%" cellspacing="8px" class="noteMacro" border="0" align="center" style="background-color:#FFFFBB;">
		  <tr>
		    <td width="40" align="center" valign="middle" height="25"><img src="${ctx}/commons/styles/message/images/warning.gif" width="16" height="16" style="vertical-align:middle;" alt="" border="0" /></td>
		    <td><p>业务员 ${customerUserInfo.real_name}，您好！该客户暂没有分配登录用户，无法登录康佳客户端，系统提醒您请尽快分配登录用户并告知。</p></td>
		  </tr>
		</table>
  		</div>
  		</c:if>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable3">
		    	<tr>
		            <th width="15%"  height="45" style="font-size:15px;font-weight:bold;font-family:Microsoft YAHEI,'黑体','宋体';color: red;"><span>合作伙伴关系</span></th>
					<th colspan="3" >&nbsp;</th>
		        </tr>
		    </table>
		    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="rtable3">
				 <tr>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>售达方编码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td class="title_item" align="right" nowrap="nowrap"><font color="red">* </font>售达方名称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td class="title_item" align="left" nowrap="nowrap">&nbsp;</td>
					<td class="title_item" align="left" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">* </font>送达方编码&nbsp;</td>
					<td class="title_item" align="left" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">* </font>送达方名称&nbsp;</td>
				 </tr>
				 <tr>
					<td class="title_item" align="right" nowrap="nowrap">
						${af.map.shoudf_id}
					</td>
					<td class="title_item" align="right" nowrap="nowrap">
						${af.map.shoudf_name}
					</td>
					<td class="title_item" align="left" nowrap="nowrap">&nbsp;</td>
					<td class="title_item" align="left" nowrap="nowrap">
						${af.map.songdf_id}
					</td>
					<td class="title_item" align="left" nowrap="nowrap">
						${af.map.songdf_name}
					</td>
				</tr>
		        <tr>
		          <td colspan="3" align="center">
		          
		            <input class="bgButtonBack" type="reset" name="reset" value="返回" id="btn_back" onclick="history.back();return false;"/>
		            <br/>
		            <div style="height: 50px">&nbsp;</div>
		          </td>
		        </tr>
		    </table>
  </div>
</div>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
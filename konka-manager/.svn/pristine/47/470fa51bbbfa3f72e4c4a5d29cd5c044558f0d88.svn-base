<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>R/3客户类型</title>

<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oartop">
		<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置&gt;&gt;R/3客户类型(可关联客户基础数据)
	</div>
	<div>
		<div class="rtabcont1">
			<html-el:form action="/R3InterfaceTest.do" >
			<html-el:hidden property="method" value="list3" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="tableTop">
<!-- 				<tr> -->
<!-- 				    <td> -->
<!-- 				   		<input name="button" type="submit" class="bgSearch" id="s_button" value="搜 索"  /> -->
<!-- 				    </td> -->
<!-- 				</tr> -->
			</table>
			
			</html-el:form>
		</div>
		<div class="rtabcont1">
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="tableClass">
				<tr>
					<th width="5%" nowrap="nowrap">序号</th>
					<th width="5%" nowrap="nowrap">客户端</th>
					<th width="8%" nowrap="nowrap">客户分类ID</th>
					<th width="8%" nowrap="nowrap">客户描述</th>
					<th width="8%" nowrap="nowrap">语言KEY</th>
				</tr>
				  <c:forEach var="cur" items="${entityList}" varStatus="vs" >
				    <tr>
				      <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
				      <td align="center" nowrap="nowrap" >${cur.MANDT}</td>
				      <td align="center" nowrap="nowrap" >${cur.KUKLA}</td>
				      <td align="center" nowrap="nowrap" >${cur.VTEXT}</td>
				      <td align="center" nowrap="nowrap" >${cur.SPRAS}</td>
				    </tr>
				  </c:forEach>
			</table>
		</div>

	<div class="rtabcont3">
	<span></span>

	</div>
</div>
<script type="text/javascript" src="../commons/scripts/jquery.js"></script>
<script type="text/javascript" >
//<![CDATA[
	$(document).ready(function(){
// 	$("#s_button").click(function(){
// 		this.form.submit();
// 	});
	}) ;
//]]>
</script>	

</body>
</html>
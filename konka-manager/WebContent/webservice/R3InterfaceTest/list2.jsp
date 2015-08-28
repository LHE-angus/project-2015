<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>R/3客户(与送达方相关)</title>

<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oartop">
		<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置&gt;&gt;R/3客户(与送达方相关)
	</div>
	<div>
		<div class="rtabcont1">
			<html-el:form action="/R3InterfaceTest.do" >
			<html-el:hidden property="method" value="list2" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="tableTop">
				<tr>
				    <td>
						<label>销售组织</label>
						<html-el:text  property="vkorg" styleId="txt_vkorg"  />
						<label>分销渠道</label>
						<html-el:text  property="vtweg" styleId="txt_vtweg" value="10"/>
						<label>产品组</label>
						<html-el:text  property="spart" styleId="txt_spart" value="10"/>
						<label>客户编码</label>
						<html-el:text  property="kunnr" styleId="txt_kunnr"   />
				   		<input name="button" type="submit" class="bgSearch" id="s_button" value="搜 索"  />
				    </td>
				</tr>
			</table>
			</html-el:form>
		</div>
		<div class="rtabcont1">
			<table width="100%" border="0" cellpadding="0" cellspacing="1"
				class="tableClass">
				<tr>
					<th width="5%" nowrap="nowrap">序号</th>
					<th width="5%" nowrap="nowrap">客户端</th>
					<th width="8%" nowrap="nowrap">客户编码</th>
					<th width="8%" nowrap="nowrap">销售组织</th>
					<th width="8%" nowrap="nowrap">销渠道</th>
					<th width="8%" nowrap="nowrap">产品组</th>
					<th width="8%" nowrap="nowrap">合作伙伴功能</th>
					<th width="8%" nowrap="nowrap">合作伙伴计数器</th>
					<th width="8%" nowrap="nowrap">业务伙伴客户号</th>
					<th width="8%" nowrap="nowrap">供应商帐号</th>
					<th width="8%" nowrap="nowrap">人员编号</th>
					<th width="8%" nowrap="nowrap">联系人号码</th>
					<th width="8%" nowrap="nowrap">客户描述(业务合作伙伴)</th>
					<th width="8%" nowrap="nowrap">缺省合作伙伴</th>
				</tr>
				  <c:forEach var="cur" items="${entityList}" varStatus="vs" >
				    <tr>
				      <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
				      <td align="center" nowrap="nowrap" >${cur.MANDT}</td>
				      <td align="center" nowrap="nowrap" >${cur.KUNNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.VKORG}</td>
				      <td align="center" nowrap="nowrap" >${cur.VTWEG}</td>
				      <td align="center" nowrap="nowrap" >${cur.SPART}</td>
				      <td align="center" nowrap="nowrap" >${cur.PARVW}</td>
				      <td align="center" nowrap="nowrap" >${cur.PARZA}</td>
				      <td align="center" nowrap="nowrap" >${cur.KUNN2}</td>
				      <td align="center" nowrap="nowrap" >${cur.LIFNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.PERNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.PARNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.KNREF}</td>
				      <td align="center" nowrap="nowrap" >${cur.DEFPA}</td>
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
	$("#s_button").click(function(){
		//下面这种写法也ok
// 		var txt_vkorg = document.getElementById("txt_vkorg");
// 		var txt_vtweg = document.getElementById("txt_vtweg");
// 		var txt_spart =  document.getElementById("txt_spart");
// 		if(txt_vkorg.value==""){
// 			alert("请指定销售组织!");
// 			return false;
// 		}
// 		if(txt_vtweg.value==""){
// 			alert("请指定分销渠道!");
// 			return false ;
// 		}
// 		if(txt_spart.value==""){
// 			alert("请指定产品组!");
// 			return false ;
// 		}
		
		var txt_vkorg = $("#txt_vkorg").val();
		var txt_vtweg = $("#txt_vtweg").val();
		var txt_spart = $("#txt_spart").val();
		
		if(txt_vkorg==""){
			alert("请指定销售组织!");
			return false;
		}
		if(txt_vtweg==""){
			alert("请指定分销渠道!");
			return false ;
		}
		if(txt_spart==""){
			alert("请指定产品组!");
			return false ;
		}	
		
		return true ;
	});
	}) ;
//]]>
</script>	

</body>
</html>
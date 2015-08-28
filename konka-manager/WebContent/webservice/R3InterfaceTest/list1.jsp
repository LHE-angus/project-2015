<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>R/3客户(基础数据)查询接口</title>
<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oartop">
		<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置&gt;&gt;R/3客户(基础数据)查询接口
	</div>
	<div>
		<div class="rtabcont1">
			<html-el:form action="/R3InterfaceTest.do" >
			<html-el:hidden property="method" value="list1" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="tableTop">
				<tr>
				    <td>
						<label>销售组织代码</label>
						<html-el:text  property="bukrs" styleId="txt_bukrs" />
				    	<label>客户创建日期</label>
						<html-el:text property="in_date" styleId="txt_indate"  readonly="readonly" styleClass="webinput"  onclick="new Calendar(1990, 2021, 0).show(this);" />
						<label>客户编码(可选)</label>
						<html-el:text  property="kunnr" styleId="txt_kunnr" value=""/>
				   		<input name="button" type="submit" class="bgSearch" id="s_button" value="搜 索" />
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
					<th width="8%" nowrap="nowrap">分类栏目(F001)</th>
					<th width="8%" nowrap="nowrap">客户类别ID</th>
					<th width="8%" nowrap="nowrap">分销售组织代码</th>
					<th width="8%" nowrap="nowrap">创建时间</th>
					<th width="8%" nowrap="nowrap">国家代码</th>
					<th width="8%" nowrap="nowrap">名称1</th>
					<th width="8%" nowrap="nowrap">名称2</th>
					<th width="8%" nowrap="nowrap">城市</th>
					<th width="8%" nowrap="nowrap">邮政编码</th>
					<th width="8%" nowrap="nowrap">地址(CODE)</th>
					<th width="8%" nowrap="nowrap">地址</th>
					<th width="8%" nowrap="nowrap">标准公司号</th>
					<th width="8%" nowrap="nowrap">财务帐号</th>
					<th width="8%" nowrap="nowrap">人员编号</th>
				</tr>
				  <c:forEach var="cur" items="${entityList}" varStatus="vs" >
				    <tr>
				      <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
				      <td align="center" nowrap="nowrap" >${cur.MANDT}</td>
				      <td align="center" nowrap="nowrap" >${cur.KUNNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.KTOKD}</td>
				      <td align="center" nowrap="nowrap" >${cur.KUKLA}</td>
				       <td align="center" nowrap="nowrap" >${cur.knb1.BUKRS}</td>
				      <td align="center" nowrap="nowrap" >${cur.ERDAT}</td>
				      <td align="center" nowrap="nowrap" >${cur.LAND1}</td>
				      <td align="center" nowrap="nowrap" >${cur.NAME1}</td>
				      <td align="center" nowrap="nowrap" >${cur.NAME2}</td>
				      <td align="center" nowrap="nowrap" >${cur.ORT01}</td>
				      <td align="center" nowrap="nowrap" >${cur.PSTLZ}</td>
				      <td align="center" nowrap="nowrap" >${cur.ADRNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.STREET}</td>
				      <td align="center" nowrap="nowrap" >${cur.BBBNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.FISKN}</td>
				     
				      <td align="center" nowrap="nowrap" >${cur.knb1.PERNR}</td>
				    </tr>
				  </c:forEach>
			</table>
		</div>

	<div class="rtabcont3">
	<span></span>

	</div>
</div>
	
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="../commons/scripts/jquery.js"></script>
<script type="text/javascript" >
 	$(document).ready(function(){
 		
		var date = new Date();
		var year = date.getFullYear();
		//从上2个月开始查起
		var month =(date.getMonth()-1)<10?'0'+(date.getMonth()-1):(date.getMonth()-1);
		var day = date.getDay()<10?'0'+date.getDay():date.getDay();
		//$("#txt_indate").attr("value",(year+"-"+month+"-"+day));
		
		$("#s_button").click(function(){
			//此两项不能为空
			var txt_in_date = $("#txt_indate").val();
			var txt_bukrs = $("#txt_bukrs").val();
			var txt_kunnr = $("#txt_kunnr").val();
			
			if(txt_bukrs==""){
				alert("请指定分销售组织代码!");
				return false ;
			}
 			
			if((txt_in_date=="")&&(txt_kunnr=="")){
				alert("创建日期和客户编码不能同时为空!");
				return false;
			}
			
			return true ;
		});
 	}) ;
	
</script>
	
</body>
</html>
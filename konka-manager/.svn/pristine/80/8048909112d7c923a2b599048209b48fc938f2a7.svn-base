<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>产品基础信息查询</title>

<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oartop">
		<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置&gt;&gt;R/3产品基础信息接口查询
	</div>
	<div>
		<div class="rtabcont1">
			<html-el:form action="/R3InterfaceTest.do" >
			<html-el:hidden property="method" value="list4" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="tableTop">
				<tr>
					<td>
					创建日期:
					<html-el:text property="erdat" styleId="erdat"  readonly="readonly" styleClass="webinput"  onclick="new Calendar(2000, 2021, 0).show(this);" />
					</td>
				    <td>
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
					<th width="8%" nowrap="nowrap">物料号</th>
					<th width="8%" nowrap="nowrap">物料描述1</th>
					<th width="8%" nowrap="nowrap">物料描述2</th>
					
					
					<th width="8%" nowrap="nowrap">维护状态</th>
					<th width="8%" nowrap="nowrap">产品组</th>
					<th width="8%" nowrap="nowrap">物料类型</th>
					<th width="8%" nowrap="nowrap">物料组</th>
					<th width="8%" nowrap="nowrap">基本单位</th>
					
					<th width="8%" nowrap="nowrap">尺寸</th>
					<th width="8%" nowrap="nowrap">长</th>
					<th width="8%" nowrap="nowrap">宽</th>
					<th width="8%" nowrap="nowrap">高</th>
					
					<th width="8%" nowrap="nowrap">创建日期</th>
					<th width="8%" nowrap="nowrap">创建人</th>
					<th width="8%" nowrap="nowrap">最后修改日期</th>
					<th width="8%" nowrap="nowrap">最后修改人</th>
				</tr>
				  <c:forEach var="cur" items="${entityList}" varStatus="vs" >
				    <tr>
				      <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
				      <td align="center" nowrap="nowrap" >${cur.MANDT}</td>
				      <td align="center" nowrap="nowrap" >${cur.MATNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.makt.MAKTX}</td>
				      <td align="center" nowrap="nowrap" >${cur.makt.MAKTG}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.PSTAT}</td>
				      <td align="center" nowrap="nowrap" >${cur.SPART}</td>
				      <td align="center" nowrap="nowrap" >${cur.MTART}</td>
				      <td align="center" nowrap="nowrap" >${cur.MATKL}</td>
				      <td align="center" nowrap="nowrap" >${cur.MEINS}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.LAENG}</td>
				      <td align="center" nowrap="nowrap" >${cur.GROES}</td>
				      <td align="center" nowrap="nowrap" >${cur.BREIT}</td>
				      <td align="center" nowrap="nowrap" >${cur.HOEHE}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.ERSDA}</td>
				      <td align="center" nowrap="nowrap" >${cur.ERNAM}</td>
				      <td align="center" nowrap="nowrap" >${cur.LAEDA}</td>
				      <td align="center" nowrap="nowrap" >${cur.AENAM}</td>
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
//<![CDATA[
	$(document).ready(function(){
	$("#s_button").click(function(){
		if($("#erdat").val()==null ||$("#erdat").val()=="" ){
			alert("创建日期不能为空!");
			return false;
		}
		
		this.form.submit();
	});
	}) ;
//]]>
</script>	

</body>
</html>
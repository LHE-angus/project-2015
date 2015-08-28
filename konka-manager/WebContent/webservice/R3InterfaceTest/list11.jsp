<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>分公司库存(精确到机型仓位)查询</title>

<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oartop">
		<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置&gt;&gt;分公司库存(精确到机型仓位)查询
	</div>
	<div>
		<div class="rtabcont1">
			<html-el:form action="/R3InterfaceTest.do" >
			<html-el:hidden property="method" value="list11" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					工厂(必填):
					<html-el:text property="zwerks" styleId="zwerks"  styleClass="webinput" />
					</td>
					<td>
					库存地点(库位)(必填):
					<html-el:text property="zlgort" styleId="zlgort"  styleClass="webinput" />
					</td>
					<td>
					仓位(可选):
					<html-el:text property="zlgpla" styleId="zlgpla"  styleClass="webinput" />
					</td>
					<td>
					机型(可选):
					<html-el:text property="zmatnr" styleId="zmatnr"  styleClass="webinput" />
					</td>
				    <td colspan="3">
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
					<th width="8%" nowrap="nowrap">工厂</th>
					<th width="8%" nowrap="nowrap">WhN</th>
					<th width="8%" nowrap="nowrap">类</th>
					
					<th width="8%" nowrap="nowrap">库位</th>
					<th width="8%" nowrap="nowrap">仓位</th>
					<th width="8%" nowrap="nowrap">仓储类型名</th>
					<th width="8%" nowrap="nowrap">名称</th>
					<th width="8%" nowrap="nowrap">物料(机型)</th>
					<th width="8%" nowrap="nowrap">可用库存</th>
					<th width="8%" nowrap="nowrap">交货日期</th>
					<th width="8%" nowrap="nowrap">物料机型(描述)</th>
					<th width="8%" nowrap="nowrap">移动价格</th>
				</tr>
				  <c:forEach var="cur" items="${entityList}" varStatus="vs" >
				    <tr>
				      <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
				      <td align="center" nowrap="nowrap" >${cur.WERKS}</td>
				      <td align="center" nowrap="nowrap" >${cur.LGNUM}</td>
				      <td align="center" nowrap="nowrap" >${cur.LGTYP}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.LGORT}</td>
				      <td align="center" nowrap="nowrap" >${cur.LGPLA}</td>
				      <td align="center" nowrap="nowrap" >${cur.LGTYP}</td>
				      <td align="center" nowrap="nowrap" >${cur.NAME1}</td>
				      <td align="center" nowrap="nowrap" >${cur.MATNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.MENGE}</td>
				      <td align="center" nowrap="nowrap" >${cur.EDATU}</td>
				      <td align="center" nowrap="nowrap" >${cur.MAKTX}</td>
				      <td align="center" nowrap="nowrap" >${cur.VERPR}</td>
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
	$("#s_button").click(function(){
		if($("#zwerks").val()==null || $("#zwerks").val()==""){
			alert("工厂不能为空");
			return false ;
		}
		if($("#zlgort").val()==null || $("#zlgort").val()==""){
			alert("库位不能为空");
			return false ;
		}
		return true;
		
	});
	}) ;
</script>	

</body>
</html>
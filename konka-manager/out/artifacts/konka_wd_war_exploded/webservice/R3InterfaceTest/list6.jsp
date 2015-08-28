<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>客户应收回款</title>

<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oartop">
		<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置&gt;&gt;客户应收回款
	</div>
	<div>
		<div class="rtabcont1">
			<html-el:form action="/R3InterfaceTest.do" >
			<html-el:hidden property="method" value="list6" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="tableTop">
				<tr>
					<td>
					年份:
					<html-el:text property="v_gjahr" styleId="v_gjahr"  styleClass="webinput" />
					</td>
					
					<td>
					月份:
					<html-el:text property="v_monat" styleId="v_monat"  styleClass="webinput" />
					</td>
					
					<td>
					产品组:
					<html-el:text property="v_spart" styleId="v_spart"  styleClass="webinput" />
					</td>
					
					<td>
					销售组织代码:
					<html-el:text property="v_vkorg" styleId="v_vkorg"  styleClass="webinput" />
					</td>
					
					<td>
					客户编码:
					<html-el:text property="kunnr" styleId="kunnr"  styleClass="webinput" />
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
					
					<th width="5%" nowrap="nowrap">客户编码</th>
					<th width="5%" nowrap="nowrap">销售组织</th>
					<th width="8%" nowrap="nowrap">客户分类</th>
					<th width="8%" nowrap="nowrap">销售部门 </th>
					<th width="8%" nowrap="nowrap">会计年度(年份)</th>
					<th width="8%" nowrap="nowrap">会计期间(月份)</th>
					
					
					<th width="8%" nowrap="nowrap">名称</th>
					<th width="8%" nowrap="nowrap">公司代码</th>
					<th width="8%" nowrap="nowrap">描述</th>
<!-- 					<th width="8%" nowrap="nowrap">ZSYZT</th> -->
					<th width="8%" nowrap="nowrap">控制范围名称</th>
					
					<th width="8%" nowrap="nowrap">期初余额</th>
					<th width="8%" nowrap="nowrap">应收</th>
					<th width="8%" nowrap="nowrap">回款</th>
					<th width="8%" nowrap="nowrap">期末余额</th>
					
				</tr>
				  <c:forEach var="cur" items="${entityList}" varStatus="vs" >
				    <tr>
				      <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.KUNNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.VKORG}</td>
				      <td align="center" nowrap="nowrap" >${cur.KUKLA}</td>
				      <td align="center" nowrap="nowrap" >${cur.VKBUR}</td>
				      <td align="center" nowrap="nowrap" >${cur.GJAHR}</td>
				      <td align="center" nowrap="nowrap" >${cur.MONAT}</td>
				      
				      
				      <td align="center" nowrap="nowrap" >${cur.NAME1}</td>
				      <td align="center" nowrap="nowrap" >${cur.BUTXT}</td>
				      <td align="center" nowrap="nowrap" >${cur.VTEXT}</td>
				      <td align="center" nowrap="nowrap" >${cur.BEZEI}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.ZQC}</td>
				      <td align="center" nowrap="nowrap" >${cur.ZJF}</td>
				      <td align="center" nowrap="nowrap" >${cur.ZDF}</td>
				      <td align="center" nowrap="nowrap" >${cur.ZYE}</td>
				    
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
		
		//前三者不能为空
		if($("#v_gjahr").val()=="" ){
			alert("年份不能为空");
			return false ;
		}
		if($("#v_monat").val()==null ||$("#v_monat").val()=="" ){
			alert("月份不能为空");
			return false ;
		}
		if($("#v_spart").val()==null ||$("#v_spart").val()=="" ){
			alert("产品组不能为空");
			return false ;
		}
		
		//后两者不能为空
		if(($("#kunnr").val()==null ||$("#kunnr").val()=="" )&&($("#v_vkorg").val()==null || $("#v_vkorg").val()=="")){
			alert("客户编码 和 分公司编码 不能为空");
			return false ;
		}
		
		return true;
		
	});
	}) ;
</script>	

</body>
</html>
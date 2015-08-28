<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>客户帐期信息</title>

<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oartop">
		<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置&gt;&gt;客户帐期信息
	</div>
	<div>
		<div class="rtabcont1">
			<html-el:form action="/R3InterfaceTest.do" >
			<html-el:hidden property="method" value="list5" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="tableTop">
				<tr>
					<td>
					信贷控制范围:
					<html-el:text property="v_kkber" styleId="v_kkber"  styleClass="webinput" />
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
					<th width="8%" nowrap="nowrap">销售组织</th>
					<th width="5%" nowrap="nowrap">客户编码</th>
					<th width="5%" nowrap="nowrap">风险类型</th>
					<th width="8%" nowrap="nowrap">货币</th>
					
					<th width="8%" nowrap="nowrap">原始分配金额</th>
					<th width="8%" nowrap="nowrap">当月分配金额</th>
					<th width="8%" nowrap="nowrap">信用账期额度</th>
					<th width="8%" nowrap="nowrap">总经理担保额度</th>
					<th width="8%" nowrap="nowrap">信贷限额</th>
					<!-- right up -->
					
					<th width="8%" nowrap="nowrap">信贷风险总额</th>
		 			<th width="8%" nowrap="nowrap">剩余额度</th>
					<th width="8%" nowrap="nowrap">使用状态</th>
					
					
					<!-- right -->
					<th width="8%" nowrap="nowrap">使用的信贷限额</th>
					<th width="8%" nowrap="nowrap">余额</th>
					<th width="8%" nowrap="nowrap">客户名称</th>
					<th width="8%" nowrap="nowrap">客户分类 </th>
					<th width="8%" nowrap="nowrap">描述</th>
					 
				</tr>
				  <c:forEach var="cur" items="${entityList}" varStatus="vs" >
				    <tr>
				      <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
				      <td align="center" nowrap="nowrap" >${cur.VKORG}</td>
				      <td align="center" nowrap="nowrap" >${cur.KUNNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.CTLPC}</td>
				      <td align="center" nowrap="nowrap" >${cur.DBWAE}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.KLIME}</td>
				      <td align="center" nowrap="nowrap" >${cur.KLIMG}</td>
				      <td align="center" nowrap="nowrap" >${cur.DBEKR}</td>
				      <td align="center" nowrap="nowrap" >${cur.ZLIMT}</td>
				      <td align="center" nowrap="nowrap" >${cur.KLIMK}</td>
				      <!-- right up -->
				      
				      <td align="center" nowrap="nowrap" >${cur.OBLIG}</td>
				      <td align="center" nowrap="nowrap" >${cur.SYED}</td>
				      <td align="center" nowrap="nowrap" ></td>
				      
				      <!-- right -->
				      <td align="center" nowrap="nowrap" >${cur.KLPRZ}</td>
				      <td align="center" nowrap="nowrap" >${cur.SKFOR}</td>
				      <td align="center" nowrap="nowrap" >${cur.NAME1}</td>
				      <td align="center" nowrap="nowrap" >${cur.KUKLA}</td>
				      <td align="center" nowrap="nowrap" >${cur.VTEXT}</td>
				     
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
		
			if($("#v_kkber").val()==null ||$("#v_kkber").val()=="" ){
				alert("信用控制范围不能为空!");
				return false;
			}
			
			if($("#v_spart").val()==null ||$("#v_spart").val()=="" ){
				alert("产品组不能为空!");
				return false;
			}
			
			if(($("#v_vkorg").val()==null ||$("#v_vkorg").val()=="")&&($("#kunnr").val()==null ||$("#kunnr").val()=="")){
				alert("销售组织代码和客户编码不能两者同时为空!");
				return false;
			}
		 return true ;
	});
	}) ;
</script>	

</body>
</html>
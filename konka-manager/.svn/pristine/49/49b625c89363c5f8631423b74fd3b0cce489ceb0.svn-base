<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>产品利润分析 </title>

<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	.tips_div {text-align:left; background-color: yellow;width:470px;  border: 1px ;color: red ; border-style: solid; }
</style>

</head>
<body>
	<div class="oartop">
		<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置&gt;&gt; 产品利润分析
	</div>
	<div>
		<div class="rtabcont1">
			<html-el:form action="/R3InterfaceTest.do" >
			<html-el:hidden property="method" value="list15" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
					<tr>
					 <td colspan="2">
					 	<div class="tips_div">
					 	<ol>
					 		<li>
					 		 * 号条件必录
					 		</li>
					 		<li>
					 		 日期格式为 yyyyMMdd 如20130101; 又如年份2013,月份01
					 		</li>
					 		<li>
					 		 当第一个条件录入框为空时,不能在第二个录入框输入
					 		</li>
					 	</ol>

					 	</div>
					 </td>
					</tr>
					<tr>
						<td width="120px">年度(*): </td>
						<td width="350px" align="left">
							<html-el:text property="v_lfgja_low" styleId="v_lfgja_low" styleClass="webinput"/>
							&nbsp;到&nbsp;
							<html-el:text property="v_lfgja_high" styleId="v_lfgja_high"  styleClass="webinput"/>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>月度(*): </td>
						<td>
							<html-el:text property="v_vlfmon_low" styleId="v_vlfmon_low"  styleClass="webinput"/>
							&nbsp;到&nbsp;
							<html-el:text property="v_vlfmon_high" styleId="v_vlfmon_high"  styleClass="webinput"/>
						</td>
					</tr>
					<tr>
						<td>销售组织(*): </td>
						<td>
							<html-el:text property="v_vkorg_low" styleId="v_vkorg_low"  styleClass="webinput"/>
							&nbsp;到&nbsp;
							<html-el:text property="v_vkorg_high" styleId="v_vkorg_high"  styleClass="webinput"/>
						</td>
					</tr>
					<tr>
						<td>客户 : </td>
						<td>
							<html-el:text property="v_kunnr_low" styleId="v_kunnr_low"  styleClass="webinput"/>
							&nbsp;到&nbsp;
						 	<html-el:text property="v_kunnr_high" styleId="v_kunnr_high"  styleClass="webinput" />
						</td>
					</tr>
					<tr>
						<td>机型(*): </td>
						<td>
							<html-el:text property="v_matnr_low" styleId="v_matnr_low"  styleClass="webinput" />
							&nbsp;到&nbsp;
						 	<html-el:text property="v_matnr_high" styleId="v_matnr_high"  styleClass="webinput" />
						</td>
					</tr>
					<tr>
						<td> </td>
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
			        <th width="30" align="center" nowrap="nowrap">序号</th>
			        <th align="center" nowrap="nowrap">机型</th>
			        <th align="center" nowrap="nowrap">实际已交货量</th>
			        <th align="center" nowrap="nowrap">销售收入</th>
			        <th align="center" nowrap="nowrap">销售成本</th>
			        <th align="center" nowrap="nowrap">销售毛利</th>
			        <th align="center" nowrap="nowrap">毛利率*100</th>

				</tr>
				  <c:forEach var="cur" items="${entityList}" varStatus="vs" >
				    <tr>
				      <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
				      <td align="center" nowrap="nowrap" >${cur.MATNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.LFIMG}</td>
				      <td align="center" nowrap="nowrap" >${cur.ZNET_SINCOME}</td>
				      <td align="center" nowrap="nowrap" >${cur.ZCOST}</td>
				      <td align="center" nowrap="nowrap" >${cur.ZPROFIT}</td>
				      <td align="center" nowrap="nowrap" >${cur.ZPROFIT_V}</td>
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
		if($("#v_lfgja_low").val()==null || $("#v_lfgja_low").val()==""){
			alert("年度不能为空");
			return false ;
		}
		if($("#v_lfgja_high").val()==null || $("#v_lfgja_high").val()==""){
			alert("年度不能为空");
			return false ;
		}
		if($("#v_vlfmon_low").val()==null || $("#v_vlfmon_low").val()==""){
			alert("月度不能为空");
			return false ;
		}
		if($("#v_vlfmon_high").val()==null || $("#v_vlfmon_high").val()==""){
			alert("月度不能为空");
			return false ;
		}
		if($("#v_matnr_low").val()==null || $("#v_matnr_low").val()==""){
			alert("机型不能为空");
			return false ;
		}

		return true;

	});
	}) ;
</script>

</body>
</html>
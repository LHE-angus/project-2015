<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title> 分公司调拨计划评估</title>

<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />

<style type="text/css">
	.tips_div {text-align:left; background-color: yellow;width:470px;  border: 1px ;color: red ; border-style: solid; }
</style>
 
</head>
<body>
	<div class="oartop">
		<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置&gt;&gt; 分公司调拨计划评估
	</div>
	<div>
		<div class="rtabcont1">
			<html-el:form action="/admin/KonkaR3DeptSailInfo" >
			<html-el:hidden property="method" value="list" />
			<html-el:hidden property="mod_id" styleId="mod_id" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable3">
					<tr>
					 <td colspan="2">
					 	<div class="tips_div"> 
					 	<ol>
					 		<li>
					 		 * 号条件必录 
					 		</li>
					 		<li>
					 		 当第一个条件录入框为空时,不能在第二个录入框输入
					 		</li>
					 	</ol>
					 	
					 	</div>
					 </td>
					</tr>
					<tr>
						<td width="120px">销售片区: </td>
						<td width="350px" align="left"> 
							<html-el:text property="v_bzirk_low" styleId="v_bzirk_low" styleClass="webinput"/>
							&nbsp;到&nbsp;
							<html-el:text property="v_bzirk_high" styleId="v_bzirk_high"  styleClass="webinput"/>
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>分公司: </td>
						<td> 
							<html-el:text property="v_class_low" styleId="v_class_low"  styleClass="webinput"/>
							&nbsp;到&nbsp;
							<html-el:text property="v_class_high" styleId="v_class_high"  styleClass="webinput"/>
						</td>
					</tr>
					<tr>
						<td>机型(*): </td>
						<td>
							<html-el:text property="v_matnr_low" styleId="v_matnr_low"  styleClass="webinput"/>
							&nbsp;到&nbsp;
							<html-el:text property="v_matnr_high" styleId="v_matnr_high"  styleClass="webinput"/>
						</td>
					</tr>
					<tr>
						<td>物料组 : </td>
						<td>
							<html-el:text property="v_matkl_low" styleId="v_matkl_low"  styleClass="webinput"/>
							&nbsp;到&nbsp;
						 	<html-el:text property="v_matkl_high" styleId="v_matkl_high"  styleClass="webinput" />
						</td>
					</tr>
					<tr>
						<td>转储单号 (*): </td>
						<td>
							<html-el:text property="v_ebeln_low" styleId="v_ebeln_low"  styleClass="webinput" value="UA00-00000"/>
							&nbsp;到&nbsp;
						 	<html-el:text property="v_ebeln_high" styleId="v_ebeln_high"  styleClass="webinput" value="UZ99-99999"/>
						</td>
					</tr>	
					<tr>
						<td>转储单日期(*): </td>
						<td>
							<html-el:text property="v_eindt_low" styleId="v_eindt_low"   styleClass="webinput" onclick="new Calendar(1990, 2021, 0).show(this);"/>
							&nbsp;到&nbsp;
							<html-el:text property="v_eindt_high" styleId="v_eindt_high"   styleClass="webinput" onclick="new Calendar(1990, 2021, 0).show(this);"/>
						</td>
					</tr>
					<tr>
						<td>销售日期(*):</td>
						<td>
							<html-el:text property="v_bstdk_low" styleId="v_bstdk_low"   styleClass="webinput" onclick="new Calendar(1990, 2021, 0).show(this);"/>
							&nbsp;到&nbsp;
							<html-el:text property="v_bstdk_high"  styleId="v_bstdk_high"  styleClass="webinput" onclick="new Calendar(1990, 2021, 0).show(this);"/>
						</td>
						
					</tr>
					
					<tr>
						<td>分销渠道 (*): </td>
						<td>
							<html-el:text property="v_vtweg_low" styleId="v_vtweg_low"  styleClass="webinput" value="10"/>
							&nbsp;到&nbsp;
						 	<html-el:text property="v_vtweg_high" styleId="v_vtweg_high"  styleClass="webinput" />
						</td>
					</tr>
					
					<tr>
						<td>销售组织(*) : </td>
						<td>
							<html-el:text property="v_vkorg_low" styleId="v_vkorg_low" styleClass="webinput" value="1001"/>
						 	&nbsp;&nbsp;&nbsp;&nbsp;
						 	<input name="button" type="submit" class="bgSearch" id="s_button" value="搜 索"  />
						</td>
					</tr>

				</table>
				
			</html-el:form>
		</div>
		<div class="rtabcont1">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableClass">
				<tr>
			        <th width="30" align="center" nowrap="nowrap">序号</th>
			        <th align="center" nowrap="nowrap">片区代码</th>
			        <th align="center" nowrap="nowrap">分公司</th>
			        <th align="center" nowrap="nowrap">经营部</th>
			        
			        <th align="center" nowrap="nowrap">物料</th>
			        <th align="center" nowrap="nowrap">上月销量</th>
			        <th align="center" nowrap="nowrap">本月销量</th>
			        <th align="center" nowrap="nowrap">总量</th>
			        
			        <th align="center" nowrap="nowrap">在途数量</th>
			        <th align="center" nowrap="nowrap">未发数量</th>
			        <th align="center" nowrap="nowrap">铺底60仓</th>
			        <th align="center" nowrap="nowrap">周转90仓</th>
			        
			        <th align="center" nowrap="nowrap">经营部F仓</th>
			        <th align="center" nowrap="nowrap">样机Y仓</th>
			        <th align="center" nowrap="nowrap">质检Q仓</th>
			        <th align="center" nowrap="nowrap">退机T仓</th>
			        
			        <th align="center" nowrap="nowrap">铺底P仓</th>
			        <th align="center" nowrap="nowrap">周转Z仓</th>
			        <th align="center" nowrap="nowrap">商务B仓</th>
			        <th align="center" nowrap="nowrap">电子商务仓</th>
					<th align="center" nowrap="nowrap">周转率%</th>
				</tr>
				  <c:forEach var="cur" items="${entityList}" varStatus="vs" >
				    <tr>
				      <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
				      <td align="center" nowrap="nowrap" >${cur.BZIRK}</td>
				      <td align="center" nowrap="nowrap" >${cur.CLASS}</td>
				      <td align="center" nowrap="nowrap" >${cur.CLASS2}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.MATNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.LFIMG}</td>
				      <td align="center" nowrap="nowrap" >${cur.LFIMG1}</td>
				      <td align="center" nowrap="nowrap" >${cur.SUM}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.MZT}</td>
				      <td align="center" nowrap="nowrap" >${cur.MWF}</td>
				      <td align="center" nowrap="nowrap" >${cur.PDLABST}</td>
				      <td align="center" nowrap="nowrap" >${cur.ZZLABST}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.JYLABST}</td>
				      <td align="center" nowrap="nowrap" >${cur.YJLABST}</td>
				      <td align="center" nowrap="nowrap" >${cur.CLLABST }</td>
				      <td align="center" nowrap="nowrap" >${cur.TJLABST}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.PCLABST}</td>
				      <td align="center" nowrap="nowrap" >${cur.ZCLABST}</td>
				      <td align="center" nowrap="nowrap" >${cur.BCLABST}</td>
				      <td align="center" nowrap="nowrap" >${cur.DZLABST}</td>
				      <td align="center" nowrap="nowrap" >${cur.map.zzl}</td>
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
		if($("#v_matnr_low").val()==null || $("#v_matnr_low").val()==""){
			alert("机型不能为空");
			return false ;
		}
		if($("#v_ebeln_low").val()==null || $("#v_ebeln_low").val()==""){
			alert("转储单号不能为空");
			return false ;
		}
		if($("#v_eindt_low").val()==null || $("#v_eindt_low").val()==""){
			alert("转储单日期不能为空");
			return false ;
		}
		if($("#v_bstdk_low").val()==null || $("#v_bstdk_low").val()==""){
			alert("销售单日期不能为空");
			return false ;
		}
		if($("#v_vtweg_low").val()==null || $("#v_vtweg_low").val()==""){
			alert("分销渠道不能为空");
			return false ;
		}
		if($("#v_vkorg_low").val()==null || $("#v_vkorg_low").val()==""){
			alert("销售组织不能为空");
			return false ;
		}
		return true;
	});
}) ;
</script>	

</body>
</html>
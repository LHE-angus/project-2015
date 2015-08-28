<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>库存查询[客户]</title>

<link href="${ctx}/styles/jxc/css/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/jxc/css/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="oartop">
		<img src="${ctx}/styles/jxc/images/arrow3.gif" style="vertical-align: text-top;" /> 当前位置&gt;&gt;库存查询[客户]
	</div>
	<div>
		<div class="rtabcont1">
			<html-el:form action="/R3InterfaceTest.do" >
			<html-el:hidden property="method" value="list7" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="tableTop">
				<tr>
					<td>
					工厂:
					<html-el:text property="v_werks" styleId="v_werks"  styleClass="webinput" />
					</td>
					
					<td>
					仓库地点(库位):
					<html-el:text property="v_lgort" styleId="v_lgort"  styleClass="webinput" />
					</td>
					
					<td>
					仓位(一般是客户编码):
					<html-el:text property="v_lgpla" styleId="v_lgpla"  styleClass="webinput" />
					</td>
					
					<td>
					物料编码:
					<html-el:text property="v_matnr" styleId="v_matnr"  styleClass="webinput" />
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
					
					<th width="5%" nowrap="nowrap">仓位</th>
<!-- 					<th width="5%" nowrap="nowrap">ZPNUM</th> -->
					
					
					<th width="8%" nowrap="nowrap">仓库号/混合仓库 </th>
					<th width="8%" nowrap="nowrap">仓储类型</th>
					<th width="8%" nowrap="nowrap">	名称 1</th>
					<th width="8%" nowrap="nowrap">存储区</th>
					<th width="8%" nowrap="nowrap">仓位类型</th>
					<th width="8%" nowrap="nowrap">仓位类型说明</th>
					<th width="8%" nowrap="nowrap">物料号</th>
					<th width="8%" nowrap="nowrap">物料描述</th>
					<th width="8%" nowrap="nowrap">批号</th>
					<th width="8%" nowrap="nowrap">工厂</th>
					<th width="8%" nowrap="nowrap">库存地点</th>
					<th width="8%" nowrap="nowrap">基本计量单位</th>
					
					<th width="8%" nowrap="nowrap">移动平均价格/周期单价</th>
					<th width="8%" nowrap="nowrap">估价的总库存价值</th>
					<th width="8%" nowrap="nowrap">可用库存</th>
					<th width="8%" nowrap="nowrap">未清转储数量</th>
					<th width="8%" nowrap="nowrap">	计划行日期   </th>
					
					
					<th width="8%" nowrap="nowrap">一般标记</th>
					<th width="8%" nowrap="nowrap">位字符字段</th>
					<th width="8%" nowrap="nowrap">一般标记</th>
					
					
<!-- 					<th width="8%" nowrap="nowrap">可用库存1</th> -->
<!-- 					<th width="8%" nowrap="nowrap">行记录数量</th> -->
					
					
				</tr>
				  <c:forEach var="cur" items="${entityList}" varStatus="vs" >
				    <tr>
				      <td align="center" nowrap="nowrap" height="20">${vs.count}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.LGPLA}</td>
<%-- 				      <td align="center" nowrap="nowrap" >${cur.ZPNUM}</td> --%>
				      
				      
				      
				      <td align="center" nowrap="nowrap" >${cur.LGNUM}</td>
				      <td align="center" nowrap="nowrap" >${cur.LGTYP}</td>
				      <td align="center" nowrap="nowrap" >${cur.NAME1}</td>
				      <td align="center" nowrap="nowrap" >${cur.LGBER}</td>
				      <td align="center" nowrap="nowrap" >${cur.LPTYP}</td>
				      <td align="center" nowrap="nowrap" >${cur.PTYPT}</td>
				      <td align="center" nowrap="nowrap" >${cur.MATNR}</td>
				      <td align="center" nowrap="nowrap" >${cur.MAKTX}</td>
				      <td align="center" nowrap="nowrap" >${cur.CHARG}</td>
				      <td align="center" nowrap="nowrap" >${cur.WERKS}</td>
				      <td align="center" nowrap="nowrap" >${cur.LGORT}</td>
				      <td align="center" nowrap="nowrap" >${cur.MEINS}</td>
				      
				      <td align="center" nowrap="nowrap" >${cur.VERPR}</td>
				       <td align="center" nowrap="nowrap" >${cur.SALK3}</td>
				       <td align="center" nowrap="nowrap" >${cur.VERME}</td>
				       <td align="center" nowrap="nowrap" >${cur.TRAME}</td>
				       <td align="center" nowrap="nowrap" >${cur.EDATU}</td>
				       
				      <td align="center" nowrap="nowrap" >${cur.NEWPAGE}</td>
				      <td align="center" nowrap="nowrap" >${cur.LINES}</td>
				      <td align="center" nowrap="nowrap" >${cur.NEWP}</td>
				      
<%-- 				       <td align="center" nowrap="nowrap" >${cur.MENGE}</td> --%>
<%-- 				       <td align="center" nowrap="nowrap" >${cur.LINE}</td> --%>
				       
				    
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
		if($("#v_werks").val()==null || $("#v_werks").val()==""){
			alert("工厂不能为空");
			return false ;
		}
		if($("#v_lgort").val()==null ||$("#v_lgort").val()==""){
			alert("仓库地点不能为空");
			return false ;
		}
		if($("#v_lgpla").val()==null ||$("#v_lgpla").val()==""){
			alert("仓位不能为空");
			return false ;
		}
		
		return true;
		
	});
	}) ;
</script>	

</body>
</html>
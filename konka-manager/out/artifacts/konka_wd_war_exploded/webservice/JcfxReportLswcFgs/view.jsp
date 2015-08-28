<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8"> </meta>
<title>决策分析 - ${title}</title>
<style>*{ margin:0} html,body{ height:100%} .wrapper{ min-height:100%; height:auto !important; height:100%; margin:0 auto -155px} .footer,.push{ height:155px} table.gridtable{ font-family:verdana,arial,sans-serif; font-size:11px; color:#333; border-width:1px; border-color:#666; border-collapse:collapse; margin:5px auto} table.gridtable th{ border-width:1px; padding:8px; border-style:solid; border-color:#666; background-color:#dedede} table.gridtable td{ border-width:1px; padding:8px; border-style:solid; border-color:#666; background-color:#fff} .middle{ text-align:center; margin:0 auto; width:90%; height:auto} .info{ font-size:12px; text-align:center; line-height:20px; padding:40px} .info a{ margin:0 10px; text-decoration:none; color:green}</style>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.min.js"></script>
</head>
<body>
<div class="wrapper">
<div class="middle" style="width:90%;">
<div style="padding:7px;">
<html-el:form>
<strong>年度</strong>
<html-el:select property="year" styleId="year" onchange="sbt();">
	<c:forEach begin="2012" end="2015" var="cur" varStatus="vs">
		<html-el:option value="${cur}">${cur}</html-el:option>
	</c:forEach>
</html-el:select>

<strong>月份</strong>
<html-el:select property="month" onchange="sbt();" styleId="month">
	<c:forEach begin="1" end="12" var="cur" varStatus="vs">
		<html-el:option value="${cur}">${cur}</html-el:option>
	</c:forEach>
</html-el:select> 
<span style="float:right;">
<strong>分公司</strong>
<html-el:select property="fgs_id" onchange="sbt();" styleId="fgs_id">
<c:forEach items="${fgsLatlngList}" var="cur" varStatus="vs">
<html-el:option value="${cur.fgs_id }">${cur.fgs_name }</html-el:option>
</c:forEach>
</html-el:select></span>
</html-el:form>
</div> 
<table id="table1" style="width:100%;">
    <tr>
    	<td width="40%">
	        <h2 style="padding: 10px 0 10px;" id="title1">...</h2> 
	        <p style="margin-top:5px;color:blue;font: normal 14px '微软雅黑';">目标零售额:<span id="option1_b">... </span>万元 </p> 
	        <p style="margin-top:5px;color:red;font: normal 14px '微软雅黑';">实际零售额: <span id="option1_a">... </span>万元</p> 
	        <div id="main1" style="height:300px;"></div>
        </td>
        <td width="60%" valign="top" align="center"> 
	       	<div style="text-align:right;margin-right:20px;">单位：万元</div> 
	        <table style="width:100%;font-size:12px;line-height:22px;">
	        <tr style="background-color: #072B31;color:#ffffff;">
	        	<td align="center" width="60">渠道</td><td  align="center">目标零售额</td><td  align="center">实际零售额</td><td  align="center">同期零售额</td><td  align="center">完成率</td><td align="center">增长率</td><td align="center">实际零售额占比</td><td align="center">累计目标额</td><td align="center">累计零售额</td><td align="center">累计完成率</td>
	        </tr>
	          <c:set var="s_month_retail_money_task" value="0"></c:set>
	          <c:set var="s_month_retail_money" value="0"></c:set>
	          <c:set var="s_pre_year_retail_money" value="0"></c:set>
	          <c:set var="s_month_retail_money_tbzf" value="0"></c:set>
	          <c:set var="s_month_retail_money_bz" value="0"></c:set>
	          <c:set var="s_month_retail_money_task_lj" value="0"></c:set>
	          <c:set var="s_retail_money_lj" value="0"></c:set>
	          <c:set var="s_fgs_month_retail_money" value="0"></c:set>
	          <c:forEach items="${areaList}" var="cur" varStatus="vs">
	          	<c:set var="s_fgs_month_retail_money" value="${s_fgs_month_retail_money + cur.month_retail_money}"></c:set>
	          </c:forEach>
	        <c:forEach items="${areaList}" var="cur" varStatus="vs">
	        <c:if test="${not empty cur.map.name  and cur.map.name ne '未知'}">
	          <c:set var="s_month_retail_money_task" value="${s_month_retail_money_task + cur.month_retail_money_task}"></c:set>
	          <c:set var="s_month_retail_money" value="${s_month_retail_money + cur.month_retail_money}"></c:set>
	          <c:set var="s_pre_year_retail_money" value="${s_pre_year_retail_money + cur.pre_year_retail_money}"></c:set>
	          <c:set var="s_month_retail_money_bz" value="${s_month_retail_money_bz + cur.month_retail_money_bz}"></c:set>
	          <c:set var="s_month_retail_money_task_lj" value="${s_month_retail_money_task_lj + cur.map.month_retail_money_task_lj}"></c:set>
	          <c:set var="s_retail_money_lj" value="${s_retail_money_lj + cur.map.retail_money_lj}"></c:set>
	        <tr style="background-color: #E1F3EB;">
		        <td align="center">${cur.map.name }</td>
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${cur.month_retail_money_task}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${cur.month_retail_money}" pattern="0.00"/></td>
		        <td align="right"><fmt:formatNumber value="${cur.pre_year_retail_money}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${cur.month_retail_money_task gt 0 or cur.month_retail_money_task lt 0 }">
		        		<fmt:formatNumber value="${(cur.month_retail_money-cur.month_retail_money_task)*100/cur.month_retail_money_task}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(cur.month_retail_money_task gt 0 or cur.month_retail_money_task lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="center">
		        	<c:if test="${cur.pre_year_retail_money gt 0 or cur.pre_year_retail_money lt 0 }">
		        		<c:if test="${cur.month_retail_money gt cur.pre_year_retail_money}">
			        		<fmt:formatNumber value="${(cur.month_retail_money-cur.pre_year_retail_money)*100/cur.pre_year_retail_money}" pattern="0.00"/>%
		        			<font color="green">↑</font>
		        		</c:if>
		        		<c:if test="${cur.month_retail_money lt cur.pre_year_retail_money}">
			        		<fmt:formatNumber value="${(cur.month_retail_money-cur.pre_year_retail_money)*100/cur.pre_year_retail_money}" pattern="0.00"/>%
		        			<font color="red">↓</font>
		        		</c:if>
		        	</c:if>
		        	<c:if test="${!(cur.month_retail_money gt 0 or cur.pre_year_retail_money lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="right">
		        	<c:if test="${s_fgs_month_retail_money gt 0 or s_fgs_month_retail_money lt 0 }">
		        		<fmt:formatNumber value="${(cur.month_retail_money-s_fgs_month_retail_money)*100/s_fgs_month_retail_money}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(s_fgs_month_retail_money gt 0 or s_fgs_month_retail_money lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${cur.map.month_retail_money_task_lj}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${cur.map.retail_money_lj}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${cur.map.month_retail_money_task_lj gt 0 or cur.map.month_retail_money_task_lj lt 0 }">
		        		<fmt:formatNumber value="${(cur.map.retail_money_lj-cur.map.month_retail_money_task_lj)*100/cur.map.month_retail_money_task_lj}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(cur.map.month_retail_money_task_lj gt 0 or cur.map.month_retail_money_task_lj lt 0) }">
		        		-
		        	</c:if>
		        </td>
	         </tr></c:if>
	         </c:forEach>
	        <tr style="background-color: #5BCAC6;">
		        <td align="center" >合计</td>
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${s_month_retail_money_task}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${s_month_retail_money}" pattern="0.00"/></td>
		        <td align="right"><fmt:formatNumber value="${s_pre_year_retail_money}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${s_month_retail_money_task gt 0 or s_month_retail_money_task lt 0 }">
		        		<fmt:formatNumber value="${(s_month_retail_money-s_month_retail_money_task)*100/s_month_retail_money_task}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(s_month_retail_money_task gt 0 or s_month_retail_money_task lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="center">
		        	<c:if test="${s_pre_year_retail_money gt 0 or s_pre_year_retail_money lt 0 }">
		        		<c:if test="${s_month_retail_money gt s_pre_year_retail_money}">
			        		<fmt:formatNumber value="${(s_month_retail_money-s_pre_year_retail_money)*100/s_pre_year_retail_money}" pattern="0.00"/>%
		        			<font color="green">↑</font>
		        		</c:if>
		        		<c:if test="${s_month_retail_money lt s_pre_year_retail_money}">
		        			<fmt:formatNumber value="${(s_month_retail_money-s_pre_year_retail_money)*100/s_pre_year_retail_money}" pattern="0.00"/>%
		        			<font color="red">↓</font>
		        		</c:if>
		        	</c:if>
		        	<c:if test="${!(s_pre_year_retail_money gt 0 or s_pre_year_retail_money lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="right">100%</td>
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${s_month_retail_money_task_lj}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${s_retail_money_lj}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${s_month_retail_money_task_lj gt 0 or s_month_retail_money_task_lj lt 0 }">
		        		<fmt:formatNumber value="${(s_retail_money_lj-s_month_retail_money_task_lj)*100/s_month_retail_money_task_lj}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(s_month_retail_money_task_lj gt 0 or s_month_retail_money_task_lj lt 0) }">
		        		-
		        	</c:if>
		        </td>
	         </tr>
	        </table> 
        </td>
        </tr>
</table> 

<h1 style="padding: 5px 0 10px;" id="title2"> </h1>  
<div id="main2" style="height:300px;"></div> 

<table id="table5" style="width:100%;">
    <tr>
        <td width="100%" valign="top" align="center"> 
	       	<div style="text-align:right;margin-right:20px;">单位：万元</div> 
	        <table style="width:100%;font-size:12px;line-height:22px;">
	        <tr style="background-color: #072B31;color:#ffffff;">
	        	<td align="center" width="80" rowspan="2" >分公司</td><td  align="center" rowspan="2" >目标零售额</td><td  align="center" rowspan="2" >实际零售额</td><td align="center"  rowspan="2" >同期零售额</td><td  align="center"  rowspan="2" >完成率</td><td align="center"  rowspan="2" >增长率</td><td align="center"  rowspan="2" >累计目标额</td><td align="center"  rowspan="2" >累计零售额</td><td align="center"  rowspan="2" >累计完成率</td>
	        	<td align="center" colspan="7">各渠道零售额</td>
	        </tr>
	        <tr style="background-color: #072B31;color:#ffffff;">
	        	 <td align="center">国美</td>
	        	 <td align="center">苏宁</td>
	        	 <td align="center">其他连锁</td>
	        	 <td align="center">超市渠道</td>
	        	 <td align="center">城市客户</td>
	        	 <td align="center">县乡客户</td>
	        	 <td align="center">其他客户</td> 
	        </tr>
	          <c:set var="s_month_retail_money_task" value="0"></c:set>
	          <c:set var="s_month_retail_money" value="0"></c:set>
	          <c:set var="s_pre_year_retail_money" value="0"></c:set>
	          <c:set var="s_month_retail_money_tbzf" value="0"></c:set>
	          <c:set var="s_month_retail_money_bz" value="0"></c:set>
	          <c:set var="s_month_retail_money_task_lj" value="0"></c:set>
	          <c:set var="s_retail_money_lj" value="0"></c:set>
	          <c:set var="s_fgs_month_retail_money" value="0"></c:set>
	          
	          <c:set var="s_month_retail_money_gm" value="0"></c:set>
		      <c:set var="s_month_retail_money_sn" value="0"></c:set>
		      <c:set var="s_month_retail_money_qtls" value="0"></c:set>
		      <c:set var="s_month_retail_money_csqd" value="0"></c:set>
		      <c:set var="s_month_retail_money_cskh" value="0"></c:set>
		      <c:set var="s_month_retail_money_xxkh" value="0"></c:set>
		      <c:set var="s_month_retail_money_qtkh" value="0"></c:set>
		        
	          <c:forEach items="${fgsList}" var="cur" varStatus="vs">
	          	<c:set var="s_fgs_month_retail_money" value="${s_fgs_month_retail_money + cur.month_retail_money}"></c:set>
	          </c:forEach>
	          
	        <c:forEach items="${fgsList}" var="cur" varStatus="vs">
	          <c:set var="s_month_retail_money_task" value="${s_month_retail_money_task + cur.month_retail_money_task}"></c:set>
	          <c:set var="s_month_retail_money" value="${s_month_retail_money + cur.month_retail_money}"></c:set>
	          <c:set var="s_pre_year_retail_money" value="${s_pre_year_retail_money + cur.pre_year_retail_money}"></c:set>
	          <c:set var="s_month_retail_money_bz" value="${s_month_retail_money_bz + cur.month_retail_money_bz}"></c:set>
	          <c:set var="s_month_retail_money_task_lj" value="${s_month_retail_money_task_lj + cur.map.month_retail_money_task_lj}"></c:set>
	          <c:set var="s_retail_money_lj" value="${s_retail_money_lj + cur.map.retail_money_lj}"></c:set>
	            <c:set var="s_month_retail_money_gm" value="${s_month_retail_money_gm + cur.map.month_retail_money_gm}"></c:set>
		      <c:set var="s_month_retail_money_sn" value="${s_month_retail_money_sn + cur.map.month_retail_money_sn}"></c:set>
		      <c:set var="s_month_retail_money_qtls" value="${s_month_retail_money_qtls + cur.map.month_retail_money_qtls}"></c:set>
		      <c:set var="s_month_retail_money_csqd" value="${s_month_retail_money_csqd + cur.map.month_retail_money_csqd}"></c:set>
		      <c:set var="s_month_retail_money_cskh" value="${s_month_retail_money_cskh + cur.map.month_retail_money_cskh}"></c:set>
		      <c:set var="s_month_retail_money_xxkh" value="${s_month_retail_money_xxkh + cur.map.month_retail_money_xxkh}"></c:set>
		      <c:set var="s_month_retail_money_qtkh" value="${s_month_retail_money_qtkh + cur.map.month_retail_money_qtkh}"></c:set>
	        <tr  style="background-color: #E1F3EB;">
		        <td align="center">${cur.map.name }</td>
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${cur.month_retail_money_task}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${cur.month_retail_money}" pattern="0.00"/></td>
		        <td align="right"><fmt:formatNumber value="${cur.pre_year_retail_money}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${cur.month_retail_money_task gt 0 or cur.month_retail_money_task lt 0 }">
		        		<fmt:formatNumber value="${(cur.month_retail_money-cur.month_retail_money_task)*100/cur.month_retail_money_task}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(cur.month_retail_money_task gt 0 or cur.month_retail_money_task lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="center">
		        	<c:if test="${cur.pre_year_retail_money gt 0 or cur.pre_year_retail_money lt 0 }">
		        		<c:if test="${cur.month_retail_money gt cur.pre_year_retail_money}">
			        		<fmt:formatNumber value="${(cur.month_retail_money-cur.pre_year_retail_money)*100/cur.pre_year_retail_money}" pattern="0.00"/>%
		        			<font color="green">↑</font>
		        		</c:if>
		        		<c:if test="${cur.month_retail_money lt cur.pre_year_retail_money}">
			        		<fmt:formatNumber value="${(cur.month_retail_money-cur.pre_year_retail_money)*100/cur.pre_year_retail_money}" pattern="0.00"/>%
		        			<font color="red">↓</font>
		        		</c:if>
		        	</c:if>
		        	<c:if test="${!(cur.month_retail_money gt 0 or cur.pre_year_retail_money lt 0) }">
		        		-
		        	</c:if>
		        </td> 
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${cur.map.month_retail_money_task_lj}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${cur.map.retail_money_lj}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${cur.map.month_retail_money_task_lj gt 0 or cur.map.month_retail_money_task_lj lt 0 }">
		        		<fmt:formatNumber value="${(cur.map.retail_money_lj-cur.map.month_retail_money_task_lj)*100/cur.map.month_retail_money_task_lj}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(cur.map.month_retail_money_task_lj gt 0 or cur.map.month_retail_money_task_lj lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_retail_money_gm}" pattern="0.00"/></td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_retail_money_sn}" pattern="0.00"/></td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_retail_money_qtls}" pattern="0.00"/></td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_retail_money_csqd}" pattern="0.00"/></td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_retail_money_cskh}" pattern="0.00"/></td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_retail_money_xxkh}" pattern="0.00"/></td>
		        <td align="right" ><fmt:formatNumber value="${cur.map.month_retail_money_qtkh}" pattern="0.00"/></td>
	         </tr>
	         </c:forEach>
	        <tr style="background-color: #5BCAC6;">
		        <td align="center" >合计</td>
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${s_month_retail_money_task}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${s_month_retail_money}" pattern="0.00"/></td>
		        <td align="right"><fmt:formatNumber value="${s_pre_year_retail_money}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${s_month_retail_money_task gt 0 or s_month_retail_money_task lt 0 }">
		        		<fmt:formatNumber value="${(s_month_retail_money-s_month_retail_money_task)*100/s_month_retail_money_task}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(s_month_retail_money_task gt 0 or s_month_retail_money_task lt 0) }">
		        		-
		        	</c:if>
		        </td>
		        <td align="center">
		        	<c:if test="${s_pre_year_retail_money gt 0 or s_pre_year_retail_money lt 0 }">
		        		<c:if test="${s_month_retail_money gt s_pre_year_retail_money}">
			        		<fmt:formatNumber value="${(s_month_retail_money-s_pre_year_retail_money)*100/s_pre_year_retail_money}" pattern="0.00"/>%
		        			<font color="green">↑</font>
		        		</c:if>
		        		<c:if test="${s_month_retail_money lt s_pre_year_retail_money}">
		        			<fmt:formatNumber value="${(s_month_retail_money-s_pre_year_retail_money)*100/s_pre_year_retail_money}" pattern="0.00"/>%
		        			<font color="red">↓</font>
		        		</c:if>
		        	</c:if>
		        	<c:if test="${!(s_pre_year_retail_money gt 0 or s_pre_year_retail_money lt 0) }">
		        		-
		        	</c:if>
		        </td> 
		        <td align="right" style="color: blue;"><fmt:formatNumber value="${s_month_retail_money_task_lj}" pattern="0.00"/></td>
		        <td align="right" style="color: red;"><fmt:formatNumber value="${s_retail_money_lj}" pattern="0.00"/></td>
		        <td align="center">
		        	<c:if test="${s_month_retail_money_task_lj gt 0 or s_month_retail_money_task_lj lt 0 }">
		        		<fmt:formatNumber value="${(s_retail_money_lj-s_month_retail_money_task_lj)*100/s_month_retail_money_task_lj}" pattern="0.00"/>%
		        	</c:if>
		        	<c:if test="${!(s_month_retail_money_task_lj gt 0 or s_month_retail_money_task_lj lt 0) }">
		        		-
		        	</c:if>
		        </td>
		      <td align="right" >${s_month_retail_money_gm}</td>
		      <td align="right" >${s_month_retail_money_sn}</td>
		      <td align="right" >${s_month_retail_money_qtls}</td>
		      <td align="right" >${s_month_retail_money_csqd}</td>
		      <td align="right" >${s_month_retail_money_cskh}</td>
		      <td align="right" >${s_month_retail_money_xxkh}</td>
		      <td align="right" >${s_month_retail_money_qtkh}</td> 
	         </tr>
	        </table> 
        </td>
        </tr>
</table> 
</div> 
</div> 
</body> 
<!-- script src="http://echarts.baidu.com/build/echarts-plain-map.js"></script-->
<script src="${ctx }/scripts/echarts-plain-map.js"></script>
<script type="text/javascript">
// 基于准备好的dom，初始化echarts图表
var img_src="${ctx}/images/loading45.gif";
function sbt(){
	document.forms[0].submit();
}
function reLoad(){
	var year = $("#year").val();
	var month = $("#month").val();
	var fgs_id = $("#fgs_id").val();
	myChart1(year,month,fgs_id);
	myChart2(year,month,fgs_id); 
}  
 
function myChart1(year,month,fgs_id){
	$("#main1").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/webservice/JcfxReportLswcFgs.do' />",
		data: { "method":"option1","year":year ,"month":month,"fgs_id":fgs_id},
		dataType: "json",
		sync: false,
		error: function (xhr, ajaxOptions, thrownError) {},
		success: function(result) {
			var option1 = $.parseJSON(result.option); 
			var title1= result.title; 
			var option1_a = result.option_a; 
			var option1_b = result.option_b;
			$("#title1").html(title1);
			$("#option1_a").html(option1_a);
			$("#option1_b").html(option1_b);

			var myChart1 = echarts.init(document.getElementById('main1')); 	
			myChart1.setOption(option1);
		}
	});
	// 为echarts对象加载数据	
} 
function myChart2(year,month,fgs_id){
	$("#main2").html("<img src='"+img_src+"' />"); 
	$.ajax({
		type: "POST",
		url: "<c:url value='/webservice/JcfxReportLswcFgs.do' />",
		data: { "method":"option2","year":year ,"month":month,"fgs_id":fgs_id},
		dataType: "json",
		sync: false,
		error: function (xhr, ajaxOptions, thrownError) {},
		success: function(result) {
			var option= $.parseJSON(result.option);  
			var title= result.title; 
			$("#title2").html(title);
			var myChart2 = echarts.init(document.getElementById('main2')); 
			myChart2.setOption(option);
		}
	});
} 

reLoad();
</script> 
</html>
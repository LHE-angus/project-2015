<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/pages/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.rtable1 td {
	padding:2px 5px;
}

#topFilterSelected a {
display: inline;
float: left;
height: 18px;
line-height: 22px\9;
_line-height: 18px;
margin: 1px 4px 5px 0;
padding: 0 0 0 6px;
color: #fff;
white-space: nowrap;
background: url(${ctx}/images/repeatX.png) repeat-x 0 -120px;
border-left: 1px solid #fe6e0d;
border-bottom: 1px solid #ff6501;
border-radius: 3px;
}

#topFilterSelected a em {
cursor: pointer;
float: left;
font-style: normal;
}

#topFilterSelected a i {
cursor: pointer;
display: inline;
float: left;
width: 19px;
height: 19px;
background: url(${ctx}/images/topF.png) no-repeat;
background: url(${ctx}/images/topFIe.png) no-repeat\9;
}

.i {
color: #f60;
font-style: normal;
}

#resetFilter {
float: left;
margin-top: 1px;
margin-top: 3px\9;
_margin-top: 1px;
white-space: nowrap;
}

.buttonPBSearch  {
width:46px;
height:20px;
repeat-x;
font:normal 12px/20px "宋体";
text-align:center;
color:#fff;
border:1px #ccc solid;
border-left:0;
background: #F00B0B;
}

.buttonSubSearch  {
width:80px;
height:30px;
repeat-x;
font:normal 15px/30px "宋体";
text-align:center;
color:#fff;
border:1px #ccc solid;
border-left:0;
background: #F00B0B;
}

#greybackground{background: #000;display: block;z-index: 100;width: 100%;position: absolute;top: 0;left: 0; } 
#md_serise_div{margin:0 auto;width:400px;height:auto;border:solid 1px #ccc;position:absolute;z-index:200;background-color:#fff;} 
#md_serise_div .top_div_heard{width:400px; height:29px;background-image:url(images/top_bg.gif); border-bottom:solid 1px #ccc;} 
#md_serise_div .top_div_heard .top_div_left{float:left;line-height:29px;margin-right:2px;padding-left:10px; color:#5aa608;} 
#md_serise_div .top_div_heard .top_div_right{float:right;line-height:29px;margin-right:5px;} 
#md_serise_div .top_div_heard .top_div_right a{color:#999;text-decoration:none;}
#md_serise_div .top_div_heard .top_div_right a:hover{color:red;text-decoration:underline;}
#md_serise_div .top_div_content{width:400px; height:300px;}
#md_serise_div .top_div_content .div_top{ width:100%; height:30px;line-height:30px;}
#md_serise_div .top_div_content .div_top .top_div_center{width:400px; text-align:center;}
</style>
</head>
<body>
  	<html-el:form action="/KonkaMobileSailDataSearchForFXToMob">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" styleId="mod_id" />
      <html-el:hidden property="search_first_flag" styleId="search_first_flag" value="0" />
      <html-el:hidden property="fgs_dept_id" styleId="fgs_dept_id" />
      <html-el:hidden property="customer_par_index" styleId="customer_par_index" />
      <html-el:hidden property="c_index" styleId="c_index" />
      <html-el:hidden property="label_db" styleId="label_db" />
      <html-el:hidden property="label_int" styleId="label_int" />
      <html-el:hidden property="size_sec" styleId="size_sec" />
      <html-el:hidden property="time_type" styleId="time_type" />
      <html-el:hidden property="label_3d" styleId="label_3d" />
      <html-el:hidden property="label_www" styleId="label_www" />
      <html-el:hidden property="model_name" styleId="model_name" />
      <html-el:hidden property="user_name" styleId="user_name" />
      <html-el:hidden property="user_id" styleId="user_id" />
      <html-el:hidden property="password" styleId="password" />
      <html-el:hidden property="md_serise" styleId="md_serise" />
      <html-el:hidden property="label_4k" styleId="label_4k" />
      <table width="100%" border="0" cellspacing="1" cellpadding="5" class="rtable1">
      	<tr id="topFilter">
      		<td width="10%" align="right" valign="top"><b>您已选择：</b></td>
      		<td colspan="2" width="90%" align="left" id="filterCond">
      			<c:if test="${not empty af.map.dept_id}">
      				<span id="topFilterSelected"><a href="#" title="${dept_name}" class="" id="filter_dept_id"><em>分公司：${dept_name}</em></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.l4_dept_id}">
      				<span id="topFilterSelected"><a href="#" title="${l4_dept_name}" class="" id="filter_l4_dept_id"><em>经营部：${l4_dept_name}</em></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.l5_dept_id}">
      				<span id="topFilterSelected"><a href="#" title="${l5_dept_name}" class="" id="filter_l5_dept_id"><em>办事处：${l5_dept_name}</em></a></span>
      			</c:if>
      			<!-- 
      			<c:if test="${not empty af.map.fgs_dept_id}">
      				<a href="#" style="background: #FF6600;color:#fff;padding:2px;" id="filter_fgs_dept_id">分公司：${fgs_dept_id_name}&nbsp;×</a>&nbsp;
      			</c:if> -->
      			<c:if test="${not empty af.map.customer_par_index}">
      			    <span id="topFilterSelected"><a href="#" title="${customer_par_index_name}" class="" id="filter_customer_par_index"><em>客户类型：${customer_par_index_name}</em></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.c_index}">
      				<span id="topFilterSelected"><a href="#" title="${c_index_name}" class="" id="filter_c_index"><em>客户细分类型：${c_index_name}</em></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.label_db}">
      				<span id="topFilterSelected"><a href="#" title="${label_db_name}" class="" id="filter_label_db"><em>是否大板：${label_db_name}</em></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.label_int}">
      				<span id="topFilterSelected"><a href="#" title="${label_int_name}" class="" id="filter_label_int"><em>是否安卓：${label_int_name}</em></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.label_3d}">
      				<span id="topFilterSelected"><a href="#" title="${label_3d_name}" class="" id="filter_label_3d"><em>3D电视：${label_3d_name}</em></a></span>
      			</c:if>
      			<!-- 
      			<c:if test="${not empty af.map.label_www}">
      				<span id="topFilterSelected"><a href="#" title="${label_www_name}" class="" id="filter_label_www"><em>网络电视：${label_www_name}</em></a></span>
      			</c:if> -->
      			<c:if test="${not empty af.map.label_4k}">
      				<span id="topFilterSelected"><a href="#" title="${label_4k_name}" class="" id="filter_label_4k"><em>4K：${label_4k_name}</em></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.md_serise_like}">
      				<span id="topFilterSelected"><a href="#" title="${af.map.md_serise_like}" class="" id="filter_md_serise_like"><em>产品系列：${af.map.md_serise_like}</em></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.md_serise}">
      				<span id="topFilterSelected"><a href="#" title="${af.map.md_serise}" class="" id="filter_md_serise"><em>产品系列：${af.map.md_serise}</em></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.size_sec}">
      				<span id="topFilterSelected"><a href="#" title="${size_sec_name}" class="" id="filter_size_sec"><em>规格尺寸：${size_sec_name}</em></a></span>
      			</c:if>
      			<!-- 
      			<c:if test="${not empty af.map.time_type}">
      				<span id="topFilterSelected"><a href="#" title="${time_type_name}" class="" id="filter_time_type"><em>日期维度：${time_type_name}</em></a></span>
      			</c:if> -->
      			<c:if test="${not empty af.map.date_begin or not empty af.map.date_end}">
      				<span id="topFilterSelected"><a href="#" class="" id="filter_date"><em>日期维度：
      					<c:if test="${empty af.map.date_begin and not empty af.map.date_end}">截止至${af.map.date_end}</c:if>
      					<c:if test="${not empty af.map.date_begin and empty af.map.date_end}">${af.map.date_begin}截止至今</c:if>
      					<c:if test="${not empty af.map.date_begin and not empty af.map.date_end}">${af.map.date_begin}至${af.map.date_end}</c:if></em></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.model_name}">
      				<span id="topFilterSelected"><a href="#" title="${af.map.model_name}" class="" id="filter_model_name"><em>型号：${af.map.model_name}</em></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.model_name_like}">
      				<span id="topFilterSelected"><a href="#" title="${af.map.model_name_like}" class="" id="filter_model_name_like"><em>型号：${af.map.model_name_like}</em></a></span>
      			</c:if>
      			<a id="resetFilter" href="#" style="color:#4598D2;display: inline;">重新筛选条件</a>
      		</td>
      	</tr>
      </table>
    </html-el:form>
  <div style="font-weight:100%;color:#F00;">
  	零售额：<fmt:formatNumber value="${all_price}" pattern="#,##0.00"/>&nbsp;元,零售量：<fmt:formatNumber value="${all_num}" pattern="#,###"/>&nbsp;台
  	<input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;float:right;" />
  </div>
  <div style="font-weight:normal;color:#A8A8A8;">注意：查询数据范围以您被授权查看数据的部门为基础. </div>
  <div style="overflow-x: auto;height:400px;" id="divExcel_all" title="销售明细">
  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
  		<tr class="tabtt1">
  			<td width="5%" align="center" nowrap="nowrap">序号</td>
        	<td align="center" nowrap="nowrap">客户名称</td>
        	<td align="center" nowrap="nowrap">R3编码</td>      
        	<td align="center" nowrap="nowrap">门店名称</td>     	
        	<td align="center" nowrap="nowrap">产品型号</td>   
        	<td width="3%" nowrap="nowrap" align="center">零售额</td>
        	<td width="6%" nowrap="nowrap" align="center">零售量</td>  
        	<td width="4%" align="center" nowrap="nowrap">分公司</td>
        	<td width="6%" align="center" nowrap="nowrap">一级部门</td>
        	<td width="6%" align="center" nowrap="nowrap">二级部门</td>
        	<td align="center" nowrap="nowrap">业务员</td>
        	<td width="6%" align="center" nowrap="nowrap">客户类型</td>
        	<td width="6%" align="center" nowrap="nowrap">客户细分类型</td>        	

  		</tr>
  		<c:forEach var="cur" items="${entityList}" varStatus="vs">
  			<tr class="list-tr">
  				<td align="center" nowrap="nowrap">${vs.count}</td>
  				<td align="left" nowrap="nowrap">${cur.customer_name}</td>
  				<td align="left" nowrap="nowrap">${cur.customer_r3_code}</td>  		
  				<td align="left" nowrap="nowrap">${cur.store_name}</td>
  				<td align="left" nowrap="nowrap">${cur.model_name}</td>  			
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.total_money}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.map.total_num}" pattern="#,###"/></td>
  				<td align="left" nowrap="nowrap">${cur.dept_name}</td>
  				<td align="left" nowrap="nowrap">${cur.l4_dept_name}</td>
  				<td align="left" nowrap="nowrap">${cur.l5_dept_name}</td>  	
  				<td align="left" nowrap="nowrap">${cur.ywy_user_name}</td>
  				<td align="left" nowrap="nowrap">${cur.par_customer_type_name}</td>
  				<td align="left" nowrap="nowrap">${cur.customer_type_name}</td>
  			</tr>
  		</c:forEach>
  	</table>
  	<br />
  </div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	var form = document.forms[0];

    // 导出excel
    $("#export_excel").click(function(){
    	toExcel('divExcel_all', '${ctx}/webservice/KonkaMobileSailDataSearchForFXToMob.do?method=toExcel');
    });
	
	function submitFormNoSearch(){
		var f = document.forms[0];
		$("#search_first_flag").val("0");
		f.submit();
	}
	//alert($("#filterCond").children().length);
	if($("#filterCond").children().length <= 1){
		$("#topFilter").attr("style","display:none");
	}else {
		$("#topFilter").attr("style","display:''");
	}
	
	$("#btn_submit_search").click(function(){
		
		submitForm();
	});
	
	//重新筛选条件
	//----------------------------------------
	$("#resetFilter").click(function(){
		$("#dept_id").val("");
		$("#customer_par_index").val("");
		$("#c_index").val("");
		$("#label_db").val("");
		$("#label_int").val("");
		$("#size_sec").val("");
		$("#time_type").val("");
		$("#label_3d").val("");
		$("#label_www").val("");
		$("#label_4k").val("");
		
		$("#date_begin").val("");
		$("#date_end").val("");
		
		$("#model_name").val("");
		$("#model_name_like").val("");
		
		$("#customer_name_like").val("");
		$("#customer_r3_code_like").val("");
		$("#store_name_like").val("");
		
		$("#md_serise").val("");
		$("#md_serise_like").val("");
		
		submitFormNoSearch();
	});
	//----------------------------------------
});    
                                          
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
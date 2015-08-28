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
<link href="${ctx}/styles/customer/jNotify/jNotify.jquery.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/manager/scripts/charts.js"></script>
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
<div class="oarcont" id="body_oarcont">
  <div class="oartop">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align: middle;" /></td>
        <td>当前位置：${naviString}</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align: middle;" /> <span id="span_help" style="cursor: pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  	<html-el:form action="/admin/KonkaMobileSailDataSearchForSizeSec">
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
      <html-el:hidden property="md_serise" styleId="md_serise" />
      <html-el:hidden property="label_4k" styleId="label_4k" />
      <html-el:hidden property="chart_type" styleId="chart_type" />
      <table width="100%" border="0" cellspacing="1" cellpadding="5" class="rtable1">
      	<tr id="topFilter">
      		<td width="10%" align="right" valign="top"><b>您已选择：</b></td>
      		<td colspan="2" width="90%" align="left" id="filterCond">
      			<c:if test="${not empty af.map.dept_id}">
      				<span id="topFilterSelected"><a href="#" title="${dept_name}" class="" id="filter_dept_id"><em>分公司：${dept_name}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.l4_dept_id}">
      				<span id="topFilterSelected"><a href="#" title="${l4_dept_name}" class="" id="filter_l4_dept_id"><em>经营部：${l4_dept_name}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.l5_dept_id}">
      				<span id="topFilterSelected"><a href="#" title="${l5_dept_name}" class="" id="filter_l5_dept_id"><em>办事处：${l5_dept_name}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<!-- 
      			<c:if test="${not empty af.map.fgs_dept_id}">
      				<a href="#" style="background: #FF6600;color:#fff;padding:2px;" id="filter_fgs_dept_id">分公司：${fgs_dept_id_name}&nbsp;×</a>&nbsp;
      			</c:if> -->
      			<c:if test="${not empty af.map.customer_par_index}">
      			    <span id="topFilterSelected"><a href="#" title="${customer_par_index_name}" class="" id="filter_customer_par_index"><em>客户类型：${customer_par_index_name}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.c_index}">
      				<span id="topFilterSelected"><a href="#" title="${c_index_name}" class="" id="filter_c_index"><em>客户细分类型：${c_index_name}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.label_db}">
      				<span id="topFilterSelected"><a href="#" title="${label_db_name}" class="" id="filter_label_db"><em>是否大板：${label_db_name}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.label_int}">
      				<span id="topFilterSelected"><a href="#" title="${label_int_name}" class="" id="filter_label_int"><em>是否安卓：${label_int_name}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.label_3d}">
      				<span id="topFilterSelected"><a href="#" title="${label_3d_name}" class="" id="filter_label_3d"><em>3D电视：${label_3d_name}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<!-- 
      			<c:if test="${not empty af.map.label_www}">
      				<span id="topFilterSelected"><a href="#" title="${label_www_name}" class="" id="filter_label_www"><em>网络电视：${label_www_name}</em><i title="关闭"></i></a></span>
      			</c:if> -->
      			<c:if test="${not empty af.map.label_4k}">
      				<span id="topFilterSelected"><a href="#" title="${label_4k_name}" class="" id="filter_label_4k"><em>4K：${label_4k_name}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.md_serise_like}">
      				<span id="topFilterSelected"><a href="#" title="${af.map.md_serise_like}" class="" id="filter_md_serise_like"><em>产品系列：${af.map.md_serise_like}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.md_serise}">
      				<span id="topFilterSelected"><a href="#" title="${af.map.md_serise}" class="" id="filter_md_serise"><em>产品系列：${af.map.md_serise}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.size_sec}">
      				<span id="topFilterSelected"><a href="#" title="${size_sec_name}" class="" id="filter_size_sec"><em>规格尺寸：${size_sec_name}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<!-- 
      			<c:if test="${not empty af.map.time_type}">
      				<span id="topFilterSelected"><a href="#" title="${time_type_name}" class="" id="filter_time_type"><em>日期维度：${time_type_name}</em><i title="关闭"></i></a></span>
      			</c:if> -->
      			<c:if test="${not empty af.map.date_begin or not empty af.map.date_end}">
      				<span id="topFilterSelected"><a href="#" class="" id="filter_date"><em>日期维度：
      					<c:if test="${empty af.map.date_begin and not empty af.map.date_end}">截止至${af.map.date_end}</c:if>
      					<c:if test="${not empty af.map.date_begin and empty af.map.date_end}">${af.map.date_begin}截止至今</c:if>
      					<c:if test="${not empty af.map.date_begin and not empty af.map.date_end}">${af.map.date_begin}至${af.map.date_end}</c:if></em><i title="关闭"></i></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.model_name}">
      				<span id="topFilterSelected"><a href="#" title="${af.map.model_name}" class="" id="filter_model_name"><em>型号：${af.map.model_name}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.model_name_like}">
      				<span id="topFilterSelected"><a href="#" title="${af.map.model_name_like}" class="" id="filter_model_name_like"><em>型号：${af.map.model_name_like}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<a id="resetFilter" href="#" style="color:#4598D2;display: inline;">重新筛选条件</a>
      		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right">分公司：</td>
      		<td colspan="2" align="left">
				<c:set var="disabledfgs" value="false" />
				<c:set var="disabledjyb" value="false" />
				<c:set var="disabledbsc" value="false" />
	            <c:if test="${af.map.dept_type eq 3}">
	            	<c:set var="disabledfgs" value="true" />
	            </c:if>
	          	<html-el:select property="dept_id" styleId="dept_id" disabled="${disabledfgs}">
	          		<html-el:option value="">-请选择-</html-el:option>
	          	</html-el:select>
	          	&nbsp;
	          	<html-el:select property="l4_dept_id" styleId="l4_dept_id" disabled="${disabledjyb}">
	          		<html-el:option value="">-请选择-</html-el:option>
	          	</html-el:select>
	          	&nbsp;
	          	<html-el:select property="l5_dept_id" styleId="l5_dept_id" disabled="${disabledbsc}">
	          		<html-el:option value="">-请选择-</html-el:option>
	          	</html-el:select>      		
      		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right">客户名称：</td>
      		<td colspan="2" align="left"><html-el:text property="customer_name_like" styleId="customer_name_like" styleClass="webinput" size="28"></html-el:text>
    		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right">R3编码：</td>
      		<td colspan="2" align="left"><html-el:text property="customer_r3_code_like" styleId="customer_r3_code_like" styleClass="webinput" size="28"></html-el:text>
    		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right">业务员：</td>
      		<td colspan="2" align="left"><html-el:text property="ywy_user_name_like" styleId="ywy_user_name_like" styleClass="webinput" size="28"></html-el:text>
    		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right" valign="top">日期：</td>
      		<td colspan="2" align="left">
				<html-el:text property="date_begin" styleId="date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
				至
				<html-el:text property="date_end" styleId="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
      		</td>
      	</tr>
      	<!-- 
      	<tr>
      		<td width="10%" align="right" valign="top">分公司：</td>
      		<td width="5%" align="center" valign="top"><a href="#" ${empty af.map.fgs_dept_id ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="fgs_class" title="">全部</a></td>
      		<td width="85%" align="left">
	      		<table width="100%">
	      			<c:forEach items="${konkaDeptList}" var="cur" varStatus="vs">
	      				<c:if test="${(vs.count mod 10) eq 1}"><tr></c:if>
	      				<td width="10%"><a href="#" ${cur.dept_id eq af.map.fgs_dept_id ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="fgs_class" title="${cur.dept_id},${cur.dept_name}">${cur.dept_name}</a></td>
	      				<c:if test="${(vs.count mod 10) eq 0}"></tr></c:if>
	      			</c:forEach>
	      		</table>
      		</td>
      	</tr> -->
      	<tr>
      		<td width="10%" align="right" valign="top">客户类型：</td>
      		<td width="5%" align="center" valign="top"><a href="#" ${empty af.map.customer_par_index ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="customer_class" title="">全部</a></td>
      		<td width="85%" align="left">
      			<c:forEach items="${konkaCategoryList}" var="cur" varStatus="vs">
      				<c:if test="${not empty cur.map.c_comm}">
      					<a href="#" ${cur.map.par_index eq af.map.customer_par_index ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="customer_class" title="${cur.map.par_index},${cur.map.c_comm}">${cur.map.c_comm}</a>&nbsp;&nbsp;
      				</c:if>
      				<c:if test="${(vs.count mod 10) eq 0}"><br/></c:if>
      			</c:forEach>
      		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right" valign="top">客户细分类型：</td>
      		<td width="5%" align="center" valign="top"><a href="#" ${empty af.map.c_index ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="customer_xf_class" title="">全部</a></td>
      		<td width="85%" align="left">
      			<c:forEach items="${konkaCategoryListForDetail}" var="cur" varStatus="vs">
      				<a href="#" ${cur.c_index eq af.map.c_index ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="customer_xf_class" title="${cur.c_index},${cur.c_name}">${cur.c_name}</a>&nbsp;&nbsp;
      				<c:if test="${(vs.count mod 10) eq 0}"><br/></c:if>
      			</c:forEach>
      		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right" valign="top">&nbsp;</td>
      		<td width="90%" align="left" colspan="2"><html-el:button styleId="btn_submit_search" property="btn_submit_search" styleClass="buttonSubSearch" value="搜索" /></td>
      	</tr>
      </table>
    </html-el:form>
  </div>
  
  	<table width="100%">
  		<tr>
  			<td width="48%" valign="top" >
  			    <div class="rtabcont1" style="font-weight:normal;color:#A8A8A8;">注意：查询数据范围以您被授权查看数据的部门为基础. 
			  	    <input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left:10px;float:right;" />
			    </div>
			    <div style="margin-left:10px;float:right;font-weight:700;color:#F00;">单位：万元，台</div>
  				<div class="rtabcont1" style="overflow-x: auto;height:400px;" id="divExcel_all" title="销售明细">
			  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
			  		<tr class="tabtt1">
			  			<td width="5%" align="center" nowrap="nowrap">序号</td>
			        	<td align="center" nowrap="nowrap">尺寸段</td>
			        	<td width="10%" align="center" nowrap="nowrap">结算额</td>      
			        	<td width="10%" align="center" nowrap="nowrap">结算量</td>     	
			        	<td width="10%" align="center" nowrap="nowrap">结算均价（元）</td>   
			        	<td width="10%" align="center" nowrap="nowrap">结算量占比</td>  
			        	<td width="10%" nowrap="nowrap" align="center">零售额</td>
			        	<td width="10%" nowrap="nowrap" align="center">零售量</td>  
			        	<td width="10%" align="center" nowrap="nowrap">零售均价（元）</td>   
			        	<td width="10%" align="center" nowrap="nowrap">零售量占比</td>       	
			
			  		</tr>
			  		<c:set var="total_r3_price" value="0" />
			  		<c:set var="total_r3_num" value="0" />
			  		<c:set var="total_r3_scale" value="0" />
			  		<c:set var="total_sale_price" value="0" />
			  		<c:set var="total_sale_num" value="0" />
			  		<c:set var="total_sale_scale" value="0" />
			  		<c:forEach var="cur" items="${entityList}" varStatus="vs">
			  			<tr class="list-tr">
			  				<td align="center" nowrap="nowrap">${vs.count}</td>
			  				<td align="left" nowrap="nowrap">
			  				
			  				<c:if test="${cur[0] eq '0'}">32寸及以下</c:if>
			  					<c:if test="${cur[0] eq 1}">32-36寸</c:if>
			  					<c:if test="${cur[0] eq 2}">37-39寸</c:if>
			  					<c:if test="${cur[0] eq 3}">40-45寸</c:if>
			  					<c:if test="${cur[0] eq 4}">46-50寸</c:if>
			  					<c:if test="${cur[0] eq 5}">51-59寸</c:if>
			  					<c:if test="${cur[0] eq 6}">60-69寸</c:if>
			  					<c:if test="${cur[0] eq 7}">70寸及以上</c:if>
			  					<c:if test="${cur[0] eq 9999}">其他</c:if>
			  					</td>
			  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur[1]}" pattern="#,##0.00"/></td>  		
			  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur[2]}" pattern="#,###"/></td>
			  				<td align="right" nowrap="nowrap">
			  					<c:if test="${cur[3] eq '-'}">-</c:if>
			  					<c:if test="${cur[3] ne '-'}"><fmt:formatNumber value="${cur[3]}" pattern="#,##0.00"/></c:if></td>  			
			  				<td align="right" nowrap="nowrap">
			  					<c:if test="${cur[4] eq '-'}">-</c:if>
			  					<c:if test="${cur[4] ne '-'}"><fmt:formatNumber value="${cur[4]}" pattern="#,##0.00"/>%</c:if></td>
			  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur[5]}" pattern="#,##0.00"/></td>  		
			  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur[6]}" pattern="#,###"/></td>
			  				<td align="right" nowrap="nowrap">
			  					<c:if test="${cur[7] eq '-'}">-</c:if>
			  					<c:if test="${cur[7] ne '-'}"><fmt:formatNumber value="${cur[7]}" pattern="#,##0.00"/></c:if></td>  			
			  				<td align="right" nowrap="nowrap">
			  					<c:if test="${cur[8] eq '-'}">-</c:if>
			  					<c:if test="${cur[8] ne '-'}"><fmt:formatNumber value="${cur[8]}" pattern="#,##0.00"/>%</c:if></td>
			  			</tr>
			  			<c:set var="total_r3_price" value="${total_r3_price+cur[1]}" />
			  			<c:set var="total_r3_num" value="${total_r3_num+cur[2]}" />
			  			<c:set var="total_r3_scale" value="${total_r3_scale+(cur[4] eq '-' ? 0:cur[4])}" />
			  			<c:set var="total_sale_price" value="${total_sale_price+cur[5]}" />
			  			<c:set var="total_sale_num" value="${total_sale_num+cur[6]}" />
			  			<c:set var="total_sale_scale" value="${total_sale_scale+(cur[8] eq '-' ? 0:cur[8])}" />
			  		</c:forEach>
			  		<tr>
			  			<td align="center" nowrap="nowrap" colspan="2">合计</td>
			  			<td align="right" nowrap="nowrap"><fmt:formatNumber value="${total_r3_price}" pattern="#,##0.00"/></td>  	
			  			<td align="right" nowrap="nowrap"><fmt:formatNumber value="${total_r3_num}" pattern="#,###"/></td>
			  			<td align="center" nowrap="nowrap">-</td>  			
			  			<td align="right" nowrap="nowrap"><fmt:formatNumber value="${total_r3_scale}" pattern="#,##0.00"/>%</td>
			  			<td align="right" nowrap="nowrap"><fmt:formatNumber value="${total_sale_price}" pattern="#,##0.00"/></td>  		
			  			<td align="right" nowrap="nowrap"><fmt:formatNumber value="${total_sale_num}" pattern="#,###"/></td>
			  			<td align="center" nowrap="nowrap">-</td>  			
			  			<td align="right" nowrap="nowrap"><fmt:formatNumber value="${total_sale_scale}" pattern="#,##0.00"/>%</td>	
			  		</tr>
			  	</table>
			  	<br />
			  </div>
  			</td>
  			<td width="2%">&nbsp;</td>
  			<td width="50%" valign="top">
				<table align="center" width="100%" cellpadding="0" cellspacing="0">
	       			<tr align="center">
	       				<td>
	       				    <div align="right">
	       				    	<select name="chart_type_e" id="chart_type_e">
					          		<option value="1" ${af.map.chart_type eq 1 ? "selected='selected'":""}>结算量占比</option>
					          		<option value="2" ${af.map.chart_type eq 2 ? "selected='selected'":""}>零售量占比</option>
					          	</select>
								<!-- <a href="javascript:setChartType(1);" style="color:${af.map.chart_type eq 1 ? '#B11212':'#0072bc'};">结算量占比</a>&nbsp;&nbsp;<a href="javascript:setChartType(2);" style="color:${af.map.chart_type eq 2 ? '#B11212':'#0072bc'};">零售量占比</a>&nbsp; -->
							</div>
	       					<div id="chartdiv"></div>
	       				</td>
	       			</tr>
    			</table>
			</td>
  		</tr>
  </table>
  
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	var form = document.forms[0];
	
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});
	
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId&isNotEpp=isNotEpp", "par_id", "0", false);
	$("#dept_id").change();
	

    // 导出excel
    $("#export_excel").click(function(){
    	toExcel('divExcel_all', '${ctx}/manager/admin/KonkaMobileSailDataSearchForSizeSec.do?method=toExcel');
    });
    
    if(${not empty entityList}){
		
		   var flash = "Pie3D.swf";

		   var chart = new FusionCharts("${ctx}/scripts/fusioncharts/swf/"+flash, "ChartId", "100%", "400", "0", "0");
		   chart.setDataUrlParams("search_first_flag"         , "${af.map.search_first_flag}");
		   chart.setDataUrlParams("chart_type"         , "${af.map.chart_type}");
		   chart.setDataUrlParams("fgs_dept_id"         , "${af.map.fgs_dept_id}");
		   chart.setDataUrlParams("customer_par_index"         , "${af.map.customer_par_index}");
		   chart.setDataUrlParams("c_index"         , "${af.map.c_index}");
		   chart.setDataUrlParams("date_begin"         , "${af.map.date_begin}");
		   chart.setDataUrlParams("date_end"         , "${af.map.date_end}");
		   chart.setDataUrlParams("dept_id"         , "${af.map.dept_id}");
		   chart.setDataUrlParams("l4_dept_id"         , "${af.map.l4_dept_id}");
		   chart.setDataUrlParams("l5_dept_id"         , "${af.map.l5_dept_id}");
		   chart.setDataUrlParams("customer_name_like"         , "${af.map.customer_name_like}");
		   chart.setDataUrlParams("customer_r3_code_like"         , "${af.map.customer_r3_code_like}");
		   chart.setDataUrlParams("ywy_user_name_like"         , "${af.map.ywy_user_name_like}");
		   chart.setDataURL("${ctx}/manager/admin/KonkaMobileSailDataSearchForSizeSec.do?method=chart");		
		   //chart.setDataURL("${ctx}/customer/script/data/MSLine.ftl");		     
		   chart.render("chartdiv");
	}
        
	function submitForm(){
		var f = document.forms[0];
		$("#search_first_flag").val("1");
		f.submit();
	}
	
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
		if($("#date_begin").val() == "" || $("#date_end").val() == ""){
			alert("日期范围不能为空！");
			return false;
		}else if(!compareDate($("#date_begin").val(), $("#date_end").val())){
			alert("开始时间不能大于结束时间！");
			return false;
		}else if($("#date_begin").val().substring(0,4)!=$("#date_end").val().substring(0,4)){
			alert("日期范围不能跨年选择！");
			return false;
		}
		
		loading();
		submitForm();
	});
	
	$("#chart_type_e").change(function(){
		if($("#date_begin").val() == "" || $("#date_end").val() == ""){
			alert("日期范围不能为空！");
			return false;
		}else if(!compareDate($("#date_begin").val(), $("#date_end").val())){
			alert("开始时间不能大于结束时间！");
			return false;
		}else if($("#date_begin").val().substring(0,4)!=$("#date_end").val().substring(0,4)){
			alert("日期范围不能跨年选择！");
			return false;
		}
		document.getElementById("chart_type").value = this.value;
		
		loading();
		submitForm();
	});
	
	//各类条件选择
	//-------------------------------------------
	//分公司
	$("#dept_id").change(function(){
		$("#l4_dept_id").val("");
		$("#l5_dept_id").val("");
		submitFormNoSearch();
	});
	
	//二级部门（经营部）
	$("#l4_dept_id").change(function(){
		$("#l5_dept_id").val("");
		submitFormNoSearch();
	});
	
	//三级部门（办事处）
	$("#l5_dept_id").change(function(){
		submitFormNoSearch();
	});
	
	//分公司
	$(".fgs_class").click(function(){
		$(".fgs_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#fgs_dept_id").val(strs[0]);
		}else if(strs.length < 2){
			$("#fgs_dept_id").val("");
			//alert($("#filter_fgs_dept_id"));
		}
		//alert($(this).attr("title"));
		//alert($("#fgs_dept_id").val());
		
		submitFormNoSearch();
	});
	
	//客户类型
	$(".customer_class").click(function(){
		$(".customer_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#customer_par_index").val(strs[0]);
		}else if(strs.length < 2){
			$("#customer_par_index").val("");
		}
		
		$("#c_index").val("");
		
		submitFormNoSearch();
	});
	
	//客户细分类型
	$(".customer_xf_class").click(function(){
		$(".customer_xf_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#c_index").val(strs[0]);
		}else if(strs.length < 2){
			$("#c_index").val("");
		}
		
		submitFormNoSearch();
	});
	
	
	//规格尺寸
	$(".size_sec_class").click(function(){
		$(".size_sec_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#size_sec").val(strs[0]);
		}else if(strs.length < 2){
			$("#size_sec").val("");
		}
		
		submitFormNoSearch();
	});
	
	//日期维度
	$(".time_class").click(function(){
		$(".time_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#time_type").val(strs[0]);
		}else if(strs.length < 2){
			$("#time_type").val("");
		}
		
		$("#date_begin").val("");
		$("#date_end").val("");
		
		submitFormNoSearch();
	});
	
	//日期维度自定义
	$("#btn_submit").click(function(){
		if($("#date_begin").val() == "" || $("#date_end").val() == ""){
			alert("日期范围不能为空！");
			return false;
		}else if(!compareDate($("#date_begin").val(), $("#date_end").val())){
			alert("开始时间不能大于结束时间！");
			return false;
		}else if($("#date_begin").val().substring(0,4)!=$("#date_end").val().substring(0,4)){
			alert("日期范围不能跨年选择！");
			return false;
		}else {
			$(".time_class").removeAttr("style");
			$("#time_type").val("");
		}
		
		submitFormNoSearch();
	});
	
	//比较日期大小  
	function compareDate(checkStartDate, checkEndDate) {     
	    var arys1= new Array();     
	    var arys2= new Array();     
		if(checkStartDate != null && checkEndDate != null) {     
		    arys1=checkStartDate.split('-');     
		    var sdate=new Date(arys1[0],parseInt(arys1[1]-1),arys1[2]);     
		    arys2=checkEndDate.split('-');     
		    var edate=new Date(arys2[0],parseInt(arys2[1]-1),arys2[2]);     
			if(sdate > edate) {     
			    //alert("日期开始时间大于结束时间");        
			    return false;        
			}  else {  
			    //alert("通过");  
			    return true;     
			}  
		}     
	}  
	
	//产品属性
	$(".label_type_class").click(function(){
		$(".label_db_class").removeAttr("style");
		$(".label_int_class").removeAttr("style");
		$(".label_3d_class").removeAttr("style");
		$(".label_www_class").removeAttr("style");
		$(".label_4k_class").removeAttr("style");
		$(".label_type_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		$("#label_db").val("");
		$("#label_int").val("");
		$("#label_3d").val("");
		$("#label_www").val("");
		$("#label_4k").val("");
		//产品系列关联
		$("#md_serise").val("");
		
		submitFormNoSearch();
	});
	
	//是否大板
	$(".label_db_class").click(function(){
		$("#label_db").val("");
		$("#label_int").val("");
		$("#label_3d").val("");
		$("#label_www").val("");
		$("#label_4k").val("");
		//产品系列关联
		$("#md_serise").val("");
		
		$(".label_db_class").removeAttr("style");
		$(".label_int_class").removeAttr("style");
		$(".label_3d_class").removeAttr("style");
		$(".label_www_class").removeAttr("style");
		$(".label_4k_class").removeAttr("style");
		$(".label_type_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#label_db").val(strs[0]);
		}else if(strs.length < 2){
			$("#label_db").val("");
		}
		
		submitFormNoSearch();
	});
	
	//是否智能
	$(".label_int_class").click(function(){
		$("#label_db").val("");
		$("#label_int").val("");
		$("#label_3d").val("");
		$("#label_www").val("");
		$("#label_4k").val("");
		//产品系列关联
		$("#md_serise").val("");
		
		$(".label_db_class").removeAttr("style");
		$(".label_int_class").removeAttr("style");
		$(".label_3d_class").removeAttr("style");
		$(".label_www_class").removeAttr("style");
		$(".label_4k_class").removeAttr("style");
		$(".label_type_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#label_int").val(strs[0]);
		}else if(strs.length < 2){
			$("#label_int").val("");
		}
		
		submitFormNoSearch();
	});
	
	//3D电视
	$(".label_3d_class").click(function(){
		$("#label_db").val("");
		$("#label_int").val("");
		$("#label_3d").val("");
		$("#label_www").val("");
		$("#label_4k").val("");
		//产品系列关联
		$("#md_serise").val("");
		
		$(".label_db_class").removeAttr("style");
		$(".label_int_class").removeAttr("style");
		$(".label_3d_class").removeAttr("style");
		$(".label_www_class").removeAttr("style");
		$(".label_4k_class").removeAttr("style");
		$(".label_type_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#label_3d").val(strs[0]);
		}else if(strs.length < 2){
			$("#label_3d").val("");
		}
		
		submitFormNoSearch();
	});
	
	//网络电视
	$(".label_www_class").click(function(){
		$("#label_db").val("");
		$("#label_int").val("");
		$("#label_3d").val("");
		$("#label_www").val("");
		$("#label_4k").val("");
		//产品系列关联
		$("#md_serise").val("");
		
		$(".label_db_class").removeAttr("style");
		$(".label_int_class").removeAttr("style");
		$(".label_3d_class").removeAttr("style");
		$(".label_www_class").removeAttr("style");
		$(".label_4k_class").removeAttr("style");
		$(".label_type_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#label_www").val(strs[0]);
		}else if(strs.length < 2){
			$("#label_www").val("");
		}
		
		submitFormNoSearch();
	});

	//4K
	$(".label_4k_class").click(function(){
		$("#label_db").val("");
		$("#label_int").val("");
		$("#label_3d").val("");
		$("#label_www").val("");
		$("#label_4k").val("");
		//产品系列关联
		$("#md_serise").val("");
		
		$(".label_db_class").removeAttr("style");
		$(".label_int_class").removeAttr("style");
		$(".label_3d_class").removeAttr("style");
		$(".label_www_class").removeAttr("style");
		$(".label_4k_class").removeAttr("style");
		$(".label_type_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#label_4k").val(strs[0]);
		}else if(strs.length < 2){
			$("#label_4k").val("");
		}
		
		
		submitFormNoSearch();
	});
	
	//型号
	$(".model_name_class").click(function(){
		$(".model_name_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		//具体型号未实现
		//var strs = new Array()
		//strs = $(this).attr("title").split(",");
		//if(strs.length==2){
		//	$("#model_name").val(strs[0]);
		//}else if(strs.length<2){
		//	$("#model_name").val("");
		//}
		
		$("#model_name").val("");
		$("#model_name_like").val("");
		
		submitFormNoSearch();
	});
	
	//型号维度自定义
	$("#btn_model").click(function(){
		if($("#model_name_like").val() == ""){
			alert("型号不能为空！");
			return false;
		}else {
			$(".model_name_class").removeAttr("style");
			$("#model_name").val("");
		}
		
		submitFormNoSearch();
	});
	
	//客户自定义
	$("#btn_customer").click(function(){
		
		
		submitFormNoSearch();
	});
	
	//产品系列
	$(".md_serise_class").click(function(){
		$(".md_serise_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");

		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length==2){
			$("#md_serise").val(strs[0]);
		}else if(strs.length<2){
			$("#md_serise").val("");
		}
		
		$("#md_serise_like").val("");
		
		submitFormNoSearch();
	});
	
	//产品系列自定义
	$("#btn_md_serise").click(function(){
		if($("#md_serise_like").val() == ""){
			alert("产品系列不能为空！");
			return false;
		}else {
			$(".md_serise_class").removeAttr("style");
			$("#md_serise").val("");
		}
		
		submitFormNoSearch();
	});
	//-------------------------------------------
	
	//去除已选择条件
	//----------------------------------------
	$("#filter_dept_id").click(function(){
		$("#dept_id").val("");
		$("#l4_dept_id").val("");
		$("#l5_dept_id").val("");
		submitFormNoSearch();
	});
	
	$("#filter_l4_dept_id").click(function(){
		$("#l4_dept_id").val("");
		$("#l5_dept_id").val("");
		submitFormNoSearch();
	});
	
	$("#filter_l5_dept_id").click(function(){
		$("#l5_dept_id").val("");
		submitFormNoSearch();
	});
	
	$("#filter_fgs_dept_id").click(function(){
		$("#fgs_dept_id").val("");
		submitFormNoSearch();
	});
	$("#filter_customer_par_index").click(function(){
		$("#customer_par_index").val("");
		submitFormNoSearch();
	});
	$("#filter_c_index").click(function(){
		$("#c_index").val("");
		submitFormNoSearch();
	});
	$("#filter_label_db").click(function(){
		$("#label_db").val("");
		submitFormNoSearch();
	});
	$("#filter_label_int").click(function(){
		$("#label_int").val("");
		submitFormNoSearch();
	});
	$("#filter_size_sec").click(function(){
		$("#size_sec").val("");
		submitFormNoSearch();
	});
	$("#filter_time_type").click(function(){
		$("#time_type").val("");
		submitFormNoSearch();
	});
	$("#filter_label_3d").click(function(){
		$("#label_3d").val("");
		submitFormNoSearch();
	});
	$("#filter_label_www").click(function(){
		$("#label_www").val("");
		submitFormNoSearch();
	});
	$("#filter_label_4k").click(function(){
		$("#label_4k").val("");
		
		submitFormNoSearch();
	});
	
	$("#filter_date").click(function(){
		$("#date_begin").val("");
		$("#date_end").val("");
		submitFormNoSearch();
	});
	
	$("#filter_model_name").click(function(){
		$("#model_name").val("");
		submitFormNoSearch();
	});
	
	$("#filter_model_name_like").click(function(){
		$("#model_name_like").val("");
		submitFormNoSearch();
	});
	
	$("#filter_md_serise_like").click(function(){
		$("#md_serise_like").val("");
		submitFormNoSearch();
	});
	
	$("#filter_md_serise").click(function(){
		$("#md_serise").val("");
		submitFormNoSearch();
	});
	//----------------------------------------
	
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
		$("#ywy_user_name_like").val("");
		
		$("#md_serise").val("");
		$("#md_serise_like").val("");
		
		submitFormNoSearch();
	});
	//----------------------------------------
});    

function loading(){
	jLoading("&nbsp;&nbsp;正在加载数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
}

function setChartType(type){
	document.getElementById("chart_type").value = type;
	
	loading();
	var f = document.forms[0];
	$("#search_first_flag").val("1");
	f.submit();
}
                                          
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
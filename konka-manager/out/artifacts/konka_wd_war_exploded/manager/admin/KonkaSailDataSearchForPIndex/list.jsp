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
  	<html-el:form action="/admin/KonkaSailDataSearchForPIndex">
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
      			<c:if test="${not empty af.map.town or not empty af.map.country or not empty af.map.city or not empty af.map.province}">
      				<span id="topFilterSelected"><a href="#" title="${full_p_name}" class="" id="filter_p_index"><em>行政区域：${full_p_name}</em><i title="关闭"></i></a></span>
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
      			<c:if test="${not empty af.map.sale_money_begin or not empty af.map.sale_money_end}">
      				<span id="topFilterSelected"><a href="#" class="" id="filter_sale_money"><em>日期维度：
      					<c:if test="${empty af.map.sale_money_begin and not empty af.map.sale_money_end}">小于等于${af.map.sale_money_end}元</c:if>
      					<c:if test="${not empty af.map.sale_money_begin and empty af.map.sale_money_end}">大于等于${af.map.sale_money_begin}元</c:if>
      					<c:if test="${not empty af.map.sale_money_begin and not empty af.map.sale_money_end}">${af.map.sale_money_end}~${af.map.sale_money_begin}元</c:if>
      					</em><i title="关闭"></i></a></span>
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
	          	<div id="hidden02" style="display:none;">
	          	&nbsp;
	          	<html-el:select property="l4_dept_id" styleId="l4_dept_id" disabled="${disabledjyb}">
	          		<html-el:option value="">-请选择-</html-el:option>
	          	</html-el:select>
	          	&nbsp;
	          	<html-el:select property="l5_dept_id" styleId="l5_dept_id" disabled="${disabledbsc}">
	          		<html-el:option value="">-请选择-</html-el:option>
	          	</html-el:select> 
	          	</div>
    		
      		</td>
      	</tr>
      	 <tr>
      		<td width="10%" align="right">行政区域：</td>
      		<td colspan="2" align="left">
      			<select name="province" id="province" style="width:180px;">
                  <option value="">-请选择省/直辖市/自治区-</option>
                </select>
                &nbsp;
                <select name="city" id="city" style="width:100px;">
                  <option value="">-请选择市-</option>
                </select>
                &nbsp;
                <select name="country" id="country" style="width:100px;">
                  <option value="">-请选择县-</option>
                </select>
                &nbsp;
                <select name="town" id="town" style="width:100px;">
                  <option value="">-请选择乡镇-</option>
                </select>
      		</td>
      	</tr> 
      	<!-- >tr>
      		<td width="10%" align="right">&nbsp;</td>
      		<td colspan="2" align="left">客户名称：<html-el:text property="customer_name_like" styleId="customer_name_like" styleClass="webinput" size="28"></html-el:text>
      			&nbsp;R3编码：<html-el:text property="customer_r3_code_like" styleId="customer_r3_code_like" styleClass="webinput" size="28"></html-el:text>
      			&nbsp;门店名称：<html-el:text property="store_name_like" styleId="store_name_like" styleClass="webinput" size="28"></html-el:text>
    		</td>
      	</tr -->
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
      		<td width="10%" align="right" valign="top">产品属性：</td>
      		<td width="5%" align="center" valign="top"><a href="#" ${(empty af.map.label_db and empty af.map.label_int and empty af.map.label_3d and empty af.map.label_4k) ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_type_class" title="">全部</a></td>
      		<td width="85%" align="left">
      			<a href="#" ${af.map.label_4k eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_4k_class" title="1,是">4K</a>&nbsp;&nbsp;
      			<a href="#" ${af.map.label_db eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_db_class" title="1,是">大板</a>&nbsp;&nbsp;
      			<a href="#" ${af.map.label_int eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_int_class" title="1,是">安卓</a>&nbsp;&nbsp;
      			<a href="#" ${af.map.label_3d eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_3d_class" title="1,是">3D电视</a>&nbsp;&nbsp;
      			<!-- <a href="#" ${af.map.label_www eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_www_class" title="1,是">网络电视</a>&nbsp;&nbsp; -->
      		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right" valign="top">产品系列：</td>
      		<td width="5%" align="center" valign="top"><a href="#" ${empty af.map.md_serise_like and empty af.map.md_serise ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="md_serise_class" title="">全部</a></td>
      		<td width="85%" align="left">
      			<c:if test="${af.map.label_4k ne 1}">
	      			<c:forEach items="${mdSeriseList}" var="md_serise" varStatus="vs">
	      				<a href="#" ${md_serise eq af.map.md_serise ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="md_serise_class" title="${md_serise},${md_serise}">${md_serise}</a>&nbsp;&nbsp;
	      				<c:if test="${(vs.count mod 10) eq 0}"><br/></c:if>
	      			</c:forEach>
      			</c:if>
      			<c:if test="${af.map.label_4k eq 1}">
      				<a href="#" ${af.map.md_serise eq 9500 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="md_serise_class" title="9500,9500">9500</a>&nbsp;&nbsp;
      				<a href="#" ${af.map.md_serise eq 9600 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="md_serise_class" title="9600,9600">9600</a>&nbsp;&nbsp;
      			</c:if>
      		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right" valign="top">规格尺寸：</td>
      		<td width="5%" align="center" valign="top"><a href="#" ${empty af.map.size_sec ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="size_sec_class" title="">全部</a></td>
      		<td width="85%" align="left">
      		<c:forEach items="${sizeSecList}" var="cur" varStatus="vs">
					<a href="#" ${af.map.size_sec eq cur.field1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="size_sec_class" title="${cur.field1},${cur.type_name}">${cur.type_name}</a>&nbsp;&nbsp;
					<c:if test="${(vs.count mod 10) eq 0}"><br/></c:if>
			</c:forEach>
      		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right" valign="top">日期：</td>
      		<td width="5%" align="center" valign="top"><a href="#" ${(empty af.map.time_type and empty af.map.date_begin and empty af.map.date_end) ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="time_class" title="">全部</a></td>
      		<td width="85%" align="left">
      			<!-- 
      			<a href="#" ${af.map.time_type eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="time_class" title="1,昨日">昨日</a>&nbsp;&nbsp;
      			<a href="#" ${af.map.time_type eq 2 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="time_class" title="2,当日">当日</a>&nbsp;&nbsp;
      			<a href="#" ${af.map.time_type eq 3 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="time_class" title="3,当周">当周</a>&nbsp;&nbsp;
      			<a href="#" ${af.map.time_type eq 4 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="time_class" title="4,当月">当月</a>&nbsp;&nbsp;
      			<a href="#" ${af.map.time_type eq 5 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="time_class" title="5,当季度">当季度</a>&nbsp;&nbsp;
      			<a href="#" ${af.map.time_type eq 6 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="time_class" title="6,当年">当年</a>&nbsp;&nbsp;
				 -->
				<html-el:text property="date_begin" styleId="date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
				至
				<html-el:text property="date_end" styleId="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
      		</td>
      	</tr>
      	<!-- tr>
      		<td width="10%" align="right" valign="top">型号：</td>
      		<td width="5%" align="center" valign="top"><a href="#" ${(empty af.map.model_name and empty af.map.model_name_like) ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="model_name_class" title="">全部</a></td>
      		<td width="85%" align="left">
				<html-el:text property="model_name_like" styleId="model_name_like" styleClass="webinput" size="28"></html-el:text>
      		</td>
      	</tr> -->
      	<tr>
      		<td width="10%" align="right" valign="top">零售额区间：</td>
      		<td width="5%" align="center" valign="top"><a href="#" ${(empty af.map.sale_money_begin and empty af.map.sale_money_end) ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="sale_money_class" title="">全部</a></td>
      		<td width="85%" align="left">
				<html-el:text property="sale_money_begin" styleId="sale_money_begin" size="9" />
				至
				<html-el:text property="sale_money_end" styleId="sale_money_end" size="9" />&nbsp;元
      		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right" valign="top">自定义显示列：</td>
      		<td colspan="2" align="left">      	
      		<div id="hidden01" style="display:none;">
      		    <html-el:radio property="group_time" value="1" styleId="group_time_1" >年度&nbsp;</html-el:radio>
      			<html-el:radio property="group_time" value="2" styleId="group_time_2">季度&nbsp;</html-el:radio>
      			<html-el:radio property="group_time" value="3" styleId="group_time_3">月份&nbsp;</html-el:radio>
      			<html-el:radio property="group_time" value="4" styleId="group_time_4">周&nbsp;</html-el:radio>
      			<html-el:radio property="group_time" value="5" styleId="group_time_5">日期&nbsp;</html-el:radio>
      			<br />		
      		</div>
      			<html-el:radio property="group_p_index" value="1" styleId="group_p_index_1" >省&nbsp;&nbsp;&nbsp;</html-el:radio>
      			<html-el:radio property="group_p_index" value="2" styleId="group_p_index_2">市&nbsp;&nbsp;&nbsp;</html-el:radio>
      			<html-el:radio property="group_p_index" value="3" styleId="group_p_index_3">县&nbsp;&nbsp;&nbsp;</html-el:radio>
      			<html-el:radio property="group_p_index" value="4" styleId="group_p_index_4">乡镇&nbsp;</html-el:radio>
      		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right" valign="top">&nbsp;</td>
      		<td width="90%" align="left" colspan="2"><html-el:button styleId="btn_submit_search" property="btn_submit_search" styleClass="buttonSubSearch" value="搜索" /></td>
      	</tr>
      </table>
    </html-el:form>
  </div>
  <div id="xianshihz" class="rtabcont1" style="font-weight:700;color:#F00;" >	
  </div>
  <div class="rtabcont1" style="font-weight:700;color:#F00;">
  	<input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;float:right;" />
  </div>
  <div class="rtabcont1" style="font-weight:normal;color:#A8A8A8;">注意：查询数据范围以您被授权查看数据的部门为基础. </div>
  <div class="rtabcont1" style="overflow-x: auto;height:400px;" id="divExcel_all" title="零售统计">
  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
  		<tr class="tabtt1">
  			<td width="5%" align="center" nowrap="nowrap">序号</td>

        	<td align="center" nowrap="nowrap">分公司 ${af.map.group_p_index}</td>    
        	<td align="center" nowrap="nowrap">省</td>  
        	<c:if test="${af.map.group_p_index eq 2}">
        		<td align="center" nowrap="nowrap">市</td>
        	</c:if>    
        	<c:if test="${af.map.group_p_index eq 3}">
        		<td align="center" nowrap="nowrap">市</td>
        		<td align="center" nowrap="nowrap">县</td> 
        	</c:if>
        	<c:if test="${af.map.group_p_index eq 4}">
        	    <td align="center" nowrap="nowrap">市</td>
        		<td align="center" nowrap="nowrap">县</td> 
        		<td align="center" nowrap="nowrap">乡镇</td> 
        	</c:if>
        	<td align="center" nowrap="nowrap">客户数</td>  
        	<td align="center" nowrap="nowrap">门店数</td>    
        	<td align="center" nowrap="nowrap">业务员数</td> 
        	<td align="center" nowrap="nowrap">促销员</td>    
        	<td width="3%" nowrap="nowrap" align="center">零售量（台）</td>    
        	<td width="6%" nowrap="nowrap" align="center">零售额（元）</td>
        	<td width="6%" nowrap="nowrap" align="center">平均单价（元）</td>
        	<td width="3%" nowrap="nowrap" align="center">GDP(万元)</td>  
        	<td width="6%" nowrap="nowrap" align="center">人口(万人)</td>  
        	<td width="6%" nowrap="nowrap" align="center">面积(万平米)</td>
        	<td width="6%" nowrap="nowrap" align="center">家电客户数(个)</td>        	
  		</tr>
  		<c:set var="total_num" value="0" />
  		<c:set var="total_money" value="0" />
  		<c:forEach var="cur" items="${entityList}" varStatus="vs">
  			<tr class="list-tr">
  				<td align="center" nowrap="nowrap">${vs.count}</td>

 				<td align="left" nowrap="nowrap">
 					<c:if test="${empty cur[2]}"><span style="color:#F00;"></span></c:if>
 					<c:if test="${not empty cur[2]}">${cur[2]}</c:if>
 				</td>  	
 				
 				<td align="center" nowrap="nowrap">  					
 				 	<c:if test="${empty cur[10]}"><span style="color:#F00;"></span></c:if>
 					<c:if test="${not empty cur[10]}">${cur[10]}</c:if>
 				</td>  

 				<c:if test="${af.map.group_p_index eq 2}">
		        	<td align="center" nowrap="nowrap">
		  				 	<c:if test="${empty cur[11]}"><span style="color:#F00;"></span></c:if>
		  					<c:if test="${not empty cur[11]}">${cur[11]}</c:if>
					</td>    
				</c:if>
				<c:if test="${af.map.group_p_index eq 3}">
		        	<td align="center" nowrap="nowrap">
		  				 	<c:if test="${empty cur[11]}"><span style="color:#F00;"></span></c:if>
		  					<c:if test="${not empty cur[11]}">${cur[11]}</c:if>
					</td> 
		        	<td align="center" nowrap="nowrap">
		  				 	<c:if test="${empty cur[12]}"><span style="color:#F00;"></span></c:if>
		  					<c:if test="${not empty cur[12]}">${cur[12]}</c:if>
					</td> 
				</c:if>
				<c:if test="${af.map.group_p_index eq 4}">
					<td align="center" nowrap="nowrap">
		  				 	<c:if test="${empty cur[11]}"><span style="color:#F00;"></span></c:if>
		  					<c:if test="${not empty cur[11]}">${cur[11]}</c:if>
					</td> 
		        	<td align="center" nowrap="nowrap">
		  				 	<c:if test="${empty cur[12]}"><span style="color:#F00;"></span></c:if>
		  					<c:if test="${not empty cur[12]}">${cur[12]}</c:if>
	
	        	<td align="center" nowrap="nowrap">
	  				 	<c:if test="${empty cur[13]}"><span style="color:#F00;"></span></c:if>
	  					<c:if test="${not empty cur[13]}">${cur[13]}</c:if>
				</td> 			
 				</c:if>
	  			<td align="right" nowrap="nowrap">  					
	  					<c:if test="${empty cur[6]}"><span style="color:#F00;">0</span></c:if>
	  					<c:if test="${not empty cur[6]}">${cur[6]}</c:if></td>  
	        	<td align="right" nowrap="nowrap"><c:if test="${empty cur[8]}"><span style="color:#F00;">0</span></c:if>
	  					<c:if test="${not empty cur[8]}">${cur[8]}</c:if></td>    
	        	<td align="right" nowrap="nowrap"><c:if test="${empty cur[7]}"><span style="color:#F00;">0</span></c:if>
	  					<c:if test="${not empty cur[7]}">${cur[7]}</c:if></td> 
	        	<td align="right" nowrap="nowrap"><c:if test="${empty cur[9]}"><span style="color:#F00;">0</span></c:if>
	  					<c:if test="${not empty cur[9]}">${cur[9]}</c:if></td> 
	
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur[4]}" pattern="#,###"/></td>		
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur[5]}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap">
  					<c:if test="${empty cur[4] or cur[4] eq 0}">-</c:if>
  					<c:if test="${not empty cur[4] and cur[4] ne 0}">
  						<fmt:formatNumber value="${cur[5]/cur[4]}" pattern="#,##0.00"/>
  					</c:if>
  				</td>
  				<td align="center" nowrap="nowrap">
	  				 	<c:if test="${empty cur[14]}"><span style="color:#F00;"></span></c:if>
	  					<c:if test="${not empty cur[14]}"><fmt:formatNumber value="${cur[14]}" pattern="#,###"/></c:if>
				</td> 
				<td align="center" nowrap="nowrap">
	  				 	<c:if test="${empty cur[15]}"><span style="color:#F00;"></span></c:if>
	  					<c:if test="${not empty cur[15]}"><fmt:formatNumber value="${cur[15]}" pattern="#,###"/></c:if>
				</td> 
				<td align="center" nowrap="nowrap">
	  				 	<c:if test="${empty cur[16]}"><span style="color:#F00;"></span></c:if>
	  					<c:if test="${not empty cur[16]}"><fmt:formatNumber value="${cur[16]}" pattern="#,###"/></c:if>
				</td> 
				<td align="center" nowrap="nowrap">
	  				 	<c:if test="${empty cur[16]}"><span style="color:#F00;"></span></c:if>
	  					<c:if test="${not empty cur[16]}"><fmt:formatNumber value="${cur[17]}" pattern="#,###"/></c:if>
				</td> 				
  			</tr>
  			<c:set var="total_num" value="${total_num+cur[4]}" />
  			<c:set var="total_money" value="${total_money+cur[5]}" />
  			<c:set var="total_r3" value="${total_r3+cur[6]}" />
  			<c:set var="total_store" value="${total_store+cur[8]}" />
  			<c:set var="total_ywy" value="${total_ywy+cur[7]}" />
  			<c:set var="total_cxy" value="${total_cxy+cur[9]}" />
  			<c:set var="total_gdp" value="${total_r3+cur[14]}" />
  			<c:set var="total_rk" value="${total_store+cur[15]}" />
  			<c:set var="total_mianji" value="${total_ywy+cur[16]}" />
  			<c:set var="total_jdkhsl" value="${total_cxy+cur[17]}" />  			
  		</c:forEach>
  		<tr style="font-weight:700;color:#F00;">
  			<td align="right" nowrap="nowrap" colspan="2">合计</td>

			<td align="right" nowrap="nowrap" colspan="1"></td>

			<c:if test="${af.map.group_p_index eq 2}">
				<td align="right" nowrap="nowrap" colspan="1"></td>
			</c:if>
			<c:if test="${af.map.group_p_index eq 3}">
			<td align="right" nowrap="nowrap" colspan="1"></td>
				<td align="right" nowrap="nowrap" colspan="1"></td>
			</c:if>
			
			<c:if test="${af.map.group_p_index eq 4}">
				<td align="right" nowrap="nowrap" colspan="1"></td>
				<td align="right" nowrap="nowrap" colspan="1"></td>			
				<td align="right" nowrap="nowrap" colspan="1"></td>
			</c:if>
  			<td align="right" nowrap="nowrap"><fmt:formatNumber value="${total_r3}" pattern="#,###"/></td>		
  			<td align="right" nowrap="nowrap"><fmt:formatNumber value="${total_store}" pattern="#,###"/></td>
  			<td align="right" nowrap="nowrap"><fmt:formatNumber value="${total_ywy}" pattern="#,###"/></td>		
  			<td align="right" nowrap="nowrap"><fmt:formatNumber value="${total_cxy}" pattern="#,###"/></td>
  			<td align="right" nowrap="nowrap"><fmt:formatNumber value="${total_num}" pattern="#,###"/></td>		
  			<td align="right" nowrap="nowrap"><fmt:formatNumber value="${total_money}" pattern="#,##0.00"/></td>
  			<td align="right" nowrap="nowrap">
  				<c:if test="${empty total_num or total_num eq 0}">-</c:if>
  				<c:if test="${not empty total_num and total_num ne 0}">
  					<fmt:formatNumber value="${(empty total_num or total_num eq 0) ? 0:total_money/total_num}" pattern="#,##0.00"/>
  				</c:if>
  			</td>
   			<td align="right" nowrap="nowrap"><!--fmt:formatNumber value="${total_gdp}" pattern="#,###"/--></td>
  			<td align="right" nowrap="nowrap"><!--fmt:formatNumber value="${total_rk}" pattern="#,###"/--></td>		
  			<td align="right" nowrap="nowrap"><!--fmt:formatNumber value="${total_mianji}" pattern="#,###"/--></td>
  			<td align="right" nowrap="nowrap"><!--fmt:formatNumber value="${total_jdkhsl}" pattern="#,###"/--></td>	 			
  			<script>
  			var title = "客户共 <fmt:formatNumber value='${total_r3}' pattern='#,###'/>，门店共<fmt:formatNumber value='${total_store}' pattern='#,###'/>，业务员共<fmt:formatNumber value='${total_ywy}' pattern='#,###'/>人，促销员共<fmt:formatNumber value='${total_cxy}' pattern='#,###'/>人，零售量<fmt:formatNumber value='${total_num}' pattern='#,###'/>台，零售额<fmt:formatNumber value='${total_money}' pattern='#,##0.00'/>元"

  			document.getElementById('xianshihz').innerHTML = title;
  			</script>
  		</tr>
  	</table>
  	<br />
  </div>
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
	
	var radio_p_index = '${af.map.group_p_index}';
	
	if(radio_p_index == ''){
		document.getElementById("group_p_index_1").checked = true;
	}else if(radio_p_index == 1){
		document.getElementById("group_p_index_1").checked = true;
	}else if(radio_p_index == 2){
		document.getElementById("group_p_index_2").checked = true;
	}else if(radio_p_index == 3){
		document.getElementById("group_p_index_3").checked = true;
	}else if(radio_p_index == 4){
		document.getElementById("group_p_index_4").checked = true;
	}
	
	var form = document.forms[0];
	
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});
	
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();

	//行政区域联动
	$("#province").attr({"subElement": "city", "defaultText": "-请选择省/直辖市/自治区-", "defaultValue": "", "selectedValue": "${af.map.province}"});
	$("#city"    ).attr({"subElement": "country", "defaultText": "-请选择市-", "defaultValue": "", "selectedValue": "${af.map.city}"});
	$("#country" ).attr({"subElement": "town","defaultText": "-请选择县-", "defaultValue": "", "selectedValue": "${af.map.country}"});
	$("#town" ).attr({"defaultText": "-请选择乡镇-", "defaultValue": "", "selectedValue": "${af.map.town}"});
	$("#province").cs("${ctx}/manager/admin/CsAjax.do?method=getBaseProvinceFourList", "p_index", "0", false);
	

    // 导出excel
    $("#export_excel").click(function(){
    	toExcel('divExcel_all', '${ctx}/manager/admin/KonkaSailDataSearchForPIndex.do?method=toExcel');
    });
    
    var screenwidth, screenheight, mytop, getPosLeft, getPosTop;
	screenwidth = $(window.parent).width(); 
	screenheight = $(window.parent).height();
	//获取滚动条距顶部的偏移 
	mytop = $(document).scrollTop();
	//计算弹出层的left
	getPosLeft = screenwidth / 2 - 200;
	//计算弹出层的top 
	getPosTop = screenheight / 2 - 150;
	//css定位弹出层 
	$("#md_serise_div").css({ "left": getPosLeft, "top": getPosTop });
	//当浏览器窗口大小改变时 
	$(window).resize(function() {
		screenwidth = $(window.parent).width();
		screenheight = $(window.parent).height();
		mytop = $(document).scrollTop();
		getPosLeft = screenwidth / 2 - 200;
		getPosTop = screenheight / 2 - 150;
		$("#md_serise_div").css({ "left": getPosLeft, "top": getPosTop + mytop });
	});
	//当拉动滚动条时，弹出层跟着移动 
	$(window).scroll(function(){
		screenwidth = $(window.parent).width(); 
		screenheight = $(window.parent).height();
		mytop = $(document).scrollTop();
		getPosLeft = screenwidth / 2 - 200;
		getPosTop = screenheight / 2 - 150;
		$("#md_serise_div").css({ "left": getPosLeft, "top": getPosTop + mytop });
	});
    
    $(document).delegate("#update_md_serise", "click", function(){
		showLogin();
	}); 
	//点击关闭按钮 
	$("#md_serise_div_close").click(function() {
		hideLogin();
	});
	
	function showLogin(){//显示div  
		$("#md_serise_div").fadeIn("slow");
		var docheight = $(document).height(); //获取页面文档的高度 
		$("body").append("<div id='greybackground' style='z-index:99990;'></div>"); //追加一个层，使背景变灰 
		$("#greybackground").css({ "opacity": "0.5", "height": docheight });
		return false;
	}

	function hideLogin(){
		$("#md_serise_div").fadeOut("slow"); 
		$("#greybackground").remove();//删除变灰的层 
		return false; 
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
		var group_time = "";
		if(document.getElementById("group_time_1").checked){
			group_time = "1"
		}else if(document.getElementById("group_time_2").checked){
			group_time = "2"
		}else if(document.getElementById("group_time_3").checked){
			group_time = "3"
		}else if(document.getElementById("group_time_4").checked){
			group_time = "4"
		}else if(document.getElementById("group_time_5").checked){
			group_time = "5"
		}
		
		var date_begin = $("#date_begin").val();
		var date_end = $("#date_end").val();
		if(group_time == "2" || group_time == "3" || group_time == "4"){
			if(date_begin == '' || date_end == ''){
				alert("日期开始时间，结束时间不能为空！");
				return false;
			}else if(date_begin.substring(0,4) != date_end.substring(0,4)){
				alert("日期不能跨年度选择！");
				return false;
			}
		}
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

	//省
	$("#province").change(function(){
		$("#city").val("");
		$("#country").val("");
		$("#town").val("");
		submitFormNoSearch();
	});

	//市
	$("#city").change(function(){
		$("#country").val("");
		$("#town").val("");
		submitFormNoSearch();
	});

	//县
	$("#country").change(function(){
		$("#town").val("");
		submitFormNoSearch();
	});

	//乡镇
	$("#town").change(function(){
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
		if($("#date_begin").val() == "" && $("#date_end").val() == ""){
			alert("日期范围不能为空！");
			return false;
		}else if(!compareDate($("#date_begin").val(), $("#date_end").val())){
			alert("开始时间不能大于结束时间！");
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

	//零售额区间
	$(".sale_money_class").click(function(){
		$(".sale_money_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");

		$("#sale_money_begin").val("");
		$("#sale_money_end").val("");
		
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

	//行政区域
	$("#filter_p_index").click(function(){
		$("#province").val("");
		$("#city").val("");
		$("#country").val("");
		$("#town").val("");
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

	$("#filter_sale_money").click(function(){
		$("#sale_money_begin").val("");
		$("#sale_money_end").val("");
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
		$("#store_name_like").val("");
		
		$("#md_serise").val("");
		$("#md_serise_like").val("");

		$("#province").val("");
		$("#city").val("");
		$("#country").val("");
		$("#town").val("");

		$("#sale_money_begin").val("");
		$("#sale_money_end").val("");
		
		submitFormNoSearch();
	});
	//----------------------------------------
});    

function loading(){
	jLoading("&nbsp;&nbsp;正在加载数据...", {autoHide:false, HorizontalPosition:"center", VerticalPosition:"center", MinWidth:150});
}
                                          
</script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
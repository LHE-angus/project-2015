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
        <td>当前位置：${naviString}渠道管理系统&nbsp;&gt;&nbsp;进销存首页</td>
        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align: middle;" /> <span id="span_help" style="cursor: pointer;">帮助</span></td>
      </tr>
    </table>
  </div>
  <div class="rtabcont1">
  	<html-el:form action="/admin/KonkaMobileSailDataSearch">
      <html-el:hidden property="method" value="toSailDataByDept" />
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
      <html-el:hidden property="label_ytv" styleId="label_ytv" />
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
      			<c:if test="${not empty af.map.label_4k}">
      				<span id="topFilterSelected"><a href="#" title="${label_4k_name}" class="" id="filter_label_4k"><em>4K：${label_4k_name}</em><i title="关闭"></i></a></span>
      			</c:if>
      			<c:if test="${not empty af.map.label_ytv}">
      				<span id="topFilterSelected"><a href="#" title="${label_ytv_name}" class="" id="filter_label_ytv"><em>易TV：${label_ytv_name}</em><i title="关闭"></i></a></span>
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
      			
      			<div align="right">
      				<input class="but5" type="button" name="Submit5" value="返回" onclick="toJxcHomePage()" />
      			</div>
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
      		<td width="10%" align="right" valign="top">产品属性：</td>
      		<td width="5%" align="center" valign="top"><a href="#" ${(empty af.map.label_db and empty af.map.label_int and empty af.map.label_3d and empty af.map.label_4k and empty af.map.label_ytv) ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_type_class" title="">全部</a></td>
      		<td width="85%" align="left">
      			<a href="#" ${af.map.label_4k eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_4k_class" title="1,是">4K</a>&nbsp;&nbsp;
      			<a href="#" ${af.map.label_db eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_db_class" title="1,是">大板</a>&nbsp;&nbsp;
      			<a href="#" ${af.map.label_int eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_int_class" title="1,是">安卓</a>&nbsp;&nbsp;
      			<a href="#" ${af.map.label_3d eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_3d_class" title="1,是">3D电视</a>&nbsp;&nbsp;
      			<a href="#" ${af.map.label_ytv eq 1 ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="label_ytv_class" title="1,是">易TV</a>
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
	      			<br/>
	      			<a id="update_md_serise" style="cursor:pointer;color:#4598D2;display: inline;">更新产品系列</a>
	      			<br/>
	      			<html-el:text property="md_serise_like" styleId="md_serise_like" styleClass="webinput" size="28"></html-el:text>
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
				<html-el:text property="date_begin" styleId="date_begin" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
				至
				<html-el:text property="date_end" styleId="date_end" size="9" maxlength="10" readonly="true" styleClass="webinput" style="cursor:pointer;" onclick="new Calendar(2005, 2030, 0).show(this);" />
      		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right" valign="top">型号：</td>
      		<td width="5%" align="center" valign="top"><a href="#" ${(empty af.map.model_name and empty af.map.model_name_like) ? "style='background: #FF6600;color:#fff;padding:2px;'":""} class="model_name_class" title="">全部</a></td>
      		<td width="85%" align="left">
				<html-el:text property="model_name_like" styleId="model_name_like" styleClass="webinput" size="28"></html-el:text>
      		</td>
      	</tr>
      	<tr>
      		<td width="10%" align="right" valign="top">&nbsp;</td>
      		<td width="90%" align="left" colspan="2"><html-el:button styleId="btn_submit_search" property="btn_submit_search" styleClass="buttonSubSearch" value="搜索" /></td>
      	</tr>
      </table>
    </html-el:form>
  </div>
  <div class="rtabcont1" style="font-weight:700;color:#F00;">
  	进货量：<fmt:formatNumber value="${TOTAL_IN_NUM}" pattern="#,###"/>&nbsp;台，
  	进货额：<fmt:formatNumber value="${TOTAL_IN_MONEY}" pattern="#,##0.00"/>&nbsp;元，
  	销售量：<fmt:formatNumber value="${TOTAL_OUT_NUM}" pattern="#,###"/>&nbsp;台，
  	销售额：<fmt:formatNumber value="${TOTAL_OUT_MONEY}" pattern="#,##0.00"/>&nbsp;元，
  	库存量：<fmt:formatNumber value="${TOTAL_STORE_NUM}" pattern="#,###"/>&nbsp;台，
  	库存额：<fmt:formatNumber value="${TOTAL_STORE_MON}" pattern="#,##0.00"/>&nbsp;元
  	<input type="button" value="Excel" id="export_excel" class="but_excel" style="margin-left: 10px;float:right;" />
  </div>
  <div class="rtabcont1" style="font-weight:normal;color:#A8A8A8;">注意：查询数据范围以您被授权查看数据的部门为基础. 列表查询的库存量/额均为当前实时库存数据。</div>
  <form action="${ctx}/manager/admin/KonkaMobileSailDataSearch.do" method="post">
	    <input type="hidden" name="method" value="toExcelJXC" />
	    <input type="hidden" name="hiddenName" id="hiddenName" value="进销存-部门" />
	    <input type="hidden" name="hiddenHtml" id="hiddenHtml"/>
	</form>
  <div class="rtabcont1" style="overflow-x: auto;" id="jxc_dept_div" title="销售明细">
  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
  		<tr class="tabtt1">
  			<td width="5%" align="center" nowrap="nowrap">序号</td>

        	<td width="10%" align="center" nowrap="nowrap">部门名称</td>
        	<td width="10%" align="center" nowrap="nowrap">部门级别</td>
        	<td width="10%" align="center" nowrap="nowrap">部门负责人</td>        	
            <td width="8%" nowrap="nowrap" align="center">进货量</td>
        	<td width="8%" nowrap="nowrap" align="center">进货额</td>       	
        	<td width="8%" nowrap="nowrap" align="center">销售量</td>
        	<td width="8%" nowrap="nowrap" align="center">销售额</td>  
         	<td width="8%" nowrap="nowrap" align="center">库存量</td>
        	<td width="8%" nowrap="nowrap" align="center">库存额</td>    
        	<td width="8%" nowrap="nowrap" align="center">周转天数（量）</td>
        	<td width="8%" nowrap="nowrap" align="center">周转天数（额）</td>   
  		</tr>
  		<c:forEach var="cur" items="${entityList}" varStatus="vs">
  			<tr class="list-tr">
  				<td align="center" nowrap="nowrap">${vs.count}</td>
  				<td align="center" nowrap="nowrap">
  					<a title="点击可查看该部门详情" style="cursor:pointer;" class="fblue" onclick="showCustOfDept('${cur.DEPT_ID}','${cur.DEPT_LEVEL}');">${cur.DEPT_NAME}</a>
  				</td>
  				<td align="center" nowrap="nowrap">${cur.DEPT_LEVEL}级</td>  				
  				<td align="center" nowrap="nowrap">${cur.LEADER_NAME}</td>  				
  				<td align="right" nowrap="nowrap" ><fmt:formatNumber value="${cur.IN_NUM}" pattern="#,###"/></td> 
   				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.IN_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.OUT_NUM}" pattern="#,###"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.OUT_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.STORE_NUM}" pattern="#,###"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.STORE_MONEY}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.ZZ_NUM}" pattern="#,##0.00"/></td>
  				<td align="right" nowrap="nowrap"><fmt:formatNumber value="${cur.ZZ_MONEY}" pattern="#,##0.00"/></td>
  			</tr>
  		</c:forEach>
  	</table>
  </div>
</div>
<div id="md_serise_div" style="display:none;z-index:99999;">
	<form action="${ctx}/manager/admin/KonkaMobileSailDataSearch.do" method="post" style="margin:0;padding:0;">
	    <input type="hidden" name="method" value="updateMdSerise" />
		<div class="top_div_heard">
			<div class="top_div_left">更新系列</div>
			<div class="top_div_right"><a href="javascript:void(0);" id="md_serise_div_close" title="close">关闭</a></div>
		</div>
		<div class="top_div_content">
	        <div class="div_top">
	            <div class="top_div_center">
	              <span style="color:red;">不同的产品系列之间以分号（,）分割，格式如下：<br/>系列001,系列002,系列003</span>
	            </div>
	            <div class="top_div_center">
	              <textarea name="md_serise_properties" rows="10" cols="30"></textarea>
	            </div>
	            <div class="top_div_center">
	              &nbsp;<input type="submit" id="btn_md_serise_div" class="buttonPBSearch" value="更新" />
	            </div>
	        </div>
		</div>
	</form>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/common.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/print.js"></script> 
<script type="text/javascript" src="${ctx}/styles/customer/jNotify/jNotify.jquery.js"></script> 
<script type="text/javascript" src="${ctx}/scripts/lhgdialog/lhgdialog.min.js?skin=discuz"></script>
<script type="text/javascript">

	function toJxcHomePage(){
		location.href = "KonkaMobileSailDataSearch.do?method=list&dept_id="+$("#dept_id").val()+"&l4_dept_id="+$("#l4_dept_id").val()+
					    "&l5_dept_id="+$("#l5_dept_id").val()+"&label_db="+$("#label_db").val()+"&label_int="+$("#label_int").val()+
					    "&label_4k="+$("#label_4k").val()+"&label_ytv="+$("#label_ytv").val()+"&label_3d="+$("#label_3d").val()+
					    "&md_serise="+$("#md_serise").val()+"&md_serise_like="+$("#md_serise_like").val()+"&size_sec="+$("#size_sec").val()+
					    "&date_begin="+$("#date_begin").val()+"&date_end="+$("#date_end").val()+"&model_name_like="+$("#model_name_like").val()+
					    "&search_first_flag=0";
	}
	
	function showCustOfDept(dept_id,dept_level){
		loading();
		location.href = "KonkaMobileSailDataSearch.do?method=list&dept_id="+$("#dept_id").val()+"&l4_dept_id="+$("#l4_dept_id").val()+
					    "&l5_dept_id="+$("#l5_dept_id").val()+"&label_db="+$("#label_db").val()+"&label_int="+$("#label_int").val()+
					    "&label_4k="+$("#label_4k").val()+"&label_ytv="+$("#label_ytv").val()+"&label_3d="+$("#label_3d").val()+
					    "&md_serise="+$("#md_serise").val()+"&md_serise_like="+$("#md_serise_like").val()+"&size_sec="+$("#size_sec").val()+
					    "&date_begin="+$("#date_begin").val()+"&date_end="+$("#date_end").val()+"&model_name_like="+$("#model_name_like").val()+
					    "&search_first_flag=1&t_dept_id="+dept_id+"&dept_level="+dept_level;
	}

  $(document).ready(function(){
	$("#dept_id" ).attr("dataType" , "Require").attr("msg" , "请选择分公司！");
	$("#date_begin" ).attr("dataType" , "Require").attr("msg" , "请选择开始日期！");
	$("#date_end" ).attr("dataType" , "Require").attr("msg" , "请选择结束日期！");  
	  
	var form = document.forms[0];
	
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});
	
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();

    // 导出excel
    $("#export_excel").click(function(){
    	if(confirm("提示，您确认导出数据？")){
    		var f = document.forms[1];
    		var divExcel = document.getElementById("jxc_dept_div");
    		$("#hiddenHtml").val(divExcel.innerHTML);
    		f.submit();
    	}
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
		var val = $("#dept_id").val();
		if(val!=''){
			loading();
			submitForm();
		}else{
			alert("请选择分公司！");
		}
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
		$(".label_ytv_class").removeAttr("style");
		$(".label_type_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		$("#label_db").val("");
		$("#label_int").val("");
		$("#label_3d").val("");
		$("#label_www").val("");
		$("#label_4k").val("");
		$("#label_ytv").val("");

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
		$("#label_ytv").val("");
		//产品系列关联
		$("#md_serise").val("");
		
		$(".label_db_class").removeAttr("style");
		$(".label_int_class").removeAttr("style");
		$(".label_3d_class").removeAttr("style");
		$(".label_www_class").removeAttr("style");
		$(".label_4k_class").removeAttr("style");
		$(".label_ytv_class").removeAttr("style");
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
		$("#label_ytv").val("");
		//产品系列关联
		$("#md_serise").val("");
		
		$(".label_db_class").removeAttr("style");
		$(".label_int_class").removeAttr("style");
		$(".label_3d_class").removeAttr("style");
		$(".label_www_class").removeAttr("style");
		$(".label_4k_class").removeAttr("style");
		$(".label_ytv_class").removeAttr("style");
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
		$("#label_ytv").val("");
		//产品系列关联
		$("#md_serise").val("");
		
		$(".label_db_class").removeAttr("style");
		$(".label_int_class").removeAttr("style");
		$(".label_3d_class").removeAttr("style");
		$(".label_www_class").removeAttr("style");
		$(".label_4k_class").removeAttr("style");
		$(".label_ytv_class").removeAttr("style");
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
		$("#label_ytv").val("");
		
		$(".label_db_class").removeAttr("style");
		$(".label_int_class").removeAttr("style");
		$(".label_3d_class").removeAttr("style");
		$(".label_www_class").removeAttr("style");
		$(".label_4k_class").removeAttr("style");
		$(".label_ytv_class").removeAttr("style");
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
		$("#label_ytv").val("");
		//产品系列关联
		$("#md_serise").val("");
		
		$(".label_db_class").removeAttr("style");
		$(".label_int_class").removeAttr("style");
		$(".label_3d_class").removeAttr("style");
		$(".label_www_class").removeAttr("style");
		$(".label_4k_class").removeAttr("style");
		$(".label_ytv_class").removeAttr("style");
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
	
	//易TV
	$(".label_ytv_class").click(function(){
		$("#label_db").val("");
		$("#label_int").val("");
		$("#label_3d").val("");
		$("#label_www").val("");
		$("#label_4k").val("");
		$("#label_ytv").val("");
		//产品系列关联
		$("#md_serise").val("");
		
		$(".label_db_class").removeAttr("style");
		$(".label_int_class").removeAttr("style");
		$(".label_3d_class").removeAttr("style");
		$(".label_www_class").removeAttr("style");
		$(".label_4k_class").removeAttr("style");
		$(".label_ytv_class").removeAttr("style");
		$(".label_type_class").removeAttr("style");
		$(this).attr("style","background: #FF6600;color:#fff;padding:2px;");
		
		var strs = new Array()
		strs = $(this).attr("title").split(",");
		if(strs.length == 2){
			$("#label_ytv").val(strs[0]);
		}else if(strs.length < 2){
			$("#label_ytv").val("");
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
	
	$("#filter_label_ytv").click(function(){
		$("#label_ytv").val("");
		
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
		
		$("#md_serise").val("");
		$("#md_serise_like").val("");
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${naviString}</title>
<link href="${ctx}/styles/global.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/styles/konka.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">
	<div class="oartop">
	    <table width="100%" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="3%"><img src="${ctx}/styles/admin-index/images/k_tup.jpg" style="vertical-align:middle;" /></td>
	        <td>当前位置：${naviString}</td>
	        <td width="60"><img src="${ctx}/images/manager/help.gif" style="vertical-align:middle;" /> <span id="span_help" style="cursor:pointer;">帮助</span></td>
	      </tr>
	    </table>
    </div>
	<div class="rtabcont1">
		<html-el:form action="/admin/KonkaR3Margin">
			<html-el:hidden property="method" value="list" />
			<html-el:hidden property="mod_id" />
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
				<tr>
					<td width="15"></td>
					<td><strong class="fb">月份选择：</strong>
						<html-el:select property="year" styleId="year">
		          		  	<html-el:option value="2011">2011年</html-el:option>
		          		  	<html-el:option value="2012">2012年</html-el:option>
		          		  	<html-el:option value="2013">2013年</html-el:option>
		          		  	<html-el:option value="2014">2014年</html-el:option>
		          		  	<html-el:option value="2015">2015年</html-el:option>
		          		  	<html-el:option value="2016">2016年</html-el:option>
		          		  	<html-el:option value="2017">2017年</html-el:option>
		          		  	<html-el:option value="2018">2018年</html-el:option>
		          		  	<html-el:option value="2019">2019年</html-el:option>
		          		  	<html-el:option value="2020">2020年</html-el:option>
		          		  	<html-el:option value="2021">2021年</html-el:option>
	          		  	</html-el:select>
	          		  	<html-el:select property="month" styleId="month">
					      	<html-el:option value="1">1月</html-el:option>
					      	<html-el:option value="2">2月</html-el:option>
					      	<html-el:option value="3">3月</html-el:option>
					      	<html-el:option value="4">4月</html-el:option>
					      	<html-el:option value="5">5月</html-el:option>
					      	<html-el:option value="6">6月</html-el:option>
					      	<html-el:option value="7">7月</html-el:option>
					      	<html-el:option value="8">8月</html-el:option>
					      	<html-el:option value="9">9月</html-el:option>
					      	<html-el:option value="10">10月</html-el:option>
					      	<html-el:option value="11">11月</html-el:option>
					      	<html-el:option value="12">12月</html-el:option>
				      	</html-el:select></td> 
				</tr>
				<tr>
			        <td width="15"></td>
			        <td><strong class="fb">分公司　：</strong>
			          <c:set var="disabled" value="false" />
			          <c:if test="${af.map.dept_type eq 3}">
			           	<c:set var="disabled" value="true" />
			          </c:if>
			          <html-el:select property="dept_id" styleId="dept_id" disabled="${disabled}">
			          	<html-el:option value="">-请选择-</html-el:option>
			          </html-el:select>
			          &nbsp;
			          <html-el:select property="l4_dept_id" styleId="l4_dept_id">
			          	<html-el:option value="">-请选择-</html-el:option>
			          </html-el:select>
			          &nbsp;
			          <html-el:select property="l5_dept_id" styleId="l5_dept_id">
			          	<html-el:option value="">-请选择-</html-el:option>
			          </html-el:select>
			         </td>
	        	</tr>
	        	<tr>
		        	<td width="15"></td>
		        	<td><strong class="fb">业务员　：</strong>
		        		<html-el:text property="ywy_user_name" size="15" maxlength="20" styleId="ywy_user_name"  /></td>
	        	</tr>
	        	<tr>
		        	<td width="15"></td>
		        	<td><strong class="fb">客&nbsp;&nbsp;户　：</strong>
		        		<html-el:text property="customer_name" size="15" maxlength="20" styleId="customer_name"  /></td>
	        	</tr>
	        	<tr>
		            <td width="15"></td>
		          
		            <td><html-el:submit styleClass="but1" value="搜索" /> 
		            	<input name="btn" type="button" class="but_excel" id="export_excel" value="Excel" style="margin-left:10px;" onclick="exportExcel();" /></td>
	        	</tr>
			</table>
		</html-el:form>
	</div>
	<div class="rtabcont1" style="overflow-x: auto;">
		<div id="divExcel" title="客户分析-毛利">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
			<tr class="tabtt1">
				<td width="5%" nowrap="nowrap">序号</td>
				<td nowrap="nowrap">客户名称</td>
				<td width="10%" nowrap="nowrap">客户类型</td>
				<td width="10%" nowrap="nowrap">R3销售量</td>
				<td width="5%" nowrap="nowrap">同比增幅</td>
				<td width="10%" nowrap="nowrap">R3销售额（元）</td>
				<td width="5%" nowrap="nowrap">同比增幅</td>
				<td width="10%" nowrap="nowrap">平均毛利（元）</td>
				<td width="5%" nowrap="nowrap">同比增幅</td>
				<td width="10%" nowrap="nowrap">平均单价（元）</td>
				<td width="5%" nowrap="nowrap">同比增幅</td>
			</tr>
			<c:set var="total_money" value="0" />
		  	<c:set var="total_num" value="0" />
		  	<c:forEach items="${entityList}" var="cur" varStatus="vs">
		  		<tr>
		  			<td nowrap="nowrap" align="center">${vs.count}</td>
		  			<td nowrap="nowrap" align="left">${fn:escapeXml(cur[1]) }</td>
		  			<td nowrap="nowrap" align="left">${fn:escapeXml(cur[2]) }</td>
		  			<td nowrap="nowrap" align="right">${fn:escapeXml(cur[4]) }</td>
		  			<td nowrap="nowrap" align="center">
		  				<c:if test="${empty cur[8] or cur[8] eq '-'}">-</c:if>
		  				<c:if test="${not empty cur[8] and cur[8] ne '-'}">${cur[8]}%</c:if>
		  			</td>
		  			<td nowrap="nowrap" align="right"><span class="kz-price-12"><fmt:formatNumber value="${cur[3]}" type="currency" /></span></td>
		  			<td nowrap="nowrap" align="center">
		  				<c:if test="${empty cur[7] or cur[7] eq '-'}">-</c:if>
		  				<c:if test="${not empty cur[7] and cur[7] ne '-'}">${cur[7]}%</c:if>
		  			</td>
		  			<td nowrap="nowrap" align="right"><span class="kz-price-12"><fmt:formatNumber value="${cur[5]}" type="currency" /></span></td>
		  			<td nowrap="nowrap" align="center">
		  				<c:if test="${empty cur[9] or cur[9] eq '-'}">-</c:if>
		  				<c:if test="${not empty cur[9] and cur[9] ne '-'}">${cur[9]}%</c:if>
		  			</td>
		  			<td nowrap="nowrap" align="right"><span class="kz-price-12"><fmt:formatNumber value="${cur[6]}" type="currency" /></span></td>
		  			<td nowrap="nowrap" align="center">
		  				<c:if test="${empty cur[10] or cur[10] eq '-'}">-</c:if>
		  				<c:if test="${not empty cur[10] and cur[10] ne '-'}">${cur[10]}%</c:if>
		  			</td>
		  		</tr>
		  		<c:set var="total_money" value="${total_money+cur[3] }" />
	  			<c:set var="total_num" value="${total_num+cur[4] }" />
		  	</c:forEach>
		  	<tr>
		  		<td align="right" colspan="3"><span style="font-size:14px;font-family:verdana;font-weight:700;">合计</span></td>
		  		<td align="right"><span style="font-size:14px;font-family:verdana;font-weight:700;">${total_num}</span></td>
		  		<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
		  		<td align="right"><span class="kz-price-12"><fmt:formatNumber value="${total_money}" type="currency" /></span></td>
		  		<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
		  		<td align="right"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
		  		<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
		  		<td align="right"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
		  		<td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
		  	</tr>
	  	</table>
	  	</div>
	</div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cs.js"></script>
<script type="text/javascript" src="${ctx}/scripts/print.js"></script> 
<script type="text/javascript">//<![CDATA[    
$(document).ready(function(){
	$("#span_help").click(function(){
        $("#cvtooltipClose").cvtooltip({
            panel: "#body_oarcont",
            direction: 1,                
            width: 420,
            left: 320,
            top: 5,
            speed: 500,
            delay: 10000
        });
    });
	
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});
	
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId&isNotEpp=isNotEpp", "par_id", "0", false);
	$("#dept_id").change();
	
});

function exportExcel(){
	toExcel('divExcel', '?method=toExcel');
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
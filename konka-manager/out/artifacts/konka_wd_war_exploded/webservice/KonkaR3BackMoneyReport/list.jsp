<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${naviString}</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
</head>
<body>
<div class="oarcont" id="body_oarcont">	
	<div class="rtabcont2">
		<html-el:form action="/KonkaR3BackMoneyReport">
			<html-el:hidden property="method" value="list" />
			<html-el:hidden property="mod_id" />
			<html-el:hidden property="user_id" styleId="${user_id}" />
        <html-el:hidden property="userpass" styleId="${userpass}" />
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtab2">
				<tr>
					<td><strong class="fb">月份选择：</strong>
						<html-el:select property="year" styleId="year" >
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
	          		  	<html-el:select property="month_start" styleId="month_start">
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
				      	</html-el:select>
				      	至
					    <html-el:select property="month_end" styleId="month_end">
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
					    </html-el:select>
					    <br/><br/>
					    <strong class="fb">分公司　：</strong>
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
				<!-- 
	        	<tr>
		        	<td width="15"></td>
		        	<td><strong class="fb">业务员　：</strong>
		        		<html-el:text property="ywy_user_name" size="15" maxlength="20" styleId="ywy_user_name"  />
		        		&nbsp;
		        		<strong class="fb">客&nbsp;&nbsp;户　：</strong>
		        		<html-el:text property="customer_name" size="15" maxlength="20" styleId="customer_name"  /></td>
	        	</tr> -->
                    <br/><br/>		          
		            <html-el:submit styleClass="but1" value="搜索" /> 
		            	<!--<input name="btn" type="button" class="but_excel" id="export_excel" value="Excel" style="margin-left:10px;" onclick="exportExcel();" />-->
		            	</td>
	        	</tr>
			</table>
		</html-el:form>
	</div>
	<div class="rtabcont1">
		<div id="divExcel" title="客户分析-回款">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtab2">
		<tr>
		<td align="left">分公司</td>
		 <td align="center">任务系数<br/>完成率</td>
		<td align="right">任务额<br/>回款额</td>
		</tr>
			<c:set var="total_money" value="0" />
		  	<c:forEach items="${entityList}" var="cur" varStatus="vs">
		  		<tr>
		  			<td align="left">${fn:escapeXml(cur[1]) } </td>
		  		   <td align="center">
		  			${fn:escapeXml(cur[2]) }%
		  			<br />
		  			${fn:escapeXml(cur[5]) }%
		  			</td>
		  			
		  			<td align="right"><span class="kz-price-12"><fmt:formatNumber value="${cur[3]}" type="currency"  /></span><br/>
		  			<span class="kz-price-12"><fmt:formatNumber value="${cur[4]}" type="currency" /></span></td>
		  		
		  		</tr>
		  		<c:set var="total_rw_money" value="${total_rw_money+cur[3] }" />
		  		<c:set var="total_back_money" value="${total_back_money+cur[4] }" />
		  	</c:forEach>
		  	<tr>
	  			<td align="left">合计 </td>
	  			
	  			<td align="right" colspan="2"><span class="kz-price-12"><fmt:formatNumber value="${total_rw_money}" type="currency" /></span><br/><span class="kz-price-12"><fmt:formatNumber value="${total_back_money}" type="currency" /></span></td>
	  		
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
	$("#l4_dept_id"    ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	
	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();
	
	$(".but1").click(function(){

		if(parseInt($("#month_end").val()) < parseInt($("#month_start").val())){
			alert("结束月份必须大于开始月份！");
			return false;
		}
	});
	
});

function exportExcel(){
	toExcel('divExcel', '?method=toExcel');
}

//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>
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
    <html-el:form action="/admin/KonkaSellUnReportDetail">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtable1">
        <tr>
          <td width="15"></td>
          <td colspan="3"><strong class="fb">分公司　：</strong>
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
          <td><strong class="fb">销售时间：</strong>
			      <html-el:text property="sell_date_start" styleId="sell_date_start"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
			              至
			      <html-el:text property="sell_date_end" styleId="sell_date_end"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
          <td><strong class="fb">门店名称：</strong>
            <html-el:text property="store_name_like" size="15" maxlength="20" styleId="store_name_like"  /></td>
          <td style="display:none;"><strong class="fb">促销员　：</strong>
            <html-el:text property="cxy_user_name_like" size="15" maxlength="20" styleId="cxy_user_name_like"  /></td>   
        </tr>
        <tr>
          <td width="15"></td>
          <td ><strong class="fb">是否申报：</strong>
            <html-el:select property="is_report">
              <html-el:option value="">请选择申报状态</html-el:option>
              <html-el:option value="0">未申报</html-el:option>
              <html-el:option value="1">已申报</html-el:option>
            </html-el:select></td>
          
          <td colspan="2"><html-el:submit styleClass="but1" value="搜索" />
          	<input name="btn" type="button" class="but_excel" id="export_excel" value="Excel" style="margin-left:10px;" onclick="exportExcel();" /> </td>
        </tr>
      </table>
    </html-el:form>
  </div>
    <div class="rtabcont1">
    <c:if test="${not empty entityList }">
	    <c:if test="${not empty af.map.sell_date_start && not empty af.map.sell_date_end }">
	    	<c:set var="title_time" value="--${af.map.sell_date_start }到${af.map.sell_date_end }" />
	    </c:if>
	    <c:if test="${empty af.map.sell_date_start && not empty af.map.sell_date_end }">
	    	<c:set var="title_time" value="--截止至${af.map.sell_date_end }" />
	    </c:if>
	    <c:if test="${not empty af.map.sell_date_start && empty af.map.sell_date_end }">
	    	<c:set var="title_time" value="--${af.map.sell_date_start }截止至今" />
	    </c:if>
	    <c:if test="${empty af.map.sell_date_start && empty af.map.sell_date_end }">
	    	<c:set var="title_time" value="--全部时间" />
	    </c:if>
    <div id="divExcel" title="销售申报情况${title_time}">
	    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="rtable2">
	      <tr class="tabtt1">
	        <td width="5%" align="center" nowrap="nowrap">序号</td>
	        <td nowrap="nowrap" align="center">分公司</td>
	        <td width="10%" align="center" nowrap="nowrap">业务员</td>
	        <td width="10%" align="center" nowrap="nowrap">门店名称</td>
	        <td width="10%" align="center" nowrap="nowrap">促销员</td>
	        <td width="5%" align="center" nowrap="nowrap">是否申报</td>
	        <td width="5%" align="center" nowrap="nowrap">申报次数</td>
	        <td width="5%" align="center" nowrap="nowrap">申报金额</td>
	        <td width="5%" align="center" nowrap="nowrap">申报数量</td>
	        <td width="5%" align="center" nowrap="nowrap">日期</td>
	      </tr>
	      <c:set var="total_count" value="0" />
	      <c:set var="total_money" value="0" />
		  <c:set var="total_num" value="0" />
	      <c:forEach items="${entityList}" var="cur" varStatus="vs">
	        <tr>
	          <td align="center">${vs.count}</td>
	          <td align="left" nowrap="nowrap">${cur[2]}&nbsp;&nbsp;${cur[4]}&nbsp;&nbsp;${cur[6]}</td>
	          <td align="center" nowrap="nowrap">${cur[7]}</td>
	          <td align="left" nowrap="nowrap">${cur[14]}</td>
	          <td align="center" nowrap="nowrap">${cur[21]}</td>
	          <td align="center" nowrap="nowrap">
			  	<c:choose>
	          		<c:when test="${cur[18] eq 0}">未申报</c:when>
	          		<c:when test="${cur[18] eq 1}">已申报</c:when>
	          		<c:otherwise>状态错误</c:otherwise>
	          	</c:choose>
			  </td>
	          <td align="center" nowrap="nowrap">${cur[18]}</td>
	          <td align="right" nowrap="nowrap"><span class="kz-price-12"><fmt:formatNumber value="${cur[19]}" type="currency" /></span></td>
	          <td align="right" nowrap="nowrap">${cur[20]}</td>
	          <td align="center" nowrap="nowrap">
<%-- 	          <c:if test="${!empty cur[16]}">${cur[16]}</c:if> --%>
<%-- 	          <c:if test="${empty cur[16]}">${af.map.sell_date_start}~${af.map.sell_date_end}</c:if> --%>
	          ${cur[16]}
	          </td>
	        </tr>
	        <c:set var="total_count" value="${total_count+cur[18]}" />
	      	<c:set var="total_money" value="${total_money+cur[19]}" />
		  	<c:set var="total_num" value="${total_num+cur[20]}" />
	      </c:forEach>
	      	<tr id="total_tr">
	          <td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">合计</span></td>
	          <td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
	          <td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
	          <td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
	          <td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
	          <td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
	          <td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">${total_count}</span></td>
	          <td align="right"><span class="kz-price"><fmt:formatNumber value="${total_money}" type="currency" /></span></td>
	          <td align="right"><span style="font-size:14px;font-family:verdana;font-weight:700;">${total_num}</span></td>
	          <td align="center"><span style="font-size:14px;font-family:verdana;font-weight:700;">/</span></td>
	        </tr>
	    </table>
    </div>
    </c:if>
  </div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/rowEffect.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/calendar.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script> 
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
	
	
	$("#dept_id").attr("dataType", "Require").attr("msg","请选择分公司！");
	$("#sell_date_start").attr("dataType", "Require").attr("msg","请选择开始时间！");
	$("#sell_date_end").attr("dataType", "Require").attr("msg","请选择结束时间！");
	$("form").eq(0).submit(function(){

		return Validator.Validate(this, 2);
	});
	
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#dept_id").cs("${ctx}/manager/admin/CsAjax.do?method=getKonkaDeptForParId", "par_id", "0", false);
	$("#dept_id").change();
	
	<c:if test="${not empty entityList }">
		$("#total_tr").clone(true).insertAfter($(".tabtt1"));
	</c:if>
	
});

function chkReport(type){
	if(type != "" && type == 0){
		$("#sell_date_start").val("");
		$("#sell_date_end").val("");
		document.getElementById("sell_date_start").disabled = true;
		document.getElementById("sell_date_end").disabled = true;
	}else {
		document.getElementById("sell_date_start").disabled = false;
		document.getElementById("sell_date_end").disabled = false;
	}
}

function exportExcel(){
	toExcel('divExcel', '?method=toExcel');
}


//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>



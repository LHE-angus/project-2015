<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>零售申报统计</title>
</head>
<link rel="stylesheet" type="text/css"
	href="${ctx}/mobile/css/common.css" />
	
	<style type="text/css">
	table.rtab1 td{margin-top:5px;padding:5px 5px;}
	</style>
<body>
<div class="oarcont">
  <div class="rtabcont2">
    <html-el:form action="/KonkaSellUnReport">
      <html-el:hidden property="method" value="list" />
      <html-el:hidden property="mod_id" />
       <html-el:hidden property="user_id"  value="${user_id}"/>
        <html-el:hidden property="userpass" value="${userpass}" />
      <table width="100%" border="0" cellspacing="1" cellpadding="0" class="rtab2">
        <tr>
         
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
<%--           	<html-el:select property="l5_dept_id" styleId="l5_dept_id"> --%>
<%--           		<html-el:option value="">-请选择-</html-el:option> --%>
<%--           	</html-el:select> --%>
          </td>
        </tr>
        <tr>
       
          <td><strong class="fb">销售时间：</strong>
			      <html-el:text property="sell_date_start" styleId="sell_date_start"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" />
			              至
			      <html-el:text property="sell_date_end" styleId="sell_date_end"  size="10" maxlength="10" readonly="true" onclick="new Calendar(2011, 2021, 0).show(this);" style="cursor:pointer;text-align:center;" title="点击选择日期" /></td>
         
      
        </tr>
        <tr>
      
         <td><strong class="fb">门店名称：</strong>
            <html-el:text property="store_name_like" size="15" maxlength="20" styleId="store_name_like"  /></td>
        </tr>
        <tr>
       
          <td >
          
          <strong class="fb">是否申报：</strong>
            <html-el:select property="is_report">
              <html-el:option value="">请选择申报状态</html-el:option>
              <html-el:option value="0">未申报</html-el:option>
              <html-el:option value="1">已申报</html-el:option>
            </html-el:select>
            
           &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
              <html-el:submit styleClass="but1" value="搜索" />
        
            
            </td>
          </tr>
      </table>
    </html-el:form>
  </div>
    <div>
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
  
    <div style="overflow-x: auto">
	    <table width="100%" border="0" class="rtab1">
	      <c:set var="total_count" value="0" />
	      <c:set var="total_money" value="0" />
		  <c:set var="total_num" value="0" />
	      <c:forEach items="${entityList}" var="cur" varStatus="vs">
	        <tr styke="border-bottom: solid 1px blue;padding:10px;margin:10px 10px;">
	        <td width="15%">
	        <c:choose>
	       <c:when test="${cur[15] eq 0}">
	       <img width="50px;" src="${ctx}/styles/images/false.png" alt="未申报" />
	       </c:when>
	       <c:when test="${cur[15] eq 1}"> 
	       <img width="50px;" src="${ctx}/styles/images/right.png" alt="已申报" />
	       </c:when>
	       <c:otherwise><img width="50px;" src="${ctx}/styles/images/false.png" alt="状态不明" /></c:otherwise>
	       </c:choose>
	        </td>
	         
	         <td align="left"><strong>${cur[14]}</strong><br/>
	          ${cur[4]}:&nbsp; ${cur[7]}<br/>${cur[22]}<br />
			    </td>
			<td align="right">${cur[16]}次<br /> <span
				class="kz-price-12"><font color="red"><fmt:formatNumber
							value="${cur[17]}" type="currency" /></font></span><br />
							 ${cur[18]}台<br />
			</td>
							</tr>
	        <c:set var="total_count" value="${total_count+cur[16]}" />
	      	<c:set var="total_money" value="${total_money+cur[17] }" />
		  	<c:set var="total_num" value="${total_num+cur[18] }" />
	      </c:forEach>
	      	<tr id="total_tr">
	      	<td align="center">合计</td>
		           <td aligh="left">
		          
	       	  <c:if test="${not empty af.map.sell_date_start && not empty af.map.sell_date_end }">
	          		${af.map.sell_date_start }~${af.map.sell_date_end }
	          	</c:if>
	          	<c:if test="${empty af.map.sell_date_start && not empty af.map.sell_date_end }">
			    	截止至${af.map.sell_date_end }
			    </c:if>
			    <c:if test="${not empty af.map.sell_date_start && empty af.map.sell_date_end }">
			    	${af.map.sell_date_start }截止至今
			    </c:if>
			    <c:if test="${empty af.map.sell_date_start && empty af.map.sell_date_end }">
			    	全部时间
			    </c:if>
		           
		           </td>
		            <td align="right">
                                         ${total_count}次<br />
          <fmt:formatNumber value="${total_money}" type="currency" /><br />
                                    ${total_num}台
	        </td>
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
	$("#dept_id").attr("dataType", "Require").attr("msg","请选择分公司！");
	$("form").eq(0).submit(function(){

		return Validator.Validate(this, 2);
	});
	
	$("#dept_id").attr({"subElement": "l4_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.dept_id}"});
	$("#l4_dept_id"    ).attr({"subElement": "l5_dept_id", "defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l4_dept_id}"});
	$("#l5_dept_id" ).attr({"defaultText": "-请选择-", "defaultValue": "", "selectedValue": "${af.map.l5_dept_id}"});

	$("#dept_id").cs("${ctx}/webservice/KonkaSellUnReport.do?method=getKonkaDeptForParId", "par_id", "0", false);
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



//]]></script>
<jsp:include page="/__analytics.jsp" />
</body>
</html>



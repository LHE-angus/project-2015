<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/pages/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="MSThemeCompatible" content="no" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
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
        <td nowrap="nowrap">当前位置：${naviString}</td>
      </tr>
    </table>
  </div>
  <div style="line-height:30px;margin:0px 7px;">账户余额 | <a href="${ctx}/manager/zmd/KonkaXxZmdAccDetails.do?method=list&mod_id=${af.map.mod_id}&zmd_id=${konkaXxZmd.zmd_id}" class="fblue">收支明细</a></div>
  <c:if test="${is_dept eq 1}">
  	<div class="rtabcont1">
	    <html-el:form action="/zmd/KonkaXxZmdAccDetails" styleClass="acc_details_form">
	      <html-el:hidden property="mod_id" value="${af.map.mod_id}" />
	      <table width="100%" border="0" cellspacing="5" cellpadding="0" class="rtable1">
	        <tr>
	          <td width="15">&nbsp;</td>            
	          <td width="500"><strong class="fb">专卖店编号：</strong><html-el:select property="zmd_id" style="width:154px;" styleId="zmd_id">
	              	<html-el:option value="">==请选择==</html-el:option>
	           		<c:forEach var="cur" items="${zmdList}">
	           			<html-el:option value="${cur.zmd_id}">${cur.zmd_sn}</html-el:option>
	           		</c:forEach>
	              </html-el:select></td>
	        <td align="left"><input class="but1" type="button" name="" value="搜索" id="btn_sub" /></td>
	        </tr>
	      </table>
	    </html-el:form>
  	</div>
  </c:if>
  <div class="rtabcont1">
    <%@ include file="/commons/pages/messages.jsp" %>
  </div>
  <c:if test="${not empty konkaXxZmd}">
	 <div class="rtabcont1">
	   <table width="100%" border="0" cellspacing="0" cellpadding="0" class="rtable2">
	   	 <tr>
	       <td style="width:17%;text-align:right;">专卖店编号：</td>
	       <td style="font-weight:bold;font-size:14px;height:30px;text-align:left;">${konkaXxZmd.zmd_sn}</td>
	     </tr>
	     <tr>
	       <td style="height:30px;text-align:right;">信用额度：</td>
	       <td class="kz-price"><fmt:formatNumber value="${konkaXxZmd.cur_credit_line}" type="currency" /></td>
	     </tr>
	   	 <tr>
	       <td style="height:40px;text-align:right;">余额：</td>
	       <td class="kz-price"><fmt:formatNumber value="${konkaXxZmd.total_credit_line}" type="currency" /></td>
	     </tr>
	   </table>
	 </div>
  </c:if>
  <div class="clear"></div>
</div>
<div id="cvtooltipClose" style="display: none; line-height: 24px;">${helpString}</div>
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.js"></script> 
<script type="text/javascript" src="${ctx}/commons/scripts/jquery.cvtooltip.js"></script>
<script type="text/javascript" src="${ctx}/commons/scripts/validator.js"></script>
<script type="text/javascript">//<![CDATA[
$(document).ready(function(){
	$("#zmd_id").attr("dataType" , "Require").attr("msg" , "选择专卖店！");
	
	$("#btn_sub").click(function (){
		if(Validator.Validate(this.form, 3)){
			$(".acc_details_form").submit();
		}
	});
});
//]]></script>
<jsp:include page="../__message/message.jsp" flush="true" />
<jsp:include page="/__analytics.jsp" />
</body>
</html>